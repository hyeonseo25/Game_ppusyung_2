package panels;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import util.DBConnection;

public class RankingPanel extends JPanel{
	private JButton replaybt;
	
	private ImageIcon backImg = new ImageIcon("images/��ŷ�гι��.png");
	private Image back = backImg.getImage();

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
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.drawImage(back, 0, 0, this);
		g.setFont(new Font("�����ٸ����", Font.BOLD, 40));
		
		DBConnection db = new DBConnection(); //��� ����
		String sql = "select * from user order by score DESC"; //score ������������ ����
		try {
			db.rs = db.stmt.executeQuery(sql);
			
			int i = 0;
			while(db.rs.next() && i < 10) {
				g.drawString(db.rs.getString("name"), 700, 265 + i * 72);
				g.drawString(db.rs.getString("score"), 1100, 265 + i * 72);				
				i++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}