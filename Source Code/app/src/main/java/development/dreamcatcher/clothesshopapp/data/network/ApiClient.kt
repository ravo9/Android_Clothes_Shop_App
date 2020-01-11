package development.dreamcatcher.clothesshopapp.data.network

import retrofit2.Call
import retrofit2.http.GET

// External gate for communication with API endpoints (operated by Retrofit)
interface ApiClient {

    @GET("/products")
    fun getItems(): Call<List<ItemGsonObject>>
}