package main;

import java.awt.CardLayout;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import panels.ClearPanel;
import panels.StartPanel;
import panels.GameOverPanel;
import panels.GamePanel;
import panels.InfoPanel;
import panels.RankingPanel;
import panels.SelectStagePanel;

public class Main extends ListenerAdapter{ 
	// �ʿ��� �޼��带 �׶��׶� �������̵� �ϱ� ���ؼ� ���� �߻�Ŭ������ ���� ��ӹ�����
	// ���⼭ ��� implement �޾Ƶθ� �� ���� �޼��嵵 �������̵� �ؾ� ��
	private JFrame frame;
	
	private int location = 1; // ���� �г��� ��ġ(1-Start, 2-info, 3-ranking, 4-stage, 5-game, 6-gameover, 7- clear)
	private int stage = 0;
	private StartPanel startPanel; 			// �����г�
	private SelectStagePanel selectStagePanel;
	private GamePanel gamePanel; 			// �����г�
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
		gamePanel = new GamePanel(this, frame, cl);
		gameOverPanel = new GameOverPanel(this);
		clearPanel = new ClearPanel(this);
		rankingPanel = new RankingPanel(this);
		infoPanel = new InfoPanel(this);
		
		// ��� �г��� ���̾ƿ��� null�� ��ȯ
		startPanel.setLayout(null);
		selectStagePanel.setLayout(null);
		gamePanel.setLayout(null);
		gameOverPanel.setLayout(null);
		clearPanel.setLayout(null);
		rankingPanel.setLayout(null);
		infoPanel.setLayout(null);
		
		// �����ӿ� �гε��� �߰��Ѵ�.(ī�� ���̾ƿ��� ���� �гε�)
		frame.getContentPane().add(startPanel, "start");
		frame.getContentPane().add(selectStagePanel, "stage");
		frame.getContentPane().add(gamePanel, "game");
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
			cl.show(frame.getContentPane(), "stage"); // stage�г��� ī�巹�̾ƿ� �ֻ������ ����
			selectStagePanel.playMusic(); // ��������ȭ�� ���� ���
			selectStagePanel.requestFocus(); // �����ʸ� stage�гο� ������ ��
			location = 4; // ���� �г� stage
		}
		else if (e.getComponent().getName().equals("GameButton")) { // StartButton�̶�� �̸��� ���� ��ư�� �����ٸ�
			selectStagePanel.closeMusic();
			
			frame.getContentPane().remove(gamePanel); // ��� �ߴ� ���� �г��� �����ӿ��� ����
			gamePanel = new GamePanel(this, frame, cl); // �� ���� �г� ����
			gamePanel.setLayout(null);
			gamePanel.gameStart(); // ���� ���� �޼��� ����
			frame.getContentPane().add(gamePanel, "game"); // �����ӿ� ���� �г� �߰�
			cl.show(frame.getContentPane(), "game"); // game�г��� ī�巹�̾ƿ� �ֻ������ ����
			gamePanel.requestFocus(); // �����ʸ� game�гο� ������ ��
			
			location = 5;  // ���� �г� game
		}
		
		else if (e.getComponent().getName().equals("RankingButton")) { // RankingButton�̶�� �̸��� ���� ��ư�� �����ٸ�
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