package ru.androidpiraters.aiweather.data

import android.content.Context
import android.speech.tts.TextToSpeech

class TextToSpeechHelper {

    companion object {
        private var textToSpeech: TextToSpeech? = null

        fun init(context: Context?) {
            if (context == null) return

            if (textToSpeech == null) {
                textToSpeech = TextToSpeech(context, TextToSpeech.OnInitListener { })
            }
        }

        fun speak(text: String) {
            textToSpeech!!.speak(text, android.speech.tts.TextToSpeech.QUEUE_FLUSH, null, "0")
        }
    }
}