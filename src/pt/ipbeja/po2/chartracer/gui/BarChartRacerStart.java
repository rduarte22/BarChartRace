/**
 * AUTHORS:
 * Vitor Abreu nº18966
 * Rui Duarte nº21469
 **/
package pt.ipbeja.po2.chartracer.gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pt.ipbeja.po2.chartracer.model.ReadFile;
import pt.ipbeja.po2.chartracer.model.Data;
import pt.ipbeja.po2.chartracer.model.WriteFile;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class BarChartRacerStart extends Application {

    private ReadFile readFile;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * make the choice of files, write the file, assign the stage dimension, create the menuBar and a border
     *
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("./src/resources"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text files", "*.txt"));
        File file = fileChooser.showOpenDialog(stage);

        if (file == null) {
            new Alert(Alert.AlertType.ERROR, "Não existe nenhum ficheiro").showAndWait();
            System.exit(0);
        }

        this.readFile = new ReadFile(file);

        WriteFile writeFile = new WriteFile(readFile.getOrderedData());
        writeFile.writeFile();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        MenuBarItems menuBarItems = new MenuBarItems(this.readFile);
        List<Data> data = this.readFile.getData();

        BarChartRacerBoard board = new BarChartRacerBoard(readFile, height, width, data.get(0).getDate());
        readFile.setView(board);
        //readFile.setBarChartRacerUpdate();
        Group root = new Group(board);
        root.getChildren().add(menuBarItems.getVb());

        Scene scene = new Scene(root, width, height);
        //Setting title to the Stage
        stage.setTitle("Bar Chart Race");
        //Adding scene to the stage
        stage.setScene(scene);
        //Displaying the contents of the stage
        stage.show();
    }
}
