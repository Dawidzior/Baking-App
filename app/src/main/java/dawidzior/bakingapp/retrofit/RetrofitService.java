package dawidzior.bakingapp.retrofit;

import java.util.List;

import dawidzior.bakingapp.model.Recipe;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitService {
    @GET("baking.json")
    Call<List<Recipe>> getRecipes();
}
