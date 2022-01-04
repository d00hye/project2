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
		eFile("파일"),
		eEdit("편집"),
		eGraphics("그래픽"),
		eHelp("도움말");
		
		private String text;
		private EMenu(String text) {
			this.text = text;
		}
		public String getText() {
			return this.text;
		}
		
	}
	
	public enum EFileMenuItem {
		eNew("새로만들기"),
		eOpen("열기"),
		eSave("저장"),
		eSaveAs("다른이름으로저장"),
		ePrint("프린트"),
		eExit("나가기");		
		private String text;
		private EFileMenuItem(String text) {
			this.text = text;
		}
		public String getText() {
			return this.text;
		}
		
	}
	
	public enum EEditMenuItem {
		eUndo("되돌리기"),
		eRedo("다시 실행"),
		
		eCut("잘라내기"),
		eCopy("복사"),
		ePaste("붙이기"),
		eDelete("삭제"),

//		eGroup("그룹"),
//		eUnGroup("해체"),
		
		eFirst("맨앞으로"),
		eLast("맨뒤로");
		
		private String text;
		private EEditMenuItem(String text) {
			this.text = text;
		}
		public String getText() {
			return this.text;
		}
		
	}
	
	public enum EGraphicsMenuItem {
		eLineColor("라인색"),
		eFillColor("채우기색");
		
		private String text;
		private EGraphicsMenuItem(String text) {
			this.text = text;
		}
		public String getText() {
			return this.text;
		}
		
	}

}
