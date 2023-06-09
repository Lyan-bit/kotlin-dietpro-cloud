package com.example.dietpro

import java.util.ArrayList

class MealVO  {

     var mealId: String = ""
     var mealName: String = ""
     var calories: Double = 0.0
     var dates: String = ""
     var images: String = ""
     var analysis: String = ""
     var userName: String = ""

    constructor() {
    	//constructor
    }

    constructor(mealIdx: String, 
        mealNamex: String, 
        caloriesx: Double, 
        datesx: String, 
        imagesx: String, 
        analysisx: String, 
        userNamex: String
        ) {
        this.mealId = mealIdx
        this.mealName = mealNamex
        this.calories = caloriesx
        this.dates = datesx
        this.images = imagesx
        this.analysis = analysisx
        this.userName = userNamex
    }

    constructor (x: Meal) {
        mealId = x.mealId
        mealName = x.mealName
        calories = x.calories
        dates = x.dates
        images = x.images
        analysis = x.analysis
        userName = x.userName
    }

    override fun toString(): String {
        return "mealId = $mealId,mealName = $mealName,calories = $calories,dates = $dates,images = $images,analysis = $analysis,userName = $userName"
    }

    fun toStringList(list: List<MealVO>): List<String> {
        val res: MutableList<String> = ArrayList()
        for (i in list.indices) {
            res.add(list[i].toString())
        }
        return res
    }
    
}
