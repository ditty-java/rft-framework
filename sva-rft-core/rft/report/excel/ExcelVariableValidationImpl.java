package rft.report.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import rft.report.ReportConfiguration;
import rft.report.ReportConfiguration.Language;
import rft.report.ReportResource;
import rft.report.ReportResource.ResourceKey;
import rft.report.ReportValidation;
import rft.report.validator.EqualValidatorImpl;
import rft.report.validator.ReportValidator;
import rft.tookit.ExcelTookit;
import rft.tookit.ExcelTookit.CellStyle;

/**
 * Excel Variable Validation Implement
 * 
 * @author Bing Qu
 *
 */
public class ExcelVariableValidationImpl extends ReportValidation {

	private String sheetName;

	/**
	 * Constructor
	 *
	 * @param report
	 * @param target
	 * @param expectValue
	 * @param actualValue
	 */
	ExcelVariableValidationImpl(ExcelReportImpl report, String sheetName, String target, String expectValue, String actualValue) {
		this(report, sheetName, target, expectValue, actualValue, null);
	}

	/**
	 * Constructor
	 *
	 * @param report
	 * @param target
	 * @param expectValue
	 * @param actualValue
	 * @param validator
	 */
	ExcelVariableValidationImpl(ExcelReportImpl report, String sheetName, String target, String expectValue, String actualValue, ReportValidator validator) {
		super.setReport(report);
		super.setTargetObject(target);
		super.setExpectValue(expectValue);
		super.setActualValue(actualValue);
		this.sheetName = sheetName;
		super.setValidator(validator);
	}

	@Override
	public void output() {
		ExcelReportImpl report = (ExcelReportImpl)getReport();
		int rowNo = report.getCurrentRowNo(sheetName);
		ReportConfiguration config = super.getReport().getConfig();
		Language lang = config.getLanguage();

		HSSFWorkbook workbook = report.getBody();

		//Target object
		ExcelTookit.insertText(workbook, sheetName, rowNo, 2, ReportResource.getResourceValue(ResourceKey.TargetObject, lang));
		ExcelTookit.insertText(workbook, sheetName, rowNo, 3, super.getTargetObject());
		rowNo = report.addRow(sheetName, 1);
		
		//Expect Value
		ExcelTookit.insertText(workbook, sheetName, rowNo, 2, ReportResource.getResourceValue(ResourceKey.ExpectValue, lang));
		ExcelTookit.insertText(workbook, sheetName, rowNo, 3, super.getExpectValue());
		rowNo = report.addRow(sheetName, 1);
		
		//Actual Value
		ExcelTookit.insertText(workbook, sheetName, rowNo, 2, ReportResource.getResourceValue(ResourceKey.ActualValue, lang));
		ExcelTookit.insertText(workbook, sheetName, rowNo, 3, super.getActualValue());
		rowNo = report.addRow(sheetName, 1);
		
		//Validation Result
		ExcelTookit.insertText(workbook, sheetName, rowNo, 2, ReportResource.getResourceValue(ResourceKey.ValidationResult, lang));
		if (isValid()) {
			ExcelTookit.insertText(workbook, sheetName, rowNo, 3, ReportResource.getResourceValue(ResourceKey.OK, lang));
		} else {
			ExcelTookit.insertText(workbook, sheetName, rowNo, 3, ReportResource.getResourceValue(ResourceKey.NG, lang), CellStyle.ERROR);
		}
		rowNo = report.addRow(sheetName, 2);
	}
}
