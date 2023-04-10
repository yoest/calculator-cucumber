package gui;

import calculator.MyNumber;
import calculator.MyTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class TimeCalculatorPane extends ContentPane implements Initializable {

    @FXML
    private Button leftButton;

    @FXML
    private Button rightButton;

    @FXML
    private Button button0;

    @FXML
    private Button doublePointButton;

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
    private Button pmButton;
    @FXML
    private ListView<String> historyListView;

    @FXML
    private Button minusButton;

    @FXML
    private Button openMarkButton;

    @FXML
    private Button plusButton;

    @FXML
    private Button amButton;

    @FXML
    private Button resetButton;

    @FXML
    private Button lastValueButton;

    @FXML
    private TextField resultField;

    @FXML
    private ChoiceBox<String> resultChoice;

    private final ArrayList<Button> OPERATORS_BUTTONS = new ArrayList<>();
    private final ArrayList<Button> NUMBER_BUTTONS = new ArrayList<>();
    private final ArrayList<Button> MARK_BUTTONS = new ArrayList<>();
    private final ArrayList<Button> ARROW_BUTTONS = new ArrayList<>();
    private final ArrayList<Button> CHAR_BUTTONS = new ArrayList<>();
    private final ArrayList<Button> ALL_BUTTONS = new ArrayList<>();

    private int caretCache = 1;

    private MyTime lastValue = null;


    @Override
    public AnchorPane start()  {
        return super.initController("/time_calculator.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeArray();

        for(Button button : CHAR_BUTTONS)
            button.setDisable(true);
        buttonEval.setDisable(true);

        initializeCalculatorFieldFocusedProperty();
        initializeButton();

        initializeCalculatorTextProperty();

        resultChoice.setValue("As complete string");
    }

    /** Add a bar into the field input if the buttons are used. */
    private void designFieldInput() {
        calculatorField.setText(calculatorField.getText().replaceAll("\\|", ""));
        calculatorField.setText(calculatorField.getText(0, caretCache - 1) + "|" + calculatorField.getText(caretCache - 1, calculatorField.getText().length()));
    }

    /** Get the resulting expression without the added bar.
     *
     * @return the resulting expression without the added bar.
     */
    public String getResults() {
        return resultField.getText().replaceAll("\\|", "");
    }

    /** Initialize the top field so that it updates itself when focused. */
    private void initializeCalculatorFieldFocusedProperty() {
        calculatorField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                calculatorField.setText(calculatorField.getText().replaceAll("\\|", ""));
            }
            else if (calculatorField.getCaretPosition() != 0) {
                caretCache = calculatorField.getCaretPosition() + 1;
                designFieldInput();
            }
            else {
                caretCache = 1;
                designFieldInput();
            }
        });
    }

    /** Initialize the top field so that when new text is written, an action can be performed. */
    private void initializeCalculatorTextProperty() {
        calculatorField.setText("|");
        calculatorField.textProperty().addListener((observableValue, s, t1) -> {
            // If there are too many characters | in calculatorField, remove them
            if (t1.length() - t1.replace("|", "").length() > 1) {
                designFieldInput();
            }

            // If there is a character in calculatorField that does not match any of the following characters, remove it
            if (!t1.matches("[0-9\\|.eEX%$+\\-*/()\\s:AMP]*")) {
                calculatorField.setText(t1.replaceAll("[^0-9\\|.eEX%$+\\-*/()\\s:AMP]", ""));
            }

            String lastChar = "";
            if (caretCache - 2 >= 0) {
                lastChar = t1.substring(caretCache - 2, caretCache-1);
            }

            // Switch case to disable buttons when necessary to avoid errors
            switch (lastChar) {
                case "+", "-" -> {
                    for (Button button : OPERATORS_BUTTONS) {
                        button.setDisable(true);
                    }
                    for (Button button : CHAR_BUTTONS) {
                        button.setDisable(true);
                    }
                }
                case "M", "A", "P", ":" -> {
                    for (Button button : CHAR_BUTTONS) {
                        button.setDisable(true);
                    }
                }
                default -> {
                    for (Button button : ALL_BUTTONS) {
                        button.setDisable(false);
                    }
                }
            }
        });
    }

    /** Initialize all buttons, add event filter to all buttons, and add listener to calculatorField. */
    private void initializeButton() {
        lastValueButton.setDisable(true);

        initializeListButtons(NUMBER_BUTTONS);
        initializeArrowsButtons();
        initializeListButtons(OPERATORS_BUTTONS);
        initializeListButtons(MARK_BUTTONS);

        initializeButtonWithValue(doublePointButton, ":", 1);
        initializeButtonWithValue(amButton, "AM", 2);
        initializeButtonWithValue(pmButton, "PM", 2);

        resetButton.addEventFilter(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
            calculatorField.setText("|");
            caretCache = 1;
        });
        lastValueButton.addEventFilter(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
            calculatorField.insertText(caretCache, lastValue.toString());
            caretCache += lastValue.toString().length();
            designFieldInput();
        });

        // -- Evaluation button
        buttonEval.addEventFilter(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
            //TODO: verify the expression (with parser) and construct the expression
            //TODO: evaluate the expression
            MyTime res = MyTime.getAsDays(0); //TODO: replace with the result of the expression
            resultField.setText(String.valueOf(res.getValue()));
            lastValue = res;
            addHistory(getResults(), String.valueOf(res.getValue()));
            lastValueButton.setDisable(false);
        });
    }

    /** Initialize all the buttons in a list with the current text of the button.
     *
     * @param buttons: a list of buttons.
     */
    private void initializeListButtons(ArrayList<Button> buttons) {
        for (Button button : buttons) {
            button.addEventFilter(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
                calculatorField.insertText(caretCache, button.getText());
                caretCache++;
                designFieldInput();
            });
        }
    }

    /** Initialize all the buttons that contains arrows. */
    private void initializeArrowsButtons() {
        for (Button button : ARROW_BUTTONS) {
            button.addEventFilter(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
                if (button.equals(leftButton)) {
                    if (caretCache - 1 > 0) {
                        calculatorField.positionCaret(caretCache - 1);
                        caretCache--;
                        designFieldInput();
                    }
                }
                else if (button.equals(rightButton)) {
                    if (caretCache < calculatorField.getText().length()) {
                        calculatorField.positionCaret(caretCache + 1);
                        caretCache++;
                        designFieldInput();
                    }
                }
            });
        }
    }

    /** Initialize a specific button when we click on it by inserting some text in the field and by shifting the bar.
     *
     * @param button: button to which add an event when we click on it.
     * @param text: text to insert in the text field.
     * @param shift: amount of shift to perform.
     */
    private void initializeButtonWithValue(Button button, String text, int shift) {
        button.addEventFilter(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
            calculatorField.insertText(caretCache, text);
            caretCache += shift;
            designFieldInput();
        });
    }

    /** Add a new value into the history.
     *
     * @param expression: expression to add into the history as a string.
     * @param result: result of the corresponding expression.
     */
    private void addHistory(String expression, String result) {
        historyListView.getItems().add(expression + " = " + result);
    }

    /** Add manually all the FXML buttons into arrays to manipulate them easily. */
    private void initializeArray() {
        OPERATORS_BUTTONS.addAll(Arrays.asList(plusButton, minusButton));
        NUMBER_BUTTONS.addAll(Arrays.asList(
                button0, button1, button2, button3, button4, button5,
                button6, button7, button8, button9, lastValueButton
        ));
        MARK_BUTTONS.addAll(Arrays.asList(openMarkButton, closeMarkButton));
        ARROW_BUTTONS.addAll(Arrays.asList(leftButton, rightButton));
        CHAR_BUTTONS.addAll(Arrays.asList(amButton, pmButton, doublePointButton));

        for(List<Button> buttonsList : Arrays.asList(OPERATORS_BUTTONS, NUMBER_BUTTONS, MARK_BUTTONS, ARROW_BUTTONS, CHAR_BUTTONS))
            ALL_BUTTONS.addAll(buttonsList);
        ALL_BUTTONS.addAll(Arrays.asList(doublePointButton, resetButton, buttonEval));
    }
}
