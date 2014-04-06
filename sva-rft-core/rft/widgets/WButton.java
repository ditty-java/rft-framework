package rft.widgets;

import rft.widgets.ancestors.Widget;

import com.rational.test.ft.object.interfaces.TestObject;

public class WButton extends Widget {
	
	//html
	/**Global string for XDE Tester HTML Input Submit Button property (".value")*/
	public static String gsHtmlSubmitButtonProperty = ObjectFactory.gsHtmlButtonProp;
	/**Global string for XDE Tester HTML Input Submit Button class ("Html.INPUT.submit")*/
	public static String gsHtmlSubmitButtonClass = ObjectFactory.gsHtmlButtonClass;
	/**Global string for XDE Tester HTML Input Image Button property (".name")*/
	public static String gsHtmlImageButtonProperty = ObjectFactory.gsHtmlButtonImageProp;
	/**Global string for XDE Tester HTML Input Image Button class ("Html.INPUT.image")*/
	public static String gsHtmlImageButtonClass = ObjectFactory.gsHtmlButtonImageClass;

	//java
	/**Global string for XDE Tester Swing Button property ("name")*/
	public static String gsSwingDefaultProperty = ObjectFactory.gsSwingRBProp;
	/**Global string for XDE Tester Swing Button class ("javax.swing.JRadioButton")*/
	public static String gsSwingDefaultClass = ObjectFactory.gsSwingRBClass;
		
	/**Global string for XDE Tester AWT Button property ("label")*/
	public static String gsAwtDefaultProperty = ObjectFactory.gsAwtRBProp;
	/**Global string for XDE Tester AWT Button class (""java.awt.RadioButton"")*/
	public static String gsAwtDefaultClass = ObjectFactory.gsAwtRBClass;
	
	

//***************************Constructors***************************************************************************
	/**		
	* Constructor to find and store a dynamically located button object <p>
	* 
	* Call with <br>
  	*		<dd><code>Button myButton = new Button(sButtonText, sProperty, sClass, BrowserPage());</code><br>
	* where sClass and sProperty indicate the class of the button and the property of that class that sButtonText represents 
	* @param String sButton - text of button to find
	* @param String sProperty - property value to search for (e.g. ".value")
	* @param String sClass - class type identifier (e.g. "Html.INPUT.submit") 
	* @param TestObject parent - Parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	*/
	public WButton(String sButton, String sProperty, String sClass, TestObject parent) {
		super(findDynamically(sButton, sProperty, sClass, parent));
	}
	
	/**		
	* Constructor to store an explicitly specified button object <p>
	* @param button	the GuiTestObject from which to construct the widget.
	* 
	*/
	public WButton(TestObject button) {
		super(button);
	}

//************************For Logging********************	
	/**
	 * Returns the name of the type of the widget<br>
	 * e.g. "button" or "listbox" or "textfield"
	 */		
	public String getWidgetType() {
		return "button";
	}

//****************************************Static Methods***************************************************	
	/**
	* Returns a WButton widget found dynamically with the specified .value property 
	* @param buttonValue	button's value property to search for 
	* @param parent		Parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	* @return a WButton object that matches criteria
	*/
	public static WButton findHtmlSubmitButtonFromValueProp(String buttonValue, TestObject parent)
	{
		return new WButton(buttonValue, ".value", gsHtmlSubmitButtonClass, parent);
	}
	
	/**
	* Returns an "Html.INPUT.image" button object found dynamically with the specified .name property 
	* @param buttonName		button's name property to search for
	* @param TestObject parent - Parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	* @return a WButton object that matches criteria
	*/
	public static WButton findHtmlImageButtonFromNameProp(String buttonName, TestObject parent)
	{
		return new WButton(buttonName, ".name", gsHtmlImageButtonClass, parent);
	}
}

