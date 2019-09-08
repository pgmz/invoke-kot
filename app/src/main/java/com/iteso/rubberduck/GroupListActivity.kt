package com.iteso.rubberduck

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iteso.rubberduck.beans.GroupListItem

class GroupListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_list)

        viewManager = LinearLayoutManager(this)

        //TODO: get images as web resources...
        val myDataset: Array<GroupListItem> = arrayOf(
            GroupListItem("Group1", R.drawable.hiking_group),
            GroupListItem("Group2", R.drawable.music_group),
            GroupListItem("Group3", R.drawable.pizza_group))

        viewAdapter = GroupListAdapter(myDataset)

        recyclerView = findViewById<RecyclerView>(R.id.group_list_activity_recycler_view).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }
    }

    class GroupListAdapter(private val groupListDataset: Array<GroupListItem>) :
        RecyclerView.Adapter<GroupListAdapter.GroupListViewHolder>() {

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder.
        // Each data item is just a string in this case that is shown in a TextView.
        class GroupListViewHolder(val groupItemView: View) : RecyclerView.ViewHolder(groupItemView)


        // Create new views (invoked by the layout manager)
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): GroupListAdapter.GroupListViewHolder {
            // create a new view
            val groupListItemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_group_list_item, parent, false) as View
            // set the view's size, margins, paddings and layout parameters

            return GroupListViewHolder(groupListItemView)
        }

        // Replace the contents of a view (invoked by the layout manager)
        override fun onBindViewHolder(holder: GroupListViewHolder, position: Int) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element

            holder.groupItemView
                .findViewById<ImageView>(R.id.activity_group_list_item_image)
                .setImageResource(groupListDataset[position].groupImage)

            holder.groupItemView
                .findViewById<TextView>(R.id.activity_group_list_item_text)
                .text = groupListDataset[position].groupName
        }

        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount() = groupListDataset.size
    }


}
