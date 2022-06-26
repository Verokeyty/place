package com.place.ui.place

import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.Manifest
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.place.R
import com.place.databinding.FragmentUpdatePlaceBinding

import android.provider.SyncStateContract.Helpers.update
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


        binding.tvAltura.text=arg.place.altura.toString()

        binding.tvLatitud.text=arg.place.latidud.toString()

        binding.tvLongitud.text=arg.place.longitud.toString()


        binding.btActualizar.setOnClickListener { updatePlace() }

        binding.btEmail.setOnClickListener{escribirCorreo()}
        binding.btPhone.setOnClickListener{llamarPlace()}

       binding.etWeb.setOnClickListener{verweb()}
       /*  binding.btWhatsapp.setOnClickListener{enviarWhat()}
        binding.btLocation.setOnClickListener{verMapa()}*/





        setHasOptionsMenu(true)
             return binding.root

    }

    private fun verweb() {
        val recurso= binding.etWeb.text.toString()
        if (recurso.isNotEmpty()){
            //se abre el sitio

             val rutina = Intent(Intent.ACTION_VIEW, Uri.parse("http://$recurso"))

            startActivity(rutina) //levanta el browser y se el sitio
        }else {
            Toast.makeText(requireContext(),
                getString(R.string.msg_datos),Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun llamarPlace() {
        val recurso= binding.etTelefono.text.toString()
        if (recurso.isNotEmpty()) {

            //se activa correo
            val rutina = Intent(Intent.ACTION_CALL)
            rutina.data = Uri.parse("tel:$recurso")
            if (
                requireActivity().checkSelfPermission(Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED){

            //solicitar permiso
                requireActivity()
                    .requestPermissions(arrayOf(Manifest.permission.CALL_PHONE), 105)
        }else{//se tiene permiso
            requireActivity().startActivity(rutina)

        }

        }else {
            Toast.makeText(requireContext(),
                getString(R.string.msg_datos),Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun escribirCorreo() {
        val recurso= binding.etCorreo.text.toString()
        if (recurso.isNotEmpty()){

           //se activa correo
            val rutina = Intent(Intent.ACTION_SEND)
            rutina.type="message/rfc822"
            rutina.putExtra(Intent.EXTRA_EMAIL, arrayOf(recurso))
            rutina.putExtra(Intent.EXTRA_SUBJECT,
            getString(R.string.msg_saludos)+" "+binding.etNombre.text)
            rutina.putExtra(Intent.EXTRA_TEXT,
            getString(R.string.msg_mensaje_correo))
            startActivity(rutina) //levanta el correo y lo presenta para modificar y enviar
        }else {
            Toast.makeText(requireContext(),
                getString(R.string.msg_datos),Toast.LENGTH_SHORT)
                .show()
        }


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