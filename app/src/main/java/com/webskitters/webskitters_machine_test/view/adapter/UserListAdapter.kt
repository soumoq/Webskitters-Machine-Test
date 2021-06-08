package com.webskitters.webskitters_machine_test.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.roomexample.data.User
import com.webskitters.webskitters_machine_test.R
import com.webskitters.webskitters_machine_test.util.loadImage
import com.webskitters.webskitters_machine_test.view.fragment.AccountFragment
import kotlinx.android.synthetic.main.recycler_user_list.view.*


class UserListAdapter(context: Context, accountFragment: AccountFragment) : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {
    val context = context
    val accountFragment = accountFragment

    private var userList: ArrayList<User> = ArrayList()

    fun updateData(user: List<User>) {
        this.userList.clear()
        this.userList.addAll(user)
        notifyDataSetChanged()
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.recycler_user_list, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userList[position],accountFragment)
    }

    override fun getItemCount() = userList.size

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        @SuppressLint("SetTextI18n")
        fun bind(user: User, accountFragment: AccountFragment) {
            view.recycler_user_name.text = "Name : ${user.name}"
            view.recycler_user_email.text = "Email : ${user.email}"
            view.recycler_user_phone.text = "Phone : ${user.phone}"
            view.recycler_user_address.text = "Address : ${user.address}"
            view.user_profile_image.loadImage(user.image)

            view.custom_row_root.setOnClickListener {
                accountFragment.startUpdateSheet(user)
            }

            view.custom_row_root.setOnLongClickListener {
                accountFragment.startDeleteDialog(user)
                true
            }
        }
    }
}
