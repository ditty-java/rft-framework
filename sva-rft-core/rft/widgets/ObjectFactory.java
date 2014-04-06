package rft.widgets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import com.rational.test.ft.ObjectNotFoundException;
import com.rational.test.ft.PropertyNotFoundException;
import com.rational.test.ft.RationalTestException;
import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.map.MappedTestObject;
import com.rational.test.ft.object.map.MappedTestObjectProperty;
import com.rational.test.ft.object.map.SpyMappedTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.value.MethodInfo;
import com.rational.test.util.regex.Regex;


public class ObjectFactory 
{
	
	//Global XDE Tester TestObject class & property types
//**************************HTML***********************************
	/**Global string for XDE Tester HTML Submit Button class*/
	public static final String gsHtmlButtonClass = "Html.INPUT.submit"; //buttons
	/**Global string for XDE Tester HTML Submit Button value property*/
	public static final String gsHtmlButtonProp = ".value";
	/**Global string for XDE Tester HTML Button Image class*/
	public static final String gsHtmlButtonImageClass = "Html.INPUT.image"; //buttons
	/**Global string for XDE Tester HTML Button Image property*/
	public static final String gsHtmlButtonImageProp = ".name";
	
	/**Global string for XDE Tester HTML CheckBox class*/
	public static final String gsHtmlCBClass = "Html.INPUT.checkbox"; //checkbox
	/**Global string for XDE Tester HTML CheckBox .nmae property*/
	public static final String gsHtmlCBProp = ".name";

	/**Global string for XDE Tester HTML Image class*/
	public static final String gsHtmlImageClass = "Html.IMG"; //Html Image
	/**Global string for XDE Tester HTML Image .alt property*/
	public static final String gsHtmlImageProp = ".alt";

	/**Global string for XDE Tester HTML Link class*/
	public static final String gsHtmlLinkClass = "Html.A"; //links
	/**Global string for XDE Tester HTML Link text property*/
	public static final String gsHtmlLinkProp= ".text";

	/**Global string for XDE Tester HTML Drop-Down List class*/
	public static final String gsHtmlLBClass = "Html.SELECT"; //drop-down list box
	/**Global string for XDE Tester HTML Drop-Down List name property*/
	public static final String gsHtmlLBProp = ".name";
	/**Global string for XDE Tester HTML Drop-Down List value property*/
	public static final String gsHtmlLBValueProp = ".value";
	/**Global string for XDE Tester HTML Drop-Down Option List class*/
	public static final String gsHtmlLBOptionClass = "Html.OPTION"; //drop-down list box option item
	/**Global string for XDE Tester HTML Drop-Down Option List .text property*/
	public static final String gsHtmlLBOptionProp = ".text";

	/**Global string for XDE Tester HTML RadioButton class. */
	public static final String gsHtmlRBClass = "Html.INPUT.radio";		
	/**Global string for XDE Tester HTML Radiobutton property*/
	public static final String gsHtmlRBProp = ".id";

	/**Global string for XDE Tester HTML Static Text class*/
	public static final String gsHtmlStaticTextClass = "Html.TextNode"; //static text  (non-mappable testobject)
	/**Global string for XDE Tester HTML Static Text .text property*/
	public static final String gsHtmlStaticTextProp = ".text";

	/**Global string for XDE Tester HTML TextField class*/
	public static final String gsHtmlTFClass = "Html.INPUT.text"; //text field
	/**Global string for XDE Tester HTML TextField name property*/
	public static final String gsHtmlTFProp = ".name";
	/**Global string for XDE Tester HTML Text Area class*/
	public static final String gsHtmlTAClass = "Html.TEXTAREA"; //text area
	/**Global string for XDE Tester HTML Text Area .name property*/
	public static final String gsHtmlTAProp = ".name";	
	/**Global string for XDE Tester HTML Password class*/
	public static final String gsHtmlPWClass = "Html.INPUT.password"; //password text field
	/**Global string for XDE Tester HTML Password name property*/
	public static final String gsHtmlPWProp = ".name";
	
	/**Global string for XDE Tester HTML Error class*/
	public static final String gsHtmlErrorClass = "Html.!";
	
	/**Global string for XDE Tester HTML class property*/
	public static final String gsHtmlClassProp = ".class"; //general class property
	/**Global string for XDE Tester HTML class index property*/
	public static final String gsHtmlClassIndexProp = ".classindex"; //general class index property
	
	/**Global string for XDE Tester HTML Table class*/
	public static final String gsHtmlTableClass = "Html.TABLE"; 

	/**Global string for XDE Tester prefix for all html classes*/
	public static final String gsHtmlPrefix = "Html.";
	
	
//************************AWT**************************
	/**Global string for XDE Tester AWT Button class*/
	public static final String gsAwtButtonClass = "java.awt.Button"; //buttons
	/**Global string for XDE Tester AWT Button value property*/
	public static final String gsAwtButtonProp = "label";
	
	/**Global string for XDE Tester AWT CheckBox class*/
	public static final String gsAwtCBClass = "java.awt.Checkbox"; //checkbox
	/**Global string for XDE Tester AWT CheckBox property*/
	public static final String gsAwtCBProp = "label";

	/**Global string for XDE Tester AWT RadioButton class. */
	public static final String gsAwtRBClass = "java.awt.RadioButton";		
	/**Global string for XDE Tester AWT Radiobutton property*/
	public static final String gsAwtRBProp = "label";

	/**Global string for XDE Tester AWT Static Text class*/
	public static final String gsAwtStaticTextClass = "java.awt.Label"; //static text  (non-mappable testobject)
	/**Global string for XDE Tester AWT Static Text .text property*/
	public static final String gsAwtStaticTextProp = "label";
	
	
//************************Swing**************************
	/**Global string for XDE Tester Swing Button class*/
	public static final String gsSwingButtonClass = "javax.swing.JButton"; //buttons
	/**Global string for XDE Tester Swing Button value property*/
	public static final String gsSwingButtonProp = "name";
	
	/**Global string for XDE Tester Swing CheckBox class*/
	public static final String gsSwingCBClass = "javax.swing.JCheckbox"; //checkbox
	/**Global string for XDE Tester Swing CheckBox property*/
	public static final String gsSwingCBProp = "name";


	/**Global string for XDE Tester Swing ComboBox List class*/
	public static final String gsSwingComboLBClass = "javax.swing.JComboBox"; //drop-down list box
	/**Global string for XDE Tester Swing ComboBox List property*/
	public static final String gsSwingComboLBProp = "name";
	/**Global string for XDE Tester Swing List class*/
	public static final String gsSwingLBClass = "javax.swing.JList"; //drop-down list box
	/**Global string for XDE Tester Swing List property*/
	public static final String gsSwingLBProp = "name";

	/**Global string for XDE Tester Swing RadioButton class. */
	public static final String gsSwingRBClass = "javax.swing.JRadioButton";		
	/**Global string for XDE Tester Swing Radiobutton property*/
	public static final String gsSwingRBProp = "name";

	/**Global string for XDE Tester Swing Static Text class*/
	public static final String gsSwingStaticTextClass = "javax.swing.JLabel"; //static text  (non-mappable testobject)
	/**Global string for XDE Tester Swing Static Text .text property*/
	public static final String gsSwingStaticTextProp = "name";


	/**Global string for XDE Tester Swing TextField class*/
	public static final String gsSwingTFClass = "javax.swing.JTextField"; //text field
	/**Global string for XDE Tester Swing TextField name property*/
	public static final String gsSwingTFProp = "name";
	/**Global string for XDE Tester Swing Text Area class*/
	public static final String gsSwingTAClass = "javax.swing.JTextArea"; //text area
	/**Global string for XDE Tester Swing Text Area name property*/
	public static final String gsSwingTAProp = "name";	
	/**Global string for XDE Tester Swing Password class*/
	public static final String gsSwingPWClass = "javax.swing..JPasswordField"; //password text field
	/**Global string for XDE Tester Swing Password name property*/
	public static final String gsSwingPWProp = "name";


	/**Global string for XDE Tester Java class property*/
	public static final String gsJavaClassProp = "class";
	
	/**Global boolean for determining whether this class should default to using string.equals() or String::indexOf() to determine matches. String::equals() is the default*/
	protected static boolean gbExactMatchDefault = true;


	/**Global string for XDE Tester Java Swing name property*/
	protected static final String gsSwingNameProp = "name";
	/**Global string for XDE Tester Java AWT name (label) property*/
	protected static final String gsAwtNameProp = "label";
	/**Global string for XDE Tester Html name property*/
	protected static final String gsHtmlNameProp = ".name";
	/**Global string for XDE Tester Html text property*/
	protected static final String gsHtmlTextProp = ".text";
	/**Global string for XDE Tester Html value property*/
	protected static final String gsHtmlValueProp = ".value";

//**********************************Private Global variables***********************************************
	/**Global boolean to return if TestObject is found*/
	protected static boolean gbKill = false;
	/**Global dynamically returned TestObject*/
	protected static TestObject gtoTestObj = null;
	/**Global string for dynamically returned web content*/
	protected static String gsContent = "";

	public static boolean gbDebug = false;
	
	/**Global string for line separator*/
	protected static String newline = System.getProperty("line.separator");	
	

//*****************************************Static Methods************************************************
	/**
	* Returns the TestObject dynamically that fits the specified search criteria <p>
	* @param s			object set up to find correct item
	* @param parent	Parent TestObject from which to search. 
	* @return found TestObject that matches criteria or null if not found
	* */
	public static TestObject findTestObject(SearchCriteria s, TestObject parent)
	{	
		return findTestObjectRF(s, parent, s.targetIsMappable());
	}
	
	/**
	* Returns the TestObject dynamically for the specified parameter values <p>
	* @param value				identifying property value to search for (i.e. "Click Here")
	* @param propertyName		property value to search for (i.e. ".text")
	* @param classID			TestObject class type identifier (e.g. "Html.A" this is how XDE Tester classifies an item of the link class) 
	* @param parent			Parent TestObject from which to search. 
	* @return found TestObject that matches criteria or null if not found
	* */
	public static TestObject findTestObject(String value, String propertyName, String classID, TestObject parent)
	{
		//set up search criteria to look for the property name specified with the class specified
		SearchCriteria s = new SearchCriteria();
		s.add(propertyName, value);
		s.add(gsHtmlClassProp, classID);
		
		return findTestObject(s, parent);
	}
	
	/**
	* Returns the TestObject dynamically for the specified parameter values <p>
	* @param value			regular expression identifying the property value to search for (i.e. "Click.*")
	* @param propertyName	property value to search for (i.e. ".text")
	* @param classID		TestObject class type identifier (e.g. "Html.A" this is how XDE Tester classifies an item of the link class) 
	* @param parent		Parent TestObject from which to search. 
	* @return found TestObject that matches criteria or null if not found
	* */
	public static TestObject findTestObject(Regex value, String propertyName, String classID, TestObject parent)
	{
		//set up search criteria to look for the property name specified with the class specified
		SearchCriteria s = new SearchCriteria();
		s.add(propertyName, value);
		s.add(gsHtmlClassProp, classID);
		
		return findTestObject(s, parent);
	}
	
	/**
	* This method finds the nth test object that matches the criteria you provide
	* @param value			identifying property value to search for (i.e. "Click Here")
	* @param propertyName	property value to search for (i.e. ".text")
	* @param classID		TestObject class type identifier (e.g. "Html.A" this is how XDE Tester classifies an item of the link class) 
	* @param parent		Parent TestObject from which to search. 
	* @return found TestObject that matches criteria or null if not found
	*/
	public static TestObject findTestObject(String value, String propertyName, String classID, TestObject parent, int matchNumber)
	{		
		SearchCriteria s = new SearchCriteria();
		s.add(propertyName, value);
		s.add(gsHtmlClassProp, classID);
		
		s.setMatchToFind(matchNumber);
		return findTestObject(s, parent);
	}
	
	/**
	 * returns the number of matches for the given search criteria
	 * @param s		the search criteria
	 * @param parent	Parent TestObject from which to search. 
	 * @return the number of matches
	 * */
	public static int getNumberOfMatches(SearchCriteria s, TestObject parent)
	{
		s.setMatchToFind(-1);
		findTestObject(s, parent);
		int returnVal = s.getMatchCount();
		s.resetMatchCounter();
		return returnVal;
	}
	
	/**
	* Turns off or on exact matching<p>
	* This method allows you set the default of the class (for the duration of a script run) to find a matches using either String::indexOf() or String::equals()<p>
	* String::equals() finds a match exactly, which is the default for this class<br>
	* String::indexOf finds a match by matching the first text it finds on the page which begins with the string entered as the first parameter to the method.<p>
	* The latter functionality can be handy when testing web pages that append a number of non-breaking spaces at the end of the text of certain objects (e.g. Links or StaticText). <br>
	* but note that you can always find an exact match by adding spaces to the end of the text, so this method is added as a convenience method - it is not necessary to use it.<p>
	* <b>Note: since by default, exact matching is on,</b> you only need to use this method if you want to turn it off (or back on again after you've explicitly turned it off
	* @param bExactMatch 	if true, uses String::equals() to find matches, if false, uses String::indexOf() 
	*/
	public static void setExactMatching(boolean bExactMatch) {
		gbExactMatchDefault = bExactMatch;	
	}	
	
	/**
	* This method allows you to choose to find a particular match using either String::indexOf() or String::equals()<p>
	* String::equals() finds a match exactly, which is the default for this class<br>
	* String::indexOf finds a match by matching the first text it finds on the page which begins with the string entered as the first parameter to the method.
	* The latter functionality can be handy when testing web pages that append a number of non-breaking spaces at the end of the text of certain objects (e.g. Links or StaticText). <br>
	* but note that you can always find an exact match by adding spaces to the end of the text, so this method is added as a convenience method - it is not necessary to use it.<p>
	* @param value			identifying property value to search for (i.e. "Click Here")
	* @param propertyName	property value to search for (i.e. ".text")
	* @param classID		TestObject class type identifier (e.g. "Html.A" this is how XDE Tester classifies an item of the link class) 
	* @param parent		Parent TestObject from which to search for the link. 
	* @param doExactMatch	if true, uses String::equals() to find a match, if false, uses String::indexOf() 
	* @return found TestObject that matches criteria or null if not found
	*/
	public static TestObject findTestObject(String value, String propertyName, String classID, TestObject parent, boolean doExactMatch)
	{
		SearchCriteria s = new SearchCriteria();
		s.setExactMatch(doExactMatch); 
		s.add(propertyName, value);
		s.add(gsHtmlClassProp, classID);
		
		return findTestObject(s, parent);
	}
	

	
	/**
	* Helper function to recursively search for and return the TestObject dynamically for the specified parameter values <p>
	* @param s					the search criteria
	* @param toParent			Parent TestObject from which to search for the link. 
	* @param targetIsMappable	boolean indicating whether or not target is mappable
	* @return found TestObject that matches criteria
	* */
	protected static TestObject findTestObjectRF(SearchCriteria s, TestObject parent, boolean targetIsMappable)
	{
		
		if(gbDebug)
		{
			System.out.println(s);
		}
		
		//if it matches this one, go no further
		if(s.matches(parent))
		{
			return parent;
		}
	
		TestObject[] objList;
		
		
		if(targetIsMappable) 
		//if what you're looking for is mappable, you need only mappable children
		{
			objList = parent.getMappableChildren();
		}
		else
		//if not, you need all of them
		{
			objList = parent.getChildren();
		}
		
		//see if we can find it further down the tree
		TestObject returnVal = null;
		int index = 0;
		for(index = 0; index < objList.length; index++)
		{
			returnVal = findTestObjectRF(s, objList[index], targetIsMappable);
			if(returnVal != null)
			{
				break;
			}
			else
			{
				//this isn't the right one so unregister it
				objList[index].unregister();
			}
			
		}
		
		//unregister anything left in the list
		for(index = index + 1; index < objList.length; index++)
		{
			objList[index].unregister();
		}
		
		return returnVal;
		
	}
	
	/**		
	* Checks to see whether a dynamically located object exists on the present page <p>
	* @param value			value of property to look for ("click here")
	* @param propertyName	name of property (".text")
	* @param classID		name of class ("Html.A" for links)
	* @param parent		Parent TestObject from which to search for the link. 
	* @return true if object exists on present page; false otherwise
	*/
	public static boolean exists(String value, String propertyName, String classID, TestObject parent)
	{
		GuiTestObject gto = null;
		gto = (GuiTestObject)ObjectFactory.findTestObject(value, propertyName, classID, parent);
		if(gto == null)
		{
			return false;
		}
		try
		{
			gto.waitForExistence(); //will throw ObjectNotFoundException to caller if not found within default timeout
		}
		catch(ObjectNotFoundException e)
		{
			return false;
		}
		finally
		{
			gto.unregister();
		}
		return true;	
	}
	
	/**************************************************************************************************************
	 * This method is to be used by appobjects classes.  When you try to get an object from an object map
	 * (i.e. Button_Search) and it's not on the screen in the place the map expects it to be (so TestObject.exists() returns false)
	 * this method looks at the criteria for that object in the map, and tries to find on the page elsewhere
	 * @param misplacedTestObject		TestObject to look for
	 * @param script					script the object is mapped in
	 **************************************************************************************************************/
	public static TestObject findMisplacedTestObject(TestObject misplacedTestObject, RationalTestScript script)
	{
		TestObject foundTestObject = null;
		
		if(! misplacedTestObject.exists())
		{
			//get the name that will appear in the map
			String ref = misplacedTestObject.getObjectReference().toString();
			ref = ref.substring(ref.indexOf(':') + 1);
			System.out.println(ref);
			
			Enumeration<MappedTestObject> e = script.getMap().elements();
			MappedTestObject mto = null;
			
			while(e.hasMoreElements())
			{
				mto = (MappedTestObject)e.nextElement();
				if(mto.getDescriptiveName().equals(ref))
				{
					break;
				}		
			}
			if(mto == null)
			{
				//handle this;
			}
			
			String[] propNames = mto.getPropertyNames();
			ObjectFactory.SearchCriteria s = new ObjectFactory.SearchCriteria();
			
			for(int i = 0; i < propNames.length; i++)
			{
				MappedTestObjectProperty prop = mto.getPropertyData(propNames[i]);
				if(!(prop.getKey().charAt(0) == '#') && !(prop.getKey().equals(".classIndex")))
				{
					System.out.println(prop.getKey() + "," + prop.getValue() + "," + prop.getWeight());							
					s.add(prop.getKey(), prop.getValue());
				}
				
			}
			
			SpyMappedTestObject smto = new SpyMappedTestObject(0, mto.getTopParent());
			System.out.println(smto);
			GuiTestObject browser = new GuiTestObject(smto);
			System.out.println(browser);
			
			foundTestObject = (GuiTestObject)ObjectFactory.findTestObject(s, browser);
			
			
			
		}
		return foundTestObject;
	}
	
	/**
	* Returns a string containing all visual web content <p>
	* @param parent	Parent TestObject. 
	* @return String containing all visual web content (i.e. [Link] MyLink,etc.)
	*/
	public static String getAllWebObjects(TestObject parent)
	{
		//Get all visual web content as string
		String s = getAllWebObjectsRF(parent);

		//Refine string output
		java.util.StringTokenizer st = new java.util.StringTokenizer(s, "|");
		int index = st.countTokens();
		StringBuffer out = new StringBuffer();
		String sTmp = "";
		int iStartPos/*, iEndPos*/;

		for (int i = 0; i < index; i++) 
		{
			sTmp = st.nextToken();

			if ((sTmp.indexOf("[") != -1) && (sTmp.indexOf("]") != -1)) 
			{
				iStartPos = sTmp.indexOf("[");
				out.append(sTmp.substring(iStartPos) + newline);
			}
		}

		//reset global storage variable
		gsContent = "";

		//return visual web content as string
		return out.toString();
	}
	
	/**
	* Helper function for getAllObjects - returns a string containing all visual web content <p>
	* @param parent		Parent TestObject. 
	* @return String containing all visual web content (i.e. [Link] MyLink,etc.)
	* */
	protected static String getAllWebObjectsRF(TestObject parent)
	{
		//Return all visual web content	

		//get top level children
		TestObject objList[] = parent.getChildren(); //getMappableChildren();

		for (int i = 0; i < objList.length; i++) 
		{

			try {
				if (objList[i].getProperty(gsHtmlClassProp).toString() != null) 
				{
					//System.out.println("*************************************************************************");
					//System.out.println("Class: " + objList[x].getProperty(gsClassProp).toString().trim());
					//printAllProps(objList[x].getProperties());
					//System.out.println("*************************************************************************");

					//Get all object text info
					if (objList[i].getProperty(gsHtmlClassProp) != null) 
					{
						//Links Text
						if (objList[i].getProperty(gsHtmlClassProp).toString().indexOf(gsHtmlLinkClass) != -1)
						//if object is a the right class	
						{
							gsContent = gsContent + getTestClassProperty(objList[i], "[Link] ",	gsHtmlLinkClass,gsHtmlLinkProp)	+ newline;
						}

						//Get Button Text
						if (objList[i].getProperty(gsHtmlClassProp).toString().indexOf(gsHtmlButtonClass)	!= -1)
						//if object is a the right class	
						{
							gsContent =	gsContent + getTestClassProperty(objList[i], "[Button] ", gsHtmlButtonClass, gsHtmlButtonProp) + newline;
						}

						//Get Static Text
						if (objList[i].getProperty(gsHtmlClassProp).toString().indexOf(gsHtmlStaticTextClass) != -1)
						//if object is a the right class	
						{
							gsContent = gsContent + getTestClassProperty(objList[i], "[Text] ",gsHtmlStaticTextClass, gsHtmlStaticTextProp)	+ newline;
						}

						//Get Image Text
						if (objList[i].getProperty(gsHtmlClassProp).toString().indexOf(gsHtmlImageClass)	!= -1)
						//if object is a the right class	
						{
							gsContent = gsContent + getTestClassProperty(objList[i], "[Image] ",gsHtmlImageClass, gsHtmlImageProp) + newline;
						}

						//Get ListBox Object
						if (objList[i].getProperty(gsHtmlClassProp).toString().indexOf(gsHtmlLBClass) != -1)
						//if object is a the right class	
						{
							gsContent =	gsContent + getTestClassProperty(objList[i],"[ListBox] ", gsHtmlLBClass,gsHtmlLBValueProp)	+ newline;
						}

						//Get ListBox Option Object
						if (objList[i].getProperty(gsHtmlClassProp).toString().indexOf(gsHtmlLBOptionClass)!= -1) 
						//if object is a the right class	
						{
								gsContent =	gsContent + getTestClassProperty(objList[i], "[ListBox Option] ", gsHtmlLBOptionClass, gsHtmlLBOptionProp) + newline;
						}

						//Get Text Field Object
						if (objList[i].getProperty(gsHtmlClassProp).toString().indexOf(gsHtmlTFClass) != -1)
						//if object is a the right class	
						{
							gsContent =	gsContent + getTestClassProperty(objList[i], "[Text Field] ", gsHtmlTFClass, gsHtmlTFProp) + newline;
						}

						//Get Password Text Field
						if (objList[i].getProperty(gsHtmlClassProp).toString().indexOf(gsHtmlPWClass) != -1)
						//if object is a the right class	
						{
							gsContent =	gsContent + getTestClassProperty(objList[i], "[Text Field] ", gsHtmlPWClass, gsHtmlPWProp)	+ newline;
						}

						//Get Text Area
						if (objList[i].getProperty(gsHtmlClassProp).toString().indexOf(gsHtmlTAClass) != -1)
							//if object is a the right class	
						{
							gsContent =	gsContent + getTestClassProperty(objList[i],"[Text Area] ",	gsHtmlTAClass, gsHtmlTAProp) + newline;
						}

						//Get RadioButton Object
						if (objList[i].getProperty(gsHtmlClassProp).toString().indexOf(gsHtmlRBClass) != -1)
						//if object is a the right class	
						{
							gsContent =	gsContent + getTestClassProperty(objList[i], "[Radio Button] ", gsHtmlRBClass, gsHtmlRBProp)	+ newline;
						}
	
						//Get CheckBox Object
						if (objList[i].getProperty(gsHtmlClassProp).toString().indexOf(gsHtmlCBClass) != -1)
						//if object is a the right class	
						{
							gsContent = gsContent + getTestClassProperty(objList[i], "[Checkbox] ",	gsHtmlCBClass, gsHtmlCBProp) + newline;
						}

					}
				}
			} 
			catch (Exception e) 
			{
				//continue;
			}

			if (objList[i].getChildren().length > 0)
			//MappableChildren().length > 0)
			{
				getAllWebObjectsRF(objList[i]);
			}

		} //end loop

		for (int i = 0; i < objList.length; i++)
		{
			objList[i].unregister();
		}

		return gsContent;

	}
	
	/**
	* Helper function for getAllObjects(). Returns a string containing the test object html class type and test object name <p>
	* @param to				TestObject. 
	* @param objIdentifier		object identifier
	* @param classID			XDE Tester class id reference (i.e. "Html.INPUT.text" for html text field)
	* @param propertyName		XDE Tester object property (i.e. ".text" for html link)
	* @return String containing all visual web content (i.e. [Link] MyLink,etc.)
	*/
	protected static String getTestClassProperty(TestObject to, String objIdentifier, String classID, String propertyName)
	{
		String output = "";
		String rtrn = "";
//		String tmp = "";

		if (to.getProperty(gsHtmlClassProp).toString().indexOf(classID) != -1)
		//if object is a the right class	`
		{
			if (!to.getProperty(propertyName).toString().trim().equals("")) 
			{
				output = to.getProperty(propertyName).toString().trim();

				if (output.length() > 1)
			    {
					rtrn = objIdentifier + output + "|";
				}

				//TG:  This doesn't seem to be doing anything
//				if (!rtrn.equals(objIdentifier + "|") || !rtrn.equals(objIdentifier + " |") || 
//				    !rtrn.equals(objIdentifier + "  |") || !rtrn.equals(objIdentifier + "   |") || 
//				    !rtrn.equals(objIdentifier + "     |")) 
//				{
//					tmp = objIdentifier + output + "|";
//				}
			}
		}

		return rtrn;
	}
	
	/**
	* Prints out all the properties of an object to the console
	* @param to	object whose properties you want to print
	*/
	public static void printProperties(TestObject to) {
		Hashtable<String, String> htable = to.getProperties();
		int i = 1;
		Enumeration<String> eKeys = htable.keys();
		Enumeration<String> eElems = htable.elements();
		while (eKeys.hasMoreElements()) {
			System.out.println("Hashtable key " + i + ": " + eKeys.nextElement() + "-" + eElems.nextElement());
			i++;
		}
	}
	
	/**
	* Prints out all the non-value properties of an object to the console
	* @param TestObject to - object whose properties you want to print
	*/
	public static void printNonValueProperties(TestObject to) {
		TestObject remref = (TestObject)to.getProperty("nonvalue");
		
		Hashtable<String, String> htable = remref.getNonValueProperties();
		int i = 1;
		Enumeration<String> eKeys = htable.keys();
		Enumeration<String> eElems = htable.elements();
		while (eKeys.hasMoreElements()) {
			System.out.println("Hashtable key " + i + ": " + eKeys.nextElement() + "-" + eElems.nextElement());
			i++;
		}
		remref.unregister();
	}
	
	/**
	* Prints the class of each of the childen of the object
	* @param parent	parent whose children you want to print
	*/
	public static void printChildren(TestObject parent)
	{
		
		TestObject [] objList = parent.getChildren();
		for (int i=0;i<objList.length;i++)
		{
			System.out.println(getClassName(objList[i]));
		}
	}
	
	/**
	* Prints the class of each of the descendents of the object.<br>
	* In other words, prints the children and the children's children, etc.<p>
	* @param parent	parent whose descendents you want to print
	*/	
	public static void printAllDescendents(TestObject parent)
	{
		printAllDescendentsRF(parent, System.out, "");
	}
	
	/**
	* Prints the class of each of the descendents of the object to a file.<br>
	* In other words, prints the children and the children's children, etc.<p>
	* @param parent	parent whose descendents you want to print
	* @param filename	the name of the file, including the path, to print to. (Warning: this file will be overwritten with the new data)
	*/	
	public static void printAllDescendents(TestObject parent, String filename)
	{
		try {
			File f = new File(filename);
			if (!f.exists())
				f.createNewFile();
			
			FileOutputStream fw = new FileOutputStream(filename);
			PrintStream ps = new PrintStream(fw);
		
			printAllDescendentsRF(parent, ps, "");
			ps.close();
			fw.close();
		} catch (IOException e) {
			//TODO
			//PackageLoggingController.logPackageError(PackageLoggingController.PACKAGELOGLEVEL_ERRORS_ONLY, "Error in ObjectFactory#printAllDescendents: " + e.getMessage());
		}
		
	}
	protected static void printAllDescendentsRF(TestObject parent, PrintStream out, String hierarchy)
	{
		TestObject [] children = parent.getChildren();
		for (int i=0;i<children.length;i++)
		{
			out.println(hierarchy + i + " " + getClassName(children[i]));
			printAllDescendentsRF(children[i], out, hierarchy + new Integer(i).toString() + "."); 
		}
	}
	
	/**
	 * Prints out the methods of an object (omitting methods from java.lang.Object)
	* @param to	object whose methods you want to print
	 */	
	public static void printMethods(TestObject to)
	{
		MethodInfo[] m = to.getMethods();
		for (int i = 0; i < m.length; ++i)
		{
			if (!m[i].getDeclaringClass().equals("java.lang.Object"))
				System.out.println( m[i] );
		}
	}
	
	/**
	* Determines if a TestObject is an HTML object
	* @param to	object to check
	* @return true if is an HTML object; false otherwise
	*/
	public static boolean isHTML(TestObject to) {
		String sClass = to.getObjectClassName();
		return sClass.indexOf(gsHtmlPrefix) != -1;
	}
		
	
	/**
	* Gets the class name of the GUI object as identified by properties of the object.
	* @param to	object from which to get the class name.
	* @return the class name. 
	*/
	public static String getClassName(TestObject to)
	{
		if (ObjectFactory.isHTML(to))
			return to.getProperty(ObjectFactory.gsHtmlClassProp).toString();
		else 
			return to.getProperty(ObjectFactory.gsJavaClassProp).toString();
			
		//return this.getClass().getName().substring(sWidgetClass.lastIndexOf('.') + 1); //strip off package name
	}

	/**
	* Gets the name of the object from the properties <br>
	* Note: the object must be on the screen or you will get an ObjectNotFoundError<br>
	* If no name could be found, this method returns null.
	* @param to	object to get the name from
	* @return the object's name or null if no name could be found.
	* 
	*/
	public static String getObjectName(TestObject to) {
		Object name = null;
		
		try {
			name = to.getPropertyFromMap("#name");
		} catch (RationalTestException e) { // the widget was created dynamically
			name = null;
		}
		
		if (name == null || name.equals("")) {//try finding the name from the properties hashtable
			Hashtable<String, String> h = to.getProperties();
			
			if (ObjectFactory.isHTML(to))
			{
				//try various keys for html until find one that isn't null or run out of ideas
				if (h.containsKey(gsHtmlNameProp))
					name = h.get(gsHtmlNameProp);
				if ((name == null || name.equals("")) && h.containsKey(gsHtmlTextProp)) //i.e. either doesn't have property or property value is null
					name = h.get(gsHtmlTextProp);
				if ((name == null || name.equals("")) && h.containsKey(gsHtmlValueProp)) //i.e. either doesn't have property or property value is null
					name = h.get(gsHtmlValueProp);
				if ((name == null || name.equals("")) && h.containsKey(ObjectFactory.gsHtmlImageProp))
					name = h.get(ObjectFactory.gsHtmlImageProp);
			}
			else //is Java
			{
				if (h.containsKey(gsSwingNameProp)) //Swing
					name = h.get(gsSwingNameProp);
				else if (h.containsKey(gsAwtNameProp)) //AWT
					name = h.get(gsAwtNameProp);
			}
		}
		
		if (name == null || name.equals(""))
			return null;
		else
			return name.toString();
	}
	


	/*********************************************************************************************
 	*
	* 	This class is used to set up interesting findTestObject searches in object factory<p>
	*   
	*   <code>ObjectFactory.SearchCriteria s = new ObjectFactory.SearchCriteria();</code><p>
	* 
	* 	After that you can add criteria as you see fit.  It excepts any object.  
	*   Of particular interest are strings and regular expressions, which are 
	*   probably going to come up most often<p>
	* 
	* 	<code>s.add(".class", "Html.INPUT.submit");<p>
	* 		  s.add(".text", "Click Here"); <p>
	* 	      s.add(".value", new Regex("Click .*"));</code><p>
	* 
	* 	if you want to find the nth match on a page <p>
	* 	<code>s.setMatchToFind(n);<code><p>
	* 	
	*	to see if a TestObject testObj fits the SearchCriteria call<p>
	* 	<code> boolean fits = s.matches(testObj);</code><p>
	* 
	* 	isMappable() looks at the search criteria and tells you whether the 
	* 	object you are looking for can be placed in an object map.
	* 	It will need to be changed if we ever start looking for non-mappable objects other than
	* 	Html.textNode's  
	*********************************************************************************************/
	public static class SearchCriteria
	{
		private Hashtable<String, Object> criteria;
		//private boolean caseSensitive; /* not used */
		//private boolean debug;
		private boolean isJava;
		private boolean exactMatch;
		private int matchNumber;
		private int matchCounter;
		private String HTMLPrefix = "Html.";
		
		public SearchCriteria()
		{	
			criteria = new Hashtable<>();
			//debug = false;
			//isJava = false;
			exactMatch = ObjectFactory.gbExactMatchDefault;
			matchNumber = 0;
			matchCounter = 0;
		}
		
		
		
		/*************************************************************
		 * adds a property name/value pair to the search criteria
		 * @param propertyName "class", ".value", ".text", etc.
		 * @param value value for it to search for (accepts Strings, Regexs, Integers, etc.)
		 *************************************************************/
		public void add(String propertyName, Object value)
		{	
			//this checks to see if they set the propertyName wrong for java and html apps (class/.class)
			if((propertyName.equals(gsHtmlClassProp)) && 
			  (((String)value).indexOf(HTMLPrefix) == -1))
			{
				propertyName = gsJavaClassProp;	
				isJava = true;
			}
			else if((propertyName.equals(gsJavaClassProp)) && 
			  (((String)value).indexOf(HTMLPrefix) != -1))
			{
				propertyName = gsHtmlClassProp;
				isJava = false;
			}
			criteria.put(propertyName, value);
		}
		
		
		
		/**************************************************************
		 * removes the property name/value pair with the given property name
		 * @param propertyName ".class", ".value", ".text", etc.
		 *************************************************************/
		public void remove(String propertyName)
		{
			criteria.remove(propertyName);
		}
		
		
		
		/**************************************************************
		 * returns the value for the given property name
		 * @param propertyName
		 * @return Object value
		 *************************************************************/
		public Object get(String propertyName)
		{
			return criteria.get(propertyName);
		}
		
		
		
		/**************************************************************
		 * tells if the SearchCriteria object contains the given property
		 * @param propertyName
		 * @return boolean
		 *************************************************************/
		public boolean contains(String propertyName)
		{
			return criteria.contains(propertyName);
		}
		
		
		
		/**************************************************************
		 * gets all property names contained in the SearchCriteria Object
		 * @return String[] all property names
		 *************************************************************/
		public String[] getPropertyNames()
		{
			Enumeration<String> keys = criteria.keys();
			Vector<String> keyVector = new Vector<>();
			String[] keyStrings;
			while(keys.hasMoreElements())
			{
				keyVector.add(keys.nextElement());
			}
			keyStrings = new String[keyVector.size()];
			for(int i = 0; i < keyStrings.length; i ++)
			{
				keyStrings[i] = (String)keyVector.elementAt(i);
			}
			return keyStrings;
		}
		
		
		
		/**************************************************************
		 * tells whether the search criteria fits this particular TestObject
		 * @param to
		 * @return boolean
		 *************************************************************/
		public boolean matches(TestObject to)
		{
			try
			{
				//if it's an error, don't try anything (Html.!)
				if(((String)to.getProperty(getClassProp())).indexOf(gsHtmlErrorClass) != -1)
				{
					return false;
				}
			
				String[] propNamesToSearchFor = this.getPropertyNames();
			
				for(int i = 0; i < propNamesToSearchFor.length; i++)
				{
					Object criterion = this.get(propNamesToSearchFor[i]);
					Object propertyValToMatch = to.getProperty(propNamesToSearchFor[i]); 
					
					//now we'll see if the particular properties match (works for regex's and other objects that override .equals())
					if(!propertyMatches(criterion, propertyValToMatch))
					{
						return false;
					}
					//else: so far so good, on to next property
				}
				if(matchCounter == matchNumber)
				{
					matchCounter = 0;
					return true;
				}
				else
				{
					matchCounter ++;
					return false;
				}
			}
			catch(PropertyNotFoundException e)
			{
				return false;
			}	
		}
		
//		// I left this in for debugging reasons - it grabs the whole properties hashtable
//		// so it is useful in times when you are getting PropertyNotFoundExceptions
//		public boolean matchesBackup(TestObject to)
//		{
//			//if it's an error, don't try anything (Html.!)
//			if(((String)to.getProperty(gsClassProp)).indexOf(gsErrorClass) != -1)
//			{
//				return false;
//			}
//			
//			//it's faster to get the entire hashtable of 200+ properties than to get one at a time
//			Hashtable objectProperties = to.getProperties();
//			String[] propNamesToSearchFor = this.getPropertyNames();
//			
//			if(debug)
//			{
//				for(int i = 0; i < propNamesToSearchFor.length; i++)
//				{
//					System.out.print(propNamesToSearchFor[i] + ": " + objectProperties.get(propNamesToSearchFor[i]) + ", ");
//				}
//				System.out.println();
//			}
//			for(int i = 0; i < propNamesToSearchFor.length; i++)
//			{
//				//if the test object doesn't contain the property we're looking, it obviously doesn't match
//				if(!objectProperties.containsKey(propNamesToSearchFor[i]))
//				{
//					return false;	
//				}
//				
//				Object criterion = this.get(propNamesToSearchFor[i]);
//				Object propertyValToMatch = objectProperties.get(propNamesToSearchFor[i]); 
//				
//				//now we'll see if the particular properties match (works for regex's and other objects that override .equals())
//				if(!propertyMatches(criterion, propertyValToMatch))
//				{
//					return false;
//				}
//				//else: so far so good, on to next property
//			}
//			return true;		
//		}
		
		
		private boolean propertyMatches(Object criterion, Object propertyToMatch)
		{
			//Check for null
			if (criterion == null) 
			{
				if (propertyToMatch == null)
				{
					return true;
				}
				else
				{
					return false;
				}
			} 
			else if (propertyToMatch == null) 
			{
				return false;
			}

			//check for match
			if(criterion instanceof Regex)
			{
				return ((Regex)criterion).matches(propertyToMatch.toString());
			}
			else if(criterion instanceof String) 
			{
				if (!exactMatch && (propertyToMatch instanceof String) )
				{
					return ((String)propertyToMatch).indexOf((String)criterion) != -1;
				}
				else
				{
					return criterion.equals(propertyToMatch.toString());
				}
			}
			else //if property to match is not a string, must return the property as is to avoid an error
			{
				return criterion.equals(propertyToMatch);	
			}
			
		}
		
		
		
		public String toString()
		{
			String retval = "";
			String[] props = getPropertyNames();
			for(int i = 0; i < props.length; i++)
			{
				retval += (props[i] + ": " + get(props[i])+";");
			}
			return retval;
		}
		
		
		
		/**************************************************************
		 * Tells whether the object with the given search criteria could be placed in an object map
		 * @return boolean
		 *************************************************************/
		public boolean targetIsMappable()
		{
			try
			{
				return !((String)criteria.get(getClassProp())).equals(gsHtmlStaticTextClass);
			}
			catch(PropertyNotFoundException e)
			{
				return false;
			}	
		}
		
		
		
		/**************************************************************
		 * Shows some debugging info if set to true
		 * @param debugModeOn
		 *************************************************************/
//		public void setDebugMode(boolean debugModeOn)
//		{
//			debug = debugModeOn;
//		}
		
		
		
		/**************************************************************
		 * If you add a class name to the search criteria, you 
		 * don't need to use this. If not, it should be false 
		 * if your software under test is an html app, and true otherwise.
		 * @param isJavaApp
		 *************************************************************/
		public void setJavaMode(boolean isJavaApp)
		{
			isJava = isJavaApp;
		}
		
		
		/**************************************************************
		 * If you set it to false, it will search for property values as a prefix instead of doing exact searches.
		 * @param doExactMatch
		 *************************************************************/
		public void setExactMatch(boolean doExactMatch)
		{
			exactMatch = doExactMatch;
		}
		
		/****************************************************************
		 * sets the number of the match you want to find - Zero Based
		 * @param matchNum
		 ************************************************************/
		public void setMatchToFind(int matchNum)
		{
			matchNumber = matchNum;
		}
		
		/****************************************************************
		 * gets the number of matches found (You shouldn't need to call this - see ObjectFactory.getNumberOfMatches()
		 * @author Travis Grigsby
		 ********************************************************/
		public int getMatchCount()
		{
			return matchCounter;
		}
		
		/***************************************************************
		 * sets the match counter to zero (You shouldn't need to call this - see ObjectFactory.getNumberOfMatches()
		 ******************************************************/
		public void resetMatchCounter()
		{
			matchCounter = 0;
		}
		
		private String getClassProp()
		{
			if(isJava)
			{
				return gsJavaClassProp;
			}
			else
			{
				return gsHtmlClassProp;
			}
		}
	}	
}