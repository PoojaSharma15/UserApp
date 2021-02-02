package com.example.myapplication.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.UserViewModel
import com.example.myapplication.UserViewModelFactory
import com.example.myapplication.UsersApplication
import com.example.myapplication.data.User
import com.example.myapplication.databinding.UsersListBinding
import com.example.myapplication.list.UserListAdapter

class ViewUserFragment : Fragment(), UserListAdapter.OnItemClickListener {
    private lateinit var binding: UsersListBinding

    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory((activity?.application as UsersApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = UsersListBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.UserListView
        val adapter = UserListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
        userViewModel.allUsers.observe(viewLifecycleOwner, Observer { users->
            users?.let { adapter.submitList(it)}
        })
    }

    override fun OnItemClick(user: User) {

        Log.i("Onitemclick", "executed")
       // setFragmentResult("user_name", bundleOf("bundlekey" to user.username))
        findNavController().navigate(R.id.action_viewUserFragment_to_userDetailFragment, bundleOf("username" to user.username))
    }


}