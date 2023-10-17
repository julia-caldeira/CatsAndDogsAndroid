package com.example.catsanddogsandroid.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.catsanddogsandroid.DisplaySentence
import com.example.catsanddogsandroid.R
import com.example.catsanddogsandroid.databinding.ActivityMainBinding
import com.example.catsanddogsandroid.viewModel.MainViewModel

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding : ActivityMainBinding;
    private lateinit var mainVM: MainViewModel

    //private lateinit var sharedPreferences: SharedPreferences
    //private val usernameKey = "username"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)

        binding.btUserName.setOnClickListener(this)

        //val savedUsername = sharedPreferences.getString(usernameKey, "")
        binding.etUserName.setText(mainVM.getUserName())

        mainVM  = ViewModelProvider(this).get(MainViewModel::class.java)

    }

    override fun onClick(view: View) {

        if(view.id == R.id.btUserName){
            var username = binding.etUserName.text.toString()
            val intent = Intent(this, DisplaySentence::class.java)

//            val editor = sharedPreferences.edit()
//            editor.putString(usernameKey, username)
//            editor.apply()

            intent.putExtra("user_name_key", username)
            startActivity(intent)
        }

    }
}