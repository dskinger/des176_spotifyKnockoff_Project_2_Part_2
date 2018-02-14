package des176_SpotifyKnockoff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class Artist {
	private String artistID;
	private String firstName;
	private String lastName;
	private String bandName;
	private String bio;
	
    /**
     * Main constructor - Used to create a new artist record in the database as well as a new artist object
     * Generates a songIartistID using  java.util.UUID.randomUUID() method
     * @param firstName - first name of the artist
     * @param lastName - last name of the artist
     * @param bandName - name of the band of artists
     * @param bio - short description of the artists
     */
	public Artist(String firstName, String lastName, String bandName, String bio){
		this.firstName = firstName;
		this.lastName = lastName;
		this.bandName = bandName;
		this.bio = bio;
		this.artistID = UUID.randomUUID().toString();
		
		String sql = "INSERT INTO artist (artist_id,first_name,last_name,band_name,bio) ";
		sql += "VALUES (?, ?, ?, ?, ?);";
		
		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, this.artistID);
			ps.setString(2, this.firstName);
			ps.setString(3, this.lastName);
			ps.setString(4, this.bandName);
			ps.setString(5, this.bio);
			ps.executeUpdate();
			db.closeDbConnection();
			db = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ErrorLogger.log(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
    /**
     * Alternate constructor - used to create a artist object using an ID to retrieve an existing record from the database
     * @param artistID - unique identifier for an artist object for the database
     */
	public Artist(String artistID){
		String sql = "SELECT * FROM artist WHERE artist_id = '" + artistID + "';";
		DbUtilities db = new DbUtilities();
		try {
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()){
				this.artistID = rs.getString("artist_id");
				this.firstName = rs.getString("first_name");
				this.lastName = rs.getString("last_name");
				this.bandName = rs.getString("band_name");
				this.bio = rs.getString("bio");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
    /**
     * Deletes an artist from the database using artistID as the key
     * @param artistID - unique identifier for an artist object for the database
     */
	public static void deleteArtist(String artistID) {
		String sql = "DELETE FROM artist WHERE artist_id = '" + artistID + "';";
		DbUtilities db = new DbUtilities();
		Connection conn = db.getConn();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.executeUpdate(sql);
			db.closeDbConnection();
			db = null; 		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Set the db to null to destroy it
	}
	
	// Getters to grab the information about the object
	/**
     * Gets a value from a private variable to promote more secure code
     * @return artistID - Unique Identifier for the database
     */
	public String getArtistID() {
		return artistID;
	}

	/**
     * Gets a value from a private variable to promote more secure code
     * @return firstName - first name of the artist 
     */
	public String getFirstName() {
		return firstName;
	}

	/**
     * Gets a value from a private variable to promote more secure code
     * @return lastName - last name of the artist
     */
	public String getLastName() {
		return lastName;
	}

	/**
     * Gets a value from a private variable to promote more secure code
     * @return bandName - name of a band of artists
     */
	public String getBandName() {
		return bandName;
	}

	/**
     * Gets a value from a private variable to promote more secure code
     * @return bio - short description of the artist
     */
	public String getBio() {
		return bio;
	}

	// Setters to set the information about the object
	/**
     * Sets a value in a private variable to promote more secure code
     * @param firstName - first name of the artist
     */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
     * Sets a value in a private variable to promote more secure code
     * @param lastName - last name of the artist
     */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
     * Sets a value in a private variable to promote more secure code
     * @param bandName - name of a band of artists
     */
	public void setBandName(String bandName) {
		this.bandName = bandName;
	}

	/**
     * Sets a value in a private variable to promote more secure code
     * @param bio - short description of the artist
     */
	public void setBio(String bio) {
		this.bio = bio;
	}
}
