package com.hexaengineer.cofinder.ui.profile

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hexaengineer.cofinder.databinding.FragmentProfileBinding
import com.hexaengineer.cofinder.ui.personality.PersonalityActivity
import com.hexaengineer.cofinder.ui.personality.PersonalityActivity.Companion.PREF_AGR
import com.hexaengineer.cofinder.ui.personality.PersonalityActivity.Companion.PREF_CON
import com.hexaengineer.cofinder.ui.personality.PersonalityActivity.Companion.PREF_EXT
import com.hexaengineer.cofinder.ui.personality.PersonalityActivity.Companion.PREF_NEU
import com.hexaengineer.cofinder.ui.personality.PersonalityActivity.Companion.PREF_OPN

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnPersonality.setOnClickListener {
            val intent = Intent(activity, PersonalityActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        sharedPreferences = requireContext().getSharedPreferences(PersonalityActivity.PERSONALITY, Context.MODE_PRIVATE)

        val extroversion = sharedPreferences.getString(PREF_EXT, "not tested")
        val neuroticism = sharedPreferences.getString(PREF_NEU, "not tested")
        val agreeableness = sharedPreferences.getString(PREF_AGR, "not tested")
        val conscientiousness = sharedPreferences.getString(PREF_CON, "not tested")
        val openness = sharedPreferences.getString(PREF_OPN, "not tested")

        binding.apply {
            tvExtroversion.text = "Extroversion: $extroversion"
            tvNeuroticism.text = "Neuroticism: $neuroticism"
            tvAgreeableness.text = "Agreeableness: $agreeableness"
            tvConscientiousness.text = "Conscientiousness: $conscientiousness"
            tvOpenness.text = "Openness: $openness"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}