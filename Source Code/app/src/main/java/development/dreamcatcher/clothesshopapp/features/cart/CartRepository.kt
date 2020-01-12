package development.dreamcatcher.clothesshopapp.features.cart

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import development.dreamcatcher.clothesshopapp.features.cart.database.CartDatabaseInteractor
import development.dreamcatcher.clothesshopapp.features.cart.database.CartItemDatabaseEntity
import development.dreamcatcher.clothesshopapp.features.cart.network.CartNetworkInteractor
import development.dreamcatcher.clothesshopapp.features.items.database.ItemsDatabaseInteractor
import io.reactivex.Observable
import io.reactivex.subjects.SingleSubject
import javax.inject.Inject

// Data Repository - the main gate of the model (features) part of the application
class CartRepository @Inject constructor(private val cartNetworkInteractor: CartNetworkInteractor,
                                         private val databaseInteractor: CartDatabaseInteractor
) {

    fun getAllCartItems(): LiveData<List<CartItemDatabaseEntity>>? {
        return databaseInteractor.getCartItems()
    }

    fun getNetworkError(): LiveData<Boolean>? {
        return cartNetworkInteractor.networkError
    }

    @SuppressLint("CheckResult")
    private fun addItemToCart(item: CartItemDatabaseEntity): Observable<Boolean>? {
        val addItemSubject = SingleSubject.create<Boolean>()

        cartNetworkInteractor.addItemToCart(item).subscribe {
            if (it.isSuccess) {

                // Update database
                databaseInteractor.addNewItem(item)

                // Set observable value
                addItemSubject.onSuccess(true)
            }
        }

        return addItemSubject.toObservable()
    }
}