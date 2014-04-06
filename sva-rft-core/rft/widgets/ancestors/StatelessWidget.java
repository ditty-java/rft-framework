package rft.widgets.ancestors;

import java.awt.Point;

import rft.widgets.ObjectFactory;

import com.rational.test.ft.ObjectNotFoundException;
import com.rational.test.ft.object.interfaces.StatelessGuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TestObject;

public class StatelessWidget extends StatelessGuiSubitemTestObject implements IWidget { 
	
	/**		
	* Constructor to store an explicitly specified ToggleGUITestObject object <p>
	* @param to	the StatelessGuiSubitemTestObject from which to construct the widget.
	*/
	public StatelessWidget(TestObject to) {
		super(to);
	}
//***********************Instance Methods*******************************************************************************

	/**		
	* Waits for the particular widget stored in this object to exist on the screen <br>
	* Use only with constructed objects (particular instances of the class)<br>
	* Use the static methods if you don't wish to construct an object before using it<br>	
	* @return true if object exists within the default time; false if doesn't
	* 
	*/
	public boolean waitForExistenceBoolean() {
		return Widget.waitForExistenceBoolean(this);
	}
	
	/**
	* Determines whether the particular widget stored in this object is enabled<br>
	* Use only with constructed objects (particular instances of the class)<br>
	* Use the static methods if you don't wish to construct an object before using it<br>	
	* @return true if enabled, false if disabled  
	* */
	public boolean isEnabled() {
		return Widget.isEnabled(this);
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
	 * Clicks on the StatelessWidget.
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
	* Determines whether an explicitly specified object exists <br>
	* @param to	the object to check
	* @return true if object exists; false otherwise
	*/
	public static boolean exists(TestObject to) {
		return new StatelessWidget(to).exists();
	}
	
	/**		
	* Waits for an explicitly specified object to exist on the screen <br>
	* @param to	the object to check
	* @return Boolean - true if object exists within the default time; false if doesn't
	* 
	*/
	public static boolean waitForExistenceBoolean(TestObject to) {
		return new StatelessWidget(to).waitForExistenceBoolean();
	}
	
	/**
	* Returns whether a widget is enabled<br>
	* @param to 	TestObject being checked. 
	* @return returns true if enabled, false if disabled  
	* */
	public static boolean isEnabled(TestObject to) {
		return new StatelessWidget(to).isEnabled();
	}
	
	/**		
	* Clicks an explicitly specified widget<p>
	* @param to	widget to click
	*/
	public static void click(TestObject to) {		
		new StatelessWidget(to).click();
	}

//***************************Dynamic Static Methods***************************************************************************	
	
	/**	
	* Determines whether a dynamically located widget exists on the present page <p>
	* 
	* Call with e.g.<br>
  	*		<code><dir>WTable.exists(sCheckBoxName, sProperty, sClass, BrowserPage());</code></dir><br>
	* where sClass and sProperty indicate the class of the check box and the property of that class that sCheckBoxText represents 
	* @param sWidgetText	text of widget to check
	* @param sProperty		property value to search for (e.g. ".value")
	* @param sClass		class type identifier (e.g. "Html.INPUT.submit") 
	* @param toParent		parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	* @return true if object exists on present page; false otherwise
	*/
	public static boolean exists(String sWidgetText, String sProperty, String sClass, TestObject toParent) {
		return Widget.exists(sWidgetText, sProperty, sClass, toParent);
	}
	
	/**
	* Determines whether a dynamically located widget is enabled<p>
	* 
	* Call with e.g.<br>
  	*		<code><dir>WImage.isEnabled(sCheckBoxName, sProperty, sClass, BrowserPage());</code></dir><br>
	* where sClass and sProperty indicate the class of the check box and the property of that class that sCheckBoxText represents 
	* @param sWidgetText	text of widget to check
	* @param sProperty		property value to search for (e.g. ".value")
	* @param sClass		class type identifier (e.g. "Html.INPUT.submit") 
	* @param toParent		parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	* @return returns true if enabled, false if disabled  
	* 
	*/
	public static boolean isEnabled(String sWidgetText, String sProperty, String sClass, TestObject toParent) {
		return Widget.isEnabled(sWidgetText, sProperty, sClass, toParent);
	}
	
	/**		
	* Clicks a dynamically located widget of the specified class<p>
	* 
	* Call with e.g.<br>
  	*		<code><dir>WImage.click(sImageText, sProperty, sClass, BrowserPage());</code></dir><br>
	* where sClass and sProperty indicate the class of the button and the property of that class that sButtonText represents<br>
	* @param sWidgetText	text of widget to check
	* @param sProperty		property value to search for (e.g. ".value")
	* @param sClass		class type identifier (e.g. "Html.INPUT.submit") 
	* @param toParent		parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	*/
	public static void click(String sWidgetText, String sProperty, String sClass, TestObject toParent) {
		Widget.click(sWidgetText, sProperty, sClass, toParent);
	}
	
	
	/**		
	* Finds a StatelessGuiSubitemTestObject dynamically <br>
	* (Note: this method verifies that the TestObject found is a StatelessGuiSubitemTestObject<p>
	* 
	* Call with e.g.<br>
  	*		<code><dir>WImage.findDynamically(sListBoxName, sProperty, sClass, BrowserPage());</code></dir><br>
	* where sClass and sProperty indicate the class of the ListBox and the property of that class that sListBoxName represents<br>
	* @param sWidgetText	text of widget to check
	* @param sProperty		property value to search for (e.g. ".value")
	* @param sClass		class type identifier (e.g. "Html.INPUT.submit") 
	* @param toParent		parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	* @return the object attempting to find
	*/
	protected static TestObject findDynamically(String sWidgetText, String sProperty, String sClass, TestObject toParent) 
		throws ObjectNotFoundException {
			
			TestObject to = Widget.findDynamically(sWidgetText, sProperty, sClass, toParent);
			if (!(to instanceof StatelessGuiSubitemTestObject))
				throw new ObjectNotFoundException("Object found is not of the class StatelessGuiSubitemTestObject. You must use a different Widget class to represent this object.");
			return to;				
	}
	

}


