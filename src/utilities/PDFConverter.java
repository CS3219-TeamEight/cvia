package utilities;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import java.io.*;

public class PDFConverter {

	private static File _file;
	private static FileOutput writer = new FileOutput();

	public void convertToText(String filePath, String outputPath) throws IOException{

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
		writer.write(outputPath, parsedText);
	}
}

