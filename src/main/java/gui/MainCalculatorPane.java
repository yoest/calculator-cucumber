package gui;

import calculator.Calculator;
import calculator.Expression;
import calculator.MyNumber;
import calculatorParser.lexer;
import calculatorParser.parser;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import real.Rounding;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;



public class MainCalculatorPane extends ContentPane implements Initializable {

    public static boolean IS_INTEGER_MODE = true;

    public static int INPUT_RADIX = 10;

    public static int OUTPUT_RADIX = 10;

    public static Rounding ROUNDING = Rounding.ROUND_HALF_UP;

    public static int PRECISION = 10;

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
    private MenuItem integerButton;

    @FXML
    private Label inputRadixLabel;

    @FXML
    private Label outputRadixLabel;

    @FXML
    private Font x3;

    @FXML
    private Color x4;

    @FXML
    private VBox mainWindow;

    private MyNumber lastValue = null;

    private Calculator calculator = new Calculator();

    private final ArrayList<Button> OPERATORS_BUTTONS = new ArrayList<>();
    private final ArrayList<Button> NUMBER_BUTTONS = new ArrayList<>();
    private final ArrayList<Button> MARK_BUTTONS = new ArrayList<>();
    private final ArrayList<Button> ARROW_BUTTONS = new ArrayList<>();
    private final ArrayList<Button> ALL_BUTTONS = new ArrayList<>();

    private int caretCache = 1;

    public MainCalculatorPane(boolean isIntegerMode) {
        if (isIntegerMode)
        {
            IS_INTEGER_MODE = true;
        }
        else
        {
            calculator.setPrecision(PRECISION);
            calculator.setRounding(ROUNDING);
            IS_INTEGER_MODE = false;
        }

    }


    @Override
    public AnchorPane start() {
        return super.initController("/main_calculator.fxml");
    }


    private void disignFieldInput() {
        calculatorField.setText(calculatorField.getText().replaceAll("\\|", ""));
        calculatorField.setText(calculatorField.getText(0, caretCache-1) + "|" + calculatorField.getText(caretCache-1, calculatorField.getText().length()));
    }

    public String getResults() {
        return resultField.getText().replaceAll("\\|", "");
    }

    /**
     * initialize all buttons
     * add event filter to all buttons
     * add listener to calculatorField
     */
    private void initializeButton() {
        calculatorField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                calculatorField.setText(calculatorField.getText().replaceAll("\\|", ""));
            }
            else if (calculatorField.getCaretPosition() != 0) {
                caretCache = calculatorField.getCaretPosition() +1;
                disignFieldInput();
            }
            else
            {
                caretCache = 1;
                disignFieldInput();
            }
        });
        lastValueButton.setDisable(true);
        for (Button button : NUMBER_BUTTONS) {
            button.addEventFilter(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
                calculatorField.insertText(caretCache, button.getText());
                caretCache++;
                disignFieldInput();
            });
        }
        for (Button button : ARROW_BUTTONS) {
            button.addEventFilter(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
                if (button.equals(leftButton))
                {
                    if (caretCache-1 > 0)
                    {
                        calculatorField.positionCaret(caretCache - 1);
                        caretCache--;
                        disignFieldInput();
                    }
                }
                else if (button.equals(rightButton))
                {
                    if (caretCache < calculatorField.getText().length())
                    {
                        calculatorField.positionCaret(caretCache + 1);
                        caretCache++;
                        disignFieldInput();
                    }
                }
            });
        }
        for (Button button : OPERATORS_BUTTONS) {
            button.addEventFilter(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
                calculatorField.insertText(caretCache, button.getText());
                caretCache++;
                disignFieldInput();
            });
        }
        for (Button button : MARK_BUTTONS) {
            button.addEventFilter(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
                calculatorField.insertText(caretCache, button.getText());
                caretCache++;
                disignFieldInput();
            });
        }
        eButton.addEventFilter(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
            calculatorField.insertText(caretCache, "e");
            caretCache++;
            disignFieldInput();
        });
        quoteButton.addEventFilter(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
            calculatorField.insertText(caretCache, ".");
            caretCache++;
            disignFieldInput();
        });
        resetButton.addEventFilter(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
            caretCache = 1;
            calculatorField.setText("|");
        });
        lastValueButton.addEventFilter(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
            calculatorField.insertText(caretCache+1, lastValue.toString());
            caretCache += lastValue.toString().length() + 1;
            disignFieldInput();
        });
        buttonEval.addEventFilter(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
            parser p = new parser(new lexer(new java.io.StringReader(calculatorField.getText().replaceAll("\\|", "") + " ")));
            Object result = null;
            try {
                result = p.parse().value;
                Expression e = (Expression) result;
                boolean isInteger = IS_INTEGER_MODE;
                int outputRadix = OUTPUT_RADIX;
                MyNumber n;
                if (isInteger) {
                    n = new MyNumber(calculator.eval(e).toString(), 10);
                    n.setRadix(outputRadix);
                    resultField.setText(n.toString());
                } else{
                    n = new MyNumber(calculator.eval(e).toString());
                    resultField.setText(n.toString());
                }
                lastValue = n;
                addHistory(e.toString(), n.toString());
                lastValueButton.setDisable(false);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("Wrong expression : " + calculatorField.getText().replaceAll("\\|", "") + "\n" + e.getMessage());
                alert.showAndWait();
            }
        });
    }
    private void addHistory(String expression, String result) {
        historyListView.getItems().add(expression + " = " + result);
    }

    private void initializeArray() {
        OPERATORS_BUTTONS.add(plusButton);
        OPERATORS_BUTTONS.add(minusButton);
        OPERATORS_BUTTONS.add(multiplyButton);
        OPERATORS_BUTTONS.add(divButton);
        OPERATORS_BUTTONS.add(moduloButton);
        OPERATORS_BUTTONS.add(reverseModuloButton);
        NUMBER_BUTTONS.add(button0);
        NUMBER_BUTTONS.add(button1);
        NUMBER_BUTTONS.add(button2);
        NUMBER_BUTTONS.add(button3);
        NUMBER_BUTTONS.add(button4);
        NUMBER_BUTTONS.add(button5);
        NUMBER_BUTTONS.add(button6);
        NUMBER_BUTTONS.add(button7);
        NUMBER_BUTTONS.add(button8);
        NUMBER_BUTTONS.add(button9);
        NUMBER_BUTTONS.add(lastValueButton);
        MARK_BUTTONS.add(openMarkButton);
        MARK_BUTTONS.add(closeMarkButton);
        ARROW_BUTTONS.add(leftButton);
        ARROW_BUTTONS.add(rightButton);
        ALL_BUTTONS.addAll(OPERATORS_BUTTONS);
        ALL_BUTTONS.addAll(NUMBER_BUTTONS);
        ALL_BUTTONS.addAll(MARK_BUTTONS);
        ALL_BUTTONS.addAll(ARROW_BUTTONS);
        ALL_BUTTONS.add(eButton);
        ALL_BUTTONS.add(quoteButton);
        ALL_BUTTONS.add(resetButton);
        ALL_BUTTONS.add(buttonEval);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeArray();
        quoteButton.setDisable(true);
        buttonEval.setDisable(true);
        initializeButton();
        calculatorField.setText("|");
        if (IS_INTEGER_MODE) {
            inputRadixLabel.setText("Input radix :  " + INPUT_RADIX);
            inputRadixLabel.setVisible(true);
            outputRadixLabel.setText("Output radix :  " + OUTPUT_RADIX);
            outputRadixLabel.setVisible(true);
        } else {
            //TODO prepare the window for floating point numbers
        }

        //add listener to calculatorField
        calculatorField.textProperty().addListener((observableValue, s, t1) -> {
            //if there are too many characters | in calculatorField, remove them
            if (t1.length() - t1.replace("|", "").length() > 1) {
                disignFieldInput();
            }
            //if there is a character in calculatorField does not match any of the following characters, remove it
            if (!t1.matches("[0-9\\|.eEX%$+\\-*/()\\s]*")) {
                calculatorField.setText(t1.replaceAll("[^0-9\\|.eEX%$+\\-*/()\\s]", ""));
            }
            // the case when the user delete caracters
            if (caretCache > t1.length() + 1) {
                caretCache = t1.length();
            }
            String lastChar = "";
            if (caretCache - 2 >= 0) {
                lastChar = t1.substring(caretCache - 2, caretCache-1);
            }
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
