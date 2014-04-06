package rft.widgets.termApp;

import java.awt.Point;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import rft.tookit.Replace;
import rft.widgets.WFrame;

import com.rational.test.ft.RationalTestException;
import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.script.RationalTestScript;

public class TATextField extends GuiTestObject {
	
//**************************************Constructors****************************************************************
	/**		
	* Constructor to find and store a dynamically located text field object <p>
	* 
	* Call with <br>
  	*		<dd><code>TextField myTextField = new TextField(sTextFieldName, sProperty, sClass, BrowserPage());</code><br>
	* where sClass and sProperty indicate the class of the text field and the property of that class that sTextFieldText represents 
	* @param sTextField name of text field to find.
	* @param sProperty property value to search for (e.g. ".name")
	* @param sClass TestObject class type identifier (e.g. "Html.INPUT.text") 
	* @param parent Parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	*/
//	public TATextField(String sTextField, String sProperty, String sClass, TestObject parent) {
//		super(findDynamically(sTextField, sProperty, sClass, parent));
//	}
	
	/**		
	* Constructor to store an explicitly specified text field object <p>
	* @param textfield 	the GuiTestObject from which to construct the widget.
	*/
	public TATextField(TestObject textfield) {
		super(textfield);
	}

//**************************************Instance Methods****************************************************************

	/**
	 * Clicks on the widget<p>
	 * Override of Widget#click to do a "silent click"<br>
	 * (For inheritance: since Widget#click does logging and don't want logging on click when setting text.
	 */	
	public void click()
	{
	    // Click on the first char of the text field
	    super.click(new Point(1,1));

	}
	
	/** 
	* Sets the text of the text field stored in this particular TextField object <p>
	* @param s		text to set the textfield to
	*/
	public void setText(String s) {
		//get properties need for logging before you click, because otherwise click might take you to another page causing an ObjectNotFoundException
//		String sWidgetType = getWidgetType();
//		String sWidgetName = getName();
		
//		this.click(); //activate text field, clearText will activate the text field
		this.clearText();
		this.inputKeys(escapeSpecialChars(s));
		
		//TODO
		//PackageLoggingController.logPackageInfo(PackageLoggingController.PACKAGELOGLEVEL_ALL_WIDGET_SETTERS, "Set text \"" + s + "\" in " + sWidgetType + " \"" + sWidgetName + "\"");
	}
	
	/** 
	* Sets the text of the text field stored in this particular TextField object <p>
	* @param s		text to set the textfield to
	* @param input_keys the extra keys to press in the screen
	*/
	public void setText(String s, String input_keys) {
		TestObject parent = null;
		
		//get properties need for logging before you click, because otherwise click might take you to another page causing an ObjectNotFoundException
//		String sWidgetType = getWidgetType();
//		String sWidgetName = getName();
		
//		this.click(); //activate text field, clearText will activate the text field
		this.clearText();
		this.inputKeys(escapeSpecialChars(s));
		
		// enter the extra keys into the top most object containing this object
	    //TODO
		//PackageLoggingController.logPackageInfo(PackageLoggingController.PACKAGELOGLEVEL_ALL_WIDGET_SETTERS, "start");
		TestObject new_parent = this.getParent();
	    //TODO
		//PackageLoggingController.logPackageInfo(PackageLoggingController.PACKAGELOGLEVEL_ALL_WIDGET_SETTERS, "got first parent");
		//int count = 1;
		while (new_parent != null) {
		    parent = new_parent;
		    new_parent = parent.getParent();
		}
		
		WFrame parent_tframe = new WFrame(parent);
		parent_tframe.inputKeys(input_keys);
		
		//TODO
		//PackageLoggingController.logPackageInfo(PackageLoggingController.PACKAGELOGLEVEL_ALL_WIDGET_SETTERS, "Set text \"" + s + "\" in " + sWidgetType + " \"" + sWidgetName + "\"");
	}
	
	/** 
	 * Certain characters are interpreted by XDE's inputKeys() method as control characters.
	 * We need to surround them with curly braces if we want these characters to actually show 
	 * up in a text field.
	 */
	protected static String escapeSpecialChars(String s){
		if (s == null) return null;
		//must replace curly braces first or they keep getting added.
		String[] re_chars = { "{", "}", "~", "(", ")",  "+", "^", "%" };
		for (int i = 0; i< re_chars.length; i++){
			s = Replace.replace(s, re_chars[i], "{" + re_chars[i] + "}");
		}
		
		return s;

	}

	
	/**
	* Deletes the contents of this particular TextField object<p>
	* */
	public void clearText() 
	{
		//Click the lower left corner of the beginning char of the text field
	    super.click(new Point(1,14));
	    
	    // Press the <Delete> key for the length of the text field
	    int length = Integer.parseInt(this.getProperty("length").toString());
	    for (int i = 0; i < length; i++)
	        this.inputKeys("{ExtDelete}");
	}

    /**
	* Types the keys indicated into this particular TextField object<p>
	* @param s		string being entered
	*/
	public void typeKeys(String s) 
	{
		//get properties need for logging before you click, because otherwise click might take you to another page causing an ObjectNotFoundException
//		String sWidgetType = getWidgetType();
//		String sWidgetName = getName();
		
		this.click(); 
		this.inputKeys(s);

		//TODO
		//PackageLoggingController.logPackageInfo(PackageLoggingController.PACKAGELOGLEVEL_ALL_WIDGET_SETTERS, "Entered text \"" + s + "\" in " + sWidgetType + " \"" + sWidgetName + "\"");
	}
	
	/**
	* Helper method to input keys into a text field<p>
	* Separated out in order to enable seperate logging for public setText and typeKeys methods
	* 	 and to avoid logging clear text
	* @param s		string to type
	* */
	protected void inputKeys(String s)
	{
		RationalTestScript.getScreen().inputKeys(s);
		
  		//TopLevelTestObject app = (TopLevelTestObject) this.getTopParent();
  		//app.inputKeys(s);
  
		//Clean up
		//app.unregister();
	}
	
	/**
	* Sets the text of an explicitly specified textfield with inputChars instead of inputKeys  <p>
	* @param s		text to set the textfield to
	* @author Chris Carlson  
	* */
	public void setTextInputChars(String s) 
	{		
		//get properties need for logging before you click, because otherwise click might take you to another page causing an ObjectNotFoundException
//		String sWidgetType = getWidgetType();
//		String sWidgetName = getName();
		
		this.click(); //activate text field
		this.clearText();
		this.inputChars(s);

		//TODO
		//PackageLoggingController.logPackageInfo(PackageLoggingController.PACKAGELOGLEVEL_ALL_WIDGET_SETTERS, "Set chars \"" + s + "\" in " + sWidgetType + " \"" + sWidgetName + "\"");
	}
	
	/**
	* Types the chars indicated into the test object<p>
	* @param s		string to type
	* */
	public void typeChars(String s) 
	{
		//get properties need for logging before you click, because otherwise click might take you to another page causing an ObjectNotFoundException
//		String sWidgetType = getWidgetType();
//		String sWidgetName = getName();
		
		this.click();
		inputChars(s);
		
		//TODO
		//PackageLoggingController.logPackageInfo(PackageLoggingController.PACKAGELOGLEVEL_ALL_WIDGET_SETTERS, "Entered chars \"" + s + "\" in " + sWidgetType + " \"" + sWidgetName + "\"");
	}

	/**
	* Helper method to input chars into a text field<p>
	* separated out in order to enable seperate logging for public setTextChars and typeChars methods
	* @param s		string to type
	* */
	protected void inputChars(String s)
	{
		RationalTestScript.getScreen().inputChars(s);
		
 	 	//TopLevelTestObject app = (TopLevelTestObject) this.getTopParent();
 	 	//app.inputChars(s);
  	  
		////Clean up
		//app.unregister();
	}
	
	/**		
	* Gets the text of the text field stored in this particular TextField object <p>
	* White spaces has been trimmed
	* @return 	String containing the text in the text field
	*/
	public String getText() {
		return this.getProperty(".text").toString().trim();
	}
	
	/**		
	* Gets the original text of the text field stored in this particular TextField object <p>
	* @return 	String containing the text in the text field
	*/
	public String getText_notrim() {
		return this.getProperty(".text").toString();
	}
	
	
	/**
	 * Sets clipboard to specified string and pastes text from clipborad to this particular textfield.<p>
	 * This is a workaround for strings that setText() has problems with
	 * @param s	string to send to clipboard
	 * @author tvenditti
	 */
	public void setTextThruClipboard(String s)
	{
		//get properties need for logging before you click, because otherwise click might take you to another page causing an ObjectNotFoundException
//		String sWidgetType = getWidgetType();
//		String sWidgetName = getName();
		
		this.clearText();
		
		//set clipboard content with specified string	
		this.setClipboardText(s);

		//paste clipboard contents
		this.typeKeys("^v");
		//TODO
		//PackageLoggingController.logPackageInfo(PackageLoggingController.PACKAGELOGLEVEL_ALL_WIDGET_SETTERS, "Set text thru clipboard \"" + s + "\" in " + sWidgetType + " \"" + sWidgetName + "\"");
	}
	
	/**
	 * Sets text of the clipboard
	 * @param s	string to send to clipboard
	 * @author tvenditti 
	 */
	protected void setClipboardText(String s)
	{

		//get clipboard object
		Clipboard clip = java.awt.Toolkit.getDefaultToolkit().getSystemClipboard();

		//set clipboard contents
		StringSelection ss = new java.awt.datatransfer.StringSelection(s);
		clip.setContents(ss, ss);
	}
	
	
	//************************For Logging********************	
	/**
	 * Returns the name of the type of the widget<br>
	 * e.g. "button" or "listbox" or "textfield"
	 * @return the widget type
	 */		
	public String getWidgetType() {
		return "textfield";
	}

	/**
	 * Returns the name of the widget<br>
	 * e.g. "button" or "listbox" or "textfield"
	 * @return the widget type
	 */		
	public String getName() {
		Object name = null;
		try {
			name = this.getPropertyFromMap("#name");
		} catch (RationalTestException e) { // the widget was created dynamically
			name = null;
		}
		
		if (name == null || name.equals(""))
			return null;
		else
			return name.toString();

	}

//*************************************Static Methods*****************************************************************
	/**		
	* Sets the text of an explicitly specified textfield <p>
	* @param s		text to set the textfield to
	* @param to	Textfield object in which to set the text
	*/
	public static void setText(String s, TestObject to) {
		new TATextField(to).setText(s);
	}
	
	
	/**
	* Types the keys indicated into the test object<p>
	* @param s		string to type
	* @param to 	test object
	* */
	public static void typeKeys(String s, TestObject to) 
	{
		new TATextField(to).typeKeys(s);		
	}
	
	/**
	* Sets the text of an explicitly specified textfield with inputChars instead of inputKeys  <p>
	* @param s		text to set the textfield to
	* @param to	Textfield object in which to set the text
	* */
	public void setTextInputChars(String s, TestObject to) 
	{
		new TATextField(to).setTextInputChars(s);	
	}
	
	/**
	* Types the chars indicated into the test object<p>
	* @param s		string to type
	* @param to	test object
	* */
	public static void typeChars(String s, TestObject to) 
	{
		new TATextField(to).typeChars(s);			
	}
	

	/**		
	* Gets the text of an explicitly specified text field <p>
	* @param to	TextField object from which to get the text
	* @return String containing the text in the textField
	*/
	public static String getText(TestObject to) {
		return new TATextField(to).getText();
	}
	
	
//**********************************Dynamic Objects****************************************************		
	/**
	* Sets the text of a dynamically located text field <p>
	* 
	* Call with <br>
  	*		<dd><code>TextField.setText(sTextToSetTo, sTextFieldName, sProperty, sClass, BrowserPage());</code><br>
	* where sClass and sProperty indicate the class of the text field and the property of that class that sTextFieldText represents 
	* @param s				text to set the textfield to
	* @param sTextField	name of text field to find
	* @param sValue		property value to search for (e.g. ".name")
	* @param sClass		TestObject class type identifier (e.g. "Html.INPUT.text") 		
	* @param parent		Parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	*/
/*	public static void setText(String s, String sTextField, String sProperty, String sClass, TestObject parent)
		throws ObjectNotFoundException {
			TestObject to = findDynamically(sTextField, sProperty, sClass, parent);
			
			//waitForExistence will throw ObjectNotFoundException to caller if not found within default timeout
			to.waitForExistence();
		
			setText(s, to);
			to.unregister();
	}
*/	
	/**
	* Gets the text of a dynamically located text field <p>
	* 
	* 
	* Call with <br>
  	*		<dd><code>TextField.getText(sTextFieldName, sProperty, sClass, BrowserPage());</code><br>
	* where sClass and sProperty indicate the class of the text field and the property of that class that sTextFieldText represents 
	* @param sTextField	name of text field to find
	* @param sValue		property value to search for (e.g. ".name")
	* @param sClass		TestObject class type identifier (e.g. "Html.INPUT.text") 		
	* @param parent		Parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	* @return 	String containing the text in the textfield
	*/
/*	public static String getText(String sTextField, String sProperty, String sClass, TestObject parent)
		throws ObjectNotFoundException {
			TestObject to = findDynamically(sTextField, sProperty, sClass, parent);
			
			//waitForExistence() will throw ObjectNotFoundException to caller if not found within default timeout
			to.waitForExistence(); 


			String sReturn = getText(to);
			to.unregister();
			return sReturn;
	}
*/	
}

