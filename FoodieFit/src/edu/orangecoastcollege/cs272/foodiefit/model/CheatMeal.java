package edu.orangecoastcollege.cs272.foodiefit.model;

public class CheatMeal {
	private int mId;
	private String mName;
	private String mCheatMeal;
	private int mCalories;
	
	public CheatMeal(int id, String name, String cheatMeal, int calories) {
		super();
		mId = id;
		mName = name;
		mCheatMeal = cheatMeal;
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

	public String getCheatMeal() {
		return mCheatMeal;
	}

	public void setCheatMeal(String cheatMeal) {
		mCheatMeal = cheatMeal;
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
		result = prime * result + ((mCheatMeal == null) ? 0 : mCheatMeal.hashCode());
		result = prime * result + mId;
		result = prime * result + ((mName == null) ? 0 : mName.hashCode());
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
		CheatMeal other = (CheatMeal) obj;
		if (mCalories != other.mCalories)
			return false;
		if (mCheatMeal == null) {
			if (other.mCheatMeal != null)
				return false;
		} else if (!mCheatMeal.equals(other.mCheatMeal))
			return false;
		if (mId != other.mId)
			return false;
		if (mName == null) {
			if (other.mName != null)
				return false;
		} else if (!mName.equals(other.mName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CheatMeal [Id=" + mId + ", Name=" + mName + ", CheatMeal=" + mCheatMeal + ", Calories=" + mCalories
				+ "]";
	}
}
