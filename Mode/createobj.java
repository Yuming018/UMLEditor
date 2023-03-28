package Mode;

import Shape.shape;
import Shape.useclass;
import Shape.classobj;
import java.awt.event.MouseEvent;

public class createobj extends mode {
	private String objType = null;
	private shape basicobj = null;

	public createobj(String objname){
		this.objType = objname;
	}

	public void mousePressed(MouseEvent e) {
		if(objType.equals("usecase")){
			basicobj = new useclass(e.getPoint().x, e.getPoint().y);
		}
		else if(objType.equals("class_option")){
			basicobj = new classobj(e.getPoint().x, e.getPoint().y);
		}
		canvas.addShape(basicobj);
		canvas.repaint();
	}
}
