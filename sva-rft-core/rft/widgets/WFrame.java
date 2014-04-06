package rft.widgets;

import rft.widgets.ancestors.IWidget;
import rft.widgets.ancestors.TopLevelWidget;
import java.awt.Point;
import java.awt.Rectangle;

import com.rational.test.ft.object.interfaces.TestObject;

public class WFrame extends TopLevelWidget implements IWidget {

	/**
	 * Constructor for TopLevelWidget.
	 * @param to	 the TopLevelObject from which to construct the widget.
	 */
	public WFrame(TestObject to) {
		super(to);
	}

		
	/**
	 * Retrieves the text (title) of the object. <br>
	 * @return the text (title) of the object
	 * @see ibm.widgets.IToggleWidget#isMaximized()
	 */	
	public String getText() {
		return (String)getProperty("text");
	}

	/**
	 * Determines if the object is maximized or not. <br>
	 * @return true if object is maximized
	 * @see ibm.widgets.IToggleWidget#isMaximized()
	 */	
	public boolean isMaximized() {
		return ((Boolean)getProperty("maximized")).booleanValue();
	}

	/**
	 * Determines if the object is minimized or not. <br>
	 * @return true if object is minimized
	 * @see ibm.widgets.IToggleWidget#isMinimized()
	 */	
	public boolean isMinimized() {
		return ((Boolean)getProperty("minimized")).booleanValue();
	}

	/**
	 * Retrieves the location of the object as a Point. <br>
	 * @return the location of the object
	 * @see ibm.widgets.IToggleWidget#getLocation()
	 */			
	public Point getLocation() {
		Point p = null;
		Rectangle location = (Rectangle)getProperty("location");
		if (location != null) {
			p = new Point(location.x, location.y);
		}
		return p;
	}
}
