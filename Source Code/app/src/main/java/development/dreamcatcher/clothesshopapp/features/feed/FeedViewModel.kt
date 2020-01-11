package development.dreamcatcher.clothesshopapp.features.feed

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import development.dreamcatcher.clothesshopapp.data.database.ItemDatabaseEntity
import development.dreamcatcher.clothesshopapp.data.repositories.ItemsRepository
import javax.inject.Inject

class FeedViewModel @Inject constructor(private val itemsRepository: ItemsRepository)
    : ViewModel(), LifecycleObserver {

    fun getAllItems(backendUpdateRequired: Boolean): LiveData<List<ItemDatabaseEntity>>? {
        return itemsRepository.getAllItems(backendUpdateRequired)
    }

    fun getNetworkError(): LiveData<Boolean>? {
        return itemsRepository.getNetworkError()
    }
}