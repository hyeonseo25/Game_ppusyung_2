package components;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

public class Monster {
	private Player player;
	private int monstercnt = 0; // ���ݱ��� �߰��� ������ ������ ��
	private Dimension view = Toolkit.getDefaultToolkit().getScreenSize();
	private ArrayList<MonsterThread> monsterList = new ArrayList<>(); // Monster ��ü�� ��� ArrayList
	
	MonsterThread[] monster = { // ���� �� ���͵��� ������ ���� �迭
    		new MonsterThread(2250, 500, 300, "images/monsters/����������ӱ���.gif", player),
    		new MonsterThread(2500, 450, 100, "images/monsters/�����ӱ���.gif", player),
    		new MonsterThread(3350, 400, 400, "images/monsters/��������.gif", player),
    		new MonsterThread(4000, 450, 100, "images/monsters/���ɷ�����.gif", player),
    		new MonsterThread(4190, 450, 100, "images/monsters/å����.gif", player),
    		new GunMonster(4520, 500, 100, "images/monsters/���ɷ�����.gif", player),
    		new MonsterThread(5570, 450, 100, "images/monsters/�����̱���.gif", player),
    		new MonsterThread(5990, 400, 100, "images/monsters/��������.gif", player),
    		new MonsterThread(6080, 450, 100, "images/monsters/�����̱���.gif", player),
    		new MonsterThread(6560, 450, 100, "images/monsters/�����ӱ���.gif", player),
    		new MonsterThread(8210, 400, 400, "images/monsters/��������.gif", player),
    		new MonsterThread(8240, 450, 300, "images/monsters/����������ӱ���.gif", player),
    		new MonsterThread(9460, 300, 1000, "images/monsters/�����ӱ�������.gif", player),
    		new MonsterThread(10520, 450, 100, "images/monsters/å����.gif", player),
    		new MonsterThread(10760, 450, 100, "images/monsters/�����̱���.gif", player),
    		new MonsterThread(11130, 450, 100, "images/monsters/���ɷ�����.gif", player),
    		new GunMonster(11430, 400, 400, "images/monsters/��������.gif", player)
    		};
	
	public Monster(Player player) {
		this.player = player;
	}
	
	public int getMonsterCnt() {
		return monstercnt;
	}

	public ArrayList<MonsterThread> getMonsterList() {
		return monsterList;
	}
	
    public void createMonsters() { // �޼ҵ� ȣ�� �� Monster ��ü�� �迭�� �߰�
    	monsterList.clear();
		monsterList.add(new MonsterThread(1200, 450, 100, "images/monsters/�����̱���.gif", player));
		monsterList.add(new MonsterThread(1600, 450, 130, "images/monsters/���ɷ�����.gif", player));
        for (int i = 0; i < monsterList.size(); i++) {
        	monsterList.get(i).start();
        }
    }
    
    public void addMonster() {
    	monster[monstercnt].setPlayer(player); // �÷��̾� ��ü ���� ����
    	monster[monstercnt].setX((int) view.getWidth()+50); // ȭ�� ������ ����
    	monsterList.add(monster[monstercnt]);
    	monsterList.get(monsterList.size()-1).start(); // ���� �ֱٿ� ����Ʈ�� �߰��� ���� ��ü ������ ����
    	if(monstercnt< monster.length-1) {
    		monstercnt++;
    	}
    }
}