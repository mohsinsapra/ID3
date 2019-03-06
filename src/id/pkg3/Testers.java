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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mahmood
 */
public class Testers {
  public static void main(String args[]) 
       {
       
      
Model m=new Model(); 
 m.Display();

     


     
       }
  
    
   static   class Model{
           
           List<String>Temperature;
           List<String> Outlook;
           List<String> Humidity;
           List<String> Windy;
           List<String> PlayGolf;
           Map<String, Integer> ValueFrequency;
           double result_entropy;
           Model() 
           {
               Temperature=new ArrayList<String>();
               Outlook=new ArrayList<String>();
               Humidity=new ArrayList<String>();
               Windy=new ArrayList<String>();
               PlayGolf=new ArrayList<String>();
               ValueFrequency = new HashMap<>();
               
           }
         double entropy(int class1,double total)
    {
               Double f1= (class1/total)*(Math.log10(class1/total)/Math.log10(2));
//   Double f2= (class2/total)*(Math.log10(class2/total)/Math.log10(2));
   
return (f1);
    
    }
         
         void List_Entropy(List<String> entropy_list)
                 {
                     
             Map<String,Integer> Frequenci = Frequency(entropy_list);
             List  list=new ArrayList(ValueFrequency.keySet());
Map<String ,Integer>count;

             List  lists=new ArrayList(Frequenci.keySet());

            for(int k=0;k<list.size();k++){
                count=new HashMap<>();
                System.out.println("");
             for (int j = 0; j < lists.size(); j++) {
                 
             
             for (int i = 0; i < PlayGolf.size(); i++) {
                   if(entropy_list.get(i).equalsIgnoreCase((String) lists.get(j))&&PlayGolf.get(i).equalsIgnoreCase((String) list.get(k)))
                   { 
//                       System.out.println(entropy_list.get(i));
                       if (count.get(lists.get(j))==null) {
                           count.put(entropy_list.get(i),1);
                           
                       }
                       else{
                           count.put(entropy_list.get(i), count.get(lists.get(j))+1);
                       }
                   }
                   
             }
             
             }
             count.put(list.get(k).toString(), 0);
             System.out.println(count);
            }
            
         }
       void List_Entropy_Result(List<String> lst )
       {            
           ValueFrequency=Frequency(lst);

            List keys=new ArrayList(ValueFrequency.keySet());
            double entropy=0;
            for (int i = 0; i < keys.size(); i++) {
              entropy+=  entropy((int) ValueFrequency.get(keys.get(i)), lst.size());
                System.out.println(keys.get(i)+" "+ValueFrequency.get(keys.get(i)));

            }
           
            result_entropy=-entropy;
            System.out.println("Entropy is "+-entropy);

       }
           void Display()
           {
                
        String csvFile = "weather.csv";
        String line = "";
        String cvsSplitBy = ",";


        try (BufferedReader br = new BufferedReader(new FileReader("weather.csv"))) {
br.readLine();
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
   

            System.out.println(Frequency(Temperature));
            System.out.println(Frequency(Outlook));
            System.out.println(Frequency(Humidity));
            
            List_Entropy(Outlook);
            List_Entropy(Humidity);
            List_Entropy(Windy);

        } catch (IOException e) {
            e.printStackTrace();
        }
           }
          Map Frequency(List<String> e)
          {
              
               Map<String, Integer> ValueFrequency= new HashMap<>();
                  for (int i = 0; i < e.size(); i++) {
                     if (ValueFrequency.get(e.get(i))==null) {
                                    ValueFrequency.put(e.get(i), 1);

                }
                else{
                                    ValueFrequency.put(e.get(i), ValueFrequency.get(e.get(i))+1);

                
                     }
                
                 
            }
          return ValueFrequency;
          }
      }


}
