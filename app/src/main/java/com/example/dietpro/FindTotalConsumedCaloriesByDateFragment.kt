package com.example.dietpro
	
import android.os.Bundle
import android.widget.*
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.content.Context
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.example.dietpro.* 
import java.lang.Exception

class FindTotalConsumedCaloriesByDateFragment : Fragment(), View.OnClickListener , AdapterView.OnItemSelectedListener {
	private lateinit var root: View
	private lateinit var myContext: Context
	private lateinit var model: ModelFacade
			
	private lateinit var findTotalConsumedCaloriesByDateBean: FindTotalConsumedCaloriesByDateBean
	private lateinit var findTotalConsumedCaloriesByDate: Button
	private lateinit var cancelFindTotalConsumedCaloriesByDate: Button
	private lateinit var findTotalConsumedCaloriesByDateResult: TextView

	private lateinit var findTotalConsumedCaloriesByDatemealsSpinner: Spinner
	private var findTotalConsumedCaloriesByDatemealsListItems: List<String> = ArrayList()
	private lateinit var findTotalConsumedCaloriesByDatemealsAdapter: ArrayAdapter<String>
	private var findTotalConsumedCaloriesByDatemealsData = "" 
	private lateinit var findTotalConsumedCaloriesByDateuserSpinner: Spinner
	private var findTotalConsumedCaloriesByDateuserListItems: List<String> = ArrayList()
	private lateinit var findTotalConsumedCaloriesByDateuserAdapter: ArrayAdapter<String>
	private var findTotalConsumedCaloriesByDateuserData = "" 
	private lateinit var datesTextField: EditText
	private var datesData: String = ""
		  		
    companion object {
        fun newInstance(c: Context): FindTotalConsumedCaloriesByDateFragment {
            val fragment = FindTotalConsumedCaloriesByDateFragment()
            val args = Bundle()
            fragment.arguments = args
            fragment.myContext = c
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		root = inflater.inflate(R.layout.findtotalconsumedcaloriesbydate_layout, container, false)
        model = ModelFacade.getInstance(myContext)
        
		findTotalConsumedCaloriesByDatemealsSpinner = root.findViewById(R.id.findTotalConsumedCaloriesByDateMealsSpinner)
		findTotalConsumedCaloriesByDatemealsListItems = model.allMealMealIds() 
		findTotalConsumedCaloriesByDatemealsAdapter = ArrayAdapter(myContext, android.R.layout.simple_spinner_item, findTotalConsumedCaloriesByDatemealsListItems) 
		findTotalConsumedCaloriesByDatemealsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) 
		findTotalConsumedCaloriesByDatemealsSpinner.adapter = findTotalConsumedCaloriesByDatemealsAdapter 
		findTotalConsumedCaloriesByDatemealsSpinner.onItemSelectedListener = this 

		findTotalConsumedCaloriesByDateuserSpinner = root.findViewById(R.id.findTotalConsumedCaloriesByDateUserSpinner)
		findTotalConsumedCaloriesByDateuserListItems = model.allUserUserNames() 
		findTotalConsumedCaloriesByDateuserAdapter = ArrayAdapter(myContext, android.R.layout.simple_spinner_item, findTotalConsumedCaloriesByDateuserListItems) 
		findTotalConsumedCaloriesByDateuserAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) 
		findTotalConsumedCaloriesByDateuserSpinner.adapter = findTotalConsumedCaloriesByDateuserAdapter 
		findTotalConsumedCaloriesByDateuserSpinner.onItemSelectedListener = this 

		datesTextField = root.findViewById(R.id.findTotalConsumedCaloriesByDateDatesField)

		findTotalConsumedCaloriesByDateResult = root.findViewById(R.id.findTotalConsumedCaloriesByDateResult)
		findTotalConsumedCaloriesByDateBean = FindTotalConsumedCaloriesByDateBean(myContext)

        findTotalConsumedCaloriesByDate = root.findViewById(R.id.findTotalConsumedCaloriesByDateOK)
        findTotalConsumedCaloriesByDate.setOnClickListener(this)
	
        cancelFindTotalConsumedCaloriesByDate = root.findViewById(R.id.findTotalConsumedCaloriesByDateCancel)
        cancelFindTotalConsumedCaloriesByDate.setOnClickListener(this)
        
			
	    return root
	}

	override fun onClick(v: View?) {
        val imm = myContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        try {
            if (v != null) {
                imm.hideSoftInputFromWindow(v?.windowToken, 0)
            }
        } catch (e: Exception) { 
        	 e.message
        }

		when (v?.id) {
			
			R.id.findTotalConsumedCaloriesByDateOK-> {
				 findTotalConsumedCaloriesByDateOK()
			}
			R.id.findTotalConsumedCaloriesByDateCancel-> {
				 findTotalConsumedCaloriesByDateCancel()
			}
			
		}
		
	}
	
	private fun findTotalConsumedCaloriesByDateOK () {
    datesData = datesTextField.text.toString()

				findTotalConsumedCaloriesByDateBean.setMeals(findTotalConsumedCaloriesByDatemealsData)
		findTotalConsumedCaloriesByDateBean.setUser(findTotalConsumedCaloriesByDateuserData)
		findTotalConsumedCaloriesByDateBean.setDates(datesData)

	    if (findTotalConsumedCaloriesByDateBean.isFindTotalConsumedCaloriesByDateError()) {
	       Log.w(javaClass.name, findTotalConsumedCaloriesByDateBean.errors())
	       Toast.makeText(myContext, "Errors: " + findTotalConsumedCaloriesByDateBean.errors(), Toast.LENGTH_LONG).show()
	    } else {
	       findTotalConsumedCaloriesByDateResult.text = findTotalConsumedCaloriesByDateBean.findTotalConsumedCaloriesByDate().toString()
	       Toast.makeText(myContext, "done!", Toast.LENGTH_LONG).show()
	    }
	}
	
	private fun findTotalConsumedCaloriesByDateCancel () {
	    findTotalConsumedCaloriesByDateBean.resetData()
	    findTotalConsumedCaloriesByDateResult.text = ""
		datesTextField.setText("")

	}
	

    override fun onItemSelected(parent: AdapterView<*>, v: View?, position: Int, id: Long) {
	 	if (parent === findTotalConsumedCaloriesByDatemealsSpinner) {
	 	    findTotalConsumedCaloriesByDatemealsData = findTotalConsumedCaloriesByDatemealsListItems[position]
	 	}
	 	if (parent === findTotalConsumedCaloriesByDateuserSpinner) {
	 	    findTotalConsumedCaloriesByDateuserData = findTotalConsumedCaloriesByDateuserListItems[position]
	 	}
	 	    }
	 	
	 	    override fun onNothingSelected(parent: AdapterView<*>) {
	 	    	//onNothingSelected
	 	    }

}
