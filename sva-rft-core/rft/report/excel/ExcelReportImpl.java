package rft.report.excel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import rft.report.Report;
import rft.report.ReportConfiguration;
import rft.report.ReportConfiguration.Language;
import rft.tookit.ExcelTookit;

import com.rational.test.ft.script.RationalTestScript;

/**
 * Excel Report Implement
 * 
 * @author Bing Qu
 *
 */
public class ExcelReportImpl extends Report {
	/** Report Home Directory */
	public static final String MAIN_SHEET_NAME = "RFT_REPORT";

	// Default Edit Scope - Begin Column
	public static final int COLUMN_BEGIN = 0;
	// Default Edit Scope - End Column
	public static final int COLUMN_END = 10;
	// Default Column Width
	public static final int COLUMN_WIDTH = 145;

	private HSSFWorkbook workbook;
	private Map<String, Integer> currentRowNoMap = new HashMap<>();
	private String fileName;

	public HSSFWorkbook getBody() {
		return workbook;
	}

	/**
	 * Get Current Row Number
	 *
	 * @param sheetName
	 * @return
	 */
	int getCurrentRowNo (String sheetName) {
		if (!currentRowNoMap.containsKey(sheetName)) {
			currentRowNoMap.put(sheetName, Integer.valueOf(0));
		}

		return currentRowNoMap.get(sheetName);
	}

	/**
	 * Add Row (Change Current Row Number)
	 *
	 * @param sheetName
	 * @param rowCount
	 * @return
	 */
	int addRow(String sheetName, int rowCount) {
		int currentRowNo = getCurrentRowNo(sheetName);
		currentRowNo += rowCount;
		currentRowNoMap.put(sheetName, currentRowNo);

		return currentRowNo;
	}

	/**
	 * Constructor
	 *
	 * @param ts
	 */
	public ExcelReportImpl(RationalTestScript ts) {
		//generate file name
		String reportHome = Report.getReportHome(ts);
		fileName = reportHome + File.separatorChar + ts.getScriptName() + ".xls";

		//set configuration information
		ReportConfiguration config = new ReportConfiguration();
		config.setLanguage(Language.JP);
		setConfig(config);

		//set description information
		ExcelReportDescriptionImpl reportDesc = new ExcelReportDescriptionImpl(this, MAIN_SHEET_NAME);
		this.setDescription(reportDesc);
		
		//initialize the final result information
		ExcelReportResultImpl finalResult = new ExcelReportResultImpl(this, MAIN_SHEET_NAME);
		this.setFinalResult(finalResult);
		
		begin();
	}

	/**
	 * Constructor
	 * 
	 * @param ts
	 * @param config
	 */
	public ExcelReportImpl(RationalTestScript ts, ReportConfiguration config) {
		setConfig(config);
	}

	/**
	 * Create scenario into Report
	 * @return
	 */
	public ExcelReportScenarioImpl createScenario(String scenarioName) {
		ExcelReportScenarioImpl scenario = (ExcelReportScenarioImpl)super.getScenario(scenarioName);
		if (scenario == null) {
			scenario = new ExcelReportScenarioImpl(this, scenarioName);
			super.addScenario(scenario);
		}

		return scenario;
	}

	/**
	 *  @see rft.report.Report#getDescription()
	 */
	public ExcelReportDescriptionImpl getDescription() {
		return (ExcelReportDescriptionImpl) super.getDescription();
	}
	/**
	 * 
	 * @param description
	 */
	public void setDescription(ExcelReportDescriptionImpl description) {
		super.setDescription(description);
	}

	@Override
	public void output() throws IOException {
		workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(MAIN_SHEET_NAME);
		sheet.setColumnBreak(8);
		for (int columnIndex = COLUMN_BEGIN; columnIndex < COLUMN_END; columnIndex ++) {
			ExcelTookit.setCellWeight(workbook, MAIN_SHEET_NAME, columnIndex, COLUMN_WIDTH);
		}

		super.output();
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(fileName);
			workbook.write(out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}
}
