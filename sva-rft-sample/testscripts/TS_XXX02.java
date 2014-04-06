package testscripts;
import tasks.T_XXX02;
import resources.testscripts.TS_XXX02Helper;
import rft.tookit.MiscUtils;

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
public class TS_XXX02 extends TS_XXX02Helper
{
	/**
	 * Script Name   : <b>TS_Sample2</b>
	 * Generated     : <b>Mar 6, 2014 3:23:39 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/03/06
	 * @author Administrator
	 */
	public void testMain(Object[] args) 
	{
		//System.out.println("v1@TS_XXX02:" + dpString("V1"));
		//System.out.println("v1@TS_XXX02:" + dpString("V1"));
		T_XXX02 t2 = new T_XXX02();
		MiscUtils.prepareDataPool(t2);
		//t2.testMain(new Object[0]);
		System.out.println("v1@T_XXX02:" + t2.dpTest("V1"));
		
		
	}
}

