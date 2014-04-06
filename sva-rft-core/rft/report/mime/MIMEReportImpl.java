/**
 * 
 */
package rft.report.mime;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import rft.report.Report;
import rft.report.ReportConfiguration;
import rft.report.ReportConfiguration.Language;
import rft.report.ReportScenario;
import rft.tookit.MIMETookit;

import com.rational.test.ft.script.RationalTestScript;

/**
 * 
 * @author Bing Qu
 *
 */
public class MIMEReportImpl extends Report {

	private String fileName;
	private StringBuilder fileBuffer = new StringBuilder();
	private List<MIMEReportImageImpl> imgList = new ArrayList<>();

	/* (non-Javadoc)
	 * @see rft.report.Report#getBody()
	 */
	@Override
	public StringBuilder getBody() {
		return fileBuffer;
	}

	/**
	 * Add Image into Image Resource List
	 *
	 * @param mimeReportImageImpl
	 */
	public void addImageResource(MIMEReportImageImpl image) {
		imgList.add(image);
	}

	/**
	 * Constructor
	 *
	 * @param ts
	 */
	public MIMEReportImpl(RationalTestScript ts) {
		//generate file name
		String reportHome = Report.getReportHome(ts);
		fileName = reportHome + File.separatorChar + ts.getScriptName() + ".doc";

		//set configuration information
		ReportConfiguration config = new ReportConfiguration();
		config.setLanguage(Language.JP);
		setConfig(config);

		//set description information
		MIMEReportDescriptionImpl reportDesc = new MIMEReportDescriptionImpl(this);
		this.setDescription(reportDesc);
		
		//initialize the final result information
		MIMEReportResultImpl finalResult = new MIMEReportResultImpl(this);
		this.setFinalResult(finalResult);
		
		begin();
	}
	
	/**
	 * Constructor
	 * 
	 * @param ts
	 * @param config
	 */
	public MIMEReportImpl(RationalTestScript ts, ReportConfiguration config) {
		this(ts);
		setConfig(config);
	}

	/**
	 * Create scenario into Report
	 * @return
	 */
	public MIMEReportScenarioImpl createScenario() {
		MIMEReportScenarioImpl scenario = new MIMEReportScenarioImpl(this);
		super.addScenario(scenario);

		return scenario;
	}
	/**
	 *  @see rft.report.Report#getDescription()
	 */
	public MIMEReportDescriptionImpl getDescription() {
		return (MIMEReportDescriptionImpl) super.getDescription();
	}
	/**
	 * 
	 * @param description
	 */
	public void setDescription(MIMEReportDescriptionImpl description) {
		super.setDescription(description);
	}

	@Override
	public void output() throws IOException {
		//MIME Header
		fileBuffer.append("MIME-Version: 1.0\r\n");
		fileBuffer.append("Content-Type: multipart/related; boundary=\"separator\"\r\n");
		fileBuffer.append("This is a multi-part message in MIME format.\r\n");
		fileBuffer.append("\r\n");

		fileBuffer.append(MIMETookit.MIME_SPEARATOR);
		fileBuffer.append("Content-Location: file:///RFT-REPORT.htm\r\n");
		fileBuffer.append("Content-Transfer-Encoding: quoted-printable\r\n");
		fileBuffer.append("Content-Type: text/html; charset=\"utf-8\"\r\n");
		fileBuffer.append("\r\n");

		fileBuffer.append("<html xmlns:v=3D\"urn:schemas-microsoft-com:vml\"\r\n");
		fileBuffer.append("xmlns:o=3D\"urn:schemas-microsoft-com:office:office\"\r\n");
		fileBuffer.append("xmlns:w=3D\"urn:schemas-microsoft-com:office:word\"\r\n");
		fileBuffer.append("xmlns:m=3D\"http://schemas.microsoft.com/office/2004/12/omml\"\r\n");
		fileBuffer.append("xmlns=3D\"http://www.w3.org/TR/REC-html40\">\r\n");

		fileBuffer.append("<head>\r\n");
		fileBuffer.append("<meta http-equiv=3DContent-Type content=3D\"text/html; charset=3Dutf-8\">\r\n");
		fileBuffer.append("<title>Title</title>\r\n");
		fileBuffer.append("</head>\r\n");
		fileBuffer.append("<body>\r\n");

		//output the children contents
		super.output();
		fileBuffer.append("</table>\r\n");

		fileBuffer.append("</body>\r\n");
		fileBuffer.append("</html>\r\n");

		//output image resources
		for (MIMEReportImageImpl img : imgList) {
			img.outputResource();
		}
		fileBuffer.append("\r\n");
		fileBuffer.append(MIMETookit.MIME_END);

		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(fileName));
			out.write(getBody().toString());
			out.flush();
			out.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.flush();
					out.close();
				} catch (Exception e) {
					//NOP
				}
			}
		}
	}
}
