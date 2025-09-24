
package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal; 
import java.math.RoundingMode;

/**
 * This class performs the product transformations
 */
public class ProductTransformer {
	 /**
     * Applies transformations to a product:
     * 1. Uppercase name
     * 2. Discount if electronics
     * 3. Recategorization if > $500
     * 4. Price range assignment
     */
    public Product transform(Product p) {
        // 1. Uppercase name
        p.setName(p.getName().toUpperCase());

        double price = p.getPrice();

        // 2. Discount for electronics (apply BEFORE rounding)
        if (p.getCategory().equalsIgnoreCase("Electronics")) {
            price = price * 0.9;  // apply discount
        }

        // Round once after all price math
        price = round(price, 2);
        p.setPrice(price);

        // 3. Recategorize if necessary
        if (p.getCategory().equalsIgnoreCase("Electronics") && price > 500.0) {
            p.setCategory("Premium Electronics");
        }

        // 4. Price range
        p.setPriceRange(getPriceRange(price));

        return p;
    }

    
    private double round(double value, int places) {
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    private String getPriceRange(double price) {
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
