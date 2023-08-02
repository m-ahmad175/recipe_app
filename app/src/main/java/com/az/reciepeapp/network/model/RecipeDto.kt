package com.az.reciepeapp.network.model

import com.google.gson.annotations.SerializedName

data  class RecipeDto(

    @SerializedName("pk")
    var id:Int? = null,
    var title:String? = null,
    var publisher:String? = null,
    var featured_image:String? = null,
    var rating:Int? = null,
    var source_url:String? = null,
    var description:String? = null,
    var cooking_instructions:String? = null,
    var date_added:String? = null,
    var date_updated:String? = null,
    var ingredients:List<String>? = listOf(),
)