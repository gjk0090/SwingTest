/**
 * Junkai Gao & Yang Zhang
 */

import java.awt.Color;


public class ModelObject {

	int panelX;
	int panelY;
	int shapeX;
	int shapeY;
	
	Color BGColor;
	Color FGColor;
	
	String shape = "ROUNDRECT";
	
	String message = "(╯′□′)╯┻━┻";
	
	String fontStyle = "BOLD ITALIC";
	int fontSize = 99;
	
	public ModelObject(int panelX,int panelY,int shapeX,int shapeY,Color BGColor,Color FGColor,String message,String fontStyle,int fontSize,String shape){
		this.panelX = panelX;
		this.panelY = panelY;
		this.shapeX = shapeX;
		this.shapeY = shapeY;
		this.BGColor = BGColor;
		this.FGColor = FGColor;
		
		this.message = message;
		this.fontStyle = fontStyle;
		this.fontSize = fontSize;
		
		this.shape = shape;
	}
}
