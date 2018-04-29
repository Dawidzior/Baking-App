package dawidzior.bakingapp.activity;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.parceler.Parcels;

import java.util.ArrayList;

import dawidzior.bakingapp.R;
import dawidzior.bakingapp.TestUtils;
import dawidzior.bakingapp.fragment.StepFragment;
import dawidzior.bakingapp.model.Step;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class StepActivityTest {

    @Rule
    public ActivityTestRule<StepActivity> stepActivityTestRule =
            new ActivityTestRule<>(StepActivity.class, true, false);

    @Before
    public void setUp() {
        Step step = TestUtils.getStepJson();
        ArrayList<Step> stepList = new ArrayList<>();
        stepList.add(step);
        Intent intent = new Intent();
        intent.putExtra(StepFragment.STEPS_LIST, Parcels.wrap(stepList));
        intent.putExtra(StepFragment.STEP_NUMBER, 0);

        stepActivityTestRule.launchActivity(intent);
    }

    @Test
    public void areButtonsVisible() {
        onView(withId(R.id.buttons_container)).check(matches(isDisplayed()));
    }

    @Test
    public void isDescriptionVisible() {
        onView(withId(R.id.description_view)).check(matches(isDisplayed()));
    }
}