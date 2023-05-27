package com.example.jornadaandroid2023.viewmodel

import androidx.lifecycle.ViewModel
import com.example.jornadaandroid2023.model.repository.HintsRepository
import com.example.jornadaandroid2023.model.source.remote.HintsRemoteDataSource

class HintsViewModel: ViewModel() {
    private val apiRepository = HintsRemoteDataSource

    val hints = HintsRepository.hints

}