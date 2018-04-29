package dawidzior.bakingapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.List;

import dawidzior.bakingapp.R;
import dawidzior.bakingapp.activity.RecipeActivity;
import dawidzior.bakingapp.fragment.RecipeFragment;
import dawidzior.bakingapp.model.Recipe;
import dawidzior.bakingapp.util.IngredientsUtil;

public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.ViewHolder> {

    private List<Recipe> recipes;

    private Context context;

    public MainListAdapter(Context context, List<Recipe> items) {
        this.context = context;
        recipes = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.recipeName.setText(recipes.get(position).getName());

        //NoFade() to avoid ugly placeholder stretching.
        Picasso.with(context).load(IngredientsUtil.getMainRecipeImageUrl(recipes.get(position).getName()))
                .placeholder(R.drawable.ic_restaurant_black).error(R.drawable.ic_error_black).fit().centerCrop()
                .noFade()
                .into(holder.recipeImage);

        holder.itemView.setTag(recipes.get(position));
        holder.itemView.setOnClickListener(mOnClickListener);
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView recipeName;
        final ImageView recipeImage;

        ViewHolder(View view) {
            super(view);
            recipeName = view.findViewById(R.id.recipe_name);
            recipeImage = view.findViewById(R.id.recipe_image);
        }
    }

    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Recipe recipe = (Recipe) view.getTag();
            Intent intent = new Intent(context, RecipeActivity.class);
            intent.putExtra(RecipeFragment.RECIPE_ARGUMENT, Parcels.wrap(recipe));
            context.startActivity(intent);
        }
    };
}