package com.example.dietpro

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PagerAdapter(private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        private val TABTITLES = arrayOf("+Meal", "ListMeal", "EditMeal", "DeleteMeal", "SearchMealByDatedates", "+ProfileUser", "ListUser", "FindTotalConsumedCaloriesByDate", "FindTargetCalories", "FindBMR", "CaloriesProgress", "AddUsereatsMeal", "RemoveUsereatsMeal", "ImageRecognition")
    }

    override fun getItem(position: Int): Fragment {
        // instantiate a fragment for the page.
        if (position == 0) {
            return CreateMealFragment.newInstance(mContext)    } 
        else if (position == 1) {
            return ListMealFragment.newInstance(mContext)    } 
        else if (position == 2) {
            return EditMealFragment.newInstance(mContext)    } 
        else if (position == 3) {
            return DeleteMealFragment.newInstance(mContext)    } 
        else if (position == 4) {
            return SearchMealByDatedatesFragment.newInstance(mContext)    } 
        else if (position == 5) {
            return CreateProfileUserFragment.newInstance(mContext)    } 
        else if (position == 6) {
            return ListUserFragment.newInstance(mContext)    } 
        else if (position == 7) {
            return FindTotalConsumedCaloriesByDateFragment.newInstance(mContext)    } 
        else if (position == 8) {
            return FindTargetCaloriesFragment.newInstance(mContext)    } 
        else if (position == 9) {
            return FindBMRFragment.newInstance(mContext)    } 
        else if (position == 10) {
            return CaloriesProgressFragment.newInstance(mContext)    } 
        else if (position == 11) {
            return AddUsereatsMealFragment.newInstance(mContext)    } 
        else if (position == 12) {
            return RemoveUsereatsMealFragment.newInstance(mContext)    } 
        else if (position == 13) {
            return ImageRecognitionFragment.newInstance(mContext)    } 
        return CreateMealFragment.newInstance(mContext) 
    }

    override fun getPageTitle(position: Int): CharSequence {
        return TABTITLES[position]
    }

    override fun getCount(): Int {
        return 14
    }
}
