/**
 * 
 */
package rft.report.validator;

/**
 * 
 * @author Bing Qu
 *
 */
public interface ReportValidator {

	/**
	 * Compare method between expectValue and actualValue
	 *
	 * @param expectValue
	 * @param actualValue
	 * @return
	 */
	boolean doValid(String expectValue, String actualValue);
}
