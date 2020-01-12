package development.dreamcatcher.clothesshopapp.items

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import development.dreamcatcher.clothesshopapp.features.items.database.ItemsDatabaseInteractor
import development.dreamcatcher.clothesshopapp.features.items.database.CartItemDatabaseEntity
import development.dreamcatcher.clothesshopapp.features.items.network.ItemsNetworkInteractor
import development.dreamcatcher.clothesshopapp.features.items.CartRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ItemsRepositoryTest {

    private var itemsRepository: CartRepository? = null
    private var fakeItemDatabaseEntity: CartItemDatabaseEntity? = null
    private var fakeItemEntitiesList = ArrayList<CartItemDatabaseEntity>()

    @Mock
    private val itemsDatabaseInteractor: ItemsDatabaseInteractor? = null

    @Mock
    private val itemsNetworkInteractor: ItemsNetworkInteractor? = null

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setupTest() {

        // Inject Mocks
        MockitoAnnotations.initMocks(this)

        // Initialize the repository
        itemsRepository = CartRepository(
            itemsNetworkInteractor!!,
            itemsDatabaseInteractor!!
        )

        // Prepare fake features
        val name = "fake/item/name"
        val capital = "fake/item/capital"
        val population = 43234234

        // Prepare fake Item Entity (DB object)
        fakeItemDatabaseEntity =
            CartItemDatabaseEntity(
                name,
                capital,
                population,
                null
            )

        // Prepare fake Items Entities List
        fakeItemEntitiesList.add(fakeItemDatabaseEntity!!)
    }

    @Test
    fun fetchAllItemsByItemsRepository() {

        // Prepare LiveData structure
        val itemEntityLiveData = MutableLiveData<List<CartItemDatabaseEntity>>()
        itemEntityLiveData.setValue(fakeItemEntitiesList);

        // Set testing conditions
        Mockito.`when`(itemsDatabaseInteractor?.getAllItems()).thenReturn(itemEntityLiveData)

        // Perform the action
        val storedItems = itemsRepository?.getAllItems(false)

        // Check results
        Assert.assertSame(itemEntityLiveData, storedItems);
    }

    @Test
    fun fetchItemByItemsRepository() {

        // Prepare LiveData structure
        val itemEntityLiveData = MutableLiveData<CartItemDatabaseEntity>()
        itemEntityLiveData.setValue(fakeItemDatabaseEntity);

        // Prepare fake item name
        val fakeItemName = "fake/item/name"

        // Set testing conditions
        Mockito.`when`(itemsDatabaseInteractor?.getSingleSavedItemByName(fakeItemName))
            .thenReturn(itemEntityLiveData)

        // Perform the action
        val storedItem = itemsRepository?.getSingleSavedItemByName(fakeItemName)

        // Check results
        Assert.assertSame(itemEntityLiveData, storedItem);
    }
}