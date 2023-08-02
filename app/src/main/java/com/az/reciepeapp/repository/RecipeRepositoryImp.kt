package com.az.reciepeapp.repository

import com.az.reciepeapp.domain.model.Recipe
import com.az.reciepeapp.network.RecipeService
import com.az.reciepeapp.network.model.RecipeDtoMapper
import javax.inject.Inject
import javax.inject.Singleton


class RecipeRepositoryImp
constructor(
    private val recipeService: RecipeService,
    private val mapper: RecipeDtoMapper
) : RecipeRepository {


    override suspend fun search(token: String, page: Int, query: String): List<Recipe> {
        return mapper.fromEntityList(recipeService.search(token = token, page, query).dataResult!!)
    }

    override suspend fun get(token: String, id: Int): Recipe {
       return mapper.mapToDomainModel(recipeService.get(token = token, id))
    }
}