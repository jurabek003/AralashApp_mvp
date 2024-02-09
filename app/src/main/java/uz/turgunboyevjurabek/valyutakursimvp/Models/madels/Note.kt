package uz.turgunboyevjurabek.valyutakursimvp.Models.madels

import android.annotation.SuppressLint
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Entity
 class Note {
    @PrimaryKey(autoGenerate = true)
    var uid: Int?=null
    @ColumnInfo(name = "note_text")
    var noteText: String?=null
    @ColumnInfo(name = "time")
    var time: String? = SimpleDateFormat("HH:mm").format(Date())
    @ColumnInfo(name = "date")
    var date: String? = SimpleDateFormat("EEEE", Locale.getDefault()).format(Date())
    @SuppressLint("SimpleDateFormat")
    @ColumnInfo(name = "day")
    var day: String? = SimpleDateFormat("dd.MM.yyyy").format(Date())

    constructor(noteText: String?) {
        this.noteText = noteText
    }

}