package com.example.radiusandroidapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.radiusandroidapp.R
import com.example.radiusandroidapp.databinding.FragmentHomeRadiusBinding
import com.example.radiusandroidapp.db.AppDatabase
import com.example.radiusandroidapp.repository.RadiusRepository
import com.example.radiusandroidapp.utils.Constant
import com.example.radiusandroidapp.utils.Constant.TAG
import com.example.radiusandroidapp.viewmodel.RadiusViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeRadiusFragment : Fragment() {
    private var _binding: FragmentHomeRadiusBinding? = null
    private val binding get() = _binding!!

//    private val radiusViewModel by viewModels<RadiusViewModel>()
private val viewModel: RadiusViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeRadiusBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Observe the LiveData in the view model
        viewModel.radiusLiveData.observe(viewLifecycleOwner) { result ->
            Log.d(Constant.TAG, "getAllProductBodyDataViewFragment: $result")
            // Handle the data as needed

        }

        // Trigger the data retrieval
        viewModel.getAllProduct()
    }


}