package readfiles;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class AutoClose {
	public static void main(String[] args) {
		/*
		 * TRY - WITH RESOURCES.
		 * WE USE PARANTHESIS AFTER TRY KEYWORD AND OPEN FILES 
		 * THERE. IT WILL CLOSE THE OPENED FILES AUTOMATICALLY AT THE END.
		 * 
		 */
		try (FileReader fr = new FileReader("Numbers.txt"); 
			 BufferedReader br = new BufferedReader(fr);) 
		{
			String value;
			while ((value = br.readLine()) != null) {
				System.out.println(value);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
