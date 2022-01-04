package shapeTool;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import main.GConstants.EDrawingStyle;

public class GOval extends GShapeTool {
	private static final long serialVersionUID = 1L;

	public GOval() {
		super(EDrawingStyle.e2PointDrawing);
		this.shape = new Ellipse2D.Double();
	}

	@Override
	public GShapeTool clone() {
		GShapeTool cloned = super.clone();
		cloned.shape =(Shape) ((Ellipse2D.Double) (this.shape)).clone();
		return cloned;
	}

	@Override
	public void setInitialPoint(int x, int y) {
		Ellipse2D ellipse = (Ellipse2D) this.shape;
		ellipse.setFrame(x, y, 0, 0);
	}

	@Override
	public void setFinalPoint(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void movePoint(int x, int y) {
		Ellipse2D ellipse = (Ellipse2D) this.shape;
		ellipse.setFrame(ellipse.getX(), ellipse.getY(), x - ellipse.getX(), y - ellipse.getY());
	}


}