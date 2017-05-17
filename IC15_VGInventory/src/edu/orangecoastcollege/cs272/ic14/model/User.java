package edu.orangecoastcollege.cs272.ic14.model;

/**
 * The <code>User</code> is an object of a user which includes id, name, email, and role.
 * 
 * @author Michael Nguyen
 * @version 1.0
 *
 */
public class User {
	private int mId;
	private String mName;
	private String mEmail;
	private String mRole;
	
	/**
	 * The <code>User</code> defines the user with an id, name, email, and role.
	 * @param id The id of the user.
	 * @param name The name of the user.
	 * @param email The email of the user.
	 * @param role The role of the user.
	 */
	public User(int id, String name, String email, String role) {
		super();
		mId = id;
		mName = name;
		mEmail = email;
		mRole = role;
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
	 * The <code>getEmail</code> gets the email of the user.
	 * @return The email of the user.
	 */
	public String getEmail() {
		return mEmail;
	}
	
	/**
	 * The <code>setEmail</code> sets the email of the user.
	 * @param email The email of the user.
	 */
	public void setEmail(String email) {
		mEmail = email;
	}
	
	/**
	 * The <code>getRole</code> gets the role of the user.
	 * @return The role of the user.
	 */
	public String getRole() {
		return mRole;
	}
	
	/**
	 * The <code>setRole</code> sets the role of the user.
	 * @param role The role of the user.
	 */
	public void setRole(String role) {
		mRole = role;
	}
	
	/**
	 * The <code>getId<code> gets the id of the user.
	 * @return The id of the user.
	 */
	public int getId() {
		return mId;
	}
	
	/**
	 * The <code>hashCode</code> returns one value.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mEmail == null) ? 0 : mEmail.hashCode());
		result = prime * result + mId;
		result = prime * result + ((mName == null) ? 0 : mName.hashCode());
		result = prime * result + ((mRole == null) ? 0 : mRole.hashCode());
		return result;
	}
	
	/**
	 * The <ode>equals</code> checks to see if the users are the same.
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
		if (mEmail == null) {
			if (other.mEmail != null)
				return false;
		} else if (!mEmail.equals(other.mEmail))
			return false;
		if (mId != other.mId)
			return false;
		if (mName == null) {
			if (other.mName != null)
				return false;
		} else if (!mName.equals(other.mName))
			return false;
		if (mRole == null) {
			if (other.mRole != null)
				return false;
		} else if (!mRole.equals(other.mRole))
			return false;
		return true;
	}
	
	/**
	 * The <code>toString</code> displays the list of users with the id, name, email, and role.
	 */
	@Override
	public String toString() {
		return "User [Id=" + mId + ", Name=" + mName + ", Email=" + mEmail + ", Role=" + mRole + "]";
	}
	


	
}
