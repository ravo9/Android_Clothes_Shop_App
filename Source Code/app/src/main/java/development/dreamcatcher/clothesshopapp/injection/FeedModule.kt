package development.dreamcatcher.clothesshopapp.injection

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import development.dreamcatcher.clothesshopapp.features.items.ItemsRepository
import development.dreamcatcher.clothesshopapp.features.items.database.CartDatabase
import development.dreamcatcher.clothesshopapp.features.items.database.ItemsDatabaseInteractor
import development.dreamcatcher.clothesshopapp.features.items.network.ApiClient
import development.dreamcatcher.clothesshopapp.features.items.network.ItemsNetworkInteractor
import development.dreamcatcher.clothesshopapp.features.items.network.NetworkAdapter
import javax.inject.Singleton

@Module
class FeedModule {

    @Provides
    @Singleton
    fun providesDatabase(application: Context): CartDatabase {
        return Room.databaseBuilder(application, CartDatabase::class.java, "main_database").build()
    }

    @Provides
    @Singleton
    fun providesItemsDatabaseInteractor(itemsDatabase: CartDatabase): ItemsDatabaseInteractor {
        return ItemsDatabaseInteractor(
            itemsDatabase
        )
    }

    @Provides
    @Singleton
    fun providesItemsNetworkInteractor(apiClient: ApiClient): ItemsNetworkInteractor {
        return ItemsNetworkInteractor(
            apiClient
        )
    }

    @Provides
    @Singleton
    fun providesApiClient(): ApiClient {
        return NetworkAdapter.apiClient()
    }

    @Provides
    @Singleton
    fun providesItemsRepository(ItemsNetworkInteractor: ItemsNetworkInteractor,
                                itemsDatabaseInteractor: ItemsDatabaseInteractor
    ): ItemsRepository {
        return ItemsRepository(
            ItemsNetworkInteractor,
            itemsDatabaseInteractor
        )
    }
}