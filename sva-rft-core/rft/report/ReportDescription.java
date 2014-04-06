package rft.report;

import java.util.ArrayList;
import java.util.List;

/**
 * Report Description Block
 *
 * @author Bing Qu
 */
public abstract class ReportDescription extends ReportBlock implements Outputable{
	private String functionName;
	private String caseNumber;
	private List<String> descriptions = new ArrayList<>();

	/**
	 * [property] Get function name
	 *
	 * @return function name
	 */
	public String getFunctionName() {
		return functionName;
	}
	/**
	 * [property] Get function name
	 *
	 * @param functionName function name
	 */
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	/**
	 * [property] get case number
	 * @return case number
	 */
	public String getCaseNumber() {
		return caseNumber;
	}
	/**
	 * [property] Set case number
	 *
	 * @param caseNumber case number
	 */
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}
	/**
	 * Get other additional descriptions
	 *
	 * @return
	 */
	protected List<String> getDescriptions() {
		return descriptions;
	}
	/**
	 * Add other additional description
	 *
	 * @param description additional description
	 */
	public void addDescription(String description) {
		this.descriptions.add(description);
	}
	
	/**
	 * output the content with special format implementation.
	 */
	public abstract void output();
}
