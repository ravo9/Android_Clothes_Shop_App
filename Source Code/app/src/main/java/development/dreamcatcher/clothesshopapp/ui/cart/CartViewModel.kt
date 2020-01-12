package development.dreamcatcher.clothesshopapp.ui.cart

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import development.dreamcatcher.clothesshopapp.features.cart.CartRepository
import javax.inject.Inject

class CartViewModel @Inject constructor(private val cartRepository: CartRepository)
    : ViewModel(), LifecycleObserver {

    /*fun getSingleSavedItemById(id: Int): LiveData<ItemDatabaseEntity>? {
        return itemsRepository.getSingleSavedItemById(id)
    }*/
}