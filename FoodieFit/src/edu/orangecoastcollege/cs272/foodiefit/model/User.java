package edu.orangecoastcollege.cs272.foodiefit.model;

/**
 * The <code>User</code> is an object of a user which includes id, username, name, age, height,
 * and weight.
 * 
 * @author Michael Nguyen
 * @version 1.0
 *
 */
public class User {
	
	private int mId;
	private String mUsername;
	private String mName;
	private int mAge;
	private int mHeight;
	private int mWeight;
	
	/**
	 * The <code>User</code> defines the user with an id, username, age, height, and weight.
	 * @param id The id of the user.
	 * @param username The username of the user.
	 * @param name The name of the user.
	 * @param age The age of the user.
	 * @param height The height of the user.
	 * @param weight The weight of the user.
	 */
	public User(int id, String username, String name, int age, int height, int weight) {
		super();
		mId = id;
		mUsername = username;
		mName = name;
		mAge = age;
		mHeight = height;
		mWeight = weight;
	}
	
	/**
	 * The <code>getId</code> gets the id of the user.
	 * @return The id of the user.
	 */
	public int getId() {
		return mId;
	}
	
	/**
	 * The <code>getUsername</code> gets the username of the user.
	 * @return The username of the user.
	 */
	public String getUsername() {
		return mUsername;
	}
	
	/**
	 * The <code>setUsername</code> sets the username of the user.
	 * @param username THe username of the user.
	 */
	public void setUsername(String username) {
		mUsername = username;
	}
	
	/**
	 * The <code>getName</code> gets the name of the user.
	 * @return The name of the user.
	 */
	public String getName() {
		return mName;
	}
	
	/**
	 * The <code>setName</code> sets the name of the user.
	 * @param name The name of the user.
	 */
	public void setName(String name) {
		mName = name;
	}
	
	/**
	 * The <code>getAge</code> gets the age of the user.
	 * @return The age of the user.
	 */
	public int getAge() {
		return mAge;
	}
	
	/**
	 * The <code>setAge</code> sets the age of the user.
	 * @param age The age of the user.
	 */
	public void setAge(int age) {
		mAge = age;
	}
	
	/**
	 * The <code>getHeight</code> gets the height of the user.
	 * @return The height of the user.
	 */
	public int getHeight() {
		return mHeight;
	}
	
	/**
	 * The <code>setHeight</code> sets the height of the user.
	 * @param height
	 */
	public void setHeight(int height) {
		mHeight = height;
	}
	
	/**
	 * The <code>getHeight</code> gets the weight of the user.
	 * @return The weight of the user.
	 */
	public int getWeight() {
		return mWeight;
	}
	
	/**
	 * The <code>setWeight</code> sets the weight of the user.
	 * @param weight The weight of the user.
	 */
	public void setWeight(int weight) {
		mWeight = weight;
	}
	
	/**
	 * The <code>hashCode</code> returns one value.
	 */
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
	
	/**
	 * The <code>equals</code> checks to see if the users are the same.
	 */
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
	
	/**
	 * The <code>toString</code> displays the list of users with the id, username, age, height,
	 * and weight.
	 */
	@Override
	public String toString() {
		return "User [Id=" + mId + ", Username=" + mUsername + ", Name=" + mName + ", Age=" + mAge + ", Height="
				+ mHeight + ", Weight=" + mWeight + "]";
	}
}