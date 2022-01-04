package Transformer;

import java.awt.Graphics2D;
import java.awt.Point;

import shapeTool.GShapeTool;

public abstract class GTransformer {
	
	protected GShapeTool selectedShape;
	protected int px, py;
	private Point startP;
	private Point endP;

	
	public GTransformer(GShapeTool selectedShape) {
		this.selectedShape= selectedShape;
		startP= new Point();
		endP= new Point();
	}
	
	public void initTransforming(Graphics2D graphics2d, int x, int y) {
		this.px=x;
		this.py=y;
		startP.setLocation(x, y);
	}	
	
	public void keepTransforming(Graphics2D graphics2d, int x, int y) {
		endP.setLocation(x, y);
		this.transform(graphics2d, x, y, px, py, startP, endP);
		this.px=x;
		this.py=y;
		
	}
	
	public void finishTransforming(Graphics2D graphics2d, int x, int y) {
		
	}
	
	public void continueTransforming(Graphics2D graphics2d, int x, int y) {

	}
	
	public abstract void transform(Graphics2D graphics2d, int x, int y, int px, int py, Point startP, Point endP);

}
