# ETL Pipeline Assignment

## How to Run
1. Place `products.csv` in the `data/` directory.
2. Compile the program:
   javac src/org/howard/edu/lsp/assignment2/ETLPipeline.java
3. Run the program from the project root:
   java org.howard.edu.lsp.assignment2.ETLPipeline
4. Output will be written to:
   data/transformed_products.csv

## Assumptions
- Input file always has a header row.
- CSV values do not contain embedded commas or quotes (per assignment instructions).
- If the file is missing or empty, the program handles it gracefully.

## Design Notes
- Extract, transform, and load are implemented in separate methods.
- Transformation order:
  1. Uppercase product names
  2. Apply 10% discount if Electronics
  3. Round prices (2 decimals, half-up)
  4. Reclassify Electronics > $500 → Premium Electronics
  5. Add PriceRange column
- Rounding implemented with `BigDecimal` to avoid floating-point issues.

## Testing
Program was tested using sample cases provided within assignment instructions
- Case 1. Empty Input; program outputs only headers to the output file
- Case 2. Missing input file; program prints a clear error message and stops
- Case 3. Normal input with a populated data/products.csv file; program outputs results to output file with all transformations applied

## AI Usage
I used ChatGPT for this assignment. I started by copying the assignment instructions and pasting as a prompt to ChatGPT. I asked it to explain some requirements that I did not understand(like the requirement to use relative paths), after which I asked it for the code.
The code was rough, but I polished it by arranging each of the tasks into separate methods to improve logical readability and ease of testing. There were a number of errors, but they were easy to find and fix.

Some prompts I used to eliminate errors include:

**Prompt 1:**  
"where do the quotes come from because they are not in the product file"

**AI Response (excerpt):**  
"That usually happens if the program doesn’t clean trailing quotes... Try using `.replace("\"", "")` on each field."

I then modified my code to strip quotes from category and name fields using `.replace("\"", "")`.  

**Prompt 2: **
"what should my csv file look like"

**AI Response (excerpt):**
"ProductID,Name,Price,Category
1,Book,12.99,Education
2,Laptop,999.99,Electronics
3,Notebook,2.49,Stationery
4,Headphones,199.99,Electronics
5,Pencil,0.99,Stationery
6,Smartphone,699.49,Electronics"

I then went ahead to use this to test my program to get the golden output as described in the assignment requirements.

## External Sources
- None (all other help was from AI and class materials).
