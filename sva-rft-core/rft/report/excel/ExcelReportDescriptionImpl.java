package rft.report.excel;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import rft.report.ReportConfiguration;
import rft.report.ReportConfiguration.Language;
import rft.report.ReportDescription;
import rft.report.ReportResource;
import rft.report.ReportResource.ResourceKey;
import rft.tookit.ExcelTookit;

/**
 * Excel Description Block Implement
 *
 * @author Bing Qu
 *
 */
public class ExcelReportDescriptionImpl extends ReportDescription {
	// Date Formatter
	private static final SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	private String sheetName;

	/**
	 * Constructor
	 *
	 * @param report
	 * @param sheetName
	 */
	ExcelReportDescriptionImpl(ExcelReportImpl report, String sheetName) {
		super.setReport(report);
		this.sheetName = sheetName;
	}

	@Override
	public void output() {
		ExcelReportImpl report = (ExcelReportImpl)getReport();
		int x = report.getCurrentRowNo(sheetName);
		ReportConfiguration config = super.getReport().getConfig();
		Language lang = config.getLanguage();

		HSSFWorkbook workbook = report.getBody();

		//Case Number
		if (!(getCaseNumber() == null || getCaseNumber().isEmpty())) {
			ExcelTookit.insertText(workbook, sheetName, x, 0, ReportResource.getResourceValue(ResourceKey.CaseNo, lang));
	
			ExcelTookit.insertText(workbook, sheetName, x, 1, getCaseNumber());
	
			x = report.addRow(sheetName, 1);
		}

		//Function Name
		if (!(getFunctionName() == null || getFunctionName().isEmpty())) {
			ExcelTookit.insertText(workbook, sheetName, x, 0, ReportResource.getResourceValue(ResourceKey.FunctionName, lang));
	
			ExcelTookit.insertText(workbook, sheetName, x, 1, getFunctionName());
	
			x = report.addRow(sheetName, 1);
		}

		//Descriptions
		if (!(getDescriptions() == null || getDescriptions().isEmpty())) {
			ExcelTookit.insertText(workbook, sheetName, x, 0, ReportResource.getResourceValue(ResourceKey.Description, lang));
	
			for (String desc : getDescriptions()) {
				ExcelTookit.insertText(workbook, sheetName, x, 1, desc);
				x = report.addRow(sheetName, 1);
			}
		}

		//Execution Date
		x = report.addRow(sheetName, 1);
		ExcelTookit.insertText(workbook, sheetName, x, 0, ReportResource.getResourceValue(ResourceKey.ExecutionDate, lang));
		ExcelTookit.insertText(workbook, sheetName, x, 1, DateFormat.format(new Date()));
		x = report.addRow(sheetName, 1);
	}

}
