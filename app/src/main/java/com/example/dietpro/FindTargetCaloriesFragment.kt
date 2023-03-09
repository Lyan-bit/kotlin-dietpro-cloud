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

class FindTargetCaloriesFragment : Fragment(), View.OnClickListener , AdapterView.OnItemSelectedListener {
	private lateinit var root: View
	private lateinit var myContext: Context
	private lateinit var model: ModelFacade
			
	private lateinit var findTargetCaloriesBean: FindTargetCaloriesBean
	private lateinit var findTargetCalories: Button
	private lateinit var cancelFindTargetCalories: Button
	private lateinit var findTargetCaloriesResult: TextView

	private lateinit var findTargetCaloriesuserSpinner: Spinner
	private var findTargetCaloriesuserListItems: List<String> = ArrayList()
	private lateinit var findTargetCaloriesuserAdapter: ArrayAdapter<String>
	private var findTargetCaloriesuserData = "" 
		  		
    companion object {
        fun newInstance(c: Context): FindTargetCaloriesFragment {
            val fragment = FindTargetCaloriesFragment()
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
		root = inflater.inflate(R.layout.findtargetcalories_layout, container, false)
        model = ModelFacade.getInstance(myContext)
        
		findTargetCaloriesuserSpinner = root.findViewById(R.id.findTargetCaloriesUserSpinner)
		findTargetCaloriesuserListItems = model.allUserUserNames() 
		findTargetCaloriesuserAdapter = ArrayAdapter(myContext, android.R.layout.simple_spinner_item, findTargetCaloriesuserListItems) 
		findTargetCaloriesuserAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) 
		findTargetCaloriesuserSpinner.adapter = findTargetCaloriesuserAdapter 
		findTargetCaloriesuserSpinner.onItemSelectedListener = this 


		findTargetCaloriesResult = root.findViewById(R.id.findTargetCaloriesResult)
		findTargetCaloriesBean = FindTargetCaloriesBean(myContext)

        findTargetCalories = root.findViewById(R.id.findTargetCaloriesOK)
        findTargetCalories.setOnClickListener(this)
	
        cancelFindTargetCalories = root.findViewById(R.id.findTargetCaloriesCancel)
        cancelFindTargetCalories.setOnClickListener(this)
        
			
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
			
			R.id.findTargetCaloriesOK-> {
				 findTargetCaloriesOK()
			}
			R.id.findTargetCaloriesCancel-> {
				 findTargetCaloriesCancel()
			}
			
		}
		
	}
	
	private fun findTargetCaloriesOK () {

				findTargetCaloriesBean.setUser(findTargetCaloriesuserData)

	    if (findTargetCaloriesBean.isFindTargetCaloriesError()) {
	       Log.w(javaClass.name, findTargetCaloriesBean.errors())
	       Toast.makeText(myContext, "Errors: " + findTargetCaloriesBean.errors(), Toast.LENGTH_LONG).show()
	    } else {
	       findTargetCaloriesResult.text = findTargetCaloriesBean.findTargetCalories().toString()
	       Toast.makeText(myContext, "done!", Toast.LENGTH_LONG).show()
	    }
	}
	
	private fun findTargetCaloriesCancel () {
	    findTargetCaloriesBean.resetData()
	    findTargetCaloriesResult.text = ""

	}
	

    override fun onItemSelected(parent: AdapterView<*>, v: View?, position: Int, id: Long) {
	 	if (parent === findTargetCaloriesuserSpinner) {
	 	    findTargetCaloriesuserData = findTargetCaloriesuserListItems[position]
	 	}
	 	    }
	 	
	 	    override fun onNothingSelected(parent: AdapterView<*>) {
	 	    	//onNothingSelected
	 	    }

}
