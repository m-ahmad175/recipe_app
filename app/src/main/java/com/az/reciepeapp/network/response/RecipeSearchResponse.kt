package com.az.reciepeapp.network.response

import com.az.reciepeapp.network.model.RecipeDto
import com.google.gson.annotations.SerializedName

class RecipeSearchResponse(

    @SerializedName("count")
    var count: Int? = null,

    @SerializedName("next")
    var next: String? = null,

    @SerializedName("previous")
    var previous: String? = null,

    @SerializedName("results")
    var dataResult: List<RecipeDto>? = null,

    )