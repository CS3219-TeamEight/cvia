import java.io.IOException;

public class Main {

	private static PDFConverter converter = new PDFConverter();
	
	public static void main(String[] args) throws IOException {
		
		//For testing my own pdf file
		String _stanley_pdf = "/Users/Stanley/Desktop/Test.pdf";
		
		converter.convertToText(_stanley_pdf);
	}

}
