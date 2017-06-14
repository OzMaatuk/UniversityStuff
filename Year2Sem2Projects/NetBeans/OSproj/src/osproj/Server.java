package osproj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @course OS
 * @Exercise Main project
 * @author Oz 305181158, 
 */

public class Server
{
    private static final int port = 2222;
    private static ServerSocket sSocket;
    private static swmPool sPool;
    private static swmPool fPool;
    private static int sNum;
    private static int fNum;
    private static int L;
    private static int catchSize;
    private static int catchFloor;
    private static cacheClass cache;
    
    private static class waitForMsg extends Thread
    {
        private static Socket clientSocket;
        private static BufferedReader clientReader;
        
        public waitForMsg(Socket newClientSocket)
        {
            try 
            {
                clientSocket = newClientSocket;
                InputStreamReader isReader = new InputStreamReader(clientSocket.getInputStream());
                clientReader = new BufferedReader(isReader);
                run();
            }
            catch (Exception ex) 
            {
                System.out.println("Unexpected searcher error... \n");
            }
        }
        
        @Override
        public void run()
        {
            String reqStr = "";
            try {
                // wait for msg from client
                reqStr = clientReader.readLine();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Lost a connection with " + "..." + "\n");
            }

            int reqNum = Integer.parseInt(reqStr);

            try {
                // sends to search threadpool with client socket and the requested number
                sPool.execute(new searcher(clientSocket, reqNum, cache, fPool));
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Lost a connection with " + "..." + "\n");
            }   catch (InterruptedException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Lost a connection with " + "..." + "\n");
                }
        }
    }
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String args[]) throws IOException
    {
        try 
        {
            // gets the args, init all pools and catch settings
            // number of searche thread
            sNum = Integer.parseInt(args[0]);
            // number of databade thread
            fNum = Integer.parseInt(args[1]);
            // answares range
            L = Integer.parseInt(args[2]);
            // cache size
            catchSize = Integer.parseInt(args[3]);
            // cache min
            catchFloor = Integer.parseInt(args[4]);
            // pool for searchers
            sPool = new swmPool(sNum);
            // pool for database threads
            fPool = new swmPool(fNum);
            // cache class
            cache = new cacheClass(sNum, catchSize, catchFloor, fPool);
            
            // opening socket on port
            sSocket = new ServerSocket(port);
            while (true) 
            {
                // accepting client
                Socket clientSocket = sSocket.accept();
                // lunch waitformsg thread
                new waitForMsg(clientSocket).start();
            }
        }
        catch (Exception ex)
        {
            System.out.println("Connection failed");
        }
    }
}