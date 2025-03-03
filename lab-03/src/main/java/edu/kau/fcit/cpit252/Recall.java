package edu.kau.fcit.cpit252;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Recall {
    @JsonProperty("Manufacturer")
    private String manufacturer;
    private boolean parkIt;
    private boolean parkOutSide;
    @JsonProperty("NHTSAActionNumber")
    private String nhtsaActionNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @JsonProperty("ReportReceivedDate")
    private Date reportReceivedDate;
    @JsonProperty("Component")
    private String component;
    @JsonProperty("Summary")
    private String summary;
    @JsonProperty("Consequence")
    private String consequence;
    @JsonProperty("Remedy")
    private String remedy;
    @JsonProperty("Notes")
    private String notes;

    public Date getReportReceivedDate(){
        return this.reportReceivedDate;
    }

    public String getComponent(){
        return this.component;
    }


    @Override
    public String toString() {
        return "Recall Details:\n\t" +
                "\n\tReport Received Date: " + this.reportReceivedDate.toString() +
                "\n\tComponent: " + this.component + "\n\tSummary: " + this.summary +
                "\n\tConsequence: " + this.consequence + "\n\tRemedy: " + this.remedy +
                "\n\tPark it: " + this.parkIt + ".\t\tPark Outside: " + this.parkOutSide +
                "\n\tNotes: " + this.notes;
    }
}
