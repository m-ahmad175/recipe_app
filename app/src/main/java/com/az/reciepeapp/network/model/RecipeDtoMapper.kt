package com.az.reciepeapp.network.model

import com.az.reciepeapp.domain.model.Recipe
import com.az.reciepeapp.domain.util.DomainMapper

class RecipeDtoMapper : DomainMapper<RecipeDto, Recipe> {

    override fun mapToDomainModel(entity: RecipeDto): Recipe {
        return Recipe(
            id = entity.id,
            title = entity.title,
            publisher = entity.publisher,
            featured_image = entity.featured_image,
            rating = entity.rating,
            source_url = entity.source_url,
            description = entity.description,
            ingredients = entity.ingredients,
            cooking_instructions = entity.cooking_instructions,
            date_added = entity.date_added,
            date_updated = entity.date_updated
        )
    }

    override fun mapFromDomainModel(domainModel: Recipe): RecipeDto {
        return RecipeDto(
            id = domainModel.id,
            title = domainModel.title,
            publisher = domainModel.publisher,
            featured_image = domainModel.featured_image,
            rating = domainModel.rating,
            source_url = domainModel.source_url,
            description = domainModel.description,
            ingredients = domainModel.ingredients,
            cooking_instructions = domainModel.cooking_instructions,
            date_added = domainModel.date_added,
            date_updated = domainModel.date_updated
        )
    }

    fun fromEntityList(initial: List<RecipeDto>): List<Recipe > {

        return initial.map { mapToDomainModel(it) }
    }

    fun toEntityList(initial: List<Recipe>): List<RecipeDto> {

        return initial.map { mapFromDomainModel(it) }
    }

}