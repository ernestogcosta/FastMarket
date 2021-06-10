package br.iesb.mobile.fastmarket.ui.fragment.maps

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.iesb.mobile.fastmarket.R
import br.iesb.mobile.fastmarket.databinding.FragmentMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentMapsBinding

    private lateinit var map: GoogleMap

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

}