package development.dreamcatcher.clothesshopapp.features.cart.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import development.dreamcatcher.clothesshopapp.features.cart.network.CartItemGsonObject
import kotlinx.coroutines.launch

// Interactor used for communication between repository and internal database
class CartDatabaseInteractor(private val cartDatabase: CartDatabase) {

    fun addNewItem(item: CartItemGsonObject): LiveData<Boolean> {
        val itemAddedLiveData = MutableLiveData<Boolean>()
        item.let {
            if (it.cartId != null && it.productId != null) {
                val cartItem =
                    CartItemDatabaseEntity(
                        cartId = it.cartId,
                        productId = it.productId
                    )
                launch {
                    cartDatabase.getCartItemsDao().insertNewCartItem(cartItem)
                }
            }
        }
        itemAddedLiveData.postValue(true)
        return itemAddedLiveData
    }

    fun removeItem(itemId: Int): LiveData<Boolean> {
        val itemAddedLiveData = MutableLiveData<Boolean>()
        itemId.let {
                launch {
                    cartDatabase.getCartItemsDao().removeItemById(itemId)
                }
            }
        itemAddedLiveData.postValue(true)
        return itemAddedLiveData
    }

    fun getCartItems(): LiveData<List<CartItemDatabaseEntity>>? {
        return cartDatabase.getCartItemsDao().getCartItems()
    }
}
