package assignment1;

import java.util.Scanner;

public class Demo {
    public static Scanner scan = new Scanner(System.in);

    private static String green = "\u001B[38;2;11;244;102m";
    private static String red = "\u001B[31m";
    private static String magenta = "\u001B[35m";
    private static String endColor = "\u001B[0m";

    public static void main(String[] args) {

        System.out.println(green+"\n========WELCOME TO CINEMA BOOKING SYSTEM========\n\n"+endColor);

        String date;
        int row, col, totalNumberOfSeats;
        double sPrice, fPrice, pPrice;

        System.out.print("Enter Number of Rows: ");
        row = scan.nextInt();

        col = 10;
        totalNumberOfSeats = row * col;

        scan.nextLine();
        System.out.print("Enter Date: ");
        date = scan.nextLine();

        System.out.print("Enter the Seat Price for Standard:\t $");
        sPrice = scan.nextDouble();

        System.out.print("Enter the Seat Price for Frequent:\t $");
        fPrice = scan.nextDouble();

        System.out.print("Enter the Seat Price for Pensioner:\t $");
        pPrice = scan.nextDouble();

        Cinema cinema = new Cinema(row, col, totalNumberOfSeats, date, sPrice, fPrice, pPrice);

        while (true) {
            int option;

            System.out.println(magenta+"\n\n----------MENU----------"+endColor);
            System.out.println(green);
            System.out.println("\t1. Display Cinema Seats");
            System.out.println("\t2. Book Seats");
            System.out.println("\t3. Refund Seats");
            System.out.println("\t4. Print Statistics Report");
            System.out.println("\t5. Exit");
            System.out.println(endColor);

            do {
                System.out.print(green+"Enter Option: "+endColor);
                option = scan.nextInt();
                if (option <= 0 || option > 5) {
                    System.out.println("Please Select Valid Option!");
                }
            } while (option <= 0 || option > 5);

            switch (option) {
                case 1:
                    System.out.println(magenta+"\n----------Display Cinema Seats----------"+endColor);
                    cinema.printCinema();
                    break;

                case 2:
                    System.out.println(magenta+"\n\n----------Book Cinema Seats----------\n\n"+endColor);
                    bookSeats(cinema);
                    break;

                case 3:
                    System.out.println(magenta+"\n\n----------Refund Cinema Seats----------\n"+endColor);
                    refundSeats(cinema);
                    break;

                case 4:
                    System.out.println(magenta+"\n\n----------Generating Statistics Report----------\n"+endColor);
                    cinema.printStatReport();
                    break;

                case 5:
                    System.out.println(red+"Program Terminated!"+endColor);
                    System.exit(0);
            }

        }
    }

    private static void bookSeats(Cinema cinema) {
        int totalNumberOfSeatsToBook, bookSeatNumber;
        char seatType, temp;
        double personTotal = 0;

        double standardTotal = 0, frequentTotal = 0, pensionareTotal = 0;
        String standardSeats = "", frequentSeats = "", pensionareSeats = "";
        int standardCount = 0, frequentCount = 0, pensionareCount = 0;

        int numberOfSeatsLeftToBook;
        numberOfSeatsLeftToBook = cinema.getTotalNumberOfSeats() - cinema.getNoOfSeatsBooked();

        System.out.println(red + "Maximum Number of Seats That can be Booked: " + numberOfSeatsLeftToBook + endColor +"\n");

        do {
            System.out.print("How Many Seats Do You Want to Book: ");
            totalNumberOfSeatsToBook = scan.nextInt();

            if (totalNumberOfSeatsToBook < 0) {
                System.out.println("Enter a Valid Number!");
            }
            if (totalNumberOfSeatsToBook > numberOfSeatsLeftToBook) {
                System.out.println("Cannot Book This Many Seats!");
            }
        } while (totalNumberOfSeatsToBook < 0 || totalNumberOfSeatsToBook > numberOfSeatsLeftToBook);

        int i = 0;

        while (i < totalNumberOfSeatsToBook) {

            boolean seatIsAvailable;

            do {
                do {
                    System.out.print("Enter The Seat Number: ");
                    bookSeatNumber = scan.nextInt();
                    if (bookSeatNumber <= 0 || bookSeatNumber > cinema.getTotalNumberOfSeats()) {
                        System.out.println("Enter Valid Seat Number!");
                    }
                } while (bookSeatNumber <= 0 || bookSeatNumber > cinema.getTotalNumberOfSeats());

                seatIsAvailable = cinema.isSeatAvailable(bookSeatNumber);

                if (!seatIsAvailable) {
                    System.out.println("This seat is Already Booked!");
                }
            } while (!seatIsAvailable);

            do {
                System.out.print("Enter The Seat Type (S,F,P): ");
                temp = scan.next().charAt(0);
                seatType = Character.toUpperCase(temp);
                if (seatType != 'S' && seatType != 'F' && seatType != 'P') {
                    System.out.println("Enter Valid Seat Type!");
                }
            } while (seatType != 'S' && seatType != 'F' && seatType != 'P');

            cinema.bookSeats(bookSeatNumber, seatType);

            switch (seatType) {
                case 'S':
                    standardTotal += cinema.getsPrice();
                    standardSeats += " " + bookSeatNumber;
                    standardCount++;
                    break;
                case 'F':
                    frequentTotal += cinema.getfPrice();
                    frequentSeats += " " + bookSeatNumber;
                    frequentCount++;
                    break;
                case 'P':
                    pensionareTotal += cinema.getpPrice();
                    pensionareSeats += " " + bookSeatNumber;
                    pensionareCount++;
                    break;
            }

            i++;

        }

        personTotal = standardTotal + frequentTotal + pensionareTotal;

        cinema.setTotalIncomeIncrement(personTotal);

        cinema.generateReceipt(totalNumberOfSeatsToBook, standardTotal, standardSeats, frequentTotal, frequentSeats,
                pensionareTotal, pensionareSeats, personTotal, standardCount, frequentCount, pensionareCount, "Booking");
    }

    private static void refundSeats(Cinema cinema) {
        double personTotal = 0;
        double standardTotal = 0, frequentTotal = 0, pensionareTotal = 0;
        String standardSeats = "", frequentSeats = "", pensionareSeats = "";
        int standardCount = 0, frequentCount = 0, pensionareCount = 0;

        int totalNumberOfSeatsToRefund;
        int refundSeatNumber;

        int numberOfBookedSeats = cinema.getNoOfSeatsBooked();
        System.out.println(red+"Maximum number of Tickets that can be refunded: " + numberOfBookedSeats+endColor+"\n");
        do {
            System.out.print("How many Seats Do you Want to Refund: ");
            totalNumberOfSeatsToRefund = scan.nextInt();

            if (totalNumberOfSeatsToRefund > numberOfBookedSeats) {
                System.out.println("This Many Seats have not been booked!");
            }
            if (totalNumberOfSeatsToRefund <= 0) {
                System.out.println("Enter Valid Number of Seats!");
            }
        } while (totalNumberOfSeatsToRefund > numberOfBookedSeats || totalNumberOfSeatsToRefund <= 0);

        int j = 0;
        while (j < totalNumberOfSeatsToRefund) {
            boolean seatIsAvailable;
            do {
                do {
                    System.out.print("Enter Seat Number: ");
                    refundSeatNumber = scan.nextInt();
                    if (refundSeatNumber > cinema.getTotalNumberOfSeats() || refundSeatNumber <= 0) {
                        System.out.println("Enter Valid Number of Seats!");
                    }
                } while (refundSeatNumber > cinema.getTotalNumberOfSeats() || refundSeatNumber <= 0);

                seatIsAvailable = cinema.isSeatAvailable(refundSeatNumber);

                if (seatIsAvailable) {
                    System.out.println("This Seat is Not Booked before!");
                }

            } while (seatIsAvailable);

            char seatType = cinema.returnSeatType(refundSeatNumber);

            switch (seatType) {
                case 'S':
                    standardTotal += cinema.getsPrice();
                    standardSeats += " " + refundSeatNumber;
                    standardCount++;
                    break;
                case 'F':
                    frequentTotal += cinema.getfPrice();
                    frequentSeats += " " + refundSeatNumber;
                    frequentCount++;
                    break;
                case 'P':
                    pensionareTotal += cinema.getpPrice();
                    pensionareSeats += " " + refundSeatNumber;
                    pensionareCount++;
                    break;
            }
            cinema.refundSeats(refundSeatNumber);
            j++;
        }
        personTotal = standardTotal + frequentTotal + pensionareTotal;

        cinema.setTotalIncomeDecrement(personTotal);

        cinema.generateReceipt(totalNumberOfSeatsToRefund, standardTotal, standardSeats, frequentTotal, frequentSeats,
                pensionareTotal, pensionareSeats, personTotal, standardCount, frequentCount, pensionareCount, "Refund");
    }

}
