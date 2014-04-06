/**
 * 
 */
package rft.tookit;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.rational.test.ft.ObjectNotFoundException;

/**
 * 
 * @author Bing Qu
 * 
 */
public class MIMETookit {
	private static final String MIME_SIGN_SPEARATOR = "separator";
	public static final String MIME_SPEARATOR = "--" + MIME_SIGN_SPEARATOR + "\r\n";
	public static final String MIME_END = "--" + MIME_SIGN_SPEARATOR + "--" + "\r\n";

	/** No options specified. Value is zero. */
	public final static int NO_OPTIONS = 0;

	/** Don't break lines when encoding (violates strict Base64 specification) */
	public final static int DONT_BREAK_LINES = 8;

	/** Maximum line length (76) of Base64 output. */
	private final static int MAX_LINE_LENGTH = 76;

	/** The new line character (\n) as a byte. */
	private final static byte NEW_LINE = (byte) '\n';

	/** The equals sign (=) as a byte. */
	private final static byte EQUALS_SIGN = (byte) '=';

	/** Preferred encoding. */
	private final static String PREFERRED_ENCODING = "UTF-8";

	/** The 64 valid Base64 values. */
	private final static byte[] ALPHABET;
	private final static byte[] _NATIVE_ALPHABET = /*
													 * May be something funny
													 * like EBCDIC
													 */
	{ (byte) 'A', (byte) 'B', (byte) 'C', (byte) 'D', (byte) 'E', (byte) 'F',
			(byte) 'G', (byte) 'H', (byte) 'I', (byte) 'J', (byte) 'K',
			(byte) 'L', (byte) 'M', (byte) 'N', (byte) 'O', (byte) 'P',
			(byte) 'Q', (byte) 'R', (byte) 'S', (byte) 'T', (byte) 'U',
			(byte) 'V', (byte) 'W', (byte) 'X', (byte) 'Y', (byte) 'Z',
			(byte) 'a', (byte) 'b', (byte) 'c', (byte) 'd', (byte) 'e',
			(byte) 'f', (byte) 'g', (byte) 'h', (byte) 'i', (byte) 'j',
			(byte) 'k', (byte) 'l', (byte) 'm', (byte) 'n', (byte) 'o',
			(byte) 'p', (byte) 'q', (byte) 'r', (byte) 's', (byte) 't',
			(byte) 'u', (byte) 'v', (byte) 'w', (byte) 'x', (byte) 'y',
			(byte) 'z', (byte) '0', (byte) '1', (byte) '2', (byte) '3',
			(byte) '4', (byte) '5', (byte) '6', (byte) '7', (byte) '8',
			(byte) '9', (byte) '+', (byte) '/' };

	static {
		byte[] __bytes;
		try {
			__bytes = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
					.getBytes(PREFERRED_ENCODING);
		} catch (java.io.UnsupportedEncodingException use) {
			__bytes = _NATIVE_ALPHABET; // Fall back to native encoding
		}
		ALPHABET = __bytes;
	}

	private static String imageToMIME(Image image, double scale)
			throws IOException {
		ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
		BufferedImage bufferImg = ImageTookit.toBufferedImage(image);// bufferImage(image);
		ImageIO.write(bufferImg, "jpg", byteArrayOut);

		// Encode the JPEG in MIME Base64
		byte[] source = byteArrayOut.toByteArray();
		int options = NO_OPTIONS;
		int off = 0;
		int len = source.length;

		// Isolate options
		int dontBreakLines = (options & DONT_BREAK_LINES);

		// Convert option to boolean in way that code likes it.
		boolean breakLines = dontBreakLines == 0;

		int len43 = len * 4 / 3;
		byte[] outBuff = new byte[(len43) // Main 4:3
				+ ((len % 3) > 0 ? 4 : 0) // Account for padding
				+ (breakLines ? (len43 / MAX_LINE_LENGTH) : 0)]; // New lines
		int d = 0;
		int e = 0;
		int len2 = len - 2;
		int lineLength = 0;
		for (; d < len2; d += 3, e += 4) {
			encode3to4(source, d + off, 3, outBuff, e);

			lineLength += 4;
			if (breakLines && lineLength == MAX_LINE_LENGTH) {
				outBuff[e + 4] = NEW_LINE;
				e++;
				lineLength = 0;
			} // end if: end of line
		} // en dfor: each piece of array

		if (d < len) {
			encode3to4(source, d + off, len - d, outBuff, e);
			e += 4;
		} // end if: some padding needed

		return new String(outBuff, 0, e, PREFERRED_ENCODING);
	}

	private static byte[] encode3to4(byte[] source, int srcOffset,
			int numSigBytes, byte[] destination, int destOffset) {
		// 1 2 3
		// 01234567890123456789012345678901 Bit position
		// --------000000001111111122222222 Array position from threeBytes
		// --------| || || || | Six bit groups to index ALPHABET
		// >>18 >>12 >> 6 >> 0 Right shift necessary
		// 0x3f 0x3f 0x3f Additional AND

		// Create buffer with zero-padding if there are only one or two
		// significant bytes passed in the array.
		// We have to shift left 24 in order to flush out the 1's that appear
		// when Java treats a value as negative that is cast from a byte to an
		// int.
		int inBuff = (numSigBytes > 0 ? ((source[srcOffset] << 24) >>> 8) : 0)
				| (numSigBytes > 1 ? ((source[srcOffset + 1] << 24) >>> 16) : 0)
				| (numSigBytes > 2 ? ((source[srcOffset + 2] << 24) >>> 24) : 0);

		switch (numSigBytes) {
		case 3:
			destination[destOffset] = ALPHABET[(inBuff >>> 18)];
			destination[destOffset + 1] = ALPHABET[(inBuff >>> 12) & 0x3f];
			destination[destOffset + 2] = ALPHABET[(inBuff >>> 6) & 0x3f];
			destination[destOffset + 3] = ALPHABET[(inBuff) & 0x3f];
			return destination;

		case 2:
			destination[destOffset] = ALPHABET[(inBuff >>> 18)];
			destination[destOffset + 1] = ALPHABET[(inBuff >>> 12) & 0x3f];
			destination[destOffset + 2] = ALPHABET[(inBuff >>> 6) & 0x3f];
			destination[destOffset + 3] = EQUALS_SIGN;
			return destination;

		case 1:
			destination[destOffset] = ALPHABET[(inBuff >>> 18)];
			destination[destOffset + 1] = ALPHABET[(inBuff >>> 12) & 0x3f];
			destination[destOffset + 2] = EQUALS_SIGN;
			destination[destOffset + 3] = EQUALS_SIGN;
			return destination;

		default:
			return destination;
		}
	}

	/**
	 * Generate Image MHTML Tag
	 *
	 * @param imageFileName
	 * @return
	 */
	public static String generateImageTag(String imageFileName) {
		StringBuilder buffer = new StringBuilder();
		// generate Image Tag   
	    buffer.append("<img src=3D\"file:///").append(imageFileName).append("\"/><br/>\r\n"); 

	    return buffer.toString();
	}

	/**
	 * Generate Image Contents with MIME Encoding
	 * 
	 * @param imageFileName
	 * @param image
	 * @param scale
	 * @return
	 * @throws IOException
	 */
	public static String generateImageResource(String imageFileName, Image image, double scale) throws IOException {
		StringBuilder buffer = new StringBuilder(MIME_SPEARATOR);
		// generate Image MIME encoding
		buffer.append("Content-Type: image/jpeg\r\n");
		buffer.append("Content-Transfer-Encoding: base64\r\n");
		buffer.append("Content-Location: file:///").append(imageFileName).append("\r\n");
		buffer.append("\r\n");

		buffer.append(imageToMIME(image, scale));

	    return buffer.toString();
	}
}
