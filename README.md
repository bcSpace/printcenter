# printcenter

### Sample Project for Solu

# Summary

Goal is to sort printing documents from print job requests and then send them out to be scheduled. 

### Main 

•	Entry point for the program
• Empty, use for testing if needed

### Printing Controller

•	Where printing jobs are sent
•	Can merge or delete jobs
•	Sorts documents with the help of GeneralUtil

### Print Api

•	Empty class just shows functions 

### General Util

•	Used for sorting documents based on priority and document grouping

### PrintDocument

• Model for documents to be printed
• Includes the priority 

### DocumentMetaData

• Includes the actual document data

### PrintJob

• Wrapper for multiple document prints with a target location and a date for the print

• Also includes a job id

### AppTest

• Varius unit tests to make sure the sorting works
