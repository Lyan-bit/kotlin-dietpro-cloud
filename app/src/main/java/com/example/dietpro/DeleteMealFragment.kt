package com.example.dietpro

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.*
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import java.lang.Exception
import java.util.ArrayList

class DeleteMealFragment : Fragment(), View.OnClickListener, AdapterView.OnItemSelectedListener {
	private lateinit var root: View
	private lateinit var myContext: Context
	private lateinit var model: ModelFacade
	
	private lateinit var mealBean: MealBean
	
	private lateinit var deleteMealSpinner: Spinner
	private var allMealmealIds: List<String> = ArrayList()
	private lateinit var mealIdTextField: EditText
	private var mealIdData = ""
	private lateinit var deleteMealButton : Button
	private lateinit var cancelMealButton : Button	
	
    companion object {
        fun newInstance(c: Context): DeleteMealFragment {
            val fragment = DeleteMealFragment()
            val args = Bundle()
            fragment.arguments = args
            fragment.myContext = c
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		root = inflater.inflate(R.layout.deletemeal_layout, container, false)
	    return root
	}
	
	override fun onResume() {
		super.onResume()
		model = ModelFacade.getInstance(myContext)
		mealBean = MealBean(myContext)

		mealIdTextField = root.findViewById(R.id.crudMealmealIdField)
		deleteMealSpinner = root.findViewById(R.id.crudMealSpinner)
		Log.i("model",model.listMeal().toString())
		Log.i("model",model.allMealMealIds().toString())

		allMealmealIds = model.allMealMealIds()
		val deleteMealAdapter =
		ArrayAdapter(myContext, android.R.layout.simple_spinner_item, allMealmealIds)
		deleteMealAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
		deleteMealSpinner.adapter = deleteMealAdapter
		deleteMealSpinner.onItemSelectedListener = this

		deleteMealButton = root.findViewById(R.id.crudMealOK)
		deleteMealButton.setOnClickListener(this)
		cancelMealButton = root.findViewById(R.id.crudMealCancel)
		cancelMealButton.setOnClickListener(this)
	}
	
	override fun onItemSelected(parent: AdapterView<*>, v: View?, position: Int, id: Long) {
		if (parent === deleteMealSpinner) {
			mealIdTextField.setText(allMealmealIds[position])
		}
	}

	override fun onNothingSelected(parent: AdapterView<*>?) {
		//onNothingSelected
	}

	override fun onClick(v: View?) {
	val imm = myContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
	try {
		imm.hideSoftInputFromWindow(v?.windowToken, 0)
		} catch (e: Exception) {
			 e.message
		}

	when (v?.id) {
		R.id.crudMealOK-> {
			crudMealOK()
		}
		R.id.crudMealCancel-> {
			crudMealCancel()
		}
	  }
    }

	private fun crudMealOK() {
		mealIdData = mealIdTextField.text.toString()
		mealBean.setMealId(mealIdData)
		if (mealBean.isDeleteMealError(allMealmealIds)) {
			Log.w(javaClass.name, mealBean.errors())
			Toast.makeText(myContext, "Errors: " + mealBean.errors(), Toast.LENGTH_LONG).show()
		} else {
		mealBean.deleteMeal()
		Toast.makeText(myContext, "meal is deleted!", Toast.LENGTH_LONG).show()
		}
	}

	private fun crudMealCancel() {
		mealBean.resetData()
		mealIdTextField.setText("")
	}
		 
}
