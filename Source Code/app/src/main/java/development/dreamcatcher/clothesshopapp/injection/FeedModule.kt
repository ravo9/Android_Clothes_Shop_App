package development.dreamcatcher.clothesshopapp.injection

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import development.dreamcatcher.clothesshopapp.data.database.ItemsDatabase
import development.dreamcatcher.clothesshopapp.data.database.ItemsDatabaseInteractor
import development.dreamcatcher.clothesshopapp.data.network.ApiClient
import development.dreamcatcher.clothesshopapp.data.network.ItemsNetworkInteractor
import development.dreamcatcher.clothesshopapp.data.network.NetworkAdapter
import development.dreamcatcher.clothesshopapp.data.repositories.ItemsRepository
import javax.inject.Singleton

@Module
class FeedModule {

    @Provides
    @Singleton
    fun providesDatabase(application: Context): ItemsDatabase {
        return Room.databaseBuilder(application, ItemsDatabase::class.java, "main_database").build()
    }

    @Provides
    @Singleton
    fun providesItemsDatabaseInteractor(itemsDatabase: ItemsDatabase): ItemsDatabaseInteractor {
        return ItemsDatabaseInteractor(itemsDatabase)
    }

    @Provides
    @Singleton
    fun providesItemsNetworkInteractor(apiClient: ApiClient): ItemsNetworkInteractor {
        return ItemsNetworkInteractor(apiClient)
    }

    @Provides
    @Singleton
    fun providesApiClient(): ApiClient {
        return NetworkAdapter.apiClient()
    }

    @Provides
    @Singleton
    fun providesItemsRepository(ItemsNetworkInteractor: ItemsNetworkInteractor,
                                    itemsDatabaseInteractor: ItemsDatabaseInteractor): ItemsRepository {
        return ItemsRepository(ItemsNetworkInteractor, itemsDatabaseInteractor)
    }
}