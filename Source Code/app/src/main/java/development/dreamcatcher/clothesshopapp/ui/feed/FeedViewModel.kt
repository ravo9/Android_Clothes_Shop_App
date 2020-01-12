package development.dreamcatcher.clothesshopapp.ui.feed

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import development.dreamcatcher.clothesshopapp.features.items.ItemsRepository
import development.dreamcatcher.clothesshopapp.features.items.database.ItemDatabaseEntity
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