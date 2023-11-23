package Computer_Network_HW02;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class FileSender {
    public static void main(String[] args) {
    	
        DatagramSocket socket = null;
        FileInputStream fileInputStream = null;
        DatagramPacket outPacket = null;
        byte[] outBuf;

        try {
            // 파일을 읽어 바이트 배열로 변환
            File file = new File("/Users/hamgyubin/Desktop/100kb_dummy.txt");  // 전송할 파일 경로 지정
            fileInputStream = new FileInputStream(file);
            outBuf = new byte[(int) file.length()];
            fileInputStream.read(outBuf);

            // 수신자의 주소와 포트 지정
            InetAddress address = InetAddress.getByName("127.0.0.1");  // 수신자의 IP 주소
            int port = 3000;  // 수신자가 바인딩한 포트 번호

            // 소켓 생성
            socket = new DatagramSocket();

            // UDP 패킷 생성 및 전송
            outPacket = new DatagramPacket(outBuf, outBuf.length, address, port);
            socket.send(outPacket);

            System.out.println("파일 전송 완료.");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}