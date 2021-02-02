package com.example.myapplication.data

class SampleDataProvider {
    companion object{
        private val user1 = User("Pooja", "Sharma",22,"pooja101","123")
        private val user2 = User("Ppppooja", "Sharrrrma",22,"pooja11101","12123")
        private val user3 = User("XXXX", "YYYY",22,"pooja333101","16423")


        fun getUsers()= arrayListOf(
                user1, user2, user3
        )
    }
}