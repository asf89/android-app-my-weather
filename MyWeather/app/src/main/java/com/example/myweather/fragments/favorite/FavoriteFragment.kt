package com.example.myweather.fragments.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myweather.databinding.FragmentFavoriteBinding

class FavoriteFragment: Fragment() {

    private val favoriteViewModel: FavoriteViewModel by activityViewModels()
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val favoriteView: View = binding.root
        val textField: TextView = binding.textFavorite
        favoriteViewModel.getFavorites().observe(viewLifecycleOwner, {
            textField.text = it
        })

        return favoriteView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}



