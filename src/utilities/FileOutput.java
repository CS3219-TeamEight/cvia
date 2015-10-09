package utilities;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileOutput {
	public void write(String outputPath, String text) throws IOException, FileNotFoundException {
		PrintWriter output = new PrintWriter((new FileWriter(outputPath)));
		output.print(text);
		output.close();
	}
}
