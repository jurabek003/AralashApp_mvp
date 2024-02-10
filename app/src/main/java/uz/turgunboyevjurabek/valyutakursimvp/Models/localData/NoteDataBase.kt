package uz.turgunboyevjurabek.valyutakursimvp.Models.localData

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import uz.turgunboyevjurabek.valyutakursimvp.Models.madels.Note

@Database(entities = [Note::class], version = 4)
abstract class NoteDataBase :RoomDatabase(){
    abstract fun noteDao():NoteDao
    companion object{

        /**
         * Migratsiya uchun obyekt
         */
//        private val migration1to2 = object : Migration(3, 4) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("ALTER TABLE Note ADD COLUMN new_column_name TEXT")
//            }
//        }
        fun newInstens(context: Context):NoteDataBase{
            return Room.databaseBuilder(context,NoteDataBase::class.java,"NoteData")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()

        }
    }
}