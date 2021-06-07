package com.webskitters.webskitters_machine_test.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.roomexample.data.User
import com.webskitters.webskitters_machine_test.R
import kotlinx.android.synthetic.main.recycler_user_list.view.*


class UserListAdapter(context: Context) : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {
    val context = context

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
        holder.bind(userList[position])
    }

    override fun getItemCount() = userList.size

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        @SuppressLint("SetTextI18n")
        fun bind(user: User) {
            view.recycler_user_name.text = user.name
            view.recycler_user_email.text = user.email
            view.recycler_user_phone.text = user.phone
            view.recycler_user_address.text = user.address

            view.custom_row_root.setOnClickListener {
                //(view.context as MainActivity).startUpdateSheet(user)
            }

            view.custom_row_root.setOnLongClickListener {
                //(view.context as MainActivity).startDeleteDialog(user)
                true
            }
        }
    }
}
