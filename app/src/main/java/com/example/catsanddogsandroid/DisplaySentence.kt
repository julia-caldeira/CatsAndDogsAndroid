package com.example.catsanddogsandroid

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.catsanddogsandroid.databinding.DisplaySentenceBinding
import com.caverock.androidsvg.SVG
import com.caverock.androidsvg.SVGParseException
import com.example.catsanddogsandroid.viewModel.DisplaySentenceViewModel
import java.io.IOException

class DisplaySentence : AppCompatActivity(), View.OnClickListener{
    val context = this

    private lateinit var binding : DisplaySentenceBinding
    private var isDogImageClicked = false
    private var isCatImageClicked = false

    private lateinit var displaySentenceVM : DisplaySentenceViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DisplaySentenceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        val nomeUsuario = intent.getStringExtra("user_name_key")
        if (nomeUsuario != null && nomeUsuario != "") {
            binding.tvUserName.text = "Olá, $nomeUsuario"
        }

        binding.imgCat.setOnClickListener(this)
        binding.imgDog.setOnClickListener(this)
        binding.btGetSentence.setOnClickListener(this)

        displaySentenceVM = ViewModelProvider(this).get(DisplaySentenceViewModel::class.java)
        displaySentenceVM.selectAnimalWarning(context)
        setObserver()


    }

    private fun setObserver() {
        displaySentenceVM.getSentence().observe(this, Observer {
            binding.tvSentence.text = it
        })
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.imgCat -> {
                if (binding.imgCat.colorFilter == null) {
                    binding.imgCat.setColorFilter(ContextCompat.getColor(this, R.color.yellow))
                    isCatImageClicked = true
                    isDogImageClicked = false
                    binding.imgDog.setColorFilter(null) // Define a imagem do cachorro para a cor padrão
                } else {
                    isCatImageClicked = false
                    binding.imgCat.setColorFilter(null) // Define a imagem do gato para a cor padrão
                }
            }

            R.id.imgDog -> {
                if (binding.imgDog.colorFilter == null) {
                    binding.imgDog.setColorFilter(ContextCompat.getColor(this, R.color.yellow))
                    isDogImageClicked = true
                    isCatImageClicked = false
                    binding.imgCat.setColorFilter(null) // Define a imagem do gato para a cor padrão
                } else {
                    isDogImageClicked = false
                    binding.imgDog.setColorFilter(null) // Define a imagem do cachorro para a cor padrão
                }
            }

            R.id.btGetSentence -> {
                if(!isCatImageClicked && !isDogImageClicked) {
                    displaySentenceVM.selectAnimalWarning(context)
                }
                if(isCatImageClicked){
                    displaySentenceVM.generateCatSentence()
                }else if(isDogImageClicked){
                    displaySentenceVM.generateDogSentence()
                }
            }
        }
    }

}
