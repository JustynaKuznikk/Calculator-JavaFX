package pl.justyna.kuznik.calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.math.MathContext;

import static pl.justyna.kuznik.calculator.Operations.*;


public class Controller {

    @FXML
    private TextField screenInput;

    private Operations operation = Operations.PLUS;
    private BigDecimal firstNumber;
    private boolean shouldClear=false;

    @FXML
    public void handlerNumberPressed(ActionEvent event){
        if(shouldClear){
            shouldClear = false;
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
        firstNumber = getNumberFromScreen();
        shouldClear = true;
    }

    private BigDecimal getNumberFromScreen() {
        try{
            String text = screenInput.getText();
            String normalized = text.replace(",",".");
            return new BigDecimal(normalized);

        }catch (Exception e){
            e.printStackTrace();
            return BigDecimal.ONE;
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
        BigDecimal a = firstNumber;
        BigDecimal b = getNumberFromScreen();
        BigDecimal output = calculatorOperations(a,b);
        screenInput.setText(String.valueOf(output));
    }

    private BigDecimal calculatorOperations(BigDecimal a, BigDecimal b) {
        switch (operation){
            case PLUS:
                return a.add(b);
            case MINUS:
                return a.subtract(b);
            case MULTIPLY:
                return a.multiply(b);
            case DIVIDE:
                return a.divide(b, MathContext.DECIMAL128);
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
