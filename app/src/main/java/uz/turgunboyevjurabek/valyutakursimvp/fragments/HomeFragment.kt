package uz.turgunboyevjurabek.valyutakursimvp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import uz.turgunboyevjurabek.valyutakursimvp.Cantract.Cantrakt
import uz.turgunboyevjurabek.valyutakursimvp.Prezenter.Precenter
import uz.turgunboyevjurabek.valyutakursimvp.R
import uz.turgunboyevjurabek.valyutakursimvp.adapter.RvGetValyuta
import uz.turgunboyevjurabek.valyutakursimvp.databinding.FragmentHomeBinding
import uz.turgunboyevjurabek.valyutakursimvp.databinding.ItemMDialogBinding
import uz.turgunboyevjurabek.valyutakursimvp.madels.Valyuta_get
import uz.turgunboyevjurabek.valyutakursimvp.network.ApiClient

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment(),Cantrakt.View {
    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private lateinit var precenter: Precenter

    private lateinit var rvGetValyuta: RvGetValyuta
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        precenter= Precenter(this,ApiClient)
        precenter.apiSuccessOrFail()

        return binding.root
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomekFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
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
        rvGetValyuta=RvGetValyuta(list)
        ayriboshlash()
    }

    override fun errorResponse(tht: String) {
        Toast.makeText(requireContext(), tht.toString(), Toast.LENGTH_LONG).show()
        showProgress()
    }
    private fun ayriboshlash(){
        try {
            val dialog=MaterialAlertDialogBuilder(requireContext()).create()
            val itemMDialog= ItemMDialogBinding.inflate(layoutInflater)

            dialog.setView(itemMDialog.root)

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