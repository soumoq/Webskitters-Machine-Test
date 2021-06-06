package com.webskitters.webskitters_machine_test.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.webskitters.webskitters_machine_test.R
import com.webskitters.webskitters_machine_test.util.loadImage
import kotlinx.android.synthetic.main.fragment_map.view.*

class MapFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_map, container, false)

        view.test_map.loadImage("https://homepages.cae.wisc.edu/~ece533/images/airplane.png")

        return view
    }
}