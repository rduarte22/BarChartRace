/**
 * AUTHORS:
 * Vitor Abreu nº18966
 * Rui Duarte nº21469
 **/
package pt.ipbeja.po2.chartracer.gui;

import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import pt.ipbeja.po2.chartracer.model.Data;
import pt.ipbeja.po2.chartracer.model.ReadFile;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MenuBarItems extends Pane {
    private VBox vb;
    private final ReadFile readFile;

    public MenuBarItems(ReadFile readFile) {
        this.readFile = readFile;
        createMenu();
    }

    /**
     * create the menu
     */
    public void createMenu() {
        // create a menu
        Menu m = new Menu("Menu");
        // create menuitems
        CheckMenuItem generateFile = new CheckMenuItem("Generate File");
        MenuItem start = new MenuItem("Start");
        start.setOnAction((event) -> {
            readFile.setBarChartRacerUpdate();
            if (generateFile.isSelected()) {
                try {
                    generate();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction((event) -> {
            System.exit(0);
        });
        // add menu items to menu
        m.getItems().add(start);
        m.getItems().add(generateFile);
        m.getItems().add(exit);
        // create a menubar
        MenuBar mb = new MenuBar();
        // add menu to menubar
        mb.getMenus().add(m);
        // create a VBox
        this.vb = new VBox(mb);
    }

    public VBox getVb() {
        return vb;
    }

    /**
     * it will generate a txt file with the data of the chosen file
     *
     * @throws IOException
     */
    public void generate() throws IOException {
        List<Data> orderedData = readFile.getOrderedData();
        List<Integer> dataSets = readFile.getDataSets();
        String dataToGenerate = "";

        String numberOfDataSets = "Number of data sets in file: ";
        String firstDate = "First date: ";
        String lastDate = "Last date: ";
        String avg = "Average number of lines in each data set: ";
        String numCols = "Number of columns in each data set: 5";
        String max = "Maximum value considering all data sets: ";
        String min = "Minimum value considering all data sets: ";

        firstDate += orderedData.get(0).getDate();

        lastDate += orderedData.get(orderedData.size() - 1).getDate();

        numberOfDataSets += dataSets.size();

        avg += getAverage(dataSets);
        max += getMaximum(orderedData);
        min += getMinimum(orderedData);

        dataToGenerate += firstDate + "\n" + lastDate + "\n" + numberOfDataSets + "\n" + avg + "\n" + numCols + "\n" + max + "\n" + min;

        FileWriter fileWriter = new FileWriter("./src/pt/ipbeja/po2/chartracer/generatedData/StatsData.txt");
        fileWriter.write(dataToGenerate);
        fileWriter.close();
    }

    /**
     * get minimum value considering all data sets
     *
     * @param list
     * @return
     */
    private int getMinimum(List<Data> list) {
        int min = list.get(0).getDataNumber();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getDataNumber() < min) {
                min = list.get(i).getDataNumber();
            }
        }
        return min;
    }

    /**
     * get maximum value considering all data sets
     *
     * @param list
     * @return
     */
    private int getMaximum(List<Data> list) {
        int max = list.get(0).getDataNumber();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getDataNumber() > max) {
                max = list.get(i).getDataNumber();
            }
        }
        return max;
    }

    /**
     * get average number of lines in each data set
     *
     * @param list
     * @return
     */
    private double getAverage(List<Integer> list) {
        double totalDataSets = 0.0;
        for (int i = 0; i < list.size(); i++) {
            totalDataSets += list.get(i);
        }
        return totalDataSets / list.size();
    }
}




