package rft.widgets;

import rft.widgets.ancestors.Widget;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.util.regex.Regex;

public class WStaticText extends Widget {
	
	/**Global string for XDE Tester HTML static text property (".text")*/	
	public static String gsHtmlDefaultProperty = ObjectFactory.gsHtmlStaticTextProp;
	/**Global string for XDE Tester HTML static text class ("Html.TextNode")*/
	public static String gsHtmlDefaultClass = ObjectFactory.gsHtmlStaticTextClass;
	
	/**Global string for XDE Tester AWT Static Text class ("java.awt.Label")*/
	public static String gsAwtDefaultClass = ObjectFactory.gsAwtStaticTextClass;
	/**Global string for XDE Tester AWT Static Text property (".label")*/
	public static String gsAwtDefaultProp = ObjectFactory.gsAwtStaticTextProp;
	
	/**Global string for XDE Tester Swing Static Text class ("javax.swing.JLabel")*/
	public static String gsSwingDefaultClass = ObjectFactory.gsSwingStaticTextClass;
	/**Global string for XDE Tester Swing Static Text property (".name")*/
	public static String gsSwingDefaultProp = ObjectFactory.gsSwingStaticTextProp;
	
	
//***************************Constructors*********************************************
	/**		
	* Constructor to find and store a dynamically located static text object <p>
	* 
	* Call with <br>
  	*		<dd><code>StaticText myStaticText = new StaticText(sStaticTextName, sProperty, sClass, BrowserPage());</code><br>
	* where sClass and sProperty indicate the class of the static text and the property of that class that sStaticTextText represents 
	* @param sStaticText	text of static text to find
	* @param sProperty		property value to search for (e.g. ".text")
	* @param sClass		class type identifier (e.g. "Html.TextNode") 
	* @param parent		Parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	*/
	public WStaticText(String sStaticText, String sProperty, String sClass, TestObject parent) {
		//find the object
		super(findDynamically(sStaticText, sProperty, sClass, parent));
	}
	
	/**		
	* Constructor to find and store a dynamically located static text object <p>
	* 
	* Call with <br>
  	*		<dd><code>StaticText myStaticText = new StaticText(sStaticTextName, sProperty, sClass, BrowserPage());</code><br>
	* where sClass and sProperty indicate the class of the static text and the property of that class that sStaticTextText represents 
	* @param reStaticText	regular expression that identifies the text you are searching for
	* @param sStaticText	text of static text to find
	* @param sProperty		property value to search for (e.g. ".text")
	* @param sClass		class type identifier (e.g. "Html.TextNode") 
	* @param parent		Parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	*/
	public WStaticText(Regex reStaticText, String sProperty, String sClass, TestObject parent) {
		//find the object
		super(findDynamically(reStaticText, sProperty, sClass, parent));
	}
	
	/**		
	* Constructor to store an explicitly static text object <p>
	* @param statictext 	the GuiTestObject from which to construct the widget.
	*/
	public WStaticText(TestObject statictext) {
		super(statictext);
	}
	
//****************************Instance Methods******************************************************	
	
	/**		
	* Gets the text of the text field stored in this particular Label object <p>
	* @return the text of the text field
	*/
	public String getText() {
		if (ObjectFactory.isHTML(this))
			return getProperty(gsHtmlDefaultProperty).toString();
		else
			return getProperty("text").toString();
	}
	
//************************For Logging********************	
	/**
	 * Returns the name of the type of the widget<br>
	 * e.g. "button" or "listbox" or "textfield"
	 * @return the widget type
	 */		
	public String getWidgetType() {
		return "statictext";
	}
	
//*********************Static Methods for dynamically-found objects***********************
	
	/**
	* Returns a WStaticText widget found dynamically with the specified text property
	* @param staticText	text property to search for 
	* @param parent		Parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	* @return  a WStaticText object that matches criteria
	*/
	public static WStaticText findHtmlStaticTextFromTextProp(String staticText, TestObject parent)
	{
		return new WStaticText(staticText, gsHtmlDefaultProperty, gsHtmlDefaultClass, parent);
	}
	
	/**
	* Returns a WStaticText widget object found dynamically with the specified text property
	* @param staticText	regular expression identifying text property to search for 
	* @param TestObject parent - Parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	* @return WStaticText - a WStaticText object that matches criteria
	*/
	public static WStaticText findHtmlStaticTextFromTextProp(Regex staticText, TestObject parent)
	{
		return new WStaticText(staticText, gsHtmlDefaultProperty, gsHtmlDefaultClass, parent);
	}

	/**
	* Returns a WStaticText widget found dynamically with the specified name property
	* @param staticText 	the name property to search for
	* @param parent		Parent TestObject from which to search (e.g MainPanel() or MainFrame()). 
	* @return a WStaticText object that matches criteria
	*/
	public static WStaticText findSwingStaticTextFromNameProp(String staticText, TestObject parent)
	{
		return new WStaticText(staticText, "name", gsHtmlDefaultClass, parent);
	}
	
	/**
	* Returns a WStaticText widget found dynamically with the specified text property
	* @param staticText	regular expression identifying the name property value to search for 
	* @param parent		Parent TestObject from which to search (e.g MainPanel() or MainFrame()). 
	* @return a WStaticText object that matches criteria
	*/
	public static WStaticText findSwingStaticTextFromNameProp(Regex staticText, TestObject parent)
	{
		return new WStaticText(staticText, "name", gsSwingDefaultClass, parent);
	}
}


