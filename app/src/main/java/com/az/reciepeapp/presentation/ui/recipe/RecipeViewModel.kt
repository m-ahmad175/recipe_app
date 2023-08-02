package com.az.reciepeapp.presentation.ui.recipe

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.az.reciepeapp.domain.model.Recipe
import com.az.reciepeapp.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class RecipeViewModel
@Inject
constructor(
    private val recipeRepository: RecipeRepository,
    @Named("auth_token") private val token: String
) :
    ViewModel() {

    val recipe: MutableState<Recipe?> = mutableStateOf(null)

    val loading = mutableStateOf(false)

    init {

    }

    fun onTriggerEvent(event: RecipeEvent) {

        viewModelScope.launch {
            try {
                when (event) {
                    is RecipeEvent.GetRecipeEvent -> {
                        if (recipe.value == null) {
                            getRecipe(event.id)
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("ahmad", e.message.toString())
            }
        }
    }

    suspend fun getRecipe(id: Int) {

        loading.value = true

        val recipe = recipeRepository.get(token, id)
        this.recipe.value = recipe

        loading.value = false
    }
}