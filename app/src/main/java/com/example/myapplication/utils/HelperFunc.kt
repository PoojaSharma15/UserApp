package com.example.myapplication.utils

import com.example.myapplication.data.User

fun getErrorType(user: User): Int {

    if (!user.firstName.matches(Regex("[a-zA-Z ]+")))
        return 1
    else if (!user.lastName.matches(Regex("[a-zA-Z ]+")))
        return 2
    else if(user.age < 18)
        return 3
    else if(!user.username.matches(Regex("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[@-]).{8,16}\$")))
        return 4
    else if(!user.password.matches(Regex("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[#?!@\$%^&*-]).{8,16}\$")))
        return 5
    else return 0
}




