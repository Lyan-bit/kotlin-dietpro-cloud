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

class FindBMRFragment : Fragment(), View.OnClickListener , AdapterView.OnItemSelectedListener {
	private lateinit var root: View
	private lateinit var myContext: Context
	private lateinit var model: ModelFacade
			
	private lateinit var findBMRBean: FindBMRBean
	private lateinit var findBMR: Button
	private lateinit var cancelFindBMR: Button
	private lateinit var findBMRResult: TextView

	private lateinit var findBMRuserSpinner: Spinner
	private var findBMRuserListItems: List<String> = ArrayList()
	private lateinit var findBMRuserAdapter: ArrayAdapter<String>
	private var findBMRuserData = "" 
		  		
    companion object {
        fun newInstance(c: Context): FindBMRFragment {
            val fragment = FindBMRFragment()
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
		root = inflater.inflate(R.layout.findbmr_layout, container, false)
        model = ModelFacade.getInstance(myContext)
        
		findBMRuserSpinner = root.findViewById(R.id.findBMRUserSpinner)
		findBMRuserListItems = model.allUserUserNames() 
		findBMRuserAdapter = ArrayAdapter(myContext, android.R.layout.simple_spinner_item, findBMRuserListItems) 
		findBMRuserAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) 
		findBMRuserSpinner.adapter = findBMRuserAdapter 
		findBMRuserSpinner.onItemSelectedListener = this 


		findBMRResult = root.findViewById(R.id.findBMRResult)
		findBMRBean = FindBMRBean(myContext)

        findBMR = root.findViewById(R.id.findBMROK)
        findBMR.setOnClickListener(this)
	
        cancelFindBMR = root.findViewById(R.id.findBMRCancel)
        cancelFindBMR.setOnClickListener(this)
        
			
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
			
			R.id.findBMROK-> {
				 findBMROK()
			}
			R.id.findBMRCancel-> {
				 findBMRCancel()
			}
			
		}
		
	}
	
	private fun findBMROK () {

				findBMRBean.setUser(findBMRuserData)

	    if (findBMRBean.isFindBMRError()) {
	       Log.w(javaClass.name, findBMRBean.errors())
	       Toast.makeText(myContext, "Errors: " + findBMRBean.errors(), Toast.LENGTH_LONG).show()
	    } else {
	       findBMRResult.text = findBMRBean.findBMR().toString()
	       Toast.makeText(myContext, "done!", Toast.LENGTH_LONG).show()
	    }
	}
	
	private fun findBMRCancel () {
	    findBMRBean.resetData()
	    findBMRResult.text = ""

	}
	

    override fun onItemSelected(parent: AdapterView<*>, v: View?, position: Int, id: Long) {
	 	if (parent === findBMRuserSpinner) {
	 	    findBMRuserData = findBMRuserListItems[position]
	 	}
	 	    }
	 	
	 	    override fun onNothingSelected(parent: AdapterView<*>) {
	 	    	//onNothingSelected
	 	    }

}
