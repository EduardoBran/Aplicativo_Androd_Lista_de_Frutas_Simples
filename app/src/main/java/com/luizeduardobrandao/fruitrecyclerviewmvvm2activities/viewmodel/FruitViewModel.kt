package com.luizeduardobrandao.fruitrecyclerviewmvvm2activities.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.luizeduardobrandao.fruitrecyclerviewmvvm2activities.entity.FruitEntity
import com.luizeduardobrandao.fruitrecyclerviewmvvm2activities.repository.FruitRepository

// Expõe a lista de frutas lida do JSON via LiveData.
class FruitViewModel(app: Application): AndroidViewModel(app) {

    private val repo = FruitRepository(app)

    private val _fruits = MutableLiveData<List<FruitEntity>>()
    val fruits: LiveData<List<FruitEntity>> = _fruits

    init {
        _fruits.value = repo.getFruits()
    }
}