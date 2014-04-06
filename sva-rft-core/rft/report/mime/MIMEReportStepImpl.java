package rft.report.mime;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import rft.report.ReportConfiguration;
import rft.report.ReportConfiguration.Language;
import rft.report.ReportResource;
import rft.report.ReportResource.ResourceKey;
import rft.report.ReportStep;
import rft.report.ReportValidation;
import rft.tookit.MiscUtils;

/**
 * Excel Report Step Block Implement
 * 
 * @author Bing Qu
 *
 */
public class MIMEReportStepImpl extends ReportStep {

	// validation list
	private List<ReportValidation> validations = new ArrayList<>();
	
	/**
	 * Constructor
	 *
	 * @param report
	 */
	MIMEReportStepImpl(MIMEReportImpl report) {
		super.setReport(report);
	}

	/**
	 * @see rft.report.ReportStep#addComment(String)
	 */
	public void addComment(String comment) {
		addContent(new MIMECommentImpl((MIMEReportImpl)getReport(), comment));
	}

	/**
	 * @see rft.report.ReportStep#addImage()
	 */
	public void addImage() {
		Image image = MiscUtils.getSysClipboardImage();
		addImage(image);
	}

	/**
	 * @see rft.report.ReportStep#addImage(Image)
	 */
	public void addImage(Image image) {
		MIMEReportImageImpl imageContent = new MIMEReportImageImpl((MIMEReportImpl)getReport());
		imageContent.setImage(image);
		addContent(imageContent);
	}

	/**
	 * @see rft.report.ReportStep#addVariableValidation(String, String, String)
	 */
	public void addVariableValidation(String target, String exceptValue, String actualValue) {
		MIMEVariableValidationImpl validation = new MIMEVariableValidationImpl((MIMEReportImpl)getReport(), target, exceptValue, actualValue);
		addContent(validation);
		validations.add(validation);
	}

	@Override
	public boolean isValid() {
		for (ReportValidation validation : validations) {
			if (!validation.isValid()) {
				return false;
			}
		}

		return true;
	}

	@Override
	public void output() throws IOException {
		MIMEReportImpl report = (MIMEReportImpl)getReport();
		ReportConfiguration config = super.getReport().getConfig();
		Language lang = config.getLanguage();
		StringBuilder buffer = report.getBody();

		// Descriptions
		buffer.append("<tr/>");
		buffer.append("<td width=3D\"80\">&nbsp;</td>\n\r");
		buffer.append("<td><b>").append(ReportResource.getResourceValue(ResourceKey.Step, lang)).append("</b>");
		if (!(getStepDescription() == null || getStepDescription().isEmpty())) {
			buffer.append("&nbsp;:&nbsp;").append(getStepDescription());
		}
		buffer.append("</td>\n\r");
		buffer.append("</tr>\r\n");

		//output the step contents
		super.output();
	}

}
