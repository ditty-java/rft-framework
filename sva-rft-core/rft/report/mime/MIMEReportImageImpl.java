package rft.report.mime;

import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import rft.report.Outputable;
import rft.report.ReportImage;
import rft.tookit.ExcelTookit;
import rft.tookit.MIMETookit;

/**
 * Excel Report Image Block Implement
 * 
 * @author Bing Qu
 *
 */
public class MIMEReportImageImpl extends ReportImage implements Outputable {
	// Virtual File Name used in MHTML
	private String imgName;

	/**
	 * Constructor
	 *
	 * @param report
	 */
	MIMEReportImageImpl(MIMEReportImpl report) {
		super.setReport(report);
		imgName = System.currentTimeMillis() + ".jpg";
		report.addImageResource(this);
	}

	@Override
	public void output() throws IOException {
		// obtain the current row number
		MIMEReportImpl report = (MIMEReportImpl)getReport();
		StringBuilder buffer = report.getBody();
		buffer.append(MIMETookit.generateImageTag(imgName));
		buffer.append("\r\n");
	}

	/**
	 * Output the image by MIME encoding
	 *
	 * @throws IOException
	 */
	public void outputResource() throws IOException {
		MIMEReportImpl report = (MIMEReportImpl)getReport();
		StringBuilder buffer = report.getBody();
		buffer.append(MIMETookit.generateImageResource(imgName, getImage(), 1));
		buffer.append("\r\n");
	}
}
