package dawidzior.bakingapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dawidzior.bakingapp.R;
import dawidzior.bakingapp.fragment.StepFragment;
import dawidzior.bakingapp.model.Step;

public class StepActivity extends AppCompatActivity {

    @BindView(R.id.previous_button)
    Button previousButton;

    @BindView(R.id.next_button)
    Button nextButton;

    private List<Step> steps;

    private int stepNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        steps = Parcels.unwrap(getIntent().getParcelableExtra(StepFragment.STEPS_LIST));
        stepNumber = getIntent().getIntExtra(StepFragment.STEP_NUMBER, 0);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, StepFragment.newInstance(steps.get(stepNumber))).commit();

        if (stepNumber == 0) previousButton.setEnabled(false);
        if (stepNumber == steps.size() - 1) nextButton.setEnabled(false);

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stepNumber--;
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, StepFragment
                        .newInstance(steps.get(stepNumber))).commit();

                if (stepNumber - 1 < 0) {
                    previousButton.setEnabled(false);
                }
                nextButton.setEnabled(true);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stepNumber++;
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, StepFragment
                        .newInstance(steps.get(stepNumber))).commit();

                if (stepNumber + 1 > steps.size() - 1) {
                    nextButton.setEnabled(false);
                }
                previousButton.setEnabled(true);
            }
        });
    }
}
