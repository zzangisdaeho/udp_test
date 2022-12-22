package org.example.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;

public class MulticastClient {

    public static void main(String[] args) {

        DatagramPacket packet = null;
        MulticastSocket socket = null;

        try {
            socket = new MulticastSocket(9006);
            System.out.println("클라이언트 생성.");

            // 그룹에 조인(라우터가 보냄)
            InetAddress address = InetAddress.getByName("224.128.1.5"); // 멀티 캐스트를 위한 아이피 설정
            socket.joinGroup(address);

            byte[] buf = new byte[512];

            packet = new DatagramPacket(buf, buf.length);

            boolean flag = true;
            while (flag) {
                // 패킷 수신
                socket.receive(packet);

                String msg = new String(packet.getData(), 0, packet.getLength());
                System.out.println("수신 > " + msg);

                if(msg.equalsIgnoreCase("FIN")) flag = false;
            }

//            socket.leaveGroup(socket.getRemoteSocketAddress(), socket.getNetworkInterface());
            socket.leaveGroup(address);
            socket.close();

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
