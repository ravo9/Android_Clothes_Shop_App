package development.dreamcatcher.clothesshopapp.ui.feed

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.recyclerview.widget.RecyclerView
import development.dreamcatcher.clothesshopapp.R
import development.dreamcatcher.clothesshopapp.features.items.database.ItemDatabaseEntity
import kotlinx.android.synthetic.main.grid_single_item.view.*

// Main adapter used for managing items grid within the main GridView (main feed listed)
class ItemsGridAdapter (val context: Context, val clickListener: (Int) -> Unit) : BaseAdapter() {

    private var itemsList: List<ItemDatabaseEntity> = ArrayList()

    fun setItems(items: List<ItemDatabaseEntity>) {
        this.itemsList = items
        notifyDataSetChanged()
    }

    fun changeSortingOrder() {
        this.itemsList = itemsList.reversed()
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return itemsList.size
    }

    override fun getItem(position: Int): Any {
        return itemsList[position]
    }

    override fun getItemId(position: Int): Long {
        return itemsList[position].id.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var view = convertView
        val holder: ViewHolder
        val inflater = LayoutInflater.from(parent?.context)

        // Inflate/ re-use the view
        if (view == null) {
            view = inflater.inflate(R.layout.grid_single_item, null)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            holder = view.tag as ViewHolder
        }

        // Prepare fetched features
        val name = itemsList[position].name

        // Set features within the holder
        holder.name.text = name

        // Set onClickListener
        holder.itemContainer.setOnClickListener{
            val itemId = itemsList[position].id
            clickListener(itemId)
        }

        // Load thumbnail
        /*val imageUrl = item.image
        val thumbnail = itemView.thumbnail
        try { Glide.with(context).load(imageUrl).into(thumbnail); }
        catch (e: Exception) {
            Log.e("Exception", e.message);
        }*/

        // In this exceptional case we use '!!' as we know that he view will be either inflated or re-used.
        return view!!
    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val name = view.name
        val itemContainer = view.single_item_container
    }
}