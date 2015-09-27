import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFText2HTML;
import org.apache.pdfbox.util.PDFTextStripper;

public class PDFConverter {
	
	private static PrintWriter writer;
	private static PDFText2HTML html;
	private static String outputPath;
	
	public void convertToText(String filePath) throws IOException {

		PDFTextStripper pdfStripper = new PDFTextStripper();
		COSDocument cosDoc = null;
		PDDocument pdDoc = null;
		File file = new File(filePath);
		System.out.println("Document exist: " + file.exists());
		
		try { 
			PDFParser parser = new PDFParser(new FileInputStream(file));
			parser.parse();
			cosDoc = parser.getDocument();
			pdDoc = new PDDocument(cosDoc);
			String parsedText = pdfStripper.getText(pdDoc);
			System.out.println(parsedText);
			outputPath = "result.txt";
			writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputPath), "utf-8")));
			writer.print(parsedText);
			//html = new PDFText2HTML(parsedText);
			//html.writeText(pdDoc, writer);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}

