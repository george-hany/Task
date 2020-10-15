package com.app.app.ui.recipesList.model

import android.os.Parcel
import android.os.Parcelable
import com.core.data.model.recipesList.RecipesListResponseItem
import com.google.gson.annotations.SerializedName

class RecipesItemUIModel(
    var calories: String? = null,
    var carbos: String? = null,
    var country: String? = null,
    var description: String? = null,
    var difficulty: Int? = null,
    var fats: String? = null,
    var headline: String? = null,
    var id: String? = null,
    var image: String? = null,
    var name: String? = null,
    var proteins: String? = null,
    var thumb: String? = null,
    var time: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(calories)
        parcel.writeString(carbos)
        parcel.writeString(country)
        parcel.writeString(description)
        parcel.writeValue(difficulty)
        parcel.writeString(fats)
        parcel.writeString(headline)
        parcel.writeString(id)
        parcel.writeString(image)
        parcel.writeString(name)
        parcel.writeString(proteins)
        parcel.writeString(thumb)
        parcel.writeString(time)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RecipesItemUIModel> {
        override fun createFromParcel(parcel: Parcel): RecipesItemUIModel {
            return RecipesItemUIModel(parcel)
        }

        override fun newArray(size: Int): Array<RecipesItemUIModel?> {
            return arrayOfNulls(size)
        }

        fun convertResponseToUI(model: RecipesListResponseItem): RecipesItemUIModel {
            model.run {
                return RecipesItemUIModel(
                    calories,
                    carbos,
                    country,
                    description,
                    difficulty,
                    fats,
                    headline,
                    id,
                    image,
                    name,
                    proteins,
                    thumb,
                    time
                )
            }
        }
    }
}