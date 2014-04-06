package rft.widgets;

import rft.widgets.ancestors.StatelessWidget;

import com.rational.test.ft.object.interfaces.TestObject;

public class WImage extends StatelessWidget {
	
		
	/**Global string for XDE Tester HTML Image property (".alt")*/
	public static String gsHtmlDefaultProperty = ObjectFactory.gsHtmlImageProp;
	/**Global string for XDE Tester HTML Image class ("Html.IMG")*/
	public static String gsHtmlDefaultClass = ObjectFactory.gsHtmlImageClass;
	
	
//*******************************Constructors***********************************************************	
	/**		
	* Constructor to find and store a dynamically located image object <p>
	* Call with <br>
  	*		<dd><code>Image myImage = new Image(sImageName, sProperty, sClass, BrowserPage());</code><br>
	* where sClass and sProperty indicate the class of the image and the property of that class that sImageText represents 
	* @param sImage	text of image to find
	* @param sValue	property value to search for (e.g. ".alt")
	* @param sClass	TestObject class type identifier (e.g. "Html.IMG") 
	* @param parent	Parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	*/
	public WImage(String sImage, String sProperty, String sClass, TestObject toParent) {
		//find the object
		super(findDynamically(sImage, sProperty, sClass, toParent));
	}
	
	/**		
	* Constructor to store an explicitly specified image object <p>
	* @param image 	the StatelessGuiSubitemTestObject from which to construct the widget.
	*/
	public WImage(TestObject image) {
		super(image);
	}

//************************For Logging********************	
	/**
	 * Returns the name of the type of the widget<br>
	 * e.g. "button" or "listbox" or "textfield"
	 * @return the widget type
	 */		
	public String getWidgetType() {
		return "image";
	}
	
//*********************Static dynamic methods***********************
	
	/**
	* Returns a "Html.IMG" object found dynamically for the specified .alt property text
	* @param altText	text of the alt property of the image
	* @param parent	Parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	* @return a WImage object that matches criteria
	*/
	public static WImage findHtmlImageFromAltProp(String altText, TestObject parent)
	{
		return new WImage(altText, ".alt", gsHtmlDefaultClass, parent);
	}

}

