# AI Prompts Log for Assignment 3  

This file contains several examples of prompts I gave to a generative AI assistant during the redesign of my ETL pipeline for Assignment 3, along with short excerpts of responses.  

---

## Prompt 1: Making the solution object-oriented  
**My prompt:**  
*"I will post my code from assignment 2 and I want you to organize it for me."*  

**AI Response (excerpt):**  
*"We can split your pipeline into classes such as `CSVReader`, `CSVWriter`, `Product`, `ProductTransformer`, and `ETLPipeline`. This will separate responsibilities and make the code more object-oriented."*  

---

## Prompt 2: OOP Concepts  
**My prompt:**  
*"Apart from classes, what other OOP strategies will you be implementing?"*  

**AI Response (excerpt):**  
*"We’ll use encapsulation by keeping fields private, composition by structuring classes together, and polymorphism potential for different transformers in the future."*  

---

## Prompt 3: Fixing transformation logic  
**My prompt:**  
*"This is what I should have in my output... but this is what I currently have... Why are the answers wrong?"*  

**AI Response (excerpt):**  
*"The issue comes from your discount logic. You’re applying a 10% discount to all electronics, including laptops and smartphones. But the expected output shows laptops and smartphones should not be discounted — only standard electronics under $500."*  

---

## Prompt 4: Markdown formatting  
**My prompt:**  
*"How do you specify headings and subheadings in a .md file?"*  

**AI Response (excerpt):**  
*"You use `#` for headings and `##`, `###` for subheadings. For example: `# Heading 1`, `## Heading 2`."*  

---


