package com.webskitters.webskitters_machine_test.view.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.webskitters.webskitters_machine_test.R
import com.webskitters.webskitters_machine_test.model.ImageModel
import com.webskitters.webskitters_machine_test.util.toast
import com.webskitters.webskitters_machine_test.view.adapter.ImageListAdapter
import com.webskitters.webskitters_machine_test.viewModel.ImageViewModel
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {

    var home_item_count: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)



        val imageViewModel = ViewModelProvider(this).get(ImageViewModel::class.java)
        imageViewModel.getImage(context!!)

        val imageListAdapter = ImageListAdapter(context!!, this)
        view.home_image_list.layoutManager = GridLayoutManager(context, 3)
        view.home_image_list.itemAnimator = DefaultItemAnimator()
        view.home_image_list.adapter = imageListAdapter

        imageViewModel.imageList.observe(viewLifecycleOwner, Observer { it_list ->
            if (it_list.isNotEmpty()) {
                imageListAdapter.updateData(it_list)
                imageListAdapter.notifyDataSetChanged()

                view.home_search_edit_text.addTextChangedListener(object : TextWatcher { override fun beforeTextChanged(
                    s: CharSequence, start: Int, count: Int, after: Int) {
                    val length: Int = view.home_search_edit_text.length()
                    if (length >= 3) {
                        val searchRes = ArrayList<ImageModel>()
                        if (view.home_search_edit_text.text.toString().isNotEmpty()) {
                            for (i in 0 until it_list.size) {
                                if (it_list[i].title.toLowerCase().contains(
                                        view.home_search_edit_text.text.toString().toLowerCase()
                                    )
                                ) {
                                    searchRes.add(it_list[i])
                                }
                            }
                            imageListAdapter.updateData(searchRes)
                            imageListAdapter.notifyDataSetChanged()
                        }
                    } else if (length < 3) {
                        imageListAdapter.updateData(it_list)
                        imageListAdapter.notifyDataSetChanged()
                    }
                }

                    override fun onTextChanged(
                        s: CharSequence,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                    }

                    override fun afterTextChanged(s: Editable) {}
                })


            } else {
                context!!.toast("Item not found")
            }
        })


        return view;
    }

    var count = 0
    fun selectRecycler(check: Boolean) {
        if (check) {
            --count;
            home_item_count?.text = count.toString()
        } else {
            ++count
            home_item_count?.text = count.toString()
        }

        if (count == 0) {
            home_item_count?.visibility = View.INVISIBLE
        } else {
            home_item_count?.visibility = View.VISIBLE
        }

    }

}