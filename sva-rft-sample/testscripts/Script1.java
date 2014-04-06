package testscripts;

import java.io.File;
import java.io.IOException;
import java.util.List;

import resources.testscripts.Script1Helper;
import rft.report.Report;
import rft.report.excel.ExcelReportDescriptionImpl;
import rft.report.excel.ExcelReportImpl;
import rft.report.mime.MIMEReportDescriptionImpl;
import rft.report.mime.MIMEReportImpl;
import rft.tookit.BrowserOps;
import rft.tookit.CSVTookit;
import tasks.T_XXX01;

/**
 * Description : Functional Test Script
 * 
 * @author Administrator
 */
public class Script1 extends Script1Helper {
	/**
	 * Script Name : <b>Script1</b> Generated : <b>Mar 11, 2014 11:15:29 AM</b>
	 * Description : Functional Test Script Original Host : WinNT Version 6.1
	 * Build 7601 (S)
	 * 
	 * @since 2014/03/11
	 * @author Administrator
	 */
	public void testMain(Object[] args) {
		//testMIME();

		testExcel();
	}

	private void testExcel() {
//		File file = new File("csv/Test01.csv");
//		System.out.println(">>" + file.getAbsolutePath());
//		try {
//			List<String[]> list = CSVTookit.importCsv("csv/Test01.csv");
//			System.out.println(">>" + list.size());
//		} catch (IOException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
		
		// create report
		ExcelReportImpl report = Report.createExcel(this);

		// set description
		ExcelReportDescriptionImpl reportDesc = report.getDescription();
		reportDesc.setCaseNumber("XXX01");
		reportDesc.setFunctionName("BAIDUの検索");
		reportDesc.addDescription("BAIDUで「IBM」を検索し、");
		reportDesc.addDescription("検索結果に、IBMのホームページのリンクを見つけて、");
		reportDesc.addDescription("BMのホームページに入る。");

		// close all IE
		BrowserOps.closeAllBrowsers();

		T_XXX01 task = new T_XXX01();
		// step 1
		try {
			task.step_XXX01_01(report);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

//		// step 2
//		task.step_XXX01_02(report);
//
//		 //step 3
//		 task.step_XXX01_03(report);

		// output report
		try {
			report.output();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void testMIME() {
		// create report
		MIMEReportImpl report = Report.createMIME(this);

		// set description
		MIMEReportDescriptionImpl reportDesc = report.getDescription();
		reportDesc.setCaseNumber("XXX01");
		reportDesc.setFunctionName("BAIDUの検索");
		reportDesc.addDescription("BAIDUで「IBM」を検索し、");
		reportDesc.addDescription("検索結果に、IBMのホームページのリンクを見つけて、");
		reportDesc.addDescription("BMのホームページに入る。");

		// close all IE
		BrowserOps.closeAllBrowsers();

		T_XXX01 task = new T_XXX01();
		// step 1
		task.step_XXX01_01(report);

		// step 2
		task.step_XXX01_02(report);

		// //step 3
		// task.step_XXX01_03(report);

		// output report
		try {
			report.output();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
