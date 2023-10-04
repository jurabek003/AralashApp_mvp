package uz.turgunboyevjurabek.valyutakursimvp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.turgunboyevjurabek.valyutakursimvp.madels.Valyuta_get

class RvGetValyuta(val list: List<Valyuta_get>) :
    RecyclerView.Adapter<RvGetValyuta.Vh>() {
    inner class Vh(val viewItem2Binding: ViewItem2Binding) : RecyclerView.ViewHolder(viewItem2Binding.root) {
        fun onBind(userGuruh: Valyuta_get) {

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ViewItem2Binding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

}