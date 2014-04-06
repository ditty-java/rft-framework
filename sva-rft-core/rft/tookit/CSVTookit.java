/**
 * 
 */
package rft.tookit;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.csvreader.CsvReader;

/**
 * 
 * @author Bing Qu
 * 
 */
public class CSVTookit {

	public static List<String[]> importCsv(String file) throws IOException {
		return importCsv(file, Charset.defaultCharset(), ',');
	}

	public static List<String[]> importCsv(String file, Charset charset, char separator) throws IOException {

		List<String[]> list = new ArrayList<>();
		CsvReader reader = null;

		try {
			// initialize the CSV Reader
			reader = new CsvReader(file, separator, charset);
			//reader.setCaptureRawRecord(true);
			String[] csvData = null;
			while (reader.readRecord()) {
				// Read CSV Rows
				String[] csvRow = reader.getValues();
				if (csvRow != null && csvRow.length > 0) {
					csvData = arrayCopy(new String[0], csvRow, 0, csvRow.length - 1);

					//if (csvRow[0] != null/* && !"".equals(csvRow[0].trim())*/) {
					list.add(csvData);
					//}
				}
			}
		} finally {
			if (reader != null)
				reader.close();
		}
		return list;
	}

	private static <T extends Object> T[] arrayCopy(T[] targetType, T[] sourceArray, int beginCol, int endCol) {
		if (sourceArray == null || beginCol < 0 || endCol < beginCol || beginCol >= sourceArray.length || endCol >= sourceArray.length) {
			return null;
		}

		List<T> targetList = new ArrayList<T>(endCol - beginCol + 1);
		for (int i = beginCol; i <= endCol; i ++) {
			targetList.add(sourceArray[i]);
		}

		return (T[])targetList.toArray(targetType);
	}

	private static String[] trimQuotes(String[] target) {
		String[] result = new String[target.length];
		for (int i = 0; i < target.length; i ++) {
			result[i] = trimQuotes(target[i]);
		}
		return result;
	}

	private static String trimQuotes(String target) {
		if (target == null) {
			return null;
		}
		if (target.startsWith("\"") && target.endsWith("\"")) {
			return target.substring(1, target.length() - 1);
		}
		return target;
	}

	public static void main(String[] args) {
		/*List<String> list = new ArrayList<>();
		CsvReader reader = null;

		try {
			// initialize the CSV Reader
			reader = new CsvReader("C:/workspaces/RFT-workspace/sva-rft-sample/csv/Test01_test.csv", ',', Charset.defaultCharset());
			reader.setCaptureRawRecord(true);
			String[] csvData = null;
			while (reader.readRecord()) {
				// Read CSV Rows
				String csvRow = reader.getRawRecord();
				csvData = csvRow.split(",");
				//System.out.println("|" + csvRow + "|");
				//System.out.println("|" + csvData.length + "|");
				String[] t = trimQuotes(csvData);
				for (String s : t) {
					System.out.print(s);
					System.out.print(",");
				}
				System.out.println();
				list.add(csvRow);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null)
				reader.close();
		}
		//System.out.println(list.size());*/
		try {
			List<String[]> list = importCsv("C:/workspaces/RFT-workspace/sva-rft-sample/csv/Test01_test.csv");
			for (String[] s : list) {
				for(String t : s) {
					System.out.print(t);
					System.out.print(",");
				}
				System.out.println();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
