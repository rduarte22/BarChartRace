/**
 * AUTHORS:
 * Vitor Abreu nº18966
 * Rui Duarte nº21469
 **/

package pt.ipbeja.po2.chartracer.gui;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class BarChartRacerBars extends Group {
    private final double xAxis;
    private final double yAxis;
    private double width;
    private final double height;
    private final String city;
    private final String Population;
    private final Rectangle rect;
    Text cityText;
    Text populationText;

    /**
     * draw the rectangles with the dimensions already assigned and indicate the names of the city and its population number
     *
     * @param xAxis      x coordinate
     * @param yAxis      y coordinate
     * @param width
     * @param height
     * @param city       get cities name
     * @param population get population
     * @param fillColor
     */

    public BarChartRacerBars(double xAxis, double yAxis, double width, double height, String city, int population, Color fillColor) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.width = width;
        this.height = height;
        this.city = city;
        this.Population = String.valueOf(population);

        this.rect = new Rectangle(xAxis, yAxis - height, this.width, height);
        this.rect.setFill(fillColor);
        getChildren().add(rect);

        this.cityText = new Text(xAxis + 2, yAxis - (height / 3), this.city);
        this.populationText = new Text(xAxis + width, yAxis - (height / 3), this.Population);
        getChildren().addAll(cityText, populationText);
    }

    /**
     * update the width of the bars
     *
     * @param width
     */
    public void updateWidth(double width) {
        this.width = width;
        this.rect.setWidth(width);
    }

    /**
     * update name of cities
     *
     * @param city
     */
    public void updateCityText(String city) {

        this.cityText.setText(city);
    }

    /**
     * update population text
     *
     * @param population
     */
    public void updatePopulationText(String population) {
        this.populationText.setText(population);
        this.populationText.setX(xAxis + this.width);
    }

    /**
     * update color bar
     *
     * @param color
     */
    public void updateColor(Color color) {

        this.rect.setFill(color);
    }
}

