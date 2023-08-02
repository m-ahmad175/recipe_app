package com.az.reciepeapp.presentation.ui.recipe

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.az.reciepeapp.presentation.BaseApplication
import com.az.reciepeapp.presentation.components.CircularProgressBar
import com.az.reciepeapp.presentation.components.RecipeDetailView
import com.az.reciepeapp.presentation.components.ShimmerRecipeCard
import com.az.reciepeapp.presentation.components.ShimmerRecipeDetail
import com.az.reciepeapp.presentation.components.utils.SnackbarController
import com.az.reciepeapp.presentation.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class RecipeFragment : Fragment() {

    @Inject
    lateinit var application: BaseApplication

    private var recipId = -1
    private val viewModel: RecipeViewModel by viewModels()

    private val snackbarController = SnackbarController(lifecycleScope)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.getInt("recipeId")?.let {
            this.recipId = it

            viewModel.onTriggerEvent(RecipeEvent.GetRecipeEvent(recipId))
        }
        Log.e("recipeId: ", recipId.toString())

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {


            setContent {

                val loading = viewModel.loading.value
                val recipe = viewModel.recipe.value
                val scaffoldState = rememberScaffoldState()

                AppTheme(darkTheme = application.isDark.value) {
                    Scaffold(scaffoldState = scaffoldState, snackbarHost = {}) {
                        scaffoldState.snackbarHostState
                    }

                    Box(modifier = Modifier.fillMaxSize()) {

                        if (!loading && recipe != null) {
                            RecipeDetailView(recipe = recipe!!)
                        } else {
                            ShimmerRecipeDetail(
                                color = listOf(
                                    Color.LightGray.copy(alpha = 0.9f),
                                    Color.LightGray.copy(alpha = 0.2f),
                                    Color.LightGray.copy(alpha = 0.9f)
                                ),
                                imageHeight = 300.dp
                            )
                        }
                        CircularProgressBar(isDisplayed = loading)
                    }
                }
            }
        }
    }

}