package rft.widgets.ancestors;

public interface IWidget {
	/**		
	* Waits for the particular widget stored in this object to exist on the screen <br>
	* @return true if object exists within the default time; false if doesn't
	* 
	*/
	public boolean waitForExistenceBoolean();
	
	/**
	* Determines whether the particular widget stored in this object is enabled<br>
	* @return true if enabled, false if disabled  
	* */
	public boolean isEnabled();
	
	/**
	* Prints out all the properties of a widget to the console
	*/
	public void printProperties();
	
	/**
	 * Prints out the methods of a widget to the console (omitting methods from java.lang.Object)
	 */	
	public void printMethods();
	
//*************************** For Logging **************************
	/**
	 * Returns the name of the type of the widget<br>
	 * e.g. "button" or "listbox" or "textfield"
	 * @return the widget type
	 */		
	public String getWidgetType();

	/**
	* Gets the name of the object from the properties <br>
	* Note: the object must be on the screen or you will get an ObjectNotFoundError<br>
	* If no name could be found, this method should return null
	* @return the name; null if no name could be found
	*/
	public String getName();	
}

