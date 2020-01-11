package development.dreamcatcher.clothesshopapp.injection

import dagger.Component
import development.dreamcatcher.clothesshopapp.data.database.ItemsDatabaseInteractor
import development.dreamcatcher.clothesshopapp.data.network.ItemsNetworkInteractor
import development.dreamcatcher.clothesshopapp.features.detailedview.DetailedViewFragment
import development.dreamcatcher.clothesshopapp.features.detailedview.DetailedViewViewModel
import development.dreamcatcher.clothesshopapp.features.feed.FeedActivity
import development.dreamcatcher.clothesshopapp.features.feed.FeedViewModel
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