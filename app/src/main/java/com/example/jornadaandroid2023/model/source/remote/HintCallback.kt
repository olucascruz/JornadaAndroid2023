package com.example.jornadaandroid2023.model.source.remote

import com.example.jornadaandroid2023.model.source.remote.entities.Hint

interface HintCallback {
    fun onResult(hints: List<Hint>)
}