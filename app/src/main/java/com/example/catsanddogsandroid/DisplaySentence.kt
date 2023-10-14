package com.example.catsanddogsandroid

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.example.catsanddogsandroid.databinding.ActivityMainBinding
import com.example.catsanddogsandroid.databinding.DisplaySentenceBinding

class DisplaySentence : AppCompatActivity(), View.OnClickListener{

    private lateinit var binding : DisplaySentenceBinding;

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DisplaySentenceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        val nomeUsuario = intent.getStringExtra("user_name_key")
        if (nomeUsuario != null) {
            binding.tvUserName.text = "Olá $nomeUsuario"
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
                    binding.imgDog.setColorFilter(null) // Define a imagem do cachorro para a cor padrão
                } else {
                    binding.imgCat.setColorFilter(null) // Define a imagem do gato para a cor padrão
                }
            }

            R.id.imgDog -> {
                if (binding.imgDog.colorFilter == null) {
                    binding.imgDog.setColorFilter(ContextCompat.getColor(this, R.color.yellow))
                    binding.imgCat.setColorFilter(null) // Define a imagem do gato para a cor padrão
                } else {
                    binding.imgDog.setColorFilter(null) // Define a imagem do cachorro para a cor padrão
                }
            }

            R.id.btGetSentence -> {
                val yellowColor = ContextCompat.getColor(this, R.color.yellow)
                val imgCatColor = (binding.imgCat.drawable as? ColorDrawable)?.color
                if (imgCatColor == yellowColor) {
                    val randomCatSentence = catSentences.random()
                    binding.tvSentence.text = randomCatSentence
                }
                //if (imgCatColor != null)
                {

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
