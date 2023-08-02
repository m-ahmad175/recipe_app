package com.az.reciepeapp.presentation.ui.recipe_list

// for uni directional data flow
sealed class RecipeListEvent {

    object NewSearchEvent: RecipeListEvent()

    object NextPageEvent: RecipeListEvent()

    // restore state after process killed
    object RestoreStateEvent:RecipeListEvent()
}