package com.manav.adoptme.model

import android.content.Context
import com.manav.adoptme.R
import com.squareup.moshi.*

@JsonClass(generateAdapter = true)
@kotlinx.parcelize.Parcelize
data class Pet(
    val id: Int,
    val name: String,
    val type: Type,
    val desc: String,
    var isLiked: Boolean,
    val isMale: Boolean,
    val size: Size,
    val location: String,
    val imageName: String,
) : android.os.Parcelable {
    enum class Type {
        CAT, DOG, CHAMELEON
    }

    enum class Size {
        S, M, L
    }

    class PetTypeAdapter {
        @FromJson
        fun fromJson(type: String): Type {
            return when (type) {
                "Cat" -> Type.CAT
                "Dog" -> Type.DOG
                "Chameleon" -> Type.CHAMELEON
                else -> throw JsonDataException("unknown suit: $type")
            }
        }
    }

    class PetSizeAdapter {
        @FromJson
        fun fromJson(size: String): Size {
            return when (size) {
                "S" -> Size.S
                "M" -> Size.M
                "L" -> Size.L
                else -> throw JsonDataException("unknown suit: $size")
            }
        }
    }

    companion object
}

fun Pet.Companion.allPets(applicationContext: Context): List<Pet>? {
    val jsonString =
        applicationContext.assets.open("pets.json").bufferedReader().use { it.readText() }
    val moshi: Moshi = Moshi.Builder().add(Pet.PetSizeAdapter()).add(Pet.PetTypeAdapter()).build()
    val type =
        Types.newParameterizedType(List::class.java, Pet::class.java)
    val jsonAdapter: JsonAdapter<List<Pet>> = moshi.adapter(type)
    return jsonAdapter.fromJson(jsonString)
}

val Pet.Companion.fake: Pet
    get() = Pet(
        id = 1,
        name = "Fake 1",
        type = Pet.Type.CAT,
        desc = "This is a cat, not a dog",
        isLiked = false,
        isMale = true,
        size = Pet.Size.M,
        location = "Bireuen, Aceh",
        imageName = "cat-peep"
    )

@Suppress("unused")
val Pet.Companion.fakes: List<Pet>
    get() = listOf(Pet.fake, Pet.fake.copy(id = 2))

fun Pet.image(context: Context): Int = context.resources.getIdentifier(
    imageName.replace('-', '_'),
    "drawable",
    context.packageName
)

fun Pet.icon(): Int = when (type) {
    Pet.Type.CAT -> R.drawable.icn_cat
    Pet.Type.CHAMELEON -> R.drawable.icn_otter
    Pet.Type.DOG -> R.drawable.icn_dog
}