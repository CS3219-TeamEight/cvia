import java.io.IOException;

public class Main {

	private static PDFConverter converter = new PDFConverter();
	
	public static void main(String[] args) throws IOException {
		String string1 = "/Users/Stanley/Desktop/Test.pdf";
		converter.convertToText(string1);
	}

}
