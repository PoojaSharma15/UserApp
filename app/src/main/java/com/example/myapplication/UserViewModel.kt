package com.example.myapplication

import android.util.Log
import androidx.lifecycle.*
import com.example.myapplication.data.User
import com.example.myapplication.data.UserRepository
import kotlinx.coroutines.launch


class UserViewModel (private val repository: UserRepository): ViewModel() {

    val allUsers: LiveData<List<User>> = repository.allUsers.asLiveData()

    fun insert(user: User) = viewModelScope.launch {
        Log.i("Insert", "User")
        repository.insert(user)
    }

    fun search(username: String?): User? {
        Log.i("Search", "User")
        var user:User = User()
        viewModelScope.launch { user = repository.requestUser(username)!! }
        return user
    }
}

    class UserViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
                return UserViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }



