package rft.report.mime;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import rft.report.ReportConfiguration;
import rft.report.ReportResource;
import rft.report.ReportValidation;
import rft.report.ReportConfiguration.Language;
import rft.report.ReportResource.ResourceKey;
import rft.tookit.MIMETookit;

/**
 * MIME Variable Validation Implement
 * 
 * @author Bing Qu
 *
 */
public class MIMEVariableValidationImpl extends ReportValidation {

	/**
	 * Constructor
	 *
	 * @param report
	 * @param target
	 * @param expectValue
	 * @param actualValue
	 */
	MIMEVariableValidationImpl(MIMEReportImpl report, String target, String expectValue, String actualValue) {
		super.setReport(report);
		super.setTargetObject(target);
		super.setExpectValue(expectValue);
		super.setActualValue(actualValue);
	}
	
	@Override
	public void output() {
		MIMEReportImpl report = (MIMEReportImpl)getReport();
		ReportConfiguration config = super.getReport().getConfig();
		Language lang = config.getLanguage();
		StringBuilder buffer = report.getBody();

		buffer.append("<tr/>");
		buffer.append("<td width=3D\"80\">&nbsp;</td>\n\r");
		buffer.append("<td>");

		// Target Object
		buffer.append("<b>").append(ReportResource.getResourceValue(ResourceKey.TargetObject, lang)).append("</b>");
		buffer.append("&nbsp;:&nbsp;").append(getTargetObject());
		
		// Expect Value
		buffer.append("<b>").append(ReportResource.getResourceValue(ResourceKey.ExpectValue, lang)).append("</b>");
		buffer.append("&nbsp;:&nbsp;").append(getExpectValue());

		// Actual Value
		buffer.append("<b>").append(ReportResource.getResourceValue(ResourceKey.ActualValue, lang)).append("</b>");
		buffer.append("&nbsp;:&nbsp;").append(getActualValue());

		// Validation Result
		buffer.append("<b>").append(ReportResource.getResourceValue(ResourceKey.ValidationResult, lang)).append("</b>");
		buffer.append("&nbsp;:&nbsp;");
		if (isValid()) {
			buffer.append(ReportResource.getResourceValue(ResourceKey.OK, lang));
		} else {
			buffer.append("<font color=3D\"red\"").append(ReportResource.getResourceValue(ResourceKey.NG, lang)).append("</font>");
		}

		buffer.append("</td>\n\r");
		buffer.append("</tr>\r\n");
	}
}
