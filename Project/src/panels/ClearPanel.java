package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.Main;
import util.DBConnection;

public class ClearPanel extends JPanel {
	private JTextField name;
	private JButton applybt;
	private JLabel scoreLabel;
	private String scoreText;
	private int score; // �÷��̾� ����
	private int stage;
	
	private Image back1 = new ImageIcon("images/Ŭ����1.png").getImage();
	private Image back2 = new ImageIcon("images/Ŭ����2.png").getImage();
	private Image back3 = new ImageIcon("images/Ŭ����3.png").getImage();

	private Image nameField = new ImageIcon("images/�̸��Է¹���.png").getImage();
	
	
	public String getName() {
		return name.getText();
	}
	public void setName(String n) {
		this.name.setText(n);
	}
	public String getScoreText() {
		return scoreText;
	}
	public void setScoreText(String scoreText) {
		this.scoreText = scoreText;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public void setStage(int stage) {
		this.stage = stage;
	}
	public int getStage() {
		return this.stage;
	}
	public ClearPanel(Object o){
		scoreLabel = new JLabel(Integer.toString(score));
		scoreLabel.setLocation(500, 50);
		add(scoreLabel);
		
		Font font=new Font("����", Font.BOLD, 35);
		name = new JTextField("�̸��� �Է����ּ���");
		name.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		name.setForeground(Color.WHITE);
		name.setBackground(Color.black);
		name.setHorizontalAlignment(JTextField.CENTER);
		// hint ������
		name.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
		        	name.setText("");
			}
		});
		name.setName("name");
		name.setFont(font);
		name.setLocation(750, 350);
		name.setSize(390, 50);
		add(name);
		
		applybt = new JButton(new ImageIcon("images/button/ClearPanelButton.png"));
		applybt.setName("ReplayButton2");
		applybt.setBorderPainted(false);
		applybt.setFocusPainted(false);
		applybt.setContentAreaFilled(false);
		applybt.setBounds(1140, 270, 200, 200);
		
		applybt.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if(!(getName().equals("")||getName().equals("�̸��� �Է����ּ���"))) {
					Main.db.insertDB(name.getText() , Integer.toString(score), getStage());	
				}
			}
		});
		applybt.addMouseListener((MouseListener) o);
		add(applybt);
	}

	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub	
		super.paintComponent(g);
		if(getStage() == 1) {
			g.drawImage(back1, 0, 0, this); // �޹��
		}
		else if(getStage() == 2) {
			g.drawImage(back2, 0, 0, this); // �޹��
		}else if(getStage() == 3){
			g.drawImage(back3, 0, 0, this); // �޹��
		}
		g.drawImage(nameField, 750, 400, this);
		g.setFont(new Font("Sandoll ������ 01 Original", Font.BOLD, 60)); 
		g.setColor(Color.WHITE);
		g.drawString(Integer.toString(score) + " ��", 1150, 640); //����
	}
}