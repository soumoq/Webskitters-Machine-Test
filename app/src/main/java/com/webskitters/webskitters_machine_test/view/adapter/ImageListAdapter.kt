package com.webskitters.webskitters_machine_test.view.adapter

import ImageModel
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.webskitters.webskitters_machine_test.R
import com.webskitters.webskitters_machine_test.util.loadImage
import com.webskitters.webskitters_machine_test.util.toast
import com.webskitters.webskitters_machine_test.view.fragment.HomeFragment
import kotlinx.android.synthetic.main.fragment_map.view.*
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
            //view.image_list_thumbnail.loadImage(imageInfo.url)
            view.recycler_image_root.setOnClickListener {
                //(it.context as HomeActivity).selectRecycler(position)
                view.context.toast("S")
                view.recycler_image_root.setBackgroundResource(R.drawable.select_back_ground)
            }

            //glide
            val url = GlideUrl(
                imageInfo.thumbnailUrl, LazyHeaders.Builder()
                    .addHeader("User-Agent", "your-user-agent")
                    .build()
            )
            Glide.with(view.context)
                .load(url)
                .error(R.drawable.ic_baseline_error_outline_24)
                .into(view.image_list_thumbnail)

        }
    }
}
