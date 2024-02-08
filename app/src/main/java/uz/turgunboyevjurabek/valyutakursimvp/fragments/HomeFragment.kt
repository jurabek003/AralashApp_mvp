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
import uz.turgunboyevjurabek.valyutakursimvp.AppObject
import uz.turgunboyevjurabek.valyutakursimvp.Cantract.Cantrakt
import uz.turgunboyevjurabek.valyutakursimvp.Prezenter.Precenter
import uz.turgunboyevjurabek.valyutakursimvp.R
import uz.turgunboyevjurabek.valyutakursimvp.adapter.RvDialog
import uz.turgunboyevjurabek.valyutakursimvp.adapter.RvGetValyuta
import uz.turgunboyevjurabek.valyutakursimvp.databinding.FragmentHomeBinding
import uz.turgunboyevjurabek.valyutakursimvp.databinding.ItemMDialogBinding
import uz.turgunboyevjurabek.valyutakursimvp.Models.madels.Valyuta_get
import uz.turgunboyevjurabek.valyutakursimvp.Models.network.ApiClient
import uz.turgunboyevjurabek.valyutakursimvp.databinding.ActivityMainBinding


class HomeFragment : Fragment(),Cantrakt.View,RvDialog.OnItemClick {
    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private lateinit var precenter: Precenter
    private lateinit var rvDialog: RvDialog
    private lateinit var dialog: BottomSheetDialog
    private var exchange: Boolean = false
    private var son1: String? = ""
    private var son2: String? = ""
    private var constNumber: Double = 0.0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        precenter = Precenter(this, ApiClient)
        precenter.apiSuccessOrFail()



        return binding.root
    }

    override fun onResume() {
        super.onResume()
        AppObject.binding.layoutTht.text="Valyutalar kursi"
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
        constNumber=rvDialog.list[0].rate.toDouble()
        val formatNumber=formatNumber(rvDialog.list[0].rate.toDouble())
        binding.text1.text=formatNumber
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
                   son2=""
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
        constNumber=valyuta_get.rate.toDouble()
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
            if (binding.text2.text != "0") {
                    son1 = "0"
                if (binding.text2.text!=""){
                    son2=binding.text2.text.toString()+son1
                }else{
                    son2 += son1
                }
                binding.text2.text = "$son2"
                    val abs = unFormatNumber(constNumber.toString())
                    binding.text1.text = formatNumber(abs * son2.toString().toDouble())
            }

        }
        binding.btn1.setOnClickListener {
                son1 = "1"
            if (binding.text2.text!=""){
                son2=binding.text2.text.toString()+son1
            }else{
                son2 += son1
            }
                binding.text2.text = "$son2"
                val abs = unFormatNumber(constNumber.toString())
                binding.text1.text = formatNumber(abs * son2.toString().toDouble())
        }
        binding.btn2.setOnClickListener {
            son1 = "2"
            if (binding.text2.text!=""){
                son2=binding.text2.text.toString()+son1
            }else{
                son2 += son1
            }
            binding.text2.text = "$son2"
            val abs = unFormatNumber(constNumber.toString())
            binding.text1.text = formatNumber(abs * son2.toString().toDouble())
        }
        binding.btn3.setOnClickListener {
                    son1 = "3"
                     if (binding.text2.text!=""){
                son2=binding.text2.text.toString()+son1
            }else{
                son2 += son1
            }
                    binding.text2.text = "$son2"
                    val abs = unFormatNumber(constNumber.toString())
                    binding.text1.text = formatNumber(abs * son2.toString().toDouble())
        }
         binding.btn4.setOnClickListener {
                    son1 = "4"
                     if (binding.text2.text!=""){
                son2=binding.text2.text.toString()+son1
            }else{
                son2 += son1
            }
                    binding.text2.text = "$son2"
                    val abs = unFormatNumber(constNumber.toString())
                    binding.text1.text = formatNumber(abs * son2.toString().toDouble())

        }
         binding.btn5.setOnClickListener {
                    son1 = "5"
                     if (binding.text2.text!=""){
                son2=binding.text2.text.toString()+son1
            }else{
                son2 += son1
            }
                    binding.text2.text = "$son2"
                    val abs = unFormatNumber(constNumber.toString())
                    binding.text1.text = formatNumber(abs * son2.toString().toDouble())

        }
         binding.btn6.setOnClickListener {
                    son1 = "6"
                     if (binding.text2.text!=""){
                son2=binding.text2.text.toString()+son1
            }else{
                son2 += son1
            }
                    binding.text2.text = "$son2"
                    val abs = unFormatNumber(constNumber.toString())
                    binding.text1.text = formatNumber(abs * son2.toString().toDouble())

        }
         binding.btn7.setOnClickListener {
                    son1 = "7"
                     if (binding.text2.text!=""){
                son2=binding.text2.text.toString()+son1
            }else{
                son2 += son1
            }
                    binding.text2.text = "$son2"
                    val abs = unFormatNumber(constNumber.toString())
                    binding.text1.text = formatNumber(abs * son2.toString().toDouble())

        }
         binding.btn8.setOnClickListener {
                    son1 = "8"
                     if (binding.text2.text!=""){
                son2=binding.text2.text.toString()+son1
            }else{
                son2 += son1
            }
                    binding.text2.text = "$son2"
                    val abs = unFormatNumber(constNumber.toString())
                    binding.text1.text = formatNumber(abs * son2.toString().toDouble())

        }
         binding.btn9.setOnClickListener {
                    son1 = "9"
                     if (binding.text2.text!=""){
                son2=binding.text2.text.toString()+son1
            }else{
                son2 += son1
            }
                    binding.text2.text = "$son2"
                    val abs = unFormatNumber(constNumber.toString())
                    binding.text1.text = formatNumber(abs * son2.toString().toDouble())
        }
        binding.btnWest.setOnClickListener {
            try {
                if (!binding.text2.text.isNullOrEmpty()){
                    son2=binding.text2.text.substring(0 until binding.text2.text.lastIndex)
                    binding.text2.text = "$son2"
                    val abs = unFormatNumber(constNumber.toString())
                    binding.text1.text = formatNumber(abs * son2.toString().toDouble())
                }else{
                    Toast.makeText(requireContext(), "Oldin son kiriting", Toast.LENGTH_SHORT).show()
                }
            }catch (e:Exception){
                binding.text2.text=""
                binding.text1.text=""
            }
        }

    }


    private fun formatNumber(number: Double): String {
        val formattedNumber = if (number.toLong() in 100..9999999999) {
            val formattedString = String.format("%,d", number.toLong())
            formattedString.replace(",", " ") // Vergulni bo'shatish
        }else{
            number.toString()
        }
        return formattedNumber
    }
    private fun unFormatNumber(string: String): Double {
        val cleanedString: String = string
            .replace("\u00A0", "") // Bo'shliklarni olib tashlash (ASCII kodi: \u00A0)
        return cleanedString.toDouble()
    }
    // img bosilsa buladigan ishlar uchun
    /*  binding.imgExchange.setOnClickListener {
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

   */


}