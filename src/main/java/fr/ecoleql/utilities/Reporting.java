package fr.ecoleql.utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Reporting {

	static LocalDateTime now = LocalDateTime.now();
	static String folderPath = "test-reporting/";
	static String fileExtension = ".png";

	public static void takeScreenShot(WebDriver driver, String description) throws Exception {
		// Call getScreenshotAs method to create image file
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// Move image file to new destination
		File destinationFile = new File(folderPath + "screenshots/" + description + ".png");
		// Copy file at destination
		FileUtils.copyFile(screenshotFile, destinationFile);
	}

	public static void writeReport(List<Error> errors) throws Exception {
		PrintWriter out = new PrintWriter(new FileWriter(folderPath + "test-reporting.txt", false), true);
		for (int i = 0; i < errors.size(); i++) {
			String error = errors.get(i).toString();
			String errorDesc = error.substring(error.indexOf(": ") + 2);
			out.println(now.toString());
			out.println(errorDesc);
			System.out.println(now.toString());
			System.out.println(errorDesc);
		}
		out.close();
	}

}
