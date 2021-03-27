package components;

import java.util.ArrayList;

// �� ��� ����
public class GunMonster extends MonsterThread{ //������ ���͸� ���
	private ArrayList<Shot> GunShotList = new ArrayList<Shot>();

	public GunMonster(int x, int y, int hp, String Image, Player player) {
		super(x, y, hp, Image, player);
	}

	public ArrayList<Shot> getShotList() {
		return GunShotList;
	}

	public void setShotList(ArrayList<Shot> GunShotList) {
		this.GunShotList = GunShotList;
	}

	@Override
	public void run() {
		shot();
		gm_hit();
		super.run();
	}
	
	
	// �Ѿ� �߻� ������ 
	public void shot() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(isStatus()) {
					try {
						GunShotList.add(new Shot(getX()+50, getY()+15, 2));
						Thread.sleep(1000); //1�ʿ� �ѹ� ��� ����
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				GunShotList.clear();
			}
		}).start();
	}	
	
	//�Ѿ��� �´��� Ȯ���ϴ� ������
	public void gm_hit() {
		// �Ѿ��� �ϳ��϶� ��� ����Ǿ 
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(isStatus()) { 
					try {
						for(int i = 0; i < GunShotList.size(); i++) {
							Shot shot = GunShotList.get(i);
							
							if(player.getX() + player.getImage().getWidth(null) > shot.getX() && player.getX() - player.getImage().getWidth(null) < shot.getX()
									&& player.getY() + player.getImage().getHeight(null) > shot.getY() && player.getY() - player.getImage().getHeight(null) < shot.getY()) {
				    			if(player.getInvincibility()==255) {
									GunShotList.remove(i);
									player.damaged(200);
								}
							}	
						}
					}catch (NullPointerException e) {
						continue;
					}
				}
			}
		}).start();
	}
}