package uz.turgunboyevjurabek.valyutakursimvp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.turgunboyevjurabek.valyutakursimvp.R
import uz.turgunboyevjurabek.valyutakursimvp.databinding.ItemDialogRvBinding
import uz.turgunboyevjurabek.valyutakursimvp.databinding.ItemRvBinding
import uz.turgunboyevjurabek.valyutakursimvp.Models.madels.Valyuta_get

class RvDialog(val list: List<Valyuta_get>, val onItemClick: OnItemClick) : RecyclerView.Adapter<RvDialog.Vh>() {
    inner class Vh(val itemDialogRvBinding: ItemDialogRvBinding):ViewHolder(itemDialogRvBinding.root){
        fun onBind(valyuta_get: Valyuta_get, position: Int) {
         itemDialogRvBinding.name.text=valyuta_get.ccyNmUZ
            itemDialogRvBinding.tht.text=valyuta_get.ccy

            itemDialogRvBinding.root.setOnClickListener{
                onItemClick.selectItem(valyuta_get, position)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemDialogRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position],position)
    }
    interface OnItemClick{
        fun selectItem(valyuta_get: Valyuta_get, position: Int)
    }
}