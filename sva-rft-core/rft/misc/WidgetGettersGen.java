package rft.misc;
import java.util.Vector;

import resources.rft.misc.WidgetGettersGenHelper;
import rft.tookit.ClassGenerator;

import com.rational.test.ft.script.RationalTestScript;

/**
 * Widgets' getter methods Generation Toolkit
 *
 * @author Bing Qu
 */
public class WidgetGettersGen extends WidgetGettersGenHelper
{
	/**
	 * Script Name   : <b>WidgetGettersGen</b>
	 */
	public void testMain(Object[] args) 
	{
		Vector<RationalTestScript> v = new Vector<RationalTestScript>();
	    
	    // for every appObject script add a new object of that type to the vector
	   	//v.add(new oSample1());
	   	//v.add(new oSample2());
	    
	    (new ClassGenerator()).updateScripts(v);
	}
}

