/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.pkg3;

import static id.pkg3.console_outs.entropy;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mahmood
 */
public class Testers {

    public static void main(String args[]) {

        Model m = new Model();
        m.Display();

    }

    static class Model {

        List<String> Temperature;
        List<String> Outlook;
        List<String> Humidity;
        List<String> Windy;
        List<String> PlayGolf;
        Map<String, Integer> ValueFrequency;
        double result_entropy;

        Model() {
            Temperature = new ArrayList<String>();
            Outlook = new ArrayList<String>();
            Humidity = new ArrayList<String>();
            Windy = new ArrayList<String>();
            PlayGolf = new ArrayList<String>();
            ValueFrequency = new HashMap<>();

        }

        double entropy(int class1, double total) {
            Double f1 = (class1 / total) * (Math.log10(class1 / total) / Math.log10(2));
//   Double f2= (class2/total)*(Math.log10(class2/total)/Math.log10(2));

            return (f1);

        }

        double entropy(int class1, int class2, double total) {
            Double f1 = (class1 / total) * (Math.log10(class1 / total) / Math.log10(2));
            Double f2 = (class2 / total) * (Math.log10(class2 / total) / Math.log10(2));

            return (-(f1 + f2));

        }

        Map<String,Double> List_Entropy_IG(List<String> entropy_list) {
            Map<String,Double> entropy=new HashMap<>();
            List<String> distinct_yesNo = new ArrayList(ValueFrequency.keySet());

            List<String> distinct_entropylist = new ArrayList(Frequency(entropy_list).keySet());

            Map<String, Integer> ValueCounter = new HashMap<>();
            int counter = 0;
            int debug = 0;
            double calculatedEntropy = 0;
            double singleEntropy = 0;
//            System.out.println(entropy_list);
//            System.out.println(PlayGolf);
//            System.out.println(distinct_yesNo);
//            System.out.println(distinct_entropylist);
//            System.out.println(distinct_entropylist);
            for (int j = 0; j < distinct_entropylist.size(); j++) {
                singleEntropy = 0;
                ValueCounter = new HashMap<>();
                counter = 0;
                debug = distinct_yesNo.size();
                for (int k = 0; k < debug; k++) {

                    for (int i = 0; i < PlayGolf.size(); i++) {

                        if (entropy_list.get(i).equalsIgnoreCase(distinct_entropylist.get(j))
                                && PlayGolf.get(i).equalsIgnoreCase(distinct_yesNo.get(k))) {
//                    System.out.println(entropy_list.get(i));

                            if (ValueCounter.get(distinct_yesNo.get(k)) == null) {

                                ValueCounter.put(distinct_yesNo.get(k), 1);
                            } else {
                                ValueCounter.put(distinct_yesNo.get(k), ValueCounter.get(distinct_yesNo.get(k)) + 1);

                            }

                        }
                    }
//                    System.out.println(ValueCounter);
                    if (ValueCounter.get(distinct_yesNo.get(k)) == null) {
                        ValueCounter.put(distinct_yesNo.get(k), 0);

                    } else {
                        counter += ValueCounter.get(distinct_yesNo.get(k));
                    }

                }
                for (int i = 0; i < distinct_yesNo.size(); i++) {

                    if (ValueCounter.get(distinct_yesNo.get(i)) != 0) {

                        singleEntropy += entropy(ValueCounter.get(distinct_yesNo.get(i)), counter);
                    } else {
                        singleEntropy = -singleEntropy;
                    }
                    entropy.put(distinct_entropylist.get(j)+distinct_yesNo.get(i),  -singleEntropy);
                }
                singleEntropy = -((double) counter / PlayGolf.size()) * singleEntropy;
                calculatedEntropy += singleEntropy;
//                System.out.println("===============================");
//
//                System.out.println("Entropy of " + distinct_entropylist.get(j) + " (" + singleEntropy + ")");

            }
          ////////////////////Information Gain is Printing///////////////
//            System.out.println("");
//            System.out.println("Information Gain is (" + (result_entropy - calculatedEntropy) + ")");
//            System.out.println("");
            /////////////////////////////
            entropy.put("IG", result_entropy-calculatedEntropy);
            return (entropy);
        }

        void List_Entropy_Result(List<String> lst) {
            ValueFrequency = Frequency(lst);

            List keys = new ArrayList(ValueFrequency.keySet());
            double entropy = 0;
            for (int i = 0; i < keys.size(); i++) {
                entropy += entropy((int) ValueFrequency.get(keys.get(i)), lst.size());
                System.out.println(keys.get(i) + " " + ValueFrequency.get(keys.get(i)));

            }

            result_entropy = -entropy;
show("Play Golf");
            System.out.println("Entropy is " + -entropy);

        }

        void Display() {

            String csvFile = "weather.csv";
            String line = "";
            String cvsSplitBy = ",";

            try (BufferedReader br = new BufferedReader(new FileReader("weather.csv"))) {

                line = br.readLine();
                String[] columnNames = line.split(cvsSplitBy);

                while ((line = br.readLine()) != null) {

                    // use comma as separator
                    String[] weather = line.split(cvsSplitBy);
                    this.Temperature.add(weather[1]);
                    this.Outlook.add(weather[2]);
                    this.Humidity.add(weather[3]);
                    this.Windy.add(weather[4]);
                    this.PlayGolf.add(weather[5]);

                }

                System.out.println(Temperature);
                System.out.println(Outlook);
                System.out.println(Humidity);
                System.out.println(Windy);
                System.out.println(PlayGolf);

                List_Entropy_Result(PlayGolf);
show(" ");
                System.out.println(Frequency(Temperature));
                System.out.println(Frequency(Outlook));
                System.out.println(Frequency(Humidity));

                List<Double> IG=new ArrayList<>();
                ////////////////////////Temperature////////////////////
//                show( columnNames[1]);
//                System.out.println("IG  is"+  List_Entropy_IG(Temperature));
                ///////////////////////////////////////////////
double temp=List_Entropy_IG(Temperature).get("IG"),wind=List_Entropy_IG(Windy).get("IG"),humidity=List_Entropy_IG(Humidity).get("IG"),outlook=List_Entropy_IG(Outlook).get("IG");
                IG.add(temp);
                ////////////////////////OUtLook////////////////////
//                show(columnNames[2]);
//                      System.out.println( List_Entropy_IG(Outlook));
                ///////////////////////////////////////////////
                IG.add(outlook);

                ////////////////////////Humidity////////////////////
//                show(columnNames[3]);
//                       System.out.println(List_Entropy_IG(Humidity));
                ///////////////////////////////////////////////
                IG.add(humidity);

//                show( columnNames[4]);
//                       System.out.println(wind);
                ///////////////////////////////////////////////
                IG.add(wind);
                
                
             
show(" ");

                    if (Collections.max(IG)==wind) {
                        System.out.println("Wind is Root Node");  
                    } else
                        if (Collections.max(IG)==humidity) {
                                                System.out.println("Humidity is Root Node");  

                                                
                    } else
                        if (Collections.max(IG)==outlook) {
                                                System.out.println("Outlook is Root Node");  

                                                System.out.println("\t\t( OUTLOOK)");
                                               
                                                System.out.println(Frequency(Outlook));
                    } else
                        if (Collections.max(IG)==temp) {
                                                System.out.println("Temperature is Root Node");  

                    }
                    
                       System.out.println("Information Gain is "+Collections.max(IG));
                    
                       
              
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        

        void show(String xr) {
            System.out.println("-------------------------------------------");
            System.out.println(" \t\t "+xr);
            System.out.println("-------------------------------------------");

        }

        Map Frequency(List<String> e) {

            Map<String, Integer> ValueFrequency = new HashMap<>();
            for (int i = 0; i < e.size(); i++) {
                if (ValueFrequency.get(e.get(i)) == null) {
                    ValueFrequency.put(e.get(i), 1);

                } else {
                    ValueFrequency.put(e.get(i), ValueFrequency.get(e.get(i)) + 1);

                }

            }
            return ValueFrequency;
        }
    }

}
