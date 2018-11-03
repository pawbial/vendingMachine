package pl.sdacademy.vending.util;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class ConfigurationTest {

    private Configuration testConfig;

    @Before
    public void init() {
        testConfig = new Configuration();
    }

    @Test
    public void shouldReturnDefaultStringValueWhenPropertyIsUnknown() {
        // Given
        String unknownPropertyName = "sadgikuhy";
        String expectedDefault = "ABCD";
        // When
        String propertyValue = testConfig.getStringProperty(unknownPropertyName, expectedDefault);
        // Then
        assertEquals(expectedDefault, propertyValue);
    }


    @Test
    public void shouldReturnProperStringValueFromConfigFile() {
        // Given
        String knownValue = "test.property.string";
        String expectedDefault = "ABC";
        String expectedValue = "qwerty";
        // When
        String propertyValue = testConfig.getStringProperty(knownValue, expectedDefault);
        // Then
        assertEquals(propertyValue, expectedValue);
    }

    @Test
    public void shouldReturnAcutalLongValueFromFile() {
        // Given
        String knownValue = "test.property.long";
        Long expectedDefault = 8L;

        // When
        Long propertyValue = testConfig.getLongProperty(knownValue, expectedDefault);
        // Then
        System.out.println(propertyValue);
        assertEquals(propertyValue, knownValue);

    }

    @Test
    public void shouldReturnDefaultLongValue() {
        // Given
        String unknownValue = "ABC";
        Long expectedDefault = 5L;

        // When
        Long propertyValue = testConfig.getLongProperty(unknownValue, expectedDefault);
        // Then
        assertEquals(propertyValue, expectedDefault);
    }


}