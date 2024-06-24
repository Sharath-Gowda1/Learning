package com.testsigma.addons.web;

import com.testsigma.sdk.ApplicationType;
import com.testsigma.sdk.Result;
import com.testsigma.sdk.StepActionType;
import com.testsigma.sdk.WebAction;
import com.testsigma.sdk.annotation.Action;
import com.testsigma.sdk.annotation.TestData;
import lombok.Data;
import org.openqa.selenium.NoSuchElementException;

@Data
@Action(actionText = "Compare <testdata1> with <Value1> AND  <testdata2> with <Value2> using operator Operator",
        description = "Thia addon will validate the two condition",
        applicationType = ApplicationType.WEB,
        actionType = StepActionType.WHILE_LOOP,
        useCustomScreenshot = false)

public class Conditional_While extends WebAction 
{

	@TestData(reference = "testdata1")
    private com.testsigma.sdk.TestData testData1;
 
    @TestData(reference = "Value1")
    private com.testsigma.sdk.TestData testData2;

    @TestData(reference = "testdata2")
    private com.testsigma.sdk.TestData testData3;
    
    @TestData(reference = "Value2")
    private com.testsigma.sdk.TestData testData4;
    
    @TestData(reference = "operator", allowedValues = {"==", "!=", "<=", ">=", "<",">" })
    private com.testsigma.sdk.TestData testData5;


    @Override
    public com.testsigma.sdk.Result execute() throws NoSuchElementException 
    {
        Result result = Result.SUCCESS;
        logger.info("Initiating execution");
        
        logger.debug("testdata1 => " + testData1.getValue().toString());
        logger.debug("value1 => " + testData2.getValue().toString());
        logger.debug("testdata2 => " + testData3.getValue().toString());
        logger.debug("value2 => " + testData4.getValue().toString());
        
        String data1 = testData1.getValue().toString();
        String value1 = testData2.getValue().toString();
        String data2 = testData3.getValue().toString();
        String value2 = testData4.getValue().toString();
        String operator = testData5.getValue().toString();
        
        Boolean blResult = false;
        
        switch (operator) {
        case "==":
            if (data1.equals(value1) && data2.equals(value2))
                blResult = true;
            break;
        case "!=":
            if (!data1.equals(value1) && !data2.equals(value2))
                blResult = true;
            break;
        case "<":
            try {
                if (Integer.parseInt(data1) < Integer.parseInt(value1) && Integer.parseInt(data2) < Integer.parseInt(value2))
                    blResult = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format for comparison.");
            }
            break;
        case ">":
            try {
                if (Integer.parseInt(data1) > Integer.parseInt(value1) && Integer.parseInt(data2) > Integer.parseInt(value2))
                    blResult = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format for comparison.");
            }
            break;
        case "<=":
            try {
                if (Integer.parseInt(data1) <= Integer.parseInt(value1) && Integer.parseInt(data2) <= Integer.parseInt(value2))
                    blResult = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format for comparison.");
            }
            break;
        case ">=":
            try {
                if (Integer.parseInt(data1) >= Integer.parseInt(value1) && Integer.parseInt(data2) >= Integer.parseInt(value2))
                    blResult = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format for comparison.");
            }
            break;
        default:
            System.out.println("Invalid comparison operator.");
    }
    
    if (blResult) {
        setSuccessMessage("Successfully verified both the conditions");
    } else {
    	setErrorMessage("One or both conditions are not metching");
        return Result.FAILED;    
        }
    return result;
    }
}