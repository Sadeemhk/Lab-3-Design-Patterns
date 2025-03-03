package edu.kau.fcit.cpit252;

import java.text.SimpleDateFormat;

public class RecallViewer {
    private Car car;

    public RecallViewer(Car car){
        this.car  = car;
    }

    public void printTableView(){
        if (car.getRecalls().size() < 0){
            return;
        }
        String lineSeparator = "+--------------+---------+-------+-------------+--------------------------------------------------------------------";
        System.out.println(lineSeparator);
        System.out.println("| Manufacturer | Model   | Year  |    Date     |                          Component                                 ");
        System.out.println(lineSeparator);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        for(Recall recall: car.getRecalls()){
            String reportedDate = formatter.format(recall.getReportReceivedDate());
            System.out.println(car.getMake()  + generateWhiteSpaces(9) + " | " +
                    car.getModel() + generateWhiteSpaces(1) +  " | " +
                    car.getYear() +  " | " + reportedDate + generateWhiteSpaces(1) + " |" +
                    recall.getComponent());
            System.out.println(lineSeparator);
        }
    }

    private String generateWhiteSpaces(int total){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < total; i++) {
            builder.append(" ");
        }
        return builder.toString();
    }


}
