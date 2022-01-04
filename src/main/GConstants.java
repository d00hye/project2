package main;

import java.awt.Dimension;
import java.awt.Point;

import shapeTool.GLine;
import shapeTool.GOval;
import shapeTool.GPen;
import shapeTool.GPolyLine;
import shapeTool.GPolygon;
import shapeTool.GRectangle;
import shapeTool.GShapeTool;

public class GConstants {
	
	public final static class CFrame{
		public final static Point point = new Point(100,200);
		public final static Dimension dimension = new Dimension(400,600);
	}
	
	public enum EDrawingStyle{
		e2PointDrawing,
		eNPointDrawing
	}
	
	public enum EAction {
		eDraw,
		eMove,
		eResize,
		eRotate,
		eShear;
	}
	
	public final static int wAnchor=10;	
	public final static int hAnchor=10;
	
	public enum EShapeTool{
		eRectangle(new GRectangle(), "Rectangle"),
		eOval(new GOval(), "Oval"),
		eLine(new GLine(), "Line"), 
		ePolygon(new GPolygon(), "Polygon"),
		ePolyLine(new GPolyLine(),"PolyLine"),
		ePen(new GPen(),"Pen");
		
		private GShapeTool shapeTool;
		private String text;
		private String path;
		private String onpath;
		private EShapeTool(GShapeTool shapeTool, String text) {
			this.shapeTool = shapeTool;
			this.text=text;
			this.path="resources/"+text+ ".jpg";
			this.onpath="resources/"+text+ "on.jpg";
		}

		public GShapeTool getShapeTool() {
			return this.shapeTool;
		}
		public String getText() {
			return this.text;
		}
		public String getPath() {
			return this.path;
		}
		public String getOnPath() {
			return this.onpath;
		}
	}
	
	public enum EMenu {
		eFile("����"),
		eEdit("����"),
		eGraphics("�׷���"),
		eHelp("����");
		
		private String text;
		private EMenu(String text) {
			this.text = text;
		}
		public String getText() {
			return this.text;
		}
		
	}
	
	public enum EFileMenuItem {
		eNew("���θ����"),
		eOpen("����"),
		eSave("����"),
		eSaveAs("�ٸ��̸���������"),
		ePrint("����Ʈ"),
		eExit("������");		
		private String text;
		private EFileMenuItem(String text) {
			this.text = text;
		}
		public String getText() {
			return this.text;
		}
		
	}
	
	public enum EEditMenuItem {
		eUndo("�ǵ�����"),
		eRedo("�ٽ� ����"),
		
		eCut("�߶󳻱�"),
		eCopy("����"),
		ePaste("���̱�"),
		eDelete("����"),

//		eGroup("�׷�"),
//		eUnGroup("��ü"),
		
		eFirst("�Ǿ�����"),
		eLast("�ǵڷ�");
		
		private String text;
		private EEditMenuItem(String text) {
			this.text = text;
		}
		public String getText() {
			return this.text;
		}
		
	}
	
	public enum EGraphicsMenuItem {
		eLineColor("���λ�"),
		eFillColor("ä����");
		
		private String text;
		private EGraphicsMenuItem(String text) {
			this.text = text;
		}
		public String getText() {
			return this.text;
		}
		
	}

}
