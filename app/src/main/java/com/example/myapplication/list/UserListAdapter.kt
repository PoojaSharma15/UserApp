package com.example.myapplication.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.User


class UserListAdapter(private val listener: OnItemClickListener): ListAdapter<User, UserListAdapter.UserViewHolder>(UserComparator()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.user_list_item, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }


   inner class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private  val fname:TextView = itemView.findViewById(R.id.item_fnameView)
        private  val lname:TextView= itemView.findViewById(R.id.item_lnameView)

        fun bind(user: User) {
            fname.text = user.firstName
            lname.text = user.lastName

        }

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?){
            val position = adapterPosition
            val user = getItem(position)
            listener.OnItemClick(user)
        }
   }

    interface OnItemClickListener{
        fun OnItemClick(user: User)
    }




    class UserComparator : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {

            return oldItem.equals(newItem)
        }
    }
}


