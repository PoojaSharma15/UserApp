package com.example.myapplication.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.HomeFragmentBinding

class HomeFragment : BaseFragment() {

    private lateinit var binding: HomeFragmentBinding
    //private lateinit var viewModel: SharedViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.addUserBtn.setOnClickListener{
            Log.i("HOME", "ADD USER")
            it.findNavController().navigate(R.id.action_homeFragment_to_addUserFragment)
        }

        binding.viewUserBtn.setOnClickListener{view:View->
            Log.i("HOME", "VIEW USER")
            view.findNavController().navigate(R.id.action_homeFragment_to_viewUserFragment)
        }
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(SharedViewModel::class.java)
//        // TODO: Use the ViewModel
//    }

}