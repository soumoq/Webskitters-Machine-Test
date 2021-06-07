package com.webskitters.webskitters_machine_test.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.target.Target
import com.webskitters.webskitters_machine_test.R
import com.webskitters.webskitters_machine_test.util.loadImage
import kotlinx.android.synthetic.main.fragment_map.view.*
import kotlinx.android.synthetic.main.recycler_image_list.view.*


class MapFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_map, container, false)


        val url = GlideUrl(
            "https://via.placeholder.com/600/54176f", LazyHeaders.Builder()
                .addHeader("User-Agent", "your-user-agent")
                .build()
        )
        Glide.with(view.context)
            .load(url)
            .error(R.drawable.ic_baseline_error_outline_24)
            .into(view.test_map)

        return view
    }
}