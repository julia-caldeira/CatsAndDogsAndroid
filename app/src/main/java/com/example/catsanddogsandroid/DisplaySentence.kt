package com.example.catsanddogsandroid

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.catsanddogsandroid.databinding.DisplaySentenceBinding
import com.caverock.androidsvg.SVG
import com.caverock.androidsvg.SVGParseException
import java.io.IOException

class DisplaySentence : AppCompatActivity(), View.OnClickListener{

    private lateinit var binding : DisplaySentenceBinding
    private var isDogImageClicked = false
    private var isCatImageClicked = false


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
                    val text = "Escolha gato ou cão para receber uma curiosidade!"
                    val duration = Toast.LENGTH_SHORT
                    val toast = Toast.makeText(applicationContext, text, duration)
                    toast.show()
                }
                if(isCatImageClicked){
                    val randomCatSentence = catSentences.random()
                    binding.tvSentence.text = randomCatSentence
                }else if(isDogImageClicked){
                    val randomDogSentence = dogSentences.random()
                    binding.tvSentence.text = randomDogSentence
                }
            }
        }
    }

    companion object {
        val catSentences = listOf(
            "Purring does not always indicate that a cat is happy and healthy - some cats will purr loudly when they are terrified or in pain.",
            "Baking chocolate is the most dangerous chocolate to your cat.",
            "Cats have been domesticated for half as long as dogs have been."
        )

        val dogSentences = listOf(
            "Yorkshires do not have a high IQ.",
            "Dogs are energetic.",
            "Most dogs have a lot of fur."
        )
    }

}
