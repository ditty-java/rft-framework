package rft.widgets.ancestors;

import java.awt.Point;

import rft.widgets.ObjectFactory;

import com.rational.test.ft.ObjectNotFoundException;
import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.util.regex.Regex;

public class Widget extends GuiTestObject implements IWidget { 

	/**		
	* Constructor to store an explicitly specified GuiTestObject object <p>
	* @param to	the GuiTestObject from which to construct the widget.
	*/		
	public Widget(TestObject to) {
		super(to);
	}

//***********************Instance Methods*******************************************************************************

	/**		
	* Waits for the particular widget stored in this object to exist on the screen <br>
	* @return true if object exists within the default time; false if doesn't
	* 
	*/
	public boolean waitForExistenceBoolean() {
		try {
			this.waitForExistence();
		} catch (com.rational.test.ft.ObjectNotFoundException e) {
			//ObjectNotFoundException is a super class of AmbiguousRecognitionException, so this'll catch both
			return false;
		}
		return true;
	}

	/**
	 * Waits up to max time for the particular widget stored in this object to exist on the screen rechecking at the interval specified<br>
	 * @param maxTime		The length of time to wait in seconds
	 * @param recheckInterval	The time at which to recheck in seconds
	 * @return true if object exists within the default time; false otherwise
	 */
	public boolean waitForExistenceBoolean(double maxTime,  double recheckInterval) {
		try {
			this.waitForExistence(maxTime,  recheckInterval);
		} catch  (com.rational.test.ft.ObjectNotFoundException e) {
			//ObjectNotFoundException  is a super class of AmbiguousRecognitionException, so this'll catch both
			return false;
		}
		return true;
	}
	
	/**
	* Determines whether the particular widget stored in this object is enabled<br>
	* If this type of widget can't be disabled (such as WStaticText or WImage), this method is the same as exists()
	* @return true if enabled, false if disabled  
	* */
	public boolean isEnabled() {
		try {
			if (ObjectFactory.isHTML(this))
			{
				if (this.getProperties().containsKey(".disabled"))
				{
					return !Boolean.valueOf(this.getProperty(".disabled").toString()).booleanValue();
				}
				else if (this.getProperties().containsKey("disabled"))
				{
					return !Boolean.valueOf(this.getProperty("disabled").toString()).booleanValue();
				}
				else
				{
					//TODO
					//PackageLoggingController.logPackageInfo(PackageLoggingController.PACKAGELOGLEVEL_ERRORS_ONLY, "Widget#isDisabled: Could not find the appropriate property indicating whether the control was enabled.");
					return false;
				}
			}		
			else //not html
			{
				return Boolean.valueOf(this.getProperty("enabled").toString()).booleanValue();
			}
		} 
		//if property not found, then widget can't be disabled, so return exists
		catch(com.rational.test.ft.PropertyNotFoundException e) {
			return exists();
		}		
	}
	
	/**
	* Prints out all the properties of a widget to the console
	*/
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
	
	/**
	 * Clicks on the widget<p>
	 * and logs action if appropriate package log level is set
	 */	
	public void click()
	{
		//get properties need for logging before you click, because otherwise click might take you to another page causing an ObjectNotFoundException
//		String sWidgetType = getWidgetType();
//		String sWidgetName = getName();
				
		super.click();
		
		//TODO
		//PackageLoggingController.logPackageInfo(PackageLoggingController.PACKAGELOGLEVEL_WIDGET_CLICKS, "Clicked on " + sWidgetType + " " + (sWidgetName != null ? "\"" + sWidgetName + "\"": "- no name for this object could be found."));
	}
			
	/**
	 * Clicks on the specified point within the widget<br>
	 * and logs action if appropriate package log level is set<p>
	 * 	 
	 * @param p	the point in the widget to click on
	 * 
	 */	
	public void click(Point p)
	{
		//get properties need for logging before you click, because otherwise click might take you to another page causing an ObjectNotFoundException
//		String sWidgetType = getWidgetType();
//		String sWidgetName = getName();
				
		super.click(p);
		
		//TODO
		//PackageLoggingController.logPackageInfo(PackageLoggingController.PACKAGELOGLEVEL_WIDGET_CLICKS, "Clicked on " + sWidgetType + " " + (sWidgetName != null ? "\"" + sWidgetName + "\" at " + p.toString() : "- no name for this object could be found."));
	}	
	
	/**
	 * Performs a click without logging, no matter what logging level is set<p>
	 * (This is used if you need to click on a widget, but want to log the action later, like in WTextField.setText()<br>
	 */
	public void silentClick()
	{
		super.click();
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
	* @return the name; null if no name could be found
	* 
	*/
	public String getName() {
		return ObjectFactory.getObjectName(this);
	}
		
//***************************Static Methods***************************************************************************	
	
	/**		
	* Waits for an explicitly specified object to exist on the screen <br>
	* @param to	the object to check
	* @return true if object exists within the default time; false if doesn't
	* 
	*/
	public static boolean waitForExistenceBoolean(TestObject to) {
		return new Widget(to).waitForExistenceBoolean();
	}
	
	/**
	* Returns whether an explicitly specified widget is enabled<br>
	* @param to	TestObject being checked. 
	* @return true if enabled, false if disabled  
	* */
	public static boolean isEnabled(TestObject to) {
		return new Widget(to).isEnabled();
	}
	
	/**
	* Determines whether an explicitly specified object exists <br>
	* @param to	the object to check for existence
	* @return true if object exists; false otherwise
	*/
	public static boolean exists(TestObject to) {
		return to.exists();
	}
	
	/**		
	* Clicks an explicitly specified widget<p>
	* and logs action if appropriate package log level is set
	* @param to 	widget to click
	*/
	public static void click(TestObject to) {		
		new Widget(to).click();
	}	
	
//*******************Dynamic Static**********************************
	/**		
	* Clicks a dynamically located widget of the specified class<p>
	* 
	* Call with e.g.<br>
  	*		<code><dir>WButton.click(sButtonName, sProperty, sClass, BrowserPage());</code></dir><br>
	* where sClass and sProperty indicate the class of the button and the property of that class that sButtonText represents<br>
	* @param sWidgetText	text of widget to check
	* @param sProperty		property value to search for (e.g. ".value")
	* @param sClass		class type identifier (e.g. "Html.INPUT.submit") 
	* @param toParent		parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	*/
	public static void click(String sWidgetText, String sProperty, String sClass, TestObject toParent)
		throws ObjectNotFoundException {
		TestObject to = null;

		//find the object
		to = findDynamically(sWidgetText, sProperty, sClass, toParent);
		to.waitForExistence(); //will throw ObjectNotFoundException to caller if not found within default timeout

		new Widget(to).click(); //to log it

		//unregister the object
		to.unregister();
	}
	/**	
	* Determines whether a dynamically located widget exists on the present page <p>
	* 
	* Call with e.g.<br>
  	*		<code><dir>WButton.exists(sButtonName, sProperty, sClass, BrowserPage());</code></dir><br>
	* where sClass and sProperty indicate the class of the button and the property of that class that sButtonText represents<br>
	* @param sWidgetText	text of widget to check
	* @param sProperty		property value to search for (e.g. ".value")
	* @param sClass		class type identifier (e.g. "Html.INPUT.submit") 
	* @param toParent		parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	* @return true if object exists on present page; false otherwise
	*/
	public static boolean exists(String sWidgetText, String sProperty, String sClass, TestObject parent) {
		TestObject to;
		try {
			to = findDynamically(sWidgetText, sProperty, sClass, parent);
		} catch (ObjectNotFoundException e) {
			return false;
		}
		to.unregister();
		return true;
	}
	
	/**
	* Determines whether a dynamically located widget is enabled<p>
	* 
	* Call with e.g.<br>
  	*		<code><dir>WButton.isEnabled(sButtonName, sProperty, sClass, BrowserPage());</code></dir><br>
	* where sClass and sProperty indicate the class of the button and the property of that class that sButtonText represents<br>
	* @param sWidgetText		text of widget to check
	* @param sProperty		property value to search for (e.g. ".value")
	* @param sClass		class type identifier (e.g. "Html.INPUT.submit") 
	* @param toParent		parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	* @return true if object is enabled; false otherwise
	*/
	public static boolean isEnabled(String sWidgetText, String sProperty, String sClass, TestObject parent) {
		TestObject to = findDynamically(sWidgetText, sProperty, sClass, parent);
		boolean bReturn = isEnabled(to);
		to.unregister();
		return bReturn;
	}
	
	/**		
	* Finds a TestObject dynamically using a string<br>
	* 
	* Call with e.g.<br>
  	*		<code><dir>WButton.findDynamically(sButtonName, sProperty, sClass, BrowserPage());</code></dir><br>
	* where sClass and sProperty indicate the class of the button and the property of that class that sButtonText represents<br>
	* @param sWidgetText	text of widget to check
	* @param sProperty		property value to search for (e.g. ".value")
	* @param sClass		class type identifier (e.g. "Html.INPUT.submit") 
	* @param toParent		parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	* @return the object attempting to find
	*/
	protected static TestObject findDynamically(String sWidgetText, String sProperty, String sClass, TestObject parent) 
		throws ObjectNotFoundException {
			TestObject to = ObjectFactory.findTestObject(sWidgetText, sProperty, sClass, parent);
		
			//Make sure the object exists
			if (to == null)
				throw new ObjectNotFoundException(sClass + " with text '" + sWidgetText + "' not found");
		
			return to;				
	}
	
	/**		
	* Finds a TestObject dynamically using a regular expression<br>
	* 
	* Call with e.g.<br>
  	*		<code><dir>WLink.findDynamically(new Regex(sLinkText), sProperty, sClass, BrowserPage());</code></dir><br>
	* where sClass and sProperty indicate the class of the Link and the property of that class that sLinkText represents<br>
	* @param reWidgetText	regular expression that identifies the text of widget you are searching for
	* @param sProperty		property value to search for (e.g. ".value")
	* @param sClass		class type identifier (e.g. "Html.INPUT.submit") 
	* @param toParent		parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	* @return the object attempting to find
	*/
	protected static TestObject findDynamically(Regex reWidgetText, String sProperty, String sClass, TestObject toParent) 
		throws ObjectNotFoundException {
		
			TestObject to = ObjectFactory.findTestObject(reWidgetText, sProperty, sClass, toParent);
		
			//Make sure the object exists
			if (to == null)
				throw new ObjectNotFoundException(sClass + " with text '" + reWidgetText.toString() + "' not found");
		
			return to;				
	}

}

