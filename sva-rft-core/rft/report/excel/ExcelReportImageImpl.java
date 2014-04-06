package rft.report.excel;

import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import rft.report.Outputable;
import rft.report.ReportImage;
import rft.tookit.ExcelTookit;

/**
 * Excel Report Image Block Implement
 * 
 * @author Bing Qu
 *
 */
public class ExcelReportImageImpl extends ReportImage implements Outputable {

	private String sheetName;

	/**
	 * Constructor
	 *
	 * @param report
	 */
	ExcelReportImageImpl(ExcelReportImpl report, String sheetName) {
		super.setReport(report);
		this.sheetName = sheetName;
	}

	@Override
	public void output() throws IOException {
		// obtain the current row number
		ExcelReportImpl report = (ExcelReportImpl)getReport();
		int x = report.getCurrentRowNo(sheetName);

		HSSFWorkbook workbook = report.getBody();

		// output step description
		ExcelTookit.insertImage(workbook, sheetName, x, 1, getImage(), 0.75);
		x = report.addRow(sheetName, 50);
	}
}
