package com.example.popularlibrarieslesson2.model.api

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class Repository(
    @Expose val name: String? = null
) : Parcelable