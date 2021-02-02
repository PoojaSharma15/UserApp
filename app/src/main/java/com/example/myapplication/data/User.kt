package com.example.myapplication.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.fragments.HomeFragment

@Entity(tableName = "User_table")
class User(

           @ColumnInfo(name = "First Name")
           var firstName: String ,

           @ColumnInfo(name = "Last Name")
           var lastName :String,

           @ColumnInfo(name = "Age")
           var age : Int,

           @PrimaryKey@ColumnInfo(name = "Username")
           var username : String,

           @ColumnInfo(name = "Password")
           var password : String){

    constructor():this("","",-1,"","")

}