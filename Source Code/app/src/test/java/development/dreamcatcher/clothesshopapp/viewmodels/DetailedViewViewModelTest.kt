package development.dreamcatcher.clothesshopapp.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import development.dreamcatcher.clothesshopapp.features.items.database.CartItemDatabaseEntity
import development.dreamcatcher.clothesshopapp.features.items.CartRepository
import development.dreamcatcher.clothesshopapp.ui.detailedview.DetailedViewViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailedViewViewModelTest {

    private var viewModel: DetailedViewViewModel? = null
    private var fakeItemDatabaseEntity: CartItemDatabaseEntity? = null

    @Mock
    private val itemsRepository: CartRepository? = null

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setupTest() {

        // Inject Mocks
        MockitoAnnotations.initMocks(this)

        // Initialize the item
        viewModel = DetailedViewViewModel(itemsRepository!!)

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
    }

    @Test
    fun fetchItemByFeedViewModel() {

        // Prepare LiveData structure
        val itemEntityLiveData = MutableLiveData<CartItemDatabaseEntity>()
        itemEntityLiveData.setValue(fakeItemDatabaseEntity);

        // Prepare fake item name
        val fakeItemName = "fake/item/name"

        // Set testing conditions
        Mockito.`when`(itemsRepository?.getSingleSavedItemByName(fakeItemName)).thenReturn(itemEntityLiveData)

        // Perform the action
        val storedItems = viewModel?.getSingleSavedItemByName(fakeItemName)

        // Check results
        Assert.assertSame(itemEntityLiveData, storedItems);
    }
}