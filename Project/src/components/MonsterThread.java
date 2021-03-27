package components;

import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;

import util.Util;

public class MonsterThread extends Thread{
	private int x = 0; 
	private int y = 0;
	private int hp;
	private Image images;
	private boolean status = false; // ��������
	private boolean fall = false;
	private boolean jump = false;
	protected boolean flag = false;
	private int field = 400;
	
	protected Player player;
	
	public MonsterThread(int x, int y, int hp, String Image, Player player) {
		setX(x);
		setY(y);
		setHp(hp);
		setStatus(true);
		setImage(Image);
		if(!Image.equals("images/monsters/��������.gif")) {
			fall();
		}
		this.player = player;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public void setImage(String Image) {
		Image monsterIcon1 = new ImageIcon(Image).getImage();
		this.images = monsterIcon1;
	}
	
	public Image getImage() {
		return images;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getX() {
		return x;
	}
	
	public void setY(double y) {
		this.y += y;
	}
	
	public int getY() {
		return y;
	}
	
	public int getField() {
		return field;
	}
	
	public void setField(int field) {
		this.field = field;
	}
	
	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public int getHp() {
		return hp;
	}
	
	public boolean isFall() {
		return fall;
	}
	
	public void setFall(boolean fall) {
		this.fall = fall;
	}
	
	public boolean isStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public boolean isJump() {
		return jump;
	}
	
	public void setJump(boolean jump) {
		this.jump = jump;
	}
	
	public void reduceHp(int hp) {
		this.hp += hp;
	}
	
	private void m_move() {
		x-=8;	
	}
	
	public void m_move(int x) {
		flag=true;
		this.x-=x;
		flag=false;
	}
    
    public void run() {
    	while(true) {
			if(flag==false) {
				m_move();
			}
			try {
				m_remove();
			}catch (NullPointerException e) {
				e.printStackTrace();
			}
			if(isStatus()==true) {
				m_hit();
			}
			if(isStatus()==false)
				break;
			try {
				Thread.sleep(30);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}	
    }
    
    //player�� monster�� �浹 
    public void m_hit() {
    	try {
    		// �浹 ���� ��
    		if((player.getY() < getY() + getImage().getHeight(null) && getY() < player.getY() + player.getImage().getHeight(null)) 
					&& (player.getX() < getX() + getImage().getWidth(null) && getX() < player.getX() + player.getImage().getWidth(null))) {
    			if(player.getInvincibility()==255) { 
        			player.damaged(200);
        		}
    		}
    	}catch (Exception e) {
    		
		}
    }
    
    
    // �Ѿ˰� player�� �浹
    public void m_remove() {
    	try {
			for(int i = 0; i < player.getShots().size(); i++) {
				Shot shot = player.getShots().get(i);
				int head = getY() - getImage().getHeight(null);
				int foot = getY() + getImage().getHeight(null);
				int shotD = shot.getShot_direction();
				
				//�Ѿ��� ���������� ���ư��� 
				if(foot >= shot.getY() && head <= shot.getY() && shotD == 0 && getX() <= shot.getX() && getX() >= player.getX()) {
					player.getShots().remove(i); //���� �Ѿ� ����
					Sound("music/hitSound.wav", false);
					reduceHp(-50); 
				}

				//�Ѿ��� �������� ���ư� �� 
				else if(foot >= shot.getY() && head <= shot.getY() && shotD ==180 && getX() + 100>= shot.getX() && getX() <= player.getX()) {
					player.getShots().remove(i);
					Sound("music/hitSound.wav", false);
					reduceHp(-50);
				}
				if(getHp() <= 0) { 
					if(isStatus()==true) {
						player.setScore(player.getScore()+200);
						Sound("music/monsterDieSound.wav", false);
					}
					setStatus(false);
					setImage(null);
					break;	
				}
				Thread.sleep(30);
			}
		} catch(IndexOutOfBoundsException e) {
			e.printStackTrace();
		} catch(InterruptedException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			
		}
    }

    //�ʵ�� ��� ���������� �ϴ� ������ 
	public void fall() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (true) {
					int foot = getY() + getImage().getHeight(null); // ĳ���� �� ��ġ �罺ĵ

					// �߹ٴ��� ���Ǻ��� ���� ������ �۵�
					if (foot < field // ���߿� ������
							&& !isJump() // ���� ���� �ƴϸ�
							&& !isFall()) { // �������� ���� �ƴ� ��
						
						setFall(true); 

						long t1 = Util.getTime(); // ����ð��� �����´�
						long t2;
						int set = 1; // ó�� ���Ϸ� (0~10) ���� �׽�Ʈ�غ���

						while (foot < field) { // ���� ���ǿ� ��� ������ �ݺ�
							t2 = Util.getTime() - t1; // ���� �ð����� t1�� ����
							int fallY = set + (int) ((t2) / 40); // ���Ϸ��� �ø���.
							foot = getY() + getImage().getHeight(null); // ĳ���� �� ��ġ �罺ĵ
							if (foot + fallY >= field) { // �߹ٴ�+���Ϸ� ��ġ�� ���Ǻ��� ���ٸ� ���Ϸ��� �����Ѵ�.
								fallY = field - foot;
							}
							setY(fallY); // Y��ǥ�� ���Ϸ��� ���Ѵ�
							try {
								Thread.sleep(40);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						} // end of while
						setFall(false);
					}
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	
	public void Sound(String file, boolean Loop){
		//���������޼ҵ�
		//�����������޾Ƶ鿩�ش���带�����Ų��.
		Clip clip;
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(file)));
			clip = AudioSystem.getClip();
			clip.open(ais);
			clip.start();
			if (Loop) clip.loop(-1);
			//Loop ����true�� ������������ѹݺ���ŵ�ϴ�.
			//false�� �ѹ��������ŵ�ϴ�.
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}