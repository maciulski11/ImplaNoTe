package com.example.implanote

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 3)
abstract class NoteDatabase: RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object{
        private var instance: NoteDatabase? = null

        fun fetchInstance(context: Context): NoteDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    NoteDatabase::class.java,
                    "note_table"
                )
                    .build()
            }
            return instance
        }
    }
}