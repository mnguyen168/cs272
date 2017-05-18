package edu.orangecoastcollege.cs272.foodiefit.model;

/**
 * The <code>Meal</code> is an object of a meal which includes id, name, plan, and calories.
 * 
 * @author Michael Nguyen
 * @version 1.0
 *
 */
public class Meal {
	private int mId;
	private String mName;
	private String mPlan;
	private int mCalories;
	
	/**
	 * The <code>Meal</code> defines the meal with an id, name, plan, and calories.
	 * @param id The id of the meal.
	 * @param name The name of the meal.
	 * @param plan The plan of the meal.
	 * @param calories The calories of the meal.
	 */
	public Meal(int id, String name, String plan, int calories) {
		super();
		mId = id;
		mName = name;
		mPlan = plan;
		mCalories = calories;
	}
	
	/**
	 * The <code>getId</code> gets the id of the meal.
	 * @return The id of the meal.
	 */
	public int getId() {
		return mId;
	}
	
	/**
	 * The <code>getName</code> gets the name of the meal.
	 * @return The name of the meal.
	 */
	public String getName() {
		return mName;
	}
	
	/**
	 * The <code>setName</code>sets the name of the meal.
	 * @param name The name of the meal.
	 */
	public void setName(String name) {
		mName = name;
	}
	
	/**
	 * The <code>getPlan</code> gets the plan of the meal.
	 * @return The plan of the meal.
	 */
	public String getPlan() {
		return mPlan;
	}
	
	/**
	 * The <code>setPlan</code> sets the plan of the meal.
	 * @param plan The plan of the meal.
	 */
	public void setPlan(String plan) {
		mPlan = plan;
	}
	
	/**
	 * The <code>getCalories</code> gets the calories of the meal.
	 * @return The calories of the meal.
	 */
	public int getCalories() {
		return mCalories;
	}
	
	/**
	 * The <code>setCalories</code> sets the calories of the meal.
	 * @param calories The calories of the meal.
	 */
	public void setCalories(int calories) {
		mCalories = calories;
	}
	
	/**
	 * The <code>hashCode</code> returns one value.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + mCalories;
		result = prime * result + mId;
		result = prime * result + ((mName == null) ? 0 : mName.hashCode());
		result = prime * result + ((mPlan == null) ? 0 : mPlan.hashCode());
		return result;
	}
	
	/**
	 * The <code>equals</code> checks to see if the meals are the same.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Meal other = (Meal) obj;
		if (mCalories != other.mCalories)
			return false;
		if (mId != other.mId)
			return false;
		if (mName == null) {
			if (other.mName != null)
				return false;
		} else if (!mName.equals(other.mName))
			return false;
		if (mPlan == null) {
			if (other.mPlan != null)
				return false;
		} else if (!mPlan.equals(other.mPlan))
			return false;
		return true;
	}
	
	/**
	 * The <code>toString</code> displays the list of meals with the id, namee, plan, and calories.
	 */
	@Override
	public String toString() {
		return "Meal [Id=" + mId + ", Name=" + mName + ", Plan=" + mPlan + ", Calories=" + mCalories + "]";
	}
	
	
}
