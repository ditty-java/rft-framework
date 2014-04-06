package rft.widgets;

import java.util.Vector;

import rft.widgets.ancestors.SubitemWidget;

import com.rational.test.ft.ObjectNotFoundException;
import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.script.MouseModifiers;
import com.rational.test.ft.script.Text;
import com.rational.test.ft.vp.ITestData;
import com.rational.test.ft.vp.ITestDataElement;
import com.rational.test.ft.vp.ITestDataElementList;
import com.rational.test.ft.vp.ITestDataList;
import com.rational.test.ft.vp.ITestDataText;
import com.rational.test.ft.vp.impl.TestDataElement;
import com.rational.test.util.regex.Regex;

public class WListBox extends SubitemWidget {

	/**Global string for XDE Tester HTML listbox property (".name")*/
	public static String gsHtmlDefaultProperty = ObjectFactory.gsHtmlLBProp;
	/**Global string for XDE Tester HTML listbox class ("Html.SELECT")*/
	public static String gsHtmlDefaultClass = ObjectFactory.gsHtmlLBClass;

	/**Global string for XDE Tester Swing listbox property ("name")*/
	public static String gsSwingDefaultProperty = ObjectFactory.gsSwingComboLBProp;
	/**Global string for XDE Tester Swing listbox class ("javax.swing.JComboBox")*/
	public static String gsSwingDefaultClass = ObjectFactory.gsSwingComboLBClass;

	
//*******************************************Constructors**********************************************
	/**		
	* Constructor to find and store a dynamically located list box object <p>
	* 
	* Call with <br>
  	*		<dd><code><dir>WListBox myListBox = new WListBox(sListBoxName, sProperty, sClass, BrowserPage());</code><br>
	* where sClass and sProperty indicate the class of the list box and the property of that class that sListBoxText represents 
	* @param sListBox		name of list box to find
	* @param sProperty		property value to search for (e.g. ".name")
	* @param sClass		class type identifier (e.g. "Html.SELECT") 
	* @param parent		Parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	*/
	public WListBox(String sListBox, String sProperty, String sClass, TestObject parent) {
		super(findDynamically(sListBox, sProperty, sClass, parent).getObjectReference());
	}
	
	/**		
	* Constructor to store an explicitly specified listbox object <p>
	* @param listbox 	the GuiSubitemTestObject from which to construct the widget.
	*/
	public WListBox(TestObject listbox) {
		super(listbox);
	}

//*****************************************Instance Methods**********************************************	

	
	/**
	* Selects an item from the listbox stored in this particular ListBox object<p>
	* @param sItem		text of the item to pick
	*/
	public void select(String sItem) {
		//get properties need for logging before you click, because otherwise click might take you to another page causing an ObjectNotFoundException
//		String sWidgetType = getWidgetType();
//		String sWidgetName = getName();
		
		this.click();
		this.click(new Text(sItem));
		//TODO
		//PackageLoggingController.logPackageInfo(PackageLoggingController.PACKAGELOGLEVEL_ALL_WIDGET_SETTERS, "Selected \"" + sItem + "\" in " + sWidgetType + " \"" + sWidgetName + "\"");							
	}
	
	/**
	* Selects an item from this listbox by the index of the item <p>
	* @param index		index of the item to pick
	*/
	public void select(int index) {		
		String sItem = this.getItemText(index);
		if (sItem == null) {
			//TODO
			//PackageLoggingController.logPackageWarning(PackageLoggingController.PACKAGELOGLEVEL_ERRORS_WARNINGS, "WListBox::select: index is out of range. No selection could be made.");
			return;
		}
		this.select(sItem);							
	}
	
	/**
	* Selects the first matched item from the listbox stored in this particular ListBox object<p>
	* @param r		regular expression of the item to pick
	*/
	public void select(Regex r) {
		//get properties need for logging before you click, because otherwise click might take you to another page causing an ObjectNotFoundException
//		String sWidgetType = getWidgetType();
//		String sWidgetName = getName();
		
//		 Get all elements
		ITestDataList nameList = (ITestDataList)this.getTestData("list");
		ITestDataElementList nameListElements = nameList.getElements();
		ITestDataElement nameListElement;

		int listElemCount = nameList.getElementCount();

	    int i;
	    String sItem = null;

	    for (i = 0; i < listElemCount; i++)
	    {
	        nameListElement = nameListElements.getElement(i);
	        if (r.matches(nameListElement.getElement().toString()))
	        {
	            sItem = nameListElement.getElement().toString();	            
	            this.select(sItem);
	        	break;
	    	}
	    }
		
	    // Logging would have been printed by the select above
		// PackageLoggingController.logPackageInfo(PackageLoggingController.PACKAGELOGLEVEL_ALL_WIDGET_SETTERS, "Selected \"" + sItem + "\" in " + sWidgetType + " \"" + sWidgetName + "\"");							
	}
	
	/**
	* Selects the first matched item from the listbox stored in this particular ListBox object<p>
	* @param r		regular expression of the item to pick
	*/
	public void select_begin_with(String s) {
		//get properties need for logging before you click, because otherwise click might take you to another page causing an ObjectNotFoundException
//		String sWidgetType = getWidgetType();
//		String sWidgetName = getName();
		
//		 Get all elements
		ITestDataList nameList = (ITestDataList)this.getTestData("list");
		ITestDataElementList nameListElements = nameList.getElements();
		ITestDataElement nameListElement;

		int listElemCount = nameList.getElementCount();

	    int i;
	    String sItem = null;

	    for (i = 0; i < listElemCount; i++)
	    {
	        nameListElement = nameListElements.getElement(i);
	        if (nameListElement.getElement().toString().indexOf(s) == 0)	// the item starts with the entered string
	        {
	            sItem = nameListElement.getElement().toString();	            
	            this.select(sItem);
	        	break;
	    	}
	    }
		
	    // Logging would have been printed by the select above
		// PackageLoggingController.logPackageInfo(PackageLoggingController.PACKAGELOGLEVEL_ALL_WIDGET_SETTERS, "Selected \"" + sItem + "\" in " + sWidgetType + " \"" + sWidgetName + "\"");							
	}
	
	/**
	* Selects an item from this multi-select listbox while preserving other selections <p>
	* @param sItem		text of the item to pick		
	*/
	public void multiSelect(String sItem) {
		//get properties need for logging before you click, because otherwise click might take you to another page causing an ObjectNotFoundException
//		String sWidgetType = getWidgetType();
//		String sWidgetName = getName();
		
		if (this.isMultiSelectable()) {
			MouseModifiers mm = new MouseModifiers(MouseModifiers.MOUSE_LEFT);
			mm.setCtrl();
			this.click(mm, new Text(sItem));
			//TODO
			//PackageLoggingController.logPackageInfo(PackageLoggingController.PACKAGELOGLEVEL_ALL_WIDGET_SETTERS, "Multi-Selected \"" + sItem + "\" in " + sWidgetType + " \"" + sWidgetName + "\"");							
				
		} else {
			//TODO
			//PackageLoggingController.logPackageWarning(PackageLoggingController.PACKAGELOGLEVEL_ERRORS_WARNINGS, "WListBox::multiSelect(): ListBox does not support multi-select. Peforming a select instead.");
			this.select(sItem);
		}							
	}
	
	/**
	* Selects an item from this multi-select listbox while preserving other selections <p>
	* @param index		index of the item to pick
	*/
	public void multiSelect(int index) {
		String sItem = getItemText(index);
		if (sItem == null) {
			//TODO
			//PackageLoggingController.logPackageWarning(PackageLoggingController.PACKAGELOGLEVEL_ERRORS_WARNINGS, "WListBox::multiSelect: index is out of range. No selection could be made.");
			return;
		}
		this.multiSelect(sItem);							
	}
	
	/**
	* Gets the text of the first item selected in the listbox stored in this particular instance of the class <p>
	* Note: if the listbox is multi-select, use getMultiSelText
	* @return text of selected item
	*/
	public String getSelText() {	
		String [] selected = getMultiSelText();
		if (selected == null) 
			return null;
		return selected[0];
	}
	
	/**
	* Gets the index of the first item selected in the listbox stored in this particular instance of the class <p>
	* Note: if the listbox is multi-select, use getMultiSelIndex
	* @return the index of the selected item
	*/
	public int getSelIndex() {	
		int [] selected = getMultiSelIndex();
		if (selected == null) 
			return -1;
		return selected[0];
	}
	
	/**
	* Gets text of all the selected items in the listbox stored in this particular instance of the class  <p>
	* @return 	String array containing text of all the selected items 
	*/
	public String[] getMultiSelText() {
		ITestData data = this.getTestData("selected");
		
		//java combo boxes return data that is not a list since only one element can be chosen at a time
		//so to avoid a caste exception test if the data is ITestDataText and if so create an array with only one element
		if (data instanceof ITestDataText) {
			ITestDataText dataText = (ITestDataText)data;
			String list[] = {dataText.getText()};
			return list;
		}
		
		//else assume it is a list and proceed
		ITestDataList dataList = (ITestDataList)data;
		if (dataList.getElementCount() == 0) 
			return null;
		
		ITestDataElementList elementList = (ITestDataElementList)dataList.getElements();
			
		return convertElementListToStringArray(elementList);
	}
	
	/**
	* Gets the index of all the selected items in the listbox stored in this particular instance of the class  <p>
	* @return 	int array containing indeces of all the selected items 
	*/
	public int[] getMultiSelIndex() {	
		
		ITestData data = this.getTestData("selected");
		
		//java combo boxes return data that is not a list since only one element can be selected at a time
		//so to avoid a caste exception test if the data is ITestDataText and if so create an array with only one element
		if (data instanceof ITestDataText) {
			ITestDataText dataText = (ITestDataText)data;
			int list[] = {this.findItem(dataText.getText())};
			return list;
		}
		
		//else assume it is a list and proceed
		ITestDataList dataList = (ITestDataList)data;

		if (dataList.getElementCount() == 0) 
			return null;
			
		ITestDataElementList elementList = (ITestDataElementList)dataList.getElements();
		Vector<TestDataElement> vSelectedList = elementList.getElements();
			
		int[] iSelectedList = new int[elementList.getLength()];
		for(int i = 0; i<iSelectedList.length;i++) {
			TestDataElement element = (TestDataElement)vSelectedList.get(i);		
			iSelectedList[i] = this.findItem((String)element.getElement());
		}				
			
		return iSelectedList;
	}
	
	/**
	* Returns the number of list items<p>
	* @return number items in the list
	*/
	public int getItemCount() {	
		ITestData data = (ITestData)this.getTestData("list");	
		ITestDataList list = (ITestDataList)data;
		return list.getElementCount();
	}
	
	/**
	* Returns all items in this listbox <p>
	* @return 	String array containing the text of each of the elements in the listbox
	*/
	public String[] getContents() {		
		ITestDataList dataList = (ITestDataList)this.getTestData("list");
		if (dataList.getElementCount() == 0) 
			return null;
		ITestDataElementList elementList = (ITestDataElementList)dataList.getElements();
				
		return convertElementListToStringArray(elementList);
	}
	
	/**
	* Finds an item in this listbox <p>
	* @param sItem		the item to find in the list
	* @return  index of item; -1 if item not found
	*/
	public int findItem(String sItem) {
		//it would be easiest to use the Vector like so:
		//return this.getContentsAsVector().indexOf(new TestDataElement(sItem));
		//but XDE doesn't have a constructor to create an "element" type and no find item methods on an ElementList
		//so there's no way to search for a particular string in the vector
		//so instead, I have to use the arrays and search manually
		
		String contents[] =  this.getContents();
		for(int i = 0; i<contents.length; i++) {
			if (contents[i].equals(sItem)) {
				return i;
			}
		}
		//if get here, item not found, so return -1
		return -1;
	}
	
	/**
	* Gets the text of an item in this listbox <p>
	* @param index		the index of the item
	* @return the text of the item; null if not found
	*/
	public String getItemText(int index) {
		String contents[] = this.getContents();
		if (index < 0 || index >= contents.length) {
			//TODO
			//PackageLoggingController.logPackageWarning(PackageLoggingController.PACKAGELOGLEVEL_ERRORS_WARNINGS, "ListBox::getItemText: index is out of range. Returning null string");
			return null;
		}
			
		return contents[index];
	}
	
	
	/**
	* Helper function for multi-select to determine whether ListBox is multi-selectable <p>
	* (really to determine if you will get an error by trying to ctrl-click)
	*/
	protected boolean isMultiSelectable() {
		if (ObjectFactory.isHTML(this)) {
        	if (Boolean.valueOf(this.getProperty(".multiple").toString()) != Boolean.TRUE) {
				return false;
			}
		} else {
			//check if java list is a combo box, which'll cause an error if try to multi-select
			//No property in java proxy to indicate multi-select!! 
			ITestData data = this.getTestData("selected");
			if (data instanceof ITestDataText) {
				return false;
			}
		}
		return true;
	}
	
	/**
	* Returns all items in a listbox as a Vector<p>
	* @return 	Vector of com.rational.test.ft.vp.impl.TestDataElements representing the items in the listbox
	*/
	protected Vector getContentsAsVector() {
		ITestDataList dataList = (ITestDataList)this.getTestData("list");
		ITestDataElementList elementList = (ITestDataElementList)dataList.getElements();
				
		return elementList.getElements();
	}
	
	//************************For Logging********************	
	/**
	 * Returns the name of the type of the widget<br>
	 * e.g. "button" or "listbox" or "textfield"
	 * @return the widget type
	 */		
	public String getWidgetType() {
		return "listbox";
	}
	
//***********************************Static Methods******************************************************
	/**
	* Selects an item from an explicitly specified listbox <p>
	* @param sItem		text of the item to pick
	* @param to		ListBox object from which to pick the string		
	*/
	public static void select(String sItem, TestObject to) {
		new WListBox(to).select(sItem);
	}

	/**
	* Selects an item from a multi-select listbox while preserving other selections <p>
	* @param sItem		text of the item to pick
	* @param to		ListBox object from which to pick the string		
	*/
	public static void multiSelect(String sItem, TestObject to) {
		new WListBox(to).multiSelect(sItem);
	}
	
	/**
	* Selects an item from an explicitly specified listbox by the index of the item <p>
	* @param index		index of the item to pick
	* @param to		ListBox object from which to pick the item		
	*/
	public static void select(int index, TestObject to) {
		new WListBox(to).select(index);							
	}
	
	/**
	* Selects an item from a multi-select listbox by the index of the item while preserving other selections<p>
	* @param index		index of the item to pick
	* @param to		ListBox object from which to pick the item		
	*/
	public static void multiSelect(int index, TestObject to) {
		new WListBox(to).multiSelect(index);		
	}
	
	
	/**
	* Gets text of the first selected item in an explicitly specified listbox object <p>
	* Note: if the listbox is multi-select and more than one item is selected, use getMultiSelText
	* @param to - ListBox object from which to pick the string
	* @return the text of selected item (returns null if none are selected)
	*/
	public static String getSelText(TestObject to) {
		return new WListBox(to).getSelText();			
	}
	
	/**
	* Gets the text of all the selected items in an explicitly specified listbox object <p>
	* @param to		ListBox object from which to pick the string
	* @return 	String array containing text of all the selected items (returns null if none are selected)
	*/
	public static String[] getMultiSelText(TestObject to) {
		return new WListBox(to).getMultiSelText();
	}
	
	/**
	* Returns the text of all items in a listbox <p>
	* @param to	ListBox object
	* @return 	String array with the text of all the items in the listbox (returns null if there are no items in the list)
	*/
	public static String[] getContents(TestObject to) {
		return new WListBox(to).getContents();
	}
	
	/**
	* Finds an item in a listbox <p>
	* @param sItem		the item to find in the list
	* @param to		ListBox object to search
	* @return the index of item (returns -1 if item not found)
	*/
	public static int findItem(String sItem, TestObject to) {
		return new WListBox(to).findItem(sItem);
	}
	
		
	/**
	* Returns the number of list items in an explicitly specified listbox object <p>
	* @param to		ListBox object
	* @return number of items in list
	*/
	public static int getItemCount(TestObject to) {
		return new WListBox(to).getItemCount();
	}

		
//********************Static dynamic methods*******************
	
	/**		
	* Selects an item from a dynamically located listbox <p>
	*
	* Call with <br>
  	*		<dd><code><dir>ListBox.select(sItem, sListBoxName, sProperty, sClass, BrowserPage());</code><br>
	* where sClass and sProperty indicate the class of the list box and the property of that class that sListBoxText represents 
	* @param sItem			item to select
	* @param sListBox		name of list box to find
	* @param sProperty		property value to search for (e.g. ".name")
	* @param sClass		class type identifier (e.g. "Html.SELECT") 
	* @param parent		Parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	*/
	public static void select(String sItem, String sListBox, String sProperty, String sClass, TestObject parent)
		throws ObjectNotFoundException {

			//find the object
			TestObject listbox = findDynamically(sListBox, sProperty, sClass, parent);
			listbox.waitForExistence(); 	//will throw ObjectNotFoundException to caller if not found within default timeout
		
			//select item
			select(sItem, listbox);
	
			//unregister the object
			listbox.unregister();
	}
	/**
	* Selects an item from a dynamically located listbox by the index of the item <p>
	* Call with <br>
  	*		<dd><code><dir>ListBox.select(iIndex, sListBoxName, sProperty, sClass, BrowserPage());</code><br>
	* where sClass and sProperty indicate the class of the list box and the property of that class that sListBoxText represents 
	* @param index			index of the item to pick
	* @param sListBox		name of list box to find
	* @param sProperty		property value to search for (e.g. ".name")
	* @param sClass		class type identifier (e.g. "Html.SELECT") 
	* @param parent		Parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	*/
	public static void select(int index, String sListBox, String sProperty, String sClass, TestObject parent) 
		throws ObjectNotFoundException {
				//find the object
			TestObject listbox = findDynamically(sListBox, sProperty, sClass, parent);
			listbox.waitForExistence(); 	//will throw ObjectNotFoundException to caller if not found within default timeout
		
			//select item
			select(index, listbox);
	
			//unregister the object
			listbox.unregister();
	}
	
		
	/**
	* Gets the text of the first selected item in a dynamically located listbox <p>
	*
	* Call with <br>
  	*		<dd><code><dir>ListBox.getSelText(sListBoxName, sProperty, sClass, BrowserPage());</code><br>
	* where sClass and sProperty indicate the class of the list box and the property of that class that sListBoxText represents 
	* @param sListBox		name of list box to find
	* @param sProperty		property value to search for (e.g. ".name")
	* @param sClass		class type identifier (e.g. "Html.SELECT") 
	* @param parent		Parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	* @return  the text of selected item
	*/
	public static String getSelText(String sListBox, String sProperty, String sClass, TestObject parent)
		throws ObjectNotFoundException {

			TestObject to = findDynamically(sListBox, sProperty, sClass, parent);
			to.waitForExistence(); 	//will throw ObjectNotFoundException to caller if not found within default timeout

			String sReturn = getSelText(to);	
			to.unregister();
			return sReturn;
	}
		
	/**
	* Returns the number of list items in a dynamically located listbox <p>
	*
	* Call with <br>
  	*		<dd><code><dir>ListBox.getItemCount(sListBoxName, sProperty, sClass, BrowserPage());</code><br>
	* where sClass and sProperty indicate the class of the list box and the property of that class that sListBoxText represents 
	* @param sListBox		name of list box to find
	* @param sProperty		property value to search for (e.g. ".name")
	* @param sClass		class type identifier (e.g. "Html.SELECT") 
	* @param parent		Parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	* @return number of items in the listbox
	*/
	public static int getItemCount(String sListBox, String sProperty, String sClass, TestObject parent) {
		
			return getItemCount(findDynamically(sListBox,  sProperty, sClass, parent));
	}
	
	/**
	* Gets the text of all the selected items in an explicitly specified listbox object <p>
	* Call with <br>
  	*		<dd><code><dir>ListBox.getSelText(sListBoxName, sProperty, sClass, BrowserPage());</code><br>
	* where sClass and sProperty indicate the class of the list box and the property of that class that sListBoxText represents 
	* @param sListBox		name of list box to find
	* @param sProperty		property value to search for (e.g. ".name")
	* @param sClass		class type identifier (e.g. "Html.SELECT") 
	* @param parent		Parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	* @return 	String array containing text of all the selected items 
	*/
	public static String[] getMultiSelText(String sListBox, String sProperty, String sClass, TestObject parent) {
			TestObject to = findDynamically(sListBox, sProperty, sClass, parent);
			to.waitForExistence(); 	//will throw ObjectNotFoundException to caller if not found within default timeout

			String[] sReturn = getMultiSelText(to);	
			to.unregister();
			return sReturn;
	}
	
	/**
	* Returns the text of all items in a dynamically located listbox <p>
	* Call with <br>
  	*		<dd><code><dir>ListBox.getContents(sListBoxName, sProperty, sClass, BrowserPage());</code><br>
	* where sClass and sProperty indicate the class of the list box and the property of that class that sListBoxText represents 
	* @param sListBox		name of list box to find
	* @param sProperty		property value to search for (e.g. ".name")
	* @param sClass		class type identifier (e.g. "Html.SELECT") 
	* @param parent		Parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	* @return 	String array with the text of all the items in the listbox
	*/
	public static String[] getContents(String sListBox, String sProperty, String sClass, TestObject parent) {
					
		return getContents(findDynamically(sListBox,  sProperty, sClass, parent));
	}
	
	/**		
	* Finds a ListBox TestObject dynamically <br>
	* (Note: this method verifies that the TestObject found is a GuiSubitemTestObject<p>
	* 
	* Call with<br>
  	*		<dd><code>ListBox.findDynamically(sListBoxName, sProperty, sClass, BrowserPage());</code><br>
	* where sClass and sProperty indicate the class of the button and the property of that class that sListBoxName represents<br>
	* @param sListBox		name of list box to find
	* @param sProperty		property value to search for (e.g. ".name")
	* @param sClass		class type identifier (e.g. "Html.SELECT") 
	* @param parent		Parent TestObject from which to search (e.g Table_HtmlTable_3() or BrowserPage()). 
	* @return the object attempting to find
	*/
	public static TestObject findDynamically(String sListBoxName, String sProperty, String sClass, TestObject parent) 
		throws ObjectNotFoundException {
			
			TestObject to = SubitemWidget.findDynamically(sListBoxName, sProperty, sClass, parent);
			if (!(to instanceof GuiSubitemTestObject))
				throw new ObjectNotFoundException("Object found is not of the class GuiSubitemTestObject, so the object is not a ListBox. You must use a Widget class other than ListBox to represent this object.");
			return to;				
	}
	
	
//********************Private and Protected methods*********************************************
		
	/**
	* Helper function to convert an ITestDataElementList to a String array <p>
	* @param elementList		the element list to convert
	* @return 	String array containing text of all the elements in the list
	*/	
	protected static String[] convertElementListToStringArray(ITestDataElementList elementList) {
			String[] list = new String[elementList.getLength()];
			for (int i = 0; i < list.length; i++)
				list[i] = elementList.getElement(i).getElement().toString();
			return list;
	}


	
}

