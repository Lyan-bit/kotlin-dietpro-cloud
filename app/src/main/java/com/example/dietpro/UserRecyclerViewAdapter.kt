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

class UserRecyclerViewAdapter (items: List<UserVO>, listener: ListUserFragment.OnListUserFragmentInteractionListener)
    : RecyclerView.Adapter<UserRecyclerViewAdapter.UserViewHolder>() {

    private var mValues: List<UserVO> = items
    private var mListener: ListUserFragment.OnListUserFragmentInteractionListener = listener
	
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerviewlistuser_item, parent, false)
        return UserViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.mItem = mValues[position]
        holder.listUserUserNameView.text = " " + mValues[position].getUserName() + " "
        holder.listUserGenderView.text = " " + mValues[position].getGender() + " "
        holder.listUserHeightsView.text = " " + mValues[position].getHeights() + " "
        holder.listUserWeightsView.text = " " + mValues[position].getWeights() + " "
        holder.listUserActivityLevelView.text = " " + mValues[position].getActivityLevel() + " "
        holder.listUserAgeView.text = " " + mValues[position].getAge() + " "
        holder.listUserTargetCaloriesView.text = " " + mValues[position].getTargetCalories() + " "
        holder.listUserTotalConsumedCaloriesView.text = " " + mValues[position].getTotalConsumedCalories() + " "
        holder.listUserBmrView.text = " " + mValues[position].getBmr() + " "

        holder.mView.setOnClickListener { mListener.onListUserFragmentInteraction(holder.mItem) }
    }
    
    override fun getItemCount(): Int {
        return mValues.size
    }

    inner class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var mView: View
        var listUserUserNameView: TextView
        var listUserGenderView: TextView
        var listUserHeightsView: TextView
        var listUserWeightsView: TextView
        var listUserActivityLevelView: TextView
        var listUserAgeView: TextView
        var listUserTargetCaloriesView: TextView
        var listUserTotalConsumedCaloriesView: TextView
        var listUserBmrView: TextView
        lateinit var mItem: UserVO

        init {
            mView = view
            listUserUserNameView = view.findViewById(R.id.listUserUserNameView)
            listUserGenderView = view.findViewById(R.id.listUserGenderView)
            listUserHeightsView = view.findViewById(R.id.listUserHeightsView)
            listUserWeightsView = view.findViewById(R.id.listUserWeightsView)
            listUserActivityLevelView = view.findViewById(R.id.listUserActivityLevelView)
            listUserAgeView = view.findViewById(R.id.listUserAgeView)
            listUserTargetCaloriesView = view.findViewById(R.id.listUserTargetCaloriesView)
            listUserTotalConsumedCaloriesView = view.findViewById(R.id.listUserTotalConsumedCaloriesView)
            listUserBmrView = view.findViewById(R.id.listUserBmrView)
        }

        override fun toString(): String {
            return super.toString() + " " + mItem
        }

    }
}
