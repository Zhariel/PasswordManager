package fr.esgi.java.passwordmanager.display.menu.model;

import fr.esgi.java.passwordmanager.managers.InputType;

import java.util.*;

/**
 * Class Form
 * Contain the skeleton of forms displayed
 */

public class Form {
    private String title;
    private ArrayList<String> instructionsForm;
    private ArrayList<String> inputsForm;
    private ArrayList<InputType> typeInputs;

    /**
     * This arrayList of Integer allows to know which instruction jumped to when the user answered "NO" to a specific instruction.
     * Specific instructions have an integer > 0 corresponding to the number of instruction that jumped, normal instructions have 0 in cursor array.
     * example : "Do you want to choose a password? y/n"
     * if the user chooses no, the input for entry password is jumped. This specific instruction has 1 in the cursor array corresponding to one jump.
     */
    private ArrayList<Integer> cursor;

    /**
     * Constructor
     * @param title : title of the form
     */
    public Form(String title) {
        this.title = title;
        this.inputsForm = new ArrayList<String>();
        this.instructionsForm = new ArrayList<String>();
        this.cursor = new ArrayList<Integer>();
        this.typeInputs= new ArrayList<InputType>();
    }

    public static boolean isInterger(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int integer = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public ArrayList<String> getInputsForm() {

        return new ArrayList<>(this.inputsForm);
    }

    public ArrayList<String> getInstructionsForm() {

        return  new ArrayList<>(this.instructionsForm);
    }

    public ArrayList<Integer> getCursor() {

        return new ArrayList<>(this.cursor);
    }

    public ArrayList<InputType> getTypeInputs() {

        return new ArrayList<>(this.typeInputs);
    }

    public String getTitle() {
        return title;
    }


    public void addInstructionsForm(String instruction){
        this.instructionsForm.add(instruction);
    }

    public void addInputsForm(String input){
        this.inputsForm.add(input);
    }

    public void addTypeInputs(List<InputType> inputsList){
        this.typeInputs.addAll(inputsList);
    }

    public void addCursor(List<Integer> listCursor){
        this.cursor.addAll(listCursor);
    }

    /**Function fillInputFormArrayListWhitNAValues
     *
     * @param numberCursor : number of inputs not displayed because user has choosen NO for a No/Yes Question in a form.
     * This inputs jumped are fill with null in the ArrayList instructionsForm
     */
    public void fillInputFormArrayListWhitNAValues(int numberCursor) {

        for (int i = 0; i < numberCursor; i++) {
            inputsForm.add(null);
        }
    }

    /**Function emptyList
     * Clear the inputsForm for a futur new entry
     */
    public void emptyList() {
        inputsForm.clear();
    }

    /**Function checkYesOrNoQuestion
     *If a yes/no question is read, this function check if the user had entry y or n.
     * **/
    public boolean checkYesOrNoQuestion(Scanner scanner, int index) {

        String tmpInput;

        do {

            tmpInput = scanner.nextLine();

            if (tmpInput.length() > 0) {
                tmpInput = tmpInput.substring(0, 1).toLowerCase();
            } else {
                tmpInput = "error";
                tmpInput = tmpInput.substring(0, 1).toLowerCase();
            }

            if (tmpInput.equals("y")) {
                this.inputsForm.add(tmpInput);
                return true;
            } else if (tmpInput.equals("n")) {
                this.inputsForm.add(tmpInput);
                this.fillInputFormArrayListWhitNAValues(this.getCursor().get(index));
                return false;
            } else {
                System.out.print(this.getInstructionsForm().get(index) + " : ");
            }
        } while (!tmpInput.equals("y") && !tmpInput.equals("n"));

        return false;
    }

    public String checkIfInputIsEmpty(int index, Scanner scanner, String tmpInput){

        String currentInput = tmpInput;

        while(currentInput.equals("")){
            System.out.print(this.getInstructionsForm().get(index) + " : ");
            currentInput= scanner.nextLine();
        }

        return currentInput;
    }

    public String checkIfInputIsNumber(int index, Scanner scanner, String tmpInput){

        String currentInput = tmpInput;

        while(!isInterger(currentInput)){
            System.out.print(this.getInstructionsForm().get(index) + " (Entier) : ");
            currentInput= scanner.nextLine();
        }

        while(Integer.parseInt(currentInput)<0){
            System.out.print(this.getInstructionsForm().get(index) + " (>0) : ");
            currentInput= scanner.nextLine();
        }

        return currentInput;


    }

    public String checkIfInputIsDuration(int index, Scanner scanner, String tmpInput){

        String currentInput = tmpInput;

        while (currentInput.length()!=11){
            System.out.print(this.getInstructionsForm().get(index) + " [Respecter bien le format : XXj/XXm/XXy ] : ");
            currentInput= scanner.nextLine();
        }

        boolean isDay,isMonth,isYear,isDate;

        isDate = isDate(currentInput);

        String day = currentInput.substring(0, 2);
        String month = currentInput.substring(4, 6);
        String year = currentInput.substring(8, 10);

        isDay = isPositiveInteger(day);
        isMonth = isPositiveInteger(month);
        isYear = isPositiveInteger(year);


        while(!isDay || !isMonth || !isYear || !isDate) {
            System.out.print(this.getInstructionsForm().get(index) + " [Respecter bien le format : XXj/XXm/XXy ] : ");
            currentInput= scanner.nextLine();
            if(currentInput.length()!=11){
                continue;
            }

            isDate = isDate(currentInput);
            day = currentInput.substring(0, 2);
            month = currentInput.substring(4, 6);
            year = currentInput.substring(8, 10);

            isDay = isPositiveInteger(day);
            isMonth = isPositiveInteger(month);
            isYear = isPositiveInteger(year);

        }

        return currentInput;
    }

    public boolean isDate(String currentInput){

        //Format duration : (XXj/XXm/XXy)

        if(currentInput.charAt(3)!='/' ||currentInput.charAt(7)!='/'){
            return false;
        }

        if(currentInput.charAt(2)!='j' || currentInput.charAt(6)!='m' || currentInput.charAt(10)!='y'){
            return false;
        }

        return true;
    }

    public boolean isPositiveInteger(String day){

        if(!isInterger(day)){
            return false;
        }

        if(Integer.parseInt(day)<0){
            return false;
        }

        return true;
    }

}
