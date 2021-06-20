package com.hexaengineer.cofinder.ui.personality

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.hexaengineer.cofinder.core.ui.ViewModelFactory
import com.hexaengineer.cofinder.databinding.ActivityPersonalityBinding
import com.hexaengineer.cofinder.ui.main.MainActivity

class PersonalityActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPersonalityBinding
    private lateinit var viewModel: PersonalityViewModel
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Personality Test"

        sharedPreferences = getSharedPreferences(PERSONALITY, Context.MODE_PRIVATE)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[PersonalityViewModel::class.java]

        binding.button.setOnClickListener {
            val words = binding.edtPersonality.text
            viewModel.postPersonality("1", words.toString()).observe(this, { userPersonality ->
                val editor = sharedPreferences.edit()
                editor.apply {
                    putString(PREF_EXT, userPersonality.ext_prediction.toString())
                    putString(PREF_NEU, userPersonality.neu_prediction.toString())
                    putString(PREF_AGR, userPersonality.agr_prediction.toString())
                    putString(PREF_CON, userPersonality.con_prediction.toString())
                    putString(PREF_OPN, userPersonality.opn_prediction.toString())
                }.apply()
            })
            Toast.makeText(this, "Sukses", Toast.LENGTH_SHORT).show()
            Log.d("Personality: ", words.toString())
        }
    }

    companion object {
        const val PERSONALITY = "personality"
        const val PREF_EXT = "pref_ext"
        const val PREF_NEU = "pref_neu"
        const val PREF_AGR = "pref_agr"
        const val PREF_CON = "pref_con"
        const val PREF_OPN = "pref_opn"
    }
}