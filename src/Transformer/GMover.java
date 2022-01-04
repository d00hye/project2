package Transformer;

import java.awt.Graphics2D;
import java.awt.Point;

import shapeTool.GShapeTool;

public class GMover extends GTransformer {

	public GMover(GShapeTool selectedShape) {
		super(selectedShape);
	}

	@Override
	public void transform(Graphics2D graphics2d, int x, int y, int px, int py, Point startP, Point endP) {
		this.selectedShape.move(graphics2d, x-px, y-py);
	}



}
