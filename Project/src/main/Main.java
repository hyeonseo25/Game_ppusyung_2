package main;

import java.awt.CardLayout;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import panels.ClearPanel;
import panels.StartPanel;
import panels.GameOverPanel;
import panels.InfoPanel;
import panels.StoryPanel;
import panels.RankingPanel;
import panels.SelectStagePanel;
import panels.Stage1Panel;
import panels.Stage2Panel;
import panels.Stage3Panel;

public class Main extends ListenerAdapter{ 
	// 필요한 메서드를 그때그때 오버라이드 하기 위해서 따로 추상클래스를 빼서 상속받은듯
	// 여기서 모두 implement 받아두면 안 쓰는 메서드도 오버라이드 해야 함
	private JFrame frame;
	
	private int location = 1; // 현재 패널의 위치(1-Start, 2-info, 3-ranking, 4-stage, 5-game, 6-gameover, 7- clear, 8- story)
	private int stage = 0;
	private StartPanel startPanel; 			// 시작패널
	private SelectStagePanel selectStagePanel;
	private StoryPanel storyPanel;
	private Stage1Panel stage1Panel;
	private Stage2Panel stage2Panel;
	private Stage3Panel stage3Panel;
	// private GamePanel gamePanel; 			// 게임패널
	private GameOverPanel gameOverPanel; 	// 게임오버패널
	private ClearPanel clearPanel; 			// 클리어패널
	private RankingPanel rankingPanel; 		// 랭킹 패널
	private InfoPanel infoPanel;			// 설명 패널
	
	private CardLayout cl; // 카드 레이아웃 - 패널 여러개를 돌려가며 보여줄 수 있게 해줌
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main main = new Main();
	}
	
	public Main() {
		init();
	}
	
	public ClearPanel getClearPanel() {
		return clearPanel;
	}
	
	public GameOverPanel getGameOverPanel() {
		return gameOverPanel;
	}
	
	public void setStage(int stage) {
		this.stage = stage;
	}
	
	public void setLocation(int location) {
		this.location = location;
	}
	
	public CardLayout getCl() {
		return cl;
	}
	
	private void init() {
		frame = new JFrame();
		frame.setTitle("뿌슝뿌슝"); // 프로그램 이름 지정
		frame.setUndecorated(true); // 상단 줄 없애기
		frame.setVisible(true); // 창 보이게하기
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // 전체화면
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		cl = new CardLayout();
		frame.getContentPane().setLayout(cl); // 프레임을 카드레이아웃으로 변경
		
		// 패널에 main에 있는 리스너를 넣어줌
		startPanel = new StartPanel(this); 
		selectStagePanel = new SelectStagePanel(this); 
		//gamePanel = new GamePanel(this, frame, cl);
		storyPanel = new StoryPanel(this, stage);
		stage1Panel = new Stage1Panel(this, frame, cl);
		stage2Panel = new Stage2Panel(this, frame, cl);
		stage3Panel = new Stage3Panel(this, frame, cl);
		gameOverPanel = new GameOverPanel(this);
		clearPanel = new ClearPanel(this);
		rankingPanel = new RankingPanel(this);
		infoPanel = new InfoPanel(this);
		
		// 모든 패널의 레이아웃을 null로 변환
		startPanel.setLayout(null);
		selectStagePanel.setLayout(null);
		storyPanel.setLayout(null);
		stage1Panel.setLayout(null);
		stage2Panel.setLayout(null);
		stage3Panel.setLayout(null);
		//gamePanel.setLayout(null);
		gameOverPanel.setLayout(null);
		clearPanel.setLayout(null);
		rankingPanel.setLayout(null);
		infoPanel.setLayout(null);
		
		// 프레임에 패널들을 추가한다.(카드 레이아웃을 위한 패널들)
		frame.getContentPane().add(startPanel, "start");
		frame.getContentPane().add(selectStagePanel, "selectStage");
		frame.getContentPane().add(storyPanel, "story");
		frame.getContentPane().add(stage1Panel, "stage1");
		frame.getContentPane().add(stage2Panel, "stage2");
		frame.getContentPane().add(stage3Panel, "stage3");
		// frame.getContentPane().add(gamePanel, "game");
		frame.getContentPane().add(gameOverPanel, "gameover");
		frame.getContentPane().add(clearPanel, "clear");
		frame.getContentPane().add(rankingPanel, "ranking");
		frame.getContentPane().add(infoPanel, "info");
		
		cl.show(frame.getContentPane(), "start"); // start패널을 카드레이아웃 최상단으로 변경
		startPanel.requestFocus(); // 리스너를 start패널에 강제로 줌
	}
	
	@Override
	public void mousePressed(MouseEvent e) { // mouseClicked로 변경가능
		if (e.getComponent().getName().equals("StartButton")) {
			startPanel.closeMusic(); // 시작화면 음악 재생 중지
			cl.show(frame.getContentPane(), "selectStage"); // stage패널을 카드레이아웃 최상단으로 변경
			selectStagePanel.playMusic(); // 스테이지화면 음악 재생
			selectStagePanel.requestFocus(); // 리스너를 stage패널에 강제로 줌
			frame.getContentPane().remove(stage1Panel); // 방금 했던 게임 패널을 프레임에서 삭제
			frame.getContentPane().remove(stage2Panel); // 방금 했던 게임 패널을 프레임에서 삭제
			frame.getContentPane().remove(stage3Panel); // 방금 했던 게임 패널을 프레임에서 삭제
			location = 4; // 현재 패널 stage
			
		}else if (e.getComponent().getName().equals("GameButton")) {
			selectStagePanel.closeMusic();
			frame.getContentPane().remove(storyPanel); // 방금 했던 프롤로그 패널을 프레임에서 삭제
			storyPanel = new StoryPanel(this, stage); // 새 프롤로그 패널 생성
			storyPanel.setLayout(null);
			frame.getContentPane().add(storyPanel, "story");
			cl.show(frame.getContentPane(), "story"); // prologue패널을 카드레이아웃 최상단으로 변경
			storyPanel.playMusic(); // 스테이지화면 음악 재생
			storyPanel.requestFocus(); // 리스너를 stage패널에 강제로 줌
			location = 8; // 현재 패널 story
			
		}
		else if (e.getComponent().getName().equals("GameStartButton")) { // StartButton이라는 이름을 가진 버튼을 눌렀다면
			
			switch (stage) {
			case 1:
				stage1Panel = new Stage1Panel(this, frame, cl); // 새 게임 패널 생성
				stage1Panel.setLayout(null);
				stage1Panel.gameStart(); // 게임 시작 메서드 실행
				frame.getContentPane().add(stage1Panel, "stage1"); // 프레임에 게임 패널 추가
				cl.show(frame.getContentPane(), "stage1"); // game패널을 카드레이아웃 최상단으로 변경
				stage1Panel.requestFocus(); // 리스너를 game패널에 강제로 줌
				break;
			case 2:
				stage2Panel = new Stage2Panel(this, frame, cl); // 새 게임 패널 생성
				stage2Panel.setLayout(null);
				stage2Panel.gameStart(); // 게임 시작 메서드 실행
				frame.getContentPane().add(stage2Panel, "stage2"); // 프레임에 게임 패널 추가
				cl.show(frame.getContentPane(), "stage2"); // game패널을 카드레이아웃 최상단으로 변경
				stage2Panel.requestFocus(); // 리스너를 game패널에 강제로 줌
				break;
			case 3:
				stage3Panel = new Stage3Panel(this, frame, cl); // 새 게임 패널 생성
				stage3Panel.setLayout(null);
				stage3Panel.gameStart(); // 게임 시작 메서드 실행
				frame.getContentPane().add(stage3Panel, "stage3"); // 프레임에 게임 패널 추가
				cl.show(frame.getContentPane(), "stage3"); // game패널을 카드레이아웃 최상단으로 변경
				stage3Panel.requestFocus(); // 리스너를 game패널에 강제로 줌
				break;
			default: break;
			}
			location = 5;  // 현재 패널 game
		}
		
		else if (e.getComponent().getName().equals("RankingButton")) { // RankingButton이라는 이름을 가진 버튼을 눌렀다면
			rankingPanel.scoreList = rankingPanel.setScores();
			startPanel.closeMusic(); // 시작화면  음악 재생 중지
			cl.show(frame.getContentPane(), "ranking"); // ranking패널을 카드레이아웃 최상단으로 변경
			rankingPanel.requestFocus(); // 리스너를 ranking패널에 강제로 줌
			location = 3;  // 현재 패널 ranking
		}
		
		else if (e.getComponent().getName().equals("ReplayButton")) { // ReplayButton이라는 이름을 가진 버튼을 눌렀다면
			switch(location) {
			case 4:
				selectStagePanel.closeMusic(); break;
			case 6: // 게임오버 화면 음악 재생 중지 
				gameOverPanel.closeMusic(); break;
			default: break;
			}
			cl.show(frame.getContentPane(), "start"); // start패널을 카드레이아웃 최상단으로 변경
			startPanel.playMusic(); // 시작화면 음악 재생
			startPanel.requestFocus(); // 리스너를 start패널에 강제로 줌	
		}
		
		else if (e.getComponent().getName().equals("ReplayButton2")) { // ReplayButton3이라는 이름을 가진 버튼을 눌렀다면(클리어 화면에서 리플레이)
			if (clearPanel.getName().equals("")||clearPanel.getName().equals("이름을 입력해주세요")) {
				JOptionPane.showMessageDialog(null, "이름을 입력해주세요"); // 캐릭터를 안골랐을경우 팝업
			}else {
				cl.show(frame.getContentPane(), "start"); // start패널을 카드레이아웃 최상단으로 변경
				startPanel.playMusic(); // 시작화면 음악 재생
				startPanel.requestFocus(); // 리스너를 start패널에 강제로 줌
			}
		}
		
		else if(e.getComponent().getName().equals("InfoButton")) { // InfoButton이라는 이름을 가진 버튼을 눌렀다면
			cl.show(frame.getContentPane(), "info"); // start패널을 카드레이아웃 최상단으로 변경
			startPanel.closeMusic(); // 시작화면 음악 중지
			infoPanel.requestFocus(); // 리스너를 info패널에 강제로 줌
			location = 2;
		}
		
		else if (e.getComponent().getName().equals("ExitButton")) { // ExitButton이라는 이름을 가진 버튼을 눌렀다면
			System.exit(0); 
		}
	}
}