package edu.orangecoastcollege.cs272.foodiefit.model;

public class User {
	
	private int mId;
	private String mUsername;
	private String mName;
	private int mAge;
	private int mHeight;
	private int mWeight;
	
	public User(int id, String username, String name, int age, int height, int weight) {
		super();
		mId = id;
		mUsername = username;
		mName = name;
		mAge = age;
		mHeight = height;
		mWeight = weight;
	}

	public int getId() {
		return mId;
	}

	public String getUsername() {
		return mUsername;
	}

	public void setUsername(String username) {
		mUsername = username;
	}

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		mName = name;
	}

	public int getAge() {
		return mAge;
	}

	public void setAge(int age) {
		mAge = age;
	}

	public int getHeight() {
		return mHeight;
	}

	public void setHeight(int height) {
		mHeight = height;
	}

	public int getWeight() {
		return mWeight;
	}

	public void setWeight(int weight) {
		mWeight = weight;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + mAge;
		result = prime * result + mHeight;
		result = prime * result + mId;
		result = prime * result + ((mName == null) ? 0 : mName.hashCode());
		result = prime * result + ((mUsername == null) ? 0 : mUsername.hashCode());
		result = prime * result + mWeight;
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
		User other = (User) obj;
		if (mAge != other.mAge)
			return false;
		if (mHeight != other.mHeight)
			return false;
		if (mId != other.mId)
			return false;
		if (mName == null) {
			if (other.mName != null)
				return false;
		} else if (!mName.equals(other.mName))
			return false;
		if (mUsername == null) {
			if (other.mUsername != null)
				return false;
		} else if (!mUsername.equals(other.mUsername))
			return false;
		if (mWeight != other.mWeight)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [Id=" + mId + ", Username=" + mUsername + ", Name=" + mName + ", Age=" + mAge + ", Height="
				+ mHeight + ", Weight=" + mWeight + "]";
	}
}