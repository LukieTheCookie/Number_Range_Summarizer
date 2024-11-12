package org.example;

import org.junit.Test;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

public class NumberRangeSummarizerImplementationTest {

    /*
     * Assumptions:
     * The Input is always in ascending order
     * The input provided is assumed to be comma-seperated numbers with possible extra white spaces
     * The collect method MUST be called first to validate and parse the input
     * Inputs may not contain numbers in a sequential range
     * Non-numeric values (float included) will throw a NumberFormatException
     * Null and empty inputs are returned as empty list
     * The input may contain duplicated which are removed in the process
     */

    public NumberRangeSummarizerImplementation summarizer = new NumberRangeSummarizerImplementation();

    public String outputString(String input){
        return summarizer.summarizeCollection(
                summarizer.collect(input));
    }

    @Test
    public void nullInput() {
        assertEquals(summarizer.collect(null), Collections.emptyList());
    }

    @Test
    public void emptyString(){
        assertEquals(summarizer.collect(""), Collections.emptyList());
    }

    @Test
    public void summarizeCollection() {
        String input = "3,5,19,20,21,23,24,25,71";
        assertEquals(outputString(input), "3,5,19-21,23-25,71");
    }

    @Test
    public void summarizeLargeNumbers() {
        String input = "1000,1001,1002,2000,2001,3000";
        assertEquals(outputString(input), "1000-1002,2000-2001,3000");
    }

    @Test
    public void floatValues(){
        String input = "17.1,17.2,17.3, 19,25,89,100,152";
        assertThrows(NumberFormatException.class, () -> summarizer.collect(input));
    }

    @Test
    public void largeInput(){
        StringBuilder largeInput = new StringBuilder();
        for (int i = 0; i < 5000000; i++) {
            largeInput.append(i).append(",");
        }
        largeInput.append(5000001);
        assertEquals(outputString(largeInput.toString()), "0-4999999,5000001");
    }

    @Test
    public void inputWithSpaces() {
        String input = " 3 , 5 , 8 , 9 , 10";
        assertEquals(outputString(input), "3,5,8-10");
    }

    @Test
    public void randomSequenceInput(){
        String input = "3,2,5,8,15,1";
        assertEquals(outputString(input), "3,2,5,8,15,1");
    }

    @Test
    public void duplicateValues(){
        String input = "1,3,5,6,7,5,9,3";
        assertEquals(outputString(input), "1,3,5-7,9");
    }

    @Test
    public void descendingValues(){
        String input = "8,7,6,3,2,1";
        assertEquals(outputString(input), "8,7,6,3,2,1");
    }

    @Test
    public void negativeValues(){
        String input = "-7,-6,-5,-2,0,2,3,4";
        assertEquals(outputString(input), "-7--5,-2,0,2-4");
    }

    @Test
    public void singleValue(){
        assertEquals(outputString("1"), "1");
    }

    @Test
    public void nonNumericalValues(){
        String input = "a, 3, b, z";
        assertThrows(NumberFormatException.class, () -> summarizer.collect(input));
    }

    @Test
    public void whiteSpaceValues(){
        String input = " ";
        assertThrows(NumberFormatException.class, () -> summarizer.collect(input));
    }
}