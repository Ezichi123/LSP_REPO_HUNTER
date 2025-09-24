
package org.howard.edu.lsp.assignment3;

/**
 * This class represents a product with attributes extracted from the CSV file
 */
public class Product {
	private int id;
	private String name;
	private double price;
	private String category;
	private String priceRange;
	
	/**
	 *  A constructor that sets the attributes of a product
	 */
	
	public Product(int id, String name, double price, String category) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.category = category;
		this.priceRange = "";
	}
	
	//Getter methods
	
	/**
	 * This method returns the product ID
	 * @return id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * This method returns the product name
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * This method returns the price of the product
	 * @return price
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * This method returns the product category
	 * @return category
	 */
	public String getCategory() {
		return category;
	}
	
	
	//Setter methods
	/**
	 * This method sets the product name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * This method sets the product price
	 * @param price
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	/**
	 * This method sets the product category
	 * @param category
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	
	/**
	 * This method sets the product price range
	 * @param priceRange
	 */
	public void setPriceRange(String priceRange) {
		this.priceRange = priceRange;
	}
	
	/**
     * Converts this product into a CSV row string.
     */
    public String toCSV() {
        return id + "," + name + "," + String.format("%.2f", price) + "," + category + "," + priceRange;
    }
}
