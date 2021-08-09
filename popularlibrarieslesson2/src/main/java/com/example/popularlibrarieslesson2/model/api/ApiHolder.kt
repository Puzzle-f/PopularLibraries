package com.example.popularlibrarieslesson2.model.api

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object ApiHolder {

    val api: IDataSource by lazy {
        val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES) // превращать поля snake_case приходящего gson  в camelCase
            .excludeFieldsWithoutExposeAnnotation() // игнорировать поля, не помеченные аннотацией @Expose
            .create()

        Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())  // превращает экземпляры Call в RxJava (в данном случае)
            .addConverterFactory(GsonConverterFactory.create(gson))     // то же самое для Gson конвертера
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                    .build()
            )
            .build()
            .create(IDataSource::class.java)
    }
}