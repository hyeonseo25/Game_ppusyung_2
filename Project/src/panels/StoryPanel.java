package panels;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class StoryPanel extends JPanel{
	private JButton nextbt;
	private JButton startbt;
	
	private int stage;
	private int page = 0;
	
	private Image scene1[] = {new ImageIcon("images/story/story1_1.PNG").getImage()
			,new ImageIcon("images/story/story1_2.PNG").getImage()};
	
	private Image scene2[] = {new ImageIcon("images/story/story2_1.PNG").getImage()
			,new ImageIcon("images/story/story2_2.PNG").getImage()};
	
	private Image scene3[] = {new ImageIcon("images/story/story3_1.PNG").getImage()
			,new ImageIcon("images/story/story3_2.PNG").getImage()};
	
	private Clip backgroundMusic;
	private Dimension view = Toolkit.getDefaultToolkit().getScreenSize();

	public StoryPanel(Object o, int stage) {
		this.stage=stage;
		page = 0;
		Image nextButton = new ImageIcon("images/button/ArrowButton.png").getImage();
		Image startButton = new ImageIcon("images/button/GameStartButton.png").getImage();
		
		nextbt = new JButton(new ImageIcon(nextButton));
		nextbt.setName("NextButton");
		nextbt.setBorderPainted(false);
		nextbt.setFocusPainted(false);
		nextbt.setContentAreaFilled(false);
		nextbt.setBounds(1500, 750, nextButton.getWidth(null), nextButton.getHeight(null));
		
		startbt = new JButton(new ImageIcon(startButton));
		startbt.setName("GameStartButton");
		startbt.setBorderPainted(false);
		startbt.setFocusPainted(false);
		startbt.setContentAreaFilled(false);
		startbt.setBounds((view.width/2 - startButton.getWidth(null)/2), 720, startButton.getWidth(null), startButton.getHeight(null));
		startbt.addMouseListener((MouseListener) o);
		
		nextbt.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				page++;
				if(page>=scene1.length-1) {
					add(startbt);
					remove(nextbt);
				}
				repaint();
			}
		});
		add(nextbt);
	}
	public void playMusic() {
		 try {
			 File file = new File("music/prologueMusic.wav");
			 AudioInputStream stream = AudioSystem.getAudioInputStream(file);
			 backgroundMusic = AudioSystem.getClip();
			 backgroundMusic.open(stream);
			 backgroundMusic.start();
			 backgroundMusic.loop(-1);
	     } catch(Exception e) {
	    	 e.printStackTrace();
	     }	
	}
	
	public void closeMusic() {
		backgroundMusic.close();
	}	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		switch (stage) {
		case 1:
			g.drawImage(scene1[page], 0, 0, this);
			break;
		case 2:
			g.drawImage(scene2[page], 0, 0, this);
			break;
		case 3:
			g.drawImage(scene3[page], 0, 0, this);
			break;
		default:
			break;
		}
		
	}
}
