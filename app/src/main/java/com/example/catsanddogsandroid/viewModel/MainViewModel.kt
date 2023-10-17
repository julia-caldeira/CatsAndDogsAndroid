package com.example.catsanddogsandroid.viewModel

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private lateinit var sharedPreferences: SharedPreferences

    private val usernameKey = "username"
    private var savedUserName  = ""

    fun saveUserName(userName: String){
        sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        savedUserName = userName
        val editor = sharedPreferences.edit()
        editor.putString(usernameKey, userName)
        editor.apply()
    }

    fun getUserName(): String {
        return savedUserName
    }

}