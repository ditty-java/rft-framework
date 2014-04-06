// DO NOT EDIT: This file is automatically generated.
//
// Only the associated template file should be edited directly.
// Helper class files are automatically regenerated from the template
// files at various times, including record actions and test object
// insertion actions.  Any changes made directly to a helper class
// file will be lost when automatically updated.

package resources.testscripts;

import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.WPF.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.object.interfaces.dojo.*;
import com.rational.test.ft.object.interfaces.generichtmlsubdomain.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.vp.IFtVerificationPoint;
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;
/**
 * Script Name   : <b>Script2</b><br>
 * Generated     : <b>2014/03/21 2:18:06 PM</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows 7 x86 6.1 <br>
 * 
 * @since  March 21, 2014
 * @author Administrator
 */
public abstract class Script2Helper extends RationalTestScript
{
	/**
	 * HtmlTable_0: with default state.
	 *		.id : 
	 * 		.title : 
	 * 		.class : Html.TABLE
	 * 		.caption : 
	 * 		.classIndex : 0
	 */
	protected StatelessGuiSubitemTestObject table_htmlTable_0() 
	{
		return new StatelessGuiSubitemTestObject(
                        getMappedTestObject("table_htmlTable_0"));
	}
	/**
	 * HtmlTable_0: with specific test context and state.
	 *		.id : 
	 * 		.title : 
	 * 		.class : Html.TABLE
	 * 		.caption : 
	 * 		.classIndex : 0
	 */
	protected StatelessGuiSubitemTestObject table_htmlTable_0(TestObject anchor, long flags) 
	{
		return new StatelessGuiSubitemTestObject(
                        getMappedTestObject("table_htmlTable_0"), anchor, flags);
	}
	
	

	protected Script2Helper()
	{
		setScriptName("testscripts.Script2");
	}
	
}
