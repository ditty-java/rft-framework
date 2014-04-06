package tasks;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import resources.tasks.T_XXX01Helper;
import rft.report.excel.ExcelReportImpl;
import rft.report.excel.ExcelReportScenarioImpl;
import rft.report.excel.ExcelReportStepImpl;
import rft.report.mime.MIMEReportImpl;
import rft.report.mime.MIMEReportScenarioImpl;
import rft.report.mime.MIMEReportStepImpl;
import rft.report.validator.EqualValidatorImpl;
import rft.report.validator.RegexValidatorImpl;
import rft.report.validator.ReportValidator;
import rft.tookit.BrowserOps;
import rft.tookit.CSVTookit;
import rft.tookit.ImageTookit;
import rft.tookit.ScreenCapture;
import rft.widgets.WBrowser;
import rft.widgets.WButton;
import rft.widgets.WTextField;
import appobjects.O_XXX01;

/**
 * Description   : Functional Test Script
 * @author Administrator
 */
public class T_XXX01 extends T_XXX01Helper
{
	/**
	 * step XXX01_01
	 *
	 * @param report
	 * @throws IOException 
	 */
	public void step_XXX01_01(ExcelReportImpl report) throws IOException {
		ExcelReportScenarioImpl scenario = report.createScenario("XXX01-1");
		ExcelReportStepImpl step = scenario.addStep();
		step.setStepDescription("XXX01-01");
		step.addComment("「www.baidu.com」でブラウザを開く。");	
		
		O_XXX01 appObj = new O_XXX01();
		
		//startup IE with www.baidu.com
		BrowserOps.startBrowser("www.baidu.com");
		BrowserOps.waitForReady();
		WBrowser ie = appObj.getBrowser_htmlBrowser();
		WTextField txt = appObj.getText_wd();
		WButton button = appObj.getButton_search_submit();
		//txt.setText("IBM中国カナ");
		appObj.text_wd().setText("IBM中国カナ");
		ie.activate();
		Image image = ScreenCapture.captureScreen(ie);
		image = ImageTookit.highlightTarget(image, ie, txt, Color.RED, 5);
		image = ImageTookit.highlightTarget(image, ie, button, Color.BLUE, 5);

		step.addImage(image);
		
		List<String[]> v1 = CSVTookit.importCsv("csv/Test01.csv");
		
		List<String[]> v2 = new ArrayList<>();
		v2.add(new String[]{"A", "B", "cC"});
		v2.add(new String[]{"AA", "BBB", "CC"});
		v2.add(new String[]{"aAA", "BBB", "CCC"});

		step.addGridValidation("grid", v1, v2, new ReportValidator[]{null, null, new RegexValidatorImpl()});
		
		step.addVariableValidation("xxx1", "A", "a", new EqualValidatorImpl(true));
		
		step.addVariableValidation("xxx2", "AA", "Aa");
	}
	/**
	 * step XXX01_01
	 *
	 * @param report
	 */
	public void step_XXX01_01(MIMEReportImpl report) {
		MIMEReportScenarioImpl scenario = report.createScenario();
		MIMEReportStepImpl step = scenario.addStep();
		step.setStepDescription("XXX01-01");
		step.addComment("「www.baidu.com」でブラウザを開く。");	
		
		O_XXX01 appObj = new O_XXX01();
		
		//startup IE with www.baidu.com
		BrowserOps.startBrowser("www.baidu.com");
		BrowserOps.waitForReady();
		appObj.getBrowser_htmlBrowser().activate();
		Image image = ScreenCapture.captureScreen(appObj.getBrowser_htmlBrowser());
		image = ImageTookit.highlightTarget(image, appObj.getBrowser_htmlBrowser(), appObj.getText_wd(), Color.RED, 2);
		step.addImage(image);
	}	
	/**
	 * step XXX01_02
	 *
	 * @param report
	 */
	public void step_XXX01_02(ExcelReportImpl report) {
		ExcelReportScenarioImpl scenario = report.createScenario("XXX01-2");
		ExcelReportStepImpl step2 = scenario.addStep();
		step2.setStepDescription("XXX01-02");
		step2.addComment("「IBM」を入力して、検索ボタンを押下する。");
		
		O_XXX01 appObj = new O_XXX01();
		
		step2.addComment("操作前");
		Image image = ScreenCapture.captureScreen(appObj.getBrowser_htmlBrowser());
		step2.addImage(image);
		appObj.getText_wd().setText("IBM");
		image = ScreenCapture.captureScreen(appObj.getBrowser_htmlBrowser());
		image = ImageTookit.highlightTarget(image, appObj.getBrowser_htmlBrowser(), appObj.getText_wd(), Color.RED, 2);
		
		step2.addComment("操作後");
		step2.addImage(image);
	}
	public void step_XXX01_02(MIMEReportImpl report) {
		MIMEReportScenarioImpl scenario = report.createScenario();
		MIMEReportStepImpl step2 = scenario.addStep();
		step2.setStepDescription("XXX01-02");
		step2.addComment("「IBM」を入力して、検索ボタンを押下する。");
		
		O_XXX01 appObj = new O_XXX01();
		WBrowser ie = appObj.getBrowser_htmlBrowser();
		WTextField txt = appObj.getText_wd();
		step2.addComment("操作前");
		Image image = ScreenCapture.captureScreen(ie);
		step2.addImage(image);
		txt.setText("IBM");
		image = ScreenCapture.captureScreen(ie);
		image = ImageTookit.highlightTarget(image, ie, txt, Color.RED, 2);
		step2.addComment("操作後");
		step2.addImage(image);
	}
	/**
	 * step XXX01_03
	 *
	 * @param report
	 */
	public void step_XXX01_03(ExcelReportImpl report) {
		ExcelReportScenarioImpl scenario = report.createScenario("XXX01-3");
		ExcelReportStepImpl step3 = scenario.addStep();
		step3.setStepDescription("XXX01-03");
		step3.addComment("検索ボタンを押下する。");

		O_XXX01 appObj = new O_XXX01();
		
		step3.addComment("操作前");
		Image image = ScreenCapture.captureScreen(appObj.getBrowser_htmlBrowser());
		step3.addImage(image);
		appObj.getButton_search_submit().click();
		BrowserOps.waitForReady();
		sleep(1);
		image = ScreenCapture.captureScreen(appObj.getBrowser_htmlBrowser());
		step3.addComment("操作後");
		step3.addImage(image);
		
		step3.addVariableValidation("検索入力欄", "IBM", appObj.getText_wd2().getText());
	}
	public void step_XXX01_03(MIMEReportImpl report) {
		MIMEReportScenarioImpl scenario = report.createScenario();
		MIMEReportStepImpl step3 = scenario.addStep();
		step3.setStepDescription("XXX01-03");
		step3.addComment("検索ボタンを押下する。");

		O_XXX01 appObj = new O_XXX01();
		
		step3.addComment("操作前");
		Image image = ScreenCapture.captureScreen(appObj.getBrowser_htmlBrowser());
		step3.addImage(image);
		appObj.getButton_search_submit().click();
		BrowserOps.waitForReady();
		sleep(1);
		image = ScreenCapture.captureScreen(appObj.getBrowser_htmlBrowser());
		step3.addComment("操作後");
		step3.addImage(image);
		
		step3.addVariableValidation("検索入力欄", "IBM", appObj.getText_wd2().getText());
	}

	/**
	 * Script Name   : <b>tSample1</b>
	 * Generated     : <b>Mar 6, 2014 3:23:52 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/03/06
	 * @author Administrator
	 */
	public void testMain(Object[] args) 
	{
		//NOP
	}
}

