package APIs;


import Entity.Message;
import Entity.User;
import IService.IMessageService;
import Service.MessageService;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatServer {

    /* Setting up variables */
    private static final int PORT = 9001;
    private static final HashMap<User,ObjectOutputStream> usersSessions = new HashMap<>();
    private static final IMessageService messageService = new MessageService();

    public static void main(String[] args) throws Exception {
        ServerSocket listener = new ServerSocket(PORT);

        try {
            while (true) {
                new Handler(listener.accept()).start();
            }
        } catch (IOException ex) {
            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            listener.close();
        }
    }


    private static class Handler extends Thread {
        private String name;
        private final Socket socket;
        private User user;
        private ObjectInputStream input;
        private OutputStream os;
        private ObjectOutputStream output;
        private InputStream is;
        
        public Handler(Socket socket) throws IOException {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                is = socket.getInputStream();
                input = new ObjectInputStream(is);
                os = socket.getOutputStream();
                output = new ObjectOutputStream(os);
                user = (User) input.readObject();
                usersSessions.put(user, output);

                while (socket.isConnected()) {
                    Message inputmsg = (Message) input.readObject();
                    if (inputmsg != null) {
                        messageService.insertMessage(inputmsg);
                        write(inputmsg);
                    }
                }
            } catch (SocketException ex) {
                
            } catch (IOException | ClassNotFoundException ex){
                
            } finally {
                closeConnections();
            }
        }
        
        private void write(Message message) throws IOException {
            ObjectOutputStream writer = usersSessions.get(message.getReceiver());
            if(writer != null)
            {
                writer.writeObject(message);
                writer.reset();
                return;
            }
            NotificationApi.createMessageNotification(message);
        }       
        private synchronized void closeConnections()  {
            
            if (output != null){
                usersSessions.remove(user);
                try {
                    output.close();
                } catch (IOException ex) {
                    Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (is != null){
                try {
                    is.close();
                } catch (IOException ex) {
                    Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (os != null){
                try {
                    os.close();
                } catch (IOException ex) {
                    Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (input != null){
                try {
                    input.close();
                } catch (IOException ex) {
                    Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
