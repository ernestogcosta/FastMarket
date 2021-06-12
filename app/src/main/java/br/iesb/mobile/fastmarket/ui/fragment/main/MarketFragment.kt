package br.iesb.mobile.fastmarket.ui.fragment.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import br.iesb.mobile.fastmarket.R
import br.iesb.mobile.fastmarket.databinding.FragmentMarketBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MarketFragment : Fragment(), OnMapReadyCallback {
    private lateinit var binding: FragmentMarketBinding

    private lateinit var map: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMarketBinding.inflate(inflater, container, false)
        binding.fragment = this
        binding.lifecycleOwner = this

        val mapFragment = childFragmentManager.findFragmentById(R.id.mapMarket) as SupportMapFragment
        mapFragment.getMapAsync(this)

        return binding.root
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        val posicaoPDA = LatLng(-15.842045579792819, -48.023104311790156)
        val pinoPDA = MarkerOptions().position(posicaoPDA).title("Pão de Açúcar Águas Claras")
        map.addMarker(pinoPDA)
        //Oba
        map.addMarker(MarkerOptions().position(LatLng(-15.840350003557049, -48.021917303570966)).title("Oba Horti-Frutti"))

        //Bellavia
        map.addMarker(MarkerOptions().position(LatLng(-15.838378644766504, -48.01933093373992)).title("Bellavia"))

        //Dona de Casa
        map.addMarker(MarkerOptions().position(LatLng(-15.835828130037385, -48.01273785023568)).title("Dona de Casa"))

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(posicaoPDA, 15f))
    }

    fun navToMaps(v: View){
        findNavController().navigate(R.id.acMarketToMaps)
    }
}