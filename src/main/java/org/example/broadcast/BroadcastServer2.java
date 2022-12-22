package org.example.broadcast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class BroadcastServer2 {


    public static void main(String[] args) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        DatagramSocket socket = null;
        DatagramPacket packet = null;

        try {
            socket = new DatagramSocket();
            socket.setBroadcast(true);

            InetAddress address = InetAddress.getByName("255.255.255.255");
            boolean flag = true;
            while(flag){
                System.out.print("입력 : ");
                String msg = reader.readLine();

                if(msg==null) {
                    break;
                }

                packet = new DatagramPacket(msg.getBytes(), msg.getBytes().length, address, 4445);

                socket.send(packet);
                if(msg.equalsIgnoreCase("FIN")) flag = false;
            }

            socket.close();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
