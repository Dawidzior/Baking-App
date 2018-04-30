package dawidzior.bakingapp;

import com.google.gson.Gson;

import dawidzior.bakingapp.model.Recipe;
import dawidzior.bakingapp.model.Step;

public class TestUtils {

    public static Recipe getRecipeJson() {
        return new Gson().fromJson(JSON, Recipe.class);
    }

    public static Step getStepJson() {
        return new Gson().fromJson(JSON, Step.class);
    }

    private final static String JSON = "{\n" +
            "    \"id\": 3,\n" +
            "    \"name\": \"Yellow Cake\",\n" +
            "    \"ingredients\": [\n" +
            "      {\n" +
            "        \"quantity\": 400,\n" +
            "        \"measure\": \"G\",\n" +
            "        \"ingredient\": \"sifted cake flour\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"quantity\": 700,\n" +
            "        \"measure\": \"G\",\n" +
            "        \"ingredient\": \"granulated sugar\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"quantity\": 4,\n" +
            "        \"measure\": \"TSP\",\n" +
            "        \"ingredient\": \"baking powder\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"quantity\": 1.5,\n" +
            "        \"measure\": \"TSP\",\n" +
            "        \"ingredient\": \"salt\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"steps\": [\n" +
            "      {\n" +
            "        \"id\": 0,\n" +
            "        \"shortDescription\": \"Recipe Introduction\",\n" +
            "        \"description\": \"Recipe Introduction\",\n" +
            "        \"videoURL\": \"https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffddf0_-intro-yellow-cake/-intro-yellow-cake.mp4\",\n" +
            "        \"thumbnailURL\": \"\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 1,\n" +
            "        \"shortDescription\": \"Starting prep\",\n" +
            "        \"description\": \"1. Preheat the oven to 350\\u00b0F. Butter the bottoms and sides of two 9\\\" round pans with 2\\\"-high sides. Cover the bottoms of the pans with rounds of parchment paper, and butter the paper as well.\",\n" +
            "        \"videoURL\": \"\",\n" +
            "        \"thumbnailURL\": \"\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 2,\n" +
            "        \"shortDescription\": \"Combine dry ingredients.\",\n" +
            "        \"description\": \"2. Combine the cake flour, 400 grams (2 cups) of sugar, baking powder, and 1 teaspoon of salt in the bowl of a stand mixer. Using the paddle attachment, beat at low speed until the dry ingredients are mixed together, about one minute\",\n" +
            "        \"videoURL\": \"https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffde28_1-mix-all-dry-ingredients-yellow-cake/1-mix-all-dry-ingredients-yellow-cake.mp4\",\n" +
            "        \"thumbnailURL\": \"\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 8,\n" +
            "        \"shortDescription\": \"Begin making buttercream.\",\n" +
            "        \"description\": \"8. Once the cake is cool, it's time to make the buttercream. You'll start by bringing an inch of water to a boil in a small saucepan. You'll want to use a saucepan that is small enough that when you set the bowl of your stand mixer in it, the bowl does not touch the bottom of the pot.\",\n" +
            "        \"videoURL\": \"\",\n" +
            "        \"thumbnailURL\": \"\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"servings\": 8,\n" +
            "    \"image\": \"\"\n" +
            "  }";
}
