
package org.howard.edu.lsp.assignment3;

import java.io.*;
import java.util.List;
/**
 * Handles writing the results to the csv file
 */
public class CSVWriter {
	/**
     * Writes products to a CSV file with header.
     * @param outputFile, the file to be written to
     * @param products, the list of products to be written to the output file
     */
    public void write(File outputFile, List<Product> products) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write("ProductID,Name,Price,Category,PriceRange\n");
            for (Product p : products) {
                writer.write(p.toCSV() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error during load: " + e.getMessage());
        }
    }
}
