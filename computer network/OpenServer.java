package Computer_Network_HW02;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class OpenServer {
	
	public static double calcString(String s) {
		
		char op = 0;
		
		for(int i=0;i<s.length();i++){
			if(s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '*' || s.charAt(i) == '/') {
				op = s.charAt(i);
				break;
			}
		}
		
		String[] strs = s.split("\\"+op);
		
		double arg1 = Double.parseDouble(strs[0].trim());
		double arg2 = Double.parseDouble(strs[1].trim());
		double result;
		
		if(op=='+') result = arg1+arg2;
		else if(op=='-')	result = arg1-arg2;
		else if(op=='*')	result = arg1*arg2;
		else	result = arg1/arg2;
						
		return result;
	}
	
	public static void main(String[] args) {
		
		
		ServerSocket serverSocket;
		Socket socket;
	    BufferedReader input = null;
	    PrintWriter output = null;
	    
	    
		
		 // 서버 활성화 & 대기
        try {
        	System.out.println("서버 실행...Client 연결중...");
            serverSocket = new ServerSocket(3000);
            
            socket = serverSocket.accept();
            System.out.println("Client 연결됨.");
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 메시지 수신
        while (true) {
            try {
                String message = input.readLine();
                if (message != null) {
                	double a = calcString(message);
                	output.printf("%.4f\n", a);
                }
            }catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}