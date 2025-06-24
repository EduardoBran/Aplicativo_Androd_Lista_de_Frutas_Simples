package com.luizeduardobrandao.fruitrecyclerviewmvvm2activities.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.luizeduardobrandao.fruitrecyclerviewmvvm2activities.R
import com.luizeduardobrandao.fruitrecyclerviewmvvm2activities.databinding.ActivityFruitDetailBinding
import com.luizeduardobrandao.fruitrecyclerviewmvvm2activities.viewmodel.FruitDetailViewModel

class FruitDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFruitDetailBinding
    private val viewModel: FruitDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityFruitDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setObservers()
    }

    private fun setObservers() {
        viewModel.fruit.observe(this) { fruit ->
            binding.textDetailName.text = fruit.name
            binding.textDetailDesc.text = fruit.description
        }
    }
}