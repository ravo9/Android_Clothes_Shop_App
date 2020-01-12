package development.dreamcatcher.clothesshopapp.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import development.dreamcatcher.clothesshopapp.features.items.database.CartItemDatabaseEntity
import development.dreamcatcher.clothesshopapp.features.items.CartRepository
import development.dreamcatcher.clothesshopapp.ui.feed.FeedViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class FeedViewModelTest {

    private var viewModel: FeedViewModel? = null
    private var fakeItemDatabaseEntity: CartItemDatabaseEntity? = null
    private var fakeItemEntitiesList = ArrayList<CartItemDatabaseEntity>()

    @Mock
    private val itemsRepository: CartRepository? = null

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setupTest() {

        // Inject Mocks
        MockitoAnnotations.initMocks(this)

        // Initialize the item
        viewModel = FeedViewModel(itemsRepository!!)

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
    fun fetchAllItemsByFeedViewModel() {

        // Prepare LiveData structure
        val itemEntityLiveData = MutableLiveData<List<CartItemDatabaseEntity>>()
        itemEntityLiveData.setValue(fakeItemEntitiesList)

        // Set testing conditions
        Mockito.`when`(itemsRepository?.getAllItems(false)).thenReturn(itemEntityLiveData)

        // Perform the action
        val storedItems = viewModel?.getAllItems(false)

        // Check results
        Assert.assertSame(itemEntityLiveData, storedItems);
    }
}