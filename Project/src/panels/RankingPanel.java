package panels;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import util.DBConnection;

public class RankingPanel extends JPanel implements MouseListener{
	private JButton replaybt;
	private JButton arrowbt1;
	private JButton arrowbt2;
	private JButton arrowbt3;
	private JButton arrowbt4;
	public ArrayList<String[]> scoreList = new ArrayList<String[]>(6);


	
	private int x = 0;
	
	private Image oneStageRangking = new ImageIcon("images/랭킹패널배경.png").getImage();
	
	private ImageIcon backImg = new ImageIcon("images/랭킹1.png");
	private Image back = backImg.getImage();
	
	private int x1 = oneStageRangking.getWidth(null) - 150;
	private int x2 = oneStageRangking.getWidth(null) + 20;
	private int x3 = 2*oneStageRangking.getWidth(null) - 150;
	private int x4 = 2*oneStageRangking.getWidth(null) + 20;


	public RankingPanel(Object o) {
		
		
		Image exitButton = new ImageIcon("images/button/ArrowButton.png").getImage();
		
		replaybt = new JButton(new ImageIcon(exitButton));
		replaybt.setName("ReplayButton");
		replaybt.setBorderPainted(false);
		replaybt.setFocusPainted(false);
		replaybt.setContentAreaFilled(false);
		replaybt.setBounds(20, 20, exitButton.getWidth(null), exitButton.getHeight(null));
		replaybt.addMouseListener((MouseListener) o);
		add(replaybt);
		
		Image arrowButton= new ImageIcon("images/button/ArrowButton.png").getImage();
		
		arrowbt1 = new JButton(new ImageIcon(arrowButton));
		arrowbt1.setName("arrowbt1");
		arrowbt1.setBorderPainted(false);
		arrowbt1.setFocusPainted(false);
		arrowbt1.setContentAreaFilled(false);
		//leftArrowbt.setBounds(20, 500, arrowButton.getWidth(null), arrowButton.getHeight(null));
		arrowbt1.setBounds(x1, 500, arrowButton.getWidth(null), arrowButton.getHeight(null));
		arrowbt1.addMouseListener(this);
		
		add(arrowbt1);
		
		arrowbt2 = new JButton(new ImageIcon(arrowButton));
		arrowbt2.setName("arrowbt2");
		arrowbt2.setBorderPainted(false);
		arrowbt2.setFocusPainted(false);
		arrowbt2.setContentAreaFilled(false);
		//leftArrowbt.setBounds(20, 500, arrowButton.getWidth(null), arrowButton.getHeight(null));
		arrowbt2.setBounds(x2, 500, arrowButton.getWidth(null), arrowButton.getHeight(null));
		arrowbt2.addMouseListener(this);
		
		add(arrowbt2);
		
		arrowbt3 = new JButton(new ImageIcon(arrowButton));
		arrowbt3.setName("arrowbt3");
		arrowbt3.setBorderPainted(false);
		arrowbt3.setFocusPainted(false);
		arrowbt3.setContentAreaFilled(false);
		//leftArrowbt.setBounds(20, 500, arrowButton.getWidth(null), arrowButton.getHeight(null));
		arrowbt3.setBounds(x3, 500, arrowButton.getWidth(null), arrowButton.getHeight(null));
		arrowbt3.addMouseListener(this);
		
		add(arrowbt3);
		
		arrowbt4 = new JButton(new ImageIcon(arrowButton));
		arrowbt4.setName("arrowbt4");
		arrowbt4.setBorderPainted(false);
		arrowbt4.setFocusPainted(false);
		arrowbt4.setContentAreaFilled(false);
		//leftArrowbt.setBounds(20, 500, arrowButton.getWidth(null), arrowButton.getHeight(null));
		arrowbt4.setBounds(x4, 500, arrowButton.getWidth(null), arrowButton.getHeight(null));
		arrowbt4.addMouseListener(this);
		
		add(arrowbt4);
		repaintThread();

	}
	
	public ArrayList<String[]> setScores() {
		String[] stage1Name = new String[10];
		String[] stage1Score = new String[10];
		String[] stage2Name = new String[10];
		String[] stage2Score = new String[10];
		String[] stage3Name = new String[10];
		String[] stage3Score = new String[10];
		
		ArrayList<String[]> list = new ArrayList<String[]>();

		
		DBConnection db = new DBConnection(); //디비 연결
		String sql1 = "select * from user where stage = 1 order by score DESC"; //score 내림차순으로 정렬
		String sql2 = "select * from user where stage = 2 order by score DESC";
		String sql3 = "select * from user where stage = 3 order by score DESC";
		try {
			db.rs = db.stmt.executeQuery(sql1);
			int i = 0;
			while(db.rs.next() && i < 10) {
				stage1Name[i] = db.rs.getString("name");
				stage1Score[i] = db.rs.getString("score");			
				i++;
			}
			
	
			db.rs = db.stmt.executeQuery(sql2);
			i = 0;
			while(db.rs.next() && i < 10) {
				stage2Name[i] = db.rs.getString("name");
				stage2Score[i] = db.rs.getString("score");			
				i++;
			}
			
			db.rs = db.stmt.executeQuery(sql3);
			i = 0;
			while(db.rs.next() && i < 10) {
				stage3Name[i] = db.rs.getString("name");
				stage3Score[i] = db.rs.getString("score");			
				i++;
			}
			
			list.add(stage1Name);
			list.add(stage1Score);

			list.add(stage2Name);
			list.add(stage2Score);

			list.add(stage3Name);
			list.add(stage3Score);
			
			return list;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public void repaintThread() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true) {
				
					repaint();
					try {
					
						Thread.sleep(10);
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
			
		}).start();
	}	
	

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(back, x, 0, this);
		g.setFont(new Font("나눔바른고딕", Font.BOLD, 40));

		for (int i = 0; i < 10; i ++) {
			try {
				g.drawString(scoreList.get(0)[i], 700 + x, 265 + i * 72);								} catch(Exception e) {}
			try {
				g.drawString(scoreList.get(1)[i], 1100 + x, 265 + i * 72); 								}catch(Exception e) {}
			try {
				g.drawString(scoreList.get(2)[i], oneStageRangking.getWidth(null) + 700 + x, 265 + i * 72); }catch(Exception e) {}
			try {
				g.drawString(scoreList.get(3)[i], oneStageRangking.getWidth(null) + 1100 + x, 265 + i * 72); }catch(Exception e) {}
			try {
				g.drawString(scoreList.get(4)[i], 2*oneStageRangking.getWidth(null) + 700 + x, 265 + i * 72); }catch(Exception e) {}
			try {
				g.drawString(scoreList.get(5)[i], 2*oneStageRangking.getWidth(null) + 1100 + x, 265 + i * 72); }catch(Exception e) {}
		}
		
	}

	public void pushBackground(int btn) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				switch(btn){
					case 1:
						while (x > -(oneStageRangking.getWidth(null))) {

							x -= 20;
							x1 -= 20;
							x2 -= 20;
							x3 -= 20;
							x4 -= 20;
							arrowbt1.setLocation(x1, 500);
							arrowbt2.setLocation(x2, 500);
							arrowbt3.setLocation(x3, 500);
							arrowbt4.setLocation(x4, 500);
							try {
								Thread.sleep(10);
							} catch(Exception e) {
								e.printStackTrace();
							}
						}
						break;
					case 2:
						while (x < 0) {
							x += 20;
							x1 += 20;
							x2 += 20;
							x3 += 20;
							x4 += 20;
							arrowbt1.setLocation(x1, 500);
							arrowbt2.setLocation(x2, 500);
							arrowbt3.setLocation(x3, 500);
							arrowbt4.setLocation(x4, 500);
							try {
								Thread.sleep(10);
							} catch(Exception e) {
								e.printStackTrace();
							}
						} 
						break;
					case 3:
						while (x > (-2 * (oneStageRangking.getWidth(null)))) {
							x -= 20;
							x1 -= 20;
							x2 -= 20;
							x3 -= 20;
							x4 -= 20;
							arrowbt1.setLocation(x1, 500);
							arrowbt2.setLocation(x2, 500);
							arrowbt3.setLocation(x3, 500);
							arrowbt4.setLocation(x4, 500);
							try {
								Thread.sleep(10);
							} catch(Exception e) {
								e.printStackTrace();
							}
						}
						break;
					case 4: 
						while (x < -(oneStageRangking.getWidth(null))) {
							x += 20;
							x1 += 20;
							x2 += 20;
							x3 += 20;
							x4 += 20;
							arrowbt1.setLocation(x1, 500);
							arrowbt2.setLocation(x2, 500);
							arrowbt3.setLocation(x3, 500);
							arrowbt4.setLocation(x4, 500);
							try {
								Thread.sleep(10);
							} catch(Exception e) {
								e.printStackTrace();
							}
						}
						break;
					}
					
					
				}
		}).start();
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getComponent().getName().equals("arrowbt1")) {
			
			pushBackground(1);
		} else if(e.getComponent().getName().equals("arrowbt2")) {
//			x -= oneStageRangking.getWidth(null);
			pushBackground(2);
		} else if(e.getComponent().getName().equals("arrowbt3")) {
//			x += oneStageRangking.getWidth(null);
			pushBackground(3);
		}else if(e.getComponent().getName().equals("arrowbt4")) {
//			x -= oneStageRangking.getWidth(null);
			pushBackground(4);
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {	
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}
}