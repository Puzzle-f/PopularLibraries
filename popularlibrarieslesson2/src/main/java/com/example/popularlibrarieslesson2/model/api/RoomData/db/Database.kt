package com.example.popularlibrarieslesson2.model.api.RoomData.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.popularlibrarieslesson2.model.api.RoomData.Dao.RepositoryDao
import com.example.popularlibrarieslesson2.model.api.RoomData.Dao.UserDao
import com.example.popularlibrarieslesson2.model.api.RoomData.RoomGithubRepository
import com.example.popularlibrarieslesson2.model.api.RoomData.RoomGithubUser

@androidx.room.Database(entities = [RoomGithubUser::class, RoomGithubRepository::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao

    // временная конструкция при отсутствии Dagger
    companion object {
        private const val DB_NAME = "database.db"    // имя бызы данных
        private var instance: Database? = null
        fun getInstance() = instance ?: throw RuntimeException("Database has not been created. Please call create(context)")

        fun create(context: Context?) {
            if (instance == null) {
                instance = Room.databaseBuilder(context!!, Database::class.java, DB_NAME)
                    .build()
            }
        }
    }
}
