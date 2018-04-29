package dawidzior.bakingapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import dawidzior.bakingapp.R;
import dawidzior.bakingapp.adapter.StepsAdapter;
import dawidzior.bakingapp.fragment.StepFragment;
import dawidzior.bakingapp.model.Step;

public class RecipeActivity extends AppCompatActivity implements StepsAdapter.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
    }

    @Override
    public void onStepListClick(Step step) {
        getSupportFragmentManager().beginTransaction().replace(R.id.step_fragment_container, StepFragment.newInstance
                (step)).commit();
    }
}
