/**
 * Junkai Gao & Yang Zhang
 */

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.*;


public class ViewPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	FontMetrics fm;
	Font font;

	ModelObject model;
	
	public ViewPanel(ModelObject model){
		this.model = model;
	}
	
	public void paint(Graphics g){
		g.setColor(model.BGColor);
		
		g.fillRect( 0, 0, model.panelX, model.panelY);

		
		g.setColor(model.FGColor);

		if (model.shape.equalsIgnoreCase("RECT")){ 
		g.drawRect((model.panelX-model.shapeX)/2, (model.panelY-model.shapeY)/2, model.shapeX-1, model.shapeY-1);}
		if (model.shape.equalsIgnoreCase("OVAL")){ 
		g.drawOval((model.panelX-model.shapeX)/2, (model.panelY-model.shapeY)/2, model.shapeX-1, model.shapeY-1);}
		if (model.shape.equalsIgnoreCase("ROUNDRECT")){ 
		g.drawRoundRect((model.panelX-model.shapeX)/2, (model.panelY-model.shapeY)/2, model.shapeX-1, model.shapeY-1,20,20);}
	    
        if (model.fontStyle.equalsIgnoreCase("REGULAR")) {
            font = new Font("Arial", 0, model.fontSize);
        } else if (model.fontStyle.equalsIgnoreCase("BOLD")) {
            font = new Font("Arial", 1, model.fontSize);
        } else if (model.fontStyle.equalsIgnoreCase("ITALIC")) {
            font = new Font("Arial", 2, model.fontSize);
        } else if (model.fontStyle.equalsIgnoreCase("BOLD ITALIC")) {
            font = new Font("Arial", 3, model.fontSize); 
        } else{
            font = new Font("Arial", 3, model.fontSize); 
        }
        
        fm = getFontMetrics(font);
        
		g.setFont(font);
		g.drawString(model.message, (model.panelX - fm.stringWidth(model.message))/2, (model.panelY/2 + fm.getHeight()/4));

	}
}
