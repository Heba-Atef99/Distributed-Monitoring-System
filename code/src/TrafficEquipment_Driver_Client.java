import java.net.*;
import java.io.*;

public class TrafficEquipment_Driver_Client {
    public static void main(String[] args) throws IOException{
        Socket s = new Socket("127.0.0.1",1234);
        String recieved_respond = null;

        DataOutputStream data_out_s = new DataOutputStream(s.getOutputStream());
        DataInputStream data_in_s = new DataInputStream(s.getInputStream());
        System.out.println("Driver send");

        data_out_s.writeUTF("Please, tell me what is the recommendation of the best route to take?");
        data_out_s.flush();
        System.out.println("Please, tell me what is the recommendation of the best route to take?");

        while (true) {
            recieved_respond = new String(data_in_s.readUTF());
            if(recieved_respond.equals("Hello please enter your location and destination."))
            {
                data_out_s.writeUTF("Location and destination");
                data_out_s.flush();
                System.out.println("Location and destination");
            }

            recieved_respond = new String(data_in_s.readUTF());
            if(recieved_respond.equals("Kindly, send me the data collected from the sensors, and surveillance cameras."))
            {
                System.out.println("Traffic equipment send");
                data_out_s.writeUTF("Data collected from sensors and cameras");
                data_out_s.flush();
                System.out.println("Data collected from sensors and cameras");
            }

            recieved_respond = new String(data_in_s.readUTF());
            if(recieved_respond.equals("Set of recommendations"))
            {
                System.out.println("Session ended");
                break;
            }
        }
        data_in_s.close();
        data_out_s.close();
        s.close();
    }    
}
