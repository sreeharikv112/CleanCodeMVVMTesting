package com.dev.ccodetest.screens.login

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dev.ccodetest.R
import com.dev.ccodetest.models.login.AllPeopleResult

/**
 * Adapter for Login screen recyclerview.
 */
class LoginRecyclerViewAdapter(val context: Context, list: ArrayList<AllPeopleResult>): RecyclerView.Adapter<LoginRecyclerViewAdapter.LoginFragViewHolder>() {

    var mItemList = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoginFragViewHolder {
        return LoginFragViewHolder(LayoutInflater.from(context).inflate(R.layout.landing_list_view_item,parent,false))
    }

    fun updateListItems(updatedList: ArrayList<AllPeopleResult>){
        mItemList.clear()
        mItemList = updatedList
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return mItemList.size
    }

    override fun onBindViewHolder(holder: LoginFragViewHolder, position: Int) {
        val model : AllPeopleResult = mItemList[position]
        holder.personName.text = model.name
        holder.dob.text = model.birth_year
    }

    class LoginFragViewHolder(item: View): RecyclerView.ViewHolder(item){
        val personName : TextView = item.findViewById(R.id.personName)
        val dob : TextView = item.findViewById(R.id.dateOfBirth)
    }
}