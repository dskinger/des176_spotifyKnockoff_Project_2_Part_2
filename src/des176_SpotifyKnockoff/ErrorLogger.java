package des176_SpotifyKnockoff;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class ErrorLogger {
	public static void log(String errorMessage) {
		// 
		
		// Date, Time, errorMessage \n
		
		// I stole this from https://stackoverflow.com/questions/1625234/how-to-append-text-to-an-existing-file-in-java
		//Changed the names and added the time/Date
	
	try(FileWriter fw = new FileWriter("errorlog.txt", true);
		    BufferedWriter bw = new BufferedWriter(fw);
		    PrintWriter out = new PrintWriter(bw))
		{
		    out.println(LocalDateTime.now() + " " + errorMessage);
		} catch (IOException e) {
		    //exception handling left as an exercise for the reader
		}
	}
}
	
