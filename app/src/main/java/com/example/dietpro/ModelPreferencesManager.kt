
package com.example.dietpro

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder

/**
 * Singleton class for managing preferences for POJO or model class's object.
 */
object ModelPreferencesManager {

    //Shared Preference field used to save and retrieve JSON string
    lateinit var preferences: SharedPreferences

    fun with(context: Context, name: String) {
        preferences = context.getSharedPreferences(
            name, Context.MODE_PRIVATE)
    }

    /**
     * Saves object into the Preferences.
     **/
    fun <T> put(`object`: T, key: String) {
        //Convert object to JSON String.
        val jsonString = GsonBuilder().create().toJson(`object`)
        //Save that String in SharedPreferences
        preferences.edit().putString(key, jsonString).apply()
    }

    /**
     * Used to retrieve object from the Preferences.
     **/
    inline fun <reified T> get(key: String): T? {
        //We read JSON String which was saved.
        val value = preferences.getString(key, null)
        //JSON String was found which means object can be read.
        //We convert this JSON String to model object. Parameter "c" (of
        //type Class < T >" is used to cast.
        return GsonBuilder().create().fromJson(value, T::class.java)
    }
}
