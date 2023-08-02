package com.az.reciepeapp.presentation.ui.recipe_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.az.reciepeapp.presentation.BaseApplication
import com.az.reciepeapp.presentation.components.*
import com.az.reciepeapp.presentation.components.utils.SnackbarController
import com.az.reciepeapp.presentation.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    @Inject
    lateinit var application: BaseApplication

    private val snackbarController = SnackbarController(lifecycleScope)

    val viewmodel: RecipeListViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        println(viewmodel)
        Log.e("ahmad", "$viewmodel")
    }

    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return ComposeView(requireContext()).apply {

            setContent {

                val scaffoldState = rememberScaffoldState()

                AppTheme(darkTheme = application.isDark.value) {

                    val recipes = viewmodel.recipes.value
                    val query = viewmodel.query.value
                    val selectedCategory = viewmodel.selectedCategory.value

                    Scaffold(topBar = {
                        SearchAppBar(
                            query = query,
                            onQueryChanged = viewmodel::onQueryChanged,
                            onExecuteSearch = {

                                if (viewmodel.selectedCategory.value?.value == "Milk") {
                                    snackbarController.getScope().launch {
                                        snackbarController.showSnackbar(
                                            scaffoldState = scaffoldState,
                                            mess = "Invalid Category: Milk",
                                            actionLabel = "Hide",
                                        )
                                    }
                                } else {
                                    viewmodel.onTriggerEvent(RecipeListEvent.NewSearchEvent)
                                }
                            },
                            selectedCategory = selectedCategory,
                            onSelectedCategoryChanged = viewmodel::onSelectedCategoryChanged,
                            onToggleTheme = {
                                application.toggleLightTheme()
                            },

                            )
                    },
                        scaffoldState = scaffoldState,
                        snackbarHost = {

                            scaffoldState.snackbarHostState
                        },
                        bottomBar = {
                            //myBottomBar()
                        },
                        drawerContent = {
                            // myDrawer()
                        }
                    ) {
                        RecipeList(
                            loading = viewmodel.loading.value,
                            recipes = recipes,
                            onChangRecipeScrollPosition = viewmodel::onChangRecipeScrollPosition,
                            page = viewmodel.page.value,
                            onTriggerEvent = {
                                viewmodel.onTriggerEvent(RecipeListEvent.NextPageEvent)
                            },
                            scaffoldState = scaffoldState,
                            snackbarController = snackbarController,
                            navController = findNavController()

                        )
                    }
                }
            }
        }
    }
}

