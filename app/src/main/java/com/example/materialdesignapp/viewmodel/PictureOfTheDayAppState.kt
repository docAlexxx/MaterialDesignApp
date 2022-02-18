package com.example.materialdesignapp.viewmodel

import com.example.materialdesignapp.Repo.PictureOfTheDayDTO

sealed class PictureOfTheDayAppState {
    data class Success(val serverResponse: PictureOfTheDayDTO) : PictureOfTheDayAppState()
    data class Error(val error: String) : PictureOfTheDayAppState()
    data class Loading(val process: Int?) : PictureOfTheDayAppState()
}