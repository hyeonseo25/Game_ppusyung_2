package components;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Shot {
	private int x;
	private int y;
	private int shot_direction;
	private int shot_type;
	private int speed=50;
	private Image image;
	
	private ImageIcon shotImg[][] = {
			{new ImageIcon("images/�Ѿ�_�Ѿ�.png"), new ImageIcon("images/�Ѿ�_�Ѿ�_left.png")},
			{new ImageIcon("images/�Ѿ�_��.png"), new ImageIcon("images/�Ѿ�_��_left.png")}};
	
	
	public Shot(JPanel main, int x, int y, int status, int shot_type) { //status 1:����������, 2:�������� �Ѿ� �̵�
		if(shot_type==1) {
			this.x = x+60;
			this.y = y+80;
		}else {
			this.x = x+25;
			this.y = y+75;
		}
		setShot_direction(status);
		setImage(shotImg[shot_type-1][status-1].getImage());
		s_move();
	}
	
	//���� shot
	public Shot(int x, int y, int status) { //status 1:����������, 2:�������� �Ѿ� �̵�
		this.x = x+20;
		this.y = y+70;
		setShot_direction(status);
		setImage(new ImageIcon("images/�Ѿ�.png").getImage());
		s_move();
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getShot_direction() {
		return shot_direction;
	}
	
	public void setShot_direction(int status) {
		if(status==1) {
			shot_direction=0;
		}else if(status==2) {
			shot_direction=180;
		}
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public Image getImage() {
		return image;
	}
	
	public void setImage(Image image) {
		this.image = image;
	}
	
	// �Ѿ��� ���󰡴� ������ 
	public void s_move() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					try {
						x += Math.cos(Math.toRadians(shot_direction)) * speed;
						y += Math.sin(Math.toRadians(shot_direction)) * speed;
						Thread.sleep(50);
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}