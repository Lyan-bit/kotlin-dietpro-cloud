package com.example.dietpro

import java.util.ArrayList

class UserVO  {

     var userName: String = ""
     var gender: String = ""
     var heights: Double = 0.0
     var weights: Double = 0.0
     var activityLevel: String = ""
     var age: Double = 0.0
     var targetCalories: Double = 0.0
     var totalConsumedCalories: Double = 0.0
     var bmr: Double = 0.0

    constructor() {
    	//constructor
    }

    constructor(userNamex: String, 
        genderx: String, 
        heightsx: Double, 
        weightsx: Double, 
        activityLevelx: String, 
        agex: Double, 
        targetCaloriesx: Double, 
        totalConsumedCaloriesx: Double, 
        bmrx: Double
        ) {
        this.userName = userNamex
        this.gender = genderx
        this.heights = heightsx
        this.weights = weightsx
        this.activityLevel = activityLevelx
        this.age = agex
        this.targetCalories = targetCaloriesx
        this.totalConsumedCalories = totalConsumedCaloriesx
        this.bmr = bmrx
    }

    constructor (x: User) {
        userName = x.userName
        gender = x.gender
        heights = x.heights
        weights = x.weights
        activityLevel = x.activityLevel
        age = x.age
        targetCalories = x.targetCalories
        totalConsumedCalories = x.totalConsumedCalories
        bmr = x.bmr
    }

    override fun toString(): String {
        return "userName = $userName,gender = $gender,heights = $heights,weights = $weights,activityLevel = $activityLevel,age = $age,targetCalories = $targetCalories,totalConsumedCalories = $totalConsumedCalories,bmr = $bmr"
    }

    fun toStringList(list: List<UserVO>): List<String> {
        val res: MutableList<String> = ArrayList()
        for (i in list.indices) {
            res.add(list[i].toString())
        }
        return res
    }
    
}
