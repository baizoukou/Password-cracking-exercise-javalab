import java.io.*;
import java.util.Scanner;

public class Solver
{
	public static void main(String args[]) throws FileNotFoundException
	{	//the boolean var will be created and i will exit when i is set to true
		boolean exit = false;
		//stored_passwords are strings;
		String salt1;

		//Buffer reader to read in the file from passwords.
		BufferedReader d = new BufferedReader(new InputStreamReader(new FileInputStream("ab_passwd")));

		try {
			//We used while loop to read each line and i is set not equal to null, if null this breaks the loop and set the boolean to false.
			while ((salt1 = d.readLine()) != null && exit ==false)  {
			// let  get the salt which is the first two numbers. With Java we always start at o and go as far as number two.
			String salt = salt1.substring(0,2);
			//starting after the second number and read the rest.
			String stored_password =  salt1.substring(2);

			// iterate through all words in the dictionary
			try
			{
				BufferedReader dict_br = new BufferedReader(new InputStreamReader(new FileInputStream("dictionary")));
				String word;
				String word_encrypted;

				boolean found = false;
				//Read the dictionary line by line and pass it to the JCrypt class.
				while ((word = dict_br.readLine()) != null && found == false)
				{
					word_encrypted = JCrypt.crypt(salt, word);

					// Print the content on the console
					if (word_encrypted.equals(salt + stored_password))
					{
						found = true;
						System.out.println(word +' ' + "has been"+" found!");
					}
				}
				dict_br.close();
				}
				catch (Exception e)
				{
					System.err.println("Error: " + e.getMessage());
				}
			}
			// closing the BufferReader.
			d.close();
			//Exit.
			exit = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
