package gui;

import calculator.Expression;
import calculator.MyNumber;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

public class CalculatorController implements Initializable {

    private MyNumber lastValue = null;

    @FXML
    private MenuItem autoSaveButton;

    @FXML
    private Button leftButton;

    @FXML
    private Button rightButton;
    @FXML
    private Button button0;

    @FXML
    private Button quoteButton;

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private Button button5;

    @FXML
    private Button button6;

    @FXML
    private Button button7;

    @FXML
    private Button button8;

    @FXML
    private Button button9;

    @FXML
    private Button buttonEval;

    @FXML
    private TextField calculatorField;

    @FXML
    private Button closeMarkButton;

    @FXML
    private MenuItem decimal10Button;

    @FXML
    private MenuItem decimalOtherButton;

    @FXML
    private Button degButton;

    @FXML
    private Button divButton;

    @FXML
    private Button eButton;

    @FXML
    private ListView<String> historyListView;

    @FXML
    private MenuItem int10Button;

    @FXML
    private MenuItem intOtherButton;

    @FXML
    private Button minusButton;

    @FXML
    private Button moduloButton;

    @FXML
    private Button multiplyButton;

    @FXML
    private Button openMarkButton;

    @FXML
    private Button plusButton;

    @FXML
    private Button radianButton;

    @FXML
    private Button resetButton;

    @FXML
    private Button lastValueButton;

    @FXML
    private TextField resultField;

    @FXML
    private Button reverseModuloButton;

    @FXML
    private MenuItem saveButton;

    @FXML
    private MenuItem timeButton;

    @FXML
    private Font x3;

    @FXML
    private Color x4;

    @FXML
    private VBox mainWindow;

    private final String[] MODES = {"PREFFIX", "INFIX", "POSTFIX"};

    private String currentMode = MODES[0];

    private final Button[] OPERATORS_BUTTONS = {plusButton, minusButton, multiplyButton, divButton, moduloButton, reverseModuloButton, degButton, radianButton};

    private final Button[] NUMBER_BUTTONS = {button0, button1, button2, button3, button4, button5, button6, button7, button8, button9, lastValueButton};
    private final Button[] MARK_BUTTONS = {openMarkButton, closeMarkButton};

    private final Button[] ARROW_BUTTONS = {leftButton, rightButton};

    private final Button[] ALL_BUTTONS = {button0, button1, button2, button3, button4, button5, button6, button7, button8, button9, plusButton, minusButton, multiplyButton, divButton, moduloButton, reverseModuloButton, degButton, radianButton, openMarkButton, closeMarkButton, quoteButton, eButton, buttonEval, resetButton, lastValueButton, leftButton, rightButton};

    /**
     * initialize all buttons
     * add event filter to all buttons
     * add listener to calculatorField
     */
    private void initializeButton() {
        lastValueButton.setDisable(true);
        for (Button button : NUMBER_BUTTONS) {
            button.addEventFilter(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
                calculatorField.insertText(calculatorField.getCaretPosition(), button.getText());
            });
        }
        for (Button button : ARROW_BUTTONS) {
            button.addEventFilter(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
                if (button.equals(leftButton))
                {
                    if (calculatorField.getCaretPosition() > 0)
                    {
                        calculatorField.positionCaret(calculatorField.getCaretPosition() - 1);
                    }
                }
                else if (button.equals(rightButton))
                {
                    if (calculatorField.getCaretPosition() < calculatorField.getText().length())
                    {
                        calculatorField.positionCaret(calculatorField.getCaretPosition() + 1);
                    }
                }
            });
        }
        for (Button button : OPERATORS_BUTTONS) {
            button.addEventFilter(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
                if (currentMode.equals(MODES[0]))
                {
                    calculatorField.insertText(calculatorField.getCaretPosition(), button.getText() + "(");

                }
                else if (currentMode.equals(MODES[1]))
                {
                    calculatorField.insertText(calculatorField.getCaretPosition(), button.getText());
                }
                else if (currentMode.equals(MODES[2]))
                {
                    calculatorField.insertText(calculatorField.getCaretPosition(), ")" + button.getText());
                }
            });
        }
        for (Button button : MARK_BUTTONS) {
            button.addEventFilter(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
                calculatorField.insertText(calculatorField.getCaretPosition(), button.getText());
            });
        }
        eButton.addEventFilter(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
            if (currentMode.equals(MODES[0]))
            {
                calculatorField.insertText(calculatorField.getCaretPosition(), "e(");
            }
            else if (currentMode.equals(MODES[1]))
            {
                calculatorField.insertText(calculatorField.getCaretPosition(), "e");
            }
            else if (currentMode.equals(MODES[2]))
            {
                calculatorField.insertText(calculatorField.getCaretPosition(), ")e");
            }
        });
        quoteButton.addEventFilter(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
            calculatorField.insertText(calculatorField.getCaretPosition(), ".");
        });
        resetButton.addEventFilter(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
            calculatorField.setText("");
            calculatorField.positionCaret(0);
        });
        buttonEval.addEventFilter(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
            //TODO: verify the expression (with calculatorParser)
            //TODO: evaluate the expression
            MyNumber res = new MyNumber("0"); //TODO: replace with the result of the expression
            resultField.setText(res.toString());
            lastValue = res;
            addHistory(calculatorField.getText(), res.toString());
            lastValueButton.setDisable(false);
        });
    }
    private void addHistory(String expression, String result) {
        historyListView.getItems().add(expression + " = " + result);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        quoteButton.setDisable(true);
        buttonEval.setDisable(true);
        initializeButton();
        //add listener to calculatorField
        calculatorField.textProperty().addListener((observableValue, s, t1) -> {
            //if there is a character in calculatorField does not match any of the following characters, remove it
            if (!t1.matches("[0-9+\\-*/()\\s]*")) {
                calculatorField.setText(t1.replaceAll("[^0-9+\\-*/()\\s]", ""));
            }
            //if the number of open marks is greater than the number of close marks, disable closeMarkButton
            if (t1.length() - t1.replace("(", "").length() > t1.length() - t1.replace(")", "").length()) {
                closeMarkButton.setDisable(true);
            } else {
                closeMarkButton.setDisable(false);
            }
            String lastChar = t1.substring(t1.length() - 1);
            //switch case to disable buttons when necessary to avoid errors
            switch (lastChar) {
                case "+":
                    for (Button button : OPERATORS_BUTTONS) {
                        button.setDisable(true);
                    }
                    quoteButton.setDisable(false);
                case "-":
                    for (Button button : OPERATORS_BUTTONS) {
                        button.setDisable(true);
                    }
                    quoteButton.setDisable(false);
                case "*":
                    for (Button button : OPERATORS_BUTTONS) {
                        button.setDisable(true);
                    }
                    quoteButton.setDisable(false);
                case "/":
                    for (Button button : OPERATORS_BUTTONS) {
                        button.setDisable(true);
                    }
                    quoteButton.setDisable(false);

                case "(":
                    quoteButton.setDisable(true);
                case ")":
                    quoteButton.setDisable(true);
                    break;
                default:
                    for (Button button : ALL_BUTTONS) {
                        button.setDisable(false);
                    }
            }
        });
    }
}
