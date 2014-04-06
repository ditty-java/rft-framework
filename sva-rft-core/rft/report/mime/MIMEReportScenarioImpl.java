/**
 * 
 */
package rft.report.mime;

import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import rft.report.ReportConfiguration;
import rft.report.ReportConfiguration.Language;
import rft.report.ReportResource;
import rft.report.ReportResource.ResourceKey;
import rft.report.ReportScenario;
import rft.tookit.ExcelTookit;

/**
 * 
 * @author Bing Qu
 *
 */
public class MIMEReportScenarioImpl extends ReportScenario {

	/**
	 * Constructor
	 */
	public MIMEReportScenarioImpl(MIMEReportImpl report) {
		setReport(report);
	}

	/**
	 * add the step into the step list
	 *
	 * @return step step
	 */
	public MIMEReportStepImpl addStep() {
		MIMEReportStepImpl step = new MIMEReportStepImpl((MIMEReportImpl)this.getReport());
		addStep(step);
		return step;
	}

	@Override
	public void output() throws IOException {
//		MIMEReportImpl report = (MIMEReportImpl)getReport();
//		int x = report.getCurrentRowNo(sheetName);
//		ReportConfiguration config = super.getReport().getConfig();
//		Language lang = config.getLanguage();
//
//		HSSFWorkbook workbook = report.getBody();
//
//		//Scenario
//		x = report.addRow(sheetName, 1);
//		ExcelTookit.insertText(workbook, sheetName, x, 0, ReportResource.getResourceValue(ResourceKey.Scenario, lang));
		
		//output the step contents
		super.output();
	}
}
