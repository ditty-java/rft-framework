/**
 * 
 */
package rft.report.excel;

import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
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
public class ExcelReportScenarioImpl extends ReportScenario {

	/**
	 * Constructor
	 */
	public ExcelReportScenarioImpl(ExcelReportImpl report, String sheetName) {
		setReport(report);
		setScenarioName(sheetName);
	}

	/**
	 * add the step into the step list
	 *
	 * @return step step
	 */
	public ExcelReportStepImpl addStep() {
		ExcelReportStepImpl step = new ExcelReportStepImpl(this.getReport(), getScenarioName());
		addStep(step);
		return step;
	}

	@Override
	public void output() throws IOException {
		ExcelReportImpl report = (ExcelReportImpl)getReport();
		int x = report.getCurrentRowNo(getScenarioName());
		ReportConfiguration config = super.getReport().getConfig();
		Language lang = config.getLanguage();

		HSSFWorkbook workbook = report.getBody();
		HSSFSheet sheet = workbook.createSheet(getScenarioName());
		sheet.setColumnBreak(8);
		for (int columnIndex = ExcelReportImpl.COLUMN_BEGIN; columnIndex < ExcelReportImpl.COLUMN_END; columnIndex ++) {
			ExcelTookit.setCellWeight(workbook, getScenarioName(), columnIndex, ExcelReportImpl.COLUMN_WIDTH);
		}
		
		//Scenario
		x = report.addRow(getScenarioName(), 1);
		ExcelTookit.insertText(workbook, getScenarioName(), x, 0, ReportResource.getResourceValue(ResourceKey.Scenario, lang));
		
		//output the step contents
		super.output();
	}
}
