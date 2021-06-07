package com.webskitters.webskitters_machine_test.view.fragment

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.roomexample.InputBottomSheet
import com.example.roomexample.UpdateBottomSheet
import com.example.roomexample.data.User
import com.example.roomexample.data.UserViewModel
import com.webskitters.webskitters_machine_test.R
import com.webskitters.webskitters_machine_test.view.adapter.UserListAdapter
import kotlinx.android.synthetic.main.fragment_account.view.*

class AccountFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_account, container, false)


        view.fab.setOnClickListener {
            val inputBottomSheet = InputBottomSheet()
            inputBottomSheet.show(requireActivity().supportFragmentManager, "Fragment")
        }

        val listAdapter = UserListAdapter(context!!)
        view.user_list.adapter = listAdapter

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer {
            listAdapter.updateData(it)
        })


        return view
    }


    fun startUpdateSheet(user: User) {
        val updateBottomSheet = UpdateBottomSheet(user)
        updateBottomSheet.show(requireActivity().supportFragmentManager, "Fragment")
    }

    fun startDeleteDialog(user: User) {
        val alert = activity?.let { it1 -> AlertDialog.Builder(it1) }
        alert?.setTitle("Are you sure you want to delete the User")
        alert?.setPositiveButton("Ok", DialogInterface.OnClickListener { dialog, which ->
            mUserViewModel.deleteUser(user)
        })
        alert?.setNegativeButton("cancel", DialogInterface.OnClickListener { dialog, which ->
        })
        alert?.show()
    }

}