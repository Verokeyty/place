package com.place.ui.place

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.place.R
import com.place.databinding.FragmentAddPlaceBinding
import com.place.model.Place
import com.place.ui.viewmodel.PlaceViewModel


class AddPlaceFragment : Fragment() {

    private lateinit var placeViewModel: PlaceViewModel

    private var _binding: FragmentAddPlaceBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        placeViewModel = ViewModelProvider(this )[PlaceViewModel::class.java]
        _binding= FragmentAddPlaceBinding.inflate(inflater, container, false)


        binding.btAdd.setOnClickListener { addPlace() }

             return binding.root
    }

    private fun addPlace(){

        val nombre=binding.etNombre.text.toString()
        val correo=binding.etCorreo.text.toString()
        val telefono=binding.etTelefono.text.toString()
        val link=binding.etWeb.text.toString()
        if (nombre.isNotEmpty()){

          val place= Place(0, nombre,correo,telefono,link, 0.0, 0.0, 0.0,"", "")
          placeViewModel.addPlace(place)
            Toast.makeText(requireContext().getString(R.string.placeAdded), Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addPlaceFragment_to_nav_place)
    } else{
            Toast.makeText(requireContext().getString(R.string.noData), Toast.LENGTH_SHORT).show()

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding= null
    }
}