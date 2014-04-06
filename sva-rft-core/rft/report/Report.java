package rft.report;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rft.report.excel.ExcelReportImpl;
import rft.report.excel.ExcelReportScenarioImpl;
import rft.report.mime.MIMEReportImpl;

import com.rational.test.ft.script.IOptionName;
import com.rational.test.ft.script.RationalTestScript;

/**
 * RFT Report
 *
 * @author Bing Qu
 *
 */
public abstract class Report implements Outputable{
	/** report home directory name */
	private static final String REPORT_HOME = "reports";

	/**
	 * Enumeration of report type supported
	 */
	public static enum ReportType {
		Excel;
	}

	/**
	 * create an instance of report
	 *
	 * @param ts RFTscript
	 * @return instance of report
	 */
	public static ExcelReportImpl createExcel(RationalTestScript ts) {
		return new ExcelReportImpl(ts);
	}
	/**
	 * create an instance of report
	 *
	 * @param ts RFTscript
	 * @return instance of report
	 */
	public static MIMEReportImpl createMIME(RationalTestScript ts) {
		return new MIMEReportImpl(ts);
	}
	/**
	 * get the report home directory
	 *
	 * @param ts RFTscript
	 * @return report home directory
	 */
	protected static String getReportHome(RationalTestScript ts) {
		String projectPath = (String)RationalTestScript.getOption(IOptionName.DATASTORE);
		return projectPath + File.separatorChar + REPORT_HOME;
	}

	private ReportConfiguration config;
	private ReportDescription description;
	private List<String> scenarioNames = new ArrayList<>();
	private Map<String, ReportScenario> scenarios = new HashMap<>();
	private ReportResult finalResult;

	/**
	 * get the report configuration
	 *
	 * @return report configuration
	 */
	public ReportConfiguration getConfig() {
		return config;
	}
	/**
	 * set the report configuration
	 *
	 * @param config report configuration
	 */
	protected void setConfig(ReportConfiguration config) {
		this.config = config;
	}

	/**
	 * set the description of report
	 *
	 * @param description description of report
	 */
	protected void setDescription(ReportDescription description) {
		this.description = description;
	}
	/**
	 * @return description of report
	 */
	protected ReportDescription getDescription() {
		return this.description;
	}

	/**
	 * Add scenario into scenario List
	 *
	 * @param scenario
	 */
	protected void addScenario(ReportScenario scenario) {
		scenarioNames.add(scenario.getScenarioName());
		scenarios.put(scenario.getScenarioName(), scenario);
	}

	/**
	 * get scenario from the scenario list
	 *
	 * @param scenario scenario
	 */
	protected ReportScenario getScenario(String scenarioName) {
		if (scenarios.containsKey(scenarioName)) {
			return scenarios.get(scenarioName);
		}

		return null;
	}

	/**
	 * @return the scenarios
	 */
	public List<ReportScenario> getScenarios() {
		List<ReportScenario> list = new ArrayList<>();
		for (String scenarioName : scenarioNames) {
			list.add(scenarios.get(scenarioName));
		}
		return list;
	}

	/**
	 * @return final result of report
	 */
	protected ReportResult getFinalResult() {
		return finalResult;
	}
	/**
	 * set the final result of report
	 *
	 * @param finalResult final result of report
	 */
	protected void setFinalResult(ReportResult finalResult) {
		this.finalResult = finalResult;
	}

	/**
	 * begin to record the execution time of test case
	 */
	protected void begin() {
		finalResult.setBeginTimestamp(System.currentTimeMillis());
	}

	/**
	 * end to record the execution time of test case
	 */
	protected void end() {
		finalResult.setEndTimestamp(System.currentTimeMillis());

		boolean passed = true;
		for (ReportScenario scenario : getScenarios()) {
			if (!scenario.isValid()) {
				passed = false;
				break;
			}
		}

		finalResult.setFinalResult(passed);
	}

	@Override
	public void output() throws IOException {
		//record the end time.
		end();

		//output the report description
		description.output();

		//output the report scenarios
		for (ReportScenario scenario : getScenarios()) {
			scenario.output();
		}

		//output the report final result
		finalResult.output();
	}

	/**
	 * get the report body for output
	 *
	 * @return report body
	 */
	public abstract Object getBody();
}
