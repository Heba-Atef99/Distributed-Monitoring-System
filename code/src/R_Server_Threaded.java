import java.net.*;
import java.io.*;

public class R_Server_Threaded {
    public static void main(String[] args){
        try {
            ServerSocket RS = new ServerSocket(4321);

            while (true) {
                Socket s = RS.accept();
                System.out.println("Neighborhood Computer Client Accepted...");

                client_Handler1 c_h = new client_Handler1(RS, s);
                Thread t = new Thread(c_h);
                t.start();
            }

        } 
        catch (IOException ex) {
            //TODO: handle exception
            System.out.println(ex.getMessage());
        }
        
    }
}


class client_Handler1 implements Runnable{

    ServerSocket RS;
    Socket s;
    public client_Handler1(ServerSocket s1, Socket s2) {
        RS = s1;
        s = s2;
    }

    @Override
    public void run(){
        try
        {
            String recieved_request = null;

            while (true) {
                DataOutputStream data_out_s = new DataOutputStream(s.getOutputStream());
                DataInputStream data_in_s = new DataInputStream(s.getInputStream());
    
                recieved_request = data_in_s.readUTF();
                if(recieved_request.equals("Kindly send the analysis of the traffic of this neighborhood based on the data from sensors and camera."))
                {
                    data_out_s.writeUTF("Analysis information");
                    data_out_s.flush();
                    System.out.println("Analysis information");
    
                    data_in_s.close();
                    data_out_s.close();
                    s.close();
    
                    break;
                }    
            }

            System.out.println("Close connection...");
            RS.close();

        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}

