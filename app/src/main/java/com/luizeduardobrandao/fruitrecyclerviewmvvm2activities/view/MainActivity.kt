package com.luizeduardobrandao.fruitrecyclerviewmvvm2activities.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.luizeduardobrandao.fruitrecyclerviewmvvm2activities.R
import com.luizeduardobrandao.fruitrecyclerviewmvvm2activities.databinding.ActivityMainBinding
import com.luizeduardobrandao.fruitrecyclerviewmvvm2activities.view.adapter.FruitAdapter
import com.luizeduardobrandao.fruitrecyclerviewmvvm2activities.viewmodel.FruitViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: FruitViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setRecycler()
        setObservers()
    }

    private fun setRecycler(){
        binding.recyclerFruits.layoutManager = LinearLayoutManager(this)
    }

    private fun setObservers() {
        viewModel.fruits.observe(this) { listFruits ->
            binding.recyclerFruits.adapter = FruitAdapter(listFruits) { fruit ->
                // passando o nome da fruta
                startActivity(Intent(this, FruitDetailActivity::class.java).apply {
                    putExtra("fruitName", fruit.name)
                })
            }
        }
    }
}