package rft.report.mime;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import rft.report.ReportConfiguration;
import rft.report.ReportConfiguration.Language;
import rft.report.ReportDescription;
import rft.report.ReportResource;
import rft.report.ReportResource.ResourceKey;
import rft.tookit.MIMETookit;

/**
 * MIME Description Block Implement
 *
 * @author Bing Qu
 *
 */
public class MIMEReportDescriptionImpl extends ReportDescription {
	// Date Formatter
	private static final SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	/**
	 * Constructor
	 *
	 * @param report
	 */
	MIMEReportDescriptionImpl(MIMEReportImpl report) {
		super.setReport(report);
	}

	@Override
	public void output() {
		MIMEReportImpl report = (MIMEReportImpl)getReport();
		ReportConfiguration config = super.getReport().getConfig();
		Language lang = config.getLanguage();
		StringBuilder buffer = report.getBody();

		//Case Number
		buffer.append("<table>\n\r");
		buffer.append("<tr>\n\r");
		buffer.append("<td width=3D\"15%\"><b>").append(ReportResource.getResourceValue(ResourceKey.CaseNo, lang)).append("</b></td>\n\r");
		buffer.append("<td width=3D\"85%\">").append(getCaseNumber()).append("</td>");
		buffer.append("</tr>\n\r");

		// Function Name
		buffer.append("<tr>\n\r");
		buffer.append("<td><b>").append(ReportResource.getResourceValue(ResourceKey.FunctionName, lang)).append("</b></td>\n\r");
		buffer.append("<td>").append(getFunctionName()).append("</td>");
		buffer.append("</tr>\n\r");

		// Descriptions
		if (!getDescriptions().isEmpty()) {
			buffer.append("<tr>\n\r");
			buffer.append("<td><b>").append(ReportResource.getResourceValue(ResourceKey.CaseNo, lang)).append("</b></td>\n\r");
			for (String descComment : getDescriptions()) {
				buffer.append("<td>").append(descComment).append("</td>");
			}
			buffer.append("</tr>\n\r");
		}
		buffer.append("</table>\n\r");

		buffer.append("<br/>\n\r");
		buffer.append("<br/>\n\r");

		// Execution Date
		buffer.append("<table>\n\r");
		buffer.append("<tr>\n\r");
		buffer.append("<td><b>").append(ReportResource.getResourceValue(ResourceKey.ExecutionDate, lang)).append("</b></td>\n\r");
		buffer.append("<td>").append(DateFormat.format(new Date())).append("</td>\n\r");
		buffer.append("</tr>\n\r");
		buffer.append("</table>\n\r");
		
		// Scenarios
		buffer.append("<table>\n\r");
		buffer.append("<tr>\n\r");
		buffer.append("<td colspan=3D\"2\"><b>").append(ReportResource.getResourceValue(ResourceKey.Scenario, lang)).append("</b></td>\n\r");
		buffer.append("</tr>\n\r");
		
	}

}
