package com.example.dietpro

import android.content.Context
import java.lang.Exception
import java.util.regex.Pattern

class MealBean(c: Context) {

    private var model: ModelFacade = ModelFacade.getInstance(c)

    private var mealId = ""
    private var mealName = ""
    private var calories = ""
    private var dcalories = 0.0
    private var dates = ""
    private var images = ""
    private var analysis = ""
    private var userName = ""

    private var errors = ArrayList<String>()

    fun setMealId(mealIdx: String) {
	 mealId = mealIdx
    }
    
    fun setMealName(mealNamex: String) {
	 mealName = mealNamex
    }
    
    fun setCalories(caloriesx: String) {
	 calories = caloriesx
    }
    
    fun setDates(datesx: String) {
	 dates = datesx
    }
    
    fun setImages(imagesx: String) {
	 images = imagesx
    }
    
    fun setAnalysis(analysisx: String) {
	 analysis = analysisx
    }
    
    fun setUserName(userNamex: String) {
	 userName = userNamex
    }
    


    fun resetData() {
	  mealId = ""
	  mealName = ""
	  calories = ""
	  dates = ""
	  images = ""
	  analysis = ""
	  userName = ""
    }
    
    fun isCreateMealError(): Boolean {
	        
	        errors.clear()
	        
          if (mealId != "") {
	//validate
}
	         else {
	               errors.add("mealId cannot be empty")
	         }
	          if (mealName != "") {
	//validate
}
	         else {
	               errors.add("mealName cannot be empty")
	         }
	    try {
	          dcalories = calories.toDouble()
	        } catch (e: Exception) {
	        	errors.add("calories is not a Double")
	        }
            if (validateDate(dates)) {
		//validateDate
	}
	         else {
	               errors.add("dates should written as \"DD-MM-YYYY\"")
	         }
          if (images != "") {
	//validate
}
	         else {
	               errors.add("images cannot be empty")
	         }
	          if (userName != "") {
	//validate
}
	         else {
	               errors.add("userName cannot be empty")
	         }
	
	        return errors.size > 0
	    }
	    
	    fun createMeal() {
	        model.createMeal(MealVO(mealId, mealName, dcalories, dates, images, analysis, userName))
	        resetData()
	    }
	   
     fun editMeal() {
		     model.editMeal(MealVO(mealId, mealName, dcalories, dates, images, analysis, userName))
		     resetData()
		 }
		       
		 fun isEditMealError(allMealmealIds: List<String>): Boolean {
       
       errors.clear()
			
			if (!allMealmealIds.contains(mealId)) {
				errors.add("The mealId is not exist")
		    }
          if (mealId != "") {
	//validate
}
	         else {
	               errors.add("mealId cannot be empty")
	         }
          if (mealName != "") {
	//validate
}
	         else {
	               errors.add("mealName cannot be empty")
	         }
    try {
	          dcalories = calories.toDouble()
	        } catch (e: Exception) {
	        	errors.add("calories is not a Double")
	        }
            if (validateDate(dates)) {
	//validateDate
}
	                else {
	                    errors.add("dates should written as \"DD-MM-YYYY\"")
	                }
          if (images != "") {
	//validate
}
	         else {
	               errors.add("images cannot be empty")
	         }
          if (userName != "") {
	//validate
}
	         else {
	               errors.add("userName cannot be empty")
	         }

       return errors.size > 0
   }
       
   fun deleteMeal() {
       model.deleteMeal(mealId)
       resetData()
   }
   
   fun isDeleteMealError(allMealmealIds: List<String>): Boolean {
        errors.clear()
			 if (!allMealmealIds.contains(mealId)) {
			    errors.add("The mealId is not exist")
        }
        return errors.size > 0
		}    

   	fun isSearchMealError(allMealdatess: List<String>): Boolean {
        errors.clear()
        if (!allMealdatess.contains(dates)) {
            errors.add("The dates is not exist")
        }
        return errors.size > 0
    }
    
		fun isSearchMealIdError(allMealIds: List<String>): Boolean {
    	   errors.clear()
   	       if (!allMealIds.contains(mealId)) {
    	       errors.add("The mealId is not exist")
    	   }
           return errors.size > 0
    }

    fun errors(): String {
        return errors.toString()
    }


private fun validateDate(date: String): Boolean {
       val regex = "^(1[0-9]|0[1-9]|3[0-1]|2[1-9])-(0[1-9]|1[0-2])-[0-9]{4}$"
       val matcher = Pattern.compile(regex).matcher(date)
       return if (matcher.matches()) {
           matcher.reset()
           if (matcher.find()) {
               val dateDetails = date.split("-")
               val day: String = dateDetails[1]
               val month: String = dateDetails[0]
               val year: String = dateDetails[2]
               if (validateMonthWithMaxDate(day, month)) {
                   false
               } else if (isFebruaryMonth(month)) {
                   if (isLeapYear(year)) {
                       leapYearWith29Date(day)
                   } else {
                       notLeapYearFebruary(day)
                   }
               } else {
                   true
               }
           } else {
               false
           }
       } else {
           false
       }
   }
	
   private fun validateMonthWithMaxDate(day: String, month: String): Boolean = day == "31" && (month == "11" || month == "04" || month == "06" || month == "09")
   private fun isFebruaryMonth(month: String): Boolean = month == "02"
   private fun isLeapYear(year: String): Boolean = year.toInt() % 4 == 0
   private fun leapYearWith29Date(day: String): Boolean = !(day == "30" || day == "31")
   private fun notLeapYearFebruary(day: String): Boolean = !(day == "29" || day == "30" || day == "31")

}

