package org.example.broadcast;

import java.io.IOException;
import java.net.*;

public class BroadcastClient {


    public static void main(String[] args) {

        DatagramSocket socket = null;
        DatagramPacket packet = null;

        try {
            socket = new DatagramSocket(4445);
            System.out.println("클라이언트 생성.");
            InetAddress address = InetAddress.getByName("255.255.255.255");

            byte[] buffer = new byte[512];

//            packet = new DatagramPacket(buffer, buffer.length, address, 4445);
            packet = new DatagramPacket(buffer, buffer.length);


            boolean flag = true;

            while(flag){
                socket.receive(packet);

                String msg = new String(packet.getData(), 0, packet.getLength());
                System.out.println("수신 > " + msg);

                if(msg.equalsIgnoreCase("FIN")) flag = false;
            }

            socket.close();





        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


}
