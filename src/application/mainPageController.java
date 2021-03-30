package application;


import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.util.Duration;

public class mainPageController implements Initializable {

    @FXML private ComboBox<String> hComb;
    @FXML private ComboBox<String> mComb;
    @FXML private ComboBox<String> sComb;
    @FXML private Label lab1;
    //	@FXML private Label title;
    @FXML private Label countDwn;
    //	@FXML private Button exebtn, cancel, shwabt;
    @FXML private ComboBox<String> DecisionComb;
    @FXML private ProgressBar prgb;

    ObservableList<String> hList = FXCollections.observableArrayList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
    ObservableList<String> mList = FXCollections.observableArrayList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25" , "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59");
    ObservableList<String> DecisionList = FXCollections.observableArrayList("LOCK", "HIBERNATE","SHUTDOWN","RESTART","BLANK-SCREEN","SLEEP","SIGNOUT");

    private int x,y,z, s1,s2,s3, totalSec;
    public Timeline timeline, timeline2;
    int destNo;
    public int tempNo;


    //method to display date and time at the top of the stage.
    public void displayTime(){
        DateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd   HH:mm:ss");
        Date date = new Date();
        String sss = dateformat.format(date);
        lab1.setText(sss);
    }
    double s, t;
    @FXML
    void pressed(MouseEvent event) {
        s = event.getSceneX();
        t = event.getSceneY();
    }

    @FXML
    void dragged(MouseEvent event) {

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() - s);
        stage.setY(event.getScreenY() - t);
    }
    @FXML
    void min(MouseEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }


    @FXML
    void close(MouseEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }


    @FXML
    private HBox hbox1;

    @FXML
    void entered(MouseEvent event) {
        hbox1.setCursor(Cursor.HAND);
    }

    @FXML
    void exited(MouseEvent event) {

    }

    //method to initialize all values and fields.
    public void initMthd(){
        hComb.setItems(hList);
        mComb.setItems(mList);
        sComb.setItems(mList);
        prgb.setProgress(00);
        prgb.setStyle("-fx-accent: #4169e1");


        hComb.setValue("");
        mComb.setValue("");
        sComb.setValue("");
        DecisionComb.setValue("HIBERNATE");
        DecisionComb.setItems(DecisionList);
        try{
            //stop timeline if it exists.
            timeline.stop();
            prgb.setProgress(0);
            return;
        }catch(Exception ee){
            System.out.println(ee.getMessage());
        }


    }


    //method to close window.
    public void clswin(){
        System.exit(0);
    }


    //method to stop count down.
    public void breakFlow(){
        //method to stop count down.
        timeline.stop();
        prgb.setProgress(0);
        prgb.setStyle("-fx-accent: #4169e1");

        return;
    }

    //method to show about app button.
    public void showAbout(){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText("About this app...");
        alert.setContentText("This app was developed by Ilerioluwa Femi-Ola. \nContact: 07065368634"
                + " Email: femiolailerioluwa@gmail.com");
        alert.showAndWait();
        DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String sss = dateformat.format(date);

        Period period = Period.between(LocalDate.parse("2020-01-12"), LocalDate.parse(sss));
        ChronoUnit.DAYS.between(LocalDate.parse("2020-03-11"),LocalDate.now());
        System.out.println(ChronoUnit.DAYS.between(LocalDate.parse("2020-03-10"),LocalDate.now()));
//		System.out.println(sss);
    }

    public void executeBtn(){
        try {
            timeline.stop();
            prgb.setProgress(0);
            prgb.setStyle("-fx-accent: #4169e1");

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());

        }

        try{
            if (hComb.getValue().isEmpty()){
                x= 0;
            }else{
                x = Integer.parseInt(hComb.getValue());
            }
            if (mComb.getValue().isEmpty()){
                y= 0;
            }else{
                y = Integer.parseInt(mComb.getValue());
            }
            if (sComb.getValue().isEmpty()){
                z= 0;
            }else{
                z = Integer.parseInt(sComb.getValue());
            }

            if (x==0 && y == 0 && z==0){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("ERROR DIALOG");
                prgb.setProgress(0);
                prgb.setStyle("-fx-accent: #4169e1");
                alert.setHeaderText("Input Error");
                alert.setContentText("Invalid input for Hour(s)/Minute(s)/Second(s)");
                alert.showAndWait();
                return;
            }
        }catch(Exception ee){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERROR DIALOG");
            alert.setHeaderText("Input Error");
            prgb.setProgress(0);
            prgb.setStyle("-fx-accent: #4169e1");
            alert.setContentText("Invalid input for Hour(s)/Minute(s)/Second(s)");
            alert.showAndWait();
            return;
        }

        try{
            s1=x*60*60; //total seconds in hour(s)
            s2=y*60; //total seconds in minutes(s)
            s3=z;  //total seconds in second(s)
        }catch(Exception ee1){

            return;
        }
        System.out.println(" ");

        totalSec = s1+s2+s3;
        destNo = s1+s2+s3;
        timeline = new Timeline(
                new KeyFrame(Duration.millis(1000),
                        ae ->
                                System.out.println(redTime(totalSec))));
        timeline.setCycleCount(totalSec);
        timeline.playFromStart();

        String command = DecisionComb.getValue();
        System.out.println(command);

    }

    //public int num;
    private int Xini = 0;
    private int redTime(int xys){
        //method to count the time down and return the value
        totalSec = xys-1;
        Xini = destNo-totalSec;
        double xtotal = Double.parseDouble(Integer.toString(destNo));
        double xXini = Double.parseDouble(Integer.toString(Xini));

        double pPoint = xXini/xtotal;
//		if (totalSec>0){
        prgb.setProgress(pPoint);
//		}
        if(totalSec>9){
            countDwn.setText(Integer.toString(totalSec));
        }
        if(totalSec<=9){
            int vvb = totalSec;
            countDwn.setText(String.format("%02d",vvb));
            prgb.setStyle("-fx-accent: #ff4500");
//			countDwn.setTextAlignment(TextAlignment.CENTER);

            Toolkit.getDefaultToolkit().beep();
        }

        if(totalSec==0){
//			prgb.setStyle("-fx-accent: default");
            executeCommand();
        }
//		if(totalSec<7){
////			c111.setStyle("-fx-fill: linear-gradient(#ff5400, #be1d00);");
//		}
        return totalSec;
    }

    private void executeCommand(){

        String comm = DecisionComb.getValue();

        switch (comm) {
            case "HIBERNATE":
                try {

                    Runtime rt = Runtime.getRuntime();
                    @SuppressWarnings("unused")
                    Process prc = rt.exec("shutdown -h");
                    //                Process child = Runtime.getRuntime().exec(shtdwn);
                } catch (IOException ex) {
                    Logger.getLogger(mainPageController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "\n Error Message: " +ex.getMessage());
                }

                break;
            case "SHUTDOWN":
                try {

                    Runtime rt = Runtime.getRuntime();
                    @SuppressWarnings("unused")
                    Process prc = rt.exec("shutdown -s");
                    //                Process child = Runtime.getRuntime().exec(shtdwn);
                } catch (IOException ex) {
                    Logger.getLogger(mainPageController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "\n Error Message: " +ex.getMessage());
                }
                break;
            case "RESTART":
                try {

                    Runtime rt = Runtime.getRuntime();
                    @SuppressWarnings("unused")
                    Process prc = rt.exec("shutdown -r");
                    //                Process child = Runtime.getRuntime().exec(shtdwn);
                } catch (IOException ex) {
                    Logger.getLogger(mainPageController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "\n Error Message: " +ex.getMessage());
                }

                break;
            case "SLEEP":
                try {

                    Runtime rt = Runtime.getRuntime();
                    @SuppressWarnings("unused")
                    Process prc = rt.exec("Rundll32.exe powrprof.dll, SetSuspendState Sleep");
                    //                Process child = Runtime.getRuntime().exec(shtdwn);
                } catch (IOException ex) {
                    Logger.getLogger(mainPageController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "\n Error Message: " +ex.getMessage());
                }
                break;
            case "BLANK-SCREEN":
                try {

                    Runtime rt = Runtime.getRuntime();
                    @SuppressWarnings("unused")
                    Process prc = rt.exec("C:\\Windows\\System32\\scrnsave.scr /s");
                    //                Process child = Runtime.getRuntime().exec(shtdwn);
                } catch (IOException ex) {
                    Logger.getLogger(mainPageController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "\n Error Message: " +ex.getMessage());
                }
                break;
            case "SIGNOUT":
                try {

                    Runtime rt = Runtime.getRuntime();
                    @SuppressWarnings("unused")
                    Process prc = rt.exec("shutdown -l");
                    //                Process child = Runtime.getRuntime().exec(shtdwn);
                } catch (IOException ex) {
                    Logger.getLogger(mainPageController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "\n Error Message: " +ex.getMessage());
                }
                break;
            case "LOCK":
                try {

                    Runtime rt = Runtime.getRuntime();
                    @SuppressWarnings("unused")
                    Process prc = rt.exec("C:\\Windows\\System32\\rundll32.exe user32.dll,LockWorkStation");
                    //                Process child = Runtime.getRuntime().exec(shtdwn);
                } catch (IOException ex) {
                    Logger.getLogger(mainPageController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "\n Error Message: " +ex.getMessage());
                }
                break;


            default:
                break;
        }
    }



    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub

        hComb.setItems(hList);
        mComb.setItems(mList);
        sComb.setItems(mList);

        hComb.setValue("");
        mComb.setValue("");
        sComb.setValue("");
        DecisionComb.setValue("HIBERNATE");

        DecisionComb.setItems(DecisionList);

        DateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String sss = dateformat.format(date);
        lab1.setText(sss);
        prgb.setProgress(0);
        prgb.setStyle("-fx-accent: #4169e1");

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(1),ae -> displayTime()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }

}
