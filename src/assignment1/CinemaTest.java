package assignment1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CinemaTest {
    private Cinema cinema;

    @BeforeEach
    void setUp() {
        // You can initialize the Cinema object here or in each test method
        cinema = new Cinema(5, 10, 50, "2023-01-01", 10.0, 15.0, 8.0);
        cinema.initializeCinema(); // Optional: Initialize the cinemaHall array
    }
    
    //JUnit test cases for isSeatAvailabe() Method

    @Test
    void atestIsSeatAvailableWhenSeatIsAvailable() {
        assertTrue(cinema.isSeatAvailable(1));
    }

    @Test
    void atestIsSeatAvailableWhenSeatIsBooked() {
        cinema.bookSeats(1, 'S');
        assertFalse(cinema.isSeatAvailable(1));
    }

    @Test
    void atestIsSeatAvailableWithInvalidSeatNumber() {
        assertThrows(IndexOutOfBoundsException.class, () -> cinema.isSeatAvailable(0));
    }
    
    
    //JUnit test cases for bookSeats() Method
    @Test
    void btestIsSeatAvailableWhenSeatIsAvailable() {
        assertTrue(cinema.isSeatAvailable(1));
    }

    @Test
    void btestIsSeatAvailableWhenSeatIsBooked() {
        cinema.bookSeats(1, 'S');
        assertFalse(cinema.isSeatAvailable(1));
    }

    @Test
    void btestIsSeatAvailableWithInvalidSeatNumber() {
        assertThrows(IndexOutOfBoundsException.class, () -> cinema.isSeatAvailable(0));
    }

    @Test
    void btestIsSeatAvailableWithInvalidNegativeSeatNumber() {
        assertThrows(IndexOutOfBoundsException.class, () -> cinema.isSeatAvailable(-1));
    }

    @Test
    void btestIsSeatAvailableWithInvalidLargeSeatNumber() {
        assertThrows(IndexOutOfBoundsException.class, () -> cinema.isSeatAvailable(60));
    }
    
    
    //JUnit test Cases for refundSeats() Method
    @Test
    void ctestRefundSeatsWhenSeatIsBooked() {
        cinema.bookSeats(1, 'S');
        cinema.refundSeats(1);
        assertTrue(cinema.isSeatAvailable(1));
        assertEquals(0, cinema.getNoOfSeatsBooked());
    }

    @Test
    void ctestRefundSeatsWithInvalidSeatNumber() {
        assertThrows(IndexOutOfBoundsException.class, () -> cinema.refundSeats(0));
    }

    @Test
    void ctestRefundSeatsWithInvalidNegativeSeatNumber() {
        assertThrows(IndexOutOfBoundsException.class, () -> cinema.refundSeats(-1));
    }

    @Test
    void ctestRefundSeatsWithInvalidLargeSeatNumber() {
        assertThrows(IndexOutOfBoundsException.class, () -> cinema.refundSeats(60));
    }
    
}
