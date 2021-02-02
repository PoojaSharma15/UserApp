package com.example.myapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.myapplication.R
import com.example.myapplication.UserViewModel
import com.example.myapplication.UserViewModelFactory
import com.example.myapplication.UsersApplication
import com.example.myapplication.data.User
import com.example.myapplication.databinding.FragmentAddUserBinding
import com.example.myapplication.utils.getErrorType

class AddUserFragment : Fragment() {

    private lateinit var user: User
    private lateinit var frBinding: FragmentAddUserBinding

    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory((activity?.application as UsersApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        frBinding = FragmentAddUserBinding.inflate(inflater, container, false)
        return frBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        user = User()

        frBinding.SubmitBtn.setOnClickListener {
            user.firstName = frBinding.editTextFirstName.editText?.text.toString()
            user.lastName = frBinding.editTextLastName.editText?.text.toString()
            user.age = Integer.parseInt(frBinding.editTextAge.editText?.text.toString())
            user.username = frBinding.editTextUsername.editText?.text.toString()
            user.password = frBinding.editTextPassword.editText?.text.toString()

            if (validateEntry()) {
                Toast.makeText(activity, "User Added Successfully ", Toast.LENGTH_LONG).show()
                userViewModel.insert(user)
                it.findNavController().navigate(R.id.action_addUserFragment_to_homeFragment)
            }
        }
    }

    private fun validateEntry(): Boolean {
        var error_code: Int
        error_code = getErrorType(user)

        //setting error message in respective fields, can add more codes for specific cases
        when (error_code) {
            1 -> frBinding.editTextFirstName.editText?.setError(" Enter valid first name")
            2 -> frBinding.editTextLastName.editText?.setError(" Enter valid first name")
            3 -> frBinding.editTextAge.editText?.setError("Age cannot be below 18")
            4 -> frBinding.editTextUsername.editText?.setError("Min 8 - Max 16 characters,One upper Case,One lower case, only alphanumeric with '-', '@' ")
            5 -> frBinding.editTextPassword.editText?.setError("Min 8 - Max 16 characters,One upper Case,One lower case, Must contain a special character")
        }

        //Validation check for password matching
        if (!frBinding.editTextPassword.editText?.text.toString()
                .equals(frBinding.editTextConfPassword.editText?.text.toString())
        ) {
            error_code = 6
        }
        when (error_code) {
            6 -> frBinding.editTextConfPassword.editText?.setError("Passwords don't match")
            0 -> return true
        }
        return false
    }
}
