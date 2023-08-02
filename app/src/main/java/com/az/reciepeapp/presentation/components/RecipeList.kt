package com.az.reciepeapp.presentation.components

import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.az.reciepeapp.R
import com.az.reciepeapp.domain.model.Recipe
import com.az.reciepeapp.presentation.components.utils.SnackbarController
import com.az.reciepeapp.presentation.ui.recipe_list.PAGE_SIZE
import com.az.reciepeapp.presentation.ui.recipe_list.RecipeListEvent
import kotlinx.coroutines.launch

@Composable
fun RecipeList(
    loading: Boolean, recipes: List<Recipe>,
    onChangRecipeScrollPosition: (Int) -> Unit, page: Int,
    onTriggerEvent: (RecipeListEvent) -> Unit, scaffoldState: ScaffoldState,
    snackbarController: SnackbarController,
    navController: NavController
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.surface)
    ) {
        if (loading
            && recipes.isEmpty()
        ) {
            LazyColumn() {

                items(5) { index ->
                    ShimmerRecipeCard(
                        color = listOf(
                            Color.LightGray.copy(alpha = 0.9f),
                            Color.LightGray.copy(alpha = 0.2f),
                            Color.LightGray.copy(alpha = 0.9f)
                        ),
                        imageHeight = 250.dp
                    )
                }

            }
        } else {
            LazyColumn {

                itemsIndexed(items = recipes) { index, recipe ->
                    onChangRecipeScrollPosition(index)
                    if ((index + 1) >=
                        (page * PAGE_SIZE)
                        && !loading
                    ) {
                        onTriggerEvent(RecipeListEvent.NextPageEvent)
                    }
                    RecipeCard(recipe = recipe, onClick = {

                        if (recipe.id != null) {
                            val bundle = Bundle()
                            bundle.putInt("recipeId", recipe.id)
                            navController.navigate(
                                R.id.action_recipeListFragment_to_recipeFragment,
                                bundle
                            )

                        } else {
                            snackbarController.getScope().launch {

                                snackbarController.showSnackbar(
                                    scaffoldState = scaffoldState,
                                    mess = "Recipe Error",
                                    actionLabel = "OK"
                                )
                            }
                        }
                    })

                }
            }
        }

        CircularProgressBar(isDisplayed = loading)
        DefaultSnackbar(
            snackbarHostState = scaffoldState.snackbarHostState,
            onDismiss = {
                scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
            }, modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}