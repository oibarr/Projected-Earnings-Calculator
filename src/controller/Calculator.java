package controller;

import model.TaxBracket;

import java.util.Scanner;

import static model.TaxBracket.*;

public class Calculator {
    private static final int WEEKS_IN_YEAR = 52;
    private static double projectedEarnings = 0;

    private static void printYearlyIncome(double projectedEarnings) {
        System.out.println("Total income before taxes: " + String.format("$%,.2f", projectedEarnings));
    }

    private static void printYearlyEarnings(double projectedEarnings) {
        System.out.println("Projected earnings: " + String.format("$%,.2f", projectedEarnings));
    }

    private static void printWeeklyEarnings(double weeklyIncomeAfterTaxes){
        System.out.println("Weekly income after taxes: " + String.format("$%,.2f", weeklyIncomeAfterTaxes));
    }

    private static void printTaxBracket(TaxBracket taxBracket) {
        System.out.println("Tax Bracket: " + taxBracket.getTaxRate() + "%");
    }

    private static double calcAggregateDeductions(TaxBracket taxBracket) {
        double aggregateDeductions = 0;
        for (TaxBracket t : TAX_BRACKETS)
            if (t.getTaxRate() < taxBracket.getTaxRate()) aggregateDeductions += t.getMaxDeduction();
        return aggregateDeductions;
    }

    private static double calcDeductions(double userIncome, TaxBracket taxBracket){
        return (((userIncome - taxBracket.getMinWage()) + 1) * taxBracket.getTaxablePercentage());
    }

    private static void calcEarningsAfterDeductions(double userIncome, TaxBracket taxBracket){
        projectedEarnings = calcDeductions(userIncome, taxBracket) + calcAggregateDeductions(taxBracket);
    }

    private static void calcTaxBracket(double userIncome) {
        for (TaxBracket taxBracket : TAX_BRACKETS) {
            if (userIncome >= taxBracket.getMinWage() && userIncome <= taxBracket.getMaxWage()) {
                printTaxBracket(taxBracket);
                calcEarningsAfterDeductions(userIncome, taxBracket);
            }
        }
    }

    //Calculates weekly income after taxes based on user annual income
    private static void calcEarnings(double userIncome) {
        projectedEarnings = userIncome;

        printYearlyIncome(projectedEarnings);
        calcTaxBracket(projectedEarnings);
        printYearlyEarnings(projectedEarnings);
        printWeeklyEarnings(projectedEarnings / WEEKS_IN_YEAR);

        initializeContinue();

    }

    private static void calculateTaxBracket(double weeklyIncomeAfterTaxes){
        for (TaxBracket taxBracket : TAX_BRACKETS){
            if (taxBracket.getMaxDeduction() < weeklyIncomeAfterTaxes){
                weeklyIncomeAfterTaxes -= taxBracket.getMaxDeduction();
            } else {
                calcTotalIncome(weeklyIncomeAfterTaxes, taxBracket);
                printYearlyIncome(projectedEarnings);
                printTaxBracket(taxBracket);
                break;
            }
        }
    }

    private static double calcWages(double weeklyIncomeAfterTaxes, TaxBracket taxBracket){
        return ((weeklyIncomeAfterTaxes / (100 - taxBracket.getTaxRate())) * 100) + calcAggregateWages(taxBracket);
    }

    private static void calcTotalIncome(double weeklyIncomeAfterTaxes, TaxBracket taxBracket){
        projectedEarnings = calcWages(weeklyIncomeAfterTaxes, taxBracket);
    }

    private static double calcAggregateWages(TaxBracket taxBracket){
        return taxBracket.getMinWage() - 1;
    }

    //Calculates earnings based on desired user weekly income after taxes
    private static void calcWeeklyEarnings(double weeklyIncomeAfterTaxes){
        projectedEarnings = weeklyIncomeAfterTaxes;

        printWeeklyEarnings(projectedEarnings);
        printYearlyEarnings(projectedEarnings *= WEEKS_IN_YEAR);
        calculateTaxBracket(projectedEarnings);

        initializeContinue();

    }

    private static void initializeContinue(){
        continuePrompt();
        continueSelection();
    }

    private static void continueSelection(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().toUpperCase();

        if (input.equals("Y")){
            initializePrompt();
            initializeSelection();
        } else if (input.equals("N")){
            System.out.println("\nExiting Projected Earnings Calculator");
            System.exit(0);
        }else{
            System.out.println("\nPlease enter a valid input");
            continueSelection();
        }
    }

    private static void continuePrompt(){
        System.out.println("\nWould you like to perform another calculation?");
        System.out.println("Please enter \"Y\" to continue or \"N\" to exit");
    }

    private static void initWeeklyCalc(){
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextDouble()) {
            double weeklyIncomeAfterTaxes = Math.max(scanner.nextDouble(), 0);
            System.out.println();
            calcWeeklyEarnings(weeklyIncomeAfterTaxes);
        } else {
            System.out.println("\nPlease enter a valid input");
            initWeeklyCalc();
        }

    }

    private static void initCalc() {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextDouble()) {
            double userIncome = Math.max(scanner.nextDouble(), 0);
            System.out.println();
            calcEarnings(userIncome);
        } else {
            System.out.println("\nPlease enter a valid input");
            initCalc();
        }
    }

    private static void initializeSelection(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().toUpperCase();

        if (input.equals("W")){
            System.out.println("\nPlease enter desired weekly income: ");
            Calculator.initWeeklyCalc();
        } else if (input.equals("A")){
            System.out.println("\nPlease enter user income: ");
            Calculator.initCalc();
        }else{
            System.out.println("\nPlease enter a valid input");
            initializeSelection();
        }
    }

    private static void initializeTitle(){
        System.out.println("\nInitializing Projected Earnings Calculator");
    }

    private static void initializePrompt(){
        System.out.println("\nWould you like to calculate weekly or annual earnings?\n");
        System.out.println("Please enter \"W\" for weekly or \"A\" for annual: ");
    }

    public static void initialize(){
        initializeTitle();
        initializePrompt();
        initializeSelection();
    }
}
