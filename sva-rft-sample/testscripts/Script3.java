package testscripts;

import java.io.IOException;

import resources.testscripts.Script3Helper;
import rft.report.Report;
import rft.report.excel.ExcelReportImpl;
import rft.report.excel.ExcelReportScenarioImpl;
import rft.report.excel.ExcelReportStepImpl;
import rft.tookit.ScreenCapture;

import com.rational.test.ft.object.interfaces.BrowserTestObject;
import com.rational.test.ft.object.interfaces.DocumentTestObject;

/**
 * Description : Functional Test Script
 * 
 * @author Administrator
 */
public class Script3 extends Script3Helper {
	/**
	 * Script Name : <b>Script3</b> Generated : <b>Mar 25, 2014 12:27:53 PM</b>
	 * Description : Functional Test Script Original Host : WinNT Version 6.1
	 * Build 7601 (S)
	 * 
	 * @since 2014/03/25
	 * @author Administrator
	 */
	public void testMain(Object[] args) {
		ExcelReportImpl report = Report.createExcel(this);

		ExcelReportScenarioImpl scenario = report.createScenario("XXX01-1");
		ExcelReportStepImpl step = scenario.addStep();
		step.setStepDescription("XXX01-01");
		BrowserTestObject browser = this.browser_htmlBrowser();
		DocumentTestObject scrollTarget = this.document_hello();
		step.addImages(ScreenCapture.captureMultipleScreens(browser, scrollTarget));
		try {
			report.output();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
