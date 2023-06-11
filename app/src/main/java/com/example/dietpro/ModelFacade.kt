package com.example.dietpro

import android.content.Context
import java.util.ArrayList
import android.graphics.Bitmap
import android.content.res.AssetManager
import org.tensorflow.lite.Interpreter


class ModelFacade private constructor(context: Context) {

    private var cdb: FirebaseDB = FirebaseDB.getInstance()
    private val assetManager: AssetManager = context.assets
    private var fileSystem: FileAccessor
    private var imageClassifier: ImageClassifier

   private var currentUser: UserVO? = null
		private var currentUsers: ArrayList<UserVO> = ArrayList()
    private var currentMeal: MealVO? = null
    private var currentMeals: ArrayList<MealVO> = ArrayList()

    init {
    	//init
        ModelPreferencesManager.with(context, "UserDATA")
        currentUser = getUser()
        fileSystem = FileAccessor(context)
        imageClassifier = ImageClassifier(context)
	}

    companion object {
        private var instance: ModelFacade? = null
        fun getInstance(context: Context): ModelFacade {
            return instance ?: ModelFacade(context)
        }
    }
    
    fun createMeal(x: MealVO) { 
			  editMeal(x)
	}
				    
    fun editMeal(x: MealVO) {
		var obj = getMealByPK(x.mealId)
		if (obj == null) {
			obj = Meal.createByPKMeal(x.mealId)
		}
			
		  obj.mealId = x.mealId
		  obj.mealName = x.mealName
		  obj.calories = x.calories
		  obj.dates = x.dates
		  obj.images = x.images
		  obj.analysis = x.analysis
		  obj.userName = x.userName
		cdb.persistMeal(obj)
		currentMeal = x
	}
		
	fun deleteMeal(id: String) {
			  val obj = getMealByPK(id)
			  if (obj != null) {
			      cdb.deleteMeal(obj)
			          Meal.killMeal(id)
			      }
			  currentMeal = null	
		}
				    
    fun setSelectedMeal(x: MealVO) {
			  currentMeal = x
	}
    fun searchMealByDate(dates: String) : ArrayList<Meal> {
			var itemsList = ArrayList<Meal>()
			for (x in currentMeals.indices) {
				if ( currentMeals[x].dates == dates) {
					val vo: MealVO = currentMeals[x]
				    val itemx = Meal.createByPKMeal(vo.mealId)
	            itemx.mealId = vo.mealId
            itemx.mealName = vo.mealName
            itemx.calories = vo.calories
            itemx.dates = vo.dates
            itemx.images = vo.images
            itemx.analysis = vo.analysis
            itemx.userName = vo.userName
					itemsList.add(itemx)
				}
			}
			return itemsList
		}
		
	
	fun searchMealByDate(): ArrayList<String> {
		val res: ArrayList<String> = ArrayList()
		for (x in currentMeals.indices) {
			res.add(currentMeals[x].dates.toString())
		}
		return res
	}
		
	fun getUser() : UserVO? {
        currentUsers.clear()
        currentUser = ModelPreferencesManager.get<User>("KEYUser")?.let { UserVO(it) }
    	currentUser?.let { currentUsers.add(0, it) }
    	return currentUser
    }
    
    fun createUser(x: UserVO) {
        ModelPreferencesManager.put(x, "KEYUser")
        currentUser = x
        currentUser?.let { currentUsers.add(0, it) }
    }
    fun setSelectedUser(x: UserVO) {
	      currentUser = x
	}
	    
    fun findTotalConsumedCaloriesByDate(meals: ArrayList<Meal>, user: User, dates: String): Double {
	      var result : Double
        var totalConsumedCalories: Double
          totalConsumedCalories  = 0.0
        for (meal in meals) {
	        if (meal.userName == user.userName && meal.dates == dates) {
		            totalConsumedCalories  = totalConsumedCalories + meal.calories
	        }
    }
          user.totalConsumedCalories  = totalConsumedCalories
        persistUser (user)
          result  = totalConsumedCalories
	return result
	}
	          
    fun findTargetCalories(user: User): Double {
	      var result : Double
          user.targetCalories  = user.calculateTargetCalories()
        persistUser (user)
          result  = user.targetCalories
	return result
	}
	          
    fun findBMR(user: User): Double {
	      var result : Double
          user.bmr  = user.calculateBMR()
        persistUser (user)
          result  = user.bmr
	return result
	}
	          
    fun caloriesProgress(user: User): Double {
	      var result : Double
        var progress: Double
          progress  = (user.totalConsumedCalories / user.targetCalories) * 100
        persistUser (user)
          result  = progress
	return result
	}
	          
    fun addUsereatsMeal(userName: String, mealId: String) {
	      var obj = getMealByPK(mealId)
	      if (obj == null) {
	          obj = Meal.createByPKMeal(mealId)
          }
	      obj.userName = userName
	      cdb.persistMeal(obj)
	      currentMeal = MealVO(obj)
	          
	 }
	    
    fun removeUsereatsMeal(userName: String, mealId: String) {
		     var obj = getMealByPK(mealId)
		     if (obj == null) {
	             obj = Meal.createByPKMeal(mealId)
	         }
		     obj.userName = "Null"
		     cdb.persistMeal(obj)
	         currentMeal = MealVO(obj)
		          
	}
	
    fun imageRecognition(meal: Meal ,images: Bitmap): String {
			val result = imageClassifier.recognizeImage(images)
	        meal.analysis = result[0].title  +": " + result[0].confidence
		    persistMeal(meal)
	    	return result[0].title  +": " + result[0].confidence
		}
			     


	fun listUser(): ArrayList<UserVO> {
		 	getUser()
	        return currentUsers
		}
	
		fun listAllUser(): ArrayList<User> {	
			currentUsers = listUser()
			var res = ArrayList<User>()
			for (x in currentUsers.indices) {
				val vo: UserVO = currentUsers[x]
				val itemx = User.createByPKUser(vo.userName)
				itemx.userName = vo.userName
				itemx.gender = vo.gender
				itemx.heights = vo.heights
				itemx.weights = vo.weights
				itemx.activityLevel = vo.activityLevel
				itemx.age = vo.age
				itemx.targetCalories = vo.targetCalories
				itemx.totalConsumedCalories = vo.totalConsumedCalories
				itemx.bmr = vo.bmr
				res.add(itemx)
			}
			return res
		}
	
	    fun stringListUser(): ArrayList<String> {
	        currentUsers = listUser()
	        val res: ArrayList<String> = ArrayList()
	        for (x in currentUsers.indices) {
	            res.add(currentUsers[x].toString())
	        }
	        return res
	    }
	
	    fun getUserByPK(value: String): User? {
	        val res: ArrayList<UserVO> = listUser()
		        return if (res.isEmpty()) {
		            null
		        } else {
					val vo: UserVO = res[0]
					val itemx = User.createByPKUser(value)
					itemx.userName = vo.userName
					itemx.gender = vo.gender
					itemx.heights = vo.heights
					itemx.weights = vo.weights
					itemx.activityLevel = vo.activityLevel
					itemx.age = vo.age
					itemx.targetCalories = vo.targetCalories
					itemx.totalConsumedCalories = vo.totalConsumedCalories
					itemx.bmr = vo.bmr
					itemx
		        }
	    }
	    
	    fun retrieveUser(value: String): User? {
	        return getUserByPK(value)
	    }
	
	    fun allUserUserNames(): ArrayList<String> {
	        currentUsers = listUser()
	        val res: ArrayList<String> = ArrayList()
	            for (user in currentUsers.indices) {
	                res.add(currentUsers[user].userName)
	            }
	        return res
	    }

	    fun setSelectedUser(i: Int) {
	        if (i < currentUsers.size) {
	            currentUser = currentUsers[i]
	        }
	    }
	
	    fun getSelectedUser(): UserVO? {
	        return currentUser
	    }
	
	    fun persistUser(x: User) {
	        val vo = UserVO(x)
	        createUser(vo)
	        currentUser = vo
	    }
		

	    	fun listMeal(): ArrayList<MealVO> {
		  val meals: ArrayList<Meal> = Meal.mealAllInstances
		  currentMeals.clear()
		  for (i in meals.indices) {
		       currentMeals.add(MealVO(meals[i]))
		  }
			      
		 return currentMeals
	}
	
	fun listAllMeal(): ArrayList<Meal> {
		  val meals: ArrayList<Meal> = Meal.mealAllInstances
		  return meals
	}
	

			    
    fun stringListMeal(): ArrayList<String> {
        val res: ArrayList<String> = ArrayList()
        for (x in currentMeals.indices) {
            res.add(currentMeals[x].toString())
        }
        return res
    }

    fun getMealByPK(value: String): Meal? {
        return Meal.mealIndex[value]
    }
    
    fun retrieveMeal(value: String): Meal? {
            return getMealByPK(value)
    }

    fun allMealMealIds(): ArrayList<String> {
        val res: ArrayList<String> = ArrayList()
            for (x in currentMeals.indices) {
                res.add(currentMeals[x].mealId)
            }
        return res
    }
    
    fun setSelectedMeal(i: Int) {
        if (i < currentMeals.size) {
            currentMeal = currentMeals[i]
        }
    }

    fun getSelectedMeal(): MealVO? {
        return currentMeal
    }

    fun persistMeal(x: Meal) {
        val vo = MealVO(x)
        cdb.persistMeal(x)
        currentMeal = vo
    }

		
}
