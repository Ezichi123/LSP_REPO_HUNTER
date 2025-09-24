package org.howard.edu.lsp.assignment3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles reading product data from a CSV file.
 */
public class CSVReader {
	private int rowsRead = 0;
    private int rowsSkipped = 0;

    /**
     * Returns the number of rows read
     * @return rowsRead
     */
    public int getRowsRead() { 
    	return rowsRead; 
    	}
    
    /**
     * Returns the number of rows skipped
     * @return rowsSkipped
     */
    public int getRowsSkipped() { 
    	return rowsSkipped; 
    	}

    /**
     * Reads products from a CSV file, skipping the header.
     * 
     * @param inputFile input CSV file
     * @return list of products
     */
    public List<Product> read(File inputFile) {
        List<Product> products = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String header = reader.readLine();
            if (header == null) return products;

            String line;
            while ((line = reader.readLine()) != null) {
                rowsRead++;
                if (line.trim().isEmpty()) {
                    rowsSkipped++;
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length < 4) {
                    rowsSkipped++;
                    continue;
                }

                try {
                	String idStr = parts[0].trim().replaceAll("[^0-9]", "");  // remove quotes & non-numeric
                    String name = parts[1].trim().replace("\"", "");          // remove quotes
                    String priceStr = parts[2].trim().replaceAll("[^0-9.]", ""); // keep only digits + decimal
                    String category = parts[3].trim().replace("\"", "");

                    int id = Integer.parseInt(idStr);
                    double price = Double.parseDouble(priceStr);
                    

                    products.add(new Product(id, name, price, category));

                } catch (NumberFormatException e) {
                    rowsSkipped++;
                }
            }
        } catch (IOException e) {
            System.err.println("Error during extraction: " + e.getMessage());
        }
        return products;
    }
}
