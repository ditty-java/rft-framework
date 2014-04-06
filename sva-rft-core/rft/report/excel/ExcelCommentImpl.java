package rft.report.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import rft.report.Comment;
import rft.tookit.ExcelTookit;

/**
 * Excel Comment Block Implement
 * 
 * @author Bing Qu
 */
public class ExcelCommentImpl extends Comment {

	private String sheetName;

	/**
	 * Constructor
	 *
	 * @param report
	 * @param contents
	 */
	ExcelCommentImpl(ExcelReportImpl report, String sheetName, String... contents) {
		super(contents);
		super.setReport(report);
		this.sheetName = sheetName;
	}

	@Override
	public void output() {
		ExcelReportImpl report = (ExcelReportImpl)getReport();
		int x = report.getCurrentRowNo(sheetName);

		HSSFWorkbook workbook = report.getBody();

		//comment output.
		for (String content : getLines()) {
			ExcelTookit.insertText(workbook, sheetName, x, 1, content);
			x = report.addRow(sheetName, 1);
		}
	}
}
