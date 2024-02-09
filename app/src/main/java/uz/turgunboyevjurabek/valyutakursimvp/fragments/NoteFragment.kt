package uz.turgunboyevjurabek.valyutakursimvp.fragments

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.icu.text.Transliterator.Position
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import uz.turgunboyevjurabek.valyutakursimvp.AppObject
import uz.turgunboyevjurabek.valyutakursimvp.Models.localData.NoteDataBase
import uz.turgunboyevjurabek.valyutakursimvp.Models.madels.Note
import uz.turgunboyevjurabek.valyutakursimvp.R
import uz.turgunboyevjurabek.valyutakursimvp.adapter.RvAdapterNote
import uz.turgunboyevjurabek.valyutakursimvp.databinding.DialogAddNoteBinding
import uz.turgunboyevjurabek.valyutakursimvp.databinding.FragmentNoteBinding

class NoteFragment : Fragment() {
    private val binding by lazy { FragmentNoteBinding.inflate(layoutInflater) }
    private val dialogAddNoteBinding by lazy { DialogAddNoteBinding.inflate(layoutInflater) }
    private lateinit var rvAdapterNote: RvAdapterNote
    var isAllFabsVisible: Boolean = false
    private lateinit var dialog:MaterialAlertDialogBuilder
    private lateinit var customDialog:AlertDialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        dialog=MaterialAlertDialogBuilder(requireContext())
            .setCancelable(false)
            .setView(dialogAddNoteBinding.root)

        customDialog=dialog.create()
        binding.floatingActionButton1.setOnClickListener {
            customDialog.show()
        }
        dialogAddNoteBinding.btnCloseDialog.setOnClickListener {
            customDialog.dismiss()
        }


        isAllFabsVisible=false



        binding.floatingActionButton1.visibility=View.GONE
        binding.floatingActionButton2.visibility=View.GONE


        binding.extFltBtn.shrink()

        return binding.root
    }
    private fun fabFun() {
        binding.extFltBtn.setOnClickListener{

            isAllFabsVisible=if(!isAllFabsVisible){
                binding.floatingActionButton1.visibility=View.VISIBLE
                binding.floatingActionButton2.visibility=View.VISIBLE
                binding.extFltBtn.extend()
                true
            }else{
                binding.floatingActionButton1.visibility=View.GONE
                binding.floatingActionButton2.visibility=View.GONE
                binding.extFltBtn.shrink()
                false
            }
        }
    }


        override fun onResume() {
        super.onResume()
        AppObject.binding.layoutTht.text="Qaydnomalar"
        fabFun()
            binding.rvNote.addOnScrollListener(object :RecyclerView.OnScrollListener(){
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (newState == RecyclerView.SCREEN_STATE_ON) {
                        binding.extFltBtn.shrink()
                        binding.floatingActionButton1.visibility=View.GONE
                        binding.floatingActionButton2.visibility=View.GONE
                    }
                }
            })
            addNoteItem()

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun addNoteItem(){
        val database=NoteDataBase.newInstens(requireContext()).noteDao()
        if (database.getAllNote().isNotEmpty()){
            val list=ArrayList<Note>()
            list.addAll(database.getAllNote())
            rvAdapterNote=RvAdapterNote(list)
            binding.rvNote.adapter=rvAdapterNote
            rvAdapterNote.notifyDataSetChanged()
        }else{
            Toast.makeText(requireContext(), "LocalData empty :(", Toast.LENGTH_SHORT).show()
        }

        dialogAddNoteBinding.btnSaveDialog.setOnClickListener {
            val textNote=dialogAddNoteBinding.extractEditText.text.toString()
            val note=Note(textNote)
            database.insertNote(note)
            rvAdapterNote.list.add(note)
            rvAdapterNote.notifyItemInserted(rvAdapterNote.list.lastIndex)
            customDialog.dismiss()
        }







    }
}