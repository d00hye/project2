package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import frame.GPanel;
import main.GConstants.EGraphicsMenuItem;
import shapeTool.GShapeTool;

public class GGraphicsMenu extends JMenu {
	private static final long serialVersionUID = 1L;
	public GShapeTool shapeTool;
	public GPanel panel;

	public GGraphicsMenu(String text) {
		super(text);
		ActionHandler actionHandler = new ActionHandler();

		for (EGraphicsMenuItem eGraphicsMenuItem : EGraphicsMenuItem.values()) {
			JMenuItem menuItem = new JMenuItem(eGraphicsMenuItem.getText());
			menuItem.setActionCommand(eGraphicsMenuItem.name());
			menuItem.setFont((new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 12)));
			menuItem.addActionListener(actionHandler);
			this.add(menuItem);
		}
	}
	

	public void setAssociation(GPanel panel) {
		this.panel = panel;
	}


	private void setLineColor() {
		Color lineColor = JColorChooser.showDialog(this, "line color", this.panel.getForeground());
		this.panel.setLineColor(lineColor);
	}
	
	private void setFillColor() {
		Color fillColor = JColorChooser.showDialog(this, "fill color", this.panel.getForeground());
		this.panel.setFillColor(fillColor);
	}
	

//	private void setThickLevel() {
//		JSlider thickerSlider = new JSlider(JSlider.HORIZONTAL, 1, 7, 1);
//		thickerSlider.setMajorTickSpacing(1);
//		thickerSlider.setPaintTicks(true);
//		thickerSlider.setPaintLabels(true);
//		
//		JFrame slider = new JFrame();
//		slider.setTitle("∂Û¿ŒµŒ≤≤");
//		slider.setSize(300, 150);
//		slider.setLocationRelativeTo(null);
//		JButton okButton = new JButton("ok");
//		slider.add(okButton);
//		ActionHandler actionHandler = new ActionHandler();
//		okButton.setActionCommand("ok");
//		okButton.addActionListener(actionHandler);
//		slider.getRootPane().setDefaultButton(okButton);
//		slider.add(thickerSlider);
//		slider.setVisible(true);
//		int thickLavel
//		this.panel.setThickLevel(thickLavel);
//	}


	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			EGraphicsMenuItem eGraphicsMenuItem = EGraphicsMenuItem.valueOf(e.getActionCommand());
			switch (eGraphicsMenuItem) {
			case eLineColor:
				setLineColor();
				break;
			case eFillColor:
				setFillColor();
				break;
//			case eLineThick:
//				setThickLevel();
//				break;
			default:
				break;

			}
		}
	}
}
