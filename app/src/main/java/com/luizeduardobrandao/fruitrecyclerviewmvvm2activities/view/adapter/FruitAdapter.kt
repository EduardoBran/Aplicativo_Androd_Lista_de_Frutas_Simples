package com.luizeduardobrandao.fruitrecyclerviewmvvm2activities.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luizeduardobrandao.fruitrecyclerviewmvvm2activities.databinding.ItemFruitBinding
import com.luizeduardobrandao.fruitrecyclerviewmvvm2activities.entity.FruitEntity

class FruitAdapter(
    private val items: List<FruitEntity>,
    private val onClick: (FruitEntity) -> Unit): RecyclerView.Adapter<FruitAdapter.FruitViewHolder>() {

    class FruitViewHolder(val binding: ItemFruitBinding) : RecyclerView.ViewHolder(binding.root)

    // Cria e inicializa cada ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitViewHolder {
        val binding = ItemFruitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FruitViewHolder(binding)
    }

    // Mét0do que retorna o número total de itens no RecyclerView
    override fun getItemCount(): Int {
        return items.size
    }

    // Vincula dados a cada ViewHolder criado
    override fun onBindViewHolder(holder: FruitViewHolder, position: Int) {
        val fruit = items[position]
        holder.binding.textNameFruit.text = fruit.name
        holder.binding.textDetailFruit.text = fruit.description
        holder.binding.root.setOnClickListener { onClick(fruit) }
    }
}