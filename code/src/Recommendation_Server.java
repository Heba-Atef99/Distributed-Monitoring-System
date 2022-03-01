import java.net.*;
import java.io.*;

public class Recommendation_Server {
    public static void main(String[] args) throws IOException {
        ServerSocket RS = new ServerSocket(4321);
        String recieved_request = null;

        while (true) {
            Socket s = RS.accept();
            System.out.println("Neighborhood Computer Client Accepted...");
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
}
