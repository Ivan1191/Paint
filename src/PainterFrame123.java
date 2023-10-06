import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import javax.swing.SwingConstants;
import javax.swing.JSlider;

public class PainterFrame123 extends JFrame
{
	private DrawPanel drawpanel;//�ŧi�@�ӵe��
	private JLabel statusBar,slidersize;//���A�C
	private JPanel west,south,east;//�T��Panel
    private static final String[] names={"����","���u","����","�x��","�ꨤ�x��","�����"};//�u��C�W��
    private static final Color[] colornames={Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.GRAY,
                                             Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE,
                                             Color.PINK, Color.RED, Color.WHITE, Color.YELLOW};//�C����
    private JButton[] buttons;//�C����s
   	private Color forecolor =Color.BLACK, backcolor=Color.WHITE,color1,color2;//�e���� ,�I����,���h��G
    private JRadioButton small,medium,big,hidden,display;//�p�B���B�j�B���áB��� radiobutton
   	private ButtonGroup pensizeradioGroup,displayradioGroup;
   	private JLabel label1,label2;
   	private JComboBox combobox;
	private JButton gradient,back,delete;//���h �I��  �R�����s
	int tool=0,size=5, x=0,Ctype,a;//��ܤu��C ����j�p  �O���I��  ��ܬO�_���h
	public BufferedImage image;//�w�ĹϹ�
	private int x1, y1, x2, y2,drag=0;//��l��m  ������m
    private JCheckBox filled;//�񺡫��s
	private boolean fill = false;//�O�_��
	private JSlider slider;//��ؤj�p
	
	
    /*******************************�D�e�����ƦC*************************************/	
	public PainterFrame123()
	{
		super("�p�e�a");
		
		drawpanel=new DrawPanel();
	    west=new JPanel();
	    south=new JPanel();
	   
	    east=new JPanel();
	    statusBar=new JLabel("�ƹ��b�~��");
		add(drawpanel,BorderLayout.CENTER);
		add(west,BorderLayout.WEST);//�N���[�JJFrame��
		add(south,BorderLayout.SOUTH);
		add(east,BorderLayout.EAST);
		
		drawpanel.setBackground(Color.WHITE);//�e���I���]���զ�
	    west.setLayout(new GridLayout(10,1));//�]��GridLayout
	  	south.setLayout(new GridLayout(2,1));//�]��GridLayout
	  	east.setLayout(new GridLayout(13,1));//�]��GridLayout
	    
        label1=new JLabel("[ø�Ϥu��]");
        combobox=new JComboBox(names);// new ø�Ϥu�㪺JComboBox
        combobox.setMaximumRowCount(6);// �X�{���ӿﶵ
        label2=new JLabel("[����j�p]");
        small=new JRadioButton("�p",true);// new �p���ꪺJRadioButton
        medium=new JRadioButton("��",false);// new �����ꪺJRadioButton
     	big=new JRadioButton("�j",false);// new �j���ꪺJRadioButton
        filled=new JCheckBox("��");
	    back=new JButton("�I����"); // �I���⪺���s
	    delete=new JButton("�R���e��"); // �R���e�������s
	    display=new JRadioButton("��ܤu��C",true);//�w�]�O���
     	hidden=new JRadioButton("���äu��C",false);
     	gradient=new JButton("���h");//new ���h���s
     	
     	slidersize=new JLabel("[����ʲ�]:"+size);
    	slider=new JSlider(SwingConstants.HORIZONTAL,0,30,5);
		slider.setMajorTickSpacing(1);
		slider.setPaintTicks(true);
		
		try {
	        UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
		} 
		catch (Exception e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		
     	buttons=new JButton[13];//new 13��JButton
     	for(int j=0;j<=12;j++)
     	{
     		
	     	buttons[j]=new JButton();//��C��button new�X��
	     	east.add(buttons[j]);//��13��Button�[�Jeast�C��C
	     	buttons[j].setBackground(colornames[j]);//���s�]�����P�C��
     	}     	
  
     	
      	south.add(statusBar);//�[�J���A�C
     	south.add(display);//�[�J��ܿﶵ
     	south.add(hidden);//�[�J���ÿﶵ
    	south.add(slidersize);  
     	south.add(slider);
     	
     	//�N�Ҧ�button�[�Jwest���u��C��
	    west.add(label1); 
      	west.add(combobox);
        west.add(filled);
      	west.add(label2);
        west.add(small);       
        west.add(medium);
        west.add(big);  
        west.add(gradient);
        west.add(back);
        west.add(delete);
        
                
        pensizeradioGroup=new ButtonGroup();//�ؤ@�ӵ���j�p��ButtonGroup
        pensizeradioGroup.add(small);
        pensizeradioGroup.add(medium); 
        pensizeradioGroup.add(big);
        
        displayradioGroup=new ButtonGroup();//�ؤ@�����ÿﶵ��ButtonGroup
        displayradioGroup.add(hidden);
        displayradioGroup.add(display);
       
        hidden.addItemListener(new RadioButtonHandler());
        display.addItemListener(new RadioButtonHandler());
        
        CheckBoxHandler mhandler=new CheckBoxHandler();//�ض�checkbox��mhandler
        filled.addItemListener(mhandler);//��fill�ܼƥ[�Jmhandler
       
        //��ܿﶵ��Listener
        display.addItemListener(
	    		new ItemListener()
	    		{
	    			public void itemStateChanged(ItemEvent event)
	    			{
	    				if(event.getStateChange()==ItemEvent.SELECTED)
	    					west.setVisible(true);
	    				    
	    				
	    			}
	    
	    		}
	    		);
        //���ÿﶵ��Listener
        hidden.addItemListener(
	    		new ItemListener()
	    		{
	    			public void itemStateChanged(ItemEvent event)
	    			{
	    				if(event.getStateChange()==ItemEvent.SELECTED)
	    					west.setVisible(false);
	    				    
	    				
	    			}
	    
	    		}
	    		);
        
        
        //��j���p���ﶵ�[�JRadioButtonHandler
        small.addItemListener(new RadioButtonHandler());
        medium.addItemListener(new RadioButtonHandler());
        big.addItemListener(new RadioButtonHandler());
        //�N���s�[�Jhandler ��ButtonHandler
        ButtonHandler handler = new ButtonHandler();
        for(int k=0;k<=12;k++)
        buttons[k].addActionListener(handler);
        gradient.addActionListener(handler);
 	    back.addActionListener(handler);
	    delete.addActionListener(handler);
	    slider.addChangeListener(handler);
	    
	        
 		//�Ntool�u����Ӥu��W
	    combobox.addItemListener(
	    		new ItemListener()
	    		{
	    			public void itemStateChanged(ItemEvent event)
	    			{
	    				if(event.getStateChange()==ItemEvent.SELECTED)
	    					tool=combobox.getSelectedIndex();
	    				    
	    				
	    			}
	    
	    		}
	    		);
	  	    
	 }//end paintFrame123
	
	/*******************************�񺡫��s����@************************************/
	private class CheckBoxHandler implements ItemListener
	{
		public void itemStateChanged(ItemEvent event)
		{
			if (filled.isSelected()) 
				fill = true;
			else 
				fill = false;
		}
	}//end CheckBoxHandler
	/*******************************����j�p����@************************************/
	private class RadioButtonHandler implements ItemListener
	{	
		public void itemStateChanged(ItemEvent event)
		{
		if (event.getSource() == small)
		{	
			size = 5;
			
		}
		if (event.getSource() == medium) 
		{
			size = 10;
			
		}
		if (event.getSource() == big)
		{
			size = 15;
			
		}
	    }//end itemStateChanged
 			
    }//end RadioButtonHandler
	
	/*******************************�C��C�B���h�B�I����B�R���e������@***************/
	private class ButtonHandler implements ActionListener,ChangeListener
	{
		
      	public void actionPerformed(ActionEvent event)
		{
      	
      		if(event.getSource()==buttons[0])
		    {forecolor=colornames[0];Ctype=0;}
	    else if(event.getSource()==buttons[1])
	        {forecolor=colornames[1];Ctype=0;}
		else if(event.getSource()==buttons[2])
			{forecolor=colornames[2];Ctype=0;}
		else if(event.getSource()==buttons[3])
			{forecolor=colornames[3];Ctype=0;}
		else if(event.getSource()==buttons[4])
            {forecolor=colornames[4];Ctype=0;}
		else if(event.getSource()==buttons[5])
			{forecolor=colornames[5];Ctype=0;}
		else if(event.getSource()==buttons[6])
			{forecolor=colornames[6];Ctype=0;}
		else if(event.getSource()==buttons[7])
			{forecolor=colornames[7];Ctype=0;}
		else if(event.getSource()==buttons[8])
	        {forecolor=colornames[8];Ctype=0;}
		else if(event.getSource()==buttons[9])
			{forecolor=colornames[9];Ctype=0;}
		else if(event.getSource()==buttons[10])
			{forecolor=colornames[10];Ctype=0;}
		else if(event.getSource()==buttons[11])
			{forecolor=colornames[11];Ctype=0;}
		else if(event.getSource()==buttons[12])
			{forecolor=colornames[12];Ctype=0;}
			  		
			
			if (event.getSource() == gradient)
			{
				
				color1=JColorChooser.showDialog(PainterFrame123.this,"����C��@",color1);
				color2=JColorChooser.showDialog(PainterFrame123.this,"����C��G",color2);
				Ctype=1;
				
				if(color1==null||color2==null)
				Ctype=0;
			}
			if (event.getSource() == back)
			{
				backcolor = JColorChooser.showDialog(PainterFrame123.this,"Choose a color", backcolor);
				if (backcolor == null)
				{
					backcolor = Color.WHITE;
				}
				back.setBackground(backcolor);
				drawpanel.setBackground(backcolor);
			}
			
			 if (event.getSource() == delete)
			{
				drawpanel.pointCount = 0;
				x1 = 0; x2 = 0; y1 = 0; y2 = 0; //���]x�By
				x=0;
				image = new BufferedImage(2000,2000,BufferedImage.TYPE_INT_ARGB_PRE);
				drawpanel.setBackground(backcolor);
				repaint();
		    }
		}
      	
    	public void stateChanged(ChangeEvent event) 
		{
			size=slider.getValue();
			slidersize.setText("[����ʲ�]:"+size);
		}//�]�w��زʲ�
      	
      	
	}//end  ButtonHandler
	
	/***********************************�e��******************************************/
	public class DrawPanel extends JPanel
	{	
	
	 private int pointCount=0;

	 private Point[] point=new Point[100000];
		
	   public DrawPanel()
	   {			     
 	   image = new BufferedImage(2000,2000,BufferedImage.TYPE_INT_ARGB_PRE);//�]�w�i�H�ϥιϧΪ�image
	   //�ؤ@��mousehandler�å[MouseListener�BMouseMotionListener�[�J
 	   MouseHandler handler=new MouseHandler();
	   addMouseListener(handler);
	   addMouseMotionListener(handler);
		
	   }//end DrawPanel()
	 
	  /*******************************�ƹ�����@*****************************************/
       private class MouseHandler implements MouseListener,MouseMotionListener
       {
		public void mouseClicked(MouseEvent event)
		{
			statusBar.setText( String.format( "�ƹ���m [%d, %d] (�I��)", event.getX(), event.getY() ) );
		}	
		public void mousePressed(MouseEvent event)
		{
			 statusBar.setText( String.format( "�ƹ���m [%d, %d] (���U)", event.getX(), event.getY() ) );
	         x1 = event.getX();//�]�w��l�y��
	         y1 = event.getY();
		}		
		public void mouseReleased(MouseEvent event)
		{
			statusBar.setText( String.format( "�ƹ���m [%d, %d] (��})", event.getX(), event.getY() ) );
	        x2 = event.getX();
		    y2 = event.getY(); //�]�w���I�y��
		    drag=0;
		    repaint();
		}
		 
		public void mouseEntered(MouseEvent event)
		{
			statusBar.setText( String.format( "�ƹ���m [%d, %d] (�i�J�e��)", event.getX(), event.getY() ) );
		}
		
		public void mouseExited(MouseEvent event)
		{
			statusBar.setText( String.format( "�ƹ����}�e���~"));
			
		}
		public void mouseDragged(MouseEvent event)
		{
			statusBar.setText( String.format( "�ƹ���m [%d, %d] (�즲)", event.getX(), event.getY() ) );
			
			if(pointCount<point.length)
			{
				if (tool == 0 || tool == 5) //��������ξ�������즲�ʧ@
				{
				point[pointCount]=event.getPoint();
				pointCount++;
				repaint();
				}
				
				if(tool==2)
				{
					x2=event.getX();
					y2=event.getY();
					repaint();
					drag=1;
				}
				if(tool==3)
				{
					x2=event.getX();
					y2=event.getY();
					repaint();
					drag=1;
				}
				if(tool==4)
				{
					x2=event.getX();
					y2=event.getY();
					repaint();
					drag=1;
				}
			}
		}
		
		public void mouseMoved(MouseEvent event)
		{
			statusBar.setText( String.format( "�ƹ���m [%d, %d] (����)", event.getX(), event.getY() ) );
		}
	
		
      } //end MouseHandler   
      /****************************�u���������@****************************************************/
      public void paintComponent(Graphics g)
      {
    	  super.paintComponent(g);//�I�sJPanel��paintComponet
    	  
    	  Graphics gg = image.createGraphics();//�I�sBufferedImage����createGraphics��method
    	  Graphics2D g2d = ( Graphics2D ) gg;	 //�Ngg�뵹Graphics2D
    	  //��ܬO�_�ϥκ��h�\��
    	  if(Ctype==0)
    	  {
    		  g2d.setPaint(forecolor);
    	  }
    	  else
    	  {
    		  g2d.setPaint(new GradientPaint(x1,y1,color1,x2,y2,color2,false));  
          }
    	   
    	  g2d.setStroke(new BasicStroke(size, BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER));//�]�w��زʲӻP����䨤    	  
    	  
    	  //�\��C����@
    	  switch(tool)
    	  {
    	  case 0://����
    		 
    		  for(int i =x  ; i < pointCount ; i++)
				g2d.draw(new Ellipse2D.Double(point[i].x,point[i].y,size,size));
    		  x=drawpanel.pointCount;			
		     	break;
    	  case 1://���u
    		
    		  g2d.draw(new Line2D.Double(x1,y1,x2,y2));
    		
    		  break;
    	  case 2://����
    		 
    		  if(drag==1)
    		  {
    			  g.drawOval(Math.min(x1,x2), Math.min(y1,y2),Math.abs(x1-x2), Math.abs(y1-y2));
    		  }
    		  if(drag==0)
    		  {
    		  if( fill ) //�p�G��
					g2d.fill(new Ellipse2D.Double(Math.min(x1,x2),Math.min(y1,y2),Math.abs(x1-x2),Math.abs(y1-y2)));
				else
    		        g2d.draw(new Ellipse2D.Double(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1-y2)));
    		  }
			  break;
    	  case 3://�x��
    		if(drag==1)
    		{
    			 g.drawRect(Math.min(x1,x2), Math.min(y1,y2),Math.abs(x1-x2), Math.abs(y1-y2));
    					 
    		}
    		if(drag==0)
    		{
    		  if( fill ) //�p�G��
				g2d.fill(new Rectangle2D.Double(Math.min(x1,x2),Math.min(y1,y2),Math.abs(x1-x2),Math.abs(y1-y2)));
			else
    		    g2d.draw(new Rectangle2D.Double(Math.min(x1,x2),Math.min(y1,y2),Math.abs(x1-x2),Math.abs(y1-y2)));
    		}
    		  break;
    	  case 4://�ꨤ�x��
    		 if(drag==1)
    		 {
    			 g.drawRoundRect(Math.min(x1,x2), Math.min(y1,y2),Math.abs(x1-x2), Math.abs(y1-y2),50,50);
    		 }
    		 if(drag==0)
    		 {
    		  if( fill ) //�p�G��
					g2d.fill(new RoundRectangle2D.Double(Math.min(x1,x2),Math.min(y1,y2),Math.abs(x1-x2),Math.abs(y1-y2),50,50));
				else
    		        g2d.draw(new RoundRectangle2D.Double(Math.min(x1,x2),Math.min(y1,y2),Math.abs(x1-x2),Math.abs(y1-y2),50,50));
    		 }
    		 
    		  break;
    	  case 5://�����
    		  for(int i = x; i < pointCount ; i++)
				{
					g2d.setColor(backcolor);//�]���I���⪺����
					g2d.draw(new Ellipse2D.Double(point[i].x,point[i].y,size,size));
				}
			
				break;
    	  }
    	  g.drawImage(image, 0, 0, this); //ø�X�Ϲ�
          }
      
	}//end DrawPanel	

}//end PainterFrame123 