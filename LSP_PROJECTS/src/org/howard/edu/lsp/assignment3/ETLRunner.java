
package org.howard.edu.lsp.assignment3;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Coordinates the ETL process: extract, transform, load.
 */
public class ETLRunner {
	 private static final String INPUT_PATH = "data/products.csv";
	 private static final String OUTPUT_PATH = "data/transformed_products.csv";
	 

	public static void main(String[] args) {
		File inputFile = new File(INPUT_PATH);
        File outputFile = new File(OUTPUT_PATH);

        if (!inputFile.exists()) {
            System.err.println("Error: Input file '" + INPUT_PATH + "' does not exist. Exiting.");
            return;
        }

        CSVReader reader = new CSVReader();
        ProductTransformer transformer = new ProductTransformer();
        CSVWriter writer = new CSVWriter();

        // Extract
        List<Product> products = reader.read(inputFile);

        // Transform
        List<Product> transformed = new ArrayList<>();
        for (Product p : products) {
            transformed.add(transformer.transform(p));
        }

        // Load
        writer.write(outputFile, transformed);

        // Summary
        System.out.println("ETL Pipeline Summary:");
        System.out.println("Rows read: " + reader.getRowsRead());
        System.out.println("Rows transformed: " + transformed.size());
        System.out.println("Rows skipped: " + reader.getRowsSkipped());
        System.out.println("Output written to: " + OUTPUT_PATH);
	}

}
