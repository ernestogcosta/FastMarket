package br.iesb.mobile.fastmarket.ui.fragment.main

import android.media.MediaRouter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.iesb.mobile.fastmarket.R
import br.iesb.mobile.fastmarket.databinding.FragmentNewListBinding
import br.iesb.mobile.fastmarket.ui.adapter.RecyclerAdapter

class NewListFragment : Fragment() {

    private lateinit var binding: FragmentNewListBinding

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: RecyclerAdapter

    private var productList = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewListBinding.inflate(inflater, container, false)
        binding.fragment = this
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvList.apply{
            binding.rvList.layoutManager = LinearLayoutManager(activity?.applicationContext)
            recyclerView = requireView().findViewById(R.id.rvList)
            recyclerAdapter = RecyclerAdapter(productList)
            recyclerView.adapter = recyclerAdapter
            val itemTouchHelper = ItemTouchHelper(simpleCallback)
            itemTouchHelper.attachToRecyclerView(recyclerView)
        }


    }

    fun addProduct(v: View){
        if(binding.etCharacter.text.toString().isEmpty()){
            Toast.makeText(view?.context, getString(R.string.newlist_product_no_name), Toast.LENGTH_LONG).show()
        }else{
            val index: Int = productList.size
            productList.add(index, binding.etCharacter.text.toString())

            recyclerAdapter.notifyItemInserted(index)
            binding.etCharacter.text.clear()
        }
    }

    fun saveNewList(v: View){
        Toast.makeText(view?.context, "Salvando no banco", Toast.LENGTH_LONG).show()
    }

    private var simpleCallback = object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            var position = viewHolder.adapterPosition

            when(direction){
                ItemTouchHelper.LEFT -> {
                    productList.removeAt(position)
                    recyclerAdapter.notifyItemRemoved(position)
                }
            }
        }

    }


}