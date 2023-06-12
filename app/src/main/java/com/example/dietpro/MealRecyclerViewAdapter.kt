package com.example.dietpro

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64

class MealRecyclerViewAdapter (items: List<MealVO>, listener: ListMealFragment.OnListMealFragmentInteractionListener)
    : RecyclerView.Adapter<MealRecyclerViewAdapter.MealViewHolder>() {

    private var mValues: List<MealVO> = items
    private var mListener: ListMealFragment.OnListMealFragmentInteractionListener = listener
	
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerviewsearchmealbydate_item, parent, false)
        return MealViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.mItem = mValues[position]
        holder.searchMealByDateMealIdView.text = " " + mValues[position].mealId + " "
        holder.searchMealByDateMealNameView.text = " " + mValues[position].mealName + " "
        holder.searchMealByDateCaloriesView.text = " " + mValues[position].calories + " "
        holder.searchMealByDateDatesView.text = " " + mValues[position].dates + " "
        val dimage: Bitmap? = try {
            // convert base64 to bitmap android
            val decodedString: ByteArray = Base64.decode(mValues[position].images, Base64.DEFAULT)
            val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
            decodedByte
        }
        catch (e: Exception) {
            e.message
            null
        }
        holder.searchMealByDateImagesView.setImageBitmap(dimage)
        holder.searchMealByDateAnalysisView.text = " " + mValues[position].analysis + " "
        holder.searchMealByDateUserNameView.text = " " + mValues[position].userName + " "

        holder.mView.setOnClickListener { mListener.onListMealFragmentInteraction(holder.mItem) }
    }
    
    override fun getItemCount(): Int {
        return mValues.size
    }

    inner class MealViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var mView: View
        var searchMealByDateMealIdView: TextView
        var searchMealByDateMealNameView: TextView
        var searchMealByDateCaloriesView: TextView
        var searchMealByDateDatesView: TextView
        var searchMealByDateImagesView: ImageView
        var searchMealByDateAnalysisView: TextView
        var searchMealByDateUserNameView: TextView
        lateinit var mItem: MealVO

        init {
            mView = view
            searchMealByDateMealIdView = view.findViewById(R.id.searchMealByDateMealIdView)
            searchMealByDateMealNameView = view.findViewById(R.id.searchMealByDateMealNameView)
            searchMealByDateCaloriesView = view.findViewById(R.id.searchMealByDateCaloriesView)
            searchMealByDateDatesView = view.findViewById(R.id.searchMealByDateDatesView)
            searchMealByDateImagesView = view.findViewById(R.id.searchMealByDateImagesView)
            searchMealByDateAnalysisView = view.findViewById(R.id.searchMealByDateAnalysisView)
            searchMealByDateUserNameView = view.findViewById(R.id.searchMealByDateUserNameView)
        }

        override fun toString(): String {
            return super.toString() + " " + mItem
        }

    }
}
