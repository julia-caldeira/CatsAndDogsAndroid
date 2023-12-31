package com.example.catsanddogsandroid.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.catsanddogsandroid.DisplaySentence
import com.example.catsanddogsandroid.R
import com.example.catsanddogsandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding : ActivityMainBinding;

    private lateinit var sharedPreferences: SharedPreferences
    private val usernameKey = "username"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)

        binding.btUserName.setOnClickListener(this)

        val savedUsername = sharedPreferences.getString(usernameKey, "")
        binding.etUserName.setText(savedUsername)


    }

    override fun onClick(view: View) {

        if(view.id == R.id.btUserName){
            var username = binding.etUserName.text.toString()
            val intent = Intent(this, DisplaySentence::class.java)

            val editor = sharedPreferences.edit()
            editor.putString(usernameKey, username)
            editor.apply()

            intent.putExtra("user_name_key", username)
            startActivity(intent)
        }

    }
}