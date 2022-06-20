package com.place.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.place.databinding.PlaceFilaBinding
import com.place.model.Place
import com.place.ui.place.PlaceFragmentDirections

class PlaceAdapter : RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>(){


    private var listaPlace= emptyList<Place>()

  inner class PlaceViewHolder(private val itemBinding: PlaceFilaBinding) :
  RecyclerView.ViewHolder(itemBinding.root){
     fun dibuja(place:Place){
         itemBinding.tvNombre.text= place.nombre
         itemBinding.tvCorreo.text= place.correo
         itemBinding.tvTelefono.text= place.telefono
         itemBinding.tvWeb.text= place.link
         itemBinding.vistaFila.setOnClickListener {
             val accion= PlaceFragmentDirections
                 .actionNavPlaceToUpdatePlaceFragment(place)
             itemView.findNavController().navigate(accion)
         }


     }

  }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
       val itemBinding= PlaceFilaBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PlaceViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        val place = listaPlace[position]
        holder.dibuja(place)
    }

    override fun getItemCount(): Int {
       return listaPlace.size
    }
    fun setData(place: List<Place>){
        this.listaPlace= place

        notifyDataSetChanged()
    }


}