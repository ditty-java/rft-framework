/**
 * 
 */
package rft.report.validator;

/**
 * 
 * @author Bing Qu
 *
 */
public class EqualValidatorImpl implements ReportValidator{

	private boolean ignoreCase;

	public EqualValidatorImpl() {
		this.ignoreCase = false;
	}

	public EqualValidatorImpl(boolean ignoreCase) {
		this.ignoreCase = ignoreCase;
	}

	public boolean doValid(String expectValue, String actualValue) {
		if (ignoreCase) {
			return (expectValue == null ? "" : expectValue).equalsIgnoreCase(actualValue);
		}

		return (expectValue == null ? "" : expectValue).equals(actualValue);
	}
}
