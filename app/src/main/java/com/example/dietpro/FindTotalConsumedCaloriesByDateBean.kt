package com.example.dietpro

import android.content.Context
import java.util.*

class FindTotalConsumedCaloriesByDateBean(c: Context) {
    private var model: ModelFacade = ModelFacade.getInstance(c)

    private var meals = ""
	private var instanceMeal: ArrayList<Meal>? = null
	
    private var user = ""
	private var instanceUser: User? = null
	
	private var dates = ""
	private var ddates = ""

    private var errors = ArrayList<String>()

    fun setMeals(mealsx: String) {
        meals = mealsx
    }
    
    fun setUser(userx: String) {
        user = userx
    }
    
    fun setDates(datesx: String) {
        dates = datesx
    }
    

    fun resetData() {
        meals = ""
        user = ""
        dates = ""
    }

    fun isFindTotalConsumedCaloriesByDateError(): Boolean {
        errors.clear()
        instanceMeal = model.listAllMeal()
        if (instanceMeal == null) {
            errors.add("meals must be a valid Meal id")
        }
        
        instanceUser = model.getUserByPK(user)
        if (instanceUser == null) {
            errors.add("user must be a valid User id")
        }
        
          if (dates != "") { ddates = dates }
else {
 	  errors.add("dates cannot be empty")
 	  }

        return errors.size > 0
    }

    fun errors(): String {
        return errors.toString()
    }

     fun findTotalConsumedCaloriesByDate (): Double {
        return model.findTotalConsumedCaloriesByDate(instanceMeal!!, instanceUser!!, ddates)
     }
}
