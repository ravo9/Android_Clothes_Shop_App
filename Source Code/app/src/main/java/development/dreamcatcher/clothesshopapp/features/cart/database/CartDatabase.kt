package development.dreamcatcher.clothesshopapp.features.cart.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [(CartItemDatabaseEntity::class)], version = 1)
abstract class CartItemsDatabase : RoomDatabase() {
    abstract fun getCartItemsDao(): CartItemsDao
}
