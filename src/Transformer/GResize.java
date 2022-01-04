package Transformer;

import java.awt.Graphics2D;
import java.awt.Point;

import shapeTool.GShapeTool;

public class GResize extends GTransformer {

	public GResize(GShapeTool selectedShape) {
		super(selectedShape);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void transform(Graphics2D graphics2d, int x, int y, int px, int py, Point startP, Point endP) {
		this.selectedShape.resize(graphics2d, (double) x/px, (double) y/py);
	}



}
