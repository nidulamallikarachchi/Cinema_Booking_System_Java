package assignment1;

class Cinema {
    // Instance Variables
    private int row;
    private int col;
    private int totalNumberOfSeats;
    private String date;

    private double sPrice;
    private double fPrice;
    private double pPrice;

    private char[] cinemaHall;

    private double totalIncome;
    private int noOfSeatsBooked;

    private String green = "\u001B[38;2;11;244;102m";
    private String red = "\u001B[31m";
    private String blue = "\u001B[94m";
    private String endColor = "\u001B[0m";

    // Constructor
    Cinema(int row, int col, int totalNumberOfSeats, String date, double sPrice, double fPrice, double pPrice) {
        this.row = row;
        this.col = col;
        this.totalNumberOfSeats = totalNumberOfSeats;
        this.date = date;

        this.sPrice = sPrice;
        this.fPrice = fPrice;
        this.pPrice = pPrice;

        this.cinemaHall = new char[totalNumberOfSeats];

        this.totalIncome = 0;
        this.noOfSeatsBooked = 0;
        initializeCinema();
    }

    // Getters
    public double getsPrice() {
        return sPrice;
    }

    public double getfPrice() {
        return fPrice;
    }

    public double getpPrice() {
        return pPrice;
    }

    public int getNoOfSeatsBooked() {
        return noOfSeatsBooked;
    }

    public int getTotalNumberOfSeats() {
        return totalNumberOfSeats;
    }

    // Setters
    public void setTotalIncomeIncrement(double personTotal) {
        this.totalIncome += personTotal;
    }

    public void setTotalIncomeDecrement(double refundTotal) {
        this.totalIncome -= refundTotal;
    }

    // Methods
    public void initializeCinema() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int arrayIndex = (i * 10) + j;
                cinemaHall[arrayIndex] = '-';
            }
        }
    }

    public void printCinema() {
        System.out.println();
        System.out.println(green+"=====================================================================================");
        System.out.println("====================================Cinema Screen====================================");
        System.out.println("=====================================================================================\n"+endColor);

        int seatNumber = 1;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (j == 5) {
                    System.out.print("\t\t");
                }
                int arrayIndex = seatNumber - 1;
                if (cinemaHall[arrayIndex] != '-') {
                    System.out.print(red);
                    System.out.printf("%02d", seatNumber);
                    System.out.print(":" + cinemaHall[arrayIndex] + "\t");
                    System.out.print(endColor);
                } else {
                    System.out.print(green);
                    System.out.printf("%02d", seatNumber);
                    System.out.print(":" + cinemaHall[arrayIndex] + "\t");
                    System.out.print(endColor);
                }

                seatNumber++;
            }
            System.out.println();
        }
    }

    public boolean isSeatAvailable(int seatNumber) {
        int arrayIndex = seatNumber - 1;
        return cinemaHall[arrayIndex] == '-';
    }

    public void bookSeats(int seatNumber, char seatType) {
        int arrayIndex = seatNumber - 1;
        cinemaHall[arrayIndex] = seatType;
        noOfSeatsBooked++;
    }

    public void generateReceipt(int totalNumberOfSeatsToBook, double standardTotal, String standardSeats,
                                double frequentTotal, String frequentSeats, double pensionareTotal, String pensionareSeats, double personTotal,
                                int standardCount, int frequentCount, int pensionareCount, String receiptType) {

        //Local Variables to format the output
        String lStandardTotal = String.format("%05.2f", standardTotal);
        String lFrequentTotal = String.format("%05.2f", frequentTotal);
        String lPensionerTotal = String.format("%05.2f", pensionareTotal);
        String lPersonTotal = String.format("%05.2f", personTotal);


        System.out.println();
        if (receiptType.equals("Booking")) {
            System.out.print(green);
            System.out.println("----------------Booking Receipt----------------");

        } else {
            System.out.println(red);
            System.out.println("----------------Refund Receipt----------------");
        }

        System.out.println("Total Seats: " + totalNumberOfSeatsToBook);
        System.out.println(
                standardCount + "\tx Standard Seats = $" + lStandardTotal + "\t| Seat Numbers: " + standardSeats);
        System.out.println(
                frequentCount + "\tx Standard Seats = $" + lFrequentTotal + "\t| Seat Numbers: " + frequentSeats);
        System.out.println(
                pensionareCount + "\tx Standard Seats = $" + lPensionerTotal + "\t| Seat Numbers: " + pensionareSeats);
        System.out.println("Total: $" + lPersonTotal);
        System.out.println("-----------------------------------------------");
        System.out.print(endColor);
    }

    public void refundSeats(int seatNumber) {
        int arrayIndex = seatNumber - 1;
        cinemaHall[arrayIndex] = '-';
        noOfSeatsBooked--;
    }

    public char returnSeatType(int seatNumber) {
        int arrayIndex = seatNumber - 1;
        return cinemaHall[arrayIndex];
    }

    public void printStatReport() {
        double avgSeatPrice = (sPrice+fPrice+pPrice)/3;
        double percentageOfSeatsSold = ((double)(noOfSeatsBooked /(double) totalNumberOfSeats))*100;
        //Local Variables to Format the String
        String lPercentage = String.format("%05.2f", percentageOfSeatsSold);
        String lAvgPrice = String.format("%05.2f", avgSeatPrice);

        System.out.println(blue);
        System.out.println("--------------Statistics Report--------------");
        System.out.println("Date: " + date);
        System.out.println("Total Income of The Cinema: $" + totalIncome);
        System.out.println("Total Number of Seats Booked: " + noOfSeatsBooked);
        System.out.println("Average Seat Price: $"+lAvgPrice);
        System.out.println("Percentage of Seats Sold: "+lPercentage+"%");
        System.out.println("---------------------------------------------");
        System.out.println(endColor);
    }

}
