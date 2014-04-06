package rft.widgets;

import rft.tookit.BrowserOps;
import rft.widgets.ancestors.IWidget;
import rft.widgets.ancestors.Widget;

import com.rational.test.ft.object.interfaces.BrowserTestObject;
import com.rational.test.ft.object.interfaces.TestObject;

public class WBrowser extends BrowserTestObject implements IWidget {
	
//***************************Constructors**************************
	/**
	 * Constructor for TopLevelWidget.
	 * @param to		the TopLevelTestObject from which to construct the widget.
	 */
	public WBrowser(TestObject to) {
		super(to);
	}
		
	/**
	 * Retrieves the caption of the browser. <br>
	 * @return the text (title) of the object
	 */	
	public String getCaption() {
		return BrowserOps.getActiveBrowserCaption();

	}

//*********************************Instance Methods************************
	/**
	 * Waits for the particular widget stored in this object to exist on the screen <br>
	 * @return true if object exists within the default time; false if doesn't
	 */
	public boolean waitForExistenceBoolean() {
		return Widget.waitForExistenceBoolean(this);
	}
	
		/**
	* Waits for the browser to be ready <p>
	* @return true if browser is ready, false if timed out
	*/
	public boolean waitForReady() {
		return BrowserOps.waitForReady(this);	
	}
	
	/**
	* Determines whether the browser is ready<br>
	* @return returns true if enabled, false if disabled
	*/
	public boolean isReady() {
		return BrowserOps.isReady(this);
	}
	
	public void printProperties() {
		ObjectFactory.printProperties(this);
	}
	
	/**
	 * Prints out the methods of a widget to the console (omitting methods from java.lang.Object)
	 */	
	public void printMethods()
	{
		ObjectFactory.printMethods(this);
	}
	
	//************************ For Logging ****************************	
	
	/**
	 * Returns the name of the type of the widget<br>
	 * e.g. "button" or "listbox" or "textfield"
	 * @return the widget type
	 */		
	public String getWidgetType() {
		return ObjectFactory.getClassName(this);
	}

	/**
	* Gets the name of the object from the properties <br>
	* Note: the object must be on the screen or you will get an ObjectNotFoundError<br>
	* If no name could be found, this method returns null
	* @return the name of the object; null if no name could be found
	*/
	public String getName() {
		return ObjectFactory.getObjectName(this);
	}
}
	


