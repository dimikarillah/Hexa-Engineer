package com.hexaengineer.cofinder.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.hexaengineer.cofinder.core.domain.model.User
import com.hexaengineer.cofinder.core.ui.ViewModelFactory
import com.hexaengineer.cofinder.databinding.ActivityDetailUserBinding

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val user = intent.getParcelableExtra<User>(EXTRA_DATA)

        if (user != null) {
            binding.apply {
                tvName.text = user.name
                tvSkill.text = user.skills
                tvCity.text = user.city
            }
            Glide.with(this)
                .load(user.picture)
                .into(binding.imgUser)
        }

        val userId = user?.id

        viewModel.getDetail(userId.toString()).observe(this, { userDetail ->
            showLoading(false)
            binding.apply {
                tvDescription.text = userDetail.description
                tvAddress.text = userDetail.address
                tvContact.text = userDetail.kontak
            }
        })


    }

    private fun showLoading(state: Boolean) {
        binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}