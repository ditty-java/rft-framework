package tasks;
import java.io.File;

import resources.tasks.T_XXX02Helper;
import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.WPF.*;
import com.rational.test.ft.object.interfaces.dojo.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.object.interfaces.generichtmlsubdomain.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.util.OptionManager;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;
/**
 * Description   : Functional Test Script
 * @author Administrator
 */
public class T_XXX02 extends T_XXX02Helper
{
	public String dpTest(String vName) {
		String datapoolName = this.getScriptDefinition().getDatapoolName();
	    boolean hasDp = datapoolName != null;
	    if (hasDp)
	    {
	        this.dpInitialization(new File(OptionManager.getString("rt.datastore"), 
	          datapoolName), 
	          -1, 
	          this.getScriptDefinition().getDatapoolIteratorClassName());
	    }
		return dpString(vName);
	}
	/**
	 * Script Name   : <b>tSample2</b>
	 * Generated     : <b>Mar 6, 2014 3:24:02 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/03/06
	 * @author Administrator
	 */
	public void testMain(Object[] args) 
	{
		System.out.println("v1@T_XXX02:" + this.dpTest("V1"));
	}
}

