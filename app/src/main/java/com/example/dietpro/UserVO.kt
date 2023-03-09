package com.example.dietpro

import java.util.ArrayList

class UserVO  {

    private var userName: String = ""
    private var gender: String = ""
    private var heights: Double = 0.0
    private var weights: Double = 0.0
    private var activityLevel: String = ""
    private var age: Double = 0.0
    private var targetCalories: Double = 0.0
    private var totalConsumedCalories: Double = 0.0
    private var bmr: Double = 0.0

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
    
    fun getUserName(): String {
        return userName
    }
    
    fun getGender(): String {
        return gender
    }
    
    fun getHeights(): Double {
        return heights
    }
    
    fun getWeights(): Double {
        return weights
    }
    
    fun getActivityLevel(): String {
        return activityLevel
    }
    
    fun getAge(): Double {
        return age
    }
    
    fun getTargetCalories(): Double {
        return targetCalories
    }
    
    fun getTotalConsumedCalories(): Double {
        return totalConsumedCalories
    }
    
    fun getBmr(): Double {
        return bmr
    }
    

    fun setUserName(x: String) {
    	userName = x
    }
    
    fun setGender(x: String) {
    	gender = x
    }
    
    fun setHeights(x: Double) {
    	heights = x
    }
    
    fun setWeights(x: Double) {
    	weights = x
    }
    
    fun setActivityLevel(x: String) {
    	activityLevel = x
    }
    
    fun setAge(x: Double) {
    	age = x
    }
    
    fun setTargetCalories(x: Double) {
    	targetCalories = x
    }
    
    fun setTotalConsumedCalories(x: Double) {
    	totalConsumedCalories = x
    }
    
    fun setBmr(x: Double) {
    	bmr = x
    }
    
}
