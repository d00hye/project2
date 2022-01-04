package Transformer;

import java.awt.Graphics2D;
import java.awt.Point;

import shapeTool.GShapeTool;

public class GRotate extends GTransformer {

	public GRotate(GShapeTool selectedShape) {
		super(selectedShape);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void transform(Graphics2D graphics2d, int x, int y, int px, int py, Point startP, Point endP) {
		double aAngle = Math.toDegrees(Math.atan2(y, x));
		double bAngle = Math.toDegrees(Math.atan2(py, px));
		double answer = (aAngle - bAngle) / 70;

		this.selectedShape.rotate(graphics2d, (double) answer);
	}

}
