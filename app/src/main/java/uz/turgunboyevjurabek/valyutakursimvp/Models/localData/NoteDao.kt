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

    /**
     * Qidirish
     */
    @Query("select *from Note where note_text like '%' || :searchQuery || '%' or uid like '%'||:searchQuery ")
    fun searchNote(searchQuery :String?):List<Note>

    /**
     * O'chirish uchun
     */
    @Delete
    fun deleteNotes(notes:ArrayList<Note>)

}