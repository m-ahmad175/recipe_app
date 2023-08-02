package com.az.reciepeapp.domain.model

import android.os.Parcel
import android.os.Parcelable

data class Recipe (
    val id:Int? = null,
    val title:String? = null,
    val publisher:String? = null,
    val featured_image:String? = null,
    val rating:Int? = null,
    val source_url:String? = null,
    val description:String? = null,
    val cooking_instructions:String? = null,
    val date_added:String? = null,
    val date_updated:String? = null,
    val ingredients:List<String>? = listOf(),
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArrayList()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(title)
        parcel.writeString(publisher)
        parcel.writeString(featured_image)
        parcel.writeValue(rating)
        parcel.writeString(source_url)
        parcel.writeString(description)
        parcel.writeString(cooking_instructions)
        parcel.writeString(date_added)
        parcel.writeString(date_updated)
        parcel.writeStringList(ingredients)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Recipe> {
        override fun createFromParcel(parcel: Parcel): Recipe {
            return Recipe(parcel)
        }

        override fun newArray(size: Int): Array<Recipe?> {
            return arrayOfNulls(size)
        }
    }
}
