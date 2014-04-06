package rft.misc;
import java.util.Vector;

import resources.rft.misc.WidgetGettersGenHelper;
import rft.tookit.ClassGenerator;
import appobjects.O_XXX01;
import appobjects.O_XXX02;

import com.rational.test.ft.script.RationalTestScript;
/**
 * Description   : Functional Test Script
 * @author Administrator
 */
public class WidgetGettersGen extends WidgetGettersGenHelper
{
	/**
	 * Script Name   : <b>WidgetGettersGen1</b>
	 * Generated     : <b>Mar 6, 2014 3:03:14 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/03/06
	 * @author Bing Qu
	 */
	public void testMain(Object[] args) 
	{
		Vector<RationalTestScript> v = new Vector<RationalTestScript>();
	    
	    // for every appObject script add a new object of that type to the vector
	   	v.add(new O_XXX01());
	   	v.add(new O_XXX02());
	    
	    (new ClassGenerator()).updateScripts(v);
	}
}

