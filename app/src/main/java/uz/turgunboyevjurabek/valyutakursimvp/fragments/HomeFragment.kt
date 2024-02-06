package uz.turgunboyevjurabek.valyutakursimvp.fragments

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.ShapeDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import uz.turgunboyevjurabek.valyutakursimvp.Models.madels.Valyuta_get
import uz.turgunboyevjurabek.valyutakursimvp.Models.network.ApiClient

class HomeFragment : Fragment(),Cantrakt.View,RvDialog.OnItemClick {
    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private lateinit var precenter: Precenter
    private lateinit var rvDialog: RvDialog
    private lateinit var dialog: BottomSheetDialog
    private var exchange: Boolean = false
    private var son1: String? = ""
    private var son2: String? = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        precenter = Precenter(this, ApiClient)
        precenter.apiSuccessOrFail()

        // img bosilsa buladigan ishlar uchun
        binding.imgExchange.setOnClickListener {
            if (!exchange) {
                exchange = true
                Toast.makeText(requireContext(), "$exchange", Toast.LENGTH_SHORT).show()

                var a=binding.text1.text
                var b=binding.thtKurs1.text
                var c=binding.thtKursName1.text

                binding.thtKurs1.text = binding.thtKurs2.text
                binding.text1.text = binding.text2.text
                binding.thtKursName1.text = binding.thtKursName2.text

                binding.text2.text =a
                binding.thtKurs2.text = b
                binding.thtKursName2.text = c

            } else {
                exchange = false
                Toast.makeText(requireContext(), "$exchange", Toast.LENGTH_SHORT).show()

                var a=binding.text2.text
                var b=binding.thtKurs2.text
                var c=binding.thtKursName2.text

                binding.thtKurs2.text = binding.thtKurs1.text
                binding.text2.text = binding.text1.text
                binding.thtKursName2.text = binding.thtKursName1.text

                binding.text1.text = a
                binding.thtKurs1.text = b
                binding.thtKursName1.text =c

            }
        }

        return binding.root
    }

    override fun showProgress() {
        binding.animationView.visibility = View.VISIBLE
        binding.constraintLayout1.visibility = View.GONE
        binding.constraintLayout2.visibility = View.GONE

    }

    override fun hideProgress() {
        binding.animationView.visibility = View.GONE
        binding.constraintLayout1.visibility = View.VISIBLE
        binding.constraintLayout2.visibility = View.VISIBLE
    }

    override fun successfulResponse(list: ArrayList<Valyuta_get>) {
        rvDialog = RvDialog(list, this)
        ayriboshlash(rvDialog)
        // tepadagilar uchun
        binding.text1.text=rvDialog.list[0].rate
        binding.thtKurs1.text="UZB"
        binding.thtKursName1.text="O'zbek so'mi"

        // pastdagilar uchun
        binding.text2.text="1"
        binding.thtKurs2.text="USD"
        binding.thtKursName2.text="AQSh dollari"

    }

    override fun errorResponse(tht: String) {
        Toast.makeText(requireContext(), tht.toString(), Toast.LENGTH_LONG).show()
        showProgress()
    }

    private fun ayriboshlash(rvDialog: RvDialog) {
        try {
            dialog = BottomSheetDialog(requireContext())
            val itemMDialog = ItemMDialogBinding.inflate(layoutInflater)
            itemMDialog.rvDialog.adapter = rvDialog
            dialog.setContentView(itemMDialog.root)


            // tepadagi textView ga ccy ni berish uchun dialogni ochish
            binding.thtKurs2.setOnClickListener {
                    dialog.show()
            }
            // dialogni yopish uchun
            itemMDialog.btnDialogBack.setOnClickListener {
                dialog.cancel()
            }

            setClickNumber()
        } catch (e: IllegalStateException) {
            Log.d("LOGing", "${e.message}")
        }


    }

    override fun selectItem(valyuta_get: Valyuta_get, position: Int) {

            binding.thtKurs1.text ="UZB"
            binding.text1.text = valyuta_get.rate
            binding.thtKursName1.text = "O'zbek so'mi"

            binding.thtKurs2.text = valyuta_get.ccy
            binding.text2.text = "1"
            binding.thtKursName2.text = valyuta_get.ccyNmUZ
            dialog.cancel()

    }

    private fun setClickNumber() {
        binding.btn0.setOnClickListener {
            if (!binding.text2.text.equals("0")) {
                    son1 = "0"
                    son2 += son1
                    binding.text2.text = "$son2"
                    val abs = binding.text1.text.toString().toDouble()
                    binding.text1.text = "${abs * son2.toString().toDouble()}"
            }
        }

        binding.btn1.setOnClickListener {
                    son1 = "1"
                    son2 += son1
                    binding.text2.text = "$son2"
                    val abs = binding.text1.text.toString().toDouble()
                    binding.text1.text = "${abs * son2.toString().toDouble()}"

        }

    }
}