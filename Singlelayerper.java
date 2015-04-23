//package Perceptron;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Singlelayerper {
	
	
	public static double calculate(ArrayList<String> attr[],ArrayList<Double> weights[], int j){
		
		double sum=0;
		
		for(int i=0;i<attr.length-1;i++){
			
			double temp=Double.parseDouble(attr[i].get(j));
			
			double temp1=weights[i].get(0);
			
			sum=sum+temp*temp1;
		}
	
		
		return sum;
		
		
	}
	
	
	public static double sigmoid(double summation){
		
		
		double z= Math.exp(-summation);
		
		double value= 1/(1+z);
		
		
		return value;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		if(args.length != 4)
		{
			System.out.println("You should give exactly 4 argumetns as input which are names of training data and test data files");
			System.exit(1);
			
		}
		System.out.println("Programm in Execution");
		String trainingData = args[0];
		String testData = args[1];
		String learningrate=args[2];
		String iterate=args[3];
		
		
		
	    double eta= Double.parseDouble(learningrate);
	    
	    int iterations=Integer.parseInt(iterate);
	
	    
		
		BufferedReader in =
				new BufferedReader(new FileReader(trainingData ), 4096 /* buffsize */ );
		
		      
				String aLine=in.readLine() ;
				
				String [] columns=aLine.split("\\s+");
				
				LinkedList<String> attrnames=new LinkedList<String>();
				for(int j=0;j<columns.length;j=j+1){
					attrnames.add(columns[j]);
				}
				
				Iterator itr=attrnames.iterator();
				
				/*while(itr.hasNext()){
					System.out.println(itr.next());
				}*/
				
				 ArrayList<String> attr[]= new ArrayList[columns.length+1]; 
				 
				 
				 for(int i=0;i<columns.length+1;i++){
						attr[i]=new ArrayList();
					}
				
				while ((aLine=in.readLine())!=null){
					
					
					 columns=aLine.split("\\s+");
					
					for(int i=0;i<columns.length;i++){
						
						attr[i].add(columns[i]);
					}
					
					}
				
			/*	itr=attr[3].iterator();
				
				while(itr.hasNext()){
					System.out.println(itr.next());
				}*/
				
				
				ArrayList<Double> weights[]=new ArrayList[columns.length-1];
				
				for(int i=0;i<columns.length-1;i++){
					weights[i]=new ArrayList();
				}
				
				for(int i=0;i<columns.length-1;i++){
					weights[i].add( (double) 0);
				}
				
				
				while(iterations!=0){
				//initialize delta wi to zero
					
					double deltawgt=0;
					for(int j=0;j<attr[0].size();j++){
						
						double summation=calculate(attr,weights,j);
						
						//System.out.println("summation is"+ summation);
						
						double output=sigmoid(summation);
						
						//System.out.println("sigmoid output is"+ output);
						
						for(int k=0;k<weights.length;k++){
							
							double t=Double.parseDouble(attr[attr.length-1].get(j));
							
							double x= Double.parseDouble(attr[k].get(j));
							
							deltawgt=deltawgt+ (eta* (t-output)*output*(1-output)*x);
							
							//System.out.println("deltaweight is"+ deltawgt);
							
							for(int p=k;p<=k;p++){
								
							double newvalue=	weights[k].get(0)+deltawgt;
							
							//System.out.println("newvalue is"+newvalue);
							
							weights[k].clear();
							
							weights[k].add(newvalue);
							
							deltawgt=0;
							}
						}
					}
					
					iterations--;
					
				}
				
				
				BufferedReader in1 =
						new BufferedReader(new FileReader( testData), 4096 /* buffsize */ );
				
				        
						String aLine1=in1.readLine() ;
						
						String [] columns1=aLine1.split("\\s+");
						
						LinkedList<String> attrnames1=new LinkedList<String>();
						for(int j=0;j<columns1.length;j=j+2){
							attrnames1.add(columns1[j]);
						}
						
				
				
						 ArrayList<String> attr1[]= new ArrayList[columns1.length+1]; 
						 
						 
						 for(int i=0;i<columns1.length+1;i++){
								attr1[i]=new ArrayList();
							}
						
						while ((aLine1=in1.readLine())!=null){
							
							
							 columns=aLine1.split("\\s+");
							
							for(int i=0;i<columns.length;i++){
								
								attr1[i].add(columns[i]);
							}
							
							}
						//System.out.println(attr1[0].size());
						double count=0;
						
						for(int m=0;m<attr1[0].size();m++){
							
							double newsum=calculate(attr1, weights,m);
							
							double newoutput=sigmoid(newsum);
							
							System.out.println("newoutput is "+ newoutput);
							
							if(newoutput>=0.5){
								
								if(attr1[attr1.length-1].get(m).equals("1")){
									
									count++;
								}
							}
							
							if(newoutput<0.5){
								
								if(attr1[attr1.length-1].get(m).equals("0")){
									count++;
									
								}
							}
							
							
						}
						
						double accuracy=(count/attr1[0].size())*100;
				
						
						System.out.println("Accuracy of Single Layer perceptron on Test data is"+ accuracy+"%");
						
		
	}

}
