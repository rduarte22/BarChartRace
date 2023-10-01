/**
 * AUTHORS:
 * Vitor Abreu nº18966
 * Rui Duarte nº21469
 **/

package pt.ipbeja.po2.chartracer.model;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReadFileTest {

    @Test
    void test1() throws IOException {
        File file1 = new File("./src/resources/cities.txt");
        ReadFile readFile = new ReadFile(file1);
        List<Data> list = readFile.getData();
        List<Data> compareTest1 = new ArrayList<>();

        compareTest1.add(new Data("1500", "Beijing", "China", 672, "East Asia"));
        compareTest1.add(new Data("1500", "Cairo", "Egypt", 400, "Middle East"));
        compareTest1.add(new Data("1500", "Cuttack", "India", 140, "South Asia"));
        compareTest1.add(new Data("2018", "Shanghai", "China", 25779, "East Asia"));
        compareTest1.add(new Data("2018", "São Paulo", "Brazil", 21698, "Latin America"));
        compareTest1.add(new Data("2018", "Tokyo", "Japan", 38194, "East Asia"));

        assertEquals(compareTest1.get(0), list.get(0));
        assertEquals(compareTest1.get(1), list.get(1));
        assertEquals(compareTest1.get(2), list.get(2));
        assertEquals(compareTest1.get(3), list.get(list.size() - 3));
        assertEquals(compareTest1.get(4), list.get(list.size() - 2));
        assertEquals(compareTest1.get(5), list.get(list.size() - 1));
    }

    @Test
    void test2() throws IOException {
        File file2 = new File("./src/resources/cities.txt");
        ReadFile readFile = new ReadFile(file2);
        List<Data> list = readFile.getOrderedData();
        List<Data> compareTestYear1500 = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getDate().equals("1500")) compareTestYear1500.add(list.get(i));
        }
        List<Data> expectTestYear1500 = new ArrayList<>();

        expectTestYear1500.add(new Data("1500", "Beijing", "China", 672, "East Asia"));
        expectTestYear1500.add(new Data("1500", "Vijayanagar", "India", 500, "South Asia"));
        expectTestYear1500.add(new Data("1500", "Cairo", "Egypt", 400, "Middle East"));
        expectTestYear1500.add(new Data("1500", "Hangzhou", "China", 250, "East Asia"));
        expectTestYear1500.add(new Data("1500", "Tabriz", "Iran", 250, "Middle East"));
        expectTestYear1500.add(new Data("1500", "Gauda", "India", 200, "South Asia"));
        expectTestYear1500.add(new Data("1500", "Istanbul", "Turkey", 200, "Europe"));
        expectTestYear1500.add(new Data("1500", "Paris", "France", 185, "Europe"));
        expectTestYear1500.add(new Data("1500", "Guangzhou", "China", 150, "East Asia"));
        expectTestYear1500.add(new Data("1500", "Nanjing", "China", 147, "East Asia"));
        expectTestYear1500.add(new Data("1500", "Cuttack", "India", 140, "South Asia"));
        expectTestYear1500.add(new Data("1500", "Fez", "Morocco", 130, "Middle East"));

        assertEquals(expectTestYear1500, compareTestYear1500);

        List<Data> compareTestYear2018 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getDate().equals("2018")) compareTestYear2018.add(list.get(i));
        }
        List<Data> expectTestYear2018 = new ArrayList<>();

        expectTestYear2018.add(new Data("2018", "Tokyo", "Japan", 38194, "East Asia"));
        expectTestYear2018.add(new Data("2018", "Delhi", "India", 27890, "South Asia"));
        expectTestYear2018.add(new Data("2018", "Shanghai", "China", 25779, "East Asia"));
        expectTestYear2018.add(new Data("2018", "Beijing", "China", 22674, "East Asia"));
        expectTestYear2018.add(new Data("2018", "Mumbai", "India", 22120, "South Asia"));
        expectTestYear2018.add(new Data("2018", "São Paulo", "Brazil", 21698, "Latin America"));
        expectTestYear2018.add(new Data("2018", "Mexico City", "Mexico", 21520, "Latin America"));
        expectTestYear2018.add(new Data("2018", "Osaka", "Japan", 20409, "East Asia"));
        expectTestYear2018.add(new Data("2018", "Cairo", "Egypt", 19850, "Middle East"));
        expectTestYear2018.add(new Data("2018", "Dhaka", "Bangladesh", 19633, "South Asia"));
        expectTestYear2018.add(new Data("2018", "New York", "United States", 18713, "North America"));
        expectTestYear2018.add(new Data("2018", "Karachi", "Pakistan", 18185, "South Asia"));

        assertEquals(expectTestYear2018, compareTestYear2018);
    }

    @Test
    void test3() throws IOException {

        File file2 = new File("./src/resources/cities.txt");
        ReadFile readFile2 = new ReadFile(file2);

        File file3 = new File("./src/pt/ipbeja/po2/chartracer/generatedData/Data.txt");

        WriteFile writeFile = new WriteFile(readFile2.getOrderedData());
        writeFile.writeFile();
        ReadFile readFile = new ReadFile(file3);

        List<Data> list = readFile.getData();
        List<Data> compareTest3 = new ArrayList<>();

        compareTest3.add(new Data("1500", "Beijing", "China", 672, "East Asia"));
        compareTest3.add(new Data("1500", "Vijayanagar", "India", 500, "South Asia"));
        compareTest3.add(new Data("1500", "Cairo", "Egypt", 400, "Middle East"));
        compareTest3.add(new Data("1500", "Hangzhou", "China", 250, "East Asia"));
        compareTest3.add(new Data("1500", "Tabriz", "Iran", 250, "Middle East"));
        compareTest3.add(new Data("2018", "Tokyo", "Japan", 38194, "East Asia"));
        compareTest3.add(new Data("2018", "Delhi", "India", 27890, "South Asia"));
        compareTest3.add(new Data("2018", "Shanghai", "China", 25779, "East Asia"));
        compareTest3.add(new Data("2018", "Beijing", "China", 22674, "East Asia"));
        compareTest3.add(new Data("2018", "Mumbai", "India", 22120, "South Asia"));

        assertEquals(compareTest3, list);
    }
}