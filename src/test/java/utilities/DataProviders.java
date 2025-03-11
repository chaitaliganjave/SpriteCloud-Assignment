package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "Logindata")
	public String[][] getData() throws IOException
	{
		String path=".\\testData\\TestDataLogin.xlsx"; //taking excel file from testdata
		ExcelUtility xlutil=new ExcelUtility(path); //creating an object for ExcelUtility
		
		int totalrows=xlutil.getRowCount("Credentials"); //get the total row count from excel
		int totalcolumns=xlutil.getCellCount("Credentials", 1); ////get the total column count from excel
		
		String loginData[][]=new String[totalrows][totalcolumns]; //created new 2 dimensional array which can store data from excel
		
		//first we need to read the data from excel - to store it into 2 dimensional array
		for (int i = 1; i < totalrows; i++) { //rows- we are reading from 1 because at 0 index headers of table are present and we should not pass headers
			
			for (int j = 0; j < totalcolumns; j++) { //columns-
				loginData[i-1][j]=xlutil.getCellData("Credentials", i, j); //passing excel data to array. i value we kept 1 in for loop to avoid header part but while passing it into array it should start from 0 i.e i-1	
			}
			
		}
		
		return loginData; //returning 2 dimensional array
		
	}

}
