package des176_SpotifyKnockoff;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Spotify {
	private static Spotify spotify;
	
	private Spotify() {
		
	}
	
	public static Spotify getInstance() {
		if(spotify == null) {
			spotify = new Spotify();
		}
		return spotify;
	}
	
	/**
	 * Passes in a search term from the user to be used to query the database for a data set containing the search term
	 * @param searchTerm - the search criteria that is being used by the user
	 */
	static DefaultTableModel getSongData(String searchTerm){
		String sql = "SELECT song_id, title, length, release_date, record_date FROM song ";
		if(!searchTerm.equals("")){
				sql += " WHERE title LIKE '%" + searchTerm + "%';";
		}
		
		try {
			DbUtilities db = new DbUtilities();
			String[] columnNames = {"Song ID", "Title", "Length", "Release Date", "Record Date"};
			return db.getDataTable(sql, columnNames);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			JOptionPane.showInputDialog("Unable to connect to database");
			ErrorLogger.log(e.getMessage());
		}
		return null;
	}
	
	/**
	 * Passes in a search term from the user to be used to query the database for a data set containing the search term
	 * @param searchTerm - the search criteria that is being used by the user
	 */
	static DefaultTableModel getAlbumData(String searchTerm){
		String sql = "SELECT album_id, title, release_date, cover_image_path, recording_company_name, number_of_tracks, PMRC_rating, length FROM album ";
		if(!searchTerm.equals("")){
				sql += " WHERE title LIKE '%" + searchTerm + "%';";
		}

		try {
			DbUtilities db = new DbUtilities();
			String[] columnNames = {"Album ID", "Title", "Release Date", "Cover Image Path", 
					"Recording Company", "Number of Tracks", "PMRC Rating", "Length"};
			return db.getDataTable(sql, columnNames);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			JOptionPane.showInputDialog("Unable to connect to database");
			ErrorLogger.log(e.getMessage());
		}
		return null;
	}
	
	/**
	 * Passes in a search term from the user to be used to query the database for a data set containing the search term
	 * @param searchTerm - the search criteria that is being used by the user
	 */
	static DefaultTableModel getArtistData(String searchTerm){
		String sql = "SELECT artist_id, first_name, last_name, band_name, bio FROM artist ";
		if((!searchTerm.equals("")) && (SpotifyGUI.getComboBox().getSelectedItem() == "First Name")){
				sql += " WHERE first_name LIKE '%" + searchTerm + "%';";
		}
		
		if((!searchTerm.equals("")) && (SpotifyGUI.getComboBox().getSelectedItem() == "Last Name")){
			sql += " WHERE last_name LIKE '%" + searchTerm + "%';";
		}
		
		if((!searchTerm.equals("")) && (SpotifyGUI.getComboBox().getSelectedItem() == "Band Name")){
			sql += " WHERE band_name LIKE '%" + searchTerm + "%';";
		}

		try {
			DbUtilities db = new DbUtilities();
			String[] columnNames = {"Artist Id", "First Name", "Last Name", "Band Name", "bio"};
			return db.getDataTable(sql, columnNames);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			JOptionPane.showInputDialog("Unable to connect to database");
			ErrorLogger.log(e.getMessage());
		}
		return null;
	}
}
