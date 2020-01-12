package development.dreamcatcher.clothesshopapp.features.items.network

import io.reactivex.Observable
import retrofit2.http.GET

// External gate for communication with API endpoints (operated by Retrofit)
interface ApiClient {

    @GET("/products")
    fun getItems(): Observable<List<ItemGsonObject>>
}