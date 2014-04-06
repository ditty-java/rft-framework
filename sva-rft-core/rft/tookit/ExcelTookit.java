package rft.tookit;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Font;


public class ExcelTookit {
	public static enum CellStyle {
		NORMAL("MS PGothic", (short)12, false, HSSFColor.BLACK.index, false),
		ERROR("MS PGothic", (short)12, false, HSSFColor.RED.index, false),
		GRID_NORMAL("MS PGothic", (short)12, false, HSSFColor.BLACK.index, true),
		GRID_ERROR("MS PGothic", (short)12, false, HSSFColor.RED.index, true);

		private String fontName;
		private short fontHeight;
		private boolean bold;
		private short color;
		private boolean grid;
		
		private CellStyle(String fontName, short fontHeight, boolean bold, short color, boolean grid) {
			this.fontName = fontName;
			this.fontHeight = fontHeight;
			this.bold = bold;
			this.color = color;
			this.grid = grid;
		}
	}
	public static void insertText(HSSFWorkbook wb, String sheetName, int x, int y, String text) {
		insertText(wb, sheetName, x, y, text, CellStyle.NORMAL);
	}

	public static void insertText(HSSFWorkbook wb, String sheetName, int x, int y, String text, CellStyle cellStyle) {
		HSSFSheet sheet = wb.getSheet(sheetName);
		if (sheet == null) {
			sheet = wb.createSheet(sheetName);
		}

		HSSFRow row = sheet.getRow(x);
		if (row == null) {
			row = sheet.createRow(x);
		}

		HSSFCell cell = row.getCell(y);
		if (cell == null) {
			cell = row.createCell(y);
		}

		formatCell(wb, cell, cellStyle);
		cell.setCellValue(text == null ? "" : text);
	}

	private static HSSFCell formatCell(HSSFWorkbook wb, HSSFCell cell, CellStyle cellStyle) {
		HSSFCellStyle style = wb.createCellStyle();
		HSSFFont font = wb.createFont();
		
		font.setFontName(cellStyle.fontName);
		font.setFontHeightInPoints(cellStyle.fontHeight);
		font.setBoldweight(cellStyle.bold ? Font.BOLDWEIGHT_BOLD : Font.BOLDWEIGHT_NORMAL);
		font.setColor(cellStyle.color);
		font.setCharSet(HSSFFont.DEFAULT_CHARSET);
		style.setFont(font);

		if (cellStyle.grid) {
			style.setBorderTop(HSSFCellStyle.BORDER_THIN);
			style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		}

		cell.setCellStyle(style);
		return cell;
	}
	
	public static void setCellWeight(HSSFWorkbook wb, String sheetName, int columnIndex, int width) {
		HSSFSheet sheet = wb.getSheet(sheetName);
		if (sheet == null) {
			sheet = wb.createSheet();
		}
		sheet.setColumnWidth(columnIndex, width * 32 );
	}

	public static HSSFSheet insertImage(HSSFWorkbook wb, String sheetName,
			int x, int y, Image image, double scale)
			throws IOException {
		//BufferedImage bufferImg = null;
		ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
		BufferedImage bufferImg = ImageTookit.toBufferedImage(image);//bufferImage(image);
		ImageIO.write(bufferImg, "jpg", byteArrayOut);

		// get or create the report main sheet
		HSSFSheet sheet = wb.getSheet(sheetName);
		HSSFPatriarch patriarch = null;
		if (sheet == null) {
			sheet = wb.createSheet(sheetName);	
		}

		patriarch = sheet.getDrawingPatriarch();
		if (patriarch == null) {
			patriarch = sheet.createDrawingPatriarch();
		}

		HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0, (short) y, x, (short) y, x);
		anchor.setAnchorType(0);
		patriarch.createPicture(anchor,
				wb.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG)).resize(scale);

		return sheet;
	}

//	public static void drawGrid(HSSFWorkbook wb, int sheetIndex, int x0, int y0, int x1, int y1) {
//		HSSFSheet sheet = wb.getSheetAt(sheetIndex);
//		if (sheet == null) {
//			sheet = wb.createSheet();
//		}
//
//		HSSFCellStyle style = wb.createCellStyle();
//		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
//		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
//		
//		for (int x = x0; x <= x1; x ++) {
//			HSSFRow row = sheet.getRow(x);
//			if (row == null) {
//				row = sheet.createRow(x);
//			}
//
//			for (int y = y0; y <= y1; y ++) {
//				HSSFCell cell = row.getCell(y);
//				if (cell == null) {
//					cell = row.createCell(y);
//				}
//				HSSFCellStyle curStyle = cell.getCellStyle();
//				if (curStyle != null) {
//					style.setFont(curStyle.getFont(wb));
//					style.setFillBackgroundColor(curStyle.getFillBackgroundColor());
//				}
//				cell.setCellStyle(style);
//			}
//		}
//	}
}
