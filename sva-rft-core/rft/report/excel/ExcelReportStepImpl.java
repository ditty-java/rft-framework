package rft.report.excel;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import rft.report.Report;
import rft.report.ReportConfiguration;
import rft.report.ReportConfiguration.Language;
import rft.report.ReportResource;
import rft.report.ReportResource.ResourceKey;
import rft.report.ReportStep;
import rft.report.ReportValidation;
import rft.report.validator.ReportValidator;
import rft.tookit.ExcelTookit;
import rft.tookit.MiscUtils;

/**
 * Excel Report Step Block Implement
 * 
 * @author Bing Qu
 *
 */
public class ExcelReportStepImpl extends ReportStep {

	private String sheetName;

	// validation list
	private List<ReportValidation> validations = new ArrayList<>();
	
	/**
	 * Constructor
	 *
	 * @param report
	 */
	ExcelReportStepImpl(Report report, String sheetName) {
		super.setReport(report);
		this.sheetName = sheetName;
	}

	/**
	 * @see rft.report.ReportStep#addComment(String)
	 */
	public void addComment(String comment) {
		addContent(new ExcelCommentImpl((ExcelReportImpl)getReport(), comment));
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
		ExcelReportImageImpl imageContent = new ExcelReportImageImpl((ExcelReportImpl)getReport(), sheetName);
		imageContent.setImage(image);
		addContent(imageContent);
	}

	/**
	 * @see rft.report.ReportStep#addVariableValidation(String, String, String)
	 */
	public void addVariableValidation(String target, String expectValue, String actualValue) {
		addVariableValidation(target, expectValue, actualValue, null);
	}

	/**
	 * @see rft.report.ReportStep#addVariableValidation(String, String, String, ReportValidator)
	 */
	public void addVariableValidation(String target, String expectValue, String actualValue, ReportValidator validator) {
		ExcelVariableValidationImpl validation = new ExcelVariableValidationImpl((ExcelReportImpl)getReport(), sheetName, target, expectValue, actualValue, validator);
		addContent(validation);
		validations.add(validation);
	}

	/**
	 * @see rft.report.ReportStep#addVariableValidation(String, List<List<String>>, List<String[]>)
	 */
	public void addGridValidation(String target, List<String[]> expectValues, List<String[]> actualValues) {
		addGridValidation(target, expectValues, actualValues, null);
	}

	/**
	 * @see rft.report.ReportStep#addVariableValidation(String, List<List<String>>, List<String[]>, ReportValidator[])
	 */
	public void addGridValidation(String target, List<String[]> expectValues, List<String[]> actualValues, ReportValidator[] validators) {
		ExcelGridValidationImpl validation = new ExcelGridValidationImpl((ExcelReportImpl)getReport(), sheetName, target, expectValues, actualValues, validators);
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
		ExcelReportImpl report = (ExcelReportImpl)getReport();
		int x = report.addRow(sheetName, 2);
		ReportConfiguration config = super.getReport().getConfig();
		Language lang = config.getLanguage();

		HSSFWorkbook workbook = report.getBody();

		//output step description
		ExcelTookit.insertText(workbook, sheetName, x, 1, ReportResource.getResourceValue(ResourceKey.Step, lang));
		ExcelTookit.insertText(workbook, sheetName, x, 2, getStepDescription());
		x = report.addRow(sheetName, 1);

		//output the step contents
		super.output();
	}

}
