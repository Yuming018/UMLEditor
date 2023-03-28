package UML;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import Mode.mode;
import Mode.select;
import Mode.createobj;
import Mode.createline;

public class Button extends JPanel {
   private Canvas canvas;
   private JButton holdBtn = null;

   public Button() {
      canvas = Canvas.getInstance(); 
      setLayout(new GridLayout(12, 5, 2, 2));

      ImageIcon selcet = new ImageIcon("image/select.png");
      Btn selectbtn = new Btn("select", selcet, new select());

      ImageIcon association = new ImageIcon("image/association.png");
      Btn associationbtn = new Btn("association", association, new createline("association"));

      ImageIcon generalization = new ImageIcon("image/generalization.png");
      Btn generalizationbtn = new Btn("generalization", generalization, new createline("generalization"));

      ImageIcon composition = new ImageIcon("image/composition.png");
      Btn compositionbtn = new Btn("composition", composition, new createline("composition"));
      
      ImageIcon class_option = new ImageIcon("image/class_option.png");
      Btn class_optionbtn = new Btn("class_option", class_option, new createobj("class_option"));

      ImageIcon use_case = new ImageIcon("image/use_case.png");
      Btn use_casebtn = new Btn("use_case", use_case, new createobj("usecase"));

      add(selectbtn);
      add(associationbtn);
      add(generalizationbtn);
      add(compositionbtn);
      add(class_optionbtn);
      add(use_casebtn);
   }

   private class Btn extends JButton{
      mode mode;
      String btnName ;

      public Btn(String btnName, ImageIcon icon, mode ToolMode){
         this.btnName = btnName;
         mode = ToolMode;
         setToolTipText(btnName);
			setIcon(icon);
			setFocusable(false);
			setBackground(new Color(100, 100, 100));
			setBorderPainted(false);
			setRolloverEnabled(true);
			addActionListener(new toolListener());
      }
      
      class toolListener implements ActionListener {
         public void actionPerformed(ActionEvent e) {
            System.out.println(btnName);
            if(holdBtn != null)
					holdBtn.setBackground(new Color(100, 100, 100));
				holdBtn = (JButton) e.getSource();
				holdBtn.setBackground(new Color(200,200,200));
            canvas.setCurrentMode(mode);
				canvas.reset();
				canvas.repaint();
         }
      }
   }
}
