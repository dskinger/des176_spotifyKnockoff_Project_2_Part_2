package des176_SpotifyKnockoff;

public class Tester {
	public static void main(String[] args){
		// Creates some test data
		// Song(String title, int length, String releaseDate, String recordDate)
		Song s1 = new Song("Stairway to Heaven", 8.02, "1971-10-08", "1971-05-07");
		Song s2 = new Song("Say a little prayer", 3.74, "1971-10-08", "1971-05-07");
		// Song s3 = new Song("Stairway to Heaven", 8.02, "1971-10-08", "1971-05-07");
		Song s4 = new Song(s1.getSongID());
		
		// Artist(String firstName, String lastName, String bandName, String bio)
		Artist art = new Artist("Bob", "Dylan", "Bob Dylan", "He was a guy");
		Artist art2 = new Artist(art.getArtistID());

		 // Album(String title, String releaseDate, String coverImagePath, String recordingCompany, int numberOfTracks, String pmrcRating, int length)
		Album album1 = new Album("Darkside of the Moon", "1971-10-08", "//something/something/Darkside", "A recording company", 9, "Mature", 80);
		Album album2 = new Album(album1.getAlbumID());
		
		//ArrayList<Song> songList = new ArrayList<Song>();
		//Map<String, Song> songList = new Hashtable<String, Song>();

		
		
		
		// Vector<Vector<Song>> songTable = new Vector <>();
		 
		//System.out.println(s1);
//		try {
//			DbUtilities db = new DbUtilities();
//			String sql = "SELECT * FROM song;";
//			ResultSet rs = db.getResultSet(sql);
//			while(rs.next()){
//				Song s = new Song(rs.getString("song_id"),
//						rs.getString("title"),
//						rs.getDouble("length"),
//						rs.getString("release_date"),
//						rs.getString("record_date"));
//				songTable.add(s.getSongRecord());
//				// System.out.println(s.getTitle());
//			}
//			db.closeDbConnection();
//			db = null;
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			// e.printStackTrace();
//			JOptionPane.showMessageDialog(n, "Unable to connect to database");
//			ErrorLogger.log(e.getMessage());
//		}
		
		// Find a song with ID b2dc89b2-205e-40ac-be60-d9b271490458

//		Song foundSong = songList.get("0615a78a-6ba1-466f-a616-5e6b5894cb1b");
//		System.out.println(foundSong.getTitle() + "Found this");
		
		// The test methods for song
		// This will attach an artist to a song
		s1.addArtist(art);
		s2.addArtist(art);
		System.out.println("Artist/Song relationship was created");
		
		//This will delete and artist song relationship
		s1.deleteArtist(art);
		s2.deleteArtist(art.getArtistID());
		System.out.println("Artist/Song relationship was deleted");
		
		// Adds a song album relationship
		album1.addSong(s1);
		System.out.println("Album/Song relationship was created");
		
		// Delete album song relationship
		album1.deleteSong(s1);
		album1.deleteSong(s1.getSongID());
		System.out.println("Album/Song relationship was deleted");
		
		// Delete all of the created entries from the database
		Artist.deleteArtist(art.getArtistID());
		Song.deleteSong(s1.getSongID());
		Album.deleteAlbum(album1.getAlbumID());
		System.out.println("All of the entries were deleted");
		
		

		
	}
	
	
	
	
}
