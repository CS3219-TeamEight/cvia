package cvia.utilities;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class PDFConverter {
    public String convertToText(String filePath) throws IOException {
        // To read take in the PDF File
        PDFTextStripper pdfStripper = new PDFTextStripper();
        File inputFile = new File(filePath);
        PDDocument doc = null;

        try {
            doc = PDDocument.load(inputFile);

            //To show the number of pages in the PDF file
            int numOfPages = doc.getNumberOfPages();

            //To print the text in the PDF file
            String parsedText = pdfStripper.getText(doc);

            //Debug purposes
            System.out.println("Document exist: " + inputFile.exists());
            System.out.println("Number of pages in the resume: " + numOfPages);

            return parsedText;
        } finally {
            if (doc != null)
                doc.close();
        }
    }
}

