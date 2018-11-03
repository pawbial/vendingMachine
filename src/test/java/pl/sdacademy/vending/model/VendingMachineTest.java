package pl.sdacademy.vending.model;

import org.junit.Test;
import pl.sdacademy.vending.util.Configuration;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class VendingMachineTest {


    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenZeroRowsConfigured() {
        // Given
        Configuration configuration = mock(Configuration.class);

        doReturn(0L).when(configuration).getLongProperty(eq("machine.size.rows"), anyLong());

        doReturn(4L).when(configuration).getLongProperty(eq("machine.size.cols"), anyLong());


        // When
        new VendingMachine(configuration);

        // Then
        fail("Exception should arise");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenZeroColumnsConfigured() {
        // Given
        Configuration configuration = mock(Configuration.class);
        doReturn(6L).when(configuration).getLongProperty(eq("machine.size.rows"), anyLong());

        doReturn(0L).when(configuration).getLongProperty(eq("machine.size.cols"), anyLong());
        // When
        new VendingMachine(configuration);
        // Then
        fail("Exception should arise");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenRowsMoreThan26() {
        // Given
        Configuration configuration = mock(Configuration.class);
        doReturn(27L).when(configuration).getLongProperty(eq("machine.size.rows"), anyLong());

        doReturn(4L).when(configuration).getLongProperty(eq("machine.size.cols"), anyLong());
        // When
        new VendingMachine(configuration);
        // Then
        fail("Exception should arise");

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenColumnsMoreThan9() {
        // Given
        Configuration configuration = mock(Configuration.class);
        doReturn(6L).when(configuration).getLongProperty(eq("machine.size.rows"), anyLong());

        doReturn(10L).when(configuration).getLongProperty(eq("machine.size.cols"), anyLong());
        // When
        new VendingMachine(configuration);
        // Then
        fail("Exception should arise");


    }
}