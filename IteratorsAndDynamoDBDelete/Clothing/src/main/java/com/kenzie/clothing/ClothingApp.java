package com.kenzie.clothing;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.kenzie.clothing.dependency.DynamoDbClientProvider;

public class ClothingApp {

    /**
     * Test your methods on a real database connection. Should print 'sh_39802342' and then 'null'.
     * @param args main method values
     */
    public static void main(String[] args) {
        //GIVEN (1st example)
        DynamoDBMapper mapper = new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient());
        String partitionKeyValue = "sh_39802342";

        //WHEN (1st example)
        ClothingDAO clothingDAO = new ClothingDAO(mapper);
        Clothing clothingValue = clothingDAO.getActiveClothingItem(partitionKeyValue);

        //THEN (1st example)
        System.out.println("id value sh_39802342: " + clothingValue.getId());

        //GIVEN (2nd example)
        String partitionKeyNull = "je_398020b8";

        //WHEN (2nd example)
        Clothing clothingNull = clothingDAO.getActiveClothingItem(partitionKeyNull);

        //THEN (2nd example)
        System.out.println("null value: " + clothingNull);
    }
}
