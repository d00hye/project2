package shapeTool;

import java.awt.Rectangle;
import java.awt.Shape;

import main.GConstants.EDrawingStyle;

public class GRectangle extends GShapeTool {
	private static final long serialVersionUID = 1L;

	public GRectangle() {
		super(EDrawingStyle.e2PointDrawing);
		this.shape = new Rectangle();
	}

	@Override
	public GShapeTool clone() {
		GShapeTool cloned = super.clone();
		cloned.shape = (Shape) ((Rectangle) (this.shape)).clone();
		return cloned;
	}

	@Override
	public void setInitialPoint(int x, int y) {
		Rectangle rectangle = (Rectangle) this.shape;
		rectangle.setLocation(x, y);
		rectangle.setSize(0, 0);
	}

	@Override
	public void setFinalPoint(int x, int y) {
	}

	@Override
	public void movePoint(int x, int y) {
		Rectangle rectangle = (Rectangle) this.shape;
		rectangle.setSize(x - rectangle.x, y - rectangle.y);
	}

}
