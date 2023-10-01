/**
 * AUTHORS:
 * Vitor Abreu nº18966
 * Rui Duarte nº21469
 **/
package pt.ipbeja.po2.chartracer.model;

import javafx.application.Platform;

import java.util.List;

public class BarChartRacerUpdate {
    /**
     * is updating the board
     *
     * @param data
     * @param view
     */
    public void updateBoard(List<Data> data, View view) {
        executeInNewThread(() -> {
            for (int i = 1; i < data.size(); i++) {
                if (!data.get(i).getDate().equals(data.get(i - 1).getDate())) {
                    String temp = data.get(i).getDate();
                    pause(80);
                    Platform.runLater(() -> {
                        view.updateDate(temp);
                    });
                }
            }
        });
    }

    /**
     * Creates a new thread and runs a method on this new thread
     * Code provided by the teachers
     *
     * @param r Method that will be run in the new thread
     **/
    public static void executeInNewThread(Runnable r) {

        new Thread(r).start();
    }

    /**
     * pauses the current thread by time miliseconds
     * Code provided by the teachers
     *
     * @param time the time the current thread is stopped
     */
    public static void pause(long time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
        }
    }
}
