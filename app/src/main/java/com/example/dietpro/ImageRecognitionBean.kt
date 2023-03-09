package com.example.dietpro

import android.content.Context
import java.util.*
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64

class ImageRecognitionBean(c: Context) {
    private var model: ModelFacade = ModelFacade.getInstance(c)

    private var meal = ""
	private var instanceMeal: Meal? = null
	
     private var images = ""    
			private var dimages: Bitmap? = null


    private var errors = ArrayList<String>()

    fun setMeal(mealx: String) {
        meal = mealx
    }
    

    fun resetData() {
        meal = ""
    }

    fun isImageRecognitionError(): Boolean {
        errors.clear()
        instanceMeal = model.getMealByPK(meal)
        if (instanceMeal == null) {
            errors.add("meal must be a valid Meal id")
        }
        
             if (instanceMeal!!.images != "") {
		            val x = instanceMeal!!.images
		            dimages= try {
				                // convert base64 to bitmap android
				                val decodedString: ByteArray = Base64.decode(x, Base64.DEFAULT)
				                val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
				                decodedByte
				            }
				            catch (e: Exception) {
				                e.message
				                null
				            }
				        } else {
				            errors.add("This is not a type of image")
				        }


        return errors.size > 0
    }

    fun errors(): String {
        return errors.toString()
    }

     fun imageRecognition (): String {
        return model.imageRecognition(instanceMeal!!, dimages!!)
     }
}
