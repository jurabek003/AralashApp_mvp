package uz.turgunboyevjurabek.valyutakursimvp.fragments

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.ShapeDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.graphics.drawable.toDrawable
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import uz.turgunboyevjurabek.valyutakursimvp.Cantract.Cantrakt
import uz.turgunboyevjurabek.valyutakursimvp.Prezenter.Precenter
import uz.turgunboyevjurabek.valyutakursimvp.R
import uz.turgunboyevjurabek.valyutakursimvp.adapter.RvDialog
import uz.turgunboyevjurabek.valyutakursimvp.adapter.RvGetValyuta
import uz.turgunboyevjurabek.valyutakursimvp.databinding.FragmentHomeBinding
import uz.turgunboyevjurabek.valyutakursimvp.databinding.ItemMDialogBinding
import uz.turgunboyevjurabek.valyutakursimvp.madels.Valyuta_get
import uz.turgunboyevjurabek.valyutakursimvp.network.ApiClient

class HomeFragment : Fragment(),Cantrakt.View,RvDialog.OnItemClick {
    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private lateinit var precenter: Precenter
    private lateinit var rvDialog: RvDialog
    private lateinit var dialog :BottomSheetDialog
    private var key1:Boolean=false
    private var key2:Boolean=false
    private var exchange:Boolean=false
    private var son1:String?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        precenter= Precenter(this,ApiClient)
        precenter.apiSuccessOrFail()


        binding.imgExchange.setOnClickListener {
            if (!exchange){
                exchange=true
                Toast.makeText(requireContext(), "$exchange", Toast.LENGTH_SHORT).show()

            }else{
                exchange=false
                Toast.makeText(requireContext(), "$exchange", Toast.LENGTH_SHORT).show()}

        }

        return binding.root
    }

    override fun showProgress() {
        binding.animationView.visibility=View.VISIBLE
        binding.constraintLayout1.visibility=View.GONE
        binding.constraintLayout2.visibility=View.GONE

    }

    override fun hideProgress() {
        binding.animationView.visibility=View.GONE
        binding.constraintLayout1.visibility=View.VISIBLE
        binding.constraintLayout2.visibility=View.VISIBLE
    }

    override fun successfulResponse(list: ArrayList<Valyuta_get>) {
        rvDialog=RvDialog(list,this)

        ayriboshlash(rvDialog)
    }

    override fun errorResponse(tht: String) {
        Toast.makeText(requireContext(), tht.toString(), Toast.LENGTH_LONG).show()
        showProgress()
    }
    private fun ayriboshlash(rvDialog: RvDialog){
        try {
             dialog=BottomSheetDialog(requireContext())
            val itemMDialog= ItemMDialogBinding.inflate(layoutInflater)
            itemMDialog.rvDialog.adapter=rvDialog
            dialog.setContentView(itemMDialog.root)


            if(exchange){
                binding.thtKurs2.text="UZB"
                binding.text2.text="1"
                binding.thtKursName2.text="O'zbek so'mi"

                binding.thtKurs1.text=rvDialog.list[0].ccy
                binding.thtKursName1.text=rvDialog.list[0].ccyNmUZ
                binding.text1.text=rvDialog.list[0].rate
                son1=binding.thtKurs1.text.toString()

            }else{
                binding.thtKurs1.text="UZB"
                binding.text1.text="1"
                binding.thtKursName1.text="O'zbek so'mi"

                binding.thtKurs2.text=rvDialog.list[0].ccy
                binding.thtKursName2.text=rvDialog.list[0].ccyNmUZ
                binding.text2.text=rvDialog.list[0].rate

                son1=binding.thtKurs2.text.toString()

            }
            binding.thtKurs1.setOnClickListener {
                if (exchange==false){
                    key1=true
                    key2=false
                    binding.thtKurs1.setBackgroundColor(Color.GREEN)
                    binding.thtKurs2.setBackgroundColor(Color.WHITE)

                    dialog.show()
                }
            }
            binding.thtKurs2.setOnClickListener {
                if (exchange==true){
                    key2=true
                    key1=false
                    binding.thtKurs2.setBackgroundColor(Color.GREEN)
                    binding.thtKurs1.setBackgroundColor(Color.WHITE)

                    dialog.show()
                }
            }

            itemMDialog.btnDialogBack.setOnClickListener {
                dialog.cancel()
            }

            
        }catch (e:IllegalStateException){
            Log.d("LOGing","${e.message}")
        }
      

    }

    override fun selectItem(valyuta_get: Valyuta_get, position: Int) {
        if (key1){
            binding.thtKurs1.text=valyuta_get.ccy
            binding.text1.text=valyuta_get.rate
            binding.thtKursName1.text=valyuta_get.ccyNmUZ
            dialog.cancel()
        }else{
            binding.thtKurs2.text=valyuta_get.ccy
            binding.text2.text=valyuta_get.rate
            binding.thtKursName2.text=valyuta_get.ccyNmUZ
            dialog.cancel()
        }
    }
    private fun setClickNumber(kurs:Boolean){

    }

}