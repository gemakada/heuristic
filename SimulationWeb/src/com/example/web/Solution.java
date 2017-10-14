package com.example.web;

import java.util.ArrayList;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Solution implements Cloneable {
	
	public int configuration1[][];
	public int NodeBs;
	public int Rncs;
	public int SGSNs;
	public int configuration2[][];
	public int cost;
	public int time;
	public ArrayList<NodeB> NodeBList;
	public ArrayList<Rnc> RncList;
	public ArrayList<Sgsn> SgsnList;
	public int sgsn_nei;
	
	
	
	public Solution(int x1, int y1, int z1, ArrayList<NodeB> NodeBList, ArrayList<Rnc> RncList, ArrayList<Sgsn> SgsnList) {
		
		this.NodeBs=x1;
		this.Rncs=y1;
		this.SGSNs=z1;
		
		this.configuration1=new int[x1][y1];
		this.configuration2=new int[y1][z1];
		this.cost=0;
		
		this.NodeBList=NodeBList;
		this.RncList=RncList;
		this.SgsnList=SgsnList;
		
	}
	

	
	
	
	
	
	protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
	
	
	public void setRandomSolution() {
		int i;
		int j;
		int thesi;
		Random rand = new Random();
		int randomNum = rand.nextInt((1 - 0) + 1) + 0;
		int number;
		int position = 0;
		for (i=0; i<NodeBs; i++) {
			//number=0;
			//loop:
			//for (j=0; j<Rncs; j++) {
				position= 0 + rand.nextInt((Rncs-1 - 0) + 1);
				this.configuration1[i][position]=1;
				for (j=0; j<Rncs; j++) {
					if (j!=position) {
						this.configuration1[i][position]=1;
						
					}
					
				}
				
				/*this.configuration1[i][j]=rand.nextInt((1 - 0) + 1) + 0;
				if (this.configuration1[i][j]==1) {
					number=number+1;
					for (int k=j+1; k<this.Rncs; k++) {
						this.configuration1[i][k]=0;
					}
				
					break loop;
				}
				*/
			//}
			
		
			
			
		}
		
		for (i=0; i<NodeBs; i++) {
			for (j=0; j<Rncs; j++) {
				System.out.print(" "+configuration1[i][j] );
			}
			System.out.println("\n" );
		}
	}
	
	public boolean validate_NodeB_Rnc() {
		int i;
		int j;
		int sum;
		Rnc node;
		for (j=0; j<Rncs; j++) {
			sum=0;
			for (i=0; i<NodeBs; i++) {
				sum=sum+configuration1[i][j];
				
				
			//	System.out.print(" "+configuration1[i][j] );
			}
			//System.out.println("\n" );
			if (sum>10) {
				return false;
				
			}
			else {
				node=RncList.get(j);
				node.set_connections(sum);
			}
			
		}
		
		
		
		
		
		return true;
		
	}
	
	public void setSGSNConnections() {
		
		int i;
		int j;
		int position1;
		int position2;
		Random rand = new Random();
		for (i=0; i<Rncs; i++) {
			if (RncList.get(i).get_connections()==0) {
				for (j=0; j<SGSNs; j++) {
					configuration2[i][j]=0;
				}
			}
			else {
				position1= 0 + rand.nextInt((SGSNs-1 - 0) + 1);
				position2=0+rand.nextInt((SGSNs-1 - 0) + 1);
				while(position1==position2) {
					position2=0+rand.nextInt((SGSNs-1 - 0) + 1);
				}
				this.configuration2[i][position1]=1;
				this.configuration2[i][position2]=1;
				for (j=0; j<SGSNs; j++) {
					if ((j!=position1)&&(j!=position2)) {
					configuration2[i][j]=0;
					}
				}
			}
				
				
			}
			
			
		
		System.out.println("SGSN" );
		for (i=0; i<Rncs; i++) {
			for (j=0; j<SGSNs; j++) {
				System.out.print(" "+configuration2[i][j] );
			}
			System.out.println("\n" );
		}
		
	}
	
	public boolean validate_Sgsn_Connections() {
		int i;
		int j;
		int sum;
		Sgsn node;
		for (j=0; j<SGSNs; j++) {
			sum=0;
			for (i=0; i<Rncs; i++) {
				sum=sum+configuration2[i][j];
				
				
			//	System.out.print(" "+configuration1[i][j] );
			}
			//System.out.println("\n" );
			if (sum>16) {
				return false;
				
			}
			else {
				node=SgsnList.get(j);
				node.set_connections(sum);
			}
			
		}
		
		
		
		
		
		return true;
		
	}
	
	public void neighbor() {
		int position;
		int position1;
		int k;
		int i;
		int j;
		Random rand=new Random();
		position= 0 + rand.nextInt((NodeBs-1 - 0) + 1);
		position1=0 + rand.nextInt((Rncs-1 - 0) + 1);
		for (j=0; j<Rncs; j++) {
			
			configuration1[position][j]=0;
		}
		configuration1[position][position1]=1;
		this.sgsn_nei=position1;
			for (j=0; j<Rncs; j++) {
				
				RncList.get(j).set_connections(0);
			}
		
		System.out.println("Neibor" +" "+position);
		for (i=0; i<NodeBs; i++) {
			for (j=0; j<Rncs; j++) {
				
				System.out.print(" "+configuration1[i][j] );
			}
			System.out.println("\n" );
		}
	}
	
	
public void setSGSNConnections2() {
		
		int i;
		int j;
		int position1;
		int position2;
		Random rand = new Random();
		for (i=0; i<Rncs; i++) {
			if (RncList.get(i).get_connections()==0) {
				for (j=0; j<SGSNs; j++) {
					configuration2[i][j]=0;
				}
			}
			if (i==this.sgsn_nei) {
				for (j=0; j<SGSNs; j++) {
					configuration2[i][j]=0;
				}
				position1= 0 + rand.nextInt((SGSNs-1 - 0) + 1);
				position2=0+rand.nextInt((SGSNs-1 - 0) + 1);
				/*while(position1==position2) {
					position2=0+rand.nextInt((SGSNs-1 - 0) + 1);
				} */
				this.configuration2[i][position1]=1;
				this.configuration2[i][position2]=1;
			/*	for (j=0; j<SGSNs; j++) {
					if ((j!=position1)&&(j!=position2)) {
					configuration2[i][j]=0;
					}
				} */
			}
				
				
			}
			
			
		
		System.out.println("SGSN to change line "+ sgsn_nei);
		for (i=0; i<Rncs; i++) {
			for (j=0; j<SGSNs; j++) {
				System.out.print(" "+configuration2[i][j] );
			}
			System.out.println("\n" );
		}
		
	}

	public int calculate_cost() {
		cost=0;
		
		for (int i=0; i<RncList.size(); i++) {
			if (RncList.get(i).get_connections()!=0) {
				cost=(cost+(RncList.get(i).get_connections())*1500+50000);
			}
			
			
		}
		for (int i=0; i<SgsnList.size(); i++) {
			if (SgsnList.get(i).get_connections()!=0) {
				cost=(cost+(SgsnList.get(i).get_connections())*4000+40000);
			}
			
			
		}
		
		
		return cost;
	}
	
	public JSONObject nodes(JSONArray cost) {
		JSONObject nodes = new JSONObject();
		JSONObject cable2 = new JSONObject();
		JSONObject edges = new JSONObject();
		JSONObject elements = new JSONObject();
		JSONObject elements2 = new JSONObject();
		JSONArray list = new JSONArray();
		JSONArray list2 = new JSONArray();
		for (int i=0; i<NodeBs; i++) {
			for (int j=0; j<Rncs; j++) {
				if (configuration1[i][j]==1) {
					JSONObject cable1 = new JSONObject();
					JSONObject data = new JSONObject();
					cable1.put("sourse","NodeB"+String.valueOf(i));
					cable1.put("target","Rnc"+String.valueOf(j));
					data.put("data", cable1.clone());
					list.add(data.clone());
					
					
					
				}
			}
		}
		
		for (int i=0; i<Rncs; i++) {
			for (int j=0; j<SGSNs; j++) {
				if (configuration2[i][j]==1) {
					JSONObject cable1 = new JSONObject();
					JSONObject data = new JSONObject();
					cable1.put("sourse","Rnc"+String.valueOf(i));
					cable1.put("target","Sgsn"+String.valueOf(j));
					data.put("data", cable1.clone());
					list.add(data.clone());
					
					
					
				}
			}
		}
		
		
		
		
		for (int i=0; i<NodeBs; i++) {
			JSONObject cable1 = new JSONObject();
			JSONObject data = new JSONObject();
			cable1.put("id","NodeB"+String.valueOf(i));
			cable1.put("type","NodeB");
			data.put("data", cable1.clone());
			
			list2.add(data.clone());
		}
		for (int i=0; i<Rncs; i++) {
			JSONObject cable1 = new JSONObject();
			JSONObject data = new JSONObject();
			cable1.put("id","Rnc"+String.valueOf(i));
			cable1.put("type","Rnc");
			data.put("data", cable1.clone());
			
			list2.add(data.clone());
		}
		for (int i=0; i<SGSNs; i++) {
			JSONObject cable1 = new JSONObject();
			JSONObject data = new JSONObject();
			cable1.put("id","Sgsn"+String.valueOf(i));
			cable1.put("type","Sgsn");
			data.put("data", cable1.clone());
			
			list2.add(data.clone());
		}
		
		elements.put("nodes", list2);
		elements.put("edges", list) ;
		elements2.put("elements", elements) ;
		elements2.put("cost", cost);
		return elements2; 
		
		
		
		
	}
	
	

}
