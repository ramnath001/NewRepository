//$Id: River_config.cpp,v 1.1 2012/05/09 23:26:04 p334-70d Exp p334-70d $
//$Log: River_config.cpp,v $
//Revision 1.14  2012/05/09 23:26:04  p334-70d
//Modified display()
//
//Revision 1.13  2012/04/27 00:32:25  p334-70d
//Added some more comments
//
//Revision 1.11  2012/04/24 21:50:06  p334-70d
//Final checks
//
//Revision 1.10  2012/04/24 21:47:27  p334-70d
//Added step numbers
//
//Revision 1.9  2012/04/24 19:07:58  p334-70d
//Added some more comments
//
//Revision 1.8  2012/04/24 19:06:00  p334-70d
//Added documentation comments
//
//Revision 1.7  2012/04/24 19:03:29  p334-70d
//Everything working normally
//
//Revision 1.6  2012/04/24 19:02:00  p334-70d
//Fixed indentation and resolved errors
//
//Revision 1.5  2012/04/24 19:01:21  p334-70d
//Added a few missing conditions
//
//Revision 1.4  2012/04/24 18:58:20  p334-70d
//Added conditions for 0 professors on LHS and RHS
//
//Revision 1.3  2012/04/24 18:37:09  p334-70d
//Implemented getmoves() for LHS
//
//Revision 1.2  2012/04/24 18:35:43  p334-70d
//Copied initial vector to individual vectors
//
//Revision 1.1  2012/04/23 01:35:18  p334-70d
//Initial revision
//
//Author: Ramnath Anantharaman
//Author: Aditya Jagtiani
//Group: p334-70d

#include<iostream>
#include<deque>
#include"River_config.h"
using namespace std;

//Constructor
River_config::River_config(int lpc , int lsc, int rpc , int rsc, vector<int> final)
:lprof_count(lpc), lst_count(lsc),rprof_count(rpc),rst_count(rsc) {
	
	initial.push_back(lprof_count);
	initial.push_back(lst_count);
	initial.push_back(rprof_count);
	initial.push_back(rst_count);
	initial.push_back(0);
	final_config = final;
}

//Method to return initial configuration
vector<int> River_config::getinitial_config() const{
	return initial;
}

//Method to return final configuration
vector<int> River_config::getfinal_config() const{
	return final_config;
}

//Method to generate moves from start to finish
vector<vector<int> > River_config:: get_moves(vector<int> v) {
	//Creating 5 vectors for the 5 possible boat moves
	vector<int> v1(5);
	vector<int> v2(5);
	vector<int> v3(5);
	vector<int> v4(5);
	vector<int> v5(5);
	vector< vector<int> > outer; //Creating a vector that contains a vector within
	
	for(int i=0;i<5;i++){ //Copying initial configuration to each of the five vectors
		v1[i]=v[i];
		v2[i]=v[i];
		v3[i]=v[i];
		v4[i]=v[i];
		v5[i]=v[i];		
	}
	
	//Conditions when the boat is on the LHS. v[4] = 0 when boat on LHS, 1 when on RHS
	if(v[4]==0){
		
		if((((v[0]-2) >= v[1]) || ((v[0]-2)==0)) && (((v[2]+2) >= v[3]) && ((v[0]-2) >=0))) {
			v1[0]=v1[0]-2;
			v1[2]=v1[2]+2;
			v1.erase(v1.begin()+4);
			v1.push_back(1);
			outer.push_back(v1);
			
		}
		
		//Professors = 0 on RHS, move = (0,2)
		if(v[2]==0){
			
			if(((v[0]) >= (v[1]-2)) && (v[2] <= (v[3]+2)) && ((v[1]-2) >=0)) {
				v2[1]=v2[1]-2;
				v2[3]=v2[3]+2;
				v2.erase(v2.begin()+4);
				v2.push_back(1);
				outer.push_back(v2);
			}
		}
		
		//Professors = 0 on LHS, move = (0,2)
		else if(v[0]==0){
			if(((v[0])<=(v[1]-2)) && ((v[2])>=(v[3]+2)) && ((v[1]-2)>=0)){
				v2[1]=v2[1]-2;
				v2[3]=v2[3]+2;
				v2.erase(v2.begin()+4);
				v2.push_back(1);
				outer.push_back(v2);
				
			}
		}

		// Professors = 0 on both LHS and RHS, move = (0,2)
		 if((v[0]== 0) && (v[2]== 0)){
			if((v[0]<=(v[1]-2)) && (v[2]<=(v[3]+2))){
				v2[1]=v2[1]-2;
				v2[3]=v2[3]+2;
				v2.erase(v2.begin()+4);
				v2.push_back(1);
				outer.push_back(v2);
			}
		}
		
		//Professors != 0 on RHS, move = (0,2)
		if(v[2]!=0) {
			if(((v[0]) >= (v[1]-2)) && (v[2] >= (v[3]+2)) && ((v[1]-2) >=0)) {
				
				v2[1]=v2[1]-2;
				v2[3]=v2[3]+2;
				v2.erase(v2.begin()+4);
				v2.push_back(1);
				outer.push_back(v2);
				
			}
		}
		
		//Professors = 0 on RHS, move = (0,1)
		if(v[2]==0){
			
			if((v[0] >= (v[1]-1)) && (v[2] <= (v[3]+1)) && ((v[1]-1) >=0)){
				v3[1]=v3[1]-1;
				v3[3]=v3[3]+1;
				v3.erase(v3.begin()+4);
				v3.push_back(1);
				outer.push_back(v3);
				
			} 
			
		}
		//Professors = 0 on LHS, move = (0,1)
		else if(v[0]==0){
			if((v[0]<=(v[1]-1)) && (v[2]>=(v[3]+1))){
				v3[1]=v3[1]-1;
				v3[3]=v3[3]+1;
				v3.erase(v3.begin()+4);
				v3.push_back(1);
				outer.push_back(v3);
				
			}
		}
		
		//Professors = 0 on both LHS and RHS, move = (0,1)
		 if((v[0]==0) && (v[2]==0)){
			if(((v[0])<=(v[1]-1)) && ((v[2])<=(v[3]+1))){
				v3[1]=v3[1]-1;
				v3[3]=v3[3]+1;
				v3.erase(v3.begin()+4);
				v3.push_back(1);
				outer.push_back(v3);
			}
		}
		
		//Professors !=0 on RHS, move = (0,1)
		else if((v[0] >= (v[1]-1)) && (v[2] >= (v[3]+1)) && ((v[1]-1) >= 0)) {
			v3[1]=v3[1]-1;
			v3[3]=v3[3]+1;
			v3.erase(v3.begin()+4);
			v3.push_back(1);
			outer.push_back(v3);
			
		} 
		
		//General move (1,0)
		if((((v[0]-1) >= v[1]) || ((v[0]-1)==0)) && ((v[2]+1) >=v[3]) && ((v[0]-1) >=0)) {
			v4[0]=v4[0]-1;
			v4[2]=v4[2]+1;
			v4.erase(v4.begin()+4);
			v4.push_back(1);
			outer.push_back(v4);
			
		}
				
		//General move (1,1)
		if(((v[0]-1) >=(v[1]-1)) && ((v[2]+1) >= (v[3]+1)) && ((v[0]-1) >=0) && ((v[1]-1)>=0)){
      			v5[0]=v5[0]-1;
			v5[1]=v5[1]-1;
			v5[2]=v5[2]+1;
			v5[3]=v5[3]+1;
			v5.erase(v5.begin()+4);
			v5.push_back(1);
			outer.push_back(v5);
			
		}
	}
	
	//Moves when the boat is on RHS, v[4] = 1
	else if(v[4]==1){
			
		//General move (2,0)
		if((((v[2]-2) >=v[3]) || ((v[2]-2)==0)) && (((v[0]+2) >=v[1]) && ((v[2]-2)>=0))){
			v1[2]=v1[2]-2;
			v1[0]=v2[0]+2;
			v1.erase(v1.begin()+4);
			v1.push_back(0);
			outer.push_back(v1);
		}
	}		
	
	//Professors = 0 on LHS, move = (0,2)
	if(v[0]==0) {
		if((v[2] >= (v[3]-2) || v[2]==0) && ((v[0] <= (v[1]+2)) && ((v[3]-2) >=0))) {
			v2[3]=v2[3]-2;
			v2[1]=v2[1]+2;
			v2.erase(v2.begin()+4);
			v2.push_back(0);
			outer.push_back(v2);
		}
	}
	 
	//Professors = 0 on RHS, move = (0,2)
	else if (v[2]==0){
		if((v[2]<=(v[3]-2)) && (v[0]>=(v[1]+2)||(v[0]==0)) && ((v[3]-2)>=0)) {                                                         
			v2[3]=v2[3]-2;
			v2[1]=v2[1]+2;
			v2.erase(v2.begin()+4);
			v2.push_back(0);
			outer.push_back(v2);
		}
	}

	//Professors != 0, move = (0,2)	
	else if(((v[2]) >= (v[3]-2)) && (v[0] >= (v[1]+2)) && ((v[3]-2) >= 0)) {
		v2[3]=v2[3]-2;
		v2[1]=v2[1]+2;
		v2.erase(v2.begin()+4);
		v2.push_back(0);
		outer.push_back(v2);
	}
	
	//Professors = 0 on LHS and RHS, move = (0,2)
	 if((v[0]== 0) &&(v[2]==0)){
		if((v[2]<=(v[3]-2)) && (v[0]<=(v[1]+2))){
			v2[3]=v2[3]-2;
			v2[1]=v2[1]+2;
			v2.erase(v2.begin()+4);
			v2.push_back(0);
			outer.push_back(v2);
		}
	}
	
	//Professors = 0 on LHS, move = (0,1)
	if(v[0]==0) {
		if((v[2] >= (v[3]-1)) && (v[0] <= (v[1]+1)) && ((v[3]-1) >= 0)) {
			v3[3]=v3[3]-1;
			v3[1]=v3[1]+1;
			v3.erase(v3.begin()+4);
			v3.push_back(0);
			outer.push_back(v3);
		} 
	}
	//Professors = 0 on RHS, move = (0,1)
	else if(v[2]==0){
		if ((v[2]<=(v[3]-1)) && (v[0]>=(v[1]+1)) && ((v[3]-1)>=0)){
			v3[3]=v3[3]-1;
			v3[1]=v3[1]+1;
			v3.erase(v3.begin()+4);
			v3.push_back(0);
			outer.push_back(v3);
		}		
	}
	
	//General Move (0,1)
	else if(((v[2]) >=(v[3]-1)) && (v[0] >=(v[1]+1)) && ((v[3]-1) >=0)){
		v3[3]=v3[3]-1;
		v3[1]=v3[1]+1;
		v3.erase(v3.begin()+4);
		v3.push_back(0);
		outer.push_back(v3);
	}
	
	//Professors = 0 on LHS and RHS, move = (0,1)
	 if((v[0]==0)&&(v[2]==0)){
		if((v[2]<=(v[3]-1)) && (v[0]<=(v[1]+1))){
			v3[3]=v3[3]-1;
			v3[1]=v3[1]+1;
			v3.erase(v3.begin()+4);
			v3.push_back(0);
			outer.push_back(v3);
		}
	}
	//General move (1,0)
	if(((v[2]-1) >= v[3] || ((v[2]-1) == 0)) && ((v[0]+1) >= v[1]) && ((v[2]-1) >= 0))  {
		v4[2]=v4[2]-1;
		v4[0]=v4[0]+1;
		v4.erase(v4.begin()+4);
		v4.push_back(0);
		outer.push_back(v4);
	}
	//General move (1,1)
	if(((v[2]-1) >= (v[3]-1)) && ((v[0]+1) >= (v[1]+1)) && ((v[2]-1) >= 0) && ((v[3]-1) >= 0)) {
		
		v5[2]=v5[2]-1;
		v5[3]=v5[3]-1;
		v5[0]=v5[0]+1;
		v5[1]=v5[1]+1;
		v5.erase(v5.begin()+4);
		v5.push_back(0);
		outer.push_back(v5);
	}		
	return outer;
}	

//Method to check whether current configuration is the goal
bool River_config::isGoal_config(vector<int> config) const{
	
	if(final_config==config){
		return true;
	}
	else return false;
}	

//Method to display moves from start to finish
void River_config::display(vector<int> cfg, vector<int> initial, 
				map<vector<int>,vector<int> >trackpath) const{
	deque<vector<int> >path;
	path.push_back(cfg);
	
	map<vector<int>,vector<int> >::iterator it= trackpath.find(cfg);
	while(it->second!=initial){
		it=trackpath.find(cfg);
		path.push_front(it->second);
		cfg=it->second;
	}
	cout<<"\t LHS Professors \t LHS Students \t\t RHS Professors \t RHS Students \t\t Boat"<<endl;
	for(unsigned int i= 0;i<path.size();i++){
		vector<int> &temp = path.at(i);
		cout<<"Step "<<i<<": ";
		for(unsigned int j=0; j<temp.size(); j++) {
			if(j!=4) {
				cout<<"\t"<<temp[j]<<"\t\t";
				}
			else {
				if(temp[j] == 0) {
					cout<<"Left";
				}
				else cout<<"Right";
			}
		}
		cout<<endl;
		}	
	}

//Destructor
River_config::~River_config(){}
