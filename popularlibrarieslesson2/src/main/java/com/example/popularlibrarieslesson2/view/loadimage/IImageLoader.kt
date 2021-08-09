package com.example.popularlibrarieslesson2.view.loadimage

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}
