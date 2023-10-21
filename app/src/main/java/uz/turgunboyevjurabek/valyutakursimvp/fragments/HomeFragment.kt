package uz.turgunboyevjurabek.valyutakursimvp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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

class HomeFragment : Fragment(),Cantrakt.View {
    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private lateinit var precenter: Precenter
    private lateinit var rvDialog: RvDialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        precenter= Precenter(this,ApiClient)
        precenter.apiSuccessOrFail()

        return binding.root
    }

    override fun showProgress() {
        binding.animationView.visibility=View.VISIBLE
        binding.constraintLayout1.visibility=View.GONE

    }

    override fun hideProgress() {
        binding.animationView.visibility=View.GONE
        binding.constraintLayout1.visibility=View.VISIBLE
    }

    override fun successfulResponse(list: ArrayList<Valyuta_get>) {
        rvDialog=RvDialog(list)

        ayriboshlash(rvDialog)
    }

    override fun errorResponse(tht: String) {
        Toast.makeText(requireContext(), tht.toString(), Toast.LENGTH_LONG).show()
        showProgress()
    }
    private fun ayriboshlash(rvDialog: RvDialog){
        try {
            val dialog=BottomSheetDialog(requireContext())
            val itemMDialog= ItemMDialogBinding.inflate(layoutInflater)
            itemMDialog.rvDialog.adapter=rvDialog

            dialog.setContentView(itemMDialog.root)

            binding.thtKurs1.setOnClickListener {
                dialog.show()
            }

            binding.thtKurs2.setOnClickListener {
                dialog.show()
            }

            itemMDialog.btnDialogBack.setOnClickListener {
                dialog.cancel()
            }

            
        }catch (e:IllegalStateException){
            Toast.makeText(requireContext(), "Xatolik contextda", Toast.LENGTH_SHORT).show()
        }
      

    }
}