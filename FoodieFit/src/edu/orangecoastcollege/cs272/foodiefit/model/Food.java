package edu.orangecoastcollege.cs272.foodiefit.model;

public class Food {
	
	private int mId;
	private String mName;
	private int mCalories;
	private double mWeight;
	private String mMeasure;
	
	public Food(int id, String name, int calories, double weight, String measure) {
		super();
		mId = id;
		mName = name;
		mCalories = calories;
		mWeight = weight;
		mMeasure = measure;
	}

	public int getId() {
		return mId;
	}

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		mName = name;
	}

	public int getCalories() {
		return mCalories;
	}

	public void setCalories(int calories) {
		mCalories = calories;
	}

	public double getWeight() {
		return mWeight;
	}

	public void setWeight(double weight) {
		mWeight = weight;
	}

	public String getMeasure() {
		return mMeasure;
	}

	public void setMeasure(String measure) {
		mMeasure = measure;
	}

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

	@Override
	public String toString() {
		return "Food [Id=" + mId + ", Name=" + mName + ", Calories=" + mCalories + ", Weight=" + mWeight
				+ ", Measure=" + mMeasure + "]";
	}

	
}
