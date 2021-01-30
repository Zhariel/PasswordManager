public class Constraint {
    private int totalLength;
    private int upperCase;
    private int lowerCase;
    private int specialChars;
    private int letters;
    private int digits;

    public Constraint(int totalLength, int upperCase, int lowerCase, int specialChars, int letters, int digits){
        this.totalLength = totalLength;
        this.upperCase = upperCase;
        this.lowerCase = lowerCase;
        this.specialChars = specialChars;
        this.letters = letters;
        this.digits = digits;
    }

    public int getTotalLength() {
        return totalLength;
    }

    public void setTotalLength(int totalLength) {
        this.totalLength = totalLength;
    }

    public int getUpperCase() {
        return upperCase;
    }

    public void setUpperCase(int upperCase) {
        this.upperCase = upperCase;
    }

    public int getLowerCase() {
        return lowerCase;
    }

    public void setLowerCase(int lowerCase) {
        this.lowerCase = lowerCase;
    }

    public int getSpecialChars() {
        return specialChars;
    }

    public void setSpecialChars(int specialChars) {
        this.specialChars = specialChars;
    }

    public int getLetters() {
        return letters;
    }

    public void setLetters(int letters) {
        this.letters = letters;
    }

    public int getDigits() {
        return digits;
    }

    public void setDigits(int digits) {
        this.digits = digits;
    }
}