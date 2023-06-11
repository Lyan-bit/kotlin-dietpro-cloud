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
import java.util.*

class CreateProfileUserFragment : Fragment(), View.OnClickListener {
	private lateinit var root: View
	private lateinit var myContext: Context
	private lateinit var model: ModelFacade
	
	private lateinit var userBean: UserBean
	
	private lateinit var userNameTextField: EditText
	private var userNameData = ""
	private lateinit var genderTextField: EditText
	private var genderData = ""
	private lateinit var heightsTextField: EditText
	private var heightsData = ""
	private lateinit var weightsTextField: EditText
	private var weightsData = ""
	private lateinit var ageTextField: EditText
	private var ageData = ""
	private lateinit var activityLevelTextField: EditText
	private var activityLevelData = ""
	private lateinit var targetCaloriesTextView: TextView
		private var targetCaloriesData = ""
	private lateinit var totalConsumedCaloriesTextView: TextView
		private var totalConsumedCaloriesData = ""
	private lateinit var bmrTextView: TextView
		private var bmrData = ""

    private lateinit var createUserButton: Button
	private lateinit var cancelUserButton: Button

		  		
    companion object {
        fun newInstance(c: Context): CreateProfileUserFragment {
            val fragment = CreateProfileUserFragment()
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
		root = inflater.inflate(R.layout.createprofileuser_layout, container, false)
        model = ModelFacade.getInstance(myContext)

				userNameTextField = root.findViewById(R.id.createUseruserNameField)
		genderTextField = root.findViewById(R.id.createUsergenderField)
		heightsTextField = root.findViewById(R.id.createUserheightsField)
		weightsTextField = root.findViewById(R.id.createUserweightsField)
		ageTextField = root.findViewById(R.id.createUserageField)
		activityLevelTextField = root.findViewById(R.id.createUseractivityLevelField)
	targetCaloriesTextView = root.findViewById(R.id.createUsertargetCaloriesView)
	totalConsumedCaloriesTextView = root.findViewById(R.id.createUsertotalConsumedCaloriesView)
	bmrTextView = root.findViewById(R.id.createUserbmrView)

		createUserButton = root.findViewById(R.id.createUserOK)
		createUserButton.setOnClickListener(this)

		cancelUserButton = root.findViewById(R.id.createUserCancel)
		cancelUserButton.setOnClickListener(this)
		
            val user = model.getUser()

		if (user != null) {
			userNameTextField.setText(user.userName.toString())
			genderTextField.setText(user.gender.toString())
			heightsTextField.setText(user.heights.toString())
			weightsTextField.setText(user.weights.toString())
			ageTextField.setText(user.age.toString())
			activityLevelTextField.setText(user.activityLevel.toString())
			targetCaloriesTextView.text = user.targetCalories.toString()
			totalConsumedCaloriesTextView.text = user.totalConsumedCalories.toString()
			bmrTextView.text = user.bmr.toString()

			createUserButton.setText("Edit")


		}
      
      userBean = UserBean(myContext)
	  return root
	}

	override fun onClick(v: View?) {
		val imm = myContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
		try {
			imm.hideSoftInputFromWindow(v?.windowToken, 0)
		} catch (e: Exception) {
			 e.message
		}
		when (v?.id) {
			R.id.createUserOK-> {
				createUserOK()
			}
			R.id.createUserCancel-> {
				createUserCancel()
			}
		}
	}

	private fun createUserOK () {
		userNameData = userNameTextField.text.toString()
		userBean.setUserName(userNameData)
		genderData = genderTextField.text.toString()
		userBean.setGender(genderData)
		heightsData = heightsTextField.text.toString()
		userBean.setHeights(heightsData)
		weightsData = weightsTextField.text.toString()
		userBean.setWeights(weightsData)
		ageData = ageTextField.text.toString()
		userBean.setAge(ageData)
		activityLevelData = activityLevelTextField.text.toString()
		userBean.setActivityLevel(activityLevelData)
		targetCaloriesData = targetCaloriesTextView.text.toString()
			userBean.setTargetCalories(targetCaloriesData)
		totalConsumedCaloriesData = totalConsumedCaloriesTextView.text.toString()
			userBean.setTotalConsumedCalories(totalConsumedCaloriesData)
		bmrData = bmrTextView.text.toString()
			userBean.setBmr(bmrData)

			if (userBean.isCreateUserError()) {
				Log.w(javaClass.name, userBean.errors())
				Toast.makeText(myContext, "Errors: " + userBean.errors(), Toast.LENGTH_LONG).show()
			} else {
					userBean.createUser()
					Toast.makeText(myContext, "User created!", Toast.LENGTH_LONG).show()
			}
	}

	private fun createUserCancel () {
		userBean.resetData()
		userNameTextField.setText("")
		genderTextField.setText("")
		heightsTextField.setText("")
		weightsTextField.setText("")
		ageTextField.setText("")
		activityLevelTextField.setText("")
		targetCaloriesTextView.text = ""
		totalConsumedCaloriesTextView.text = ""
		bmrTextView.text = ""
	}
	
		
}
