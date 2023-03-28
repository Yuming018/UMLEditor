package UML;
import java.awt.*;
import javax.swing.*;

public class UMLEditor extends JFrame{
  
   public UMLEditor(){
      Canvas canvas = new Canvas();
      Button button = new Button();
      Menu menu = new Menu();

      canvas = Canvas.getInstance();

      getContentPane().setLayout(new BorderLayout());
      getContentPane().add(canvas, BorderLayout.CENTER);
      getContentPane().add(button, BorderLayout.WEST);
      getContentPane().add(menu, BorderLayout.NORTH);
   }
   public static void main(String[] args) {
      UMLEditor umleditor = new UMLEditor();
      umleditor.setTitle("UML Editor");
      umleditor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      umleditor.setSize(1200,700);
      umleditor.setLocationRelativeTo(null);
      umleditor.setVisible(true);
   }
}
