package com.example.dietpro

import java.util.HashMap

class Meal {

    init {
        mealAllInstances.add(this)
    }

    companion object {
        var mealAllInstances = ArrayList<Meal>()
        fun createMeal(): Meal {
            return Meal()
        }
        
        var mealIndex: HashMap<String, Meal> = HashMap<String, Meal>()
        
        fun createByPKMeal(idx: String): Meal {
            var result: Meal? = mealIndex[idx]
            if (result != null) { return result }
                  result = Meal()
                  mealIndex.put(idx,result)
                  result.mealId = idx
                  return result
        }
        
		fun killMeal(idx: String?) {
            val rem = mealIndex[idx] ?: return
            val remd = ArrayList<Meal>()
            remd.add(rem)
            mealIndex.remove(idx)
            mealAllInstances.removeAll(remd)
        }        
    }

    var mealId = ""  /* identity */
    var mealName = "" 
    var calories = 0.0 
    var dates = "" 
    var images = "" 
    var analysis = ""  /* derived */
    var userName = "" 
    var eatenBy : User? = null

}
