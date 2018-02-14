package des176_SpotifyKnockoff;

import java.util.Map;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Song {
	private String songID;
	private String title;
	private double length;
	private String filePath;
	private String releaseDate;
	private String recordDate;
	Map<String, Artist> songArtists;
	
    /**
     * Main constructor - Used to create a new song record in the database as well as a new song object
     * Generates a songID using  java.util.UUID.randomUUID() method
     * @param title - title of the song
     * @param length - length of the song
     * @param releaseDate - The date the song was released
     * @param recordDate - The date that the song was recorded
     */
	public Song(String title, double length, String releaseDate, String recordDate){
		this.title = title;
		this.length = length;
		this.releaseDate = releaseDate;
		this.recordDate = recordDate;
		this.songID = UUID.randomUUID().toString();
		
		// System.out.println(this.songID);
		// String sql = "INSERT INTO song (song_id,title,length,file_path,release_date,record_date,fk_album_id) ";
		// sql += "VALUES ('" + this.songID + "', '" + this.title + "', " + this.length + ", '', '" + this.releaseDate + "', '" + this.recordDate + "', '" + this.albumID + "');";
		String sql = "INSERT INTO song (song_id,title,length,file_path,release_date,record_date) ";
		sql += "VALUES (?, ?, ?, ?, ?, ?);";
		
		//
		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, this.songID);
			ps.setString(2, this.title);
			ps.setDouble(3, this.length);
			ps.setString(4, " ");
			ps.setString(5, this.releaseDate);
			ps.setString(6, this.recordDate);
			ps.executeUpdate();
			db.closeDbConnection();
			db = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    /**
     * Alternate constructor - used to create a Song object using an ID to retrieve an existing record from the database
     * @param songID - Unique Identifier for a song object in the SpotifyKnockoff Database
     */
	public Song(String songID){
		songArtists = new Hashtable<>(); 
		String sql = "SELECT * FROM song WHERE song_id = '" + songID + "';";
		DbUtilities db = new DbUtilities();
		try {
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()){
				this.songID = rs.getString("song_id");
				this.title = rs.getString("title");
				this.releaseDate = rs.getDate("release_date").toString();
				this.recordDate = rs.getDate("record_date").toString();
				this.length = rs.getDouble("length");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
    /**
     * Deletion of a song entry - used to delete a Song object using an ID from the database
     * @param songID - Unique Identifier for a song object in the SpotifyKnockoff Database
     */
	public static void deleteSong(String songID) {
		String sql = "DELETE FROM song WHERE song_id = '" + songID + "';";
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
	
    /**
     * Creates a relationship between a song and artist objects in the song artists table in the database
     * @param artist - artist object from the database
     */
	public void addArtist(Artist artist) {
		songArtists = new Hashtable<>(); 
		songArtists.put(artist.getArtistID(), artist);
		String sql = "INSERT INTO song_artist (fk_song_id,fk_artist_id) VALUES (?, ?);";
		
		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, this.songID);
			ps.setString(2, artist.getArtistID());
			ps.executeUpdate();
			db.closeDbConnection();
			db = null; 	
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
    /**
     * Deletes a relationship between song and artist objects in the song artists table in the database using the artistID
     * @param artistID - unique identifier for an artist object from the database
     */
	public void deleteArtist(String artistID) {
		songArtists = new Hashtable<>(); 
		songArtists.remove(songID, artistID);
		String sql = "DELETE FROM song_artist WHERE fk_song_id = ? AND fk_artist_id = ?;";
		
		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, this.songID);
			ps.setString(2, artistID);
			ps.executeUpdate();
			db.closeDbConnection();
			db = null; 	
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
    
	/**
     * Deletes a relationship between song and artist objects in the song artists table in the database using the artist object
     * @param artist - artist object from the database
     */
	public void deleteArtist(Artist artist) {
		songArtists = new Hashtable<>(); 
		songArtists.put(songID, artist);
		String sql = "DELETE FROM song_artist WHERE fk_song_id = ? AND fk_artist_id = ?;";
		
		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, this.songID);
			ps.setString(2, artist.getArtistID());
			ps.executeUpdate();
			db.closeDbConnection();
			db = null; 	
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
    /**
     * Gets a value from a private variable to promote more secure code
     * @return releaseDate - date a song was released
     */
	public String getReleaseDate() {
		return releaseDate;
	}

	/**
     * Gets a value from a private variable to promote more secure code
     * @return songID - Unique Identifier for the Database
     */
	public String getSongID() {
		return songID;
	}

	/**
     * Gets a value from a private variable to promote more secure code
     * @return title - title of the song
     */
	public String getTitle() {
		return title;
	}
	
	/**
     * Gets a value from a private variable to promote more secure code
     * @return length - length of the song
     */
	public double getLength() {
		return length;
	}

	/**
     * Gets a value from a private variable to promote more secure code
     * @return filePath - file path where a song is stored 
     */
	public String getFilePath() {
		return filePath;
	}

	/**
     * Gets a value from a private variable to promote more secure code
     * @return recordDate - date a song was recorded
     */
	public String getRecordDate() {
		return recordDate;
	}

	/**
     * Gets a value from a private variable to promote more secure code
     * @return songArtists - used to help make connections for the database
     */
	public Map<String, Artist> getSongArtists() {
		return songArtists;
	}

	/**
     * Sets a value in a private variable to promote more secure code
     * @param title - title of the song
     */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
     * Sets a value in a private variable to promote more secure code
     * @param length - length of the song
     */
	public void setLength(double length) {
		this.length = length;
	}

	/**
     * Sets a value in a private variable to promote more secure code
     * @param filePath - stores the file path in which the song is stored
     */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
     * Sets a value in a private variable to promote more secure code
     * @param releaseDate - date a song was released
     */
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	/**
     * Sets a value in a private variable to promote more secure code
     * @param recordDate - date a recording was recorded
     */
	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

	/**
     * Sets a value in a private variable to promote more secure code
     * @param songArtists - helps with relationship between artist and song
     */
	public void setSongArtists(Map<String, Artist> songArtists) {
		this.songArtists = songArtists;
	}
	
/*
	Vector<String> getSongRecord(){
		Vector<String> songRecord = new Vector<>(6);
		songRecord.add(this.songID);
		songRecord.add(this.title);
		songRecord.add(this.filePath);
		songRecord.add(this.songID);
		songRecord.add(this.songID);
		songRecord.add(this.songID);
		return songRecord;
	}
*/
}
