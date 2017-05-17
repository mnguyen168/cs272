package edu.orangecoastcollege.cs272.ic14.model;

/**
 * The <code>VideoGame</code> is an object of a video game which includes the id, name, platform, year, genre, and publisher.
 * @author Michael Nguyen
 * @version 1.0
 *
 */
public class VideoGame {
	
	private int mId;
	private String mName;
	private String mPlatform;
	private int mYear;
	private String mGenre;
	private String mPublisher;
	
	/**
	 * The <code>VideoGame</code> defines the video game with an id, name, platform, year, genre, and publisher.
	 * @param id The id of the video game.
	 * @param name The name of the video game.
	 * @param platform The platform of the video game.
	 * @param year The year of the video game.
	 * @param genre The genre of the video game.
	 * @param publisher The publisher of the video game.
	 */
	public VideoGame(int id, String name, String platform, int year, String genre, String publisher) {
		super();
		mId = id;
		mName = name;
		mPlatform = platform;
		mYear = year;
		mGenre = genre;
		mPublisher = publisher;
	}
	
	/**
	 * The <code>getName</code> gets the name of the video game.
	 * @return The name of the video game.
	 */
	public String getName() {
		return mName;
	}
	
	/**
	 * The <code>setName</code> sets the name of the video game.
	 * @param name The name of the video game.
	 */
	public void setName(String name) {
		mName = name;
	}
	
	/**
	 * The <code>getPlatform</code> gets the platform of the video game.
	 * @return THe platform of the video game.
	 */
	public String getPlatform() {
		return mPlatform;
	}
	
	/**
	 * The <code>setPlatform</code> sets the platform of the video game.
	 * @param platform The platform of the video game.
	 */
	public void setPlatform(String platform) {
		mPlatform = platform;
	}
	
	/**
	 * The <code>getYear</code> gets the year of the video game.
	 * @return The year of the video game.
	 */
	public int getYear() {
		return mYear;
	}
	
	/**
	 * The <code>setYear</code> sets the year of the video game.
	 * @param year The year of the video game.
	 */
	public void setYear(int year) {
		mYear = year;
	}
	
	/**
	 * The <code>getGenre</code> gets the genre of the video game.
	 * @return The genre of the video game.
	 */
	public String getGenre() {
		return mGenre;
	}
	
	/**
	 * The <code>setGenre</code> sets the genre of the video game.
	 * @param genre The genre of the video game.
	 */
	public void setGenre(String genre) {
		mGenre = genre;
	}
	
	/**
	 * The <code>getPUblisher</code> gets the publisher of the video game.
	 * @return The publisher of the video game.
	 */
	public String getPublisher() {
		return mPublisher;
	}
	
	/**
	 * The <code>setPublisher</code> sets the publisher of the video game.
	 * @param publisher THe publisher of the video game.
	 */
	public void setPublisher(String publisher) {
		mPublisher = publisher;
	}
	
	/**
	 * The <code>getId</code> gets the id of the video game.
	 * @return The id of the video game.
	 */
	public int getId() {
		return mId;
	}
	
	/**
	 * Th e<code>hashCode</code> returns one value.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mGenre == null) ? 0 : mGenre.hashCode());
		result = prime * result + mId;
		result = prime * result + ((mName == null) ? 0 : mName.hashCode());
		result = prime * result + ((mPlatform == null) ? 0 : mPlatform.hashCode());
		result = prime * result + ((mPublisher == null) ? 0 : mPublisher.hashCode());
		result = prime * result + mYear;
		return result;
	}
	
	/**
	 * The <code>equals</code> checks to see if th video games are the same.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VideoGame other = (VideoGame) obj;
		if (mGenre == null) {
			if (other.mGenre != null)
				return false;
		} else if (!mGenre.equals(other.mGenre))
			return false;
		if (mId != other.mId)
			return false;
		if (mName == null) {
			if (other.mName != null)
				return false;
		} else if (!mName.equals(other.mName))
			return false;
		if (mPlatform == null) {
			if (other.mPlatform != null)
				return false;
		} else if (!mPlatform.equals(other.mPlatform))
			return false;
		if (mPublisher == null) {
			if (other.mPublisher != null)
				return false;
		} else if (!mPublisher.equals(other.mPublisher))
			return false;
		if (mYear != other.mYear)
			return false;
		return true;
	}
	
	/**
	 * The <code>toString</code> displays the list of video games with the id, name, platform, year, genre, and publisher.
	 */
	@Override
	public String toString() {
		return "VideoGame [Id=" + mId + ", Name=" + mName + ", Platform=" + mPlatform + ", Year=" + mYear
				+ ", Genre=" + mGenre + ", Publisher=" + mPublisher + "]";
	}
	
	

}
