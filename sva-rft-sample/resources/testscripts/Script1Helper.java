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
 * Script Name   : <b>Script1</b><br>
 * Generated     : <b>2014/03/11 2:41:24 PM</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows 7 x86 6.1 <br>
 * 
 * @since  March 11, 2014
 * @author Administrator
 */
public abstract class Script1Helper extends RationalTestScript
{
	/**
	 * wd: with default state.
	 *		.id : kw1
	 * 		.type : text
	 * 		.title : 
	 * 		.class : Html.INPUT.text
	 * 		.name : wd
	 * 		.classIndex : 0
	 */
	protected TextGuiTestObject text_wd() 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("text_wd"));
	}
	/**
	 * wd: with specific test context and state.
	 *		.id : kw1
	 * 		.type : text
	 * 		.title : 
	 * 		.class : Html.INPUT.text
	 * 		.name : wd
	 * 		.classIndex : 0
	 */
	protected TextGuiTestObject text_wd(TestObject anchor, long flags) 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("text_wd"), anchor, flags);
	}
	
	/**
	 * Locate and return the verification point wd_standard object in the SUT.
	 */
	protected IFtVerificationPoint wd_standardVP() 
	{
		return vp("wd_standard");
	}
	protected IFtVerificationPoint wd_standardVP(TestObject anchor)
	{
		return vp("wd_standard", anchor);
	}
	
	

	protected Script1Helper()
	{
		setScriptName("testscripts.Script1");
	}
	
}

