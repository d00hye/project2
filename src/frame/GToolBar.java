package frame;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JToolBar;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import main.GConstants.EShapeTool;

public class GToolBar extends JToolBar {
	private static final long serialVersionUID = 1L;

	// associations
	private GPanel panel;
	private Vector<JButton> buttons;
	private JSpinner s;

//	boolean on=false;
	public GToolBar() {
		// initialize components
		ActionHandler actionHandler = new ActionHandler();
		buttons = new Vector<JButton>();
		for (EShapeTool eShapeTool : EShapeTool.values()) {
			JButton button = new JButton(new ImageIcon(eShapeTool.getPath()));

			button.setActionCommand(eShapeTool.toString());
			button.addActionListener(actionHandler);
			button.setToolTipText(eShapeTool.getText());
			this.buttons.add(button);
			this.add(button);
		}

		JLabel thickLabel = new JLabel("  µÎ²² ");
		thickLabel.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 13));

		s = new JSpinner();
		SpinnerModel model = new SpinnerNumberModel(1, 1, 10, 1);
		s.setModel(model);
		
		s.setPreferredSize(new Dimension(60, 20));
		JPanel p = new JPanel();
		p.add(thickLabel);
		p.add(s);
		

		this.add(p);
		ChangeHandler changeHandler = new ChangeHandler();
		s.addChangeListener(changeHandler);

	}

	public void initialize() {
		((JButton) (this.getComponent(EShapeTool.eRectangle.ordinal()))).doClick();
	}

	public void setAssociation(GPanel panel) {
		this.panel = panel;
	}

	private void changeThicker() {
		int thicker = (int) s.getValue();
		this.panel.setThickLevel(thicker);
	}

	// gPanel °ÍÀ» »ç¿ëÇÒ ¼ö ÀÖ°Ô µÊ
	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			EShapeTool eShapeTool = EShapeTool.valueOf(e.getActionCommand());
			int i = 0;
			for (EShapeTool eShapeTools : EShapeTool.values()) {
				buttons.get(i).setIcon(new ImageIcon(eShapeTools.getPath()));
				i++;
			}
			for (JButton button : buttons) {
				if (e.getSource().equals(button)) {
					button.setIcon(new ImageIcon(eShapeTool.getOnPath()));
				}
			}

			panel.setShapeTool(eShapeTool.getShapeTool());
		}
	}

	private class ChangeHandler implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			changeThicker();
		}
	}
}
