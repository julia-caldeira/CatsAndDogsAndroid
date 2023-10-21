package com.example.catsanddogsandroid.viewModel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DisplaySentenceViewModel: ViewModel() {

    private var outputSentence = MutableLiveData<String>()

    init {
        outputSentence.value = ""
    }

    fun selectAnimalWarning(context: Context) {
        val text = "Escolha gato ou cão para receber uma curiosidade!"
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(context, text, duration)
        toast.show()
    }

    fun getSentence() : LiveData<String> {
        return outputSentence
    }

    fun generateCatSentence() {
        outputSentence.value = catSentences.random()
        getSentence()
    }

    fun generateDogSentence() {
        outputSentence.value = dogSentences.random()
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