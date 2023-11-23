package Computer_Network_HW02;
import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientGUI extends JFrame implements ActionListener {
    
	private static final long serialVersionUID = 1L;
	
    private Socket socket;
    private InputStream input;
    private OutputStream output;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    private JTextArea messageArea;
    private JTextField messageField;
    private JButton sendButton;
    private JButton sendFileButton;
    
    private String saveFilePath = "/Users/hamgyubin/Desktop/computer_network/";


    public ClientGUI(String title) {
        super(title);
        
        // UI 초기화
        messageArea = new JTextArea(10, 30);
        messageArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(messageArea);
        add(scrollPane, BorderLayout.CENTER);

        messageField = new JTextField(20);
        sendFileButton = new JButton("+");
        sendButton = new JButton("전송");
        JPanel panel = new JPanel();
        panel.add(sendFileButton);
        panel.add(messageField);
        panel.add(sendButton);
        add(panel, BorderLayout.SOUTH);

        // 이벤트 리스너 등록
        messageField.addActionListener(this);
        sendButton.addActionListener(this);
        sendFileButton.addActionListener(this);

        // 서버에 연결
        try {
        	
        	// 생성과 함께 connect
        	Socket socket = new Socket("127.0.0.1", 3000);
        	
            input = socket.getInputStream();
            output = socket.getOutputStream();
            dataInputStream = new DataInputStream(input);
            dataOutputStream = new DataOutputStream(output);
            
        } catch (IOException e) {
            e.printStackTrace();
        }

     // 메시지 수신 스레드 시작
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                	try {
                		String dataType = dataInputStream.readUTF();
						
							if(dataType.equals("text")) {
								String message = dataInputStream.readUTF();
								messageArea.append("Server : " + message + "\n");
							}else if(dataType.equals("file")) {
								String fileName = dataInputStream.readUTF();
								long fileSize = dataInputStream.readLong();
								String filePath = saveFilePath + fileName;
								 
								FileOutputStream fileOutputStream = new FileOutputStream(filePath);
										
							    byte[] buffer = new byte[409600];
							    int bytesRead;
							    
							    while (fileSize > 0 && (bytesRead = input.read(buffer, 0, (int) Math.min(buffer.length, fileSize))) != -1) {
							        fileOutputStream.write(buffer, 0, bytesRead); // 파일 데이터 수신
							        fileSize -= bytesRead;
							    }
							    messageArea.append("Server send " + fileName + "\n");
							}
                	}catch (Exception e) {
                		e.printStackTrace();
					}
                }
            }
        }).start();
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == messageField || e.getSource() == sendButton) {
            String message = messageField.getText();
            messageArea.append("Client : " + message + "\n");
            try {
            	dataOutputStream.writeUTF("text");
            	dataOutputStream.writeUTF(message);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
            messageField.setText("");
        }else if(e.getSource() == sendFileButton) {
        	FileDialog fileOpen = new FileDialog(this, "파일열기", FileDialog.LOAD);
        	fileOpen.setVisible(true);
        	

        	String filePath = fileOpen.getDirectory() + fileOpen.getFile();
        	File f = new File(filePath);
        	
			try (FileInputStream fileInputStream = new FileInputStream(f)) {
				dataOutputStream.writeUTF("file");
				
				dataOutputStream.writeUTF(fileOpen.getFile());
				dataOutputStream.writeLong(f.length());
				
			    byte[] buffer = new byte[409600];
			    int bytesRead;
			    while ((bytesRead = fileInputStream.read(buffer)) != -1) {
			        output.write(buffer, 0, bytesRead); // 파일 데이터 전송
			    }
			    messageArea.append("Cilent send " + fileOpen.getFile() + "\n");
			}catch (Exception e2) {
				e2.printStackTrace();
			}
        }
    }
        	

    
    
    public static void main(String[] args) {
        ClientGUI client = new ClientGUI("클라이언트 메신저");
        client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.pack();
        client.setVisible(true);
    }
}