package rft.report.excel;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import rft.report.ReportConfiguration;
import rft.report.ReportConfiguration.Language;
import rft.report.ReportResource;
import rft.report.ReportResource.ResourceKey;
import rft.report.ReportValidation;
import rft.report.validator.EqualValidatorImpl;
import rft.report.validator.ReportValidator;
import rft.tookit.ExcelTookit;

/**
 * Excel Grid Data Validation Implement
 * 
 * @author Bing Qu
 *
 */
public class ExcelGridValidationImpl extends ReportValidation {

	private String sheetName;
	private ReportValidator[] validators;
	private List<String[]> expectValues;
	private List<String[]> actualValues;
	private List<Boolean[]> validateResults = new ArrayList<>();
	private boolean valid = false;

	/**
	 * Constructor
	 *
	 * @param report
	 * @param target
	 * @param expectValue
	 * @param actualValue
	 */
	ExcelGridValidationImpl(ExcelReportImpl report, String sheetName, String target, List<String[]> expectValues, List<String[]> actualValues) {
		super.setReport(report);
		super.setTargetObject(target);
		setExpectValues(expectValues);
		setActualValues(actualValues);
		this.sheetName = sheetName;
	}

	/**
	 * Constructor
	 *
	 * @param report
	 * @param target
	 * @param expectValue
	 * @param actualValue
	 */
	ExcelGridValidationImpl(ExcelReportImpl report, String sheetName, String target, List<String[]> expectValues, List<String[]> actualValues, ReportValidator[] validators) {
		super.setReport(report);
		super.setTargetObject(target);
		setExpectValues(expectValues);
		setActualValues(actualValues);
		this.sheetName = sheetName;
		if (validators != null) {
			this.validators = new ReportValidator[validators.length];
			for (int i = 0; i < validators.length; i ++) {
				this.validators[i] = validators[i];
			}
		}
	}

	/**
	 * Get validation method for specified index
	 *
	 * @param i
	 * @return
	 */
	public ReportValidator getValidator(int i) {
		if (this.validators == null || this.validators.length <= i || this.validators[i] == null) {
			return DEFAULT_VALIDATOR;
		}

		return this.validators[i];
	}

	/**
	 * @return the expectValues
	 */
	public List<String[]> getExpectValues() {
		return expectValues;
	}
	/**
	 * @param expectValues the expectValues to set
	 */
	public void setExpectValues(List<String[]> expectValues) {
		this.expectValues = expectValues;
	}
	/**
	 * @return the actualValues
	 */
	public List<String[]> getActualValues() {
		return actualValues;
	}
	/**
	 * @param actualValues the actualValues to set
	 */
	public void setActualValues(List<String[]> actualValues) {
		this.actualValues = actualValues;
	}

	/**
	 * @see ReportValidation#isValid()
	 */
	public boolean isValid() {
		if (validateResults == null || validateResults.isEmpty()) {
			validate();
		}

		return this.valid;
	}
	/**
	 * Validate the validation.
	 */
	public void validate() {
		String[] expectRow = null;
		String[] actualRow = null;
		Boolean[] resultRow = null;
		String expectValue = null;
		String actualValue = null;
		boolean finalResult = true;

		for (int x = 0; x < expectValues.size(); x ++) {
			expectRow = expectValues.get(x);
			resultRow = new Boolean[expectRow.length];
			validateResults.add(resultRow);

			if (x >= actualValues.size()) {
				actualRow = new String[expectRow.length];
			} else {
				actualRow = actualValues.get(x);
			}

			for (int y = 0; y < expectRow.length; y ++) {
				expectValue = expectRow[y];

				if (y < actualRow.length) {
					actualValue = actualRow[y];
				}

				if (getValidator(y).doValid(expectValue, actualValue)) {
					resultRow[y] =Boolean.TRUE;
				} else {
					resultRow[y] = Boolean.FALSE;
					finalResult = false;
				}
			}
		}

		this.valid = finalResult;
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
		
		//Expect Values
		ExcelTookit.insertText(workbook, sheetName, rowNo, 2, ReportResource.getResourceValue(ResourceKey.ExpectValue, lang));
		rowNo = report.addRow(sheetName, 1);

		String[] expectRow = null;
		for (int x = 0; x < expectValues.size(); x ++) {
			expectRow = expectValues.get(x);
			for (int y = 0; y < expectRow.length; y ++) {
				ExcelTookit.insertText(workbook, sheetName, rowNo, 3 + y, expectRow[y], ExcelTookit.CellStyle.GRID_NORMAL);
			}
			rowNo = report.addRow(sheetName, 1);
		}

		//perform the validation
		validate();

		//Actual Values
		ExcelTookit.insertText(workbook, sheetName, rowNo, 2, ReportResource.getResourceValue(ResourceKey.ActualValue, lang));
		rowNo = report.addRow(sheetName, 1);

		String[] actualRow = null;
		for (int x = 0; x < expectValues.size(); x ++) {
			if (x < actualValues.size()) {
				actualRow = actualValues.get(x);
			} else {
				actualRow = new String[expectValues.get(0).length];
			}

			for (int y = 0; y < actualRow.length; y ++) {
				if (validateResults.get(x)[y]) {
					ExcelTookit.insertText(workbook, sheetName, rowNo, 3 + y, actualRow[y], ExcelTookit.CellStyle.GRID_NORMAL);
				} else {
					ExcelTookit.insertText(workbook, sheetName, rowNo, 3 + y, actualRow[y], ExcelTookit.CellStyle.GRID_ERROR);
				}
			}
			rowNo = report.addRow(sheetName, 1);
		}

		//Validation Result
		ExcelTookit.insertText(workbook, sheetName, rowNo, 2, ReportResource.getResourceValue(ResourceKey.ValidationResult, lang));
		if (isValid()) {
			ExcelTookit.insertText(workbook, sheetName, rowNo, 3, ReportResource.getResourceValue(ResourceKey.OK, lang));
		} else {
			ExcelTookit.insertText(workbook, sheetName, rowNo, 3, ReportResource.getResourceValue(ResourceKey.NG, lang), ExcelTookit.CellStyle.ERROR);
		}

		rowNo = report.addRow(sheetName, 2);
	}
}
