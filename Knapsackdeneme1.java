/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*For better understanding of Knapsack with branch and bound please check these videos patiently.
    1- https://www.youtube.com/watch?v=slayHO7gKEQ
    2- https://www.youtube.com/watch?v=R6BQ3gBrfjQ

*/
package knapsackdeneme1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 *
 * @author rana yilmaz
 */
class Item{
  double weight;
  int value;
  
  public Item(){
      this.weight=-1;
      this.value=-1;
  }
  public Item(double weight,int value){
    this.weight=weight;
    this.value=value;
  }
  public String toString(){
      double ratio = (double)value / weight;
      return "weight: " + weight+" value: "+value+" ratio: "+ratio;
  }

}
class Node{
  double weight;
  int level,profit,bound;
  
  
  public Node(){
      this.weight=-1;
      this.level=-1;
      this.profit=-1;
      this.bound=-1;
  }
  public Node(double weight,int level,int profit,int bound){
      this.weight=-1;
      this.level=-1;
      this.profit=-1;
      this.bound=-1;
  }
  public Node(Node v){
      this.weight=v.weight;
      this.level=v.level;
      this.profit=v.profit;
      this.bound=v.bound;
  }
  
  @Override
  public String toString(){
      return "weight: " + weight+" level: "+level+" profit: "+profit+"bound: "+bound;
  }
}
public class Knapsackdeneme1 {

    public static ArrayList<Item> sort (ArrayList<Item> arr){
      ArrayList<Item> arr2=new ArrayList<Item>();
      int size=arr.size();
      while(arr2.size()!=size){
        double max=-1;
        for(Item item:arr){
            
            
            double ratio = (double)item.value / item.weight;
            if(ratio>max){
              max=ratio;
            }             
        }
        for(Item item:arr){
            
            double ratio = (double)item.value / item.weight;
            if(ratio==max){
             arr2.add(item);
             arr.remove(item);
             break;
             
            }             
        }
      
      }
      return arr2;
    }
    public static int bound(Node u,int n,int W,ArrayList<Item> arr){
        //This function defines the upper bound for current node.
        if(u.weight >=W){
         return 0 ;
        
        }
        
        int profit_bound = u.profit; 
  
       
        int j = u.level + 1; 
        double totweight =  u.weight; 

        
        while ((j < n) && (totweight + arr.get(j).weight <= W)) 
        { 
            totweight    += arr.get(j).weight; 
            profit_bound += arr.get(j).value; 
            j++; 
        } 

        
        if (j < n
                ) 
            profit_bound += (W - totweight) * (double)((double)arr.get(j).value /arr.get(j).weight); 

        return profit_bound; 
        
        
        
    }
    
    public static int knapsack(int W,ArrayList<Item> arr,int n){
        arr=sort(arr);
        
        Queue<Node> Q = new LinkedList<>();
        Node u=new Node();
        Node v=new Node();
        
        u.level=-1;u.profit=0;u.weight=0;
         Q.add(u);
        int maxProfit=0;
        while(!Q.isEmpty()){
            u=Q.poll();
            
            if(u.level==-1){
                v.level=0;
            }
            if (u.level == n-1){ 
            continue; 
            }
            v.level = u.level + 1;
            v.weight = u.weight + arr.get(v.level).weight; 
            v.profit = u.profit + arr.get(v.level).value;
            
            if (v.weight <= W && v.profit > maxProfit){
               maxProfit = v.profit; 
            } 
              
            
            v.bound = bound(v, n, W, arr);  
        
            if (v.bound > maxProfit){
                Node x=new Node(v);
                
                Q.add(x);
            }            
            if (v.bound ==0){
                continue;
            }
            v.weight = u.weight; 
            v.profit = u.profit; 
            v.bound = bound(v, n, W, arr); 
            if (v.bound > maxProfit){
                Node x=new Node(v);
                
                Q.add(x);
            } 
        }
        
        return maxProfit;
    }
    
    public static void main(String[] args) {
        int W=10;
        ArrayList<Item> arr = new ArrayList<Item>();
        Item item1=new Item(2, 40);
        Item item2=new Item(3.14, 50);
        Item item3=new Item(1.98, 100);
        Item item4=new Item(0.5, 95);
        Item item5=new Item(3, 30);
        arr.add(item1);
        arr.add(item2);
        arr.add(item3);
        arr.add(item4);
        arr.add(item5);
        int n=arr.size();
        arr=sort(arr);
        for(Item i:arr){
         System.out.println(i);
        
        }
        System.out.println(knapsack(W,arr,n));    
              
        
        
    }
    
}
