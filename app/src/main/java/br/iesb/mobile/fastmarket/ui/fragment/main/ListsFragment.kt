package br.iesb.mobile.fastmarket.ui.fragment.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import br.iesb.mobile.fastmarket.R
import br.iesb.mobile.fastmarket.databinding.FragmentListsBinding
import br.iesb.mobile.fastmarket.database.ProductDao
import br.iesb.mobile.fastmarket.database.ProductDatabase
import br.iesb.mobile.fastmarket.ui.adapter.ProductAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListsFragment : Fragment() {
    private lateinit var binding: FragmentListsBinding

    private lateinit var database: ProductDatabase
    private lateinit var dao: ProductDao
    val searchText = MutableLiveData<String>("")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListsBinding.inflate(inflater, container, false)
        binding.fragment = this
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvSavedList.layoutManager = LinearLayoutManager(activity?.applicationContext)

        database = Room.databaseBuilder(
            requireActivity().applicationContext,
            ProductDatabase::class.java, "arquivo-de-produtos"
        ).build()
        dao = database.getProductDao()

        list(view)
    }

    fun list(v: View) {
        GlobalScope.launch {
            searchText.value?.let {
                val list = dao.getProduct(it)
                val adapter = ProductAdapter(list)
                withContext(Dispatchers.Main) {
                    binding.rvSavedList.adapter = adapter
                }
            }
        }
    }

    fun startNewList(v: View){
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                findNavController().navigate(R.id.acListToNewList)
            }
        }

    }

    fun startShopping(v: View){
        findNavController().navigate(R.id.acListToMarket)
    }



}