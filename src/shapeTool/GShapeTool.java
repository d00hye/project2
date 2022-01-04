package shapeTool;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;

import main.GConstants;
import main.GConstants.EAction;
import main.GConstants.EDrawingStyle;

public abstract class GShapeTool implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	public enum EAnchors {
		x0y0,
		x0y1,
		x0y2,
		x1y0,
		x1y2,
		x2y0,
		x2y1,
		x2y2,
		RR;
	}

	private EDrawingStyle eDrawingStyle;
	private boolean isSelected;

	protected Shape shape;
	private Ellipse2D[] anchors;
	@SuppressWarnings("unused")
	private EAnchors eSelectedAnchor;
	private EAction eAction;
	private AffineTransform affineTransform;
	private Color lineColor, fillColor;
	private int thickLevel;
		
	public GShapeTool clone() {
		// shape, anchors, affineTransform 은 딥카피 필요(new했으니까)
		GShapeTool cloned = null;
		try {
			// super == object
			cloned = (GShapeTool) super.clone();
			cloned.anchors = this.anchors.clone();
			// clone할땐 index로
			for (int i = 0; i < this.anchors.length; i++) {
				cloned.anchors[i]= (Ellipse2D) this.anchors[i].clone();
			}
			cloned.affineTransform = (AffineTransform) this.affineTransform.clone();
			
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cloned;
	}

	public GShapeTool(EDrawingStyle eDrawingStyle) {
		this.eDrawingStyle = eDrawingStyle;
		this.isSelected = false;
		this.anchors= new Ellipse2D.Double[EAnchors.values().length];
		for(EAnchors eAnchor: EAnchors.values()) {
			this.anchors[eAnchor.ordinal()] = new Ellipse2D.Double();
		}
		this.eSelectedAnchor=null;
		this.eAction=null;
		this.affineTransform = new AffineTransform();
		this.affineTransform.setToIdentity();
		
		this.fillColor = Color.white;
		this.lineColor = Color.black;
	}

	public EDrawingStyle getDrawingStyle() {
		return this.eDrawingStyle;
	}

	public EAction getAction() {
		return this.eAction;
	}
	
	public EAction contains(int x, int y) {
		Shape transformedShape;
		this.eAction=null;
		if(this.isSelected) {
			for (int i = 0; i < EAnchors.values().length-1; i++) {
				transformedShape = this.affineTransform.createTransformedShape(this.anchors[i]);
				if(transformedShape.contains(x, y)) {
					this.eSelectedAnchor=EAnchors.values()[i];
					this.eAction= EAction.eResize;
				}
			} 
			transformedShape = this.affineTransform.createTransformedShape(this.anchors[EAnchors.RR.ordinal()]);
			if(transformedShape.contains(x, y)) {
				this.eAction= EAction.eRotate;
			}
		}
		transformedShape = this.affineTransform.createTransformedShape(this.shape);

		if (transformedShape.contains(x, y)) {
			this.eAction= EAction.eMove;
		}
		return this.eAction;
	}
	
	private void drawAnchors(Graphics2D graphics2d) {
		int wAnchor = GConstants.wAnchor;
		int hAnchor = GConstants.hAnchor;
		
		graphics2d.setColor(new Color(0,0,0));
		// 외접 사각형 그리기
		Rectangle rectangle = this.shape.getBounds();
		int x0= rectangle.x-wAnchor/2;
		int x1= rectangle.x+(rectangle.width/2)-wAnchor/2;
		int x2= rectangle.x+rectangle.width-wAnchor/2;
		int y0= rectangle.y-hAnchor/2;
		int y1= rectangle.y+(rectangle.height/2)-hAnchor/2;
		int y2= rectangle.y+rectangle.height-hAnchor/2;
		
		this.anchors[EAnchors.x0y0.ordinal()].setFrame(x0, y0, wAnchor, hAnchor);
		this.anchors[EAnchors.x0y1.ordinal()].setFrame(x0, y1, wAnchor, hAnchor);
		this.anchors[EAnchors.x0y2.ordinal()].setFrame(x0, y2, wAnchor, hAnchor);
		this.anchors[EAnchors.x1y0.ordinal()].setFrame(x1, y0, wAnchor, hAnchor);
		this.anchors[EAnchors.x1y2.ordinal()].setFrame(x1, y2, wAnchor, hAnchor);
		this.anchors[EAnchors.x2y0.ordinal()].setFrame(x2, y0, wAnchor, hAnchor);
		this.anchors[EAnchors.x2y1.ordinal()].setFrame(x2, y1, wAnchor, hAnchor);
		this.anchors[EAnchors.x2y2.ordinal()].setFrame(x2, y2, wAnchor, hAnchor);
		
		this.anchors[EAnchors.RR.ordinal()].setFrame(x1, y0-50, wAnchor, hAnchor);

		for(Ellipse2D eAnchor: this.anchors) {
			Color currentColor = graphics2d.getColor();
			
			Shape transformedAnchor = this.affineTransform.createTransformedShape(eAnchor);
			graphics2d.setColor(Color.white);
			graphics2d.fill(transformedAnchor);
			graphics2d.setColor(currentColor);
			graphics2d.setStroke(new BasicStroke(1)); 
			graphics2d.draw(transformedAnchor);
			}
	}

	public void setSelected(boolean isSelected) {
		this.isSelected= isSelected;
	}
	
	public void draw(Graphics2D graphics2d) {
		Shape s = this.affineTransform.createTransformedShape(this.shape);
		graphics2d.setColor(fillColor);
		graphics2d.fill(s);
		graphics2d.setColor(lineColor);
		graphics2d.setStroke(new BasicStroke(thickLevel)); 
		graphics2d.draw(s);
		if(this.isSelected) {
			this.drawAnchors(graphics2d);
		}
	}
	
	public void move(Graphics2D graphics2d, int dx, int dy) {
		this.draw(graphics2d);
		this.affineTransform.translate(dx, dy);
		this.draw(graphics2d); 
	}

	public void resize(Graphics2D graphics2d, double i, double j) {
		this.draw(graphics2d);
		this.affineTransform.scale(i, j);
		this.draw(graphics2d); 
	}
	
	
	public void rotate(Graphics2D graphics2d, double answer) {
		this.draw(graphics2d);
		this.affineTransform.rotate(answer);
		this.draw(graphics2d);
	}
	
	public void animate(Graphics2D graphics2d, int x, int y) {
		this.draw(graphics2d);
		this.movePoint(x, y);
		this.draw(graphics2d);
	
	}
	
	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor; 
	}
	
	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor; 
	}
	
	public void setThickLevel(int thickLevel) {
		this.thickLevel = thickLevel; 
	}

	public abstract void setInitialPoint(int x, int y);

	public abstract void setFinalPoint(int x, int y);

	public void setIntermediatePoint(int x, int y) {
	}

	public abstract void movePoint(int x, int y);

}
