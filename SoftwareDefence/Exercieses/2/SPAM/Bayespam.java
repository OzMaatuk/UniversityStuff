/*
	Spam filter using a naive Bayes text classifier.

	
	The program is incomplete, it only gives access
	to the data and accumulates the vocabulary together
	with the word counts per class.

	The  students should complete the program as
	a practical assignment.
*/

import java.io.*;
import java.util.*;
import java.lang.*;

public class Bayespam
{
	// This a class with two counters (for regular and for spam)
	static class Multiple_Counter
	{
		int counterRegular = 0;
		int counterSpam    = 0;
	}


	public static void main(String[] args)
	throws IOException
	{
		// Location of the directory (the path) taken from the cmd line (first arg)
		File dir_location      = new File( args[0] );
		
		// Listing of the directory (should contain 2 subdirectories: regular/ and spam/)
		File[] dir_listing     = new File[0];

		// Check if the cmd line arg is a directory and list it
		if ( dir_location.isDirectory() )
		{
			dir_listing = dir_location.listFiles();
		}
		else
		{
			System.out.println( "- Error: cmd line arg not a directory.\n" );
		        Runtime.getRuntime().exit(0);
		}
		
		// Listings of the two subdirectories (regular/ and spam/)
		File[] listing_regular = new File[0];
		File[] listing_spam    = new File[0];
		
		// Check that there are 2 subdirectories
		if ( dir_listing.length == 2 )
		{
			listing_regular = dir_listing[0].listFiles();
			listing_spam    = dir_listing[1].listFiles();
		}
		else
		{
			System.out.println( "- Error: specified directory does not contain two subdirectories.\n" );
		        Runtime.getRuntime().exit(0);
		}

		// Print out the number of messages in regular and in spam
		System.out.println( "\t number of regular messages is: " + listing_regular.length );
		System.out.println( "\t number of spam messages is: "    + listing_spam.length );
		
		// Create a hash table for the vocabulary (word searching is very fast in a hash table)
		Hashtable vocab = new Hashtable();
		Multiple_Counter old_cnt   = new Multiple_Counter();

		// Read the e-mail messages
		// The regular mail
		for ( int i = 0; i < listing_regular.length; i ++ )
		{
			FileInputStream i_s = new FileInputStream( listing_regular[i] );
			BufferedReader in = new BufferedReader(new InputStreamReader(i_s));
	                String line;
			String word;
			
        	        while ((line = in.readLine()) != null)					// read a line
			{
				StringTokenizer st = new StringTokenizer(line);			// parse it into words
		
				while (st.hasMoreTokens())
				{
					word = st.nextToken();
					
					if ( vocab.containsKey(word) )				// check if word exists already in the vocabulary
					{
						old_cnt = (Multiple_Counter)vocab.get(word);	// get the counter from the hashtable
						old_cnt.counterRegular ++;			// and increment it
					
						vocab.put(word, old_cnt);
					}
					else
					{
						Multiple_Counter fresh_cnt = new Multiple_Counter();
						fresh_cnt.counterRegular = 1;
						fresh_cnt.counterSpam    = 0;
						
						vocab.put(word, fresh_cnt);			// put the new word with its new counter into the hashtable
					}
				}
			}

                	in.close();
		}
		// The spam mail
		for ( int i = 0; i < listing_spam.length; i ++ )
		{
			FileInputStream i_s = new FileInputStream( listing_spam[i] );
			BufferedReader in = new BufferedReader(new InputStreamReader(i_s));
	                String line;
			String word;
			
        	        while ((line = in.readLine()) != null)					// read a line
			{
				StringTokenizer st = new StringTokenizer(line);			// parse it into words
		
				while (st.hasMoreTokens())
				{
					word = st.nextToken();
					
					if ( vocab.containsKey(word) )				// check if word exists already in the vocabulary
					{
						old_cnt = (Multiple_Counter)vocab.get(word);	// get the counter from the hashtable
						old_cnt.counterSpam ++;			// and increment it
					
						vocab.put(word, old_cnt);
					}
					else
					{
						Multiple_Counter fresh_cnt = new Multiple_Counter();
						fresh_cnt.counterRegular = 0;
						fresh_cnt.counterSpam    = 1;
						
						vocab.put(word, fresh_cnt);			// put the new word with its new counter into the hashtable
					}
				}
			}

                	in.close();
		}
		
		// Print out the hash table
		for (Enumeration e = vocab.keys() ; e.hasMoreElements() ;)
		{	
			String word;
			
			word = (String)e.nextElement();
			old_cnt  = (Multiple_Counter)vocab.get(word);
			
			System.out.println( word + " | in regular: " + old_cnt.counterRegular + 
			                             " in spam: "    + old_cnt.counterSpam);
		}
		
		// Now all students must continue from here
		// The vocabulary must be clean: punctuation and digits must be removed, case insensitive, ...
		// A priori class probabilities must be computed from the number of regular and spam messages
		// Conditional probabilities must be computed for every word
		// Zero probabilities must be replaced by a small estimated value
		// Probabilities must be converted to logprobabilities (loglikelihoods).
		// Bayes rule must be applied on new messages, followed by argmax classification (using logprobabilities)
		// Errors must be computed on the test set and a confusion matrix must be generated
	}
}
