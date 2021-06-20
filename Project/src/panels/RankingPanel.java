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
	private JButton exitbt1;
	private JButton exitbt2;
	private JButton exitbt3;

	private JButton rightbt;
	private JButton leftbt;
	public ArrayList<String[]> scoreList = new ArrayList<String[]>(6);


	
	private int x = 0;
	
	private Image oneStageRangking = new ImageIcon("images/랭킹패널배경.png").getImage();
	
	private ImageIcon backImg = new ImageIcon("images/랭킹1.png");
	private Image back = backImg.getImage();
	
	private int x1 = oneStageRangking.getWidth(null) - 150;
	private int x2 = -500; //처음에는 화면에서 안보이게

	private int bt1_x = 1500;
	private int bt2_x = oneStageRangking.getWidth(null) + 1500;
	private int bt3_x = 2*(oneStageRangking.getWidth(null)) + 1500;



	public RankingPanel(Object o) {
		
		
		Image exitButton = new ImageIcon("images/button/X버튼.png").getImage();
		
		exitbt1 = new JButton(new ImageIcon(exitButton));
		exitbt1.setName("ReplayButton");
		exitbt1.setBorderPainted(false);
		exitbt1.setFocusPainted(false);
		exitbt1.setContentAreaFilled(false);
		exitbt1.setBounds(1500, 100, exitButton.getWidth(null), exitButton.getHeight(null));
		exitbt1.addMouseListener((MouseListener) o);
		add(exitbt1);
		
		exitbt2 = new JButton(new ImageIcon(exitButton));
		exitbt2.setName("ReplayButton");
		exitbt2.setBorderPainted(false);
		exitbt2.setFocusPainted(false);
		exitbt2.setContentAreaFilled(false);
		exitbt2.setBounds(oneStageRangking.getWidth(null) + 1500, 100, exitButton.getWidth(null), exitButton.getHeight(null));
		exitbt2.addMouseListener((MouseListener) o);
		add(exitbt2);
		
		exitbt3 = new JButton(new ImageIcon(exitButton));
		exitbt3.setName("ReplayButton");
		exitbt3.setBorderPainted(false);
		exitbt3.setFocusPainted(false);
		exitbt3.setContentAreaFilled(false);
		exitbt3.setBounds(2*(oneStageRangking.getWidth(null)) + 1500, 100, exitButton.getWidth(null), exitButton.getHeight(null));
		exitbt3.addMouseListener((MouseListener) o);
		add(exitbt3);
		
		
		Image arrowButton= new ImageIcon("images/button/ArrowButton.png").getImage();
		
		rightbt = new JButton(new ImageIcon(arrowButton));
		rightbt.setName("rightbt");
		rightbt.setBorderPainted(false);
		rightbt.setFocusPainted(false);
		rightbt.setContentAreaFilled(false);
		rightbt.setBounds(x1, 500, arrowButton.getWidth(null), arrowButton.getHeight(null));
		rightbt.addMouseListener(this);
		
		add(rightbt);
		
		leftbt = new JButton(new ImageIcon(arrowButton));
		leftbt.setName("leftbt");
		leftbt.setBorderPainted(false);
		leftbt.setFocusPainted(false);
		leftbt.setContentAreaFilled(false);
		leftbt.setBounds(x2, 500, arrowButton.getWidth(null), arrowButton.getHeight(null));
		leftbt.addMouseListener(this);
		
		add(leftbt);
		
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
				// 1stage 이름 
				g.drawString(scoreList.get(0)[i], 500 + x + 600 * (i / 5), 325 + (i%5) * 152);								} catch(Exception e) {}
			try {
				// 1stage 점수
				g.drawString(scoreList.get(1)[i], 750 + x + 600 * (i / 5), 325 + (i%5) * 152); 								}catch(Exception e) {}
			try {
				// 2stage 이름
				g.drawString(scoreList.get(2)[i], oneStageRangking.getWidth(null) + 510 + x + 600 * (i / 5), 325 + (i%5) * 152); }catch(Exception e) {}
			try {
				// 2stage 점수
				g.drawString(scoreList.get(3)[i], oneStageRangking.getWidth(null) + 760 + x + 600 * (i / 5), 325 + (i%5) * 152); }catch(Exception e) {}
			try {
				// 3stage 이름
				g.drawString(scoreList.get(4)[i], 2*oneStageRangking.getWidth(null) + 500 + x + 600 * (i / 5), 325 + (i%5) * 152); }catch(Exception e) {}
			try {
				// 3stage 점수
				g.drawString(scoreList.get(5)[i], 2*oneStageRangking.getWidth(null) + 750 + x + 600 * (i / 5), 325 + (i%5) * 152); }catch(Exception e) {}
		}
		
	}

	public void pushBackground(int btn) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				switch(btn){
					case 1:
						if(x > -(oneStageRangking.getWidth(null))) {
							while (x > -(oneStageRangking.getWidth(null))) {
	
								x -= 20;
								
								bt1_x -= 20;
								bt2_x -= 20;
								bt3_x -= 20;
								leftbt.setLocation(20, 500); // 화면에서 보이게
								exitbt1.setLocation(bt1_x, 100);
								exitbt2.setLocation(bt2_x, 100);							
								exitbt3.setLocation(bt3_x, 100);
								try {
									Thread.sleep(10);
								} catch(Exception e) {
									e.printStackTrace();
								}
							}
						}
						else {
							while (x > (-2 * (oneStageRangking.getWidth(null)))) {
								x -= 20;
								bt1_x -= 20;
								bt2_x -= 20;
								bt3_x -= 20;
								rightbt.setLocation(-300, 500); // 화면에서 안보이게 
								exitbt1.setLocation(bt1_x, 100);
								exitbt2.setLocation(bt2_x, 100);							
								exitbt3.setLocation(bt3_x, 100);
								try {
									Thread.sleep(10);
								} catch(Exception e) {
									e.printStackTrace();
								}
							}
						}
						break;
						
					case 2:							
						if(x < -(oneStageRangking.getWidth(null))) {

							while (x < -(oneStageRangking.getWidth(null))) {
								x += 20;
								bt1_x += 20;
								bt2_x += 20;
								bt3_x += 20;
								rightbt.setLocation(oneStageRangking.getWidth(null) - 150, 500);
								exitbt1.setLocation(bt1_x, 100);
								exitbt2.setLocation(bt2_x, 100);							
								exitbt3.setLocation(bt3_x, 100);
								try {
									Thread.sleep(10);
								} catch(Exception e) {
									e.printStackTrace();
								}
							}
							
						} else {
							while (x < 0) {
								x += 20;
								bt1_x += 20;
								bt2_x += 20;
								bt3_x += 20;
								leftbt.setLocation(-400, 500);
								exitbt1.setLocation(bt1_x, 100);
								exitbt2.setLocation(bt2_x, 100);							
								exitbt3.setLocation(bt3_x, 100);
								try {
									Thread.sleep(10);
								} catch(Exception e) {
									e.printStackTrace();
								}
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
		if(e.getComponent().getName().equals("rightbt")) {
			pushBackground(1);
		} else if(e.getComponent().getName().equals("leftbt")) {
			pushBackground(2);
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