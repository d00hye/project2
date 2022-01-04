package menu;

import java.util.Vector;

import shapeTool.GShapeTool;

public class GUndoStack {

	public final static int MAXSIZE = 10;
	private int index, top, bottom;
	private Vector<Vector<GShapeTool>> snapShots;

	public GUndoStack() {
		this.index = -1;
		this.top = -1;
		this.bottom = 0;

		this.snapShots = new Vector<Vector<GShapeTool>>();
		for (int i = 0; i < MAXSIZE; i++) {
			this.snapShots.add(new Vector<GShapeTool>());
		}
	}

	public void push(Vector<GShapeTool> snapShot) {
		this.index = this.index + 1;
		this.snapShots.set(this.index % MAXSIZE, snapShot);
		this.top = this.index;
	}

	public Vector<GShapeTool> undo() {
		if ((this.index - this.bottom - 1) < 0) {
			return null;
		}
		index = index - 1;
		return this.snapShots.get(this.index % MAXSIZE);
	}

	public Vector<GShapeTool> redo() {
		if ((this.top - this.index - 1) < 0) {
			return null;
		}
		index = index + 1;
		return this.snapShots.get(this.index % MAXSIZE);
	}

}
