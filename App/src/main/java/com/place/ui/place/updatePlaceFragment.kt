package com.place.ui.place

import android.app.AlertDialog
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.update
import android.view.*
import androidx.fragment.app.Fragment
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


        setHasOptionsMenu(true)
             return binding.root

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete){
            deletePlace()
    }
        return super.onOptionsItemSelected(item)
    }


    private fun deletePlace() {
        val consulta=AlertDialog.Builder(requireContext())

        consulta.setTitle(R.string.delete)
        consulta.setMessage(getString(R.string.seguroBorrar)+" ${arg.place.nombre}?")
        //Acciones a ejecutar en caso de si
        consulta.setPositiveButton(getString(R.string.si)){ _,_->
            placeViewModel.deletePlace(arg.place)
            findNavController().navigate(R.id.action_updatePlaceFragment_to_nav_place)
        }
        consulta.setNegativeButton(getString(R.string.no)) { _,_->}
        consulta.create().show()    }

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