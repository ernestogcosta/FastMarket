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

        simularListaDeProdutosDoMercado()


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
                        binding.tvProduct.setText(productList[0])
                    }
                }
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        val posicaoPDA = LatLng(-15.842045579792819, -48.023104311790156)

//        val pda1 = LatLng(-15.841875586853474, -48.023184710112176)
//        val pda2 = LatLng(-15.841922032883092, -48.02315788802181)
//        val pino1 = MarkerOptions().position(pda1)
//        val pino2 = MarkerOptions().position(pda2)
//        map.addMarker(pino1)
//        map.addMarker(pino2)
        
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
            productListWithCorridor.removeAt(0)

            binding.tvProduct.setText(nome)

//            for(product in marketProductList){
//                if(nome.equals(product.productName)){
//                    map.addMarker(MarkerOptions().position(marketCorridorsLatLng[product.productCorridor - 1]))
//                    break
//                }
//            }
            map.addMarker(MarkerOptions().position(marketCorridorsLatLng[productListWithCorridor[0].productCorridor - 1]))

        }




    }

    fun simularListaDeProdutosDoMercado(){
        marketProductList.add(ProductCorridor("Queijo", 1))
        marketProductList.add(ProductCorridor("Feijão", 2))
        marketProductList.add(ProductCorridor("Alface", 1))
        marketProductList.add(ProductCorridor("Café", 0))
        marketProductList.add(ProductCorridor("Salgadinho", 3))

        Log.d(TAG, "antes do for")

        for (name in productList){
            Log.d(TAG, "dentro do for name")
            for(product in marketProductList){
                Log.d(TAG, "logo antes do if")
                if(name.equals(product.productName)){
                    productListWithCorridor.add(product)
                    Log.d(TAG, "adicionou no productListWithCorridor")
                    break
                }
            }
        }
        productListWithCorridor.sortBy { it.productCorridor }


        marketCorridorsLatLng.add(0, LatLng(-15.8421284583915, -48.02307878280237))
        marketCorridorsLatLng.add(1, LatLng(-15.842079432076709, -48.02309219384755))
        marketCorridorsLatLng.add(2, LatLng(-15.842026535250016, -48.02311633372889))
        marketCorridorsLatLng.add(3, LatLng(-15.84196976790841, -48.02313510919214))
    }

}