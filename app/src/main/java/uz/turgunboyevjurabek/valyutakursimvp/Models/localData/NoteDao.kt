package uz.turgunboyevjurabek.valyutakursimvp.Models.localData

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.turgunboyevjurabek.valyutakursimvp.Models.madels.Note

@Dao
interface NoteDao {
    /**
     * Hamma note larni olish uchun
     */
    @Query("select *from Note")
    fun getAllNote():List<Note>

    /**
     * LocalDataga yangi note qushish uchun
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(vararg note: Note)

    /**
     * Noteni o'chirish uchun
     */
    @Delete
    fun deleteNote(note: Note)
}