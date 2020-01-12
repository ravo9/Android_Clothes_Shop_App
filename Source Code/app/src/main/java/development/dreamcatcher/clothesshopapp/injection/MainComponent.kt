package development.dreamcatcher.clothesshopapp.injection

import dagger.Component
import development.dreamcatcher.clothesshopapp.features.items.database.ItemsDatabaseInteractor
import development.dreamcatcher.clothesshopapp.features.items.network.ItemsNetworkInteractor
import development.dreamcatcher.clothesshopapp.ui.detailedview.DetailedViewFragment
import development.dreamcatcher.clothesshopapp.ui.detailedview.DetailedViewViewModel
import development.dreamcatcher.clothesshopapp.ui.feed.FeedActivity
import development.dreamcatcher.clothesshopapp.ui.feed.FeedViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, FeedModule::class, ViewModelModule::class))
interface MainComponent {
    fun inject(feedActivity: FeedActivity)
    fun inject(detailedViewFragment: DetailedViewFragment)
    fun inject(feedViewModel: FeedViewModel)
    fun inject(detailedViewViewModel: DetailedViewViewModel)
    fun inject(ItemsNetworkInteractor: ItemsNetworkInteractor)
    fun inject(itemsDatabaseInteractor: ItemsDatabaseInteractor)
}