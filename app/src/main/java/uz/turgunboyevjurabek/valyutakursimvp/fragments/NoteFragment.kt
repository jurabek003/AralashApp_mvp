package uz.turgunboyevjurabek.valyutakursimvp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.turgunboyevjurabek.valyutakursimvp.AppObject
import uz.turgunboyevjurabek.valyutakursimvp.Models.madels.Note
import uz.turgunboyevjurabek.valyutakursimvp.R
import uz.turgunboyevjurabek.valyutakursimvp.adapter.RvAdapterNote
import uz.turgunboyevjurabek.valyutakursimvp.databinding.FragmentNoteBinding

class NoteFragment : Fragment() {
    private val binding by lazy { FragmentNoteBinding.inflate(layoutInflater) }
    private lateinit var rvAdapterNote: RvAdapterNote
    var isAllFabsVisible: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        isAllFabsVisible=false
        val note=Note("Assalom alekum mening ismimni bilasz men o'sha androidchi Jurabek man :)")

        val list=ArrayList<Note>()
        list.addAll(listOf(
            Note("Assalom alekum mening ismimni bilasz men o'sha androidchi Jurabek man :)" +
                    "Assalom alekum mening ismimni bilasz men o'sha androidchi Jurabek man :)" +
                    "Assalom alekum mening ismimni bilasz men o'sha androidchi Jurabek man :)"),
            Note("Assalom alekum mening ismimni bilasz men o'sha androidchi Jurabek man :)"),
            Note("Assalom alekum mening ismimni bilasz men o'sha androidchi Jurabek man :)"),
            Note("Assalom alekum mening ismimni bilasz men o'sha androidchi Jurabek man :)"),
            Note("Assalom alekum mening ismimni bilasz men o'sha androidchi Jurabek man :)" +
                    "Assalom alekum mening ismimni bilasz men o'sha androidchi Jurabek man :)" +
                    "Assalom alekum mening ismimni bilasz men o'sha androidchi Jurabek man :)"),
            Note("Assalom alekum mening ismimni bilasz men o'sha androidchi Jurabek man :)"),
            Note("Assalom alekum mening ismimni bilasz men o'sha androidchi Jurabek man :)"),
            Note("Assalom alekum mening ismimni bilasz men o'sha androidchi Jurabek man :)"),
            Note("Assalom alekum mening ismimni bilasz men o'sha androidchi Jurabek man :)" +
                    "Assalom alekum mening ismimni bilasz men o'sha androidchi Jurabek man :)" +
                    "Assalom alekum mening ismimni bilasz men o'sha androidchi Jurabek man :)"),
            Note("Assalom alekum mening ismimni bilasz men o'sha androidchi Jurabek man :)"),
            Note("Assalom alekum mening ismimni bilasz men o'sha androidchi Jurabek man :)"),
            Note("Assalom alekum mening ismimni bilasz men o'sha androidchi Jurabek man :)"),
            Note("Assalom alekum mening ismimni bilasz men o'sha androidchi Jurabek man :)"),
            Note("Assalom alekum mening ismimni bilasz men o'sha androidchi Jurabek man :)" +
                    "Assalom alekum mening ismimni bilasz men o'sha androidchi Jurabek man :)" +
                    "Assalom alekum mening ismimni bilasz men o'sha androidchi Jurabek man :)"),
        ))

        rvAdapterNote=RvAdapterNote(list)
        binding.rvNote.adapter=rvAdapterNote
        rvAdapterNote.notifyDataSetChanged()

        binding.floatingActionButton1.visibility=View.GONE
        binding.floatingActionButton2.visibility=View.GONE


        binding.extFltBtn.shrink()

        return binding.root
    }
    private fun fabfun() {
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
        fabfun()
            binding.rvNote.addOnScrollListener(object :RecyclerView.OnScrollListener(){
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        binding.extFltBtn.shrink()
                        binding.floatingActionButton1.visibility=View.GONE
                        binding.floatingActionButton2.visibility=View.GONE
                    }
                }
            })

    }
}