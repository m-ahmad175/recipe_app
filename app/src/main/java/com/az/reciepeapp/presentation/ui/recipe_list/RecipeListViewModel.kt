package com.az.reciepeapp.presentation.ui.recipe_list

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.az.reciepeapp.domain.model.Recipe
import com.az.reciepeapp.repository.RecipeRepository
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

const val PAGE_SIZE = 30

// keys to use SavedStateHandle to save sate even after process is forced killed
const val STATE_KEY_PAGE = "recipe.state.page.key"
const val STATE_KEY_QUERY = "recipe.state.query.key"
const val STATE_KEY_LIST_POSITION = "recipe.state.query.list_position"
const val STATE_KEY_SELECTED_CATEGORY = "recipe.state.query"

@HiltViewModel
class RecipeListViewModel
@Inject
constructor(
    private val repository: RecipeRepository,
    @Named("auth_token") private val token: String,
) : ViewModel() {

    val recipes: MutableState<List<Recipe>> = mutableStateOf(listOf())
    val query = mutableStateOf("")
    val selectedCategory: MutableState<FoodCategory?> = mutableStateOf(null)
    var categoryScrollPosition: Int = 0
    var loading = mutableStateOf(false)

    val page = mutableStateOf(1)
    private var recipeScrollPosition = 0


    init {

        onTriggerEvent(RecipeListEvent.NewSearchEvent)
    }

    fun onTriggerEvent(event : RecipeListEvent){

        viewModelScope.launch {
            try {

                when(event){
                    is RecipeListEvent.NewSearchEvent -> {
                        newSearch()
                    }
                    is RecipeListEvent.NextPageEvent -> {
                        nextPage()
                    }
                }
            } catch (e:Exception){
                Log.e("ahmad", e.message.toString())
            }
        }
    }
    // use case 1
    private suspend fun newSearch() {

        viewModelScope.launch {
            loading.value = true
            resetSearchState()

            val result = repository.search(
                token = token,
                page = 1,
                query = query.value
            )

            recipes.value = result
            loading.value = false
        }
    }
    private suspend fun nextPage(){

        viewModelScope.launch {

            if (recipeScrollPosition + 1 >= page.value * PAGE_SIZE) {
                loading.value = true
                incrementPage()
                Log.e("ahmad", "Next Page: " + page.value)

                delay(1000)
                if (page.value > 1){
                    val reslt = repository.search(token = token,
                        page = page.value,
                        query = query.value)
                    Log.e("ahmad", "nextPage:$reslt")
                    appendRecipes(recipe = reslt)
                }
                loading.value = false

            }
        }
    }

    /**
     *
     */
    private fun appendRecipes(recipe: List<Recipe>){

        val current = ArrayList(this.recipes.value)
        current.addAll(recipe)

        this.recipes.value = current
    }
    private fun incrementPage(){
        page.value = page.value + 1
    }

    fun onChangRecipeScrollPosition(position: Int){
        recipeScrollPosition = position
    }

    fun onQueryChanged(query: String) {
        this.query.value = query
    }

    fun onSelectedCategoryChanged(category: String) {
        val newCat = getFoodCategory(category)
        selectedCategory.value = newCat
        onQueryChanged(category)

    }

    fun onChangeCategoryScrollPosition(pos: Int) {
        categoryScrollPosition = pos
    }

    private fun resetSearchState() {
        recipes.value = listOf()
        page.value = 1
        onChangRecipeScrollPosition(0)
        if (selectedCategory.value?.value != query.value){
            clearSelectedCategory()
        }
    }

    private fun clearSelectedCategory() {
        selectedCategory.value = null
    }

}