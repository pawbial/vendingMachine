package pl.sdacademy.vending.model;

import org.junit.Test;
import pl.sdacademy.vending.util.Configuration;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;

public class VendingMachineTest {


    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenZeroRowsConfigured() {
        // Given
        Configuration configuration = getConfiguration(0L, 4L);


        // When
        new VendingMachine(configuration);

        // Then
        fail("Exception should arise");
    }

    private Configuration getConfiguration(long l, long l2) {
        Configuration configuration = mock(Configuration.class);

        doReturn(l).when(configuration).getLongProperty(eq("machine.size.rows"), anyLong());

        doReturn(l2).when(configuration).getLongProperty(eq("machine.size.cols"), anyLong());
        return configuration;
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

    @Test
    public void shoudlBeAbleToAddTryToEmptySpot() {
        // Given
        Tray tray = Tray.builder("A2").build();


        Configuration configuration = mock(Configuration.class);
        doReturn(6L).when(configuration).getLongProperty(eq("machine.size.rows"), anyLong());

        doReturn(4L).when(configuration).getLongProperty(eq("machine.size.cols"), anyLong());

        VendingMachine testVendingMachine = new VendingMachine(configuration);
        // When

        boolean placed = testVendingMachine.placeTray(tray);

        // Then
        assertTrue(placed);
        testVendingMachine.getTrayAtPosition(6,0);
    }

}