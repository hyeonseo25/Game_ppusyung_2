package panels;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import main.Main;
import util.DBConnection;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

public class SelectStagePanel extends JPanel{
	private JButton replaybt;
	private JButton gamebt1;
	private JButton gamebt2;
	private JButton gamebt3;
	
	private ImageIcon backImg = new ImageIcon("images/select_bg.png");
	private Image back = backImg.getImage();
	
	private Clip backgroundMusic;
	
	private Dimension view = Toolkit.getDefaultToolkit().getScreenSize();
	
	private Main main;
	
	public SelectStagePanel(Object o) {
		main = (Main)o;
		Image exitButton = new ImageIcon("images/button/ArrowButton.png").getImage();
		Image stageButton1 = new ImageIcon("images/button/Stage1Button.png").getImage();
		Image stageButton2 = new ImageIcon("images/button/Stage2Button.png").getImage();
		Image stageButton3 = new ImageIcon("images/button/Stage3Button.png").getImage();
		
		replaybt = new JButton(new ImageIcon(exitButton));
		replaybt.setName("ReplayButton");
		replaybt.setBorderPainted(false);
		replaybt.setFocusPainted(false);
		replaybt.setContentAreaFilled(false);
		replaybt.setBounds(20, 20, exitButton.getWidth(null), exitButton.getHeight(null));
		replaybt.addMouseListener((MouseListener) o);
		add(replaybt);
		
		gamebt1 = new JButton(new ImageIcon(stageButton1));
		gamebt1.setName("GameStartButton");
		gamebt1.setBorderPainted(false);
		gamebt1.setFocusPainted(false);
		gamebt1.setContentAreaFilled(false);
		gamebt1.setBounds(150, 320, stageButton1.getWidth(null), stageButton1.getHeight(null));
		gamebt1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				main.setStage(1);
			}
		});
		gamebt1.addMouseListener((MouseListener) o);
		add(gamebt1);
		
		gamebt2 = new JButton(new ImageIcon(stageButton2));
		gamebt2.setName("GameStartButton");
		gamebt2.setBorderPainted(false);
		gamebt2.setFocusPainted(false);
		gamebt2.setContentAreaFilled(false);
		gamebt2.setBounds((view.width/2 - stageButton2.getWidth(null)/2), 320, stageButton2.getWidth(null), stageButton2.getHeight(null));
		gamebt2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				main.setStage(2);
			}
		});	
		gamebt2.addMouseListener((MouseListener) o);
		add(gamebt2);
		
		gamebt3 = new JButton(new ImageIcon(stageButton3));
		gamebt3.setName("GameStartButton");
		gamebt3.setBorderPainted(false);
		gamebt3.setFocusPainted(false);
		gamebt3.setContentAreaFilled(false);
		gamebt3.setBounds(1300, 320, stageButton3.getWidth(null), stageButton3.getHeight(null));
		gamebt3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				main.setStage(3);
			}
		});
		gamebt3.addMouseListener((MouseListener) o);
		add(gamebt3);
	}

	public void playMusic() {
		 try {
			 File file = new File("music/startPanelMusic.wav");
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
		g.drawImage(back, 0, 0, this);
	}
}