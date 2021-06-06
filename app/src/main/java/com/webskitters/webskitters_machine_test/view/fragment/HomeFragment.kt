package com.webskitters.webskitters_machine_test.view.fragment

import ImageModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.webskitters.webskitters_machine_test.R
import com.webskitters.webskitters_machine_test.util.toast
import com.webskitters.webskitters_machine_test.viewModel.ImageViewModel

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_home, container, false)

        //        allAudioViewModel = ViewModelProvider(this).get(AllAudioViewModel :: class.java)
        val imageModel = ViewModelProvider(this).get(ImageViewModel::class.java)
        imageModel.imageList.observe(viewLifecycleOwner, Observer {
            
        })
        imageModel.getImage(requireContext())

        return view;
    }

}