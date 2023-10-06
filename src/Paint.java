import javax.swing.JFrame;

public class Paint
{
   public static void main(String[] args)
   {
	  //PainterFrame application = new PainterFrame(); // ªìª©
      PaintFrame application = new PaintFrame();
      application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      application.setSize(800, 600);
      application.setVisible(true);
   } 
} 
