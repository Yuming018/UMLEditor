package UML;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

public class Menu extends JMenuBar {
   private Canvas canvas;

   public Menu() {
      canvas = Canvas.getInstance();

      JMenu menu;
      JMenuItem menu_item;
      
      menu = new JMenu("File");
      add(menu);

      menu_item = new JMenuItem("New file");
      menu.add(menu_item);
      menu_item.addActionListener(new new_file());
      menu_item = new JMenuItem("Exit");
      menu.add(menu_item);
      menu_item.addActionListener(new exit());
      
      menu = new JMenu("Edit");
      add(menu);

      menu_item = new JMenuItem("Rename");
      menu.add(menu_item);
      menu_item.addActionListener(new ChangeName());
      menu_item = new JMenuItem("Group");
      menu.add(menu_item);
      menu_item.addActionListener(new Groupobj());
      menu_item = new JMenuItem("Ungroup");
      menu.add(menu_item);
      menu_item.addActionListener(new UNGroupobj());
      menu_item = new JMenuItem("Delete");
      menu.add(menu_item);
      menu_item.addActionListener(new Deleteobj());
   }

   	class new_file implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			canvas.new_file();
		}
	}

   	class exit implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

   	class ChangeName implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			changeName();
		}
	}

   	class Groupobj implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			canvas.GroupShape();
		}
	}

   	class UNGroupobj implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			canvas.removeGroup();
		}
	}

	class Deleteobj implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			canvas.delete_shape();
		}
	}

   private void changeName() {
      JFrame inputTextFrame = new JFrame("Change Object Name");
		inputTextFrame.setSize(400, 100);
		inputTextFrame.getContentPane().setLayout(new GridLayout(0, 1));

      JPanel panel = null;
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JTextField Text = new JTextField("");
		panel.add(Text);
		inputTextFrame.getContentPane().add(panel);
		
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JButton confirm = new JButton("OK");
		panel.add(confirm);
		
		JButton cancel = new JButton("Cancel");
		panel.add(cancel);

      inputTextFrame.getContentPane().add(panel);
      inputTextFrame.setLocationRelativeTo(null);
      inputTextFrame.setVisible(true);

      confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.changeObjName(Text.getText());
				inputTextFrame.dispose();
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputTextFrame.dispose();
			}
		});
   }
}
