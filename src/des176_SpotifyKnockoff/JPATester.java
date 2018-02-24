package des176_SpotifyKnockoff;

public class JPATester {

	public static void main(String[] args) {
		
		Song s1 = new Song();
//		Song s2 = new Song("Say a little prayer", 3.74, "1971-10-08", "1971-05-07");
//		// Song s3 = new Song("Stairway to Heaven", 8.02, "1971-10-08", "1971-05-07");
//		Song s4 = new Song(s1.getSongID());
//		
//		// Artist(String firstName, String lastName, String bandName, String bio)
//		Artist art = new Artist("Bob", "Dylan", "Bob Dylan", "He was a guy");
		Artist art1 = new Artist();
//		Artist art2 = new Artist(art.getArtistID());
//
//		 // Album(String title, String releaseDate, String coverImagePath, String recordingCompany, int numberOfTracks, String pmrcRating, int length)
//		Album album1 = new Album("Darkside of the Moon", "1971-10-08", "//something/something/Darkside", "A recording company", 9, "Mature", 80);
		Album album2 = new Album();
		
		Song.createSong(s1);
		Song.updateSong(s1);
		Song.deleteSong(s1);
		
		Artist.createArtist(art1);
		Artist.updateArtist(art1);
		Artist.deleteArtist(art1);

		Album.createAlbum(album2);
		Album.updateAlbum(album2);
		Album.deleteAlbum(album2);
	}
}
