package testscripts;
import java.io.IOException;

import resources.testscripts.TS_XXX01Helper;
import rft.report.Report;
import rft.report.excel.ExcelReportDescriptionImpl;
import rft.report.excel.ExcelReportImpl;
import rft.tookit.BrowserOps;
import tasks.T_XXX01;
/**
 * Description   : Functional Test Script
 * @author Administrator
 */
public class TS_XXX01 extends TS_XXX01Helper
{
	/**
	 * Script Name   : <b>TS_Sample1</b>
	 * Generated     : <b>Mar 6, 2014 3:23:11 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/03/06
	 * @author Administrator
	 * @throws IOException 
	 */
	public void testMain (Object[] args) throws IOException 
	{
		//create report
		ExcelReportImpl report = Report.createExcel(this);
		
		//set description
		ExcelReportDescriptionImpl reportDesc = report.getDescription();
		reportDesc.setCaseNumber("XXX01");
		reportDesc.setFunctionName("BAIDUの検索");
		reportDesc.addDescription("BAIDUで「IBM」を検索し、");
		reportDesc.addDescription("検索結果に、IBMのホームページのリンクを見つけて、");
		reportDesc.addDescription("BMのホームページに入る。");

		//close all IE
		BrowserOps.closeAllBrowsers();

		T_XXX01 task = new T_XXX01();
		//step 1
		task.step_XXX01_01(report);
		
		//step 2
		task.step_XXX01_02(report);

		//step 3
		task.step_XXX01_03(report);
		
		//output report
		try {
			report.output();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

