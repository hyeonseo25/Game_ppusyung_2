package components;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

public class Monster {
	private Player player;
	private int monstercnt = 0; // 지금까지 추가로 생성된 몬스터의 수
	private int stage;
	private Dimension view = Toolkit.getDefaultToolkit().getScreenSize();
	private ArrayList<MonsterThread> monsterList = new ArrayList<>(); // Monster 객체를 담는 ArrayList
	
	MonsterThread[] monster1 = { // 스폰 될 몬스터들의 정보를 담은 배열
    		new MonsterThread(2250, 500, 200, "images/monsters/노란색슬라임괴물.gif", player),
    		new MonsterThread(2500, 450, 100, "images/monsters/슬라임괴물.gif", player),
    		new MonsterThread(3350, 400, 250, "images/monsters/날개괴물.gif", player),
    		new MonsterThread(4000, 450, 100, "images/monsters/물걸레괴물.gif", player),
    		new MonsterThread(4190, 450, 100, "images/monsters/책괴물.gif", player),
    		new MonsterThread(4520, 500, 100, "images/monsters/물걸레괴물.gif", player),
    		new MonsterThread(5570, 570, 100, "images/monsters/미사일.png", player),
    		new MonsterThread(5990, 400, 100, "images/monsters/날개괴물.gif", player),
    		new MonsterThread(6080, 570, 100, "images/monsters/노란색슬라임괴물.gif", player),
    		new MonsterThread(6560, 450, 100, "images/monsters/슬라임괴물.gif", player),
    		new MonsterThread(8210, 400, 250, "images/monsters/날개괴물.gif", player),
    		new MonsterThread(8240, 450, 200, "images/monsters/노란색슬라임괴물.gif", player),
    		new MonsterThread(9460, 300, 100, "images/monsters/슬라임괴물.gif", player),
    		new MonsterThread(10520, 450, 100, "images/monsters/책괴물.gif", player),
    		new MonsterThread(10760, 570, 100, "images/monsters/미사일.png", player),
    		new MonsterThread(11130, 450, 100, "images/monsters/물걸레괴물.gif", player),
    		new MonsterThread(11430, 400, 250, "images/monsters/날개괴물.gif", player)
    		};
	
	MonsterThread[] monster2 = { // 스폰 될 몬스터들의 정보를 담은 배열
    		new MonsterThread(2250, 500, 200, "images/monsters/노란색슬라임괴물.gif", player),
    		new MonsterThread(2500, 450, 100, "images/monsters/슬라임괴물.gif", player),
    		new MonsterThread(3350, 400, 300, "images/monsters/날개괴물.gif", player),
    		new MonsterThread(4000, 450, 100, "images/monsters/물걸레괴물.gif", player),
    		new MonsterThread(4190, 450, 100, "images/monsters/책괴물.gif", player),
    		new MonsterThread(4520, 500, 100, "images/monsters/물걸레괴물.gif", player),
    		new MonsterThread(5570, 570, 100, "images/monsters/미사일.png", player),
    		new MonsterThread(5990, 400, 300, "images/monsters/날개괴물.gif", player),
    		new MonsterThread(6080, 570, 100, "images/monsters/미사일.png", player),
    		new MonsterThread(6560, 450, 100, "images/monsters/슬라임괴물.gif", player),
    		new MonsterThread(8210, 400, 300, "images/monsters/날개괴물.gif", player),
    		new MonsterThread(8240, 450, 200, "images/monsters/노란색슬라임괴물.gif", player),
    		new MonsterThread(9460, 300, 900, "images/monsters/슬라임괴물보스.gif", player),
    		new MonsterThread(10520, 450, 100, "images/monsters/책괴물.gif", player),
    		new MonsterThread(10760, 570, 100, "images/monsters/미사일.png", player),
    		new MonsterThread(11130, 450, 100, "images/monsters/물걸레괴물.gif", player),
    		new MonsterThread(11430, 400, 300, "images/monsters/날개괴물.gif", player)
    		};
	
	MonsterThread[] monster3 = { // 스폰 될 몬스터들의 정보를 담은 배열
    		new MonsterThread(2250, 500, 300, "images/monsters/노란색슬라임괴물.gif", player),
    		new MonsterThread(2500, 450, 100, "images/monsters/슬라임괴물.gif", player),
    		new MonsterThread(3350, 400, 400, "images/monsters/날개괴물.gif", player),
    		new MonsterThread(4000, 450, 100, "images/monsters/물걸레괴물.gif", player),
    		new MonsterThread(4190, 450, 100, "images/monsters/책괴물.gif", player),
    		new GunMonster(4520, 500, 100, "images/monsters/물걸레괴물.gif", player),
    		new MonsterThread(5570, 570, 100, "images/monsters/미사일.png", player),
    		new MonsterThread(5990, 400, 100, "images/monsters/날개괴물.gif", player),
    		new MonsterThread(6080, 570, 100, "images/monsters/미사일.png", player),
    		new MonsterThread(6560, 450, 100, "images/monsters/슬라임괴물.gif", player),
    		new MonsterThread(8210, 400, 400, "images/monsters/날개괴물.gif", player),
    		new MonsterThread(8240, 450, 300, "images/monsters/노란색슬라임괴물.gif", player),
    		new MonsterThread(9460, 300, 1000, "images/monsters/슬라임괴물보스.gif", player),
    		new MonsterThread(10520, 450, 100, "images/monsters/책괴물.gif", player),
    		new MonsterThread(10760, 570, 100, "images/monsters/미사일.png", player),
    		new MonsterThread(11130, 450, 100, "images/monsters/물걸레괴물.gif", player),
    		new GunMonster(11430, 400, 400, "images/monsters/날개괴물.gif", player)
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
	
    public void createMonsters(int stage) { // 메소드 호출 시 Monster 객체가 배열에 추가
    	this.stage =  stage;
    	switch (stage) {
		case 1:
			monsterList.clear();
			monsterList.add(new MonsterThread(1200, 570, 100, "images/monsters/책괴물.gif", player));
			monsterList.add(new MonsterThread(1600, 450, 130, "images/monsters/물걸레괴물.gif", player));
	        for (int i = 0; i < monsterList.size(); i++) {
	        	monsterList.get(i).start();
	        }
	        
			break;
		case 2:
			monsterList.clear();
			monsterList.add(new MonsterThread(1200, 570, 100, "images/monsters/미사일.png", player));
			monsterList.add(new MonsterThread(1600, 450, 130, "images/monsters/물걸레괴물.gif", player));
	        for (int i = 0; i < monsterList.size(); i++) {
	        	monsterList.get(i).start();
	        } 
			break;
		case 3:
			monsterList.clear();
			monsterList.add(new MonsterThread(1200, 570, 100, "images/monsters/미사일.png", player));
			monsterList.add(new MonsterThread(1600, 450, 130, "images/monsters/물걸레괴물.gif", player));
	        for (int i = 0; i < monsterList.size(); i++) {
	        	monsterList.get(i).start();
	        }
			break;

		default:
			break;
		}
    	
    }
    
    public void addMonster() {
    	switch (stage) {
		case 1:
			monster1[monstercnt].setPlayer(player); // 플레이어 객체 정보 전달
	    	monster1[monstercnt].setX((int) view.getWidth()+50); // 화면 끝에서 생성
	    	monsterList.add(monster1[monstercnt]);
	    	monsterList.get(monsterList.size()-1).start(); // 가장 최근에 리스트의 추가된 몬스터 객체 스레드 실행
	    	if(monstercnt< monster1.length-1) {
	    		monstercnt++;
	    	}
			break;
		case 2:
			monster2[monstercnt].setPlayer(player); // 플레이어 객체 정보 전달
	    	monster2[monstercnt].setX((int) view.getWidth()+50); // 화면 끝에서 생성
	    	monsterList.add(monster2[monstercnt]);
	    	monsterList.get(monsterList.size()-1).start(); // 가장 최근에 리스트의 추가된 몬스터 객체 스레드 실행
	    	if(monstercnt< monster2.length-1) {
	    		monstercnt++;
	    	}
			break;
		case 3:
			monster3[monstercnt].setPlayer(player); // 플레이어 객체 정보 전달
	    	monster3[monstercnt].setX((int) view.getWidth()+50); // 화면 끝에서 생성
	    	monsterList.add(monster3[monstercnt]);
	    	monsterList.get(monsterList.size()-1).start(); // 가장 최근에 리스트의 추가된 몬스터 객체 스레드 실행
	    	if(monstercnt< monster3.length-1) {
	    		monstercnt++;
	    	}
			break;
		default:
			break;
		}
    	
    }
}