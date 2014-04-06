package rft.report.mime;

import rft.report.Comment;
import rft.report.ReportConfiguration;
import rft.report.ReportConfiguration.Language;
import rft.report.ReportResource;
import rft.report.ReportResource.ResourceKey;

/**
 * Excel Comment Block Implement
 * 
 * @author Bing Qu
 */
public class MIMECommentImpl extends Comment {

	/**
	 * Constructor
	 *
	 * @param report
	 * @param contents
	 */
	MIMECommentImpl(MIMEReportImpl report, String... contents) {
		super(contents);
		super.setReport(report);
	}

	@Override
	public void output() {
		MIMEReportImpl report = (MIMEReportImpl)getReport();
		StringBuilder buffer = report.getBody();

		// Comment Line
		buffer.append("<tr/>");
		buffer.append("<td>&nbsp;</td>\n\r");
		buffer.append("<td>");
		for (String commentLine : this.getLines()) {
			buffer.append(commentLine).append("<br/>");
		}
		buffer.append("</td>\n\r");
		buffer.append("</tr>\r\n");
	}
}
