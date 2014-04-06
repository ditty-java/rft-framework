package rft.report;

import rft.report.validator.EqualValidatorImpl;
import rft.report.validator.ReportValidator;

/**
 * Report Validation Block
 *
 * @author Bing Qu
 */
public abstract class ReportValidation extends ReportBlock implements Outputable {
	protected static final ReportValidator DEFAULT_VALIDATOR = new EqualValidatorImpl();

	private String targetObject;
	private String expectValue;
	private String actualValue;
	private ReportValidator validator;

	public String getTargetObject() {
		return targetObject;
	}
	public void setTargetObject(String targetObject) {
		this.targetObject = targetObject;
	}
	public String getExpectValue() {
		return expectValue;
	}
	public void setExpectValue(String expectValue) {
		this.expectValue = expectValue;
	}
	public String getActualValue() {
		return actualValue;
	}
	public void setActualValue(String actualValue) {
		this.actualValue = actualValue;
	}
	public ReportValidator getValidator() {
		return validator;
	}
	public void setValidator(ReportValidator validator) {
		this.validator = validator;
	}
	

	/**
	 * Check the validation result
	 *
	 * @return
	 */
	public boolean isValid() {
		return (getValidator() == null ? DEFAULT_VALIDATOR : getValidator()).doValid(expectValue, actualValue);
	}
}
