package development.dreamcatcher.clothesshopapp.data.repositories

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import development.dreamcatcher.clothesshopapp.data.database.ItemsDatabaseInteractor
import development.dreamcatcher.clothesshopapp.data.database.ItemDatabaseEntity
import development.dreamcatcher.clothesshopapp.data.network.ItemsNetworkInteractor
import development.dreamcatcher.clothesshopapp.data.network.ItemGsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

// Data Repository - the main gate of the model (data) part of the application
class ItemsRepository @Inject constructor(private val ItemsNetworkInteractor:  ItemsNetworkInteractor,
                                              private val databaseInteractor: ItemsDatabaseInteractor) {

    fun getSingleSavedItemById(id: Int): LiveData<ItemDatabaseEntity>? {
        return databaseInteractor.getSingleSavedItemById(id)
    }

    fun getAllItems(backendUpdateRequired: Boolean): LiveData<List<ItemDatabaseEntity>>? {
        if (backendUpdateRequired) {
            updateDataFromBackEnd()
        }
        return databaseInteractor.getAllItems()
    }

    fun getNetworkError(): LiveData<Boolean>? {
        return ItemsNetworkInteractor.getNetworkError()
    }

    fun setNetworkError(t: Throwable?) {
        ItemsNetworkInteractor.setNetworkError(t)
    }

    @SuppressLint("CheckResult")
    private fun updateDataFromBackEnd() {

        ItemsNetworkInteractor.getAllItems().enqueue(object: Callback<List<ItemGsonObject>> {

            override fun onResponse(call: Call<List<ItemGsonObject>>?, response: Response<List<ItemGsonObject>>?) {

                // Clear database not to store outdated items
                databaseInteractor.clearDatabase()

                // Save freshly fetched items
                response?.body()?.forEach {
                    databaseInteractor.addNewItem(it)
                }
            }

            override fun onFailure(call: Call<List<ItemGsonObject>>?, t: Throwable?) {
                setNetworkError(t)
            }
        })
    }
}