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
        PDDocument pdDoc = PDDocument.load(inputFile);
        System.out.println("Document exist: " + inputFile.exists());

        //To show the number of pages in the PDF file
        int numOfPages = pdDoc.getNumberOfPages();
        System.out.println("Number of pages in the resume: " + numOfPages);

        //To print the text in the PDF file
        String parsedText = pdfStripper.getText(pdDoc);

        return parsedText;
    }
}

