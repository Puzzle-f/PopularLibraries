package com.example.popularlibrarieslesson2.model.api.RoomData

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    // описываем первичные ключи
    foreignKeys = [ForeignKey(
        entity = RoomGithubUser::class,     // с каким классом связываемся
        parentColumns = ["id"],             // колонка RoomGithubUser
        childColumns = ["userId"],           // наша колонка
        onDelete = ForeignKey.CASCADE       // когда будем удалять пользователя, чтобы с ним удалялись все его репозитории
    )]
)
data class RoomGithubRepository(
//    @PrimaryKey var id: String,         // id репозитория (у одного юзера их много)
    @PrimaryKey var name: String,
                var userId: String                  // id пользователя (связываем эти id в @Entity)
    //    var forksCount: Int,
)

