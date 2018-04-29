package dawidzior.bakingapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import dawidzior.bakingapp.R;
import dawidzior.bakingapp.adapter.IngredientsAdapter;
import dawidzior.bakingapp.adapter.StepsAdapter;
import dawidzior.bakingapp.model.Recipe;
import lombok.Getter;

public class RecipeFragment extends Fragment {

    public static final String RECIPE_ARGUMENT = "RECIPE_ARGUMENT";

    @BindView(R.id.nested_scroll_view)
    NestedScrollView nestedScrollView;

    @BindView(R.id.ingredients_list)
    RecyclerView ingredientsListView;

    @BindView(R.id.steps_list)
    RecyclerView stepsListView;

    @Getter
    private StepsAdapter stepsAdapter;

    private Recipe recipe;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            recipe = Parcels.unwrap(getActivity().getIntent().getParcelableExtra(RECIPE_ARGUMENT));
        } else {
            recipe = Parcels.unwrap(savedInstanceState.getParcelable(RECIPE_ARGUMENT));
        }
        getActivity().setTitle(recipe.getName());

        stepsAdapter = new StepsAdapter(getContext(), recipe.getSteps());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_recipe, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        nestedScrollView.setNestedScrollingEnabled(true);
        ingredientsListView.setAdapter(new IngredientsAdapter(recipe.getIngredients()));
        stepsListView.setAdapter(stepsAdapter);
    }
}