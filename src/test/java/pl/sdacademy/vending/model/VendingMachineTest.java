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
    public void shouldBeAbleToAddTrayToEmptySpot() {
        // given
        Tray tray = Tray.builder("A2").build();
        Configuration config = mock(Configuration.class);
        doReturn(6L)
                .when(config)
                .getLongProperty(
                        eq("machine.size.rows"),
                        anyLong()
                );
        doReturn(4L)
                .when(config)
                .getLongProperty(
                        eq("machine.size.cols"),
                        anyLong()
                );
        VendingMachine testedMachine = new VendingMachine(config);

        // when
        boolean placed = testedMachine.placeTray(tray);


        // then
        assertTrue(placed);
        assertEquals(tray, testedMachine.getTrayAtPosition(0, 1).get());
    }

    @Test
    public void shouldNotBeAbleToAddTrayToTakenSpot() {
        // Given
        Tray tray = Tray.builder("A2").build();
        Tray secondTray = Tray.builder("A2").build();


        Configuration configuration = mock(Configuration.class);
        doReturn(6L).when(configuration).getLongProperty(eq("machine.size.rows"), anyLong());

        doReturn(4L).when(configuration).getLongProperty(eq("machine.size.cols"), anyLong());

        VendingMachine testedMachine = new VendingMachine(configuration);
        // When
        boolean firstTrayPlacementResult = testedMachine.placeTray(tray);
        boolean secondTrayPlacementResult = testedMachine.placeTray(secondTray);
        // Then
        assertTrue(firstTrayPlacementResult);
        assertFalse(secondTrayPlacementResult);
        assertEquals(tray, testedMachine.getTrayAtPosition(0, 1).get());
    }

    @Test
    public void shouldNotBeAbleToAddTrayToNonExistingPosition() {
        // given
        Tray tray = Tray.builder("$$").build();
        Configuration config = mock(Configuration.class);
        doReturn(6L)
                .when(config)
                .getLongProperty(
                        eq("machine.size.rows"),
                        anyLong()
                );
        doReturn(4L)
                .when(config)
                .getLongProperty(
                        eq("machine.size.cols"),
                        anyLong()
                );
        VendingMachine testedMachine = new VendingMachine(config);

        // When
        boolean placed = testedMachine.placeTray(tray);

        // Then
        assertFalse(placed);


    }

}