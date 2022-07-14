package com.place.ui.place

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.place.R
import com.place.adapter.PlaceAdapter
import com.place.databinding.FragmentPlaceBinding

import com.place.ui.viewmodel.PlaceViewModel
import java.lang.Exception

class PlaceFragment : Fragment() {

    private var _binding: FragmentPlaceBinding? = null


    private lateinit var placeViewModel: PlaceViewModel




    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        placeViewModel = ViewModelProvider(this )[PlaceViewModel::class.java]
        _binding= FragmentPlaceBinding.inflate(inflater, container, false)

// se programa la accion  add lugar
        binding.addPlaceBotton.setOnClickListener{
            try{
                println("hola")
                findNavController().navigate(R.id.action_nav_place_to_addPlaceFragment)
            }catch (ex:Exception){

            }

        }



        // activar reciclador
        val placeAdapter=PlaceAdapter()
        val reciclador = binding.reciclador

        reciclador.adapter= placeAdapter
        reciclador.layoutManager= LinearLayoutManager(requireContext())

        placeViewModel= ViewModelProvider(this)[PlaceViewModel::class.java]

        placeViewModel.getAllDate.observe(viewLifecycleOwner){
            Place -> placeAdapter.setData(Place)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding= null
    }

}