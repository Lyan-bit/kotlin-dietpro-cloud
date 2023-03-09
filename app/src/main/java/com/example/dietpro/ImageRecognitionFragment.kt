package com.example.dietpro

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.*
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.lifecycle.lifecycleScope
import androidx.fragment.app.Fragment
import java.util.*

class ImageRecognitionFragment : Fragment(), View.OnClickListener, AdapterView.OnItemSelectedListener {
	private lateinit var root: View
	private lateinit var myContext: Context
	private lateinit var model: ModelFacade
	
	lateinit  var imageRecognitionBean : ImageRecognitionBean		
	private lateinit var imageRecognitionSpinner: Spinner
	private var allMealmealIds: List<String> = ArrayList()

	private lateinit var mealIdTextField: EditText
	var mealIdData = ""
	private lateinit var outputTextView: TextView

	//Button declaration
	lateinit var buttonTest: Button
	lateinit var buttonCancel: Button

	companion object {
		fun newInstance(c: Context): ImageRecognitionFragment {
			val fragment = ImageRecognitionFragment()
			val args = Bundle()
			fragment.arguments = args
			fragment.myContext = c
			return fragment
		}
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		root = inflater.inflate(R.layout.imagerecognition_layout, container, false)
		super.onViewCreated(root, savedInstanceState)
		model = ModelFacade.getInstance(myContext)

		imageRecognitionBean = ImageRecognitionBean (myContext)
		imageRecognitionSpinner = root.findViewById(R.id.imageRecognitionSpinner)

		Log.i("model",model.listMeal().toString())
		Log.i("model",model.allMealMealIds().toString())

		allMealmealIds = model.allMealMealIds()
		val imageRecognitionAdapter =
		ArrayAdapter(myContext, android.R.layout.simple_spinner_item, allMealmealIds)
		imageRecognitionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
		imageRecognitionSpinner.adapter = imageRecognitionAdapter
		imageRecognitionSpinner.onItemSelectedListener = this
	
		//UI components declaration
		mealIdTextField = root.findViewById(R.id.imageRecognitionmealIdField)
		outputTextView = root.findViewById(R.id.textView)

		buttonTest = root.findViewById(R.id.imageRecognitionOK)
		buttonTest.setOnClickListener(this)
		
		buttonCancel = root.findViewById(R.id.imageRecognitionCancel)
		buttonTest.setOnClickListener(this)

		return root

	}

	override fun onClick(v: View?) {

		when (v?.id) {
			R.id.imageRecognitionOK-> {
				imageRecognitionOK()
			}
			R.id.imageRecognitionCancel-> {
				imageRecognitionCancel()
			}
		}
	}

	fun imageRecognitionOK () {

			mealIdData = mealIdTextField.text.toString()
			imageRecognitionBean.setMeal(mealIdData)

			if (imageRecognitionBean.isImageRecognitionError()) {
				Log.w(javaClass.name, imageRecognitionBean.errors())
				Toast.makeText(requireContext(), "Errors: " + imageRecognitionBean.errors(), Toast.LENGTH_LONG).show()
			} else {
				viewLifecycleOwner.lifecycleScope.launchWhenStarted {
				outputTextView.setText(imageRecognitionBean.imageRecognition()) }
			}
	}

	private fun imageRecognitionCancel () {
	    imageRecognitionBean.resetData()
	    outputTextView.text = ""
	}
	
	override fun onItemSelected(parent: AdapterView<*>, v: View?, position: Int, id: Long) {
	 	if (parent ===imageRecognitionSpinner) {
	 	    mealIdTextField.setText(allMealmealIds[position])
	 	}
 	}
 	
 	override fun onNothingSelected(parent: AdapterView<*>) {
 		//onNothingSelected
 	}

}
