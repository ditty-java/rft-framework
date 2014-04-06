package rft.widgets;

import rft.widgets.ancestors.Widget;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.util.regex.Regex;

public class WLink extends Widget {
	
	/**Global string for XDE Tester HTML Link property (".text")*/
	public static String gsHtmlDefaultProperty = ObjectFactory.gsHtmlLinkProp;
	/**Global string for XDE Tester HTML Link class ("Html.A")*/
	public static String gsHtmlDefaultClass = ObjectFactory.gsHtmlLinkClass;
	
	
//***************************Constructors***************************************************************************
	/**		
	* Constructor to find and store a dynamically located link object <p>
	* 
	* Call with <br>
  	*		<dd><code><dir>Link myLink = new Link(sLinkText, sProperty, sClass, BrowserPage());</code><br>
	* where sClass and sProperty indicate the class of the link and the property of that class that sLinkText represents 
	* @param sLinkText		text of link to find
	* @param sProperty		property value to search for (e.g. ".text")
	* @param sClass		class type identifier (e.g. "Html.A") 
	* @param parent		Parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	*/
	public WLink(String sLinkText, String sProperty, String sClass, TestObject parent) {
		super(findDynamically(sLinkText, sProperty, sClass, parent));
	}
	
	/**		
	* Constructor to find and store a dynamically located link object <p>
	* 
	* Call with <br>
  	*		<dd><code>Link myLink = new Link(new Regex(sLinkText), sProperty, sClass, BrowserPage());</code><br>
	* where sClass and sProperty indicate the class of the link and the property of that class that sLinkText represents 
	* @param reWidgetText	regular expression that identifies the text of link you are searching for
	* @param sProperty		property value to search for (e.g. ".text")
	* @param sClass		class type identifier (e.g. "Html.A") 
	* @param parent		Parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	*/
	public WLink(Regex reLinkText, String sProperty, String sClass, TestObject parent) {
		super(findDynamically(reLinkText, sProperty, sClass, parent));
	}
	
	/**		
	* Constructor to store an explicitly specified link object <p>
	* @param link		the GuiTestObject from which to construct the widget.
	* 
	*/
	public WLink(TestObject link) {
		super(link);
	}

//************************For Logging********************	
	/**
	 * Returns the name of the type of the widget<br>
	 * e.g. "button" or "listbox" or "textfield"
	 * @return the widget type
	 */		
	public String getWidgetType() {
		return "link";
	}
		
//*****************************Static Methods*******************************
	/**
	* Returns a "Html.A" object found dynamically with the specified link text
	* @param linkText		the text property of the link 
	* @param parent		Parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	* @return a WLink object that matches criteria
	*/
	public static WLink findHtmlLinkFromTextProp(String linkText, TestObject parent)
	{
		return new WLink(linkText, ".text", gsHtmlDefaultClass, parent);
	}

	
	/**
	* Returns a "Html.A" object found dynamically with the specified link caption
	* @param linkText		regular expression identifying the link's text property to search for
	* @param parent		Parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	* @return a WLink object that matches criteria
	*/

	
	public static WLink findHtmlLinkFromTextProp(Regex linkText, TestObject parent)
	{
		return new WLink(linkText, ".text", gsHtmlDefaultClass, parent);
	}

	/**
	* Returns a "Html.A" object found dynamically with the specified link caption
	* @param linkCaption	the caption property of the link 
	* @param parent		Parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	* @return a WLink object that matches criteria
	*/
	public static WLink findHtmlLinkFromCaptionProp(String linkCaption, TestObject parent)
	{
		return new WLink(linkCaption, "LinkCaption", gsHtmlDefaultClass, parent);
	}

	
	/**
	* Returns a "Html.A" object found dynamically with the specified link caption
	* @param linkCaption 		regular expression identifying the link's caption property value to search for 
	* @param parent			Parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	* @return a WLink object that matches criteria
	*/
	public static WLink findHtmlLinkFromCaptionProp(Regex linkCaption, TestObject parent)
	{
		return new WLink(linkCaption, "LinkCaption", gsHtmlDefaultClass, parent);
	}
	
	/**
	* Returns a "Html.A" object found dynamically with the specified link href
	* @param linkHref		rhw  
	* @param TestObject parent - Parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	* @return WLink - a WLink object that matches criteria
	*/
	public static WLink findHtmlLinkFromHrefProp(String linkHref, TestObject parent)
	{
		return new WLink(linkHref, "href", gsHtmlDefaultClass, parent);
	}

	
	/**
	* Returns a "Html.A" object found dynamically with the specified link href
	* @param linkHref		the href of the link
	* @param parent		Parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	* @return a WLink object that matches criteria
	*/
	public static WLink findHtmlLinkFromHrefProp(Regex linkHref, TestObject parent)
	{
		return new WLink(linkHref, "href", gsHtmlDefaultClass, parent);
	}

}