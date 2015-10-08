import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFText2HTML;
import org.apache.pdfbox.util.PDFTextStripper;

import java.io.*;

public class PDFConverter {

	private static File _file;
	//private final static String _path = "/Users/Stanley/Desktop/test3.pdf";
	private final static String outputPath = "result.txt";
	private final static String outputPath2 = "result2.txt";
	private static PrintWriter writer;
	private static PrintWriter writer2;
	private static PDFText2HTML pdfHtml;

	public void convertToText(String filePath) throws IOException{

		// To read take in the PDF File
		PDFTextStripper pdfStripper = new PDFTextStripper();
		_file = new File(filePath);
		PDDocument pdDoc = PDDocument.load(_file);
		System.out.println("Document exist: " + _file.exists());

		//To show the number of pages in the PDF file
		int numOfPages = pdDoc.getNumberOfPages();
		System.out.println("Number of pages in the resume: " + numOfPages);

		//To print the Text in the PDF file
		String parsedText = pdfStripper.getText(pdDoc);
		System.out.println(parsedText);

		//To output the text in the PDF into a plain text file
		writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputPath), "utf-8")));
		writer.print(parsedText);
		writer.close();

		//To output the text in the PDF into a text file retaining the HTML formatting
		pdfHtml = new PDFText2HTML("utf-8");
		String parsedText2 = pdfHtml.getText(pdDoc);
		System.out.println(parsedText2);

		writer2 = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputPath2), "utf-8")));
		writer2.print(parsedText2);
		writer2.close();
	}
}

