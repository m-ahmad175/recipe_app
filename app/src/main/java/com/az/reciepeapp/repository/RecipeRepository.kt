package com.az.reciepeapp.repository

import com.az.reciepeapp.domain.model.Recipe

interface RecipeRepository {

    suspend fun search(token: String, page:Int, query:String):List<Recipe>

    suspend fun get(token:String, id:Int):Recipe
}