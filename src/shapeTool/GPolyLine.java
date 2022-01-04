package shapeTool;

import java.awt.Graphics2D;
import java.awt.Polygon;

import main.GConstants.EDrawingStyle;

public class GPolyLine extends GShapeTool {
	private static final long serialVersionUID = 1L;

	private Polygon polyline;

	public GPolyLine() {
		super(EDrawingStyle.eNPointDrawing);

		this.polyline = new Polygon();
	}

	@Override
	public GShapeTool clone() {
		GShapeTool cloned = super.clone();
		Polygon polyline = new Polygon();
		for (int i = 0; i < this.polyline.npoints; i++) {
			polyline.addPoint(this.polyline.xpoints[i], this.polyline.ypoints[i]);
		}
		cloned.shape = polyline;
		return cloned;

	}

	@Override
	public void setInitialPoint(int x, int y) {
		this.polyline = (Polygon) this.shape;
		this.polyline.addPoint(x, y);
		this.polyline.addPoint(x, y);
	}

	public void setIntermediatePoint(int x, int y) {
		this.polyline = (Polygon) this.shape;
		this.polyline.addPoint(x, y);
	}

	@Override
	public void setFinalPoint(int x, int y) {
	}

	@Override
	public void draw(Graphics2D graphics2d) {
		this.polyline = (Polygon) this.shape;
		graphics2d.drawPolyline(this.polyline.xpoints, this.polyline.ypoints, polyline.npoints);
	}

	@Override
	public void movePoint(int x, int y) {
		this.polyline = (Polygon) this.shape;
		this.polyline.xpoints[polyline.npoints - 1] = x;
		this.polyline.ypoints[polyline.npoints - 1] = y;
	}

}
