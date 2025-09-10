package org.howard.edu.lsp.assignment2;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ETLPipeline {
	private static final String INPUT_PATH = "data/products.csv";
	private static final String OUTPUT_PATH = "data/transformed_products.csv";
	
	private static int rowsRead = 0;
	private static int rowsTransformed = 0;
	private static int rowsSkipped = 0;

	public static void main(String[] args) {
		File inputFile = new File(INPUT_PATH);
		
		//Handle missing file
		if(!inputFile.exists()) {
			System.err.println("Error: Input file '" + INPUT_PATH + "' does not exist. Exiting");
			return;
		}
		
		//Run ETL
		
		List <String[]> rawData = extract(inputFile);
		List <String[]> transformedData = transform(rawData);
		load(transformedData, new File(OUTPUT_PATH));
		
		//Print Summary
		System.out.println("ETL Pipeline Summary:");
        System.out.println("Rows read: " + rowsRead);
        System.out.println("Rows transformed: " + rowsTransformed);
        System.out.println("Rows skipped: " + rowsSkipped);
        System.out.println("Output written to: " + OUTPUT_PATH);

	}
	
	/**
	 * This method reads data from the .csv file, while skipping the header
	 * @param inputFile : The .csv file to be read
	 * @return rows: a list of String arrays
	 */
	private static List<String[]> extract(File inputFile) {
		List<String[]> rows = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
			String header = reader.readLine();
			if(header == null) {
				return rows;
			}
			String line;
			while ((line = reader.readLine()) != null) {
				rowsRead++;
				if(line.trim().isEmpty()) {
					rowsSkipped++;
					continue;
				}
				rows.add(line.split(","));
			}
		} catch (IOException e) {
			System.err.println("Error during extraction: " + e.getMessage());
		}
		return rows;
	}
	
	/**
	 * This method applies the required transformations in the correct order
	 * @param rows : a list of String arrays
	 * @return results : a list of String arrays
	 */
	private static List<String[]> transform(List<String[]> rows){
		List<String[]> results = new ArrayList<>();
		
		for (String[] parts : rows) {
			if (parts.length < 4) {
				rowsSkipped++;
				continue;
			}
			
			try {
				 String productIdStr = parts[0].trim().replaceAll("[^0-9]", "");
				 String name = parts[1].trim().replace("\"", "").toUpperCase();
		            String priceStr = parts[2].trim().replaceAll("[^0-9.]", "");
		            String category = parts[3].trim().replace("\"", "");

		            int productId = Integer.parseInt(productIdStr);
		            double price = Double.parseDouble(priceStr);
				
				//Discount for electronics
				if (category.equalsIgnoreCase("Electronics")) {
					price = round(price * 0.9,2);
				} else {
					price = round(price, 2);
				}
				
				//Recategorization
				if (category.equalsIgnoreCase("Electronics") && price >500.00) {
					category = "Premium Electronics";
				}
				
				//Price Range
				String priceRange = getPriceRange(price);
				
				String[] transformedRow = {
					String.valueOf(productId),
					name,
					String.format("%.2f", price),
					category,
					priceRange
				};
				
				results.add(transformedRow);
				rowsTransformed++;
			} catch (NumberFormatException e) {
				rowsSkipped++;
			}
		}
		return results;
	}
	
	/**
	 * This method writes the transformed data into a new .csv file
	 * @param rows : a list of String arrays
	 * @param outputFile : 
	 */
	
	private static void load(List<String[]> rows, File outputFile) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
			//Always write header
			writer.write("ProductID, Name, Price, Category, PriceRange\n");
			
			//write transformed rows
			for (String[] row : rows) {
				writer.write(String.join(",", row) + "\n");
			}
		} catch (IOException e) {
			System.err.println("Error during load: " + e.getMessage());
		}
	}
	/**
	 * round: This method rounds a specified value, into a chosen number of decimal places
	 * @param value: The number you want to round up
	 * @param places: The number of places you are rounding up to
	 * @return bd.doubleValue: returns the rounded number
	 */
	private static double round(double value, int places) {
		BigDecimal bd = new BigDecimal(Double.toString(value));
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
	
	/**
	 * This method returns the price range based on predefined definitions
	 * @param price
	 * @return String value
	 */
	private static String getPriceRange(double price) {
		if (price <= 10.00) {
			return "Low";
		
		} else if (price <= 100.00) {
			return "Medium";
		} else if (price <= 500.00) {
			return "High";
		} else {
			return "Premium";
		}
	}
	
	
}


