package Shape;

import java.util.List;
import java.awt.Rectangle;
import java.util.ArrayList;

public class port extends Rectangle{

	private List<line> lines = new ArrayList<line>();
	
	public void setpoint(int center_x, int center_y, int offset) {
			int x = center_x - offset;
			int y = center_y - offset;
			int w = offset * 2;
			int h = offset * 2;
			setBounds(x, y, w, h);
	}

	public void addline(line line) {
		lines.add(line);
	}

	public List<line> getline() {
		return lines;
	}
	
	public void resetlines(){
		for(int i = 0; i < lines.size(); i++){
			line line = lines.get(i);
			line.resetLocation();
		}
	}
}
