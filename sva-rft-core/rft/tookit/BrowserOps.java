package rft.tookit;

import rft.widgets.ObjectFactory;
import rft.widgets.WBrowser;
import rft.widgets.ancestors.Widget;

import com.rational.test.ft.object.interfaces.BrowserTestObject;
import com.rational.test.ft.object.interfaces.ClientTestObject;
import com.rational.test.ft.object.interfaces.DomainTestObject;
import com.rational.test.ft.object.interfaces.IScreen;
import com.rational.test.ft.object.interfaces.IWindow;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.IOptionName;
import com.rational.test.ft.script.RationalTestScript;

public class BrowserOps {

	/**Global string for XDE Tester Html Browser class. Html browser class - associated property values = .browserName*/
	public static String gsBrowserClass = "Html.HtmlBrowser";

	/**Global string for XDE Tester class property*/
	protected static final String gsClassProp = ".class";

	/**Global string for Html string*/
	protected static final String gsHtml = "Html";

	/**Global string for XDE Tester Static Text class. Static text class (non-mappable testobject)  - associated property value = .text*/
	protected static final String gsTextProp = ".text";

	/**Global string for XDE Tester Html DialogBox Static Text class. Html Dialog Static Text class - associated property values = .text*/
	public static String gsBrowserDialogTextClass = "Html.DialogStatic";

	/**Global string for XDE Tester Html DialogBox class. Html Dialog class - associated property values = .caption*/
	public static String gsBrowserDialogClass = "Html.Dialog";

//	/**provides this class with access to RationalTestScript functions*/
//	protected static BrowserHelperMethods bu = new BrowserHelperMethods();

//***********************************************************************
	/** 	
	* Closes all Browsers currently open<p>
	*/
	public static void closeAllBrowsers() {
		closeAllBrowsersExcept(null);
	}

	/**				
	* Close all browsers currently open except for any whose caption matches the string entered<p>
	* @param sLeaveOpen 	the caption of the browser to leave open (if null, all browsers will be closed)
	*/
	public static void closeAllBrowsersExcept(String sLeaveOpen) {
		BrowserHelperMethods bu = new BrowserHelperMethods();
		DomainTestObject domains[] = bu.rtsGetDomains();

		for (int i = 0; i < domains.length; ++i) {
			if (domains[i].getName().equals(gsHtml)) {
				//We found an Html domain.
				TestObject[] topObjects = domains[i].getTopObjects();
				if (topObjects != null) {
					try {
						for (int j = 0; j < topObjects.length; ++j) {
							//found a browser window
							if (topObjects[j]
								.getProperty(gsClassProp)
								.equals(gsBrowserClass)) {
								BrowserTestObject browser =
									new BrowserTestObject(topObjects[j]);

								//make found browser active
								browser.activate();

								//if browser caption does not contain the text specified in string sLeaveOpen then close it
								if ((sLeaveOpen == null) || (bu.getActiveWindowCaption().indexOf(sLeaveOpen) == -1)) {
									browser.close();
									// Added by Paul Chinag, it is possible to remove one browser, but
									// remove multiple domains, so the domains.length is reduced
									domains = bu.rtsGetDomains();
								}
							}
						}
					} catch (Exception e) {
						//TODO
						//PackageLoggingController.logPackageError(PackageLoggingController.PACKAGELOGLEVEL_ERRORS_ONLY, "Error in BrowserOps#closeAllBrowsersExcept: " + e);
					}
				}
			}
		}
	}
	
	/**				
	* Closes the browser with the particular caption<p>
	* @param sToClose 	the caption of the browser to close
	*/
	public static void closeBrowserWithCaption(String sToClose) {
		BrowserHelperMethods bu = new BrowserHelperMethods();
		DomainTestObject domains[] = RationalTestScript.getDomains();

		for (int i = 0; i < domains.length; ++i) {
			if (domains[i].getName().equals(gsHtml)) {
				
				//		We found an Html domain.
				TestObject[] topObjects = domains[i].getTopObjects();
				if (topObjects != null) {
					try {
						for (int j = 0; j < topObjects.length; ++j) {
							
							//		found a browser window
							if (topObjects[j].getProperty(gsClassProp).equals(gsBrowserClass)) {
								BrowserTestObject browser =new BrowserTestObject(topObjects[j]);
								WBrowser wb = new WBrowser(browser);
								
								//			make found browser active
								wb.activate();
								//		sCaption=wb.getCaption();
								//		System.out.println(sCaption);
								//		if browser caption contains the text specified in string sToClose then close it
								if (bu.getActiveWindowCaption().indexOf(sToClose) != -1) {
									browser.close();
									//		return; Not Used to close Multiple browses with same caption.
								}
							}
						}
					} catch (Exception e) {
						//TODO:
						//PackageLoggingController.logPackageError(PackageLoggingController.PACKAGELOGLEVEL_ERRORS_ONLY, "Error in BrowserOps#closeBrowserWithCaption: " + e);
					}
				}
			}
		}
	}

	
	/**				
	* Gets the caption of the currently active browser<p>
	* @return caption of currently active browser.
	*/
	public static String getActiveBrowserCaption() {
		BrowserHelperMethods bu = new BrowserHelperMethods();
		
		return bu.getActiveWindowCaption();
	}

	//	/** 	
	//	* Closes all IE Browsers currently open<p>
	//	*/
	//	public static void closeAllIEBrowsers() {
	//		IWindow[] wins = RationalTestScript.getTopWindows();
	//		for (int i = 0; i < wins.length; i++) {
	//			if (wins[i].getWindowClassName().equals("IEFrame")) {
	//				wins[i].close();
	//			}
	//		}
	//	} 

	/**
	* Dynamically finds and returns an instance of a specified html browser among multiple or ambiguous browser child windows <p>
	* @param sValue 		property value to search for (i.e. "link")
	* @param sProperty		identifying property value to search for (i.e. ".text")
	* @param sClassID		TestObject class type identifier (i.e. "Html.A" this is how XDE Tester classifies an item of the link class) 
	* @return instance of Html child window; returns null if no dialog found
	*/
	public static TestObject findParticularBrowser(
		String sValue,
		String sProperty,
		String sClassID) {
		return findParticularBrowserWindow(
			sValue,
			sProperty,
			sClassID,
			gsBrowserClass);
	}
	
	/**
	* Waits for the browser to be ready <p>
	* @param to 	instance of Browser to wait for
	* @return true if browser is ready, false if timed out
	*/
	public static boolean waitForReady(TestObject to) {
		BrowserHelperMethods bu = new BrowserHelperMethods();
		long maxWaitTimeMillis = (long)bu.getDefaultMaxWaitTime() * 1000;
		//boolean bReturn = false;
		
		if (!Widget.waitForExistenceBoolean(to))
			return false;
			
    	//get start time so can determine timeout
   		long startTime = System.currentTimeMillis();
	
		while (!isReady(to) && (System.currentTimeMillis() - startTime) < maxWaitTimeMillis)
			bu.wait(2);
		if (isReady(to))
			return true;
		else
			return false;	
	}
	
	/**
	* Waits for the first browser found to be ready <p>
	* Use when you know there is only one browser up, such as after closeAllBrowsers
	* @return true if browser is ready, false if timed out
	*/
	public static boolean waitForReady() {
		BrowserHelperMethods bu = new BrowserHelperMethods();
		long maxWaitTimeMillis = (long)bu.getDefaultMaxWaitTime() * 1000;
		//boolean bReturn = false;
		
		  //get start time so can determine timeout
   		long startTime = System.currentTimeMillis();
   		
   		TestObject to = findBrowser();
		
		while ((to ==null) && ((System.currentTimeMillis() - startTime) < maxWaitTimeMillis))
		{
			bu.wait(10);
			to = findBrowser();
		}
		if (to == null)
			return false;
			
    	//get start time again for next timeout
   		startTime = System.currentTimeMillis();
	
		while (!isReady(to) && (System.currentTimeMillis() - startTime) < maxWaitTimeMillis)
			bu.wait(1);
		
		if (isReady(to)) {
			RationalTestScript.unregister(new Object[]{to});
			return true;
		}
		else {
			RationalTestScript.unregister(new Object[]{to});
			return false;
		}
	}
	
	/**
	* Determines if the browser is ready <p>
	* @param to 	instance of Browser to wait for
	* @return true if browser is ready, false otherwise
	*/
	public static boolean isReady(TestObject to) {
		return Integer.parseInt(to.getProperty(".readyState").toString()) == 4;		
	}

	/**				
	* Verifies whether text exists on the indicated browser page
	* @param textToFind 	the text to find on the page.
	* @param browserPage 	the browser page to check.
	* @return true if text is found; false otherwise.
	*/
	public static boolean verifyTextOnBrowserPage(
		String textToFind,
		TestObject browserPage) {
		return browserPage.getProperty(".text").toString().indexOf(textToFind)
			!= -1;
	}
	
	/**				
	* Returns the first browser it finds.<br>
	* This is handy for use in methods when you know you only have one browser open, but don't have an object map,<br>
	* like calling BrowserOps.waitForReady from a package script.<br>
	* @return the first browser found; null if no browsers open.
	*/
	public static BrowserTestObject findBrowser() {
		BrowserHelperMethods bu = new BrowserHelperMethods();
		DomainTestObject domains[] = bu.rtsGetDomains();

		for (int i = 0; i < domains.length; ++i) {
			try {
				if (domains[i].getName().equals(gsHtml)) {
					//We found an Html domain.
					TestObject[] topObjects = domains[i].getTopObjects();
					if (topObjects != null) {
						try {
							for (int j = 0; j < topObjects.length; ++j) {
							
								if (topObjects[j] instanceof BrowserTestObject) 
								{
									return (BrowserTestObject)topObjects[j];
								}
							}
						} catch (Exception e) {
							System.out.println("Exception in findBrowser: " + e);
							e.printStackTrace();
						}
					}
				}
			} catch (com.rational.test.ft.TargetGoneException e) {
				//noop - continue if target has since disappeared
			}
		}
		//if we get here, we didn't find a browser
		return null;
	}
	

//******************************************Dialogs*********************************************************	
	/**				
	* Dynamically finds and closes all HTML dialogs
	*/
	public static void closeAllBrowserDialogs() {

		BrowserHelperMethods bu = new BrowserHelperMethods();

		//TestObject dlg = null;

		//Get all domains and search each domain (html windows) for the right browser
		DomainTestObject domains[] = bu.rtsGetDomains();
		for (int i = 0; i < domains.length; ++i) {
			if (domains[i].getName().equals(gsHtml)) {
				//We found an Html domain.
				TestObject[] topObjects = domains[i].getTopObjects();
				if (topObjects != null) {
					for (int j = 0; j < topObjects.length; ++j) {
						//System.out.println(topObjects[j].getProperty(gsClassProp).toString());

						if (topObjects[j]
							.getProperty(gsClassProp)
							.equals(gsBrowserDialogClass))
							 ((TopLevelTestObject) topObjects[j]).close();
					}
				}
			}
		}
	}

	/** 
	* Returns DialogBox message text <p>
	* @param dlg 		the dialog whose message text you want
	* @return sMsg 	the DialogBox's static message text   	
	*/
	public static String getBrowserDialogMessageText(TestObject dlg) {

		String sMsg = "";

		if (dlg == null) {
			System.out.println(
				"FAIL - No dialog was specified or dialog was null");
			return sMsg;
		}

		if (dlg.exists()) {
			TestObject[] toDlg = dlg.getChildren();

			for (int i = 0; i < toDlg.length; i++) {
				//System.out.println("*************************************************************************");
				//System.out.println("Class: " + toDlg[i].getProperty(gsClassProp).toString().trim());
				//ObjectFactory.PrintAllProperties(toDlg[i].getProperties());
				//System.out.println("*************************************************************************");

				if (toDlg[i]
					.getProperty(gsClassProp)
					.toString()
					.equals(gsBrowserDialogTextClass)) {
					//System.out.println(toDlg[x].getProperty(".text").toString());
					sMsg = toDlg[i].getProperty(gsTextProp).toString();

					if (sMsg.length() >= 1) {
						return sMsg;
					}
				}
			}
		}
		return sMsg;
	}

	/**
	* Dynamically finds and returns an instance of a specified html browser among multiple or ambiguous browser child windows <p>
	* @param sValue 		property value to search for (i.e. "link")
	* @param sProperty 	identifying property value to search for (i.e. ".text")
	* @param sClassID 		TestObject class type identifier (i.e. "Html.A" this is how XDE Tester classifies an item of the link class) 
	* @param sWindowClass 	parent window class type identifier (i.e. "Html.Dialog") 
	* @return instance of Html child window. Returns null if no dialog found
	*/
	public static TestObject findParticularBrowserWindow(
		String sValue,
		String sProperty,
		String sClassID,
		String sWindowClass) {
		BrowserHelperMethods bu = new BrowserHelperMethods();

		TestObject window = null;

		//Get all domains and search each domain (html windows) for the right browser
		DomainTestObject domains[] = bu.rtsGetDomains();
		for (int i = 0; i < domains.length; ++i) {
			if (domains[i].getName().equals(gsHtml)) {
				//We found an Html domain.
				TestObject[] topObjects = domains[i].getTopObjects();
				if (topObjects != null) {
					try {
						for (int j = 0; j < topObjects.length; ++j) {
							//System.out.println(topObjects[j].getProperty(gsClassProp).toString());

							//found a browser Dialog window
							if (topObjects[j]
								.getProperty(gsClassProp)
								.equals(sWindowClass)) {
								//System.out.println(GetAllObjects(topObjects[j]));	

								// find the right browser window by searching for the existing document title or test object
								TestObject b =
									ObjectFactory.findTestObject(
										sValue,
										sProperty,
										sClassID,
										topObjects[j]);

								if (b != null) {
									window = new ClientTestObject(topObjects[j]);
									//System.out.println("found match");
									return window;
								}
							}
						}
					} catch (Exception e) {
						System.out.println(
							"Exception in BrowserOps::getParticularBrowserDialog(): "
								+ e);
					}
				}
			}
		}

		return window;
		//should still be null if reach here						
	}
	
	/**				
	* starts Browser and sets to particular page <p>
	* This method just wraps the RationalTestScript method startBrowser, allowing public access<br>
	* @param sBrowserPage 	the page to set the new instance of the browser to
	*/
	public static void startBrowser(String sBrowserPage) {
		BrowserHelperMethods bu = new BrowserHelperMethods();
		
		bu.rtsStartBrowser(sBrowserPage);
	}

//**************************Inner Class****************************************************************************
	/**	
	* Description: this class is for separating out RationalTestScript methods and putting them in a format so that the Browser Ops can be static			
	* 
	*/
	protected static class BrowserHelperMethods extends RationalTestScript 
	{
		public boolean DEBUG = false;
		public double dblMaxWaitTime = 120000;
		
		public BrowserHelperMethods()
		{
			//must set the script name in order for startBrowser to work
			String caller = getTopScriptName();
			if (DEBUG)
				System.out.println(caller);
			try 
			{
				setScriptName(caller);
			} 
			catch(Exception e) //in case the calling script doesn't have a matching script definition in the resources directory for some reason, just bail
			{ 
				//noop
			}
			
			try
			{
				dblMaxWaitTime = Double.parseDouble(super.getOption(IOptionName.MAXIMUM_WAIT_FOR_EXISTENCE).toString());
			}
			catch (Exception e2)
			{
				//set to alternative default above
			}
		}
			
		/**				
		* Waits the specified amount of time. (just a wrapper for the sleep method from RationalTestScript) <p>
		*/
		public void wait(int time) {
			super.sleep(time);
		}

		/**
		* Gets all the domains and returns them in an array<p>
		* Wraps the getDomains() method of RationalTestScript
		* @return array of DomainTestObjects including all the domains
		*/
		public DomainTestObject[] rtsGetDomains() {
			return super.getDomains();
		}

		/**				
		* Gets active window caption <p>
		* @return active window caption.
		*/
		public String getActiveWindowCaption() {
			//Get Active Window Caption
			IScreen wActiveWin = super.getScreen();
			IWindow win = wActiveWin.getActiveWindow();

			return win.getText();
		}

		/**				
		* starts Browser and sets to particular page <p>
		* @param sBrowserPage 	the page to set the new instance of the browser to
		*/
		public void rtsStartBrowser(String sBrowserPage) {
			super.startBrowser(sBrowserPage);
		}
	
		/**				
		* returns the Maximimum Wait For Existence time stored in the preferences of XDE <p>
		* @return maximum time that this version of XDE is set to wait for the existence of an object
		*/	
		public double getDefaultMaxWaitTime() {
			return dblMaxWaitTime;
		}
	}
}

