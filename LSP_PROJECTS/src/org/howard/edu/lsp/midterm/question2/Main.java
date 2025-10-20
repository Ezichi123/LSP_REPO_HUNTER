package org.howard.edu.lsp.midterm.question2;

public class Main {

	public static void main(String[] args) {
		// Normal successful calculations
        System.out.println("Circle radius 3.0 → area = " + AreaCalculator.area(3.0));
        System.out.println("Rectangle 5.0 x 2.0 → area = " + AreaCalculator.area(5.0, 2.0));
        System.out.println("Triangle base 10, height 6 → area = " + AreaCalculator.area(10, 6));
        System.out.println("Square side 4 → area = " + AreaCalculator.area(4));

        // Exception demonstration
        try {
            System.out.println("Circle radius -1.0 → area = " + AreaCalculator.area(-1.0));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

	}
	  /*
     * Explanation:
     * Method overloading is preferred here because all methods conceptually perform
     * the same task — calculating an area — but differ only by the parameters needed.
     * Overloading keeps the interface simple and consistent, unlike having separate
     * method names (circleArea, rectangleArea, etc.), which would clutter the API.
     */
}
