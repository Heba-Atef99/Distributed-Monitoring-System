import java.net.*;
import java.io.*;

public class Neighborhood_Computer_Intermediate {
    public static void main(String[] args) throws IOException{
        boolean flag = true;
        ServerSocket NCS = new ServerSocket(1234);
        String recieved_request = null;

        while (flag) {
            Socket s1 = NCS.accept();
            System.out.println("Driver client Accepted...");
            DataOutputStream data_out_s1 = new DataOutputStream(s1.getOutputStream());
            DataInputStream data_in_s1 = new DataInputStream(s1.getInputStream());

            recieved_request = new String(data_in_s1.readUTF());
            if(recieved_request.equals("Please, tell me what is the recommendation of the best route to take?"))
            {
                data_out_s1.writeUTF("Hello please enter your location and destination.");
                data_out_s1.flush();
                System.out.println("Hello please enter your location and destination.");
            }

            recieved_request = new String(data_in_s1.readUTF());
            if(recieved_request.equals("Location and destination"))
            {
                data_out_s1.writeUTF("Kindly, send me the data collected from the sensors, and surveillance cameras.");
                data_out_s1.flush();
                System.out.println("Kindly, send me the data collected from the sensors, and surveillance cameras.");
            }

            recieved_request = new String(data_in_s1.readUTF());
            if(recieved_request.equals("Data collected from sensors and cameras"))
            {
                Socket s = new Socket("127.0.0.1",4321);
            
                DataOutputStream data_out_s2 = new DataOutputStream(s.getOutputStream());
                DataInputStream data_in_s2 = new DataInputStream(s.getInputStream());
                data_out_s2.writeUTF("Kindly send the analysis of the traffic of this neighborhood based on the data from sensors and camera.");
                data_out_s2.flush();
                System.out.println("Kindly send the analysis of the traffic of this neighborhood based on the data from sensors and camera.");

                String recieved_respond2 = null;
                while (true) {
                    recieved_respond2 = data_in_s2.readUTF();
                    if(recieved_respond2.equals("Analysis information"))
                    {
                        System.out.println("Close connection with Recommendation server..");
                        flag = false;
                        data_in_s2.close();
                        data_out_s2.close();
                        s.close();
                        break;
                    }
                }
        
                data_out_s1.writeUTF("Set of recommendations");
                data_out_s1.flush();
                System.out.println("Set of recommendations");
            }

            data_in_s1.close();
            data_out_s1.close();
            s1.close();
        }
        System.out.println("Close connection with Client..");
        NCS.close();
    }    
}
