
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @course Computers Network 
 * @Exercise 04, Chat
 * @author Oz 305181158, Avishalom 308481423
 */

/**
* Server Class with JFrame
*/
public class Server extends javax.swing.JFrame {

    //parameters for server: array list of clients thread, server listener thread.
   private ArrayList<ListenToClient> clientsThreadsArr = null;
   private serverListener serverTh = null;
    
   /**
    * public class ListenToClient
    * @extends Thread
    * a Thread class that listening to messages on the socket from server.
    */
    public class ListenToClient extends Thread	
    {
        //all requaiers parameters for client server connection by socket
       private BufferedReader clientReader;
       private Socket clientSocket;
       private PrintWriter clientWriter;
       private String clientName = "";
       
       /**
        * public ListenToClient
        * ListenToClient constructor.
        * define all parameter for server - client connection (reader, writer)
        * @param newClientSocket - get the Socket of the new client.
        * @throws IOException 
        */
       public ListenToClient(Socket newClientSocket) throws IOException 
       {
            try 
            {
                clientSocket = newClientSocket;
                clientWriter = new PrintWriter(clientSocket.getOutputStream());
                InputStreamReader isReader = new InputStreamReader(clientSocket.getInputStream());
                clientReader = new BufferedReader(isReader);
            }
            catch (Exception ex) 
            {
                serverDialog.append("Unexpected error... \n");
            }
       }
       
       /**
        * The run method of the thread.
        * working while messages from server can be accepted,
        * accepting message and sends it to the proper function to handle (by the first command in the message)
        */
       @Override
       public void run() 
       {
            //parameters for spliting the message and puttig into array
            String[] data;
            String msg = "";
            try 
            {
                while ((msg = clientReader.readLine()) != null)
                {
                    data = msg.split(":");
                    
                    synchronized(clientsThreadsArr)
                    {
                        if (data[0].equals("newUser"))
                        {
                            addUser(data[1], clientWriter);
                            clientName = data[1];
                            clientsThreadsArr.add(this);
                        }

                        if (data[0].equals("msg"))
                        {
                            //merging the rest of the message (all message with ':' char after the commands)
                            String newMsg = "";
                            for (int i = 3; i < data.length; i++) {
                                if (i != 3) {
                                    newMsg += ":";
                                }
                            newMsg += data[i];
                            }
                            
                            recievedMsg(data[1], data[2], newMsg);
                        }

                        if (data[0].equals("disconnected")) 
                        {
                            userDis(data[1]);
                        }
                    }
                    clientWriter.flush();
                    serverDialog.setCaretPosition(serverDialog.getDocument().getLength());
                } 
             } 
             catch (Exception ex)
             {
                serverDialog.append("Lost a connection with " + clientName + "\n");
                userDis(clientName);
                this.interrupt();
             } 
	} 
    }
    
    /**
    * private void sendBroadcast
    * sends a message to all users
    * @param msg - - message to send.
    * @param from - - name of the sender.
    */
    private void addUser(String userName, PrintWriter clientWriter) 
    {
        //checking if the name already exist
        boolean nameToken = false;
        if (clientsThreadsArr != null)
        {
            for (ListenToClient curClient : clientsThreadsArr)
            {
                if (curClient.clientName.equals(userName))
                {
                    nameToken = true;
                }
            }
        }

        //if name is token, else add the new client to clients threads array and combobox
        if (nameToken)
        {
            clientWriter.println("nameToken:");
        }
        else
        {
            clientWriter.println("connected:");
            serverDialog.append(userName + " is connected \n");

            if (clientsThreadsArr != null)
            {
                for (ListenToClient curClient : clientsThreadsArr)
                {
                    curClient.clientWriter.println("newUser:" + userName);
                    curClient.clientWriter.flush();
                    clientWriter.println("newUser:" + curClient.clientName);
                }
            }
        }
    }
    
    /**
    * private void recievedMsg
    * pass a message to the correct user
    * @param msg - - message to send.
    * @param from - - name of the sender.
    * @param to - - name of user to accept the message
    */
    private void recievedMsg(String from, String to, String msg) 
    {
        serverDialog.append("Received message from " + from + " to " + to + ": " + msg + "\n");
        
        if (to.equals("Broadcast")) 
        {
            sendBroadcast(msg, from);
        }
        else
        {
            for (ListenToClient curClient : clientsThreadsArr)
            {
                if (curClient.clientName.equals(to))
                {
                    curClient.clientWriter.println("msg:" + from + ":" + msg + "(private message)");
                    curClient.clientWriter.flush();
                }
            }
        }
    }
    
    /**
    * private void userDis
    * remove a disconnected user from clients thread array
    * @param userName - - the user name to remove
    */
    private void userDis(String userName) 
    {
        serverDialog.append(userName + " is disconnected \n");

        //remove the user from clients thread array
        if (clientsThreadsArr != null)
        {
            int i = 0;
            for (ListenToClient curClient : clientsThreadsArr)
            {
                if (curClient.clientName.equals(userName))
                {
                    curClient.interrupt();
                    clientsThreadsArr.remove(i);
                }
                else
                {
                    i++;
                }
            }
        }

        //sends to all clients message about user disconnected
        if (clientsThreadsArr != null) {
            for (ListenToClient curClient : clientsThreadsArr) {
                curClient.clientWriter.println("removeUser:" + userName);
                curClient.clientWriter.flush();
            }
        }
    }
    
    /**
    * private void sendBroadcast
    * sends a message to all users
    * @param msg - - message to send.
    * @param from - - name of the sender.
    */
    private void sendBroadcast(String msg, String from) 
    {
        serverDialog.append("Sending Broadcast: " + msg + "\n");
        if (clientsThreadsArr != null)
        {
            for (ListenToClient curClient : clientsThreadsArr) 
            {
                if (!curClient.clientName.equals(from))
                {
                    try 
                    {
                        PrintWriter writer = curClient.clientWriter;
                        writer.println("msg:" + from + ":" + msg);
                        writer.flush();
                    } 
                    catch (Exception ex) 
                    {
                        serverDialog.append("Error sending Broadcast. \n");
                    }
                }
            }
        }
    }
    
    /**
     * public Client()
     * GUI JFrame default Constructor
     */
    public Server() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        startBut = new javax.swing.JButton();
        stopBut = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        serverDialog = new javax.swing.JTextArea();
        clearBut = new javax.swing.JButton();
        OnUsersBut = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        startBut.setText("START");
        startBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButActionPerformed(evt);
            }
        });

        stopBut.setText("STOP");
        stopBut.setEnabled(false);
        stopBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButActionPerformed(evt);
            }
        });

        serverDialog.setEditable(false);
        serverDialog.setColumns(20);
        serverDialog.setRows(5);
        jScrollPane1.setViewportView(serverDialog);

        clearBut.setText("CLEAR");
        clearBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButActionPerformed(evt);
            }
        });

        OnUsersBut.setText("Online Users");
        OnUsersBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OnUsersButActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(startBut)
                        .addGap(18, 18, 18)
                        .addComponent(stopBut, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(clearBut)
                        .addGap(18, 18, 18)
                        .addComponent(OnUsersBut)
                        .addGap(0, 38, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 41, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(clearBut)
                            .addComponent(stopBut)
                            .addComponent(startBut)
                            .addComponent(OnUsersBut))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
    * private void clearButActionPerformed
    * clear the server text area
    */
    private void clearButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButActionPerformed
        // TODO add your handling code here:
        serverDialog.setText("");
    }//GEN-LAST:event_clearButActionPerformed
    
    /**
    * private void stopButActionPerformed
    * activate when clicking start button
    * prints message about starting the server,
    * and activating the server listener thread.
    */
    private void startButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButActionPerformed
        // TODO add your handling code here:
        serverDialog.append("Server is started \n");
        serverTh = new serverListener();
        serverTh.start();
        stopBut.setEnabled(true);
        startBut.setEnabled(false);
    }//GEN-LAST:event_startButActionPerformed

    /**
    * private void formWindowClosing
    * activate when X button is clicked by user
    * activating private void stopButActionPerformed to stop server
    */
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        stopButActionPerformed(null);
    }//GEN-LAST:event_formWindowClosing

    /**
    * private void stopButActionPerformed
    * activate when clicking stop button
    * prints and sends to clients message about stopping the server,
    * interrupting the server listen thread and the clients thread array
    */
    private void stopButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButActionPerformed
        // TODO add your handling code here:
        serverDialog.append("Server stopping... \n");
        sendBroadcast("serverOff:", "server");
        
        try 
        {
            Thread.sleep(3000);
        } 
        catch(InterruptedException ex) {Thread.currentThread().interrupt();}
        
        if (clientsThreadsArr != null)
        {
            for (ListenToClient curClient : clientsThreadsArr)
            {
                curClient.clientWriter.flush();
                curClient.interrupt();
            }
        }
        
        serverTh.interrupt();
        
        clientsThreadsArr = null;
        serverDialog.setText("");
        serverDialog.append("server stopped. \n");
        startBut.setEnabled(true);
        stopBut.setEnabled(false);
    }//GEN-LAST:event_stopButActionPerformed
    
    /**
    * private void OnUsersButActionPerformed
    * activate when online clients button is clicked
    * print to the server text area all names of clients from the clients array
    */
    private void OnUsersButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OnUsersButActionPerformed
        // TODO add your handling code here:
        serverDialog.append("\n Online users : \n");
        for (ListenToClient curClient : clientsThreadsArr)
        {
            serverDialog.append(curClient.clientName);
            serverDialog.append("\n");
        }
    }//GEN-LAST:event_OnUsersButActionPerformed
    
    /**
     * public class serverListener
     * @extends Thread
     * defining server socket, and clients array.
     * running in background and listening to accept messages about clients,
     * when a client is accepted, it makes a new client object for him, start it and add it to the clients array. 
     */
    public class serverListener extends Thread
    {
        @Override
        public void run() 
        {
            clientsThreadsArr = new ArrayList(); 
            try 
            {
                ServerSocket serverSocket = new ServerSocket(2222);
                while (true) 
                {
				Socket clientSocket = serverSocket.accept();
                                ListenToClient newListenerToClient = new ListenToClient(clientSocket);
                                newListenerToClient.start();
                                serverDialog.append("There is a new connection \n");
                }
            }
            catch (Exception ex)
            {
                serverDialog.append("\n Error making a connection. \n");
            }
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Server().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton OnUsersBut;
    private javax.swing.JButton clearBut;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea serverDialog;
    private javax.swing.JButton startBut;
    private javax.swing.JButton stopBut;
    // End of variables declaration//GEN-END:variables
}
