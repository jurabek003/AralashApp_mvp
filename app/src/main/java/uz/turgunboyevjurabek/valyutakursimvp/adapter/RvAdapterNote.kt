package uz.turgunboyevjurabek.valyutakursimvp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.turgunboyevjurabek.valyutakursimvp.Models.madels.Note
import uz.turgunboyevjurabek.valyutakursimvp.databinding.ItemNoteRvBinding

class RvAdapterNote( val list: ArrayList<Note>) :
    RecyclerView.Adapter<RvAdapterNote.Vh>() {
    inner class Vh(private val itemNoteRvBinding: ItemNoteRvBinding) : RecyclerView.ViewHolder(itemNoteRvBinding.root) {
        fun onBind(note: Note) {
            itemNoteRvBinding.thtNote.text=note.noteText.toString()
            itemNoteRvBinding.thtDate.text=note.date.toString()
            itemNoteRvBinding.thtTime.text=note.time.toString()
            itemNoteRvBinding.thtDay.text=note.day.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemNoteRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

}