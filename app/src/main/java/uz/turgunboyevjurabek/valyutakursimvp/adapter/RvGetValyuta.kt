package uz.turgunboyevjurabek.valyutakursimvp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.turgunboyevjurabek.valyutakursimvp.databinding.ItemRvBinding
import uz.turgunboyevjurabek.valyutakursimvp.Models.madels.Valyuta_get

class RvGetValyuta(val list: List<Valyuta_get>) :
    RecyclerView.Adapter<RvGetValyuta.Vh>() {
    inner class Vh(val itemRvBinding: ItemRvBinding) : RecyclerView.ViewHolder(itemRvBinding.root) {
        fun onBind(valyuta_get: Valyuta_get) {
            itemRvBinding.name.text=valyuta_get.ccyNmUZ.toString()
            itemRvBinding.date.text=valyuta_get.date.toString()
            itemRvBinding.tht.text=valyuta_get.ccy
            itemRvBinding.summa.text=valyuta_get.rate.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

}