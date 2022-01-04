package shapeTool;

import java.awt.Polygon;

import main.GConstants.EDrawingStyle;

public class GPolygon extends GShapeTool {
	private static final long serialVersionUID = 1L;
	
	private Polygon polygon;

	public GPolygon() {
		super(EDrawingStyle.eNPointDrawing);
		this.polygon = new Polygon();
	}

	@Override
	public GShapeTool clone() {
		GShapeTool cloned = super.clone();
		Polygon polygon =new Polygon();
		for (int i = 0; i < this.polygon.npoints; i++) {
			polygon.addPoint(this.polygon.xpoints[i], this.polygon.ypoints[i]);
		}

		cloned.shape = polygon;
		return cloned;
	}
	
	
	@Override
	public void setInitialPoint(int x, int y) {
		this.polygon =(Polygon) this.shape;
		this.polygon.addPoint(x, y);
		this.polygon.addPoint(x, y);

	}

	public void setIntermediatePoint(int x, int y) {
		this.polygon =(Polygon) this.shape;
		this.polygon.addPoint(x, y);

	}

	@Override
	public void setFinalPoint(int x, int y) {

	}

	@Override
	public void movePoint(int x, int y) {
		this.polygon =(Polygon) this.shape;
		this.polygon.xpoints[polygon.npoints - 1] = x;
		this.polygon.ypoints[polygon.npoints - 1] = y;		
	}


}
