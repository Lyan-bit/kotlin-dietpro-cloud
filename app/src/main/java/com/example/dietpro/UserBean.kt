package com.example.dietpro

import android.content.Context
import java.lang.Exception

class UserBean(c: Context) {

    private var model: ModelFacade = ModelFacade.getInstance(c)

    private var userName = ""
    private var gender = ""
    private var heights = ""
    private var dheights = 0.0
    private var weights = ""
    private var dweights = 0.0
    private var activityLevel = ""
    private var age = ""
    private var dage = 0.0
    private var targetCalories = ""
    private var dtargetCalories = 0.0
    private var totalConsumedCalories = ""
    private var dtotalConsumedCalories = 0.0
    private var bmr = ""
    private var dbmr = 0.0
    private var mealId = ""

    private var errors = ArrayList<String>()

    fun setUserName(userNamex: String) {
	 userName = userNamex
    }
    
    fun setGender(genderx: String) {
	 gender = genderx
    }
    
    fun setHeights(heightsx: String) {
	 heights = heightsx
    }
    
    fun setWeights(weightsx: String) {
	 weights = weightsx
    }
    
    fun setActivityLevel(activityLevelx: String) {
	 activityLevel = activityLevelx
    }
    
    fun setAge(agex: String) {
	 age = agex
    }
    
    fun setTargetCalories(targetCaloriesx: String) {
	 targetCalories = targetCaloriesx
    }
    
    fun setTotalConsumedCalories(totalConsumedCaloriesx: String) {
	 totalConsumedCalories = totalConsumedCaloriesx
    }
    
    fun setBmr(bmrx: String) {
	 bmr = bmrx
    }
    

    fun setMealId(mealIdx : String) {
	mealId = mealIdx
    }

    fun resetData() {
	  userName = ""
	  gender = ""
	  heights = ""
	  weights = ""
	  activityLevel = ""
	  age = ""
	  targetCalories = ""
	  totalConsumedCalories = ""
	  bmr = ""
    }
    
    fun isCreateUserError(): Boolean {
	        
	 errors.clear()
	        
          if (userName != "") {
	//validate
}
	else {
	 	  errors.add("userName cannot be empty")
	}
          if (gender != "") {
	//validate
}
	else {
	 	  errors.add("gender cannot be empty")
	}
    try {
	  dheights = heights.toDouble()
	} catch (e: Exception) {
	  errors.add("heights is not a Double")
	}
    try {
	  dweights = weights.toDouble()
	} catch (e: Exception) {
	  errors.add("weights is not a Double")
	}
          if (activityLevel != "") {
	//validate
}
	else {
	 	  errors.add("activityLevel cannot be empty")
	}
    try {
	  dage = age.toDouble()
	} catch (e: Exception) {
	  errors.add("age is not a Double")
	}

	        return errors.isNotEmpty()
	    }
	    
	    fun createUser() {
	        model.createUser(UserVO(userName, gender, dheights, dweights, activityLevel, dage, dtargetCalories, dtotalConsumedCalories, dbmr))
	        resetData()
	    }

    fun isListUserError(): Boolean {
	        errors.clear()
	        return errors.isNotEmpty()
	    }


		fun isSearchUserIdError(allUserIds: List<String>): Boolean {
    	   errors.clear()
   	       if (!allUserIds.contains(userName)) {
    	       errors.add("The userName is not exist")
    	   }
           return errors.isNotEmpty()
    }

    fun errors(): String {
        return errors.toString()
    }

   fun isAddUsereatsMealError(): Boolean {
        errors.clear()
	if (mealId != "") {
	//ok
	}
	else
	   errors.add("mealId cannot be empty")
        return errors.isNotEmpty()
    }

    fun addUsereatsMeal() {
         model.addUsereatsMeal(userName, mealId)
         resetData()
    }
    
   fun isRemoveUsereatsMealError(): Boolean {
        errors.clear()
	if (userName != "") {
	//ok
	}
	else
	   errors.add("userName cannot be empty")
        return errors.isNotEmpty()
    }

    fun removeUsereatsMeal() {
         model.removeUsereatsMeal(userName, mealId)
         resetData()
    }
    


}

