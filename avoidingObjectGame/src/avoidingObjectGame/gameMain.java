package avoidingObjectGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.JLabel;

public class gameMain {

	public static void main(String[] args) throws InterruptedException {
		
		Random rand = new Random();
		
		GameFrame gf = new GameFrame("GameFrame");
		ArrayList<JLabel> ghostList = gf.ghostList;
		
		JLabel mario = gf.mario;
		JLabel temp;
		boolean clear = true;
		
		JLabel end = new JLabel();
		end.setOpaque(true);
		end.setBackground(Color.BLACK);
		end.setForeground(Color.white);
		end.setHorizontalAlignment(JLabel.CENTER);
		end.setBounds(0, 0, gf.getBounds().width, gf.getBounds().height);
		end.setFont(new Font("Dialog", Font.BOLD, 100));
		gf.getContentPane().add(end);
		end.setVisible(false);
		
		
		
		for(int i=0;i<gf.ghostList.size(); i++) {
			temp = ghostList.get(i);
			while(temp.getY()<gf.getHeight()) {
				// drop ghost one time loop
			temp.setLocation(temp.getX(),temp.getY()+10);
			TimeUnit.MILLISECONDS.sleep(10);
			
			if((mario.getX() > temp.getX()) && (temp.getX()+temp.getWidth()-70)>mario.getX()) {
			// 1. mario's x is bigger than ghost
				if(temp.getY()-50>mario.getY()) {
				gf.progress.setValue(gf.progress.getValue() - 10);
				// hp decrease
				gf.progress.setString(String.valueOf(gf.progress.getValue()));
				// set progressbar's String
				temp.setVisible(false);
				break;
			}	// if condition end
			}else if((mario.getX() < temp.getX()) && (mario.getX()+mario.getWidth()-70)>temp.getX()) {
			// 2. ghost's x is bigger than ghost
				if(temp.getY()-50>mario.getY()) {
				gf.progress.setValue(gf.progress.getValue() - 10);
				// hp decrease
				gf.progress.setString(String.valueOf(gf.progress.getValue()));
				// set progressbar's String
				temp.setVisible(false);
				break;
			} // if condition end
			} // if condition end
			} //while loop end
			if(gf.progress.getValue() == 0) {
				end.setText("죽음");
				clear = false;
				break;
			}
		} // for loop end
		if(clear)	end.setText("끗");
		end.setVisible(true);
			} // main method end
} // class end


