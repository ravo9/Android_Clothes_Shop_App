package development.dreamcatcher.clothesshopapp.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import javax.inject.Inject

// Interactor used for communication between data repository and external API
class ItemsNetworkInteractor @Inject constructor(var apiClient: ApiClient) {

    private val networkError: MutableLiveData<Boolean> = MutableLiveData()

    fun getAllItems(): Call<List<ItemGsonObject>> {
        return apiClient.getItems()
    }

    fun getNetworkError(): LiveData<Boolean>? {
        return networkError
    }

    fun setNetworkError(t: Throwable?) {
        networkError.postValue(true)
        if (t != null) { Log.e("Network Error: ", t.message) }
    }
}