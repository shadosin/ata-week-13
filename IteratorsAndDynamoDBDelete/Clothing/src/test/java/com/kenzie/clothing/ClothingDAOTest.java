package com.kenzie.clothing;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ClothingDAOTest {

    @Mock
    DynamoDBMapper mapper;

    @BeforeEach
    public void setup() {
        initMocks(this);
    }

    @Test
    public void getActiveClothingItem_activeItem_returnsClothingItem() {
        //GIVEN
        Clothing clothing = new Clothing();
        String partitionKey = "sh_39802342";
        clothing.setId(partitionKey);
        clothing.setStatus("active");
        clothing.setClothingType("shirt");
        clothing.setColor("blue");
        clothing.setPrice(10.53);
        when(mapper.load(Clothing.class, partitionKey, "active")).thenReturn(clothing);

        //WHEN
        ClothingDAO clothingDAO = new ClothingDAO(mapper);
        Clothing actualClothing = clothingDAO.getActiveClothingItem(partitionKey);

        //THEN
        assertEquals(partitionKey, actualClothing.getId(), "The clothing item with id 'sh_39802342' is " +
                "currently active and should be returned by the method getActiveClothingItem()");
    }

    @Test
    public void getActiveClothingItem_inactiveItem_returnsNull() {
        //GIVEN
        String partitionKey = "je_398020b8";
        when(mapper.load(Clothing.class, partitionKey, "active")).thenReturn(null);

        //WHEN
        ClothingDAO clothingDAO = new ClothingDAO(mapper);
        Clothing actualClothing = clothingDAO.getActiveClothingItem(partitionKey);

        //THEN
        assertEquals(null, actualClothing, "The clothing item with id 'je_398020b8' is not currently " +
                "active and shouldn't be returned by the method getActiveClothingItem()");
    }
}
