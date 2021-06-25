package panels;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

public class StartPanel extends JPanel{
	private JButton startbt;
	private JButton rankbt;
	private JButton infobt;
	
	private ImageIcon backImg = new ImageIcon("images/메인페이지.png");
	private Image back = backImg.getImage();
	
	private Clip backgroundMusic;
	
	private Dimension view = Toolkit.getDefaultToolkit().getScreenSize();
	
	public StartPanel(Object o) {
		Image startButton = new ImageIcon("images/button/플레이버튼.png").getImage();
		ImageIcon startButtonMouseOver = new ImageIcon("images/button/플레이버튼(마우스 오버).png");
		Image rankButton = new ImageIcon("images/button/랭킹버튼.png").getImage();
		ImageIcon rankButtonMouseOver = new ImageIcon("images/button/랭킹버튼(마우스 오버).png");
		Image InfoButton = new ImageIcon("images/button/설명버튼.png").getImage();
		ImageIcon InfoButtonMouseOver = new ImageIcon("images/button/설명버튼(마우스 오버).png");
		
		startbt = new JButton(new ImageIcon("images/button/플레이버튼.png"));
		startbt.setName("StartButton");
		startbt.setBorderPainted(false);
		startbt.setFocusPainted(false);
		startbt.setContentAreaFilled(false);
		startbt.setBounds((view.width/2 - startButton.getWidth(null)/2) - 300, 430, startButtonMouseOver.getImage().getWidth(null), startButtonMouseOver.getImage().getHeight(null));		
		startbt.addMouseListener((MouseListener) o);
		startbt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				startbt.setIcon(startButtonMouseOver);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				startbt.setIcon(new ImageIcon("images/button/플레이버튼.png"));
			}
		});
		add(startbt);
		
		rankbt = new JButton(new ImageIcon("images/button/랭킹버튼.png"));
		rankbt.setName("RankingButton");
		rankbt.setBorderPainted(false);
		rankbt.setFocusPainted(false);
		rankbt.setContentAreaFilled(false);
		rankbt.setBounds((view.width/2 - startButton.getWidth(null)/2) + 300, 430, rankButtonMouseOver.getImage().getWidth(null), rankButtonMouseOver.getImage().getHeight(null));
		rankbt.addMouseListener((MouseListener) o);
		rankbt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				rankbt.setIcon(rankButtonMouseOver);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				rankbt.setIcon(new ImageIcon("images/button/랭킹버튼.png"));
			}
		});
		add(rankbt);
		
		infobt = new JButton(new ImageIcon("images/button/설명버튼.png"));
		infobt.setName("InfoButton");
		infobt.setBorderPainted(false);
		infobt.setFocusPainted(false);
		infobt.setContentAreaFilled(false);
		infobt.setBounds((view.width/2 - InfoButton.getWidth(null)/2), 430, InfoButtonMouseOver.getImage().getWidth(null), InfoButtonMouseOver.getImage().getHeight(null));
		infobt.addMouseListener((MouseListener) o);
		infobt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				infobt.setIcon(InfoButtonMouseOver);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				infobt.setIcon(new ImageIcon("images/button/설명버튼.png"));
			}
		});
		add(infobt);
		
		playMusic();
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