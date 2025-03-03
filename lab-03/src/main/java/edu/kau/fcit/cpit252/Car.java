package edu.kau.fcit.cpit252;

import org.apache.hc.core5.net.URIBuilder;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class Car implements Cloneable {
    private String make;
    private String model;
    private int year;
    private List<Recall> recalls;

    public Car(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.recalls = fetchRecalls();
    }

    public Car(Car other) {
        this.make = other.make;
        this.model = other.model;
        this.year = other.year;
        this.recalls = other.fetchRecalls();
    }

    private List<Recall> fetchRecalls() {
        System.out.println("********************************");
        System.out.println("Fetching recalls from NHTSA...");
        List<Recall> recallList = null;

        try {
            URIBuilder b = new URIBuilder("https://api.nhtsa.gov/recalls/recallsByVehicle");
            b.addParameter("make", this.make);
            b.addParameter("model", this.model);
            b.addParameter("modelYear", Integer.toString(this.year));
            URI uri = b.build();
            HttpResponse<String> response = HTTPHelper.sendGet(uri);
            if (response != null) {
                recallList = HTTPHelper.parseIntoCollection(response.body(), List.class, Recall.class);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return recallList;
    }

    @Override
    public Car clone() {
        try {
            Car clonedCar = (Car) super.clone();
            clonedCar.recalls = (this.recalls != null) ? new ArrayList<>(this.recalls) : null;
            return clonedCar;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public String getMake() {
        return this.make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Recall> getRecalls() {
        return this.recalls;
    }

    public String toString() {
        StringBuilder recallsInfo = new StringBuilder();
        recallsInfo.append(String.format("%d recall(s).\n", (this.recalls != null) ? this.recalls.size() : 0));
        if (this.recalls != null) {
            for (Recall recall : this.recalls) {
                recallsInfo.append(recall.toString());
            }
        }
        return this.make + "\t" + this.model + "\t" + this.year + "\n" + recallsInfo;
    }
}