/**
 * AUTHORS:
 * Vitor Abreu nº18966
 * Rui Duarte nº21469
 **/
package pt.ipbeja.po2.chartracer.model;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReadFile {

    public List<Data> data;
    public List<Data> getOrderedData;
    private final File file;
    private View view;
    public String titleText;
    public List<Integer> dataSets = new ArrayList<>();
    BarChartRacerUpdate barChartRacerUpdate = new BarChartRacerUpdate();

    public void setView(View view) {
        this.view = view;
    }

    public ReadFile(File file) throws IOException {
        this.file = file;
        this.fileRead();
    }

    public void setBarChartRacerUpdate() {
        barChartRacerUpdate.updateBoard(this.data, this.view);
    }

    /**
     * save the data read from the file
     *
     * @return
     */
    public List<Data> getData() {
        return this.data;
    }

    /**
     * saves the sorted data read from the file
     *
     * @return
     */
    public List<Data> getOrderedData() {
        Collections.sort(getOrderedData);
        return this.getOrderedData;
    }

    public List<Integer> getDataSets() {
        return dataSets;
    }

    /**
     * will read the data from the file
     **/
    private void fileRead() {
        this.data = new ArrayList<>();
        this.getOrderedData = new ArrayList<>();
        String line;
        int i = 0;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while ((line = bufferedReader.readLine()) != null) {
                if (i == 0) {
                    this.titleText = Files.readAllLines(Paths.get(file.getAbsolutePath())).get(0);
                    // System.out.println(titleText);
                    i++;
                }
                if (line.contains(",")) {
                    String[] values = line.split(",");
                    if (values.length > 3) {
                        data.add(new Data(values[0], values[1], values[2], Integer.parseInt(values[3]), values[4]));
                        getOrderedData.add(new Data(values[0], values[1], values[2], Integer.parseInt(values[3]), values[4]));
                        //System.out.println(values[1]);
                        i++;
                    }
                } else {
                    if (!line.isEmpty() && i >= 4) {
                        dataSets.add(Integer.parseInt(line));
                    }
                }
            }
            // System.out.println(dataSets);
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getTitleText() {
        return titleText;
    }
}
