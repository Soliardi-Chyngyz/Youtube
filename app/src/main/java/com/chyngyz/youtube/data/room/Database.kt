package com.chyngyz.youtube.data.room

import android.content.Context
import androidx.room.*
import com.chyngyz.youtube.data.converter.MyTapeConverters
import com.chyngyz.youtube.data.converter.PlaylistConverter
import com.chyngyz.youtube.data.model.PlayListItem
import com.chyngyz.youtube.data.model.VideoInfo

@TypeConverters(MyTapeConverters::class, PlaylistConverter::class)
@Database(entities = [VideoInfo::class, PlayListItem::class], version = 4)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao(): PlayListDao
    abstract fun infoDao(): InfoDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "todo-list.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}