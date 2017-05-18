package edu.orangecoastcollege.cs272.foodiefit.model;

/**
 * The <code>Food</code> is an object of a food which includes id, name, calories, weight, and measure.
 * 
 * @author Michael Nguyen
 * @version 1.0
 *
 */
public class Food {
	
	private int mId;
	private String mName;
	private int mCalories;
	private double mWeight;
	private String mMeasure;
	
	/**
	 * The <code>Food</code> defines the food with an id, name, calories, weight, and measure.
	 * @param id The id of the food.
	 * @param name The name of the food.
	 * @param calories The calories of the food.
	 * @param weight The weight of the food.
	 * @param measure The measure of the food.
	 */
	public Food(int id, String name, int calories, double weight, String measure) {
		super();
		mId = id;
		mName = name;
		mCalories = calories;
		mWeight = weight;
		mMeasure = measure;
	}
	
	/**
	 * The <code>getId</code> gets the id of the food.
	 * @return The id of the food.
	 */
	public int getId() {
		return mId;
	}
	
	/**
	 * The <code>getName</code> gets the name of the food.
	 * @return The name of the food.
	 */
	public String getName() {
		return mName;
	}
	
	/**
	 * The <code>setName</code> sets the name of the food.
	 * @param name The name of the food.
	 */
	public void setName(String name) {
		mName = name;
	}
	
	/**
	 * The <code>getCalories</code> gets the name of the food.
	 * @return The calories of the food.
	 */
	public int getCalories() {
		return mCalories;
	}
	
	/**
	 * The <code>setCalories</code> sets the calories of the food.
	 * @param calories The calories of the food.
	 */
	public void setCalories(int calories) {
		mCalories = calories;
	}
	
	/**
	 * The <code>getWeight</code> gets the weight of the food.
	 * @return The weight of the food.
	 */
	public double getWeight() {
		return mWeight;
	}
	
	/**
	 * The <code>setWeight</code> sets the weight of the food.
	 * @param weight The weight of the food.
	 */
	public void setWeight(double weight) {
		mWeight = weight;
	}
	
	/**
	 * The <code>getMeasure</code> gets the measure of the food.
	 * @return The measure of the food.
	 */
	public String getMeasure() {
		return mMeasure;
	}
	
	/**
	 * The <code>setMeasure</code> sets the measure of the food.
	 * @param measure The measure of the food.
	 */
	public void setMeasure(String measure) {
		mMeasure = measure;
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
		result = prime * result + ((mMeasure == null) ? 0 : mMeasure.hashCode());
		result = prime * result + ((mName == null) ? 0 : mName.hashCode());
		long temp;
		temp = Double.doubleToLongBits(mWeight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	
	/**
	 * The <code>equals</code> checks to see if the foods are the same.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Food other = (Food) obj;
		if (mCalories != other.mCalories)
			return false;
		if (mId != other.mId)
			return false;
		if (mMeasure == null) {
			if (other.mMeasure != null)
				return false;
		} else if (!mMeasure.equals(other.mMeasure))
			return false;
		if (mName == null) {
			if (other.mName != null)
				return false;
		} else if (!mName.equals(other.mName))
			return false;
		if (Double.doubleToLongBits(mWeight) != Double.doubleToLongBits(other.mWeight))
			return false;
		return true;
	}
	
	/**
	 * The <code>toString</code> displays the list of foods with the id, name, calories, weight, and measure.
	 */
	@Override
	public String toString() {
		return "Food [Id=" + mId + ", Name=" + mName + ", Calories=" + mCalories + ", Weight=" + mWeight
				+ ", Measure=" + mMeasure + "]";
	}

	
}
