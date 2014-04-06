package appobjects;

import java.awt.Image;

import resources.appobjects.O_XXX02Helper;
import rft.report.Report;
import rft.report.excel.ExcelReportDescriptionImpl;
import rft.report.excel.ExcelReportImpl;
import rft.report.excel.ExcelReportScenarioImpl;
import rft.report.excel.ExcelReportStepImpl;
import rft.tookit.BrowserOps;
import rft.tookit.ScreenCapture;
import rft.widgets.WBrowser;
import rft.widgets.WLink;
import rft.widgets.WStaticText;
import rft.widgets.WTextField;
import rft.widgets.ancestors.Widget;

import com.rational.test.ft.object.interfaces.TestObject;

public class O_XXX02 extends O_XXX02Helper {

	public WBrowser getBrowser_htmlBrowser() {
		TestObject to = browser_htmlBrowser(ANY, NO_STATE);
		return new WBrowser(to);
	}

	public WTextField getFrame_main() {
		TestObject to = frame_main(ANY, NO_STATE);
		return new WTextField(to);
	}

	public WStaticText getFrame_menu() {
		TestObject to = frame_menu(ANY, NO_STATE);
		return new WStaticText(to);
	}

	public WStaticText getHtml_header_content() {
		TestObject to = html_header_content(ANY, NO_STATE);
		return new WStaticText(to);
	}

	public WStaticText getHtml_ibody() {
		TestObject to = html_ibody(ANY, NO_STATE);
		return new WStaticText(to);
	}

	public WStaticText getHtml_node_0() {
		TestObject to = html_node_0(ANY, NO_STATE);
		return new WStaticText(to);
	}

	public WLink getLink_ls_fastloginfield_ctrl() {
		TestObject to = link_ls_fastloginfield_ctrl(ANY, NO_STATE);
		return new WLink(to);
	}

	public WLink getLink_天健慈善() {
		TestObject to = link_天健慈善(ANY, NO_STATE);
		return new WLink(to);
	}

	public Widget getObject_htmlOBJECT() {
		TestObject to = object_htmlOBJECT(ANY, NO_STATE);
		return new Widget(to);
	}

	// --------------------------------------------------------------------------

	public void testMain(Object[] args) 
	{

		//create report
		ExcelReportImpl report = Report.createExcel(this);
		
		//set description
		ExcelReportDescriptionImpl reportDesc = report.getDescription();
		reportDesc.setCaseNumber("XXX02");
		reportDesc.setFunctionName("RUNSKYのスクロール操作");
		reportDesc.addDescription("メニューのフレームにクリックし");
		reportDesc.addDescription("スクロールダウン操作を実行する。");
		reportDesc.addDescription("メインフレームにクリックし");
		reportDesc.addDescription("スクロールダウン操作を実行する。");

		//close all IE
		BrowserOps.closeAllBrowsers();

		//step 1
		ExcelReportScenarioImpl scenario = report.createScenario("1");
		ExcelReportStepImpl step1 = scenario.addStep();
		step1.setStepDescription("XXX02-01");
		step1.addComment("「bbs.runsky.com」でブラウザを開く。");

		BrowserOps.startBrowser("bbs.runsky.com");
		BrowserOps.waitForReady();
		browser_htmlBrowser().activate();
		Image image = ScreenCapture.captureScreen(browser_htmlBrowser());
		step1.addImage(image);

		//step 2
		ExcelReportStepImpl step2 = scenario.addStep();
		step2.setStepDescription("XXX02-02");
		step2.addComment("マインフレームのスクロールダウンを実行する");
		html_header_content().click();
		browser_htmlBrowser(frame_main(),DEFAULT_FLAGS).inputKeys("{ExtPgDn}");
		image = ScreenCapture.captureScreen(browser_htmlBrowser());
		step2.addImage(image);
		browser_htmlBrowser(frame_main(),DEFAULT_FLAGS).inputKeys("{ExtPgDn}");
		image = ScreenCapture.captureScreen(browser_htmlBrowser());
		step2.addImage(image);
		browser_htmlBrowser(frame_main(),DEFAULT_FLAGS).inputKeys("{ExtPgDn}");
		image = ScreenCapture.captureScreen(browser_htmlBrowser());
		step2.addImage(image);
		browser_htmlBrowser(frame_main(),DEFAULT_FLAGS).inputKeys("{ExtPgDn}");
		image = ScreenCapture.captureScreen(browser_htmlBrowser());
		step2.addImage(image);
		browser_htmlBrowser(frame_main(),DEFAULT_FLAGS).inputKeys("{ExtPgDn}");
		image = ScreenCapture.captureScreen(browser_htmlBrowser());
		step2.addImage(image);
		browser_htmlBrowser(frame_main(),DEFAULT_FLAGS).inputKeys("{ExtPgDn}");
		image = ScreenCapture.captureScreen(browser_htmlBrowser());
		step2.addImage(image);
		browser_htmlBrowser(frame_main(),DEFAULT_FLAGS).inputKeys("{ExtPgDn}");
		image = ScreenCapture.captureScreen(browser_htmlBrowser());
		step2.addImage(image);
		
		//step 3
		ExcelReportStepImpl step3 = scenario.addStep();
		step3.setStepDescription("XXX02-03");
		step3.addComment("メニューフレームのスクロールダウンを実行する");
		frame_menu().click(atPoint(135,6));
		
		image = ScreenCapture.captureScreen(browser_htmlBrowser());
		step3.addImage(image);
		browser_htmlBrowser(frame_menu(),DEFAULT_FLAGS).inputKeys("{ExtPgDn}");
		image = ScreenCapture.captureScreen(browser_htmlBrowser());
		step3.addImage(image);

		//step 4
		ExcelReportStepImpl step4 = scenario.addStep();
		step4.setStepDescription("XXX02-04");
		step4.addComment("「天健慈善」のリンクをクリックする");
		link_天健慈善().click();
		BrowserOps.waitForReady();
		image = ScreenCapture.captureScreen(browser_htmlBrowser());
		step4.addImage(image);
		
		//step 5
		ExcelReportStepImpl step5 = scenario.addStep();
		step5.setStepDescription("XXX02-05");
		step5.addComment("「天健慈善」の内容をするロールダウンする。");
		link_ls_fastloginfield_ctrl().click();
		BrowserOps.waitForReady();
		image = ScreenCapture.captureScreen(browser_htmlBrowser());
		step5.addImage(image);
		browser_htmlBrowser(frame_main(),DEFAULT_FLAGS).inputKeys("{ExtPgDn}");
		image = ScreenCapture.captureScreen(browser_htmlBrowser());
		step5.addImage(image);
		browser_htmlBrowser(frame_main(),DEFAULT_FLAGS).inputKeys("{ExtPgDn}");
		image = ScreenCapture.captureScreen(browser_htmlBrowser());
		step5.addImage(image);
		browser_htmlBrowser(frame_main(),DEFAULT_FLAGS).inputKeys("{ExtPgDn}");
		image = ScreenCapture.captureScreen(browser_htmlBrowser());
		step5.addImage(image);
		browser_htmlBrowser(frame_main(),DEFAULT_FLAGS).inputKeys("{ExtPgDn}");
		image = ScreenCapture.captureScreen(browser_htmlBrowser());
		step5.addImage(image);

		//output report
		try {
			report.output();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
