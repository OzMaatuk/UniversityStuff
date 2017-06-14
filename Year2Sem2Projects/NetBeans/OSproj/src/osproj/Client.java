package osproj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @course OS
 * @Exercise Main project
 * @author Oz 305181158, 
 */

public class Client
{    
    //Parameters for client: Down range integer, Up range integer, text file name, Setver IP Address, Server Port to connect.
    private static String serverIP = "localhost";
    private static int port = 2222;
    private static int R1 = 0;
    private static int R2 = 0;
    private static int requestNum = 0;
    private static final int range = 1000;
    
    //Parameters for client connection: Socket with server, Writer to server, Reader from server, Listener to server bt Thread.
    private static Socket clientSocket;
    private static BufferedReader clientReader;
    private static PrintWriter clientWriter;
    
    private static void readStatisticsFile(String fileName, int statisticsArr[]) throws FileNotFoundException, IOException
    {
        // create file conteiner and reader
        File statisticFile = new File(fileName);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(statisticFile));
        
        // read the line from the file
        String input = bufferedReader.readLine();
        
        // parse references from the input line
        StringTokenizer st = new StringTokenizer(input, ",");
        
        // init R1, R2
        R1 = Integer.parseInt(st.nextToken());
        R2 = Integer.parseInt(st.nextToken());
        
        // init array
        int cur = 0;
        while (st.hasMoreTokens())
        {
            int offset = 0;
            for (int i = 0; i < (Integer.parseInt(st.nextToken()) * range); i++)
            {
                statisticsArr[cur] = R1 + offset;
                cur++;
            }
            offset++;
        }
    }
    
    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        // init parameters
        String fileName = args[0];
        int statisticsArr[] = new int[range];
        
        // get name for client
        Scanner sc = new Scanner(System.in);
        String clientName = sc.next();
        
        // read statistics file and init statistic array
        readStatisticsFile(fileName, statisticsArr);
        
        // connection
        try 
        {
            // open sockect connection between client and server, sets reader and writer.
            clientSocket = new Socket(serverIP, port);
            InputStreamReader streamReader = new InputStreamReader(clientSocket.getInputStream());
            clientReader = new BufferedReader(streamReader);
            clientWriter = new PrintWriter(clientSocket.getOutputStream());
        }
        catch (Exception ex) 
        {
            System.out.println("connection failed");
            return;
        }
        
        // while there is connection with server
        while (clientSocket.isConnected())
        {
            // define answare
            String ans = "";
            
            // choose number randomly
            Random rand = new Random();
            requestNum = rand.nextInt(R2-R1) + R1;

            // sends request to server
            clientWriter.println(requestNum);
            clientWriter.flush();

            // prints...
            System.out.println("Client " + clientName + ": sending " + requestNum);

            try {
                // gets answare from server
                ans = clientReader.readLine();

                if (ans == null)
                {
                    System.out.println("Server off");
                }
                else
                {
                    System.out.println("Client " + clientName + ": got reply " + ans + " for query: " + requestNum);
                }
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        //disconnect - closing sockects
        clientWriter.close();
        clientReader.close();
        clientSocket.close();
    }
}
