package des176_SpotifyKnockoff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;

public class Album {
	private String albumID;
	private String title;
	private String releaseDate;
	private String coverImagePath;
	private String recordingCompany;
	private int numberOfTracks;
	private String pmrcRating;
	private double length;
	Map<String, Song> albumSongs;
	
	 /**
     * Main constructor - Used to create a new album record in the database as well as a new album object
     * Generates a albumID using  java.util.UUID.randomUUID() method
     * @param title - title of the album
     * @param releaseDate - The date the album was released
     * @param coverImagePath - where the cover image for the album is stored
     * @param recordingCompany - Name of the company the recorded the album
     * @param numberOfTracks - number of songs on the album
     * @param pmrcRating - the maturity rating given to the album
     * @param length - length of the album
     */
	public Album(String title, String releaseDate, String coverImagePath, String recordingCompany, int numberOfTracks, String pmrcRating, double length){
		this.title = title;
		this.releaseDate = releaseDate;
		this.coverImagePath = coverImagePath;
		this.recordingCompany = recordingCompany;
		this.numberOfTracks = numberOfTracks;
		this.pmrcRating = pmrcRating;
		this.length = length;
		this.albumID = UUID.randomUUID().toString();
		
		albumSongs = new Hashtable<>();
		
		String sql = "INSERT INTO album (album_id,title,release_date,cover_image_path,recording_company_name,number_of_tracks,PMRC_rating,length) ";
		sql += "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
		
		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, this.albumID);
			ps.setString(2, this.title);
			ps.setString(3, this.releaseDate);
			ps.setString(4, this.coverImagePath);
			ps.setString(5, this.recordingCompany);
			ps.setInt(6, this.numberOfTracks);
			ps.setString(7, this.pmrcRating);
			ps.setDouble(8, this.length);
			ps.executeUpdate();
			db.closeDbConnection();
			db = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ErrorLogger.log(e.getMessage());
		}
	}
	
    /**
     * Alternate constructor - used to create a album object using an ID to retrieve an existing record from the database
     * @param albumID - Unique Identifier for a album object in the SpotifyKnockoff Database
     */
	public Album(String albumID){
		String sql = "SELECT * FROM album WHERE album_id = '" + albumID + "';";
		
		DbUtilities db = new DbUtilities();
		try {
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()){
				this.albumID = rs.getString("album_id");
				this.title = rs.getString("title");
				this.releaseDate = rs.getString("release_date").toString();
				this.coverImagePath = rs.getString("cover_image_path").toString();
				this.recordingCompany = rs.getString("recording_company_name");
				this.numberOfTracks = rs.getInt("number_of_tracks");
				this.pmrcRating = rs.getString("PMRC_rating");
				this.length = rs.getDouble("length");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
    /**
     * Deletion of a album entry - used to delete a album object using an ID from the database
     * @param albumID - Unique Identifier for a album object in the SpotifyKnockoff Database
     */
	public static void deleteAlbum(String albumID) {
		String sql = "DELETE FROM album WHERE album_id = '" + albumID + "';";
		
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
     * Creates a relationship between a album and song objects in the album song table in the database
     * @param song - song object from the database
     */
	public void addSong(Song song) {
		albumSongs = new Hashtable<>(); 
		albumSongs.put(song.getSongID(), song);
		String sql = "INSERT INTO album_song (fk_album_id,fk_song_id) VALUES (?, ?);";
		
		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, this.albumID);
			ps.setString(2, song.getSongID());
			ps.executeUpdate();
			db.closeDbConnection();
			db = null; 	
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
    /**
     * Deletes a relationship between a album and song objects in the album song table in the database using the songID as the key
     * @param songID - unique identifier for a song object in the database
     */
	public void deleteSong(String songID) {
		albumSongs = new Hashtable<>(); 
		albumSongs.remove(albumID, songID);
		String sql = "DELETE FROM album_song WHERE fk_album_id = ? AND fk_song_id = ?;";
		
		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, this.albumID);
			ps.setString(2, songID);
			ps.executeUpdate();
			db.closeDbConnection();
			db = null; 	
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
    /**
     * Deletes a relationship between a album and song objects in the album song table in the database using the song object
     * @param song - song object in the database representing a row in the database
     */
	public void deleteSong(Song song) {
		albumSongs = new Hashtable<>(); 
		albumSongs.remove(albumID, song);
		String sql = "DELETE FROM album_song WHERE fk_album_id = ? AND fk_song_id = ?;";
		
		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, this.albumID);
			ps.setString(2, song.getSongID());
			ps.executeUpdate();
			db.closeDbConnection();
			db = null; 	
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Gets a value from a private variable to promote more secure code
	 * @return albumID - Unique identifier for the album object
	 */
	public String getAlbumID() {
		return albumID;
	}

	/**
	 * Gets a value from a private variable to promote more secure code
	 * @return title - title of the album
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Gets a value from a private variable to promote more secure code
	 * @return releaseDate - The date the album was released
	 */
	public String getReleaseDate() {
		return releaseDate;
	}

	/**
	 * Gets a value from a private variable to promote more secure code
	 * @return coverImagePath - where the cover image for the album is stored
	 */
	public String getCoverImagePath() {
		return coverImagePath;
	}

	/**
	 * Gets a value from a private variable to promote more secure code
	 * @return recordingCompany - Name of the company the recorded the album
	 */
	public String getRecordingCompany() {
		return recordingCompany;
	}

	/**
	 * Gets a value from a private variable to promote more secure code
	 * @return numberOfTracks - number of songs on the album
	 */
	public int getNumberOfTracks() {
		return numberOfTracks;
	}

	/**
	 * Gets a value from a private variable to promote more secure code
	 * @return pmrcRating - the maturity rating given to the album
	 */
	public String getPmrcRating() {
		return pmrcRating;
	}

	/**
	 * Gets a value from a private variable to promote more secure code
	 * @return length - length of the album
	 */
	public double getLength() {
		return length;
	}
	
	//*****************Setters start here****************
	/**
     * Sets a value in a private variable to promote more secure code
     * @param title - title of the album
     */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
     * Sets a value in a private variable to promote more secure code
     * @param releaseDate - The date the album was released
     */
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	/**
     * Sets a value in a private variable to promote more secure code
     * @param coverImagePath - where the cover image for the album is stored
     */
	public void setCoverImagePath(String coverImagePath) {
		this.coverImagePath = coverImagePath;
	}

	/**
     * Sets a value in a private variable to promote more secure code
     * @param recordingCompany - Name of the company the recorded the album
     */
	public void setRecordingCompany(String recordingCompany) {
		this.recordingCompany = recordingCompany;
	}

	/**
     * Sets a value in a private variable to promote more secure code
     * @param numberOfTracks - number of songs on the album
     */
	public void setNumberOfTracks(int numberOfTracks) {
		this.numberOfTracks = numberOfTracks;
	}

	/**
     * Sets a value in a private variable to promote more secure code
     * @param pmrcRating - the maturity rating given to the album
     */
	public void setPmrcRating(String pmrcRating) {
		this.pmrcRating = pmrcRating;
	}

	/**
     * Sets a value in a private variable to promote more secure code
     * @param length - length of the album
     */
	public void setLength(double length) {
		this.length = length;
	}

	/**
     * Sets a value in a private variable to promote more secure code
     * @param albumSongs - helps with relationship between album and song
     */
	public void setAlbumSongs(Map<String, Song> albumSongs) {
		this.albumSongs = albumSongs;
	}	
}
