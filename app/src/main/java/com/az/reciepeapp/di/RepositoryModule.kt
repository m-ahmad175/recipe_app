package com.az.reciepeapp.di

import com.az.reciepeapp.network.RecipeService
import com.az.reciepeapp.network.model.RecipeDtoMapper
import com.az.reciepeapp.repository.RecipeRepository
import com.az.reciepeapp.repository.RecipeRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRecipeRepository(recipeService: RecipeService,
                                recipeDtoMapper: RecipeDtoMapper
    ): RecipeRepository {

        return RecipeRepositoryImp(recipeService, recipeDtoMapper)
    }
}