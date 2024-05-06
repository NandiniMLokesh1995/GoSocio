package com.example.gosocio.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.gosocio.R
import com.example.gosocio.entities.Items

class RecyclerItemsAdapter(val items: List<Items>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_1 =0
    private val TYPE_2 =1

    override fun getItemViewType(position: Int): Int {

        return when(items[position].type){
            "text" -> TYPE_1
            "image" -> TYPE_2
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            TYPE_1->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_type_one, parent, false)
                TypeOneViewHolder(view)
            }
            TYPE_2->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_type_two, parent, false)
                TypeTwoViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(holder.itemViewType){
            TYPE_1 -> {
                val typeOneViewHolder = holder as TypeOneViewHolder
                val title = items[position].title
                val name = items[position].name
                typeOneViewHolder.bind(title,name)
            }
            TYPE_2 -> {
                val typeTwoViewHolder = holder as TypeTwoViewHolder
                val title = items[position].title
                val name = items[position].name
                val thumbnail = items[position].thumbnail
                typeTwoViewHolder.bind(title, name, thumbnail)
            }
        }
    }

    inner class TypeOneViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val titleView: TextView = itemView.findViewById(R.id.txt_title)
        private val nameView: TextView = itemView.findViewById(R.id.txt_name)

        fun bind(title: String, name: String) {
            titleView.text = title
            nameView.text = name
        }
    }
    inner class TypeTwoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val titleView: TextView = itemView.findViewById(R.id.txt_title)
        private val nameView: TextView = itemView.findViewById(R.id.txt_name)
        private val imgView: ImageView = itemView.findViewById(R.id.img_pic)


        fun bind(title: String, name: String, thumbnail :String) {
            imgView.load(thumbnail)
            titleView.text = title
            nameView.text = name
        }
    }

}

