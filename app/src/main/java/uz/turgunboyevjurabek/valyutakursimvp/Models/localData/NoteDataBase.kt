package uz.turgunboyevjurabek.valyutakursimvp.Models.localData

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.turgunboyevjurabek.valyutakursimvp.Models.madels.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDataBase :RoomDatabase(){
    abstract fun noteDao():NoteDao
    companion object{
        fun newInstens(context: Context):NoteDataBase{
            return Room.databaseBuilder(context,NoteDataBase::class.java,"NoteData")
                .allowMainThreadQueries()
                .build()
        }
    }
}