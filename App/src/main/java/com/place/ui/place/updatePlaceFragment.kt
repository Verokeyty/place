package com.place.ui.place

import android.os.Bundle
import android.provider.SyncStateContract.Helpers.update
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.place.R
import com.place.databinding.FragmentUpdatePlaceBinding

import com.place.model.Place
import com.place.ui.viewmodel.PlaceViewModel


class UpdatePlaceFragment : Fragment() {

    //argumento

    private val arg by navArgs<UpdatePlaceFragmentArgs>()

    private lateinit var placeViewModel: PlaceViewModel

    private var _binding: FragmentUpdatePlaceBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        placeViewModel = ViewModelProvider(this )[PlaceViewModel::class.java]
        _binding= FragmentUpdatePlaceBinding.inflate(inflater, container, false)

        //info objeto lugar

        binding.etNombre.setText(arg.place.nombre)
        binding.etTelefono.setText(arg.place.telefono)
        binding.etCorreo.setText(arg.place.correo)
        binding.etWeb.setText(arg.place.link)
        binding.btActualizar.setOnClickListener { updatePlace() }

             return binding.root
    }

    private fun updatePlace(){

        val nombre=binding.etNombre.text.toString()
        val correo=binding.etCorreo.text.toString()
        val telefono=binding.etTelefono.text.toString()
        val link=binding.etWeb.text.toString()
        if (nombre.isNotEmpty()){

          val place= Place(arg.place.id, nombre,correo,telefono,link, 0.0, 0.0, 0.0,"", "")
          PlaceViewModel.update(place)
            Toast.makeText(requireContext().getString(R.string.placeAdded), Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_nav_place_to_updatePlaceFragment)
            Toast.makeText(requireContext().getString(R.string.noData), Toast.LENGTH_SHORT).show()
        } else{

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding= null
    }
}