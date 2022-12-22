package org.example.multicast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastServer {

    public static void main(String[] args) {

        DatagramPacket packet = null;
        MulticastSocket socket = null;

        try {
            socket = new MulticastSocket();
            System.out.println("서버 생성 성공.");

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            // IPv4 아래의 내용은 이전의 포스팅을 통해 설명 해두었음.
            // A Class : 0000 (0000) => 0.0.0.0 ~ 127.255.255.255
            // B Class : 1000 (0000) => 128.0.0.0 ~ 191.255.255.255
            // C Class : 1100 (0000) => 192.0.0.0 ~ 223.255.255.255
            // D Class : 1110 (0000) => 224.0.0.0 ~ 239.255.255.255
            // E Class : 1111 (0000) => 240.0.0.0 ~ 255.255.255.255
            InetAddress address = InetAddress.getByName("224.128.1.5"); // 멀티캐스트 방식으로 서버 주소를 설정함.

            boolean flag = true;
            while(flag) {
                System.out.print("입력 : ");
                String msg = reader.readLine();

                if(msg==null) {
                    break;
                }

                packet = new DatagramPacket(msg.getBytes(), msg.getBytes().length, address, 9006);

                socket.send(packet);

                if(msg.equalsIgnoreCase("FIN")) flag = false;
            }

            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
