package sample;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Obligatorisk oppgave - Tree (rekursjon)
 *
 *  Anders Olai Pedersen - 225280
 *  Tommy Otervik - 225245
 */

public class RightMenuVbox extends VBox {

    private final Slider randomSlider = new Slider(0,3,0);
    private final TextField lengthTf = new TextField();
    private final TextField orderTf = new TextField();
    private final Button btnOk = new Button("Ok! Go!");
    private final CheckBox discoCb = new CheckBox();

    private static final int TF_WIDTH = 70;

    public RightMenuVbox() {

        randomSlider.setMajorTickUnit(1);
        randomSlider.setMinorTickCount(0);
        randomSlider.setShowTickMarks(true);
        randomSlider.setShowTickLabels(true);
        randomSlider.setSnapToTicks(true);


        lengthTf.setText("100");
        lengthTf.setMaxWidth(TF_WIDTH);
        orderTf.setText("0");
        orderTf.setMaxWidth(TF_WIDTH);


        this.setSpacing(5);
        this.setPadding(new Insets(20));
        this.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));


        getChildren().addAll(new Label(""),
                new Label("Grad random:"), randomSlider,
                new Label("Lengde (stamme): "), lengthTf,
                new Label("Nivå: "), orderTf,
                new Label("Disco?"), discoCb,
                btnOk

        );
    }

    public Button getBtnOk() {
        return btnOk;
    }

    public TextField getOrderTf() {
        return orderTf;
    }

    public int getOrder() {
        if (!orderTf.getText().trim().isEmpty()) {
            return Integer.parseInt(orderTf.getText());
        }
        return 0;
    }

    public double getLength() {
        if (!lengthTf.getText().trim().isEmpty()) {
            return Double.parseDouble(lengthTf.getText());
        }
        return 0.0;
    }


    public double getRandomSliderValue() {
        return randomSlider.getValue();
    }

    public CheckBox getDiscoCb() {
        return discoCb;
    }
}
