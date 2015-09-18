/**
 * Junkai Gao & Yang Zhang
 */

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.Timer;
import java.util.TimerTask;

public class ControllerFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	/**
	 * @param args
	 */
	ModelObject model;
	ViewPanel view;
	
	int frameWidh = 1207;
	int frameHeight = 975;
	
	int leftWidth = 245;
	int leftHeight = frameHeight-26;
	
	int rightWidth = frameWidh-leftWidth-8;
	int rightHeight = frameHeight-26;
	
	int viewLocationX=0;
	int viewLocationY=0;
	
	int defaultViewX = rightWidth-100;
	int defaultViewY = rightHeight-100;
	
	Container c;
	
	JPanel leftPanel;
	JScrollPane leftSP;
	JPanel rightPanel;
	JScrollPane rightSP;
	
	
	JLabel viewXValueLabel;
	JSlider viewXSlider;
	int viewXSliderValue = defaultViewX;
	JLabel viewYValueLabel;
	JSlider viewYSlider;
	int viewYSliderValue = defaultViewY;
	
	JLabel shapeXValueLabel;
	JSlider shapeXSlider;
	int shapeXSliderValue = viewXSliderValue-100;
	JLabel shapeYValueLabel;
	JSlider shapeYSlider;
	int shapeYSliderValue = viewYSliderValue-100;
	
	JLabel BGLabel;
	JLabel BGRLabel;
	JSlider BGRSlider;
	int BGR = 0;
	JLabel BGGLabel;
	JSlider BGGSlider;
	int BGG = 100;
	JLabel BGBLabel;
	JSlider BGBSlider;
	int BGB = 100;
	JButton moreBGColor;
	Color BGColor = new Color(BGR,BGG,BGB);

	JLabel FGLabel;
	JLabel FGRLabel;
	JSlider FGRSlider;
	int FGR = 0;
	JLabel FGGLabel;
	JSlider FGGSlider;
	int FGG = 200;
	JLabel FGBLabel;
	JSlider FGBSlider;
	int FGB = 0;
	JButton moreFGColor;
	Color FGColor = new Color(FGR,FGG,FGB);
	
	String message = "Let's go PITT !";
	String fontStyle = "bold";
	int maxSize = 99;
	int fontSize = maxSize;
	
	JLabel fontSizeValueLabel;
	JSlider fontSizeSlider;
	
	JTextField messageField;
	
	String shape = "ROUNDRECT";
	
	public static void main(String[] args){
		// TODO Auto-generated method stub
		new ControllerFrame();
	}
	
	public ControllerFrame(){
		//title
		super("Show Message - Junkai Gao & Yang Zhang");
		
		//frame settings
		//setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		setSize(frameWidh,frameHeight);		
        setFont(new Font("Arial", Font.PLAIN + Font.PLAIN, 14));
		c = getContentPane();
		c.setBackground(Color.BLUE);
		
		//set default font size
		maxSize = findMaxFontSize(shapeXSliderValue,shapeYSliderValue,message,fontStyle);
		fontSize = maxSize;
		
		
		
		
		
		
		
		
		
		
		
		
		//left panel
		leftPanel = new JPanel();
		leftPanel.setPreferredSize(new Dimension(leftWidth,leftHeight));
		//leftPanel.setBackground(Color.red);
		leftPanel.setLayout(new FlowLayout());

		//message
		JLabel messageLabel = new JLabel("Message");
		messageLabel.setFont(new Font("Arial", Font.BOLD + Font.PLAIN, 14));
        leftPanel.add(messageLabel);

        messageField = new JTextField(message);
        messageField.setPreferredSize(new Dimension(215,40));
        messageField.getDocument().addDocumentListener(new DocumentListener(){
        	public void changedUpdate(DocumentEvent e){
        		//System.out.println(message);
        		message = messageField.getText();
        		model.message = message;
        		view.model = model;
				view.repaint();
				rightPanel.repaint();
				
				maxSize=findMaxFontSize(shapeXSliderValue,shapeYSliderValue,message,fontStyle);
				fontSizeSlider.setMaximum(maxSize);
        	}
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println(message);
        		message = messageField.getText();
        		model.message = message;
        		view.model = model;
				view.repaint();
				rightPanel.repaint();
				
				maxSize=findMaxFontSize(shapeXSliderValue,shapeYSliderValue,message,fontStyle);
				fontSizeSlider.setMaximum(maxSize);
			}
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println(message);
        		message = messageField.getText();
        		model.message = message;
        		view.model = model;
				view.repaint();
				rightPanel.repaint();
				
				maxSize=findMaxFontSize(shapeXSliderValue,shapeYSliderValue,message,fontStyle);
				fontSizeSlider.setMaximum(maxSize);
			}
        });
        leftPanel.add(messageField);
        
        //font size
		JLabel fontSizeLabel = new JLabel("                Font Size               ");
		fontSizeLabel.setFont(new Font("Arial", Font.BOLD + Font.PLAIN, 14));
        leftPanel.add(fontSizeLabel);
        
		fontSizeValueLabel = new JLabel();
		fontSizeValueLabel.setText("1            Current : "+fontSize+"            "+maxSize);
		leftPanel.add(fontSizeValueLabel);
		
		fontSizeSlider = new JSlider();
		fontSizeSlider.setPreferredSize(new Dimension(220,20));
		fontSizeSlider.setMaximum(maxSize);
		fontSizeSlider.setMinimum(1);
		fontSizeSlider.setToolTipText("Slider for font size");
		fontSizeSlider.setValue(fontSize);
		fontSizeSlider.addChangeListener(new ChangeListener(){
        	public void stateChanged(ChangeEvent e){
        		fontSize = ((JSlider)e.getSource()).getValue();
        		fontSizeValueLabel.setText("1            Current : "+fontSize+"            "+maxSize);
        		model.fontSize = fontSize;
        		view.model = model;
				view.repaint();
				rightPanel.repaint();
        	}
        });
		leftPanel.add(fontSizeSlider);
		
        //font style
		JLabel fontStyleLabel = new JLabel("                Font Style                ");
		fontStyleLabel.setFont(new Font("Arial", Font.BOLD + Font.PLAIN, 14));
        leftPanel.add(fontStyleLabel);
        
        JRadioButton RadioButton1 = new JRadioButton("Regular"); 
        RadioButton1.setFont(new Font("Arial", Font.PLAIN + Font.PLAIN, 13));
        RadioButton1.setPreferredSize(new Dimension(94,20));
        RadioButton1.addActionListener(new ActionListener() {     
        	public void actionPerformed(ActionEvent e) {
        		fontStyle = "REGULAR";
        		model.fontStyle = fontStyle;
        		view.model = model;
				view.repaint();
				rightPanel.repaint();
				
				maxSize=findMaxFontSize(shapeXSliderValue,shapeYSliderValue,message,fontStyle);
				fontSizeSlider.setMaximum(maxSize);
        	}
        });
        JRadioButton RadioButton2 = new JRadioButton("Italic"); 
        RadioButton2.setFont(new Font("Arial", Font.ITALIC + Font.PLAIN, 13));
        RadioButton2.setPreferredSize(new Dimension(115,20));
        RadioButton2.addActionListener(new ActionListener() {    
        	public void actionPerformed(ActionEvent e) {
        		fontStyle = "ITALIC";
        		model.fontStyle = fontStyle;
        		view.model = model;
				view.repaint();
				rightPanel.repaint();
				
				maxSize=findMaxFontSize(shapeXSliderValue,shapeYSliderValue,message,fontStyle);
				fontSizeSlider.setMaximum(maxSize);
        	}
        });
        JRadioButton RadioButton3 = new JRadioButton("Bold", true); 
        RadioButton3.setFont(new Font("Arial", Font.PLAIN + Font.BOLD, 13));
        RadioButton3.setPreferredSize(new Dimension(94,20));
        RadioButton3.addActionListener(new ActionListener() {    
        	public void actionPerformed(ActionEvent e) {
        		fontStyle = "BOLD";
        		model.fontStyle = fontStyle;
        		view.model = model;
				view.repaint();
				rightPanel.repaint();
				
				maxSize=findMaxFontSize(shapeXSliderValue,shapeYSliderValue,message,fontStyle);
				fontSizeSlider.setMaximum(maxSize);
        	}
        });
        JRadioButton RadioButton4 = new JRadioButton("Bold Italic"); 
        RadioButton4.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 13));
        RadioButton4.setPreferredSize(new Dimension(115,20));
        RadioButton4.addActionListener(new ActionListener() {    
        	public void actionPerformed(ActionEvent e) {
        		fontStyle = "BOLD ITALIC";
        		model.fontStyle = fontStyle;
        		view.model = model;
				view.repaint();
				rightPanel.repaint();
				
				maxSize=findMaxFontSize(shapeXSliderValue,shapeYSliderValue,message,fontStyle);
				fontSizeSlider.setMaximum(maxSize);
        	}
        });
        ButtonGroup fontRadioButtonGroup = new ButtonGroup();  
        fontRadioButtonGroup.add(RadioButton1);           
        fontRadioButtonGroup.add(RadioButton2);  
        fontRadioButtonGroup.add(RadioButton3);           
        fontRadioButtonGroup.add(RadioButton4);        
        leftPanel.add(RadioButton1);                        
        leftPanel.add(RadioButton2);                   
        leftPanel.add(RadioButton3);                        
        leftPanel.add(RadioButton4);                   
        leftPanel.add(new JLabel("-------------------------------------------------"));
        //shape
		JLabel shapeLabel = new JLabel("                       Shape                       ");
		shapeLabel.setFont(new Font("Arial", Font.BOLD + Font.PLAIN, 14));
        leftPanel.add(shapeLabel);
        
        /*
        //shape radio button
        JRadioButton rectangleJRB = new JRadioButton("RECTANGLE");  //rectangleJRB.setIcon(new ImageIcon("a.png"));
        rectangleJRB.setPreferredSize(new Dimension(185,20));
        rectangleJRB.addActionListener(new ActionListener() {     
        	public void actionPerformed(ActionEvent e) {
        		shape = "RECT";
        		model.shape = shape;
        		view.model = model;
				view.repaint();
				rightPanel.repaint();
				
				maxSize=findMaxFontSize(shapeXSliderValue,shapeYSliderValue,message,fontStyle);
				fontSizeSlider.setMaximum(maxSize);
        	}
        });
        JRadioButton ovalJRB = new JRadioButton("OVAL"); 
        ovalJRB.setPreferredSize(new Dimension(185,20));
        ovalJRB.addActionListener(new ActionListener() {    
        	public void actionPerformed(ActionEvent e) {
        		shape = "OVAL";
        		model.shape = shape;
        		view.model = model;
				view.repaint();
				rightPanel.repaint();
				
				maxSize=findMaxFontSize(shapeXSliderValue,shapeYSliderValue,message,fontStyle);
				fontSizeSlider.setMaximum(maxSize);
        	}
        });
        JRadioButton roundJRB = new JRadioButton("ROUNDRECT", true); 
        roundJRB.setPreferredSize(new Dimension(185,20));
        roundJRB.addActionListener(new ActionListener() {    
        	public void actionPerformed(ActionEvent e) {
        		shape = "ROUNDRECT";
        		model.shape = shape;
        		view.model = model;
				view.repaint();
				rightPanel.repaint();
				
				maxSize=findMaxFontSize(shapeXSliderValue,shapeYSliderValue,message,fontStyle);
				fontSizeSlider.setMaximum(maxSize);
        	}
        });
        
        ButtonGroup shapeRadioButtonGroup = new ButtonGroup();  
        shapeRadioButtonGroup.add(rectangleJRB);           
        shapeRadioButtonGroup.add(ovalJRB);  
        shapeRadioButtonGroup.add(roundJRB);           
        leftPanel.add(rectangleJRB);                        
        leftPanel.add(ovalJRB);                   
        leftPanel.add(roundJRB);      
        */                  

		//shape button
		JToggleButton rectButton=new JToggleButton();
		//rectButton.setRolloverEnabled(true);
		rectButton.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/rect.jpg")));
		//rectButton.setRolloverIcon(new ImageIcon("rect.jpg"));
		//rectButton.setSelectedIcon(new ImageIcon("rect.jpg"));
		rectButton.setToolTipText("Shape : rectangle");
		rectButton.addActionListener(new ActionListener() {    
        	public void actionPerformed(ActionEvent e) {
        		shape = "RECT";
        		model.shape = shape;
        		view.model = model;
				view.repaint();
				rightPanel.repaint();
				
				maxSize=findMaxFontSize(shapeXSliderValue,shapeYSliderValue,message,fontStyle);
				fontSizeSlider.setMaximum(maxSize);
        	}
        });

		JToggleButton ovalButton=new JToggleButton();
		//ovalButton.setRolloverEnabled(true);
		ovalButton.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/oval.jpg")));
		//ovalButton.setRolloverIcon(new ImageIcon("oval.jpg"));
		//ovalButton.setSelectedIcon(new ImageIcon("oval.jpg"));
		ovalButton.setToolTipText("Shape : oval");
		ovalButton.addActionListener(new ActionListener() {    
        	public void actionPerformed(ActionEvent e) {
        		shape = "OVAL";
        		model.shape = shape;
        		view.model = model;
				view.repaint();
				rightPanel.repaint();
				
				maxSize=findMaxFontSize(shapeXSliderValue,shapeYSliderValue,message,fontStyle);
				fontSizeSlider.setMaximum(maxSize);
        	}
        });
		
		JToggleButton roundButton=new JToggleButton("",true);
		//roundButton.setRolloverEnabled(true);
		roundButton.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/round.jpg")));
		//roundButton.setRolloverIcon(new ImageIcon("round.jpg"));
		//roundButton.setSelectedIcon(new ImageIcon("round.jpg"));
		roundButton.setToolTipText("Shape : round rectangle");
		roundButton.addActionListener(new ActionListener() {    
        	public void actionPerformed(ActionEvent e) {
        		shape = "ROUNDRECT";
        		model.shape = shape;
        		view.model = model;
				view.repaint();
				rightPanel.repaint();
				
				maxSize=findMaxFontSize(shapeXSliderValue,shapeYSliderValue,message,fontStyle);
				fontSizeSlider.setMaximum(maxSize);
        	}
        });
			  
		ButtonGroup shapeButtonGroup = new ButtonGroup();  
		shapeButtonGroup.add(rectButton);           
		shapeButtonGroup.add(ovalButton); 
		shapeButtonGroup.add(roundButton); 
		
        leftPanel.add(rectButton);
        leftPanel.add(ovalButton);
        leftPanel.add(roundButton);
        
        //shape X
        JLabel shapeXLabel = new JLabel("              Shape Width              ");
        shapeXLabel.setFont(new Font("Arial", Font.BOLD + Font.PLAIN, 14));
		leftPanel.add(shapeXLabel);
		
        shapeXValueLabel = new JLabel();
        shapeXValueLabel.setText("100       Current : "+shapeXSliderValue+"       "+viewXSliderValue);
        leftPanel.add(shapeXValueLabel);
        
        shapeXSlider = new JSlider();
        shapeXSlider.setPreferredSize(new Dimension(220,20));
        shapeXSlider.setMaximum(viewXSliderValue);
        shapeXSlider.setMinimum(100);
        shapeXSlider.setToolTipText("Slider for shape width");
        shapeXSlider.setValue(shapeXSliderValue);
        shapeXSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
            	shapeXSliderValue = ((JSlider)e.getSource()).getValue();
            	shapeXValueLabel.setText("100       Current : " + shapeXSliderValue+"       "+viewXSliderValue);
               
				model.shapeX = shapeXSliderValue;
				view.model = model;
				view.repaint();
				rightPanel.repaint();
				
				maxSize=findMaxFontSize(shapeXSliderValue,shapeYSliderValue,message,fontStyle);
				fontSizeSlider.setMaximum(maxSize);
			}
        });
        leftPanel.add(shapeXSlider);

        //shape Y
        JLabel shapeYLabel = new JLabel("              Shape Height              ");
        shapeYLabel.setFont(new Font("Arial", Font.BOLD + Font.PLAIN, 14));
		leftPanel.add(shapeYLabel);
		
        shapeYValueLabel = new JLabel();
        shapeYValueLabel.setText("100       Current : "+shapeYSliderValue+"       "+viewYSliderValue);
        leftPanel.add(shapeYValueLabel);
        
        shapeYSlider = new JSlider();
        shapeYSlider.setPreferredSize(new Dimension(220,20));
        shapeYSlider.setMaximum(viewYSliderValue);
        shapeYSlider.setMinimum(100);
        shapeYSlider.setToolTipText("Slider for shape height");
        shapeYSlider.setValue(shapeYSliderValue);
        shapeYSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
            	shapeYSliderValue = ((JSlider)e.getSource()).getValue();
            	shapeYValueLabel.setText("100       Current : " + shapeYSliderValue+"       "+viewYSliderValue);
               
				model.shapeY = shapeYSliderValue;
				view.model = model;
				view.repaint();
				rightPanel.repaint();
				
				maxSize=findMaxFontSize(shapeXSliderValue,shapeYSliderValue,message,fontStyle);
				fontSizeSlider.setMaximum(maxSize);
            }
        });
        leftPanel.add(shapeYSlider);
        leftPanel.add(new JLabel("-------------------------------------------------"));

		//canvas X
        JLabel viewXLabel = new JLabel("              Canvas Width              ");
        viewXLabel.setFont(new Font("Arial", Font.BOLD + Font.PLAIN, 14));
		leftPanel.add(viewXLabel);

		viewXValueLabel = new JLabel();
		viewXValueLabel.setText("200      Current : "+viewXSliderValue+"      "+rightWidth);
		leftPanel.add(viewXValueLabel);
		
		viewXSlider = new JSlider();
		viewXSlider.setPreferredSize(new Dimension(220,20));
        viewXSlider.setMaximum(rightWidth);
        viewXSlider.setMinimum(200);
        viewXSlider.setToolTipText("Slider for canvas width");
        viewXSlider.setValue(viewXSliderValue);
        viewXSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
            	viewXSliderValue = ((JSlider)e.getSource()).getValue();
            	viewXValueLabel.setText("200      Current : " + viewXSliderValue+"      "+rightWidth);
               
				model.panelX = viewXSliderValue;
				view.model = model;
				viewLocationX = rightWidth/2-model.panelX/2;
				viewLocationY = rightHeight/2-model.panelY/2;
				view.setBounds(viewLocationX, viewLocationY, model.panelX, model.panelY);
				view.repaint();
				rightPanel.repaint();
				
		        shapeXSlider.setMaximum(viewXSliderValue);
            }
        });
        leftPanel.add(viewXSlider);
		
        //canvas Y
        JLabel viewYLabel = new JLabel("              Canvas Height              ");
        viewYLabel.setFont(new Font("Arial", Font.BOLD + Font.PLAIN, 14));
		leftPanel.add(viewYLabel);
		
		viewYValueLabel = new JLabel();
		viewYValueLabel.setText("200      Current : "+viewYSliderValue+"      "+rightHeight);
		leftPanel.add(viewYValueLabel);
		
		viewYSlider = new JSlider();
		viewYSlider.setPreferredSize(new Dimension(220,20));
		viewYSlider.setMaximum(rightHeight);
		viewYSlider.setMinimum(200);
		viewYSlider.setToolTipText("Slider for canvas height");
		viewYSlider.setValue(viewYSliderValue);
		viewYSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
            	viewYSliderValue = ((JSlider)e.getSource()).getValue();
            	viewYValueLabel.setText("200      Current : " + viewYSliderValue+"      "+rightHeight);
               
				model.panelY = viewYSliderValue;
				view.model = model;
				viewLocationX = rightWidth/2-model.panelX/2;
				viewLocationY = rightHeight/2-model.panelY/2;
				view.setBounds(viewLocationX, viewLocationY, model.panelX, model.panelY);
				view.repaint();
				rightPanel.repaint();
				
		        shapeYSlider.setMaximum(viewYSliderValue);
            }
        });
        leftPanel.add(viewYSlider);
        leftPanel.add(new JLabel("-------------------------------------------------"));

        //background color 
        BGLabel = new JLabel("                Background Color                ");BGLabel.setForeground(BGColor);
        BGLabel.setFont(new Font("Arial", Font.BOLD + Font.PLAIN, 14));
        leftPanel.add(BGLabel);
        
        //BGR
        BGRLabel = new JLabel("R:"+BGR);BGRLabel.setForeground(new Color(BGR,0,0));
        BGRLabel.setPreferredSize(new Dimension(37,20));
        leftPanel.add(BGRLabel);
        
        BGRSlider = new JSlider();
        BGRSlider.setPreferredSize(new Dimension(175,20));
        BGRSlider.setMaximum(255);
        BGRSlider.setMinimum(0);
        BGRSlider.setToolTipText("Slider for background color RED value");
        BGRSlider.setValue(BGR);
        BGRSlider.addChangeListener(new ChangeListener(){
        	public void stateChanged(ChangeEvent e){
        		BGR = ((JSlider)e.getSource()).getValue();BGRLabel.setForeground(new Color(BGR,0,0));
        		BGRLabel.setText("R:"+BGR);
        		BGColor = new Color(BGR, BGG, BGB);BGLabel.setForeground(BGColor);
        		model.BGColor = BGColor;
        		view.model = model;
				view.repaint();
				rightPanel.repaint();
        	}
        });
        leftPanel.add(BGRSlider);
        
        //BGG
        BGGLabel = new JLabel("G:"+BGG);BGGLabel.setForeground(new Color(0,BGG,0));
        BGGLabel.setPreferredSize(new Dimension(37,20));
        leftPanel.add(BGGLabel);
        
        BGGSlider = new JSlider();
        BGGSlider.setPreferredSize(new Dimension(175,20));
        BGGSlider.setMaximum(255);
        BGGSlider.setMinimum(0);
        BGGSlider.setToolTipText("Slider for background color GREEN value");
        BGGSlider.setValue(BGG);
        BGGSlider.addChangeListener(new ChangeListener(){
        	public void stateChanged(ChangeEvent e){
        		BGG = ((JSlider)e.getSource()).getValue();BGGLabel.setForeground(new Color(0,BGG,0));
        		BGGLabel.setText("G:"+BGG);
        		BGColor = new Color(BGR, BGG, BGB);BGLabel.setForeground(BGColor);
        		model.BGColor = BGColor;
        		view.model = model;
				view.repaint();
				rightPanel.repaint();
        	}
        });
        leftPanel.add(BGGSlider);
        
        //BGB
        BGBLabel = new JLabel("B:"+BGB);BGBLabel.setForeground(new Color(0,0,BGB));
        BGBLabel.setPreferredSize(new Dimension(37,20));
        leftPanel.add(BGBLabel);
        
        BGBSlider = new JSlider();
        BGBSlider.setPreferredSize(new Dimension(175,20));
        BGBSlider.setMaximum(255);
        BGBSlider.setMinimum(0);
        BGBSlider.setToolTipText("Slider for background color BLUE value");
        BGBSlider.setValue(BGB);
        BGBSlider.addChangeListener(new ChangeListener(){
        	public void stateChanged(ChangeEvent e){
        		BGB = ((JSlider)e.getSource()).getValue();BGBLabel.setForeground(new Color(0,0,BGB));
        		BGBLabel.setText("B:"+BGB);
        		BGColor = new Color(BGR, BGG, BGB);BGLabel.setForeground(BGColor);
        		model.BGColor = BGColor;
        		view.model = model;
				view.repaint();
				rightPanel.repaint();
        	}
        });
        leftPanel.add(BGBSlider);
        
        //BG more color
        moreBGColor = new JButton("More...");
		ActionListener a;
		a = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Color tempColor = JColorChooser.showDialog(getParent(), "Choose Background Color",BGColor);
				
				if(tempColor!=null){
					BGColor=tempColor;
					BGRSlider.setValue(tempColor.getRed());
					BGGSlider.setValue(tempColor.getGreen());
					BGBSlider.setValue(tempColor.getBlue());
					model.BGColor = BGColor;
	        		view.model = model;
					view.repaint();
					rightPanel.repaint();
				}    		
			}
		};		
		moreBGColor.addActionListener(a);
		moreBGColor.setToolTipText("Show color chooser dialog for background color");
		leftPanel.add(moreBGColor);
        
        //foreground color 
        FGLabel = new JLabel("                Foreground Color                ");FGLabel.setForeground(FGColor);
        FGLabel.setFont(new Font("Arial", Font.BOLD + Font.PLAIN, 14));
        leftPanel.add(FGLabel);
        
        //FGR
        FGRLabel = new JLabel("R:"+FGR);FGRLabel.setForeground(new Color(FGR,0,0));
        FGRLabel.setPreferredSize(new Dimension(37,20));
        leftPanel.add(FGRLabel);
        
        FGRSlider = new JSlider();
        FGRSlider.setPreferredSize(new Dimension(175,20));
        FGRSlider.setMaximum(255);
        FGRSlider.setMinimum(0);
        FGRSlider.setToolTipText("Slider for foreground color RED value");
        FGRSlider.setValue(FGR);
        FGRSlider.addChangeListener(new ChangeListener(){
        	public void stateChanged(ChangeEvent e){
        		FGR = ((JSlider)e.getSource()).getValue();FGRLabel.setForeground(new Color(FGR,0,0));
        		FGRLabel.setText("R:"+FGR);
        		FGColor = new Color(FGR, FGG, FGB);FGLabel.setForeground(FGColor);
        		model.FGColor = FGColor;
        		view.model = model;
				view.repaint();
				rightPanel.repaint();
        	}
        });
        leftPanel.add(FGRSlider);
        
        //FGG
        FGGLabel = new JLabel("G:"+FGG);FGGLabel.setForeground(new Color(0,FGG,0));
        FGGLabel.setPreferredSize(new Dimension(37,20));
        leftPanel.add(FGGLabel);
        
        FGGSlider = new JSlider();
        FGGSlider.setPreferredSize(new Dimension(175,20));
        FGGSlider.setMaximum(255);
        FGGSlider.setMinimum(0);
        FGGSlider.setToolTipText("Slider for foreground color GREEN value");
        FGGSlider.setValue(FGG);
        FGGSlider.addChangeListener(new ChangeListener(){
        	public void stateChanged(ChangeEvent e){
        		FGG = ((JSlider)e.getSource()).getValue();FGGLabel.setForeground(new Color(0,FGG,0));
        		FGGLabel.setText("G:"+FGG);
        		FGColor = new Color(FGR, FGG, FGB);FGLabel.setForeground(FGColor);
        		model.FGColor = FGColor;
        		view.model = model;
				view.repaint();
				rightPanel.repaint();
        	}
        });
        leftPanel.add(FGGSlider);
        
        //FGB
        FGBLabel = new JLabel("B:"+FGB);FGBLabel.setForeground(new Color(0,0,FGB));
        FGBLabel.setPreferredSize(new Dimension(37,20));
        leftPanel.add(FGBLabel);
        
        FGBSlider = new JSlider();
        FGBSlider.setPreferredSize(new Dimension(175,20));
        FGBSlider.setMaximum(255);
        FGBSlider.setMinimum(0);
        FGBSlider.setToolTipText("Slider for foreground color BLUE value");
        FGBSlider.setValue(FGB);
        FGBSlider.addChangeListener(new ChangeListener(){
        	public void stateChanged(ChangeEvent e){
        		FGB = ((JSlider)e.getSource()).getValue();FGBLabel.setForeground(new Color(0,0,FGB));
        		FGBLabel.setText("B:"+FGB);
        		FGColor = new Color(FGR, FGG, FGB);FGLabel.setForeground(FGColor);
        		model.FGColor = FGColor;
        		view.model = model;
				view.repaint();
				rightPanel.repaint();
        	}
        });
        leftPanel.add(FGBSlider);

        //FG more color
        moreFGColor = new JButton("More...");
		ActionListener b;
		b = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Color tempColor = JColorChooser.showDialog(getParent(), "Choose Foreground Color",FGColor);
				
				if(tempColor!=null){
					FGColor=tempColor;
					FGRSlider.setValue(tempColor.getRed());
					FGGSlider.setValue(tempColor.getGreen());
					FGBSlider.setValue(tempColor.getBlue());
					model.FGColor = FGColor;
	        		view.model = model;
					view.repaint();
					rightPanel.repaint();
				}    		
			}
		};		
		moreFGColor.addActionListener(b);
		moreFGColor.setToolTipText("Show color chooser dialog for foreground color");
		leftPanel.add(moreFGColor);
        leftPanel.add(new JLabel("-------------------------------------------------"));

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
				new ControllerFrame();
			}
        });
		leftPanel.add(resetButton);

        
        
	    leftSP = new JScrollPane(leftPanel);
		leftSP.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        
        
        
		
		
		
		
        
		
        
        
		
		//right panel
		rightPanel = new JPanel();
		rightPanel.setLayout(null);
		//rightPanel.setPreferredSize(new Dimension(rightWidth,rightHeight));
		rightPanel.setBackground(new Color(200,200,200));
				
		model = new ModelObject(defaultViewX,defaultViewY,shapeXSliderValue,shapeYSliderValue,BGColor,FGColor,message,fontStyle,fontSize,shape);

		view = new ViewPanel(model);
		
		viewLocationX = rightWidth/2-model.panelX/2;
		viewLocationY = rightHeight/2-model.panelY/2;
		
		view.setBounds(viewLocationX, viewLocationY, model.panelX, model.panelY);
		view.setBackground(Color.magenta);
		rightPanel.add(view);
		//not working

		rightSP = new JScrollPane(rightPanel);
		
		
		
		
		
		
		
		
		
		
		
		
		//timer for window size change
		Timer timer = new Timer();
	    timer.schedule(new TimerTask() {
	    	public void run() {
	    		int tempHeight = rightPanel.getHeight();
        		int tempWidth = rightPanel.getWidth();
        		
        		if(tempHeight!=rightHeight||tempWidth!=rightWidth){
    	    		rightHeight = tempHeight;
            		rightWidth = tempWidth;
    				
    		        viewXSlider.setMaximum(rightWidth);
    		        viewYSlider.setMaximum(rightHeight);
        		}

	    	}
	    }, 1000, 100);

	    
	    
		
		
		
		
	    
	    
	    
		
		//frame settings
		c.add(leftSP,BorderLayout.WEST);
		c.add(rightSP,BorderLayout.CENTER);
		
		setVisible(true); 
	}

	public int findMaxFontSize(int shapeX,int shapeY,String message,String fontStyle){
		
		int maxSize = 99;
		Font font;
        
        if (fontStyle.equalsIgnoreCase("REGULAR")) {
            font = new Font("Arial", 0, maxSize);
        } else if (fontStyle.equalsIgnoreCase("BOLD")) {
            font = new Font("Arial", 1, maxSize);
        } else if (fontStyle.equalsIgnoreCase("ITALIC")) {
            font = new Font("Arial", 2, maxSize);
        } else if (fontStyle.equalsIgnoreCase("BOLD ITALIC")) {
            font = new Font("Arial", 3, maxSize); 
        } else{
            font = new Font("Arial", 3, maxSize); 
        }
        
		FontMetrics fm = getFontMetrics(font);
		
        int stringWidth = fm.stringWidth(message);
        //System.out.println(stringWidth);
        int stringHeight = fm.getHeight();
        
        while(stringWidth >= shapeX){
            maxSize -= 1;
            font = new Font("Arial", font.getStyle(), maxSize);
            fm = getFontMetrics(font);
            stringWidth = fm.stringWidth(message);
        }
        
        stringHeight = fm.getHeight();
        
        while(stringHeight >= shapeY){
            maxSize -= 1;
            font = new Font("Arial", font.getStyle(), maxSize);
            fm = getFontMetrics(font);
            stringHeight = fm.getHeight();
        }
        //System.out.println(stringWidth+" "+maxSize);
        return maxSize;
	}
}
