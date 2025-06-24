package com.luizeduardobrandao.fruitrecyclerviewmvvm2activities.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.luizeduardobrandao.fruitrecyclerviewmvvm2activities.entity.FruitEntity
import com.luizeduardobrandao.fruitrecyclerviewmvvm2activities.repository.FruitRepository

/**
 * ViewModel da tela de detalhe.
 * Agora estende AndroidViewModel para receber Application
 * e também aceita SavedStateHandle para ler os extras.
 */
class FruitDetailViewModel(
    app: Application,
    private val state: SavedStateHandle
): AndroidViewModel(app) {

    // Criamos o repo aqui usando o Application (contexto correto)
    private val repo = FruitRepository(app)

    // LiveData privada que guarda o FruitEntity selecionado
    private val _fruit = MutableLiveData<FruitEntity>()
    val fruit: LiveData<FruitEntity> = _fruit

    init{
        // 1) Lê o parâmetro "fruitName" que a MainActivity colocou na Intent
        //    O SavedStateHandle age como um mapa chave→valor dos argumentos.
    val name: String? = state["fruitName"]
        // 2) Só continua se esse nome não for nulo
        name?.let {
            // 3) Carrega TODA a lista de frutas do repositório
            //    (que leu o JSON em res/raw)
            repo.getFruits()
                // 4) Procura o primeiro elemento cujo .name coincida com a chave
                .firstOrNull { f -> f.name == it }
                // 5) Se encontrou (não retornou null), faz:
                ?.let { match ->
                    // 6) Atribui ao MutableLiveData, despachando o dado
                    _fruit.value = match
                }
        }
    }
}

/*


Por que usar SavedStateHandle?
- Captura e persiste argumentos (como extras de Intent) de forma segura.
- Sobrevive a rotações e a possíveis recriações de processo pelo sistema.
- Evita que você precise extrair manualmente intent.getStringExtra(...) na Activity.

Por que state.get<Application>("app")!!?
- Para criar o FruitRepository precisamos de um Context (aqui usamos o Application).
- Ao usar SavedStateHandle numa Activity ou Fragment, o framework salva automaticamente
o Application sob a chave "app".
- Com state.get<Application>("app")!! recuperamos esse Application sem precisar
passar o contexto pela fábrica do ViewModel.
- O !! indica que esperamos sempre encontrar esse valor; se não estiver lá, lançará erro.

 */