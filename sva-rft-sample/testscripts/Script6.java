package testscripts;
import resources.testscripts.Script6Helper;
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
public class Script6 extends Script6Helper
{
	/**
	 * Script Name   : <b>Script6</b>
	 * Generated     : <b>Mar 27, 2014 3:54:58 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/03/27
	 * @author Administrator
	 */
	public void testMain(Object[] args) 
	{
		System.out.println("begin.");
		System.out.println(System.currentTimeMillis()/1000);
		for (int i = 0; i < 10; i ++) {
			RationalTestScript.logInfo("sleep for 5 seconds.");
			RationalTestScript.sleep(5);
			System.out.println(System.currentTimeMillis()/1000);
		}
		System.out.println("end.");
	}
}

