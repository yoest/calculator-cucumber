package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import unit_converter.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Stream;

public class ConverterPane extends ContentPane implements Initializable {

    @FXML
    private ChoiceBox<String> unitChoice;

    @FXML
    private ChoiceBox<String> fromChoice;

    @FXML
    private ChoiceBox<String> toChoice;

    @FXML
    private TextField inputTextField;

    @FXML
    private TextField outputTextField;

    @FXML
    private Button convertButton;

    private MeasureConverter selectedConverter;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        handleChangeInChoiceBox();
        handleChangeInTextField();

        convertButton.setOnAction(actionEvent -> convert());
    }

    /** Add all the listener to the choice box to update them when a change is made. */
    private void handleChangeInChoiceBox() {
        unitChoice.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            List<String> unitValues;

            switch (unitChoice.getValue()) {
                case "Currency" -> {
                    unitValues = Stream.of(CurrencyConverter.CurrencyUnits.values()).map(CurrencyConverter.CurrencyUnits::name).toList();
                    assignValuesToChoiceBox(unitValues);
                }
                case "Energy" -> {
                    unitValues = Stream.of(EnergyConverter.EnergyUnits.values()).map(EnergyConverter.EnergyUnits::name).toList();
                    assignValuesToChoiceBox(unitValues);
                }
                case "Length" -> {
                    unitValues = Stream.of(LengthConverter.LengthUnits.values()).map(LengthConverter.LengthUnits::name).toList();
                    assignValuesToChoiceBox(unitValues);
                }
                case "Mass" -> {
                    unitValues = Stream.of(MassConverter.MassUnits.values()).map(MassConverter.MassUnits::name).toList();
                    assignValuesToChoiceBox(unitValues);
                }
                case "Temperature" -> {
                    unitValues = Stream.of(TemperatureConverter.TemperatureUnits.values()).map(TemperatureConverter.TemperatureUnits::name).toList();
                    assignValuesToChoiceBox(unitValues);
                }
                case "Time" -> {
                    unitValues = Stream.of(TimeConverter.TimeUnits.values()).map(TimeConverter.TimeUnits::name).toList();
                    assignValuesToChoiceBox(unitValues);
                }
            }
        });
        unitChoice.setValue("Length");
    }

    /** Add all the listener to the text fields to update them when a change is made. */
    private void handleChangeInTextField() {
        // Input field cannot be another thing than digit and cannot be empty (so we replace by 0 when it is the case)
        inputTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[+-]?([0-9]*[.])?[0-9]+")) {
                inputTextField.setText(newValue.replaceAll("[^([+-]?([0-9]*[.])?[0-9]+)]", ""));
            }

            // Cannot contain more than one points
            int pointIndex = inputTextField.getText().indexOf(".");
            if(pointIndex != -1) {
                String newText = inputTextField.getText().substring(0, pointIndex + 1) + inputTextField.getText().substring(pointIndex + 1).replaceAll("\\.", "");
                inputTextField.setText(newText);
            }
        });
        inputTextField.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            if(!t1 && inputTextField.getText().length() == 0)
                inputTextField.setText("0");
        });
        inputTextField.setText("0");

        outputTextField.setEditable(false);
    }

    /** Assign the correct values to the ChoiceBox based on the selected converter.
     *
     * @param unitValues: all the values of the converter to assign to the choice box
     */
    private void assignValuesToChoiceBox(List<String> unitValues) {
        ObservableList<String> enumNames = FXCollections.observableList(unitValues);
        fromChoice.setItems(enumNames);
        fromChoice.setValue(enumNames.get(0));
        toChoice.setItems(enumNames);
        toChoice.setValue(enumNames.get(0));
    }

    private void convert() {
        double value = Double.parseDouble(inputTextField.getText());

        switch (unitChoice.getValue()) {
            case "Currency" -> {
                CurrencyConverter.CurrencyUnits from = CurrencyConverter.CurrencyUnits.valueOf(fromChoice.getValue());
                CurrencyConverter.CurrencyUnits to = CurrencyConverter.CurrencyUnits.valueOf(toChoice.getValue());
                convertWithConverter(new CurrencyConverter(value, from), to);
            }
            case "Energy" -> {
                EnergyConverter.EnergyUnits from = EnergyConverter.EnergyUnits.valueOf(fromChoice.getValue());
                EnergyConverter.EnergyUnits to = EnergyConverter.EnergyUnits.valueOf(toChoice.getValue());
                convertWithConverter(new EnergyConverter(value, from), to);
            }
            case "Length" -> {
                LengthConverter.LengthUnits from = LengthConverter.LengthUnits.valueOf(fromChoice.getValue());
                LengthConverter.LengthUnits to = LengthConverter.LengthUnits.valueOf(toChoice.getValue());
                convertWithConverter(new LengthConverter(value, from), to);
            }
            case "Mass" -> {
                MassConverter.MassUnits from = MassConverter.MassUnits.valueOf(fromChoice.getValue());
                MassConverter.MassUnits to = MassConverter.MassUnits.valueOf(toChoice.getValue());
                convertWithConverter(new MassConverter(value, from), to);
            }
            case "Temperature" -> {
                TemperatureConverter.TemperatureUnits from = TemperatureConverter.TemperatureUnits.valueOf(fromChoice.getValue());
                TemperatureConverter.TemperatureUnits to = TemperatureConverter.TemperatureUnits.valueOf(toChoice.getValue());
                convertWithConverter(new TemperatureConverter(value, from), to);
            }
            case "Time" -> {
                TimeConverter.TimeUnits from = TimeConverter.TimeUnits.valueOf(fromChoice.getValue());
                TimeConverter.TimeUnits to = TimeConverter.TimeUnits.valueOf(toChoice.getValue());
                convertWithConverter(new TimeConverter(value, from), to);
            } default -> System.out.println("You need to define a converter for: " + unitChoice.getValue());
        }
    }

    /** Convert the wanted value to the correct unit.
     *
     * @param converter: converter of a specific type to convert from one unit to another.
     * @param to: unit to convert to.
     */
    private void convertWithConverter(MeasureConverter converter, Unit to) {
        double result = converter.getAs(to);
        outputTextField.setText(String.valueOf(result));
    }

    @Override
    public AnchorPane start() {
        return super.initController("converter.fxml");
    }
}
