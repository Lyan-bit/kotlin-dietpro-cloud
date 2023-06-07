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
            return when (position) {
                 0 -> { 
                    CreateProfileUserFragment.newInstance(mContext) 
                }
                 1 -> { 
                    ListUserFragment.newInstance(mContext) 
                }
                 2 -> { 
                    CreateMealFragment.newInstance(mContext) 
                }
                 3 -> { 
                    ListMealFragment.newInstance(mContext) 
                }
                 4 -> { 
                    EditMealFragment.newInstance(mContext) 
                }
                 5 -> { 
                    DeleteMealFragment.newInstance(mContext) 
                }
                 6 -> { 
                    SearchMealByDatedatesFragment.newInstance(mContext) 
                }
                 7 -> { 
                    FindBmrFragment.newInstance(mContext) 
                }
                 8 -> { 
                    FindConsumedCaloriesFragment.newInstance(mContext) 
                }
                 9 -> { 
                    FindProgressFragment.newInstance(mContext) 
                }
                 10 -> { 
                    RecogniseFoodFragment.newInstance(mContext) 
                }
                else -> CreateProfileUserFragment.newInstance(mContext) 
             }

    override fun getPageTitle(position: Int): CharSequence {
        return TABTITLES[position]
    }

    override fun getCount(): Int {
        return 14
    }
}
