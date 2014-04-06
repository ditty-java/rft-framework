/**
 * 
 */
package rft.report.validator;

/**
 * 
 * @author Bing Qu
 *
 */
public class RegexValidatorImpl implements ReportValidator{

	private String regexPattern = null;

	/**
	 * Constructor
	 */
	public RegexValidatorImpl() {
		this(null);
	}

	/**
	 * Constructor
	 *
	 * @param regex
	 */
	public RegexValidatorImpl(String regex) {
		regexPattern = regex;
	}
	/* 
	 * @see rft.report.validator.ReportValidator#doValid(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean doValid(String expectValue, String actualValue) {
		if (regexPattern != null) {
			return actualValue.matches(regexPattern);
		} else {
			return actualValue.matches(expectValue);
		}
	}

}
