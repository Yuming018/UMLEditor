package UML;
import Shape.shape;
import Shape.line;
import Shape.Group;
import Mode.mode;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.EventListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Canvas extends JPanel  {
   protected static Canvas instance = null; 
   private List<shape> shapes = new ArrayList<shape>();
   public Rectangle SelectedArea = new Rectangle();
   private EventListener listener = null;
   
   public shape selectedObj = null;
   public shape tempLine = null;

   public Canvas() {

   }
   
   	public static Canvas getInstance() {
		if (instance == null) {
			instance = new Canvas();
		}
		return instance;
	}

   public void new_file() {
		shapes = new ArrayList<shape>();
		this.selectedObj = null;
		repaint();
	}

   public void setCurrentMode(mode currentMode) {
		removeMouseListener((MouseListener) listener);
		removeMouseMotionListener((MouseMotionListener) listener);
		listener = currentMode;
		addMouseListener((MouseListener) listener);
		addMouseMotionListener((MouseMotionListener) listener);
	}

   	public void addShape(shape shape) {
		shapes.add(shape);
	}

	public void removeShape(shape shape) {
		shapes.remove(shape);
	}

	public void delete_shape() {
		if(selectedObj != null){
			List<line> lines = selectedObj.getline();
			for(int i = 0; i < lines.size(); i++){
				shape line = lines.get(i);
				removeShape(line);
			}
			removeShape(selectedObj);
			this.selectedObj = null;
		}
		else{
			for(int i=0; i < shapes.size(); i++){
				shape shape = shapes.get(i);
				if(shape.group_selected){
					removeShape(shape);
					i--;
				}
			}
		}
		repaint();
	}

   public List<shape> getShape() {
		return shapes;
	}

	public void GroupShape() {
		int group_num = 0;
		for(int i=0; i < shapes.size(); i++){
			shape shape = shapes.get(i);
			if(shape.group_selected){
				group_num += 1;
			}
		}

		if (group_num > 1){
			Group group = new Group();
			for(int i=0; i < shapes.size(); i++){
				shape shape = shapes.get(i);
				if(shape.group_selected){
					group.addShapes(shape);
					shapes.remove(i);
					i--;
				}
			}
			group.setBounds();
			shapes.add(group);
			selectedObj = group;
			repaint();
		}
		else{
			JFrame jFrame = new JFrame();
			JOptionPane.showMessageDialog(jFrame, "Please select 2 or more object for grouping");
		}
	}

	public void removeGroup() {
		Group group = (Group) selectedObj;
		List<shape> groupShapes = group.getShapes();
		for(int i=0; i < groupShapes.size(); i++){
			shape shape = groupShapes.get(i);
			shapes.add(shape);
		}
		shapes.remove(selectedObj);
		reset();
		repaint();
	}
   
   public void reset() {
		if(selectedObj != null){
			selectedObj = null;
		}
		for(int i = 0; i < shapes.size(); i++){
			shape shape = shapes.get(i);
			shape.group_selected = false;
		}
		SelectedArea.setBounds(0, 0, 0, 0);
	}

	public void changeObjName(String name) {
		if(selectedObj != null){
			selectedObj.changeName(name);
			repaint();
		}
	}

	public boolean checkSelectedArea(shape shape) {
		Point upperleft = new Point(shape.getx1(), shape.gety1());
		Point lowerright = new Point(shape.getx2(), shape.gety2());
		if (SelectedArea.contains(upperleft) && SelectedArea.contains(lowerright)) {
			return true;
		}
		return false;
	}

   public void paint(Graphics g) {
		Dimension dim = getSize();
		g.setColor(new Color(0, 0, 0));
		g.fillRect(0, 0, dim.width, dim.height);
		g.setColor(Color.white);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(1));
		for (int i = shapes.size() - 1; i >= 0; i--) {
			shape shape = shapes.get(i);
			shape.draw(g);
			if (shape.group_selected) {
				shape.showport(g);	
			}
		}

		if (this.selectedObj != null) {
			selectedObj.showport(g);
		}
		if (this.tempLine != null){
			tempLine.draw(g);
		}
		if (!SelectedArea.isEmpty()) {
			g.setColor(new Color(37, 148, 216));
			g.drawRect(SelectedArea.x, SelectedArea.y, SelectedArea.width, SelectedArea.height);
		}
		
	}
}
