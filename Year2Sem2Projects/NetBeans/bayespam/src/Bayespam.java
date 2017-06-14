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

public class Bayespam
{
	// This a class with two counters (for regular and for spam)
	static class Multiple_Counter
	{
		int counterRegular = 0;
		int counterSpam    = 0;
                double PWreg = 0;
                double PWspam = 0;
	}

        static String improved(String str)
        {
            // for evry char in string
            for (int i = 0; i < str.length(); i++)
            {
                // if its not a small letter
                if (!((str.charAt(i) >= 'a' && str.charAt(i) <= 'z')))
                {
                    // if its upper letter
                    if (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z')
                    {
                        // make it small
                        str = str.replace(str.charAt(i), (char)(str.charAt(i)+32));
                    }
                    // if its not a space or -
                    else if (str.charAt(i) != ' ' && str.charAt(i) != '-')
                    {
                        // remove the char
                        str = str.substring(0, i) + str.substring(i+1);
                        i--;
                    }
                }
            }
            return str;
        }
        
	public static void main(String[] args)
	throws IOException
	{
		// Location of the directory (the path) taken from the cmd line (first arg)
		//File dir_location      = new File( args[0] );
                File dir_location      = new File( "spam-filter/train" );
		
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
					if (word.length() >= 4)                             // check if the word longer then 4 letters
                                        {
                                            // improve word
                                            word = improved(word);
                                            
                                            if (word.length() >= 4)
                                            {
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
					
                                        if (word.length() >= 4)                             // check if the word longer then 4 letters
                                        {
                                            // improve word
                                            word = improved(word);
                                            
                                            if (word.length() >= 4) 
                                            {
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
				}
			}

                	in.close();
		}
		
                /*
		// Print out the hash table
		for (Enumeration e = vocab.keys() ; e.hasMoreElements() ;)
		{	
			String word;
			
			word = (String)e.nextElement();
			old_cnt  = (Multiple_Counter)vocab.get(word);
			
			System.out.println( word + " | in regular: " + old_cnt.counterRegular + 
			                             " in spam: "    + old_cnt.counterSpam);
		}
		*/
                
		// Now all students must continue from here
		// The vocabulary must be clean: punctuation and digits must be removed, case insensitive, ...
		// A priori class probabilities must be computed from the number of regular and spam messages
		// Conditional probabilities must be computed for every word
		// Zero probabilities must be replaced by a small estimated value
		// Probabilities must be converted to logprobabilities (loglikelihoods).
		// Bayes rule must be applied on new messages, followed by argmax classification (using logprobabilities)
		// Errors must be computed on the test set and a confusion matrix must be generated
                
                // gets num of msg in regular dir
                double nMessagesRegular = listing_regular.length;
                // gets num of msg in spam dir
                double nMessagesSpam = listing_spam.length;
                // sum them
                double nMessagesTotal = nMessagesRegular + nMessagesSpam;
                // calc P for reg
                double Preg = nMessagesRegular/nMessagesTotal;
                // calc P for spam
                double Pspam = nMessagesSpam/nMessagesTotal;
                
                // count word in reg msgs
                int nWordsRegular = 0;
                for (Enumeration e = vocab.keys() ; e.hasMoreElements() ;)
		{	
			String word;
			
			word = (String)e.nextElement();
			old_cnt  = (Multiple_Counter)vocab.get(word);
			nWordsRegular = nWordsRegular + old_cnt.counterRegular;
		}
                
                // count word in spam msgs
                int nWordsSpam = 0;
                for (Enumeration e = vocab.keys() ; e.hasMoreElements() ;)
		{	
			String word;
			
			word = (String)e.nextElement();
			old_cnt  = (Multiple_Counter)vocab.get(word);
			nWordsSpam = nWordsSpam + old_cnt.counterSpam;
		}
                
                // calc two class conditional for every word
                double low = (double)(0.00000000001 / (nWordsRegular + nWordsSpam));      // estimated small non-zero value
                for (Enumeration e = vocab.keys() ; e.hasMoreElements() ;)
		{	
			String word;
			
			word = (String)e.nextElement();
			old_cnt  = (Multiple_Counter)vocab.get(word);
                        
                        // calc P for word in reg
                        // if its bigger then the estimated small non-zero value
                        if (((double)old_cnt.counterRegular/(double)nWordsRegular) > low)
                        {
                            old_cnt.PWreg = (double)old_cnt.counterRegular/(double)nWordsRegular;
                        }
                        else     // else put the small non-zero value
                        {
                            old_cnt.PWreg = low;
                        }
                        
                        // calc p for word in spam
                        // if its bigger then the estimated small non-zero value
                        if (((double)old_cnt.counterSpam/(double)nWordsSpam) > low)
                        {
                            old_cnt.PWspam = (double)old_cnt.counterSpam/(double)nWordsSpam;
                        }
                        else    // else put the small non-zero value
                        {
                            old_cnt.PWspam = low;
                        }
                        
		}
                
                // Print out the hash table
		for (Enumeration e = vocab.keys() ; e.hasMoreElements() ;)
		{	
			String word;
			
			word = (String)e.nextElement();
			old_cnt  = (Multiple_Counter)vocab.get(word);
			
			System.out.println( word + " | in regular: " + old_cnt.counterRegular + 
                                                    " in spam: "    + old_cnt.counterSpam + 
                                                    " P(regular): " + old_cnt.PWreg + 
                                                    " P(spam): " + old_cnt.PWspam);
		}
                // print all results
                System.out.println("nMessagesRegular: " + nMessagesRegular);
                System.out.println("nMessagesSpam: " + nMessagesSpam);
                System.out.println("nMessagesTotal: " + nMessagesTotal);
                System.out.println("Preg: " + Preg);
                System.out.println("Pspam: " + Pspam);
                System.out.println("nWordsRegular: " + nWordsRegular);
                System.out.println("nWordsSpam: " + nWordsSpam);
                
                
                
                
                
                
                // all again with the train dir
                // Location of the directory (the path) taken from the cmd line (first arg)
		//File dir_location      = new File( args[0] );
                dir_location      = new File( "spam-filter/test" );
		
		// Listing of the directory (should contain 2 subdirectories: regular/ and spam/)
		dir_listing     = new File[0];

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
		listing_regular = new File[0];
		listing_spam    = new File[0];
                String regCheckArr[] = null;
                String spamCheckArr[] = null;
		
		// Check that there are 2 subdirectories
		if ( dir_listing.length == 2 )
		{
			listing_regular = dir_listing[0].listFiles();
			listing_spam    = dir_listing[1].listFiles();
                        regCheckArr = new String[listing_regular.length];
                        spamCheckArr = new String[listing_spam.length];
		}
		else
		{
			System.out.println( "- Error: specified directory does not contain two subdirectories.\n" );
		        Runtime.getRuntime().exit(0);
		}

		// Print out the number of messages in regular and in spam
		System.out.println( "\t number of regular messages is: " + listing_regular.length );
		System.out.println( "\t number of spam messages is: "    + listing_spam.length );
                
                // Read the e-mail messages
		// The regular mail
                // and mark it in the regular array
		for ( int i = 0; i < listing_regular.length; i ++ )
		{
			FileInputStream i_s = new FileInputStream( listing_regular[i] );
			BufferedReader in = new BufferedReader(new InputStreamReader(i_s));
	                String line;
			String word;
			
                        double regPerMsg = 0;
                        double spamPerMsg = 0;
                        
        	        while ((line = in.readLine()) != null)					// read a line
			{
				StringTokenizer st = new StringTokenizer(line);			// parse it into words
		
				while (st.hasMoreTokens())
				{
					word = st.nextToken();
					
                                        if ( vocab.containsKey(word) )
                                        {
                                            old_cnt = (Multiple_Counter)vocab.get(word);
                                            regPerMsg = regPerMsg + old_cnt.PWreg;
                                            spamPerMsg = spamPerMsg + old_cnt.PWspam;
                                            //System.out.println("old_cnt.PWreg: " + old_cnt.PWreg + " , old_cnt.PWspam: " + old_cnt.PWspam);
                                        }
				}
			}

                        if (regPerMsg > spamPerMsg)
                        {
                            regCheckArr[i] = "regular";
                        }
                        else
                        {
                            regCheckArr[i] = "spam";
                        }
                        
                	in.close();
		}
                
                // The spam mail
                // and mark it in the spam array
		for ( int i = 0; i < listing_spam.length; i ++ )
		{
			FileInputStream i_s = new FileInputStream( listing_spam[i] );
			BufferedReader in = new BufferedReader(new InputStreamReader(i_s));
	                String line;
			String word;
			
                        double regPerMsg = 0;
                        double spamPerMsg = 0;
                        
        	        while ((line = in.readLine()) != null)					// read a line
			{
				StringTokenizer st = new StringTokenizer(line);			// parse it into words
		
				while (st.hasMoreTokens())
				{
					word = st.nextToken();
					
                                        if ( vocab.containsKey(word) )
                                        {
                                            // if the word in the hash, sum the P for spam and for regular
                                            old_cnt = (Multiple_Counter)vocab.get(word);
                                            regPerMsg = regPerMsg + Math.log(old_cnt.PWreg);
                                            spamPerMsg = spamPerMsg + Math.log(old_cnt.PWspam);
                                            //System.out.println("old_cnt.PWreg: " + old_cnt.PWreg + " , old_cnt.PWspam: " + old_cnt.PWspam);
                                        }
				}   
			}
                        
                        if (regPerMsg > spamPerMsg)
                        {
                            spamCheckArr[i] = "regular";
                        }
                        else
                        {
                            spamCheckArr[i] = "spam";
                        }
                        
                	in.close();
		}
                
                System.out.println("from regular training folder, results:");
                for (int i = 0; i < regCheckArr.length; i++)
                {
                    System.out.println("file num: " + i + " is calc for " + regCheckArr[i]);
                }
                System.out.println("");
                System.out.println("from spam training folder, results:");
                for (int i = 0; i < spamCheckArr.length; i++)
                {
                    System.out.println("file num: " + i + " is calc for " + spamCheckArr[i]);
                }
                
                // counting correct results
                int countRegCorrect = 0;
                for (int i = 0; i < regCheckArr.length; i++)
                {
                    if (regCheckArr[i] == "regular")
                    {
                        countRegCorrect++;
                    }
                }
                
                int countSpamCorrect = 0;
                for (int i = 0; i < spamCheckArr.length; i++)
                {
                    if (spamCheckArr[i] == "spam")
                    {
                        countSpamCorrect++;
                    }
                }
                
                // build matrix
                String mat[][] = new String[3][3];
                mat[0][0] = "       ";
                mat[1][0] = "correct";
                mat[2][0] = "wrong";
                mat[0][1] = "regular";
                mat[0][2] = "spam";
                mat[1][1] = "" + countRegCorrect;
                mat[1][2] = "" + countSpamCorrect;
                mat[2][1] = "" + (regCheckArr.length - countRegCorrect);
                mat[2][2] = "" + (spamCheckArr.length - countSpamCorrect);
                System.out.println("");
                System.out.println("Matix: ");
                System.out.println(Arrays.toString(mat[0]));
                System.out.println(Arrays.toString(mat[1]));
                System.out.println(Arrays.toString(mat[2]));
	}
}
