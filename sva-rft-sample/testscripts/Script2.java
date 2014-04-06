package testscripts;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import resources.testscripts.Script2Helper;
import rft.report.Report;
import rft.report.excel.ExcelReportImpl;
import rft.report.excel.ExcelReportScenarioImpl;
import rft.report.excel.ExcelReportStepImpl;
import rft.report.validator.RegexValidatorImpl;
import rft.report.validator.ReportValidator;
import rft.tookit.CSVTookit;
import rft.tookit.MiscUtils;

import com.rational.test.ft.object.interfaces.StatelessGuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
/**
 * Description   : Functional Test Script
 * @author Administrator
 */
public class Script2 extends Script2Helper
{
	/**
	 * Script Name   : <b>Script2</b>
	 * Generated     : <b>Mar 21, 2014 2:14:17 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/03/21
	 * @author Administrator
	 */
	public void testMain(Object[] args) 
	{
		try {
			ExcelReportImpl report = Report.createExcel(this);
			
			ExcelReportScenarioImpl scenario = report.createScenario("XXX01-1");
			ExcelReportStepImpl step = scenario.addStep();
			step.setStepDescription("XXX01-01");
			
			List<String[]> v1 = CSVTookit.importCsv("csv/Test01.csv");
			
			List<String[]> v2 = MiscUtils.loadHtmlTable(table_htmlTable_0());

			step.addGridValidation("html table/csv compare.", v1, v2, new ReportValidator[]{null, null, null, new RegexValidatorImpl()});
			
			try {
				report.output();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

