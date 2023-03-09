package com.example.dietpro

import com.google.firebase.database.*
import kotlin.collections.ArrayList

class FirebaseDB() {

    var database: DatabaseReference? = null

    companion object {
        private var instance: FirebaseDB? = null
        fun getInstance(): FirebaseDB {
            return instance ?: FirebaseDB()
        }
    }

    init {
        connectByURL("https://dietpro-e8868-default-rtdb.europe-west1.firebasedatabase.app/")
    }

    fun connectByURL(url: String) {
        database = FirebaseDatabase.getInstance(url).reference
        if (database == null) {
            return
        }
        val mealListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get instances from the cloud database
                val meals = dataSnapshot.value as HashMap<String, Object>?
                if (meals != null) {
                    val keys = meals.keys
                    for (key in keys) {
                        val x = meals[key]
                        MealDAO.parseRaw(x)
                    }
                    // Delete local objects which are not in the cloud:
                    val locals = ArrayList<Meal>()
                    locals.addAll(Meal.mealAllInstances)
                    for (x in locals) {
                        if (keys.contains(x.mealId)) {
                            //check
                        } else {
                            Meal.killMeal(x.mealId)
                        }
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
            	//onCancelled
            }
        }
        database!!.child("meals").addValueEventListener(mealListener)
    }

    fun persistMeal(ex: Meal) {
        val evo = MealVO(ex)
        val key = evo.getMealId()
        if (database == null) {
            return
        }
        database!!.child("meals").child(key).setValue(evo)
    }

    fun deleteMeal(ex: Meal) {
        val key: String = ex.mealId
        if (database == null) {
            return
        }
        database!!.child("meals").child(key).removeValue()
    }
}
