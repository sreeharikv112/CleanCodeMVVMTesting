package com.dev.ccodetest.uigeneric

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


/**
 * Common adapter for RecyclerViews.
 *
 */
abstract class GenericAdapter<T>: RecyclerView.Adapter<RecyclerView.ViewHolder> {

    var mListItems: List<T>
    //Can be used for filtering
    var mListItemsFiltered: List<T>
    var mListener: View.OnClickListener? = null

    init {
        mListItems = emptyList()
        mListItemsFiltered = emptyList()
    }

    constructor(){
        mListItems = emptyList()
        mListItemsFiltered = emptyList()

    }

    constructor(listener: View.OnClickListener){
        mListItems = emptyList()
        mListItemsFiltered = emptyList()
        mListener = listener
    }

    fun setItems(listItems: List<T>) {
        this.mListItems = listItems
        this.mListItemsFiltered = listItems
        notifyDataSetChanged()
    }

    fun refreshRecyclerView(){
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return getViewHolder(
            LayoutInflater.from(parent.context)
            .inflate(viewType, parent, false)
            , viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as Binder<T>).bind(mListItemsFiltered[position])
        holder.itemView.tag = mListItemsFiltered[position]

        if(mListener != null)
        holder.itemView.setOnClickListener(mListener)
    }

    override fun getItemCount(): Int {

        return mListItemsFiltered.size
    }

    override fun getItemViewType(position: Int): Int {

        return getLayoutId(position, mListItemsFiltered[position])
    }

    protected abstract fun getLayoutId(position: Int, obj: T): Int

    abstract fun getViewHolder(view: View, viewType: Int):RecyclerView.ViewHolder

    internal interface Binder<T> {
        fun bind(data: T)
    }
}