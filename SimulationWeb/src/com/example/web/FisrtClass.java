package com.example.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import jdk.nashorn.api.scripting.JSObject;



public  class FisrtClass {
	
	
	
	
	
	
	public JSONObject execute(int x, int y, int z) {
		
		int NodeBSize=x;
		int RNCSize=y;
		int SGSNSize=z;
		boolean validation;
		boolean validation2;
		int cost_curr;
		int cost_new;
		int temp=NodeBSize;
		double tmp1;
		JSONObject cost;
		JSONArray list_cost;
		JSONObject cost_res;
		int t;
		double p;
		int dekto=0;
		JSObject ret;
		System.out.println("hello World");
		int exp;
		int i;
		int j;
		list_cost=new JSONArray();
		cost_res= new JSONObject();
		ArrayList<NodeB> NodeBList = new ArrayList<NodeB>();
		ArrayList<Rnc> RncList = new ArrayList<Rnc>();
		ArrayList<Sgsn> SgsnList = new ArrayList<Sgsn>();
		for (i=0; i<NodeBSize; i++) {
			NodeBList.add(i,new NodeB(20000,120));
		}
		
		for (i=0; i<RNCSize; i++) {
			RncList.add(i,new Rnc(50000,2000));
		}
		for (i=0; i<SGSNSize; i++) {
			SgsnList.add(i,new Sgsn(40000,20000));
		}
		Solution sol;
		Solution tmp=null;
		Solution current=null;
		Solution candi;
		sol= new Solution(NodeBList.size(),RncList.size(),SgsnList.size(),NodeBList,RncList,SgsnList);
		sol.setRandomSolution();
		validation=sol.validate_NodeB_Rnc();
		while (!validation) {
			for (i=0; i<RncList.size(); i++) {
				RncList.get(i).set_connections(0);
			}
			System.out.println("NEw Solution");
			sol= new Solution(NodeBList.size(),RncList.size(),SgsnList.size(),NodeBList,RncList,SgsnList);
			
			sol.setRandomSolution();
			validation=sol.validate_NodeB_Rnc();
			
		}
	
		sol.setSGSNConnections();
		validation2=sol.validate_Sgsn_Connections();
		while (!validation2) {
			for (i=0; i<SgsnList.size(); i++) {
				SgsnList.get(i).set_connections(0);
			}
			System.out.println("NEw SGSN Solution");
			sol.setSGSNConnections();
			validation2=sol.validate_Sgsn_Connections();
			
			//sol.setRandomSolution();
			
			
		}
		try {
			current= (Solution) sol.clone();
			} catch (CloneNotSupportedException e1) {
			// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		cost_curr=current.calculate_cost();
		dekto=cost_curr;
		while (temp>0) {
			tmp1=(double)temp*1500;
			for (int equil=0; equil<50; equil++) {
				
				//cost_curr=current.calculate_cost();
				cost_curr=dekto;
				try {
					
					sol=(Solution) current.clone();
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				sol.neighbor();
			
				validation=sol.validate_NodeB_Rnc();
		
				while (!validation) {
					for (i=0; i<RncList.size(); i++) {
						RncList.get(i).set_connections(0);
					}
					sol.neighbor();
					validation=sol.validate_NodeB_Rnc();
				}
				sol.setSGSNConnections2();
				for (i=0; i<SgsnList.size(); i++) {
					SgsnList.get(i).set_connections(0);
				}
				validation2=sol.validate_Sgsn_Connections();
				while (!validation2) {
					for (i=0; i<SgsnList.size(); i++) {
						SgsnList.get(i).set_connections(0);
					}
					System.out.println("NEw SGSN Solution");
					sol.setSGSNConnections2();
					validation2=sol.validate_Sgsn_Connections();
			
			//sol.setRandomSolution();
			
			
				}
		//if (current==sol) {
				
				cost_new=sol.calculate_cost();
				
				System.out.println(cost_new+" "+ cost_curr);
				System.out.println("Currnet "+ dekto);
				if (cost_new<dekto) {
					
					System.out.println("liiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
					
					dekto=cost_new;
					try {
						
						current= (Solution) sol.clone();
						} catch (CloneNotSupportedException e1) {
						// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}
				else {
					System.out.println("laaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
					
				exp= -cost_new+dekto;
				
				p=Math.exp((double)divide(exp,tmp1));
				
				double start = 0;
				double end = 1;
				double random = new Random().nextDouble();
				double r = start + (random * (end - start));
				System.out.println(r);
				float x1=Float.parseFloat(String.valueOf(exp));
				float x2=Float.parseFloat(String.valueOf(temp));
				System.out.println(p);
				
				if (r<p) {
					System.out.println("Mpaineeeiiiiiii");
					
					//cost_curr=sol.calculate_cost();
					dekto=cost_new;
					}
				else {
					System.out.println("Elseeee");
					dekto=dekto;
					
				}
				}
				System.out.println("Current Solution= "+dekto);
				cost=new JSONObject();
				cost.put("cost", dekto);
				list_cost.add(cost.clone());
				
			
		//}
		
		
			}
			temp--;
		}
		System.out.println("Cost is "+ dekto);
		
		return current.nodes(list_cost);
	}
	 public static double divide(int num, double denom) {
		 String x=String.valueOf(num)+".0";
		 String y1=String.valueOf(denom)+".0";
		 
		 System.out.println("num/denom"+num+" "+denom+" "+(double)num / denom);
	        return (((double)num / denom));
	        
	    }

}
