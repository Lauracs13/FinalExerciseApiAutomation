# Final Exercise API Testing

The SuiteTest.xml file runs the BankTransactionsTest class which contains the following test cases:
 
> ➔ @Test 1 > Verify the Endpoint is empty (If it has any data use the DELETE request to clean and
leave it empty).
> 
> ➔ @Test 2 > Initialize the POJO (data response manager) with 10 random data.  
> - The method invoked makes a code verification for avoiding duplicate email accounts and 
> performs the POST request.
> 
> ➔ @Test 3 > Make the GET request, asserting that there are not duplicate email accounts. 
> - The setEmailToBeRepeated method in the Request class can be used to check the 
> functionality of the method used in the test.
> 
> ➔ @Test 4 > Add a test to update an existing AccountNumber.