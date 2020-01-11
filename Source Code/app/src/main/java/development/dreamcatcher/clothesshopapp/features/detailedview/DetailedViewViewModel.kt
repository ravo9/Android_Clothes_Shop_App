package development.dreamcatcher.clothesshopapp.features.detailedview

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import development.dreamcatcher.clothesshopapp.data.database.ItemDatabaseEntity
import development.dreamcatcher.clothesshopapp.data.repositories.ItemsRepository
import javax.inject.Inject

class DetailedViewViewModel @Inject constructor(private val itemsRepository: ItemsRepository)
    : ViewModel(), LifecycleObserver {

    fun getSingleSavedItemById(id: Int): LiveData<ItemDatabaseEntity>? {
        return itemsRepository.getSingleSavedItemById(id)
    }
}