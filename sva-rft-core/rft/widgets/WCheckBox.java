package rft.widgets;

import rft.widgets.ancestors.ToggleWidget;

import com.rational.test.ft.ObjectNotFoundException;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.ToggleGUITestObject;
import com.rational.test.ft.script.State;

public class WCheckBox extends ToggleWidget {
	
	//html
	/**Global string for XDE Tester HTML CheckBox property (".name")*/
	public static String gsHtmlDefaultProperty = ObjectFactory.gsHtmlCBProp;
	/**Global string for XDE Tester HTML CheckBox class ("Html.INPUT.checkbox")*/
	public static String gsHtmlDefaultClass = ObjectFactory.gsHtmlCBClass;
	
	//java
	/**Global string for XDE Tester Swing CheckBox property ("name")*/
	public static String gsSwingDefaultProperty = ObjectFactory.gsSwingCBProp;
	/**Global string for XDE Tester Swing CheckBox class ("javax.swing.JCheckBox")*/
	public static String gsSwingDefaultClass = ObjectFactory.gsSwingCBClass;
		
	/**Global string for XDE Tester AWT CheckBox property ("label")*/
	public static String gsAwtDefaultProperty = ObjectFactory.gsAwtCBProp;
	/**Global string for XDE Tester AWT CheckBox class ("java.awt.CheckBox")*/
	public static String gsAwtDefaultClass = ObjectFactory.gsAwtCBClass;
	
//******************************Constructors************************************************************************

	/**		
	* Constructor to find and store a dynamically located check box object <p>
	* 
	* Call with <br>
  	*		<dd><code>CheckBox myCheckBox = new CheckBox(sCheckBoxName, sProperty, sClass, BrowserPage());</code><br>
	* where sClass and sProperty indicate the class of the check box and the property of that class that sCheckBoxText represents (e.g. Class = "Html.INPUT.submit"; Property = ".value"<br>
	* @param String sCheckBox - text caption/id of check box to find.
	* @param String sValue - property value to search for (e.g. ".name").
	* @param String sClass - TestObject class type identifier (e.g. "Html.INPUT.checkbox"").
	* @param TestObject parent - Parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	*/
	public WCheckBox(String sCheckBox, String sProperty, String sClass, TestObject toParent) {
		super(findDynamically(sCheckBox, sProperty, sClass, toParent));
	}
	
	/**		
	* Constructor to store an explicitly specified check box object <p>
	* @param checkbox		the ToggleGUITestObject from which to construct the widget.
	* 
	*/
	public WCheckBox(TestObject checkbox) {
		super(checkbox);
	}
	
//************************For Logging********************	
	/**
	 * Returns the name of the type of the widget<br>
	 * e.g. "button" or "listbox" or "textfield"
	 * @return the widget type.
	 */		
	public String getWidgetType() {
		return "checkbox";
	}
	
//*****************************************Instance Methods***********************************

	/**
	* Checks the check box stored in this particular CheckBox object <p>
	* Use only with constructed CheckBox objects (particular instances of the class)<br>
	* Use the static methods if you don't wish to construct a CheckBox object before using it	
	*/
	public void check()
	{
		//get properties need for logging before you click, because otherwise click might take you to another page causing an ObjectNotFoundException
//		String sWidgetType = getWidgetType();
//		String sWidgetName = getName();
		
		this.clickToState(State.selected());
		//TODO
		//PackageLoggingController.logPackageInfo(PackageLoggingController.PACKAGELOGLEVEL_WIDGET_CLICKS, "Checked " + sWidgetType + " \"" + sWidgetName + "\"");
	}
	
	/**
	* Unchecks the check box stored in this particular CheckBox object <p>
	* Use only with constructed CheckBox objects (particular instances of the class)<br>
	* Use the static methods if you don't wish to construct a CheckBox object before using it	
	*/
	public void uncheck()
	{
		//get properties need for logging before you click, because otherwise click might take you to another page causing an ObjectNotFoundException
//		String sWidgetType = getWidgetType();
//		String sWidgetName = getName();
		
		this.clickToState(State.notSelected());
		//TODO
		//PackageLoggingController.logPackageInfo(PackageLoggingController.PACKAGELOGLEVEL_WIDGET_CLICKS, "Unchecked " + sWidgetType + " \"" + sWidgetName + "\"");
	}
	
	/**
	* Determines if this particular CheckBox object is checked<p>
	* Use only with constructed CheckBox objects (particular instances of the class)<br>
	* Use the static methods if you don't wish to construct a CheckBox object before using it	
	* @return true if checked; false otherwise.
	*/
	public boolean isChecked()
	{
		return this.getState().isSelected();
	}

//************************************Static Methods*******************************************************
	
	/** 	
	* Checks an explicitly specified CheckBox TestObject <p>
	* @param checkbox		CheckBox to modify.
	*/
	public static void check(TestObject checkbox)
	{
		new WCheckBox(checkbox).check();
	}
	
	/** 	
	* Unchecks a explicitly specified CheckBox TestObject <p>
	* @param checkbox		CheckBox to modify.
	*/
	public static void uncheck(TestObject checkbox)
	{
		new WCheckBox(checkbox).uncheck();
	}
		
	/**		
	* Determines if an explicitly specified CheckBox is checked<p>
	* @param checkbox		CheckBox TestObject.
	* @return true if checked; false otherwise.
	* */
	public static boolean isChecked(TestObject checkbox)
	{
		return new WCheckBox(checkbox).isChecked();
		
	}
	
//*****************Dynamic Static Methods************************
	/** 	
	* Checks a dynamically located CheckBox <p>
	* 
	* Call with<br>
  	*		<dd><code>CheckBox.check(sCheckBoxName, sProperty, sClass, BrowserPage());</code><br>
	* where sClass and sProperty indicate the class of the check box and the property of that class that sCheckBoxText represents <br>
	* @param sCheckBox		text of check box to check.
	* @param sProperty		property value to search for (e.g. ".name").
	* @param sClass		TestObject class type identifier (e.g. "Html.INPUT.checkbox"). 
	* @param parent		Parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	*/
	public static void check(String sCheckBox, String sProperty, String sClass, TestObject toParent)
		throws ObjectNotFoundException {

			//find the object
			ToggleGUITestObject tCheckBox =  (ToggleGUITestObject)findDynamically(sCheckBox, sProperty, sClass, toParent);
			tCheckBox.waitForExistence(); 	//will throw ObjectNotFoundException to caller if not found within default timeout

			check(tCheckBox);

			//unregister the object
			tCheckBox.unregister();
	}
	
	/** 	
	* Unchecks a dynamically located CheckBox <p>
	* 
	* Call with<br>
  	*		<dd><code>CheckBox.uncheck(sCheckBoxName, sProperty, sClass, BrowserPage());</code><br>
	* where sClass and sProperty indicate the class of the check box and the property of that class that sCheckBoxText represents 
	* @param sCheckBox		text of check box to check.
	* @param sProperty		property value to search for (e.g. ".name").
	* @param sClass		TestObject class type identifier (e.g. "Html.INPUT.checkbox"). 
	* @param parent		Parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	*/
	public static void uncheck(String sCheckBox, String sProperty, String sClass, TestObject parent)
		throws ObjectNotFoundException {
		
			//find the object
			TestObject tCheckBox = findDynamically(sCheckBox, sProperty, sClass, parent);
			tCheckBox.waitForExistence(); 	//will throw ObjectNotFoundException to caller if not found within default timeout

			uncheck(tCheckBox);

			//unregister the object
			tCheckBox.unregister();
	}
	
	/**		
	* Determines if a dynamically located check box item is checked<p>
	* 
	* Call with<br>
  	*		<dd><code>CheckBox.isChecked(sCheckBoxName, sProperty, sClass, BrowserPage());</code><br>
	* where sClass and sProperty indicate the class of the check box and the property of that class that sCheckBoxText represents 
	* @param sCheckBox		text of check box to check.
	* @param sProperty		property value to search for (e.g. ".name").
	* @param sClass		TestObject class type identifier (e.g. "Html.INPUT.checkbox"). 
	* @param parent		Parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	* @return true if checked; false otherwise. 
	* 		
	*/
	public static boolean isChecked(String sCheckBox, String sProperty, String sClass, TestObject parent)
		throws ObjectNotFoundException {
			
			boolean bReturn;

			//find the object
			TestObject checkbox = findDynamically(sCheckBox, sProperty, sClass, parent);
			checkbox.waitForExistence(); //will throw ObjectNotFoundException to caller if not found within default timeout
		
			bReturn = isChecked(checkbox);

			//unregister the object
			checkbox.unregister();
		
			return bReturn;
	}
	


}


