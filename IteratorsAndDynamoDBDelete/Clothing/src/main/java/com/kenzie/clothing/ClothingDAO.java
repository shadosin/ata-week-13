package com.kenzie.clothing;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

public class ClothingDAO {

    private DynamoDBMapper mapper;

    /**
     * Allows access to and manipulation of Clothing objects from the data store.
     * @param mapper Access to DynamoDB
     */
    public ClothingDAO(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Uses the load() method to get an item from the ClothingItems table given a partition key value and a hardcoded
     * sort key value that is always 'active'.
     * @param partitionKey the given partition key value used to find the correct item to load
     * @return the Clothing instance that's been loaded from the table
     */
    public Clothing getActiveClothingItem(String partitionKey) {
        return new Clothing();
    }

    /**
     * Uses the save() method to save a given item to the ClothingItems table.
     * @param clothing given Clothing instance that's saved to the table
     */
    public void saveClothingItem(Clothing clothing) {
        mapper.save(clothing);
    }
}
