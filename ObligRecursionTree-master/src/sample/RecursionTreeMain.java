package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.Random;

/**
 * Obligatorisk oppgave - Tree (rekursjon)
 *
 *  Anders Olai Pedersen - 225280
 *  Tommy Otervik - 225245
 */

public class RecursionTreeMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        RecursiveTreePane pane = new RecursiveTreePane();
        RightMenuVbox rBox = new RightMenuVbox();

        rBox.getBtnOk().setOnAction(e -> {
            pane.disco = rBox.getDiscoCb().isSelected();
            pane.setTree(rBox.getOrder(), rBox.getLength(), rBox.getRandomSliderValue());
        });

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(pane);
        borderPane.setRight(rBox);

        Scene scene = new Scene(borderPane, 700, 600);
        primaryStage.setTitle("RecTree");
        primaryStage.setScene(scene);
        primaryStage.show();

        pane.widthProperty().addListener(ov -> pane.paint());
        pane.heightProperty().addListener(ov -> pane.paint());
    }

    static class RecursiveTreePane extends Pane {

        private int order;
        private double len;
        private double sliderRandomVal;
        private boolean disco = false;


        public void setTree(int order, double len, double sliderRandomVal){
            this.order = order;
            this.len = len;
            this.sliderRandomVal = sliderRandomVal;

            if (disco) {
                this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
            } else{
                this.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
            }

            paint();
        }



        public RecursiveTreePane() {
            this.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));

        }

        protected void paint() {
            Point2D p = new Point2D(getWidth() / 2, getHeight());

            this.getChildren().clear();
            displayTree(order, p, -90, len);
        }


        /**
         *
         * @param n depth / number of recursive "iterations"
         * @param origin the x and y on where to start drawing
         * @param rotation degrees
         * @param length how long the line should be (pixels)
         */
        private void displayTree(int n, Point2D origin, double rotation, double length) {

            Point2D endPoint = endPoint(origin, rotation, length);

            int red = (int) (Math.random() * 256);
            int green = (int) (Math.random() * 256);
            int blue = (int) (Math.random() * 256);


            if (n == 0) {
                Line line = new Line(origin.getX(), origin.getY(), endPoint.getX(), endPoint.getY());

                if (disco) line.setStroke(Color.rgb(red,green,blue)); else line.setStroke(Color.WHITE);

                getChildren().add(line);
            }
            else if (length > 2) {

                Line line = new Line(origin.getX(), origin.getY(), endPoint.getX(), endPoint.getY());

                if (disco) line.setStroke(Color.rgb(red,green,blue)); else line.setStroke(Color.WHITE);

                getChildren().add(line);

                if(sliderRandomVal == 0.0) {
                    displayTree(n - 1, endPoint, rotation - randomRotation(sliderRandomVal), length * randomLength(sliderRandomVal));
                    displayTree(n - 1, endPoint, rotation + randomRotation(sliderRandomVal), length * randomLength(sliderRandomVal));
                }
                else {
                    // Grein nr 1 blir blokkert, tegner grein 2
                    if(randomNumberGen(1, 10) == 1) {
                        displayTree(n - 1, endPoint, rotation + randomRotation(sliderRandomVal), length * randomLength(sliderRandomVal));
                    }
                    // Grein nr 2 blir blokkert, tegner grein 1
                    else if(randomNumberGen(1, 10) == 1) {
                        displayTree(n - 1, endPoint, rotation - randomRotation(sliderRandomVal), length * randomLength(sliderRandomVal));
                    }
                    // Ingen greiner blokkert, tegner begge.
                    else {
                        displayTree(n - 1, endPoint, rotation - randomRotation(sliderRandomVal), length * randomLength(sliderRandomVal));
                        displayTree(n - 1, endPoint, rotation + randomRotation(sliderRandomVal), length * randomLength(sliderRandomVal));
                    }
                }
            }
        }

        private int randomRotation(double level) {
            int number = 30;
            switch((int) level) {
                case 0:
                    break;
                case 1:
                    number = randomNumberGen(25, 35);
                    break;
                case 2:
                    number = randomNumberGen(20, 40);
                    break;
                case 3:
                    number = randomNumberGen(15, 45);
                    break;
                default:
            }
            return number;
        }


        // Generate random number within a range
        private int randomNumberGen(int low, int high) {
            Random rn = new Random();
            int range = high - low + 1;
            return rn.nextInt(range) + low;
        }

        private float randomNumberGen(float low, float high) {
            Random r = new Random();
            return low + r.nextFloat() * (high - low);
        }

        private float randomLength(double level) {
            float tall = 0.8f;
            switch((int) level) {
                case 0:
                    break;
                case 1:
                    tall = randomNumberGen(0.7F, 0.8F);
                    break;
                case 2:
                    tall = randomNumberGen(0.6F, 0.9F);
                    break;
                case 3:
                    tall = randomNumberGen(0.5F, 1.1F);
                    break;
                default:
            }
            return tall;
        }


        /**
         * Creates end point for
         * @param origin Start point
         * @param rotation Rotation (degrees)
         * @param len Length
         *
         */
        private Point2D endPoint(Point2D origin, double rotation, double len) {
            double x = origin.getX();
            double y = origin.getY();

            double radians = Math.PI / 180.0 * rotation;  // Math.toRadians()

            x += (len * Math.cos(radians));
            y += (len * Math.sin(radians));

            return new Point2D(x, y);
        }
    }




    public static void main(String[] args) {
        launch(args);
    }
}
