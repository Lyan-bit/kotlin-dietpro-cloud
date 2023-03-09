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

class CaloriesProgressFragment : Fragment(), View.OnClickListener , AdapterView.OnItemSelectedListener {
	private lateinit var root: View
	private lateinit var myContext: Context
	private lateinit var model: ModelFacade
			
	private lateinit var caloriesProgressBean: CaloriesProgressBean
	private lateinit var caloriesProgress: Button
	private lateinit var cancelCaloriesProgress: Button
	private lateinit var caloriesProgressResult: TextView
	
    private lateinit var progressBar: ProgressBar
    private lateinit var percentProgressBar: TextView

	private lateinit var caloriesProgressuserSpinner: Spinner
	private var caloriesProgressuserListItems: List<String> = ArrayList()
	private lateinit var caloriesProgressuserAdapter: ArrayAdapter<String>
	private var caloriesProgressuserData = "" 
		  		
    companion object {
        fun newInstance(c: Context): CaloriesProgressFragment {
            val fragment = CaloriesProgressFragment()
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
		root = inflater.inflate(R.layout.caloriesprogress_layout, container, false)
        model = ModelFacade.getInstance(myContext)
        
		caloriesProgressuserSpinner = root.findViewById(R.id.caloriesProgressUserSpinner)
		caloriesProgressuserListItems = model.allUserUserNames() 
		caloriesProgressuserAdapter = ArrayAdapter(myContext, android.R.layout.simple_spinner_item, caloriesProgressuserListItems) 
		caloriesProgressuserAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) 
		caloriesProgressuserSpinner.adapter = caloriesProgressuserAdapter 
		caloriesProgressuserSpinner.onItemSelectedListener = this 


		caloriesProgressResult = root.findViewById(R.id.caloriesProgressResult)
		caloriesProgressBean = CaloriesProgressBean(myContext)

        caloriesProgress = root.findViewById(R.id.caloriesProgressOK)
        caloriesProgress.setOnClickListener(this)
	
        cancelCaloriesProgress = root.findViewById(R.id.caloriesProgressCancel)
        cancelCaloriesProgress.setOnClickListener(this)
        
		progressBar = root.findViewById(R.id.progressBar)
	    percentProgressBar = root.findViewById(R.id.percentProgressBar)
			
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
			
			R.id.caloriesProgressOK-> {
				 caloriesProgressOK()
			}
			R.id.caloriesProgressCancel-> {
				 caloriesProgressCancel()
			}
			
		}
		
	}
	
	private fun caloriesProgressOK () {

		caloriesProgressBean.setUser(caloriesProgressuserData)
	    if (caloriesProgressBean.isCaloriesProgressError()) {
	       Log.w(javaClass.name, caloriesProgressBean.errors())
	       Toast.makeText(myContext, "Errors: " + caloriesProgressBean.errors(), Toast.LENGTH_LONG).show()
	    } else {
	       caloriesProgressResult.text = caloriesProgressBean.caloriesProgress().toString()
	       progressBar(caloriesProgressBean.caloriesProgress())
	       Toast.makeText(myContext, "done!", Toast.LENGTH_LONG).show()
	    }
	}
	
	private fun caloriesProgressCancel () {
	    caloriesProgressBean.resetData()
	    caloriesProgressResult.text = ""
	}
    

    override fun onItemSelected(parent: AdapterView<*>, v: View?, position: Int, id: Long) {
	 	if (parent === caloriesProgressuserSpinner) {
	 	    caloriesProgressuserData = caloriesProgressuserListItems[position]
	 	}
	 	}
	 	
	override fun onNothingSelected(parent: AdapterView<*>) {
		//onNothingSelected
	}

	
    private fun progressBar(result: Double) {
        progressBar.progress = result.toInt()
        percentProgressBar.text = "$result%"
    }
    
}
