package uz.turgunboyevjurabek.valyutakursimvp.fragments

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.icu.text.Transliterator.Position
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
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
import uz.turgunboyevjurabek.valyutakursimvp.databinding.ItemNoteRvBinding

class NoteFragment : Fragment(),RvAdapterNote.ItemClick {

    private val binding by lazy { FragmentNoteBinding.inflate(layoutInflater) }
    private val dialogAddNoteBinding by lazy { DialogAddNoteBinding.inflate(layoutInflater) }
    private val database by lazy { NoteDataBase.newInstens(requireContext()).noteDao() }
    private val rvAdapterNote by lazy { RvAdapterNote(this) }
    private val itemNoteRvBinding by lazy { ItemNoteRvBinding.inflate(layoutInflater) }

    var isAllFabsVisible: Boolean = false
    private lateinit var dialog:MaterialAlertDialogBuilder
    private lateinit var customDialog:AlertDialog
    private  var list=ArrayList<Note>()

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
            searchNote()
            val itemNoteRvBinding=ItemNoteRvBinding.inflate(layoutInflater)

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun addNoteItem(){
        getAllNoteInRv()

        dialogAddNoteBinding.btnSaveDialog.setOnClickListener {
            val textNote=dialogAddNoteBinding.extractEditText.text.toString()
            val note=Note(textNote)
            database.insertNote(note)
            getAllNoteInRv()
            customDialog.dismiss()

        }
    }
    private fun searchNote(){
        binding.searchNote.setOnQueryTextListener(object:androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val list = database.searchNote(newText)
                if (list.isNotEmpty()) {
                    binding.rvNote.visibility=View.VISIBLE
                    binding.lottiNoNote.visibility=View.GONE

                    rvAdapterNote.list.clear()
                    rvAdapterNote.list.addAll(list)
                    rvAdapterNote.notifyDataSetChanged()

                }
                else {
                    binding.rvNote.visibility=View.GONE
                    binding.lottiNoNote.visibility=View.VISIBLE
                }
                if (newText==null){
                    binding.rvNote.visibility=View.VISIBLE
                    binding.lottiNoNote.visibility=View.GONE
                    getAllNoteInRv()
                }
                return true
            }
        })
        }

    private fun getAllNoteInRv(){
        if (database.getAllNote().isNotEmpty()){
            val list=ArrayList<Note>()
            list.addAll(database.getAllNote())
            rvAdapterNote.updateDate(list)
            binding.rvNote.adapter=rvAdapterNote
            rvAdapterNote.notifyDataSetChanged()
        }else{
            Toast.makeText(requireContext(), "LocalData empty :(", Toast.LENGTH_SHORT).show()
        }
    }

    override fun itemDelete(note: Note, position: Int) {

        itemNoteRvBinding.itemNoteCheckBox.visibility=View.VISIBLE

        if (note.isChecked){
            list.add(note)
        }

        AppObject.binding.actionDelete.visibility=View.VISIBLE
        AppObject.binding.actionDelete.setOnClickListener {

            val dialog=AlertDialog.Builder(requireContext())
                .setIcon(R.drawable.ic_info)
                .setTitle("O'chirish")
                .setMessage("Ushbu note larni rostanham o'chirmoqchimisz!")
                .setPositiveButton("ha") { dialog, which ->
                    database.deleteNotes(list)
                    getAllNoteInRv()
                    dialog.dismiss()
                    AppObject.binding.actionDelete.visibility=View.GONE
                }
                .setNegativeButton("Yo'q"){dialog,which->
                    dialog.dismiss()
                }
                .show()

        }


    }
}
