package development.dreamcatcher.clothesshopapp.interactors

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import development.dreamcatcher.clothesshopapp.features.items.database.CartDao
import development.dreamcatcher.clothesshopapp.features.items.database.CartDatabase
import development.dreamcatcher.clothesshopapp.features.items.database.ItemsDatabaseInteractor
import development.dreamcatcher.clothesshopapp.features.items.database.CartItemDatabaseEntity
import development.dreamcatcher.clothesshopapp.features.items.network.CartItemGsonObject
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ItemsDatabaseInteractorTest {

    private var itemsDatabaseInteractor: ItemsDatabaseInteractor? = null
    private var fakeItemGsonObject: CartItemGsonObject? = null
    private var fakeItemDatabaseEntity: CartItemDatabaseEntity? = null

    @Mock
    private val itemsDatabase: CartDatabase? = null

    @Mock
    private val cartDao: CartDao? = null

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setupTest() {

        // Inject Mocks
        MockitoAnnotations.initMocks(this)

        // Initialize the Interactor
        itemsDatabaseInteractor =
            ItemsDatabaseInteractor(
                itemsDatabase!!
            )

        // Prepare fake features
        val name = "fake/item/name"
        val capital = "fake/item/capital"
        val population = 43234234

        // Prepare fake Gson (API) object
        fakeItemGsonObject =
            CartItemGsonObject(
                name,
                capital,
                population,
                null
            )

        // Prepare fake Item Entity (DB object)
        fakeItemDatabaseEntity =
            CartItemDatabaseEntity(
                name,
                capital,
                population,
                null
            )
    }

    @Test
    fun saveItemByDatabaseInteractor() {

        // Perform the action
        val resultStatus = itemsDatabaseInteractor!!.addNewItem(fakeItemGsonObject).value

        // Check results
        Assert.assertTrue(resultStatus!!)
    }

    @Test
    fun fetchItemByDatabaseInteractor() {

        // Prepare LiveData structure
        val itemEntityLiveData = MutableLiveData<CartItemDatabaseEntity>()
        itemEntityLiveData.setValue(fakeItemDatabaseEntity);

        // Set testing conditions
        Mockito.`when`(itemsDatabase?.getItemsDao()).thenReturn(cartDao)
        Mockito.`when`(cartDao?.getSingleSavedItemByName(anyString())).thenReturn(itemEntityLiveData)

        // Perform the action
        val storedItem = itemsDatabaseInteractor?.getSingleSavedItemByName("fake/item/name")

        // Check results
        Assert.assertSame(itemEntityLiveData, storedItem);
    }
}