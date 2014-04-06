package tasks;
import java.io.File;

import resources.tasks.DPTestHelper;

import com.rational.test.ft.util.OptionManager;
/**
 * Description   : Functional Test Script
 * @author Administrator
 */
public class DPTest extends DPTestHelper
{
	//dynamic block
//	{
//	    String datapoolName = this.getScriptDefinition().getDatapoolName();
//	    boolean hasDp = datapoolName != null;
//	    if (hasDp)
//	    {
//	        this.dpInitialization(new File(OptionManager.getString("rt.datastore"), 
//	          datapoolName), 
//	          -1, 
//	          this.getScriptDefinition().getDatapoolIteratorClassName());
//	        this.dpInitialize(getDatapool(), -1, 1);
//	        this.getDatapool().getDefaultEquivalenceClassIndex();
//	    }
//	}

	public void test() 
	{
		System.out.println(this.dpString("V1"));
	}

	/**
	 * Script Name   : <b>DPTest</b>
	 * Generated     : <b>Mar 26, 2014 3:26:30 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/03/26
	 * @author Administrator
	 */
	public void testMain(Object[] args) 
	{
		System.out.println(this.dpString("V1"));
	}
}

