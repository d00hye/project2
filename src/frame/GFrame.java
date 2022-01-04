package frame;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

import menu.GMenuBar;

// 상속 - 확장하기위함! 기능 추가!
// 부모 안에 자식이 들어가야 함 - 상속을 받아서 추가해야 함

public class GFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private GMenuBar menuBar;
	private GToolBar toolBar;
	private GPanel panel;
	
	public GFrame() {
		// initialize attributes - 내가 가지는 고유의 값들, 나의 속성
		this.setTitle("그림판");
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("resources/icon.png");
		this.setIconImage(img);

		// initialize components 자식 만들기
		// JPanel을 자식으로 추가하는 것이지만 많은 기능이 더 추가됨으로 또 상속
		
		this.menuBar= new GMenuBar();
		this.setJMenuBar(this.menuBar);
		
		BorderLayout layoutManager = new BorderLayout();
		this.getContentPane().setLayout(layoutManager);
		
		this.toolBar = new GToolBar();
		this.getContentPane().add(this.toolBar, BorderLayout.NORTH);
		
		this.panel = new GPanel();
		this.getContentPane().add(this.panel, BorderLayout.CENTER);

		// 자식 두개를 연결 set association
		
		this.menuBar.setAssociation(this.panel);
		this.toolBar.setAssociation(this.panel);
		
		
	}

	public void initialize() {
		// TODO Auto-generated method stub
		this.menuBar.initialize();
		this.toolBar.initialize();
		this.panel.initialize();
	}
	
		// 속성, components, association 순서 초기화
}
