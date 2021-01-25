package fr.esgi.java.passwordmanager.display.menu.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/** Class Form
 * Contain the skeleton of forms displayed
*/
public class Form {
    private String title;
    private ArrayList<String> instructionsForm;
    private ArrayList<String> inputsForm;

    /**
     * This arrayList of Integer allows to know which instruction jumped to when the user answered "NO" to a specific instruction.
     * Specific instructions have an integer > 0 corresponding to the number of instruction that jumped, normal instructions have 0 in cursor array.
     * example : "Do you want to choose a password? y/n"
     * if the user chooses no, the input for entry password is jumped. This specific instruction has 1 in the cursor array corresponding to one jump.
     */
    private ArrayList<Integer> cursor;

    public Form(String title){
        this.title = title;
        this.inputsForm = new ArrayList<String>();
        this.instructionsForm = new ArrayList<String>();
        this.cursor = new ArrayList<Integer>();
    }

    public ArrayList<String> getInputsForm() {
        return inputsForm;
    }

    public ArrayList<String> getInstructionsForm() {
        return instructionsForm;
    }

    public ArrayList<Integer> getCursor() {
        return cursor;
    }

    public String getTitle() {
        return title;
    }

    public void  fillInputFormArrayListWhitNAValues(int numberCursor){

        for(int i=0;i<numberCursor;i++){
            inputsForm.add(null);
        }
    }

    public void emptyList(){
        inputsForm.clear();
    }
}
