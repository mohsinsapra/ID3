/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.pkg3;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.lang.Math;
/**
 *
 * @author Mahmood
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TextField first;
    
    @FXML
    private TextField second;
    
    @FXML
    private TextField total;
    @FXML
    private TextField rows;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");

    entropy();}
    void entropy()
    {
          Double f1=  Double.valueOf(first.getText())/Double.valueOf(rows.getText());//*(Math.log(Double.valueOf(first.getText())/Math.log(2)));
          Double f2=  Double.valueOf(second.getText())/Double.valueOf(rows.getText());//*(Math.log(Double.valueOf(first.getText())/Math.log(2)));
                 Double f11= f1*(Math.log10(Double.valueOf(f1))/Math.log10(2));
   Double f22= f2*(Math.log10(Double.valueOf(f2))/Math.log10(2));
   Double f3=-(f11+f22);
          total.setText(f3.toString());
          System.out.println(f3);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    
    }    
    
}
