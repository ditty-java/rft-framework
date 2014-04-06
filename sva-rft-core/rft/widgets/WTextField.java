package rft.widgets;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import rft.widgets.ancestors.Widget;

import com.rational.test.ft.ObjectNotFoundException;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.script.RationalTestScript;

public class WTextField extends Widget {
	
	/**Constant to indicate clear text with <code>RationalTestScript.getScreen().inputKeys("^a{ExtDelete}")</code>*/
	public static final int CLEARTEXT_CTRL_a = 0;
	/**Constant to indicate clear text with <code>RationalTestScript.getScreen().inputKeys("^{ExtHome}^+{ExtEnd}{ExtDelete}")</code>*/
	public static final int CLEARTEXT_CTRL_HOME = 1;
	/**Constant to indicate clear text with <code>RationalTestScript.getScreen().inputKeys("{ExtHome}+{ExtEnd}{ExtDelete}")</code>. NOTE: this will not clear text from text areas, so it is recommended to not use this option unless absolutely necessary.*/
	public static final int CLEARTEXT_HOME = 2;
	/**Constant to indicate clear text with <code>RationalTestScript.getScreen().inputKeys("^e{ExtDelete}")</code>. NOTE: some intl browsers use ctrl-e instead of ctrl-a.*/
	public static final int CLEARTEXT_CTRL_e = 3;

	/**This variable controls which method is used to clear text. It defaults to CTRL-a, but you can change the default by calling setClearTextMethod at the beginning of your script. Put this call in a SuperHelper or other standard template if you always need to use a different method to clear text for your application.*/
	public static int CLEARTEXT_METHOD = 0;
	// Changed by Paul Chiang for the FMS project, so it caters for cleartext of dialog boxes
	//public static int CLEARTEXT_METHOD = CLEARTEXT_CTRL_HOME;
	
	//html
	/**Global string for XDE Tester HTML text field property (".name")*/
	public static String gsHtmlDefaultTFProperty = ObjectFactory.gsHtmlTFProp;
	/**Global string for XDE Tester HTML text field class ("Html.INPUT.text")*/	
	public static String gsHtmlDefaultTFClass = ObjectFactory.gsHtmlTFClass;
	/**Global string for XDE Tester HTML text area property (".name")*/	
	public static String gsHtmlDefaultTAProperty = ObjectFactory.gsHtmlTAProp;
	/**Global string for XDE Tester HTML text area class ("Html.TEXTAREA")*/	
	public static String gsHtmlDefaultTAClass = ObjectFactory.gsHtmlTAClass;
	/**Global string for XDE Tester HTML password text field property (".name")*/	
	public static String gsHtmlDefaultPWProperty = ObjectFactory.gsHtmlPWProp;
	/**Global string for XDE Tester HTML password text field class ("Html.INPUT.password")*/	
	public static String gsHtmlDefaultPWClass = ObjectFactory.gsHtmlPWClass;

	//java
	/**Global string for XDE Tester Swing text field property ("name")*/
	public static String gsSwingDefaultTFProperty = ObjectFactory.gsSwingTFProp;
	/**Global string for XDE Tester Swing text field class ("javax.swing.JTextField")*/	
	public static String gsSwingDefaultTFClass = ObjectFactory.gsSwingTFClass;
	/**Global string for XDE Tester Swing text area property ("name")*/	
	public static String gsSwingDefaultTAProperty = ObjectFactory.gsSwingTAProp;
	/**Global string for XDE Tester Swing text area class ("javax.swing.JTextArea")*/	
	public static String gsSwingDefaultTAClass = ObjectFactory.gsSwingTAClass;
	/**Global string for XDE Tester Swing password text field property ("name")*/	
	public static String gsSwingDefaultPWProperty = ObjectFactory.gsSwingPWProp;
	/**Global string for XDE Tester Swing password text field class ("javax.swing.JPasswordField")*/	
	public static String gsSwingDefaultPWClass = ObjectFactory.gsSwingPWClass;

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
	public WTextField(String sTextField, String sProperty, String sClass, TestObject parent) {
		super(findDynamically(sTextField, sProperty, sClass, parent));
	}
	
	/**		
	* Constructor to store an explicitly specified text field object <p>
	* @param textfield 	the GuiTestObject from which to construct the widget.
	*/
	public WTextField(TestObject textfield) {
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
		silentClick();
	}
	
	public static void setClearTextMethod(int clearTextMethod)
	{
		CLEARTEXT_METHOD = clearTextMethod;
	}
	
	public static int getClearTextMethod()
	{
		return CLEARTEXT_METHOD;
	}
	
	/** 
	* Sets the text of the text field stored in this particular TextField object <p>
	* This method uses Rational's inputChars method so charecters with "special meaning" 
	* are literally entered into the text field and not interpretted.<br>
	* The characters with special meanings are: +^%~(){}<br>
	* See Rational's ITopWindow#inputChars.<p>
	* 
	* This method will clear the text of the textfield using the default method in WTextField; use WTextField.setClearTextMethod to change it. (See WTextField.)<p>
	* 
	* @param s		text to set the textfield to
	*/
	public void setText(String s) {
		//get properties need for logging before you click, because otherwise click might take you to another page causing an ObjectNotFoundException
//		String sWidgetType = getWidgetType();
//		String sWidgetName = getName();
		
		this.silentClick(); //activate text field
		this.clearText();
		this.inputChars(s);

		//TODO
		//PackageLoggingController.logPackageInfo(PackageLoggingController.PACKAGELOGLEVEL_ALL_WIDGET_SETTERS, "Set text \"" + s + "\" in " + sWidgetType + " \"" + sWidgetName + "\"");
	}

	
	/**
	* Sets the text of an explicitly specified textfield with inputChars instead of inputKeys  <p>
	* This method uses Rational's inputKeys method so charecters with "special meaning" 
	* are not literally entered, but rather are interpreted.<br>
	* The characters with special meanings are: +^%~(){}<br>
	* See Rational's ITopWindow#inputKeys.<p>
	* 
	* This method will clear the text of the textfield using the default method in WTextField; use WTextField.setClearTextMethod to change it. (See WTextField.)<p>
	* 
	* @param s		text to set the textfield to
	*/
	public void setTextInputKeys(String s) 
	{		
		//get properties need for logging before you click, because otherwise click might take you to another page causing an ObjectNotFoundException
//		String sWidgetType = getWidgetType();
//		String sWidgetName = getName();
		
		this.silentClick(); //activate text field
		this.clearText();
		this.inputKeys(s);

		//TODO
		//PackageLoggingController.logPackageInfo(PackageLoggingController.PACKAGELOGLEVEL_ALL_WIDGET_SETTERS, "Set chars \"" + s + "\" in " + sWidgetType + " \"" + sWidgetName + "\"");
	}
	
	/**
	* Deletes the contents of this particular TextField object<p>
	* 
	* This method will clear the text of the textfield using the default method in WTextField; use WTextField.setClearTextMethod to change it. (See WTextField.)<p>
	* 
	*/
	public void clearText() 
	{
		clearText(CLEARTEXT_METHOD);
	}

	/**
	* Types the keys indicated into this particular TextField object<p>
	* This method uses Rational's inputKeys method so charecters with "special meaning" 
	* are not literally entered, but rather are interpreted.<br>
	* The characters with special meanings are: +^%~(){}<br>
	* See Rational's ITopWindow#inputKeys.<p>
	* 
	* @param s		string being entered
	*/
	public void typeKeys(String s) 
	{
		//get properties need for logging before you click, because otherwise click might take you to another page causing an ObjectNotFoundException
//		String sWidgetType = getWidgetType();
//		String sWidgetName = getName();
		
		this.silentClick(); 
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
	public void inputKeys(String s)
	{
		RationalTestScript.getScreen().inputKeys(s);
		
  		//TopLevelTestObject app = (TopLevelTestObject) this.getTopParent();
  		//app.inputKeys(s);
  
		//Clean up
		//app.unregister();
	}
	
	/**
	* Sets the text of an explicitly specified textfield with inputChars instead of inputKeys  <p>
	* This method uses Rational's inputChars method so charecters with "special meaning" 
	* are literally entered into the text field and not interpretted.<br>
	* The characters with special meanings are: +^%~(){}<br>
	* See Rational's ITopWindow#inputChars.<p>
	* 
	* This method will clear the text of the textfield using the default method in WTextField; use WTextField.setClearTextMethod to change it. (See WTextField.)<p>
	* 
	* @param s		text to set the textfield to
	* */
	public void setTextInputChars(String s) 
	{		
		//get properties need for logging before you click, because otherwise click might take you to another page causing an ObjectNotFoundException
//		String sWidgetType = getWidgetType();
//		String sWidgetName = getName();
		
		this.silentClick(); //activate text field
		this.clearText();
		this.inputChars(s);

		//TODO
		//PackageLoggingController.logPackageInfo(PackageLoggingController.PACKAGELOGLEVEL_ALL_WIDGET_SETTERS, "Set chars \"" + s + "\" in " + sWidgetType + " \"" + sWidgetName + "\"");
	}
	
	/**
	* Types the chars indicated into the test object<p>
	* This method uses Rational's inputChars method so charecters with "special meaning" 
	* are literally entered into the text field and not interpretted.<br>
	* The characters with special meanings are: +^%~(){}<br>
	* See Rational's ITopWindow#inputChars.<p>
	* 
	* @param s		string to type
	* */
	public void typeChars(String s) 
	{
		//get properties need for logging before you click, because otherwise click might take you to another page causing an ObjectNotFoundException
//		String sWidgetType = getWidgetType();
//		String sWidgetName = getName();
		
		this.silentClick();
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
	* Sets the text of the text field stored in this particular TextField object <p>
	* This method uses Rational's inputChars method so charecters with "special meaning" 
	* are literally entered into the text field and not interpretted.<br>
	* The characters with special meanings are: +^%~(){}<br>
	* See Rational's ITopWindow#inputChars.<p>
	* 
	* @param s		text to set the textfield to
	* @param clearTextMethod	use the "CLEARTEXT" constants in this class to indicate the way in which you want the text cleared
	*/
	public void setText(String s, int clearTextMethod) {
		//get properties need for logging before you click, because otherwise click might take you to another page causing an ObjectNotFoundException
//		String sWidgetType = getWidgetType();
//		String sWidgetName = getName();
		
		this.silentClick(); //activate text field
		clearText(clearTextMethod);
		this.inputChars(s);

		//TODO
		//PackageLoggingController.logPackageInfo(PackageLoggingController.PACKAGELOGLEVEL_ALL_WIDGET_SETTERS, "Set text \"" + s + "\" in " + sWidgetType + " \"" + sWidgetName + "\"");
	}

	
	/**
	* Sets the text of an explicitly specified textfield with inputChars instead of inputKeys  <p>
	* This method uses Rational's inputKeys method so charecters with "special meaning" 
	* are not literally entered, but rather are interpreted.<br>
	* The characters with special meanings are: +^%~(){}<br>
	* See Rational's ITopWindow#inputKeys.<p>
	* 
	* @param s		text to set the textfield to
	* @param clearTextMethod	use the "CLEARTEXT" constants in this class to indicate the way in which you want the text cleared
	*/
	public void setTextInputKeys(String s, int clearTextMethod) 
	{		
		//get properties need for logging before you click, because otherwise click might take you to another page causing an ObjectNotFoundException
//		String sWidgetType = getWidgetType();
//		String sWidgetName = getName();
		
		this.silentClick(); //activate text field
		clearText(clearTextMethod);
		this.inputKeys(s);

		//TODO
		//PackageLoggingController.logPackageInfo(PackageLoggingController.PACKAGELOGLEVEL_ALL_WIDGET_SETTERS, "Set chars \"" + s + "\" in " + sWidgetType + " \"" + sWidgetName + "\"");
	}
	
	/**
	* Sets the text of an explicitly specified textfield with inputChars instead of inputKeys  <p>
	* This method uses Rational's inputChars method so charecters with "special meaning" 
	* are literally entered into the text field and not interpretted.<br>
	* The characters with special meanings are: +^%~(){}<br>
	* See Rational's ITopWindow#inputChars.<p>
	* 
	* @param s		text to set the textfield to
	* @param clearTextMethod	use the "CLEARTEXT" constants in this class to indicate the way in which you want the text cleared
	* */
	public void setTextInputChars(String s, int clearTextMethod) 
	{		
		//get properties need for logging before you click, because otherwise click might take you to another page causing an ObjectNotFoundException
//		String sWidgetType = getWidgetType();
//		String sWidgetName = getName();
		
		this.silentClick(); //activate text field
		clearText(clearTextMethod);
		this.inputChars(s);
		
		//TODO
		//PackageLoggingController.logPackageInfo(PackageLoggingController.PACKAGELOGLEVEL_ALL_WIDGET_SETTERS, "Set chars \"" + s + "\" in " + sWidgetType + " \"" + sWidgetName + "\"");
	}
	
	/**
	* Deletes the contents of this particular TextField object<p>
	* 
	* @param clearTextMethod	use the "CLEARTEXT" constants in this class to indicate the way in which you want the text cleared
	* */
	public static void clearText(int clearTextMethod) 
	{
		switch (clearTextMethod)
		{
			case 0:
				RationalTestScript.getScreen().inputKeys("^a{ExtDelete}");
				break;
			case 1:
				RationalTestScript.getScreen().inputKeys("^{ExtHome}^+{ExtEnd}{ExtDelete}");
				break;
			case 2:
				RationalTestScript.getScreen().inputKeys("{ExtHome}+{ExtEnd}{ExtDelete}");
				break;
			case 3:
				RationalTestScript.getScreen().inputKeys("^e{ExtDelete}");
				break;
			default:
				//TODO
				//PackageLoggingController.logPackageError(PackageLoggingController.PACKAGELOGLEVEL_ERRORS_ONLY, "Error in clearText(): Bad Argument for clearTextMethod. Use the 'CLEARTEXT' constants.");
		}	
	}
	
	
	
	
	
	/**		
	* Gets the text of the text field stored in this particular TextField object <p>
	* @return 	String containing the text in the text field
	*/
	public String getText() {
		return ObjectFactory.isHTML(this) ? this.getProperty(".value").toString() : this.getProperty("text").toString();
	}
	

	public String getTextValue() {
		return this.getProperty(".text").toString();
	}
	
	
	/**
	 * Sets clipboard to specified string and pastes text from clipborad to this particular textfield.<p>
	 * This is a workaround for strings that setText() has problems with
	 * @param s	string to send to clipboard
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

//*************************************Static Methods*****************************************************************
	/**		
	* Sets the text of an explicitly specified textfield <p>
	* @param s		text to set the textfield to
	* @param to	Textfield object in which to set the text
	*/
	public static void setText(String s, TestObject to) {
		new WTextField(to).setText(s);
	}
	
	
	/**
	* Types the keys indicated into the test object<p>
	* @param s		string to type
	* @param to 	test object
	* */
	public static void typeKeys(String s, TestObject to) 
	{
		new WTextField(to).typeKeys(s);		
	}
	
	/**
	* Sets the text of an explicitly specified textfield with inputChars instead of inputKeys  <p>
	* @param s		text to set the textfield to
	* @param to	Textfield object in which to set the text
	* */
	public void setTextInputChars(String s, TestObject to) 
	{
		new WTextField(to).setTextInputChars(s);	
	}
	
	/**
	* Types the chars indicated into the test object<p>
	* @param s		string to type
	* @param to	test object
	* */
	public static void typeChars(String s, TestObject to) 
	{
		new WTextField(to).typeChars(s);			
	}
	

	/**		
	* Gets the text of an explicitly specified text field <p>
	* @param to	TextField object from which to get the text
	* @return String containing the text in the textField
	*/
	public static String getText(TestObject to) {
		return new WTextField(to).getText();
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
	public static void setText(String s, String sTextField, String sProperty, String sClass, TestObject parent)
		throws ObjectNotFoundException {
			TestObject to = findDynamically(sTextField, sProperty, sClass, parent);
			
			//waitForExistence will throw ObjectNotFoundException to caller if not found within default timeout
			to.waitForExistence();
		
			setText(s, to);
			to.unregister();
	}
	
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
	public static String getText(String sTextField, String sProperty, String sClass, TestObject parent)
		throws ObjectNotFoundException {
			TestObject to = findDynamically(sTextField, sProperty, sClass, parent);
			
			//waitForExistence() will throw ObjectNotFoundException to caller if not found within default timeout
			to.waitForExistence(); 


			String sReturn = getText(to);
			to.unregister();
			return sReturn;
	}
}


