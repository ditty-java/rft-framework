package rft.widgets.ancestors;

import rft.widgets.ObjectFactory;

import com.rational.test.ft.ObjectNotFoundException;
import com.rational.test.ft.object.TestObjectReference;
import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.StatelessGuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TestObject;

public class SubitemWidget extends GuiSubitemTestObject implements IWidget { 
	
	/**		
	* Constructor to store an explicitly specified GuiSubitemTestObject object <p>
	* @param ref	a reference to the the GuiSubitemTestObject from which to construct the widget. 
	* (GuiSubitemTestObject doesn't have a constructor that takes a TestObject, so we need to construct these with a reference. You can use to.getObjectReference() to get the reference of a test object (which also works for objects found dynamically).)
	* 
	*/
	public SubitemWidget(TestObjectReference ref) {
		super(ref);
	}
	
	/**		
	* Constructor to store an explicitly specified GuiSubitemTestObject object <p>
	* @param to	the GuiSubitemTestObject from which to construct the widget.
	* 
	*/
	public SubitemWidget(TestObject to) {
		//GuiSubitemTestObject doesn't have a constructor that takes a TestObject, 
		//so we need to construct these with a reference. to.getObjectReference() gets the reference of a test object 
		//(This also works for objects found dynamically)
		super(to.getObjectReference());
		
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
	* @return  true if enabled, false if disabled  
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
	* @return true if object exists within the default time; false if doesn't
	* 
	*/
	public static boolean waitForExistenceBoolean(TestObject to) {
		return new StatelessWidget(to).waitForExistenceBoolean();
	}
	
	/**
	* Returns whether a widget is enabled<br>
	* @param to	TestObject being checked. 
	* @return true if enabled, false if disabled  
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
  	*		<code><dir>WListBox.exists(sCheckBoxName, sProperty, sClass, BrowserPage());</code></dir><br>
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
  	*		<code><dir>WListBox.isEnabled(sCheckBoxName, sProperty, sClass, BrowserPage());</code></dir><br>
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
  	*		<code><dir>WListBox.click(sImageText, sProperty, sClass, BrowserPage());</code></dir><br>
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
	* Finds a GuiSubitemTestObject dynamically <br>
	* (Note: this method verifies that the TestObject found is a GuiSubitemTestObject<p>
	* 
	* Call with e.g.<br>
  	*		<code><dir>WListBox.findDynamically(sListBoxName, sProperty, sClass, BrowserPage());</code></dir><br>
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
			if (!(to instanceof GuiSubitemTestObject))
				throw new ObjectNotFoundException("Object found is not of the class GuiSubitemTestObject. You must use a different Widget class to represent this object.");
			return to;				
	}
	

}



