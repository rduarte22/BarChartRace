/**
 * AUTHORS:
 * Vitor Abreu nº18966
 * Rui Duarte nº21469
 **/
package pt.ipbeja.po2.chartracer.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class WriteFile {
    private final List<Data> arrFilled;

    public WriteFile(List<Data> arrFilled) throws IOException {
        this.arrFilled = arrFilled;

    }

    /**
     * will write a txt file where it saves the first 5 sorted values of the years 1500 and 2018
     *
     * @throws IOException
     */
    public void writeFile() throws IOException {
        FileWriter fileWriter = new FileWriter("./src/pt/ipbeja/po2/chartracer/generatedData/Data.txt");
        Collections.sort(arrFilled);
        int limit = 0;
        for (int i = 0; i < arrFilled.size(); i++) {
            if (limit == 5 && !arrFilled.get(i).getDate().equals(arrFilled.get(i - 1).getDate())) {
                limit = 0;
            }
            if ((arrFilled.get(i).getDate().equals("2018") || arrFilled.get(i).getDate().equals("1500")) && limit < 5) {
                fileWriter.write(arrFilled.get(i) + "\n");
                limit++;
            }
        }
        fileWriter.close();
    }
}




