package gabriellee.project.foodrecipes;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import gabriellee.project.foodrecipes.models.Recipe;
import gabriellee.project.foodrecipes.requests.RecipeApi;
import gabriellee.project.foodrecipes.requests.ServiceGenerator;
import gabriellee.project.foodrecipes.requests.response.RecipeResponse;
import gabriellee.project.foodrecipes.requests.response.RecipeSearchResponse;
import gabriellee.project.foodrecipes.util.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeListActivity extends BaseActivity {

    private static final String TAG = "RecipeListActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testRetrofitRequest();
            }
        });
    }

    private void testRetrofitRequest() {
        RecipeApi recipeApi = ServiceGenerator.getRecipeApi();

//        Call<RecipeSearchResponse> responseCall = recipeApi.searchRecipe(
//                Constants.API_KEY,
//                "chicken breast",
//                "1"
//        );
//
//        responseCall.enqueue(new Callback<RecipeSearchResponse>() {
//            @Override
//            public void onResponse(Call<RecipeSearchResponse> call, Response<RecipeSearchResponse> response) {
//                Log.d(TAG, "onResponse: server response " + response.toString());
//                if(response.code() == 200){
//                    Log.d(TAG, "onResponse: " +response.body().toString());
//                    List<Recipe> recipes = new ArrayList<>(response.body().getRecipes());
//                    for(Recipe recipe: recipes) {
//                        Log.d(TAG, "onResponse: " +recipe.getTitle());
//
//                    }
//                }
//                else {
//                    try{
//                        Log.d(TAG, "onResponse: " + response.errorBody().string());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<RecipeSearchResponse> call, Throwable t) {
//
//            }
//        });

            Call<RecipeResponse> responseCall = recipeApi.getRecipe(
            Constants.API_KEY,
            "8c0314"
        );

        responseCall.enqueue(new Callback<RecipeResponse>() {
            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                Log.d(TAG, "onResponse: server response" +response.body().toString());

                if(response.code() == 200){
                    Log.d(TAG, "onResponse: " +response.body().toString());
                    Recipe recipe = response.body().getRecipe();
                    Log.d(TAG, "onResponse: RETRIEVED RECIPE: " + recipe.toString());
                }
                else {
                    try{
                        Log.d(TAG, "onResponse: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {

            }
        });


    }

}