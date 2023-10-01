/**
 * AUTHORS:
 * Vitor Abreu nº18966
 * Rui Duarte nº21469
 **/
package pt.ipbeja.po2.chartracer.model;

import java.util.Objects;


public class Data implements Comparable<Data> {

    private final String date;
    private final String names;
    private final String countries;
    private final int dataNumber;
    private final String region;

    public Data(String date, String names, String countries, int dataNumber, String region) {
        this.date = date;
        this.names = names;
        this.countries = countries;
        this.dataNumber = dataNumber;
        this.region = region;

    }

    /**
     * @return
     */
    public String toString() {

        return date + "," + names + "," + countries + "," + dataNumber + "," + region;
    }

    /**
     * receive the date
     *
     * @return
     */
    public String getDate() {

        return date;
    }

    /**
     * @return
     */
    public String getNames() {

        return names;
    }

    /**
     * @return
     */
    public String getCountries() {

        return countries;
    }

    /**
     * @return
     */
    public String getRegion() {

        return region;
    }

    /**
     * @return
     */
    public int getDataNumber() {

        return dataNumber;
    }

    @Override
    public int compareTo(Data o) {
        if (o.getDate().equals(this.getDate())) {
            return o.getDataNumber() - this.getDataNumber();
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data = (Data) o;
        return dataNumber == data.dataNumber && Objects.equals(date, data.date) && Objects.equals(names, data.names) && Objects.equals(countries, data.countries) && Objects.equals(region, data.region);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, names, countries, dataNumber, region);
    }
}
