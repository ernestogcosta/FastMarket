package br.iesb.mobile.fastmarket.ui.fragment.maps

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import br.iesb.mobile.fastmarket.R
import br.iesb.mobile.fastmarket.database.ProductCorridor
import br.iesb.mobile.fastmarket.database.ProductDao
import br.iesb.mobile.fastmarket.database.ProductDatabase
import br.iesb.mobile.fastmarket.databinding.FragmentMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_maps.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MapsFragment : Fragment(), OnMapReadyCallback {
    private val TAG = "Compras"

    private lateinit var binding: FragmentMapsBinding

    private lateinit var map: GoogleMap

    private var productList = mutableListOf<String>()
    private var productListWithCorridor = mutableListOf<ProductCorridor>()
    private var marketProductList = mutableListOf<ProductCorridor>()
    private var marketCorridorsLatLng = mutableListOf<LatLng>()

    private val searchText = MutableLiveData<String>("")
    private lateinit var database: ProductDatabase
    private lateinit var dao: ProductDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapsBinding.inflate(inflater, container, false)
        binding.fragment = this
        binding.lifecycleOwner = this

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = Room.databaseBuilder(
            requireActivity().applicationContext,
            ProductDatabase::class.java, "arquivo-de-produtos"
        ).build()
        dao = database.getProductDao()

        GlobalScope.launch {
            searchText.value?.let {
                val list = dao.getProduct(it)
                withContext(Dispatchers.Main){
                    for(i in 0..list.size-1){
                        productList.add(list[i].name.toString())
                    }

                    simularListaDeProdutosDoMercado()
                    prepararListaDoUsuario()
                    binding.tvProduct.setText(productListWithCorridor[0].productName)
                    map.addMarker(MarkerOptions().position(marketCorridorsLatLng[productListWithCorridor[0].productCorridor]))
                    productListWithCorridor.removeAt(0)
                }
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        val posicaoPDA = LatLng(-15.842045579792819, -48.023104311790156)
        
        map.uiSettings.setAllGesturesEnabled(false)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(posicaoPDA, 20f))

        val point1 = LatLng(-15.841767343822005, -48.02317630677175)
        val point2 = LatLng(-15.842374916741292, -48.02311251273033)
        val latLngBounds = LatLngBounds.Builder()
            .include(point1)
            .include(point2)
            .build()

        map.setLatLngBoundsForCameraTarget(latLngBounds)
    }

    fun getNextProduct(v: View){
        map.clear()
        if(productListWithCorridor.isNullOrEmpty()){
            Toast.makeText(view?.context, "Compras terminadas, ir para o caixa!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.acMapsToHome)
        }else{
            val nome = productListWithCorridor[0].productName

            binding.tvProduct.setText(nome)

            map.addMarker(MarkerOptions().position(marketCorridorsLatLng[productListWithCorridor[0].productCorridor]))
            productListWithCorridor.removeAt(0)
        }
    }

    fun prepararListaDoUsuario(){
        for(i in 0..productList.size-1){
            for(j in 0..marketProductList.size-1){
                if(productList[i].lowercase().equals(marketProductList[j].productName.lowercase())){
                    productListWithCorridor.add(marketProductList[j])
                    break
                }
            }
        }
        Log.d(TAG, "depois dos for")
        productListWithCorridor.sortBy { it.productCorridor }
    }

    fun simularListaDeProdutosDoMercado(){
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

        marketCorridorsLatLng.add(0, LatLng(-15.842173614197218, -48.02303854966681))
        marketCorridorsLatLng.add(1, LatLng(-15.842115556730867, -48.02306939507074))
        marketCorridorsLatLng.add(2, LatLng(-15.842072981244915, -48.02308548832496))
        marketCorridorsLatLng.add(3, LatLng(-15.842076851744016, -48.02308817053399))
        marketCorridorsLatLng.add(4, LatLng(-15.842061369747194, -48.02311499262437))
        marketCorridorsLatLng.add(5, LatLng(-15.84202266474996, -48.02311901593793))
        marketCorridorsLatLng.add(6, LatLng(-15.841992990913715, -48.023129744774074))
        marketCorridorsLatLng.add(7, LatLng(-15.84197105807544, -48.02314047361022))
        marketCorridorsLatLng.add(8, LatLng(-15.841927192391733, -48.023167295700596))
        marketCorridorsLatLng.add(9, LatLng(-15.841885907033642, -48.02318070674578))
    }

}