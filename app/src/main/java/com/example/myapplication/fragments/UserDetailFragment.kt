package com.example.myapplication.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.example.myapplication.UserViewModel
import com.example.myapplication.UserViewModelFactory
import com.example.myapplication.UsersApplication
import com.example.myapplication.databinding.FragmentUserDetailBinding

class UserDetailFragment : Fragment() {

    private lateinit var binding: FragmentUserDetailBinding
    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory((activity?.application as UsersApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var id = arguments?.getString("username").toString()
        Log.i("DETAIL", id)
        val select_user = userViewModel.search(id)

        if (select_user != null) {
            binding.DetailNameView.text = select_user.firstName.toString() + " " + select_user.lastName.toString()
            binding.DetailAgeView.text = select_user.age.toString()
            binding.DetailUsernameView.text = select_user.username
        } else {
            Log.i("DetailFragment", "User not found")
        }
    }
}