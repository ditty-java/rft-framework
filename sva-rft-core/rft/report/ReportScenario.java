/**
 * 
 */
package rft.report;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Bing Qu
 *
 */
public abstract class ReportScenario extends ReportBlock implements Outputable{

	private String scenarioName;
	private List<ReportStep> steps = new ArrayList<>();

	/**
	 * @return the scenarioName
	 */
	public String getScenarioName() {
		return scenarioName;
	}
	/**
	 * @param scenarioName the scenarioName to set
	 */
	public void setScenarioName(String scenarioName) {
		this.scenarioName = scenarioName;
	}

	/**
	 * get step list of report
	 *
	 * @return step list
	 */
	protected List<ReportStep> getSteps() {
		return this.steps;
	}
	/**
	 * add the step into the step list
	 *
	 * @param step step
	 */
	protected void addStep(ReportStep step) {
		this.steps.add(step);
	}

	/**
	 * Check Validation Result
	 *
	 * @return
	 */
	public boolean isValid() {
		for (ReportStep step : getSteps()) {
			if (!step.isValid()) {
				return false;
			}
		}

		return true;
	}

	/* (non-Javadoc)
	 * @see rft.report.Outputable#output()
	 */
	@Override
	public void output() throws IOException {
		for (ReportStep step : getSteps()) {
			step.output();
		}
	}
}
