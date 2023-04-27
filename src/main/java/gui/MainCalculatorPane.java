package gui;

import calculator.Expression;
import calculator.MyNumber;
import calculator.Operation;
import calculatorParser.lexer;
import calculatorParser.parser;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import memory.MemoryCalculator;
import memory.Snapshot;
import real.Rounding;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.List;



public class MainCalculatorPane extends ContentPane implements Initializable {

    public static boolean IS_INTEGER_MODE = true;

    public static int MAX_MEMORY_SIZE = 1000;
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
    private Button undoButton;

    @FXML
    private Button redoButton;

    @FXML
    private Button loadButton;

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
    private Button saveHistoryButton;

    @FXML
    private Button loadHistoryButton;

    @FXML
    private Button exportButton;
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

    //private Calculator calculator = new Calculator();
    private MemoryCalculator calculator = new MemoryCalculator(MAX_MEMORY_SIZE);

    private final ArrayList<Button> OPERATORS_BUTTONS = new ArrayList<>();
    private final ArrayList<Button> NUMBER_BUTTONS = new ArrayList<>();
    private final ArrayList<Button> MARK_BUTTONS = new ArrayList<>();
    private final ArrayList<Button> ARROW_BUTTONS = new ArrayList<>();
    private final ArrayList<Button> ALL_BUTTONS = new ArrayList<>();

    private int caretCache = 1;

    private String regex = null;

    public MainCalculatorPane(boolean isIntegerMode) {
        if (isIntegerMode)
        {
            IS_INTEGER_MODE = true;
            regex = Regex.getRegexInt(INPUT_RADIX);
        }
        else
        {
            calculator.setPrecision(PRECISION);
            calculator.setRounding(ROUNDING);
            regex = Regex.DECIMAL;
            IS_INTEGER_MODE = false;
        }

    }


    @Override
    public AnchorPane start() {
        return super.initController("main_calculator.fxml");
    }


    private void disignFieldInput() {
        calculatorField.setText(calculatorField.getText().replaceAll("\\|", ""));
        calculatorField.setText(calculatorField.getText(0, caretCache-1) + "|" + calculatorField.getText(caretCache-1, calculatorField.getText().length()));
    }

    /**
     * initialize all buttons
     * add event filter to all buttons
     * add listener to calculatorField
     */
    private void initializeButton() {
        redoButton.setDisable(!calculator.redoable());
        undoButton.setDisable(historyListView.getItems().isEmpty());
        calculator.createSavesFolder();
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
                synchronizeHistory();
                lastValueButton.setDisable(false);
                undoButton.setDisable(historyListView.getItems().isEmpty());

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("Wrong expression : " + calculatorField.getText().replaceAll("\\|", "") + "\n" + e.getMessage());
                alert.showAndWait();
            }
        });
        undoButton.addEventFilter(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
            redoButton.setDisable(!calculator.redoable());
            undoButton.setDisable(historyListView.getItems().isEmpty());
            Snapshot last;
            try {
                last = calculator.undo();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            synchronizeHistory();
            Expression lastExpression = last.getExpression();
            calculatorField.setText(lastExpression.toString());
            undoButton.setDisable(historyListView.getItems().isEmpty());
            redoButton.setDisable(!calculator.redoable());
        });
        redoButton.addEventFilter(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
            Snapshot last = null;
            redoButton.setDisable(!calculator.redoable());
            try {
                last = calculator.redo();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Expression lastExpression = last.getExpression();
            synchronizeHistory();
            calculatorField.setText(lastExpression.toString());
            undoButton.setDisable(historyListView.getItems().isEmpty());
            redoButton.setDisable(!calculator.redoable());

        });
        loadButton.addEventFilter(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
            //load an expression
            //Opening a menu, then write the id of the expression, then load it
            TextInputDialog dialog = new TextInputDialog("ID of the expression");
            dialog.setTitle("Load an expression");
            dialog.setHeaderText("Load an expression");
            dialog.setContentText("Please enter the name of the expression:");
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()){


                Expression e = calculator.load(result.get());
                if (e != null) {
                    calculatorField.setText(e.toString());
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error");
                    alert.setContentText("The expression " + result.get() + " doesn't exist");
                    alert.showAndWait();
                }
            }
        });
        saveHistoryButton.addEventFilter(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
            //Save the history
            String name = calculator.saveHistory();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Information");
            alert.setContentText("History saved as " + name);
            alert.showAndWait();
        });
        loadHistoryButton.addEventFilter(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
            //Load the history
            //Opening the folder where the saves are stored and let the user choose the file
            //the folder is saves/history/ser, the window is opened in the saves folder
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            fileChooser.setInitialDirectory(new File("saves/history/ser"));
            // the file is a ser file
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("SER Files", "*.ser")
            );
            File selectedFile = fileChooser.showOpenDialog(null);

            if (selectedFile != null) {
                //Load the history
                calculator.loadHistory(selectedFile.getName());
                calculator.adaptSizeOfHistory();
                synchronizeHistory();

            }
        });
        exportButton.addEventFilter(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
            //Export the history
            String name = calculator.exportHistory();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Information");
            alert.setContentText("History in txt exported as " + name);
            alert.showAndWait();
            //Open the folder where the file is stored
            try {
                Desktop.getDesktop().open(new File("saves/history/txt"));
            } catch (IOException e) {
                System.err.println("Error while opening the folder");
            }
        });
        radianButton.addEventFilter(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
            //create dialog box to ask the user to enter a value in degree
            TextInputDialog dialog = new TextInputDialog("Value in degree");
            dialog.setTitle("Value in degree");
            dialog.setHeaderText("Value in degree");
            dialog.setContentText("Please enter the value in degree:");
            //add a filter to the text field to only accept numbers and the dot
            TextField textField = dialog.getEditor();
            textField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*(\\.\\d*)?")) {
                    textField.setText(oldValue);
                }
            });
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()){
                MyNumber n = new MyNumber(result.get());
                n = n.toRadian();
                calculatorField.insertText(caretCache+1, n.toString());
                caretCache += n.toString().length() + 1;
                disignFieldInput();
            }
        });
        degButton.addEventFilter(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
            //create dialog box to ask the user to enter a value in radian
            TextInputDialog dialog = new TextInputDialog("Value in radian");
            dialog.setTitle("Value in radian");
            dialog.setHeaderText("Value in radian");
            dialog.setContentText("Please enter the value in radian:");
            //add a filter to the text field to only accept numbers and the dot
            TextField textField = dialog.getEditor();
            textField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*(\\.\\d*)?")) {
                    textField.setText(oldValue);
                }
            });
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()){
                MyNumber n = new MyNumber(result.get());
                n = n.toDegree();
                calculatorField.insertText(caretCache+1, n.toString());
                caretCache += n.toString().length() + 1;
                disignFieldInput();
            }
        });
    }

    private void addHistory(String expression, String result, String id) {
        historyListView.getItems().add(expression + " = " + result + " | The ID is " + id);
    }

    private void synchronizeHistory() {
        historyListView.getItems().clear();
        List<Snapshot> history = calculator.getHistory();
        for (Snapshot s : history) {
            Expression e1 = s.getExpression();
            adaptRadix(e1);
            MyNumber e2 = s.getComputed();
            e2.setRadix(OUTPUT_RADIX);
            addHistory(e1.toString(), e2.toString(), s.getName());
        }
    }

    private void adaptRadix(Expression e) {
        if (e instanceof MyNumber) ((MyNumber) e).setRadix(INPUT_RADIX);
            else {
            List<Expression> list = ((Operation) e).getElist();
            for (Expression n : list) {
                adaptRadix(n);
            }
        }
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
            radianButton.setVisible(false);
            degButton.setVisible(false);
            eButton.setVisible(false);
            quoteButton.setVisible(false);
            moduloButton.setVisible(true);
            reverseModuloButton.setVisible(true);
        }
        else {
            inputRadixLabel.setText("Precision :  " + PRECISION);
            inputRadixLabel.setVisible(true);
            outputRadixLabel.setText("ROUNDING :  " + ROUNDING);
            outputRadixLabel.setVisible(true);
            radianButton.setVisible(true);
            degButton.setVisible(true);
            eButton.setVisible(true);
            quoteButton.setVisible(true);
            moduloButton.setVisible(false);
            reverseModuloButton.setVisible(false);
        }

        //add listener to calculatorField
        calculatorField.textProperty().addListener((observableValue, s, t1) -> {
            //if there are too many characters | in calculatorField, remove them
            if (t1.length() - t1.replace("|", "").length() > 1) {
                disignFieldInput();
            }
            //if there is a character in calculatorField does not match any of the following characters, remove it
            if (!t1.matches(regex)) {
                calculatorField.setText(t1.replaceAll(Regex.negateRegex(regex), ""));
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
                case "+", "-", "*", "/" -> {
                    for (Button button : OPERATORS_BUTTONS) {
                        button.setDisable(true);
                    }
                    quoteButton.setDisable(false);
                }
                case "(", ")" -> quoteButton.setDisable(true);
                default -> {
                    for (Button button : ALL_BUTTONS) {
                        button.setDisable(false);
                    }
                }
            }
        });
    }

    public static boolean isIsIntegerMode() {
        return IS_INTEGER_MODE;
    }

    public static void setIsIntegerMode(boolean isIntegerMode) {
        IS_INTEGER_MODE = isIntegerMode;
    }

    public static void setMaxMemorySize(int maxMemorySize) {
        MAX_MEMORY_SIZE = maxMemorySize;
    }

    public static int getInputRadix() {
        return INPUT_RADIX;
    }

    public static void setInputRadix(int inputRadix) {
        INPUT_RADIX = inputRadix;
    }

    public static int getOutputRadix() {
        return OUTPUT_RADIX;
    }

    public static void setOutputRadix(int outputRadix) {
        OUTPUT_RADIX = outputRadix;
    }

    public static void setROUNDING(Rounding ROUNDING) {
        MainCalculatorPane.ROUNDING = ROUNDING;
    }

    public static void setPRECISION(int PRECISION) {
        MainCalculatorPane.PRECISION = PRECISION;
    }
}
