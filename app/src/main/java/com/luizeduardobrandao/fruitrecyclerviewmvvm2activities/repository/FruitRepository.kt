package com.luizeduardobrandao.fruitrecyclerviewmvvm2activities.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.luizeduardobrandao.fruitrecyclerviewmvvm2activities.R
import com.luizeduardobrandao.fruitrecyclerviewmvvm2activities.entity.FruitEntity

class FruitRepository(private val context: Context) {

    fun getFruits(): List<FruitEntity> {

        // abre o raw resouce
        val input = context.resources.openRawResource(R.raw.fruits)
        val json = input.bufferedReader().use { it.readText() }

        // converte JSON em List<FruitEntity> usando Gson
        val listType = object: TypeToken<List<FruitEntity>>() {}.type
        return Gson().fromJson(json, listType)
    }
}