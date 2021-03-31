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
	// �ʿ��� �޼��带 �׶��׶� �������̵� �ϱ� ���ؼ� ���� �߻�Ŭ������ ���� ��ӹ�����
	// ���⼭ ��� implement �޾Ƶθ� �� ���� �޼��嵵 �������̵� �ؾ� ��
	private JFrame frame;
	
	private int location = 1; // ���� �г��� ��ġ(1-Start, 2-info, 3-ranking, 4-stage, 5-game, 6-gameover, 7- clear, 8- story)
	private int stage = 0;
	private StartPanel startPanel; 			// �����г�
	private SelectStagePanel selectStagePanel;
	private StoryPanel storyPanel;
	private Stage1Panel stage1Panel;
	private Stage2Panel stage2Panel;
	private Stage3Panel stage3Panel;
	// private GamePanel gamePanel; 			// �����г�
	private GameOverPanel gameOverPanel; 	// ���ӿ����г�
	private ClearPanel clearPanel; 			// Ŭ�����г�
	private RankingPanel rankingPanel; 		// ��ŷ �г�
	private InfoPanel infoPanel;			// ���� �г�
	
	private CardLayout cl; // ī�� ���̾ƿ� - �г� �������� �������� ������ �� �ְ� ����
	
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
		frame.setTitle("�ѽ��ѽ�"); // ���α׷� �̸� ����
		frame.setUndecorated(true); // ��� �� ���ֱ�
		frame.setVisible(true); // â ���̰��ϱ�
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // ��üȭ��
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		cl = new CardLayout();
		frame.getContentPane().setLayout(cl); // �������� ī�巹�̾ƿ����� ����
		
		// �гο� main�� �ִ� �����ʸ� �־���
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
		
		// ��� �г��� ���̾ƿ��� null�� ��ȯ
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
		
		// �����ӿ� �гε��� �߰��Ѵ�.(ī�� ���̾ƿ��� ���� �гε�)
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
		
		cl.show(frame.getContentPane(), "start"); // start�г��� ī�巹�̾ƿ� �ֻ������ ����
		startPanel.requestFocus(); // �����ʸ� start�гο� ������ ��
	}
	
	@Override
	public void mousePressed(MouseEvent e) { // mouseClicked�� ���氡��
		if (e.getComponent().getName().equals("StartButton")) {
			startPanel.closeMusic(); // ����ȭ�� ���� ��� ����
			cl.show(frame.getContentPane(), "selectStage"); // stage�г��� ī�巹�̾ƿ� �ֻ������ ����
			selectStagePanel.playMusic(); // ��������ȭ�� ���� ���
			selectStagePanel.requestFocus(); // �����ʸ� stage�гο� ������ ��
			frame.getContentPane().remove(stage1Panel); // ��� �ߴ� ���� �г��� �����ӿ��� ����
			frame.getContentPane().remove(stage2Panel); // ��� �ߴ� ���� �г��� �����ӿ��� ����
			frame.getContentPane().remove(stage3Panel); // ��� �ߴ� ���� �г��� �����ӿ��� ����
			location = 4; // ���� �г� stage
			
		}else if (e.getComponent().getName().equals("GameButton")) {
			selectStagePanel.closeMusic();
			frame.getContentPane().remove(storyPanel); // ��� �ߴ� ���ѷα� �г��� �����ӿ��� ����
			storyPanel = new StoryPanel(this, stage); // �� ���ѷα� �г� ����
			storyPanel.setLayout(null);
			frame.getContentPane().add(storyPanel, "story");
			cl.show(frame.getContentPane(), "story"); // prologue�г��� ī�巹�̾ƿ� �ֻ������ ����
			storyPanel.playMusic(); // ��������ȭ�� ���� ���
			storyPanel.requestFocus(); // �����ʸ� stage�гο� ������ ��
			location = 8; // ���� �г� story
			
		}
		else if (e.getComponent().getName().equals("GameStartButton")) { // StartButton�̶�� �̸��� ���� ��ư�� �����ٸ�
			
			switch (stage) {
			case 1:
				stage1Panel = new Stage1Panel(this, frame, cl); // �� ���� �г� ����
				stage1Panel.setLayout(null);
				stage1Panel.gameStart(); // ���� ���� �޼��� ����
				frame.getContentPane().add(stage1Panel, "stage1"); // �����ӿ� ���� �г� �߰�
				cl.show(frame.getContentPane(), "stage1"); // game�г��� ī�巹�̾ƿ� �ֻ������ ����
				stage1Panel.requestFocus(); // �����ʸ� game�гο� ������ ��
				break;
			case 2:
				stage2Panel = new Stage2Panel(this, frame, cl); // �� ���� �г� ����
				stage2Panel.setLayout(null);
				stage2Panel.gameStart(); // ���� ���� �޼��� ����
				frame.getContentPane().add(stage2Panel, "stage2"); // �����ӿ� ���� �г� �߰�
				cl.show(frame.getContentPane(), "stage2"); // game�г��� ī�巹�̾ƿ� �ֻ������ ����
				stage2Panel.requestFocus(); // �����ʸ� game�гο� ������ ��
				break;
			case 3:
				stage3Panel = new Stage3Panel(this, frame, cl); // �� ���� �г� ����
				stage3Panel.setLayout(null);
				stage3Panel.gameStart(); // ���� ���� �޼��� ����
				frame.getContentPane().add(stage3Panel, "stage3"); // �����ӿ� ���� �г� �߰�
				cl.show(frame.getContentPane(), "stage3"); // game�г��� ī�巹�̾ƿ� �ֻ������ ����
				stage3Panel.requestFocus(); // �����ʸ� game�гο� ������ ��
				break;
			default: break;
			}
			location = 5;  // ���� �г� game
		}
		
		else if (e.getComponent().getName().equals("RankingButton")) { // RankingButton�̶�� �̸��� ���� ��ư�� �����ٸ�
			rankingPanel.scoreList = rankingPanel.setScores();
			startPanel.closeMusic(); // ����ȭ��  ���� ��� ����
			cl.show(frame.getContentPane(), "ranking"); // ranking�г��� ī�巹�̾ƿ� �ֻ������ ����
			rankingPanel.requestFocus(); // �����ʸ� ranking�гο� ������ ��
			location = 3;  // ���� �г� ranking
		}
		
		else if (e.getComponent().getName().equals("ReplayButton")) { // ReplayButton�̶�� �̸��� ���� ��ư�� �����ٸ�
			switch(location) {
			case 4:
				selectStagePanel.closeMusic(); break;
			case 6: // ���ӿ��� ȭ�� ���� ��� ���� 
				gameOverPanel.closeMusic(); break;
			default: break;
			}
			cl.show(frame.getContentPane(), "start"); // start�г��� ī�巹�̾ƿ� �ֻ������ ����
			startPanel.playMusic(); // ����ȭ�� ���� ���
			startPanel.requestFocus(); // �����ʸ� start�гο� ������ ��	
		}
		
		else if (e.getComponent().getName().equals("ReplayButton2")) { // ReplayButton3�̶�� �̸��� ���� ��ư�� �����ٸ�(Ŭ���� ȭ�鿡�� ���÷���)
			if (clearPanel.getName().equals("")||clearPanel.getName().equals("�̸��� �Է����ּ���")) {
				JOptionPane.showMessageDialog(null, "�̸��� �Է����ּ���"); // ĳ���͸� �Ȱ������� �˾�
			}else {
				cl.show(frame.getContentPane(), "start"); // start�г��� ī�巹�̾ƿ� �ֻ������ ����
				startPanel.playMusic(); // ����ȭ�� ���� ���
				startPanel.requestFocus(); // �����ʸ� start�гο� ������ ��
			}
		}
		
		else if(e.getComponent().getName().equals("InfoButton")) { // InfoButton�̶�� �̸��� ���� ��ư�� �����ٸ�
			cl.show(frame.getContentPane(), "info"); // start�г��� ī�巹�̾ƿ� �ֻ������ ����
			startPanel.closeMusic(); // ����ȭ�� ���� ����
			infoPanel.requestFocus(); // �����ʸ� info�гο� ������ ��
			location = 2;
		}
		
		else if (e.getComponent().getName().equals("ExitButton")) { // ExitButton�̶�� �̸��� ���� ��ư�� �����ٸ�
			System.exit(0); 
		}
	}
}