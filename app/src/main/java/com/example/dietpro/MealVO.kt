package com.example.dietpro

import java.util.ArrayList

class MealVO  {

    private var mealId: String = ""
    private var mealName: String = ""
    private var calories: Double = 0.0
    private var dates: String = ""
    private var images: String = ""
    private var analysis: String = ""
    private var userName: String = ""

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
    
    fun getMealId(): String {
        return mealId
    }
    
    fun getMealName(): String {
        return mealName
    }
    
    fun getCalories(): Double {
        return calories
    }
    
    fun getDates(): String {
        return dates
    }
    
    fun getImages(): String {
        return images
    }
    
    fun getAnalysis(): String {
        return analysis
    }
    
    fun getUserName(): String {
        return userName
    }
    

    fun setMealId(x: String) {
    	mealId = x
    }
    
    fun setMealName(x: String) {
    	mealName = x
    }
    
    fun setCalories(x: Double) {
    	calories = x
    }
    
    fun setDates(x: String) {
    	dates = x
    }
    
    fun setImages(x: String) {
    	images = x
    }
    
    fun setAnalysis(x: String) {
    	analysis = x
    }
    
    fun setUserName(x: String) {
    	userName = x
    }
    
}
