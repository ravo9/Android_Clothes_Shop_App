package development.dreamcatcher.clothesshopapp.interactors

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import development.dreamcatcher.clothesshopapp.features.items.network.ApiClient
import development.dreamcatcher.clothesshopapp.features.items.network.ItemsNetworkInteractor
import development.dreamcatcher.clothesshopapp.features.items.network.CartItemGsonObject
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ItemsNetworkInteractorTest {

    private var itemsNetworkInteractor: ItemsNetworkInteractor? = null
    private var fakeItemGsonObject: CartItemGsonObject? = null

    @Mock
    private val apiClient: ApiClient? = null

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setupTest() {

        // Inject Mocks
        MockitoAnnotations.initMocks(this)

        // Initialize the Interactor
        itemsNetworkInteractor =
            ItemsNetworkInteractor(
                apiClient!!
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
    }
}
