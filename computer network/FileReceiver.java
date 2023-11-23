package Computer_Network_HW02;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class FileReceiver {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        DatagramPacket inPacket = null;
        byte[] inBuf = new byte[65507];  // UDP 패킷의 최대 크기
        FileOutputStream fileOutputStream = null;

        try {
            // 소켓 생성 및 바인딩
            socket = new DatagramSocket(3000);  // 수신자가 바인딩한 포트 번호

            // 파일 수신
            inPacket = new DatagramPacket(inBuf, inBuf.length);
            socket.receive(inPacket);

            // 바이트 배열을 파일로 저장
            fileOutputStream = new FileOutputStream("/Users/hamgyubin/Desktop/computer_network_UDP/1kb_dummy.txt");  // 저장할 파일 경로 지정
            fileOutputStream.write(inPacket.getData(), 0, inPacket.getLength());

            System.out.println("파일 수신 완료.");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
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