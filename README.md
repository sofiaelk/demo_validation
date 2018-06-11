# demo_validation
Maven project for data validation, what it does : 
  - Reads URL of json file from input,
  - Counts number of json objects in the file,
  - Counts null occurrences in json file for each parameter,
  - Displays number of errors fond; if found, and displays object where the error was found,
  - Documents if json file is valid to given schema or not.
  
The tests that were considered for this project, and were manually tested during coding :
  - test with empty json file (from url and local file)
  - test with invalid json file, wrong json format
  - test with wrong parameter type
  - test with non existing required parameter
  - test with other type of file
  - test with all parameters "null", and see if the correct value of occurrences was displayed for each one
  - test with invalid URL
 
