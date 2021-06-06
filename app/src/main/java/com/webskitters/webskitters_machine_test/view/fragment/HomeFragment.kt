package com.webskitters.webskitters_machine_test.view.fragment

import ImageModel
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.webskitters.webskitters_machine_test.R
import com.webskitters.webskitters_machine_test.util.toast
import com.webskitters.webskitters_machine_test.view.activity.HomeActivity
import com.webskitters.webskitters_machine_test.view.adapter.ImageListAdapter
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val imageListAdapter = ImageListAdapter(context!!, this)
        view.home_image_list.layoutManager = GridLayoutManager(context, 3)
        view.home_image_list.itemAnimator = DefaultItemAnimator()
        view.home_image_list.adapter = imageListAdapter


        val imageViewModel = (context!! as HomeActivity).getImageViewModel()
        imageViewModel?.imageList?.observe(viewLifecycleOwner, Observer { it_list ->
            if (it_list.isNotEmpty()) {
                imageListAdapter.updateData(it_list)
                imageListAdapter.notifyDataSetChanged()

                view.home_search_edit_text.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                        val length: Int = view.home_search_edit_text.length()
                        if (length>=3){
                            val searchRes = ArrayList<ImageModel>()
                            if (view.home_search_edit_text.text.toString().isNotEmpty()) {
                                for (i in 0 until it_list.size) {
                                    if (it_list[i].title.contains(view.home_search_edit_text.text.toString())) {
                                        searchRes.add(it_list[i])
                                    }
                                }
                                imageListAdapter.updateData(searchRes)
                                imageListAdapter.notifyDataSetChanged()
                            }
                        }else if(length<3){
                            imageListAdapter.updateData(it_list)
                            imageListAdapter.notifyDataSetChanged()
                        }
                    }

                    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    }

                    override fun afterTextChanged(s: Editable) {}
                })


            } else {
                context!!.toast("Item not found")
            }
        })



        return view;
    }


    fun selectRecycler(int: Int) {

    }


}