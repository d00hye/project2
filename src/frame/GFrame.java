package frame;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

import menu.GMenuBar;

// ��� - Ȯ���ϱ�����! ��� �߰�!
// �θ� �ȿ� �ڽ��� ���� �� - ����� �޾Ƽ� �߰��ؾ� ��

public class GFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private GMenuBar menuBar;
	private GToolBar toolBar;
	private GPanel panel;
	
	public GFrame() {
		// initialize attributes - ���� ������ ������ ����, ���� �Ӽ�
		this.setTitle("�׸���");
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("resources/icon.png");
		this.setIconImage(img);

		// initialize components �ڽ� �����
		// JPanel�� �ڽ����� �߰��ϴ� �������� ���� ����� �� �߰������� �� ���
		
		this.menuBar= new GMenuBar();
		this.setJMenuBar(this.menuBar);
		
		BorderLayout layoutManager = new BorderLayout();
		this.getContentPane().setLayout(layoutManager);
		
		this.toolBar = new GToolBar();
		this.getContentPane().add(this.toolBar, BorderLayout.NORTH);
		
		this.panel = new GPanel();
		this.getContentPane().add(this.panel, BorderLayout.CENTER);

		// �ڽ� �ΰ��� ���� set association
		
		this.menuBar.setAssociation(this.panel);
		this.toolBar.setAssociation(this.panel);
		
		
	}

	public void initialize() {
		// TODO Auto-generated method stub
		this.menuBar.initialize();
		this.toolBar.initialize();
		this.panel.initialize();
	}
	
		// �Ӽ�, components, association ���� �ʱ�ȭ
}
