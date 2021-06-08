package com.example.roomexample

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
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
import com.webskitters.webskitters_machine_test.util.ImpFun
import com.webskitters.webskitters_machine_test.util.loadImage
import com.webskitters.webskitters_machine_test.util.toast
import kotlinx.android.synthetic.main.buttom_sheet.view.*
import kotlinx.android.synthetic.main.recycler_user_list.view.*

class UpdateBottomSheet(user: User) : BottomSheetDialogFragment() {

    var user_name: EditText? = null
    var user_email: EditText? = null
    var user_phone: EditText? = null
    var user_address: EditText? = null

    var imageUrl = "https://homepages.cae.wisc.edu/~ece533/images/airplane.png"

    var user = user

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.buttom_sheet, container, false)

        view.bottom_sheet_title.text = "Update User Details"

        user_name = view.user_name
        user_email = view.user_email
        user_phone = view.user_phone
        user_address = view.user_address

        view.user_name.setText(user.name)
        view.user_email.setText(user.email)
        view.user_phone.setText(user.phone)
        view.user_address.setText(user.address)
        view.user_profile_image_select.loadImage(user.image)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.insert.text = "Update"
        view.insert.setOnClickListener {
            updateDataToDatabase()
        }

        view.user_profile_image_select.setOnClickListener {
            pickImage()
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

    fun pickImage() {
        val i = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        // ******** code for crop image
        // ******** code for crop image
        i.putExtra("crop", "true")
        i.putExtra("aspectX", 100)
        i.putExtra("aspectY", 100)
        i.putExtra("outputX", 256)
        i.putExtra("outputY", 356)

        try {
            i.putExtra("return-data", true)
            startActivityForResult(
                Intent.createChooser(i, "Select Picture"), 0
            )
        } catch (ex: ActivityNotFoundException) {
            ex.printStackTrace()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
            try {
                val bundle = data?.extras
                val bitmap = bundle!!.getParcelable<Bitmap>("data")
                imageUrl = ImpFun.getImageUri(requireContext(),bitmap!!).toString()
                view?.user_profile_image_select!!.loadImage(imageUrl)


            } catch (e: Exception) {
                e.printStackTrace()
                context!!.toast(e.message.toString())
            }
        }else{
            context!!.toast("Image selector failed")

        }

    }

}