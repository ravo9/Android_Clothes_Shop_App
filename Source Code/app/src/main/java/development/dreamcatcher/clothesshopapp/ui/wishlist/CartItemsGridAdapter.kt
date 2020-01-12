package development.dreamcatcher.clothesshopapp.ui.wishlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.recyclerview.widget.RecyclerView
import development.dreamcatcher.clothesshopapp.R
import development.dreamcatcher.clothesshopapp.features.wishlist.database.WishlistItemDatabaseEntity
import kotlinx.android.synthetic.main.grid_single_item_wishlist.view.*

// Main adapter used for managing items grid within the main GridView (main feed listed)
class WishlistItemsGridAdapter (val context: Context,
                            val removeItemClickListener: (Int) -> Unit) : BaseAdapter() {

    private var itemsList: List<WishlistItemDatabaseEntity> = ArrayList()

    fun setItems(items: List<WishlistItemDatabaseEntity>) {
        this.itemsList = items
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return itemsList.size
    }

    override fun getItem(position: Int): Any {
        return itemsList[position]
    }

    override fun getItemId(position: Int): Long {
        return itemsList[position].productId.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var view = convertView
        val holder: ViewHolder
        val inflater = LayoutInflater.from(parent?.context)

        // Inflate/ re-use the view
        if (view == null) {
            view = inflater.inflate(R.layout.grid_single_item_wishlist, null)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            holder = view.tag as ViewHolder
        }

        // Prepare fetched features
        //val name = itemsList[position].name

        // Set features within the holder
        //holder.name.text = name

        // Set onClickListeners
        holder.btnRemove.setOnClickListener{
            val itemId = itemsList[position].productId
            removeItemClickListener(itemId)
        }

        // In this exceptional case we use '!!' as we know that he view will be either inflated or re-used.
        return view!!
    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val name = view.name
        val btnRemove = view.btn_remove
        val itemContainer = view.single_item_container
    }
}