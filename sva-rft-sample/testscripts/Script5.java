package testscripts;
import resources.testscripts.Script5Helper;
import rft.tookit.MiscUtils;
import tasks.DPTest;

import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.WPF.*;
import com.rational.test.ft.object.interfaces.dojo.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.object.interfaces.generichtmlsubdomain.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;
/**
 * Description   : Functional Test Script
 * @author Administrator
 */
public class Script5 extends Script5Helper
{
	/**
	 * Script Name   : <b>Script5</b>
	 * Generated     : <b>Mar 26, 2014 3:28:03 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/03/26
	 * @author Administrator
	 */
	public void testMain(Object[] args) 
	{
		DPTest test = new DPTest();
		MiscUtils.prepareDataPool(test);
		test.test();
	}
}

