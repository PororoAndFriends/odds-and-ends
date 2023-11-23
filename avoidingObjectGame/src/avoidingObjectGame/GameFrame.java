package avoidingObjectGame;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class GameFrame extends JFrame {
	
	Random rand = new Random();
	
	ImageIcon marioI = new ImageIcon("/Users/hamgyubin/eclipse-workspace/avoidingObjectGame/mario.png");
	JLabel mario = imageSetSize(marioI, 100, 100);
	JProgressBar progress = new JProgressBar(0, 100);
	ArrayList<JLabel> ghostList = new ArrayList<>();
	
	
	public GameFrame(String title) {
		super(title);
		
		Dimension res = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((res.width/2)-350, (res.height/2)-450, 700, 900);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		
		mario.setBounds(getX()/2, getHeight()-300,  200,  300);
		
		progress.setSize(700,50);
		progress.setLocation(0, 0);
		progress.setStringPainted(true);
		progress.setString("100");
		progress.setValue(100);
		
		for(int i=0;i<(rand.nextInt(50)+10);i++) {
			JLabel temp = getNewGhost();
			temp.setBounds((int)(rand.nextDouble()*600),  -100,  100,  100);
			ghostList.add(temp);
			contentPane.add(temp);
		}
		
		contentPane.add(mario);
		contentPane.add(progress);
		
		addKeyListener(new MoveImage());
		setVisible(true);
	}
	
	public static JLabel getNewGhost() {
		ImageIcon ghost = new ImageIcon("/Users/hamgyubin/eclipse-workspace/avoidingObjectGame/ghost.png");
		JLabel newGhost = imageSetSize(ghost, 50, 50); 
		
		return newGhost;
	}
	
	public static JLabel imageSetSize(ImageIcon icon, int x, int y) {
		Image ximg = icon.getImage();
		Image yimg = ximg.getScaledInstance(x, y, Image.SCALE_SMOOTH);
		ImageIcon xyimg = new ImageIcon(yimg); 
		JLabel label = new JLabel(xyimg);
		return label;
	}
	

	class MoveImage extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			
			if(mario.getY() > -100) {
				if(key == e.VK_UP) {
					mario.setLocation(mario.getX(), mario.getY()-20);
			}	}
			if(mario.getY() < getHeight()-mario.getSize().height+50) {
				if(key == e.VK_DOWN) {
					mario.setLocation(mario.getX(), mario.getY()+20);
			}	}
			if(mario.getX() > -50) {
				if(key == e.VK_LEFT) {
					mario.setLocation(mario.getX()-20, mario.getY());
			}	}
			if(mario.getX() < getWidth()-mario.getSize().width + 50) {
				if(key == e.VK_RIGHT) {
					mario.setLocation(mario.getX()+20, mario.getY());
			}	}
	}	}

}
