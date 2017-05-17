package edu.orangecoastcollege.cs272.foodiefit.model;

public class Meal {
	private int mId;
	private String mName;
	private String mPlan;
	private int mCalories;
	
	public Meal(int id, String name, String plan, int calories) {
		super();
		mId = id;
		mName = name;
		mPlan = plan;
		mCalories = calories;
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

	public String getPlan() {
		return mPlan;
	}

	public void setPlan(String plan) {
		mPlan = plan;
	}

	public int getCalories() {
		return mCalories;
	}

	public void setCalories(int calories) {
		mCalories = calories;
	}

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

	@Override
	public String toString() {
		return "Meal [Id=" + mId + ", Name=" + mName + ", Plan=" + mPlan + ", Calories=" + mCalories + "]";
	}
	
	
}
