package com.example.likphoto.models

import androidx.annotation.Keep

@Keep
data class IndexSearch (
    val total: Int,
    val total_pages: Int,
    val results: List<Pictures>
)