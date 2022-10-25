package model;

public class TaxBracket {
    int taxRate;
    double taxablePercentage;
    int minWage;
    int maxWage;
    int wageRange;
    double minDeduction;
    double maxDeduction;

    public TaxBracket(int taxRate, int minWage, int maxWage) {
        this.taxRate = taxRate;
        this.taxablePercentage = (100 - taxRate) * .01;
        this.minWage = minWage;
        this.maxWage = maxWage;
        this.wageRange = ((maxWage + 1) - minWage);
        this.minDeduction = minWage * taxablePercentage;
        this.maxDeduction = wageRange * taxablePercentage;
    }

    public int getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(int taxRate) {
        this.taxRate = taxRate;
    }

    public double getTaxablePercentage() {
        return taxablePercentage;
    }

    public void setTaxablePercentage(double taxablePercentage) {
        this.taxablePercentage = taxablePercentage;
    }

    public int getMinWage() {
        return minWage;
    }

    public void setMinWage(int minWage) {
        this.minWage = minWage;
    }

    public int getMaxWage() {
        return maxWage;
    }

    public void setMaxWage(int maxWage) {
        this.maxWage = maxWage;
    }

    public int getWageRange() {
        return wageRange;
    }

    public void setWageRange(int wageRange) {
        this.wageRange = wageRange;
    }

    public double getMaxDeduction() {
        return maxDeduction;
    }

    public void setMaxDeduction(int maxDeduction) {
        this.maxDeduction = maxDeduction;
    }

    public static final TaxBracket TAX_BRACKET_10 = new TaxBracket(10, 1, 10275);
    public static final TaxBracket TAX_BRACKET_12 = new TaxBracket(12, 10276, 41775);
    public static final TaxBracket TAX_BRACKET_22 = new TaxBracket(22, 41776, 89075);
    public static final TaxBracket TAX_BRACKET_24 = new TaxBracket(24, 89076, 170050);
    public static final TaxBracket TAX_BRACKET_32 = new TaxBracket(32, 170051, 215950);
    public static final TaxBracket TAX_BRACKET_35 = new TaxBracket(35, 215951, 539900);
    public static final TaxBracket TAX_BRACKET_37 = new TaxBracket(37, 539901, Integer.MAX_VALUE);
    public static final TaxBracket[] TAX_BRACKETS = new TaxBracket[]{TAX_BRACKET_10, TAX_BRACKET_12, TAX_BRACKET_22, TAX_BRACKET_24, TAX_BRACKET_32, TAX_BRACKET_35, TAX_BRACKET_37};

    public static void printInfo(TaxBracket[] taxBracketArray){
        for(TaxBracket t : taxBracketArray){
            System.out.println(t.getMaxDeduction());
        }
    }

    public static void calcMaxDeduction(TaxBracket[] taxBracketArray) {
        for(TaxBracket t : taxBracketArray){
            System.out.println(t.getMaxDeduction());
        }
    }

}
