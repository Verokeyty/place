package com.place.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.place.databinding.FragmentPlaceBinding
import com.place.ui.viewmodel.PlaceViewModel

class AddPlaceFragment : Fragment() {

    private lateinit var placeViewModel: PlaceViewModel

    private var _binding: FragmentPlaceBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        placeViewModel = ViewModelProvider(this).get(PlaceViewModel::class.java)
        _binding = FragmentPlaceBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

