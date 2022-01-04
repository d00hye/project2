package shapeTool;

import java.awt.Shape;
import java.awt.geom.Path2D;

import main.GConstants.EDrawingStyle;

public class GPen extends GShapeTool {
	private static final long serialVersionUID = 1L;
	
	
	public GPen() {
		super(EDrawingStyle.eNPointDrawing);
		this.shape= new Path2D.Double();
	}

	@Override
	public GShapeTool clone() {
		GShapeTool cloned = super.clone();
		cloned.shape =(Shape) ((Path2D.Double) (this.shape)).clone();
		return cloned;
	}


	@Override
	public void setInitialPoint(int x, int y) {
		Path2D pen = (Path2D) this.shape;
		pen.moveTo(x, y);
	}
	
	@Override
	public void setFinalPoint(int x, int y) {
	}


	@Override
	public void movePoint(int x, int y) {
		Path2D pen = (Path2D) this.shape;
		pen.lineTo(x, y);		
	}


}
