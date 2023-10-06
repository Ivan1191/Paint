import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JRadioButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;


public class PainterFrame extends JFrame
   implements ItemListener, ActionListener
   {

   private static final String[] shapes = 
	      {"����", "���u",  "����", "�x��","�ꨤ�x��"};
   
   private JButton undoButton; 
   private JButton clearButton;
   private final JRadioButton bigButton;
   private final JRadioButton mediumButton;
   private final JRadioButton smallButton;
   private final ButtonGroup radioGroup;
   private String bigFont;
   private String mediumFont;
   private String smallFont;
   private JComboBox<String> shapeChoices;
   private JCheckBox filledCheckBox;
   private final JLabel statusBar;

   public PainterFrame()
   {
	  
	  super("Painter");
	  
	  Icon bug = new ImageIcon(getClass().getResource("java.png"));
	  JOptionPane.showMessageDialog(null, "Wellcome","�T��",2,bug); // OK_CANCEL_OPTION
      
      JPanel topPanel1 = new JPanel();        
      topPanel1.setLayout(new GridLayout(2,1));

      Box box1 = Box.createHorizontalBox();
	  Box box2 = Box.createHorizontalBox();
      
      shapeChoices = new JComboBox<String>(shapes);
      shapeChoices.setMaximumRowCount(3);
      shapeChoices.addItemListener(this);
      JLabel label1 = new JLabel("ø�Ϥu��");
      
      box1.add(label1);
      box2.add(shapeChoices);
      
      topPanel1.add(box1);  
      topPanel1.add(box2);
	
	  
	  JPanel topPanel2 = new JPanel();
	  topPanel2.setLayout(new GridLayout(2,1));
	  
	  Box box3 = Box.createHorizontalBox();
	  Box box4 = Box.createHorizontalBox();
	  
	  JLabel label2 = new JLabel("����j�p");
	  
	  box3.add(label2);
      
      bigButton = new JRadioButton("�j   ",false); // �_�l�Ҭ��D��
      mediumButton = new JRadioButton("��   ",false);
      smallButton = new JRadioButton("�p   ",false);
	  
      box4.add(smallButton);
      box4.add(mediumButton);
      box4.add(bigButton);
      
	  radioGroup = new ButtonGroup(); 
	  radioGroup.add(bigButton); 
	  radioGroup.add(mediumButton); 
	  radioGroup.add(smallButton); 
	  

	  bigButton.addItemListener(new RadioButtonHandler("�j"));
	  mediumButton.addItemListener(new RadioButtonHandler("��"));
	  smallButton.addItemListener(new RadioButtonHandler("�p"));
	  
	  topPanel2.add(box3);
	  topPanel2.add(box4);
	  
	  JPanel topPanel3 = new JPanel();
	  topPanel3.setLayout(new GridLayout(2,1));
	  
	  JLabel label3 = new JLabel("��");
      filledCheckBox = new JCheckBox(" ");
      filledCheckBox.addItemListener(this);
      topPanel3.add(label3);
      topPanel3.add(filledCheckBox);

      JPanel topPanel4 = new JPanel();
      
      undoButton = new JButton("�����C��");
      undoButton.addActionListener(this);
      topPanel4.add(undoButton);
      
      clearButton = new JButton("�M���e��");
      clearButton.addActionListener(this);
      topPanel4.add(clearButton);
      
      JPanel topPanel5 = new JPanel();
      
      topPanel5.add(topPanel1);
      topPanel5.add(topPanel2);
      topPanel5.add(topPanel3);
      topPanel5.add(topPanel4);
      
      add(topPanel5, BorderLayout.NORTH);
      
      JPanel topPanel6 = new JPanel();
      topPanel6.setBackground(Color.white);
      add(topPanel6, BorderLayout.CENTER);
      
      statusBar = new JLabel("Mouse outside Frame");
      add(statusBar,BorderLayout.SOUTH);
      MouseHandler handler = new MouseHandler();
      topPanel6.addMouseListener(handler); 
      topPanel6.addMouseMotionListener(handler); 
   } 

   private class RadioButtonHandler implements ItemListener 
   {
		private String a;
		
		public RadioButtonHandler(String a)
		{
			this.a = a;
		}
		public void itemStateChanged(ItemEvent event)
		{
   		if (event.getStateChange() == ItemEvent.SELECTED)
   			System.out.println("��� "+a+" ����");
		}
		
   }
   public void itemStateChanged(ItemEvent e)
   {
      if (e.getSource() == shapeChoices) // choosing a shape
      {
    	  if (e.getStateChange() == ItemEvent.SELECTED)
    		  System.out.println("��� "+shapes[shapeChoices.getSelectedIndex()]); // ���}�C����
      }
      else if (e.getSource() == filledCheckBox) // filled/unfilled
      {	  
    	    if (e.getStateChange() == ItemEvent.SELECTED) {
		        System.out.println("��� ��");
		    }
		    if (e.getStateChange() == ItemEvent.DESELECTED) {
		        System.out.println("���� ��");
		    }
      } 
   }
   public void actionPerformed(ActionEvent e)
   {
      if (e.getSource() == undoButton)
    	  System.out.println("�I�� "+e.getActionCommand());
      else if (e.getSource() == clearButton)
    	  System.out.println("�I�� "+e.getActionCommand());
   }
   public class MouseHandler extends MouseAdapter
   {   
	   public void mouseMoved(MouseEvent event)
	   {
	      getContentPane().setBackground(Color.BLACK); 
	      statusBar.setForeground(Color.WHITE); // ���r�C��
		  statusBar.setText(String.format("���Ц�m:(%d, %d)", 
	       event.getX(), event.getY())); 
	   }
	   public void mouseExited(MouseEvent event)
	   {
		   getContentPane().setBackground(null); // ��^�w�]��
		   statusBar.setForeground(null);
		   statusBar.setText("Mouse outside Frame");
	   }
   }   
}