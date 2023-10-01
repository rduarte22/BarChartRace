/**
 * AUTHORS:
 * Vitor Abreu nº18966
 * Rui Duarte nº21469
 **/
package pt.ipbeja.po2.chartracer.gui;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import pt.ipbeja.po2.chartracer.model.ReadFile;
import pt.ipbeja.po2.chartracer.model.Data;
import pt.ipbeja.po2.chartracer.model.View;

import java.util.ArrayList;
import java.util.List;

public class BarChartRacerBoard extends Pane implements View {

    private final ReadFile game;
    private String date;
    private final int width;
    private final int height;
    private Text dateText;


    private final double NUMBER_BARS = 12;
    private final double MAX_BARS = 12;
    private final double MARGIN = 0.90;
    private final List<BarChartRacerBars> barsList;
    private List<String> newRegionList;
    private Text titleText;


    public BarChartRacerBoard(ReadFile game, int height, int width, String date) {
        this.game = game;
        this.date = date;
        this.height = height;
        this.width = width;
        this.barsList = new ArrayList<>();

        drawBars();
    }

    /**
     * draw 2 lines, one vertical and one horizontal
     * draws a big bar that will receive the largest data (largest population number)
     * will draw the missing bars taking into account the width of the big bars
     */
    public void drawBars() {

        getArrayWithoutDuplicate();
        setPrefSize(width, height);
        List<Data> data = this.game.getOrderedData();

        double startXGraph = width * ((1 - MARGIN) / 2);
        double endXGraph = width * MARGIN;

        double startYGraph = height * MARGIN;
        double endYGraph = height * (1 - MARGIN);

        Line hLine = new Line();
        hLine.setStartY(startYGraph);
        hLine.setEndY(startYGraph);
        hLine.setStartX(startXGraph);
        hLine.setEndX(endXGraph);

        Line vLine = new Line();
        vLine.setStartY(startYGraph);
        vLine.setEndY(endYGraph);
        vLine.setStartX(startXGraph);
        vLine.setEndX(startXGraph);

        int limit = 0;
        double barWidth;
        double biggestBarWidth = 0.0;
        int biggestBarPopulation = 0;

        double barHeight = (endYGraph - startYGraph / (MAX_BARS + 1));
        double yAxis = endYGraph + barHeight;
        double colorScope = 360.0 / MAX_BARS;

        for (int i = 0; i < data.size(); i++) {
            if ((data.get(i).getDate().equals(this.date) && limit < MAX_BARS)) {
                Color colorChange = Color.hsb(limit * colorScope, 0.4, 1);
                if (limit == 0) {
                    biggestBarPopulation = data.get(i).getDataNumber();
                    biggestBarWidth = endXGraph - startXGraph;
                    BarChartRacerBars BigBar = new BarChartRacerBars(startXGraph, endYGraph + barHeight, biggestBarWidth, barHeight, data.get(i).getNames(), data.get(i).getDataNumber(), colorChange);
                    getChildren().add(BigBar);
                    this.barsList.add(BigBar);
                } else {
                    yAxis += (height * MARGIN / (NUMBER_BARS + 1) + (1 / (NUMBER_BARS + 1)));

                    barWidth = (data.get(i).getDataNumber() * biggestBarWidth) / biggestBarPopulation;

                    BarChartRacerBars bar = new BarChartRacerBars(startXGraph, yAxis, barWidth, barHeight, data.get(i).getNames(), data.get(i).getDataNumber(), colorChange);
                    getChildren().add(bar);
                    this.barsList.add(bar);
                }
                limit++;
            }
        }
        drawDateText();
        drawTitleText();
        getChildren().addAll(hLine, vLine, this.dateText, this.titleText);
    }

    /**
     * update the date
     *
     * @param date
     */
    public void updateDate(String date) {
        List<Data> data = this.game.getOrderedData();

        this.date = date;
        int limit = 0;
        double barWidth;
        double biggestBarWidth = 0.0;
        int biggestBarPopulation = 0;

        double startXGraph = width * ((1 - MARGIN) / 2);
        double endXGraph = width * MARGIN;

        for (int i = 0; i < data.size(); i++) {
            if ((data.get(i).getDate().equals(this.date) && limit < MAX_BARS)) {
                Color colorChange = getColor(data.get(i).getRegion());
                if (limit == 0) {
                    biggestBarPopulation = data.get(i).getDataNumber();
                    biggestBarWidth = endXGraph - startXGraph;
                    this.barsList.get(limit).updateWidth(biggestBarWidth);

                } else {
                    barWidth = (data.get(i).getDataNumber() * biggestBarWidth) / biggestBarPopulation;
                    this.barsList.get(limit).updateWidth(barWidth);
                }
                this.barsList.get(limit).updateCityText(data.get(i).getNames());
                this.barsList.get(limit).updatePopulationText("" + data.get(i).getDataNumber());
                this.dateText.setText(data.get(i).getDate());
                this.barsList.get(limit).updateColor(colorChange);
                limit++;
            }
        }
    }

    /**
     * will write the current date in the lower right corner
     */
    private void drawDateText() {
        double startXGraph = width * ((1 - MARGIN) / 2);
        double endXGraph = width * MARGIN;
        double startYGraph = height * MARGIN;
        double endYGraph = height * (1 - MARGIN);

        this.dateText = new Text(this.date);
        dateText.setTextAlignment(TextAlignment.RIGHT);
        dateText.setX(endXGraph - (startXGraph * 4));
        dateText.setY(startYGraph - endYGraph);
        dateText.setFill(Color.GRAY);
        dateText.setStyle("-fx-font: 60 arial;");
    }

    /**
     * will write the file title
     */
    private void drawTitleText() {
        this.titleText = new Text(this.game.getTitleText());
        titleText.setX(width * ((1 - MARGIN) / 2));
        titleText.setY(height * (1 - MARGIN) - 20);
        titleText.setFill(Color.GRAY);
        titleText.setStyle("-fx-font: 40 arial;");
    }

    /**
     * will remove repeated region names
     */
    public void getArrayWithoutDuplicate() {
        List<Data> data = this.game.getData();
        List<String> regionList = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            regionList.add(data.get(i).getRegion());
        }
        this.newRegionList = removeDuplicates(regionList);
        removeDuplicates(regionList);

    }

    /**
     * atribute a color per region
     *
     * @param region
     * @return
     */

    public Color getColor(String region) {

        double colorScope = 360.0 / this.newRegionList.size();

        for (int i = 0; i < this.newRegionList.size(); i++) {
            if (region.equals(this.newRegionList.get(i))) {
                colorScope = i * colorScope;

            }
        }
        return Color.hsb(colorScope, 0.4, 1);
    }

    /**
     * Function to remove duplicates from an ArrayList
     * Function gotten from https://www.geeksforgeeks.org/how-to-remove-duplicates-from-arraylist-in-java/
     **/
    public static <String> ArrayList<String> removeDuplicates(List<String> list) {
        // Create a new ArrayList
        ArrayList<String> newList = new ArrayList<String>();

        // Traverse through the first list
        for (String element : list) {

            // If this element is not present in newList
            // then add it
            if (!newList.contains(element)) {
                newList.add(element);
            }
        }
        // return the new list
        return newList;
    }
}



