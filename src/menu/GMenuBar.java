package menu;

import java.awt.Font;

import javax.swing.JMenuBar;

import frame.GPanel;
import main.GConstants.EMenu;

public class GMenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;
	
	private GFileMenu fileMenu;
	private GEditMenu editMenu;
	private GGraphicsMenu graphicsMenu;


	
	public GMenuBar() {
		
		this.fileMenu=new GFileMenu(EMenu.eFile.getText());
		this.fileMenu.setFont((new Font("¸¼Àº °íµñ", Font.PLAIN, 13)));
		this.add(this.fileMenu);
		
		this.editMenu=new GEditMenu(EMenu.eEdit.getText());
		this.editMenu.setFont((new Font("¸¼Àº °íµñ", Font.PLAIN, 13)));
		this.add(this.editMenu);
		
		this.graphicsMenu=new GGraphicsMenu(EMenu.eGraphics.getText());
		this.graphicsMenu.setFont((new Font("¸¼Àº °íµñ", Font.PLAIN, 13)));
		this.add(this.graphicsMenu);
	}

	public void setAssociation(GPanel panel) {
		this.fileMenu.setAssociation(panel);
		this.editMenu.setAssociation(panel);
		this.graphicsMenu.setAssociation(panel);
	}

	public void initialize() {
	}

}
