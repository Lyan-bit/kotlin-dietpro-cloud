package com.example.dietpro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager

import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity(), ListMealFragment.OnListMealFragmentInteractionListener, ListUserFragment.OnListUserFragmentInteractionListener  {
        
       	private lateinit var model: ModelFacade
       	
       	override fun onCreate(savedInstanceState: Bundle?) {
       	      super.onCreate(savedInstanceState)
       	      setContentView(R.layout.activity_main)
       	
       	      val myPagerAdapter = PagerAdapter(this, supportFragmentManager)
       	      val viewpager: ViewPager = findViewById(R.id.view_pager)
       	      viewpager.adapter = myPagerAdapter
       	      val tabs: TabLayout = findViewById(R.id.tabs)
       	      tabs.setupWithViewPager(viewpager)
       	      model = ModelFacade.getInstance(this)
       	}
       	
       	override fun onListMealFragmentInteraction(item: MealVO) {
       		  model.setSelectedMeal(item)
       	}
       		 	       	
       	override fun onListUserFragmentInteraction(item: UserVO) {
       		  model.setSelectedUser(item)
       	}
       		 	       	

    }
