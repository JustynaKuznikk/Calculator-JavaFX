package pl.justyna.kuznik.calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import static pl.justyna.kuznik.calculator.Operations.*;


public class Controller {

    @FXML
    private TextField screenInput;

    private Operations operation = Operations.PLUS;
    private double firstNumber;
    private boolean shouldClear=false;

    @FXML
    public void handlerNumberPressed(ActionEvent event){
        if(shouldClear){
            shouldClear = !shouldClear;
            screenInput.clear();
        }
        Object obj = event.getSource();
        if(obj instanceof Button){
            Button button = (Button) obj;
            String text = button.getText();
            System.out.println(text);
            screenInput.appendText(text);
        }

    }

    @FXML
    public void handlerPlusPressed(ActionEvent event){
        operation= PLUS;
        getFirstNumberAndClear();
    }

    private void getFirstNumberAndClear() {
        firstNumber=getNumberFromScreen();
        shouldClear=true;
    }

    private double getNumberFromScreen() {
        try{
            String text = screenInput.getText();
            String normalized = text.replace(",",".");
            return Double.parseDouble(normalized);

        }catch (Exception e){
            e.printStackTrace();
            return 1;
        }
    }
    @FXML
    public void handlerMinusPressed(ActionEvent event){
        operation = MINUS;
        getFirstNumberAndClear();
    }
    @FXML
    public void handlerMultiplyPressed(ActionEvent event){
        operation = MULTIPLY;
        getFirstNumberAndClear();
    }
    @FXML
    public void handlerDividePressed(ActionEvent event){
        operation = DIVIDE;
        getFirstNumberAndClear();
    }
    @FXML
    public void handlerEqualPressed(ActionEvent event){
        double a = firstNumber;
        double b = getNumberFromScreen();
        double output = calculatorOperations(a,b);
        screenInput.setText(String.valueOf(output));
    }

    private double calculatorOperations(double a, double b) {
        switch (operation){
            case PLUS:
                return a + b;
            case MINUS:
                return a - b;
            case MULTIPLY:
                return a * b;
            case DIVIDE:
                return a / b;
        }
        throw new IllegalStateException("Unsupported operation"
                + operation);
    }
    @FXML
    public void handlerAcPressed(ActionEvent event){
        screenInput.clear();
    }
    @FXML
    public void handlerCPressed(ActionEvent event){
        screenInput.clear();
    }

}
