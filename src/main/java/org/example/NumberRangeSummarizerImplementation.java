package org.example;

import java.util.*;

public class NumberRangeSummarizerImplementation implements NumberRangeSummarizer{

    /*
     * Assumptions:
     * The input provided is assumed to be comma-seperated numbers with possible extra white spaces
     * Non-numeric values (float included) will throw a NumberFormatException
     * Null and empty inputs are returned as empty list
     */
    public Collection<Integer> collect(String input) {
        if (input == null || input.isEmpty()){
            return Collections.emptyList();
        }

        String[] inputArray = input.split(",");
        Set<Integer> resultSet = new LinkedHashSet<>();
        for (String s : inputArray) {
            try {
                resultSet.add(Integer.parseInt(s.trim()));
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Invalid input: " + s.trim() + " is not a valid integer");
            }

        }
        return resultSet;
    }

    /*
     * Assumptions:
     * The Input is sorted in ascending order
     * The input has already been validated for non-numerical values in the collect method
     */
    public String summarizeCollection(Collection<Integer> input) {
        List<Integer> inputSet = new ArrayList<>(input);
        StringBuilder result = new StringBuilder();

        int rangeStart = inputSet.get(0);
        int rangeEnd = inputSet.get(0);

        for (int i = 1; i < inputSet.size(); i++) {
            int currentNum = inputSet.get(i);
            if (currentNum == rangeEnd+1){
                rangeEnd = currentNum;
            } else {
                setRange(result, rangeStart, rangeEnd);
                rangeStart = currentNum;
                rangeEnd = currentNum;
            }
        }
        setRange(result, rangeStart, rangeEnd);
        return result.toString();
    }

    public void setRange(StringBuilder result, int rangeStart, int rangeEnd) {
        if (result.length() > 0){
            result.append(",");
        }
        if (rangeStart == rangeEnd){
            result.append(rangeStart);
        } else {
            result.append(rangeStart).append("-").append(rangeEnd);
        }
    }

    public static void main(String[] args) {}
}
