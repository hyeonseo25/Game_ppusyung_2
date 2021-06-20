package components;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import util.Util;

public class Player {
	private JPanel mainPanel;
	private int x;
	private int y;
	private int distance = 200; // ĳ���Ͱ� �̵��� �� �Ÿ�
	private int hp = 1000; // ü��
	private int status; // ĳ���Ͱ� �ٶ󺸴� ���� : 1=������, 2=����
	private int hit_status=1; // ���ݻ��� : 1=��, 2=Ȱ, 3=����
	private int shot_range = 800; // �̹��� ����
	private int invincibility = 255; // �̹��� ����
	private int score = 0; // ����
	private Image image;
	private ArrayList<Shot> shots = new ArrayList<Shot>(); // �÷��̾ �� �Ѿ˵��� ���� ����Ʈ
	private int cnt = 0; // ���� ĳ���� �̹��� �����ϴ� ���            
	private boolean fall = false; // ���� ����
	private boolean jump = false; // ���� ����
	private boolean hit = false; // ���� ����
	private int stage;
	private int countJump = 0;
	private int field = 900;
	
	private ImageIcon backImg = new ImageIcon("images/��ŷ1.png");
	private Image back = backImg.getImage();
	
	private Dimension view = Toolkit.getDefaultToolkit().getScreenSize();
	
	private Image images[] = {new ImageIcon("images/Player/3�г�/3�г�.png").getImage()
			,new ImageIcon("images/Player/3�г�/3�г�.png").getImage()
			,new ImageIcon("images/Player/3�г�/3�г�2.png").getImage()
			,new ImageIcon("images/Player/3�г�/3�г�2.png").getImage()
			,new ImageIcon("images/Player/3�г�/3�г�.png").getImage()
			,new ImageIcon("images/Player/3�г�/3�г�.png").getImage()
			,new ImageIcon("images/Player/3�г�/3�г�2.png").getImage()
			,new ImageIcon("images/Player/3�г�/3�г�2.png").getImage()
			,new ImageIcon("images/Player/3�г�/3�г�.png").getImage()
			,new ImageIcon("images/Player/3�г�/3�г�.png").getImage()
			,new ImageIcon("images/Player/3�г�/3�г�2.png").getImage()
			,new ImageIcon("images/Player/3�г�/3�г�2.png").getImage()};

	
	private Image imagesLeft[] = {new ImageIcon("images/Player/���Ի�/���Ի�_left.png").getImage()
			,new ImageIcon("images/Player/���Ի�/���Ի�_left.png").getImage()
			,new ImageIcon("images/Player/���Ի�/���Ի�2_left.png").getImage()
			,new ImageIcon("images/Player/���Ի�/���Ի�2_left.png").getImage()
			,new ImageIcon("images/Player/���Ի�/���Ի�_left.png").getImage()
			,new ImageIcon("images/Player/���Ի�/���Ի�_left.png").getImage()
			,new ImageIcon("images/Player/���Ի�/���Ի�2_left.png").getImage()
			,new ImageIcon("images/Player/���Ի�/���Ի�2_left.png").getImage()
			,new ImageIcon("images/Player/���Ի�/���Ի�2_left.png").getImage()
			,new ImageIcon("images/Player/���Ի�/���Ի�_left.png").getImage()
			,new ImageIcon("images/Player/���Ի�/���Ի�_left.png").getImage()
			,new ImageIcon("images/Player/���Ի�/���Ի�2_left.png").getImage()};
	
	private ImageIcon jumpImg[] = {new ImageIcon("images/Player/���Ի�/���Ի�_����.png"),
			new ImageIcon("images/Player/���Ի�/���Ի�_����_left.png")};
	
	private ImageIcon hitImg[][] = {
			{new ImageIcon("images/Player/���Ի�/���Ի���.png"), new ImageIcon("images/Player/���Ի�/���Ի���_left.png")},
			{new ImageIcon("images/Player/���Ի�/���Ի�_ȭ�����.png"), new ImageIcon("images/Player/���Ի�/���Ի�_ȭ�����_left.png")},
			{new ImageIcon("images/Player/���Ի�/���Ի�_�ܰŸ� ����.png"), new ImageIcon("images/Player/���Ի�/���Ի�_�ܰŸ� ����_left.png")}};
	
	private ImageIcon afterHitImg[] = {new ImageIcon("images/Player/���Ի�/���Ի�_ȭ����.png"),
			new ImageIcon("images/Player/���Ի�/���Ի�_ȭ����_left.png")};
	
	public boolean isFall() {
		return fall;
	}
	
	public void setFall(boolean fall) {
		this.fall = fall;
	}
	
	public int getField() {
		return field;
	}
	
	public void setField(int field) {
		this.field = field;
	}
	
	public boolean isJump() {
		return jump;
	}
	
	public void setJump(boolean jump) {
		this.jump = jump;
	}
	
	public boolean isHit() {
		return hit;
	}
	
	public void setHit(boolean hit) {
		this.hit = hit;
	}
	
	public int getCountJump() {
		return countJump;
	}
	public int getShot_range() {
		return shot_range;
	}

	public void setShot_range(int shot_range) {
		this.shot_range = shot_range;
	}

	public void setCountJump(int countJump) {
		this.countJump = countJump;
	}
	
	public ArrayList<Shot> getShots() {
		return shots;
	}
	
	public void setShots(ArrayList<Shot> shots) {
		this.shots = shots;
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
	
	public int getInvincibility() {
		return invincibility;
	}
	
	public void setInvincibility(int invincibility) {
		this.invincibility = invincibility;
	}
	
	public int getDistance() {
		return distance;
	}
	
	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	public int getHp() {
		return hp;
	}
	
	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getHit_status() {
		return hit_status;
	}
	
	public void setHit_status(int hit_status) {
		if(hit_status==1) {
			setShot_range(800);
		}else if(hit_status==2) {
			setShot_range(1400);
		}
		this.hit_status = hit_status;
	}
	
	public Image getImage() {
		return image;
	}
	
	public void setImage(Image imageIcon) {
		this.image = imageIcon;
	}
	
	public void setImages(int stage) {
		this.stage = stage;
		switch (stage) {
		case 1:
			Image images[] = {new ImageIcon("images/Player/���Ի�/���Ի�2.png").getImage()
					,new ImageIcon("images/Player/���Ի�/���Ի�.png").getImage()
					,new ImageIcon("images/Player/���Ի�/���Ի�.png").getImage()
					,new ImageIcon("images/Player/���Ի�/���Ի�2.png").getImage()
					,new ImageIcon("images/Player/���Ի�/���Ի�2.png").getImage()
					,new ImageIcon("images/Player/���Ի�/���Ի�2.png").getImage()
					,new ImageIcon("images/Player/���Ի�/���Ի�.png").getImage()
					,new ImageIcon("images/Player/���Ի�/���Ի�.png").getImage()
					,new ImageIcon("images/Player/���Ի�/���Ի�2.png").getImage()
					,new ImageIcon("images/Player/���Ի�/���Ի�2.png").getImage()
					,new ImageIcon("images/Player/���Ի�/���Ի�2.png").getImage()
					,new ImageIcon("images/Player/���Ի�/���Ի�.png").getImage()
					,new ImageIcon("images/Player/���Ի�/���Ի�.png").getImage()
					,new ImageIcon("images/Player/���Ի�/���Ի�2.png").getImage()
					,new ImageIcon("images/Player/���Ի�/���Ի�2.png").getImage()};
			this.images = images;
			
			Image imagesLeft[] = {new ImageIcon("images/Player/���Ի�/���Ի�2_left.png").getImage()
					,new ImageIcon("images/Player/���Ի�/���Ի�_left.png").getImage()
					,new ImageIcon("images/Player/���Ի�/���Ի�_left.png").getImage()
					,new ImageIcon("images/Player/���Ի�/���Ի�2_left.png").getImage()
					,new ImageIcon("images/Player/���Ի�/���Ի�2_left.png").getImage()
					,new ImageIcon("images/Player/���Ի�/���Ի�2_left.png").getImage()
					,new ImageIcon("images/Player/���Ի�/���Ի�_left.png").getImage()
					,new ImageIcon("images/Player/���Ի�/���Ի�_left.png").getImage()
					,new ImageIcon("images/Player/���Ի�/���Ի�2_left.png").getImage()
					,new ImageIcon("images/Player/���Ի�/���Ի�2_left.png").getImage()
					,new ImageIcon("images/Player/���Ի�/���Ի�2_left.png").getImage()
					,new ImageIcon("images/Player/���Ի�/���Ի�_left.png").getImage()
					,new ImageIcon("images/Player/���Ի�/���Ի�_left.png").getImage()
					,new ImageIcon("images/Player/���Ի�/���Ի�2_left.png").getImage()
					,new ImageIcon("images/Player/���Ի�/���Ի�2_left.png").getImage()};
			this.imagesLeft = imagesLeft;
			
			jumpImg[0] = new ImageIcon("images/Player/���Ի�/���Ի�_����.png");
			jumpImg[1] = new ImageIcon("images/Player/���Ի�/���Ի�_����_left.png");
			
			ImageIcon hitImg[][] = {
					{new ImageIcon("images/Player/���Ի�/���Ի���.png"), new ImageIcon("images/Player/���Ի�/���Ի���_left.png")},
					{new ImageIcon("images/Player/���Ի�/���Ի�_ȭ�����.png"), new ImageIcon("images/Player/���Ի�/���Ի�_ȭ�����_left.png")},
					{new ImageIcon("images/Player/���Ի�/���Ի�_�ܰŸ� ����.png"), new ImageIcon("images/Player/���Ի�/���Ի�_�ܰŸ� ����_left.png")}};
			this.hitImg = hitImg;
			
			afterHitImg[0] = new ImageIcon("images/Player/���Ի�/���Ի�_ȭ����.png");
			afterHitImg[1] = new ImageIcon("images/Player/���Ի�/���Ի�_ȭ����_left.png");
			break;
		case 2:
			Image images2[] = {new ImageIcon("images/Player/3�г�/3�г�.png").getImage()
					,new ImageIcon("images/Player/3�г�/3�г�2.png").getImage()
					,new ImageIcon("images/Player/3�г�/3�г�2.png").getImage()
					,new ImageIcon("images/Player/3�г�/3�г�.png").getImage()
					,new ImageIcon("images/Player/3�г�/3�г�.png").getImage()
					,new ImageIcon("images/Player/3�г�/3�г�.png").getImage()
					,new ImageIcon("images/Player/3�г�/3�г�2.png").getImage()
					,new ImageIcon("images/Player/3�г�/3�г�2.png").getImage()
					,new ImageIcon("images/Player/3�г�/3�г�.png").getImage()
					,new ImageIcon("images/Player/3�г�/3�г�.png").getImage()
					,new ImageIcon("images/Player/3�г�/3�г�.png").getImage()
					,new ImageIcon("images/Player/3�г�/3�г�2.png").getImage()
					,new ImageIcon("images/Player/3�г�/3�г�2.png").getImage()
					,new ImageIcon("images/Player/3�г�/3�г�.png").getImage()
					,new ImageIcon("images/Player/3�г�/3�г�.png").getImage()};
			this.images = images2;
			
			Image imagesLeft2[] = {new ImageIcon("images/Player/3�г�/3�г�_left.png").getImage()
					,new ImageIcon("images/Player/3�г�/3�г�2_left.png").getImage()
					,new ImageIcon("images/Player/3�г�/3�г�2_left.png").getImage()
					,new ImageIcon("images/Player/3�г�/3�г�_left.png").getImage()
					,new ImageIcon("images/Player/3�г�/3�г�_left.png").getImage()
					,new ImageIcon("images/Player/3�г�/3�г�_left.png").getImage()
					,new ImageIcon("images/Player/3�г�/3�г�2_left.png").getImage()
					,new ImageIcon("images/Player/3�г�/3�г�2_left.png").getImage()
					,new ImageIcon("images/Player/3�г�/3�г�_left.png").getImage()
					,new ImageIcon("images/Player/3�г�/3�г�_left.png").getImage()
					,new ImageIcon("images/Player/3�г�/3�г�_left.png").getImage()
					,new ImageIcon("images/Player/3�г�/3�г�2_left.png").getImage()
					,new ImageIcon("images/Player/3�г�/3�г�2_left.png").getImage()
					,new ImageIcon("images/Player/3�г�/3�г�_left.png").getImage()
					,new ImageIcon("images/Player/3�г�/3�г�_left.png").getImage()};
			this.imagesLeft = imagesLeft2;
			
			jumpImg[0] = new ImageIcon("images/Player/3�г�/3�г�_����.png");
			jumpImg[1] = new ImageIcon("images/Player/3�г�/3�г�_����_left.png");
			break;
		case 3:
			Image images3[] = {new ImageIcon("images/Player/������/������.png").getImage()
					,new ImageIcon("images/Player/������/������2.png").getImage()
					,new ImageIcon("images/Player/������/������2.png").getImage()
					,new ImageIcon("images/Player/������/������.png").getImage()
					,new ImageIcon("images/Player/������/������.png").getImage()
					,new ImageIcon("images/Player/������/������.png").getImage()
					,new ImageIcon("images/Player/������/������2.png").getImage()
					,new ImageIcon("images/Player/������/������2.png").getImage()
					,new ImageIcon("images/Player/������/������.png").getImage()
					,new ImageIcon("images/Player/������/������.png").getImage()
					,new ImageIcon("images/Player/������/������.png").getImage()
					,new ImageIcon("images/Player/������/������2.png").getImage()
					,new ImageIcon("images/Player/������/������2.png").getImage()
					,new ImageIcon("images/Player/������/������.png").getImage()
					,new ImageIcon("images/Player/������/������.png").getImage()};
			this.images = images3;
			
			Image imagesLeft3[] = {new ImageIcon("images/Player/������/������_left.png").getImage()
					,new ImageIcon("images/Player/������/������2_left.png").getImage()
					,new ImageIcon("images/Player/������/������2_left.png").getImage()
					,new ImageIcon("images/Player/������/������_left.png").getImage()
					,new ImageIcon("images/Player/������/������_left.png").getImage()
					,new ImageIcon("images/Player/������/������_left.png").getImage()
					,new ImageIcon("images/Player/������/������2_left.png").getImage()
					,new ImageIcon("images/Player/������/������2_left.png").getImage()
					,new ImageIcon("images/Player/������/������_left.png").getImage()
					,new ImageIcon("images/Player/������/������_left.png").getImage()
					,new ImageIcon("images/Player/������/������_left.png").getImage()
					,new ImageIcon("images/Player/������/������2_left.png").getImage()
					,new ImageIcon("images/Player/������/������2_left.png").getImage()
					,new ImageIcon("images/Player/������/������_left.png").getImage()
					,new ImageIcon("images/Player/������/������_left.png").getImage()};
			this.imagesLeft = imagesLeft3;
			
			jumpImg[0] = new ImageIcon("images/Player/������/������_����.png");
			jumpImg[1] = new ImageIcon("images/Player/������/������_����_left.png");
			break;
		default:
			break;
		}
	}
	
	//player �������� �̵� (key)
	public void p_moveLeft() {
		setStatus(2);
		if(cnt == imagesLeft.length) {
			cnt = 0;
		}
		if(!isHit()) {
			if(isJump()||isFall()) {
				setImage(jumpImg[1].getImage());
			}else {
				setImage(imagesLeft[cnt]);	
			}
		}
		cnt++;
		if(x > 0) {
			x -= 15;
			distance -= 15;
		}else {
			x = 0;
		}
	}
	
	//player ���������� �̵� (key)
	public void p_moveRight() {
		setStatus(1);
		if(cnt == images.length) {
			cnt = 0;
		}
		if(!isHit()) {
			if(isJump()||isFall()) {
				setImage(jumpImg[0].getImage());
			}else {
				setImage(images[cnt]);
			}
		}
		cnt++;
		if(distance < back.getWidth(null)-130) {
			x += 15;
			distance += 15;
		}	
	}
	
	// ȭ�� �߰����� ������ �̵�(key)
	public void p_moveRight(int num) {
		setStatus(1);
		if(cnt == images.length) {
			cnt = 0;
		}
		if(!isHit()) {
			if(isJump()||isFall()) {
				setImage(jumpImg[0].getImage());
				
			}else {
				setImage(images[cnt]);	
			}
		}
		cnt++;
		distance += 10;
	}
	
	// ���⶧ �̹��� ����
	public void stop() {
		cnt = 0;
		if(!isHit()) {
			if(isJump()||isFall()) {
				setImage(jumpImg[status-1].getImage());
			}else {
				if (status == 1) {
					setImage(images[cnt]);	
				}else if (status == 2) {
					setImage(imagesLeft[cnt]);
				}		
			}
		}
	}
	
	// �Ѿ� �߻�
	public void p_hit() {
		switch (hit_status) {
		case 1:
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						setHit(true);
						setImage(hitImg[hit_status-1][status-1].getImage());
						shots.add(new Shot(mainPanel, x+50, y+15, status,1));
						Thread.sleep(100);
						if(status==1) {
							setImage(images[0]);
						}else {
							setImage(imagesLeft[0]);
						}
						Thread.sleep(100);
						setHit(false);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
			break;
		case 2:
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						setHit(true);
						setImage(hitImg[hit_status-1][status-1].getImage());
						Thread.sleep(200);
						shots.add(new Shot(mainPanel, x+50, y+15, status,2));
						setImage(afterHitImg[status-1].getImage());
						Thread.sleep(100);
						if(status==1) {
							setImage(images[0]);
						}else {
							setImage(imagesLeft[0]);
						}
						Thread.sleep(100);
						setHit(false);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
			break;
		case 3:
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						setHit(true);
						setImage(hitImg[hit_status-1][status-1].getImage());
						Thread.sleep(200);
						if(status==1) {
							setImage(images[0]);
						}else {
							setImage(imagesLeft[0]);
						}
						Thread.sleep(200);
						setHit(false);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
			break;
		default:
			break;
		}
		
	}
	
	// ������ ���� �� 
	public void damaged(int damage) {
		if(invincibility == 255) { //������ 255�϶�
			Sound("music/hitSound.wav", false);
			Sound("music/ouchSound.wav", false);
			invincibility = 80;
			this.hp -= damage;
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						//������ �ٲپ��ش�. 
						Thread.sleep(500);
						invincibility=254;
						Thread.sleep(500);
						invincibility=80;
						Thread.sleep(500);
						invincibility=254;
						Thread.sleep(500);
						invincibility=80;
						Thread.sleep(500);
						invincibility=255;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}
	
	public void deleteShot() { // ȭ�� ������ ���� �Ѿ��� ���ִ� �޼���
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					for(int i = 0; i < shots.size(); i++) {
						try {
							if(shots.get(i).getX()>view.getWidth()-100 || shots.get(i).getX()<0
									||shots.get(i).getX()>getX()+getShot_range()||shots.get(i).getX()<getX()-getShot_range()) {
								shots.remove(i); //���� �Ѿ� ����
							}
						}catch (IndexOutOfBoundsException e) {
							
						}catch (NullPointerException e) {
							e.printStackTrace();
						}
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
	
	// �ʵ�� �������� �޼��� 
	public void fall() {
		new Thread(new Runnable() {
			@Override
			public void run() {

				while (true) {
					int foot = getY() + image.getHeight(null); // ĳ���� �� ��ġ �罺ĵ

					// �߹ٴ��� ���Ǻ��� ���� ������ �۵�
					if (foot < field // ���߿� ������
							&& !isJump() // ���� ���� �ƴϸ�
							&& !isFall()) { // �������� ���� �ƴ� ��
						
						setFall(true); // �������� ������ ��ȯ

						long t1 = Util.getTime(); // ����ð��� �����´�
						long t2;
						int set = 1; // ó�� ���Ϸ� (0~10) ���� �׽�Ʈ�غ���
						while (foot < field) { // ���� ���ǿ� ��� ������ �ݺ�
							if(foot > field-10) {
								setFall(false);
								switch (status) {
								case 1:
									setImage(images[0]);
									break;
								case 2:
									setImage(imagesLeft[0]);
									break;
								default:
									break;
								}
							}
							t2 = Util.getTime() - t1; // ���� �ð����� t1�� ����
							int fallY = set + (int) ((t2) / 40); // ���Ϸ��� �ø���.
							foot = getY() + image.getHeight(null); // ĳ���� �� ��ġ �罺ĵ
							if (foot + fallY >= field) { // �߹ٴ�+���Ϸ� ��ġ�� ���Ǻ��� ���ٸ� ���Ϸ��� �����Ѵ�.
								fallY = field - foot;
							}
							setY(getY() + fallY); // Y��ǥ�� ���Ϸ��� ���Ѵ�
							if (isJump()) { // �������ٰ� ������ �ϸ� ��������
								break;
							}
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						setFall(false);
						
						if (!isJump()) { // ���� ���� ��� ���� ���� �ƴ� �� �������� ī��Ʈ�� 0���� ����
							setCountJump(0);
							
						}
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
	
	// ���� ����
	public void jump() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				setCountJump(getCountJump() + 1); // ���� Ƚ�� ����
				int nowJump = getCountJump(); // �̹������� �������� ������������ ����
				setJump(true); // ���������� ����
				long t1 = Util.getTime(); // ����ð��� �����´�
				long t2;
				int set = 12; // ���� ��� ����(0~20) ������ �ٲ㺸��
				int jumpY = 1; // 1�̻����θ� �����ϸ� �ȴ�.(while�� ���� ����)
				setImage(jumpImg[status-1].getImage());
				setImage(jumpImg[status-1].getImage());
				
				while (jumpY >= 0) { // ��� ���̰� 0�϶����� �ݺ�
					
					t2 = Util.getTime() - t1; // ���� �ð����� t1�� ����
					jumpY = set - (int) ((t2) / 40); // jumpY �� �����Ѵ�.
					setY(getY() - jumpY); // Y���� �����Ѵ�.
					if (nowJump != getCountJump()) { // ������ �ѹ� ���Ǹ� ù��° ������ �����.
						break;
					}
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				if (nowJump == getCountJump()) { // ������ ��¥ ������ ���� Ȯ��
					setJump(false); // �������¸� false�� ����
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
	
	public Player(JPanel main, int stage){
		this.mainPanel = main;
		setX(200);
		setY(100);
		setDistance(200);
		setScore(0);
		setInvincibility(255);
		setStatus(1);
		shots.clear();
		setImages(stage);
		setImage(images[0]);
	}
}