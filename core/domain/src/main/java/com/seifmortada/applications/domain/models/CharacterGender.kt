package com.seifmortada.applications.domain.models

sealed class CharacterGender( val name:String) {
    data object Male : CharacterGender("Male")
    data object Female : CharacterGender("Female")
    data object GenderLess : CharacterGender("No Gender")
    data object Unknown: CharacterGender("Not Specified")
}