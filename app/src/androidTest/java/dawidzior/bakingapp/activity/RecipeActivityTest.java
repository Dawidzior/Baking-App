package dawidzior.bakingapp.activity;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.parceler.Parcels;

import dawidzior.bakingapp.R;
import dawidzior.bakingapp.TestUtils;
import dawidzior.bakingapp.fragment.RecipeFragment;
import dawidzior.bakingapp.model.Recipe;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class RecipeActivityTest {

    @Rule
    public ActivityTestRule<RecipeActivity> recipeActivityTestRule =
            new ActivityTestRule<>(RecipeActivity.class, true, false);

    @Before
    public void setUp() {
        Recipe recipe = TestUtils.getRecipeJson();
        Intent intent = new Intent();
        intent.putExtra(RecipeFragment.RECIPE_ARGUMENT, Parcels.wrap(recipe));
        recipeActivityTestRule.launchActivity(intent);
    }

    @Test
    public void isTextDisplayed() {
        onView(withText("Ingredients:")).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void isIngredientListDisplayed() {
        onView(withId(R.id.ingredients_list)).check(matches(isDisplayed()));
    }

    @Test
    public void isExoPlayerNotVisible() {
        onView(withId(R.id.player_view)).check(doesNotExist());
    }

    @Test
    public void isStepsListDisplayed() {
        onView(withId(R.id.ingredients_list)).perform(swipeUp());
        onView(withId(R.id.steps_list)).check(matches(isDisplayed()));
    }
}