package com.webskitters.webskitters_machine_test.view.adapter

import ImageModel
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.webskitters.webskitters_machine_test.R
import com.webskitters.webskitters_machine_test.util.loadImage
import com.webskitters.webskitters_machine_test.view.fragment.HomeFragment
import kotlinx.android.synthetic.main.recycler_image_list.view.*


class ImageListAdapter(context: Context, homeFragment: HomeFragment) : RecyclerView.Adapter<ImageListAdapter.ViewHolder>() {
    val context = context
    val homeFragment = homeFragment

    private var images: ArrayList<ImageModel> = ArrayList()
    private var selectedPos : Int = RecyclerView.NO_POSITION

    fun updateData(image: List<ImageModel>) {
        this.images.clear()
        this.images.addAll(image)
        notifyDataSetChanged()
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.recycler_image_list, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(images[position],position,homeFragment)
    }

    override fun getItemCount() = images.size

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        @SuppressLint("SetTextI18n")
        fun bind(imageInfo: ImageModel, position: Int, homeFragment: HomeFragment) {
            view.image_list_title.text = imageInfo.title
            view.image_list_thumbnail.loadImage(imageInfo.url)

            view.recycler_image_root.setOnClickListener {
                //(it.context as HomeActivity).selectRecycler(position)
                homeFragment.selectRecycler(position)
            }

        }
    }
}