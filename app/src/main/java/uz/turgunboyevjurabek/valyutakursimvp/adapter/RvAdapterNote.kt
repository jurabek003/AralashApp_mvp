package uz.turgunboyevjurabek.valyutakursimvp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.turgunboyevjurabek.valyutakursimvp.Models.madels.Note
import uz.turgunboyevjurabek.valyutakursimvp.databinding.ItemNoteRvBinding

class RvAdapterNote(val itemClick: ItemClick) :
    RecyclerView.Adapter<RvAdapterNote.Vh>() {
    val list=ArrayList<Note>()
    inner class Vh(private val itemNoteRvBinding: ItemNoteRvBinding) : RecyclerView.ViewHolder(itemNoteRvBinding.root) {
        fun onBind(note: Note,position: Int) {

            itemNoteRvBinding.thtNote.text=note.noteText.toString()
            itemNoteRvBinding.thtDate.text=note.date.toString()
            itemNoteRvBinding.thtTime.text=note.time.toString()
            itemNoteRvBinding.thtDay.text=note.day.toString()

//            itemNoteRvBinding.itemNoteCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
//                note.isChecked=isChecked
//            }

            itemNoteRvBinding.root.setOnLongClickListener {
                itemNoteRvBinding.itemNoteCheckBox.visibility=View.VISIBLE
                itemNoteRvBinding.itemNoteCheckBox.isChecked=true

                itemNoteRvBinding.itemNoteCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
                    note.isChecked =isChecked
                }

                itemClick.itemDelete(note,position)
                true
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemNoteRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position],position)
    }

    fun updateDate(newData: ArrayList<Note>){
        if (list.isNotEmpty()){
            list.clear()
        }
        list.addAll(newData)
    }
    interface ItemClick{
        fun itemDelete(note: Note,position: Int)
    }
}