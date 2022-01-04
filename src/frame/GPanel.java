package frame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JComponent;

import Transformer.GMover;
import Transformer.GResize;
import Transformer.GRotate;
import Transformer.GTransformer;
import main.GConstants.EAction;
import main.GConstants.EDrawingStyle;
import menu.GUndoStack;
import shapeTool.GShapeTool;

public class GPanel extends JPanel {
	// attributes
	private static final long serialVersionUID = 1L;
	private GUndoStack undoStack;
	private GShapeTool shapeTool;
	private GShapeTool selectedShape;
	private Vector<GShapeTool> shapes;
	private GShapeTool copiedShape;

	private GTransformer transforemer;
	private boolean bModified;

	// get set
	public Vector<GShapeTool> getShapes() {
		return this.shapes;
	}

	@SuppressWarnings("unchecked")
	public void setShapes(Object object) {
		this.shapes = (Vector<GShapeTool>) object;
		this.updateUI();
	}

	public void setShapeTool(GShapeTool shapeTool) {
		this.shapeTool = shapeTool;
	}

	public boolean isbModified() {
		return this.bModified;
	}

	public void setbModified(boolean bModified) {
		this.bModified = bModified;
	}

	public Vector<GShapeTool> deepCopy(Vector<GShapeTool> originals) {
		@SuppressWarnings("unchecked")
		Vector<GShapeTool> clones = (Vector<GShapeTool>) originals.clone();
		for (int i = 0; i < originals.size(); i++) {
			clones.set(i, originals.get(i).clone());
		}
		return clones;
	}

	// constructor
	public GPanel() {
		// initialize attributes
		this.setBackground(Color.WHITE);

		// initialize components
		this.undoStack = new GUndoStack();
		this.shapes = new Vector<GShapeTool>();

		KeyHandler keyHandler = new KeyHandler();
		this.addKeyListener(keyHandler);

		MouseHandler mouseHandler = new MouseHandler();
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
		this.addMouseWheelListener(mouseHandler);
//		
//		JTextPane text = new JTextPane();
//		text.setBounds(100,330,200,40);
//
//		text.setSize(100, 10);
//		text.setVisible(true);
//		this.add(text);

//		JTextArea j = new JTextArea(1, 1);
//		j.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 30));
//		Rectangle r = new Rectangle();
//		r.setBounds(30, 70, 100, 100);
//		JPanel jd = new JPanel();
//		jd.setBounds(60, 60, 30, 10);
//		jd.add(j);
//		this.add(jd);

	}

	public void initialize() {
		this.bModified = false;
		this.copiedShape = null;
		this.shapes.clear();
		this.undoStack.push(this.deepCopy(this.shapes));
		this.repaint();

		this.setKey();
	}

	public void undo() {
		Vector<GShapeTool> snapShot = this.undoStack.undo();
		if (snapShot == null) {
			return;
		}
		this.shapes = this.deepCopy(snapShot);
		this.repaint();
	}

	public void redo() {
		Vector<GShapeTool> snapShot = this.undoStack.redo();
		if (snapShot == null) {
			return;
		}
		this.shapes = this.deepCopy(snapShot);
		this.repaint();
	}

	public void copy() {
		if (this.selectedShape != null) {
			this.copiedShape = this.selectedShape.clone();
		}
	}

	public void paste() {
		if (this.copiedShape != null) {
			Graphics2D graphics2D = (Graphics2D) this.getGraphics();
			this.copiedShape.move(graphics2D, +15, +15);
			this.shapes.add(this.copiedShape.clone());
			this.undoStack.push(this.deepCopy(this.shapes));
			this.repaint();
		}
	}

	public void cut() {
		if (this.selectedShape != null) {
			this.copiedShape = this.selectedShape.clone();
			this.shapes.remove(this.shapes.indexOf(selectedShape));
			this.undoStack.push(this.deepCopy(this.shapes));
			this.repaint();
		}

	}

	public void delete() {
		if (this.selectedShape != null) {
			this.shapes.remove(this.shapes.indexOf(selectedShape));
			this.undoStack.push(this.deepCopy(this.shapes));
			this.repaint();
		}
	}

	public void group() {
		if (this.selectedShape != null) {
//			this.shapes.add(selectedShape);
//			this.gShapes.add(shapes);
//			
//			this.shapes.remove(this.shapes.indexOf(selectedShape));
//			this.shapes = this.deepCopy(selectedShapes);
//
//
//			this.undoStack.push(this.deepCopy(selectedShapes));
//			this.repaint();
		}
	}

	public void first() {
		if (this.selectedShape != null) {
			this.shapes.remove(this.shapes.indexOf(selectedShape));
			this.shapes.add(this.shapes.size(), selectedShape);
			this.undoStack.push(this.deepCopy(this.shapes));
			this.repaint();
		}
	}

	public void last() {
		if (this.selectedShape != null) {
			this.shapes.remove(this.shapes.indexOf(selectedShape));
			this.shapes.add(0, selectedShape);
			this.undoStack.push(this.deepCopy(this.shapes));
			this.repaint();
		}
	}

	public void paint(Graphics graphics) {
		super.paint(graphics);
		// redraw shapes
		for (GShapeTool shape : this.shapes) {
			shape.draw((Graphics2D) graphics);
		}
	}

	private void setSelected(GShapeTool selectedShape) {
		for (GShapeTool shape : this.shapes) {
			shape.setSelected(false);
		}

		this.selectedShape = selectedShape;
		this.selectedShape.setSelected(true);
		this.repaint();
	}

	private GShapeTool onShape(int x, int y) {
		for (int i = shapes.size() - 1; i > -1; i--) {
			EAction eAction = this.shapes.get(i).contains(x, y);
			if (eAction != null) {
				return this.shapes.get(i);
			}
		}
		return null;
	}

	private void initDrawing(int x, int y) {
		this.selectedShape = this.shapeTool.clone();
		this.selectedShape.setInitialPoint(x, y);
	}

	private void keepDrawing(int x, int y) {
		Graphics2D graphics2D = (Graphics2D) this.getGraphics();
		// set xor mode
		graphics2D.setXORMode(this.getBackground());
		this.selectedShape.animate(graphics2D, x, y);
	}

	private void finishDrawing(int x, int y) {
		this.selectedShape.setFinalPoint(x, y);
//		this.selectedShape.setSelected(true);
		this.shapes.add(this.selectedShape);
		this.bModified = true;
		this.undoStack.push(this.deepCopy(this.shapes));
	}

	private void setIntermediatePoint(int x, int y) {
		this.selectedShape.setIntermediatePoint(x, y);
	}

	private void initTransforming(GShapeTool selectedShape, int x, int y) {
		this.selectedShape = selectedShape;
		EAction eAction = this.selectedShape.getAction();
		switch (eAction) {
		case eMove:
			this.transforemer = new GMover(this.selectedShape);
			break;
		case eResize:
			this.transforemer = new GResize(this.selectedShape);
			break;
		case eRotate:
			this.transforemer = new GRotate(this.selectedShape);
			break;
		default:
			break;

		}
		Graphics2D graphics2d = (Graphics2D) this.getGraphics();
		graphics2d.setXORMode(this.getBackground());
		this.transforemer.initTransforming(graphics2d, x, y);
	}

	private void keepTransforming(int x, int y) {
		Graphics2D graphics2d = (Graphics2D) this.getGraphics();
		graphics2d.setXORMode(this.getBackground());
		this.transforemer.keepTransforming(graphics2d, x, y);
	}

	private void finishTransforming(int x, int y) {
		this.setSelected(this.selectedShape);
		this.repaint();
		this.bModified = true;

		this.undoStack.push(this.deepCopy(this.shapes));
	}

	private class KeyHandler implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
			System.out.println("d");
		}

		@Override
		public void keyPressed(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
	}

	private class MouseHandler implements MouseListener, MouseMotionListener, MouseWheelListener {

		private boolean isDrawing;
		private boolean isTransforming;

		public MouseHandler() {
			this.isDrawing = false;
			this.isTransforming = false;
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				if (!this.isDrawing) {
					GShapeTool selectedShape = onShape(e.getX(), e.getY());
					if (selectedShape == null) {
						if (shapeTool.getDrawingStyle() == EDrawingStyle.e2PointDrawing) {
							initDrawing(e.getX(), e.getY());
							this.isDrawing = true;
						}
					} else {
						initTransforming(selectedShape, e.getX(), e.getY());
						this.isTransforming = true;
					}
				}
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if (this.isDrawing) {
				if (shapeTool.getDrawingStyle() == EDrawingStyle.e2PointDrawing) {
					keepDrawing(e.getX(), e.getY());
				}
			} else if (this.isTransforming) {
				keepTransforming(e.getX(), e.getY());
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (this.isDrawing) {
				if (shapeTool.getDrawingStyle() == EDrawingStyle.e2PointDrawing) {
					finishDrawing(e.getX(), e.getY());
					this.isDrawing = false;
				}
			} else if (this.isTransforming) {
				finishTransforming(e.getX(), e.getY());
				this.isTransforming = false;
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			if (this.isDrawing) {
				if (shapeTool.getDrawingStyle() == EDrawingStyle.eNPointDrawing) {
					keepDrawing(e.getX(), e.getY());
				}
			}
		}

		private void mouseLButton1Clicked(MouseEvent e) {
			if (!this.isDrawing) {
				GShapeTool selectedShape = onShape(e.getX(), e.getY());
				if (selectedShape == null) {
					if (shapeTool.getDrawingStyle() == EDrawingStyle.eNPointDrawing) {
						initDrawing(e.getX(), e.getY());
						this.isDrawing = true;
					}
				} else {
					setSelected(selectedShape);
				}
			} else {
				if (shapeTool.getDrawingStyle() == EDrawingStyle.eNPointDrawing) {
					setIntermediatePoint(e.getX(), e.getY());
				}
			}

		}

		private void mouseLButton2Clicked(MouseEvent e) {
			if (this.isDrawing) {
				if (shapeTool.getDrawingStyle() == EDrawingStyle.eNPointDrawing) {
					finishDrawing(e.getX(), e.getY());
					this.isDrawing = false;
				}
			}
		}

		private void mouseRButton1Clicked(MouseEvent e) {
		}

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				if (e.getClickCount() == 1) {
					this.mouseLButton1Clicked(e);
				} else if (e.getClickCount() == 2) {
					this.mouseLButton2Clicked(e);
				}
			} else if (e.getButton() == MouseEvent.BUTTON2) {
				if (e.getClickCount() == 1) {
					this.mouseRButton1Clicked(e);
				}
			}
		}

	}

	private void setKey() {
		InputMap inMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		inMap.put(KeyStroke.getKeyStroke("control C"), "copy");
		Action copy = new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				copy();
				System.out.println("ctrl + C");
			}
		};
		this.getActionMap().put("copy", copy);

		inMap.put(KeyStroke.getKeyStroke("control V"), "paste");
		Action paste = new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				paste();
				System.out.println("ctrl + v");
			}
		};
		this.getActionMap().put("paste", paste);

		inMap.put(KeyStroke.getKeyStroke("control Z"), "undo");
		Action undo = new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				undo();
				System.out.println("ctrl + z");
			}
		};
		this.getActionMap().put("undo", undo);

		inMap.put(KeyStroke.getKeyStroke("control X"), "cut");
		Action cut = new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				cut();
				System.out.println("ctrl + x");
			}
		};
		this.getActionMap().put("cut", cut);

		inMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "delete");
		Action delete = new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				delete();
				System.out.println("delete");
			}
		};
		this.getActionMap().put("delete", delete);

	}

	public void setLineColor(Color lineColor) {
		this.shapeTool.setLineColor(lineColor);
	}

	public void setFillColor(Color fillColor) {
		this.shapeTool.setFillColor(fillColor);
	}

	public void setThickLevel(int thickLevel) {
		this.shapeTool.setThickLevel(thickLevel);
	}

}
