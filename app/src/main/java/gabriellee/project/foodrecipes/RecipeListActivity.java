package gabriellee.project.foodrecipes;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Parcelable;
import android.support.annotation.Nullable;
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
import gabriellee.project.foodrecipes.util.Testing;
import gabriellee.project.foodrecipes.viewmodels.RecipeListViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeListActivity extends BaseActivity {

    private static final String TAG = "RecipeListActivity";

    private RecipeListViewModel mRecipeListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        mRecipeListViewModel = ViewModelProviders.of(this).get(RecipeListViewModel.class);
        subscribeObservers();
        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testRetrofitRequest();
            }
        });
    }

    public void searchRecipesApi(String query, int pageNumber) {
        mRecipeListViewModel.searchRecipesApi(query, pageNumber);
    }

    private void subscribeObservers() {
        mRecipeListViewModel.getRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(@Nullable List<Recipe> recipes) {
                if(recipes != null) {
                    for(Recipe recipe: recipes){
                        Testing.printRecipes(recipes, "recipes test");
                    }
                }

            }
        });
    }

    private void testRetrofitRequest() {
       searchRecipesApi("chicken breast", 1);
    }

}
