package rft.report.mime;

import java.text.DecimalFormat;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import rft.report.ReportConfiguration;
import rft.report.ReportConfiguration.Language;
import rft.report.ReportResource;
import rft.report.ReportResource.ResourceKey;
import rft.report.ReportResult;
import rft.tookit.ExcelTookit;
import rft.tookit.ExcelTookit.CellStyle;

/**
 * Excel Report Final Result Block Implement
 * 
 * @author Bing Qu
 *
 */
public class MIMEReportResultImpl extends ReportResult {
	// Decimal Formatter
	private static final DecimalFormat decimalFormat = new DecimalFormat("#,##0");

	/**
	 * Constructor
	 *
	 * @param report
	 */
	MIMEReportResultImpl(MIMEReportImpl report) {
		super.setReport(report);
	}

	@Override
	public void output() {
		MIMEReportImpl report = (MIMEReportImpl)getReport();
//		int x = report.addRow(2);
//		ReportConfiguration config = super.getReport().getConfig();
//		Language lang = config.getLanguage();
//
//		HSSFWorkbook workbook = report.getBody();
//
//		//output final result
//		ExcelTookit.insertText(workbook, 0, x, 0, ReportResource.getResourceValue(ResourceKey.TestResult, lang));
//		if (getFinalResult()) {
//			ExcelTookit.insertText(workbook, 0, x, 1, ReportResource.getResourceValue(ResourceKey.Successed, lang));
//		} else {
//			ExcelTookit.insertText(workbook, 0, x, 1, ReportResource.getResourceValue(ResourceKey.Failed, lang), CellStyle.ERROR);
//		}
//		x = report.addRow(1);
//
//		//output execution time
//		ExcelTookit.insertText(workbook, 0, x, 0, ReportResource.getResourceValue(ResourceKey.ExecutionTime, lang));
//		String executionTime = decimalFormat.format(getTotalMilliseconds() / 1000);
//		if (getFinalResult()) {
//			ExcelTookit.insertText(workbook, 0, x, 1, executionTime  + ReportResource.getResourceValue(ResourceKey.Seconds, lang));
//		} else {
//			ExcelTookit.insertText(workbook, 0, x, 1, executionTime  + ReportResource.getResourceValue(ResourceKey.Seconds, lang), CellStyle.ERROR);
//		}
//		x = report.addRow(1);
	}

}
