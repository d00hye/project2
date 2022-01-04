package menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import frame.GPanel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.GConstants.EEditMenuItem;

public class GEditMenu extends JMenu {
	private static final long serialVersionUID = 1L;
	private GPanel panel;

	public GEditMenu(String text) {
		super(text);
		ActionHandler actionHandler = new ActionHandler();
		for (EEditMenuItem eEditMenuItem : EEditMenuItem.values()) {
			JMenuItem menuItem = new JMenuItem(eEditMenuItem.getText());
			menuItem.setActionCommand(eEditMenuItem.name());
			menuItem.addActionListener(actionHandler);
			menuItem.setFont((new Font("¸¼Àº °íµñ", Font.PLAIN, 12)));
			this.add(menuItem);
		}
	}

	private void undo() {
		this.panel.undo();
	}

	private void redo() {
		this.panel.redo();
	}

	private void copy() {
		this.panel.copy();
	}

	private void paste() {
		this.panel.paste();
	}

	private void cut() {
		this.panel.cut();

	}

	private void delete() {
		this.panel.delete();
	}
	
//	private void group() {
//		this.panel.group();
//	}

	
	private void first() {
		this.panel.first();
	}
	
	private void last() {
		this.panel.last();
	}

	public void setAssociation(GPanel panel) {
		this.panel = panel;
	}

	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			EEditMenuItem eEditMenuItem = EEditMenuItem.valueOf(e.getActionCommand());
			switch (eEditMenuItem) {
			case eUndo:
				undo();
				break;
			case eRedo:
				redo();
				break;
			case eCut:
				cut();
				break;
			case eCopy:
				copy();
				break;
			case ePaste:
				paste();
				break;
			case eDelete:
				delete();
				break;
//			case eGroup:
//				group();
//				break;
//			case eUnGroup:
//				break;
			case eFirst:
				first();
				break;
			case eLast:
				last();
				break;
			default:
				break;
			}

		}
	}
}
