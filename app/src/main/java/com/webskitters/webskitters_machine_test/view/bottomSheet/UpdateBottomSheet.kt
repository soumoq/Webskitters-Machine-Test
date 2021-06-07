package com.example.roomexample

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.roomexample.data.User
import com.example.roomexample.data.UserViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.webskitters.webskitters_machine_test.R
import kotlinx.android.synthetic.main.buttom_sheet.view.*

class UpdateBottomSheet(user: User) : BottomSheetDialogFragment() {

    var user_name: EditText? = null
    var user_email: EditText? = null
    var user_phone: EditText? = null
    var user_address: EditText? = null

    val imageUrl = "https://homepages.cae.wisc.edu/~ece533/images/airplane.png"

    var user = user

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.buttom_sheet, container, false)

        view.bottom_sheet_title.text = "Update"

        user_name = view.user_name
        user_email = view.user_email
        user_phone = view.user_phone
        user_address = view.user_address

        view.user_name.setText(user.name)
        view.user_email.setText(user.email)
        view.user_phone.setText(user.phone)
        view.user_address.setText(user.address)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.insert.text = "Update"
        view.insert.setOnClickListener {
            updateDataToDatabase()
        }

        return view;

    }

    private fun updateDataToDatabase() {
        val userName = user_name?.text.toString()
        val userEmail = user_email?.text.toString()
        val userPhone = user_phone?.text.toString()
        val userAddress = user_address?.text.toString()

        if (inputCheck(userName, userEmail, userPhone, userAddress)) {
            // Create User Object
            val updateUser = User(user.id, userName, userEmail, userPhone, userAddress, imageUrl)
            mUserViewModel.updateUser(updateUser)
            Toast.makeText(context, "Successfully Updated!", Toast.LENGTH_LONG).show()
            dismiss()
        } else {
            Toast.makeText(context, "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(
        name: String,
        email: String,
        phone: String,
        userAddress: String
    ): Boolean {
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(email) && TextUtils.isEmpty(phone) && TextUtils.isEmpty(
            userAddress
        ))
    }

}