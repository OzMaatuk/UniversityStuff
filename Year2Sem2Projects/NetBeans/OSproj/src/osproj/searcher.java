package osproj;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @course OS
 * @Exercise Main project
 * @author Oz 305181158, 
 */
public class searcher implements Runnable
{
    private Socket clientSocket;
    private PrintWriter clientWriter;
    private cacheObj reqObj;
    private cacheClass cacheC;
    private swmPool DBPool;
    
    public searcher(Socket newClientSocket, int reqNum, cacheClass newCacheC, swmPool newDBPool) throws IOException
    {
        try 
            {
                clientSocket = newClientSocket;
                clientWriter = new PrintWriter(clientSocket.getOutputStream());
                reqObj = new cacheObj("read", reqNum);
                this.cacheC = newCacheC;
                this.DBPool = newDBPool;
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
        // set activity for searching
        reqObj.setAct("SEARCHE");
        cacheC.getAns(reqObj);
        
        // while there is no change in object activity
        while (reqObj.getAct().equals("SEARCHE"))
        {}
        
        // if not found in cache
        if (reqObj.getAct().equals("NOTINCACHE"))
        {
            try
            {
                // sends fitted runnable to DBpool
                // fix what file to send
                DBPool.execute(new fileThread(reqObj, null));
            }
            catch (InterruptedException ex)
            {
                Logger.getLogger(searcher.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        // wait for answare
        while (!reqObj.getAct().equals("DONE"))
        {
            // sends back answare
            clientWriter.print("answare " + reqObj.getAns() + "for request " + reqObj.getReq());
        }
    }
}
