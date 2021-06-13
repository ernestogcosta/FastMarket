package br.iesb.mobile.fastmarket.ui.fragment.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import br.iesb.mobile.fastmarket.R
import br.iesb.mobile.fastmarket.databinding.FragmentNewListBinding
import br.iesb.mobile.fastmarket.database.Product
import br.iesb.mobile.fastmarket.database.ProductCorridor
import br.iesb.mobile.fastmarket.database.ProductDao
import br.iesb.mobile.fastmarket.database.ProductDatabase
import br.iesb.mobile.fastmarket.ui.adapter.RecyclerAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewListFragment : Fragment() {

    private lateinit var binding: FragmentNewListBinding

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: RecyclerAdapter

    private lateinit var database: ProductDatabase
    private lateinit var dao: ProductDao

    private var productList = mutableListOf<String>()
    private var marketProductList = mutableListOf<ProductCorridor>()

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

        initMarketProdcutList()

        database = Room.databaseBuilder(
            requireActivity().applicationContext,
            ProductDatabase::class.java, "arquivo-de-produtos"
        ).build()
        dao = database.getProductDao()

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
        var achou: Boolean = false
        if(binding.etCharacter.text.toString().isEmpty()){
            Toast.makeText(view?.context, getString(R.string.newlist_product_no_name), Toast.LENGTH_LONG).show()
        }else{
            val product = binding.etCharacter.text.toString().lowercase()
            for(item in marketProductList){
                val aux = item.productName.lowercase()
                if(product.equals(aux)){
                    val index: Int = productList.size
                    productList.add(index, binding.etCharacter.text.toString())

                    recyclerAdapter.notifyItemInserted(index)
                    binding.etCharacter.setText("")
                    achou = true
                    break
                }
            }
            if(!achou){
                Toast.makeText(view?.context, R.string.newlist_product_not_found, Toast.LENGTH_LONG).show()
            }
        }
    }

//    fun saveNewList(v: View){
//        Toast.makeText(view?.context, "Salvando no banco", Toast.LENGTH_LONG).show()
//    }

    fun saveNewList(v: View) {
        GlobalScope.launch {
            dao.deleteAll()
            for(product in productList){
               val p = Product(name = product)
               dao.insertProduct(p)
            }
            withContext(Dispatchers.Main) {
                Toast.makeText(view?.context, getString(R.string.newlist_saved), Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.acNewListToList)
            }
        }
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

    private fun initMarketProdcutList(){
        marketProductList.add(ProductCorridor("Frutas", 0))
        marketProductList.add(ProductCorridor("Maçã", 0))
        marketProductList.add(ProductCorridor("Laranja", 0))
        marketProductList.add(ProductCorridor("Manga", 0))
        marketProductList.add(ProductCorridor("Pera", 0))
        marketProductList.add(ProductCorridor("Banana", 0))
        marketProductList.add(ProductCorridor("Abacate", 0))
        marketProductList.add(ProductCorridor("Tangerina", 0))
        marketProductList.add(ProductCorridor("Uva", 0))
        marketProductList.add(ProductCorridor("Melão", 0))
        marketProductList.add(ProductCorridor("Melancia", 0))
        marketProductList.add(ProductCorridor("Legumes", 0))
        marketProductList.add(ProductCorridor("Cenoura", 0))
        marketProductList.add(ProductCorridor("Chuchu", 0))
        marketProductList.add(ProductCorridor("Abobrinha", 0))
        marketProductList.add(ProductCorridor("Abóbora", 0))
        marketProductList.add(ProductCorridor("Tomate", 0))
        marketProductList.add(ProductCorridor("Cebola", 0))
        marketProductList.add(ProductCorridor("Batata", 0))
        marketProductList.add(ProductCorridor("Alface", 0))
        marketProductList.add(ProductCorridor("Pão", 0))
        marketProductList.add(ProductCorridor("Bolo", 0))
        marketProductList.add(ProductCorridor("Biscoito de polvilho", 0))
        marketProductList.add(ProductCorridor("Vinho", 1))
        marketProductList.add(ProductCorridor("Cerveja", 1))
        marketProductList.add(ProductCorridor("Vodka", 1))
        marketProductList.add(ProductCorridor("Uísque", 1))
        marketProductList.add(ProductCorridor("Whisky", 1))
        marketProductList.add(ProductCorridor("Tequila", 1))
        marketProductList.add(ProductCorridor("Sardinha", 2))
        marketProductList.add(ProductCorridor("Atum", 2))
        marketProductList.add(ProductCorridor("Molho de tomate", 2))
        marketProductList.add(ProductCorridor("Azeitona", 2))
        marketProductList.add(ProductCorridor("Shoyu", 2))
        marketProductList.add(ProductCorridor("Tempero", 2))
        marketProductList.add(ProductCorridor("Óleo", 2))
        marketProductList.add(ProductCorridor("Miojo", 2))
        marketProductList.add(ProductCorridor("Macarrào", 2))
        marketProductList.add(ProductCorridor("Azeite", 2))
        marketProductList.add(ProductCorridor("Prato", 3))
        marketProductList.add(ProductCorridor("Copo", 3))
        marketProductList.add(ProductCorridor("Taça", 3))
        marketProductList.add(ProductCorridor("Jarra", 3))
        marketProductList.add(ProductCorridor("Forma", 3))
        marketProductList.add(ProductCorridor("Feijão", 3))
        marketProductList.add(ProductCorridor("Farinha", 3))
        marketProductList.add(ProductCorridor("Arroz", 3))
        marketProductList.add(ProductCorridor("Talher", 3))
        marketProductList.add(ProductCorridor("Absorvente", 4))
        marketProductList.add(ProductCorridor("Fralda", 4))
        marketProductList.add(ProductCorridor("Papel Higiênico", 4))
        marketProductList.add(ProductCorridor("Sabonete", 4))
        marketProductList.add(ProductCorridor("Shampoo", 4))
        marketProductList.add(ProductCorridor("Condicionador", 4))
        marketProductList.add(ProductCorridor("Escova de dente", 4))
        marketProductList.add(ProductCorridor("Desodorante", 4))
        marketProductList.add(ProductCorridor("Gilete", 4))
        marketProductList.add(ProductCorridor("Sabão", 5))
        marketProductList.add(ProductCorridor("Alvejante", 5))
        marketProductList.add(ProductCorridor("Amaciante", 5))
        marketProductList.add(ProductCorridor("Bucha", 5))
        marketProductList.add(ProductCorridor("Esponja", 5))
        marketProductList.add(ProductCorridor("Água sanitária", 5))
        marketProductList.add(ProductCorridor("Detergente", 5))
        marketProductList.add(ProductCorridor("Pano", 5))
        marketProductList.add(ProductCorridor("Luva", 5))
        marketProductList.add(ProductCorridor("Saco de lixo", 5))
        marketProductList.add(ProductCorridor("Ração", 5))
        marketProductList.add(ProductCorridor("Inseticida", 5))
        marketProductList.add(ProductCorridor("Álcool", 5))
        marketProductList.add(ProductCorridor("Álcool em gel", 5))
        marketProductList.add(ProductCorridor("Toalha de papel", 6))
        marketProductList.add(ProductCorridor("Gelatina", 6))
        marketProductList.add(ProductCorridor("Amido de milho", 6))
        marketProductList.add(ProductCorridor("Leite de coco", 6))
        marketProductList.add(ProductCorridor("Leite condensado", 6))
        marketProductList.add(ProductCorridor("Mistura de bolo", 6))
        marketProductList.add(ProductCorridor("Papel alumínio", 6))
        marketProductList.add(ProductCorridor("Guardanapo", 6))
        marketProductList.add(ProductCorridor("Geleia", 6))
        marketProductList.add(ProductCorridor("Biscoito", 6))
        marketProductList.add(ProductCorridor("Bolacha", 6))
        marketProductList.add(ProductCorridor("NUtella", 6))
        marketProductList.add(ProductCorridor("Bala", 6))
        marketProductList.add(ProductCorridor("Chiclete", 6))
        marketProductList.add(ProductCorridor("Fini", 6))
        marketProductList.add(ProductCorridor("Chocolate", 6))
        marketProductList.add(ProductCorridor("Bombom", 6))
        marketProductList.add(ProductCorridor("Barra de cereal", 7))
        marketProductList.add(ProductCorridor("Granola", 7))
        marketProductList.add(ProductCorridor("Sucrilhos", 7))
        marketProductList.add(ProductCorridor("Frutas secas", 7))
        marketProductList.add(ProductCorridor("Aveia", 7))
        marketProductList.add(ProductCorridor("Leite", 7))
        marketProductList.add(ProductCorridor("Mel", 7))
        marketProductList.add(ProductCorridor("Chá", 7))
        marketProductList.add(ProductCorridor("Açúcar", 7))
        marketProductList.add(ProductCorridor("Adoçante", 7))
        marketProductList.add(ProductCorridor("Achocolatado", 7))
        marketProductList.add(ProductCorridor("Café", 7))
        marketProductList.add(ProductCorridor("Água", 8))
        marketProductList.add(ProductCorridor("Suco", 8))
        marketProductList.add(ProductCorridor("Refrigerante", 8))
        marketProductList.add(ProductCorridor("Energético", 8))
        marketProductList.add(ProductCorridor("Carne", 9))
        marketProductList.add(ProductCorridor("Peixe", 9))
        marketProductList.add(ProductCorridor("Frango", 9))
        marketProductList.add(ProductCorridor("Nugget", 9))
        marketProductList.add(ProductCorridor("Batata frita", 9))
        marketProductList.add(ProductCorridor("Lasanha", 9))
        marketProductList.add(ProductCorridor("Pizza", 9))
        marketProductList.add(ProductCorridor("Iogurte", 9))
        marketProductList.add(ProductCorridor("Manteiga", 9))
        marketProductList.add(ProductCorridor("Gordura vegetal", 9))
        marketProductList.add(ProductCorridor("Queijo", 9))
        marketProductList.add(ProductCorridor("Requeijão", 9))
        marketProductList.add(ProductCorridor("Salsicha", 9))
        marketProductList.add(ProductCorridor("Bacon", 9))
        marketProductList.add(ProductCorridor("Linguiça", 9))
    }


}