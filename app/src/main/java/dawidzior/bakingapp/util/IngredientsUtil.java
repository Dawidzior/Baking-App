package dawidzior.bakingapp.util;

public class IngredientsUtil {

    public static String getMainRecipeImageUrl(String name) {
        switch (name) {
            case "Nutella Pie":
                return "https://www.recipeboy.com/wp-content/uploads/2016/09/No-Bake-Nutella-Pie.jpg";
            case "Brownies":
                return "https://food.fnr.sndimg.com/content/dam/images/food/fullset/2016/2/18/1/FNK_Brownie-Guide-Classic-Brownies_s4x3.jpg.rend.hgtvcom.616.462.suffix/1456176242492.jpeg";
            case "Yellow Cake":
                return "https://dessertswithbenefits.com/wp-content/uploads/2014/01/33.jpg";
            case "Cheesecake":
                return "https://www.seriouseats.com/recipes/images/2017/06/20170526-no-bake-cheesecake-vicky-wasik-18-1500x1125.jpg";
        }
        return null;
    }
}
