package com.manav.adoptme.ui

import androidx.compose.runtime.mutableStateListOf
import com.manav.adoptme.model.Pet

class AdoptmeAppState(private val initialPets: List<Pet> = listOf()) {
    private val _pets: MutableList<Pet> =
        mutableStateListOf(*initialPets.toTypedArray())

    val pets: List<Pet> = _pets

    fun like(pet: Pet) {
        _pets[_pets.indexOf(pet)] = pet.copy(isLiked = !pet.isLiked)
    }

    companion object {
        val TAG = AdoptmeAppState::class.java.toString()
    }
}