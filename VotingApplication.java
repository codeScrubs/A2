package A_2;
/*
The purpose of this is to gather up all the votes and all the possible candidates (from the provided text files) and then
sort them with bucket sorting. After that is done, see which candidate won the election.
**/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class VotingApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String pathCandidates= "./src/A_2/candidate.txt"; //POSSIBLY BAD PATHING?
		String pathVoters = "./src/A_2/voters.txt"; //I WANT PATHING THAT CAN WORK EVEN WHEN ON ANOTHER PC
		Candidate[] candidates =null;
		Voter[] voters =null;
		try{
			candidates = getCandidates(pathCandidates);
			voters = getVoters(pathVoters);
			Candidate winner = getWinner(candidates , voters);
			JOptionPane.showMessageDialog(null, "message");
			JOptionPane.showMessageDialog(null, "The winner is: " + winner.toString());
		}
		catch(FileNotFoundException e){
			
		}catch(IOException e){
			
		}
	}
	
	
	public static Candidate[] getCandidates(String path) throws FileNotFoundException,
	IOException{
		BufferedReader br = new BufferedReader(new FileReader(new File(path)));
		String line; 
		Scanner scan = null; 
		int count=0;
		while((line = br.readLine()) !=null){
			count++;
		}
		
		br = new BufferedReader(new FileReader(new File(path)));
		Candidate c; 
		String name;
		int code; 
		Candidate[] candids = new Candidate[count];
		int i=0;
		while((line = br.readLine()) !=null){
			scan =new Scanner(line);
			scan.useDelimiter(",");
			name = scan.next(); 
			code = Integer.parseInt(scan.next().trim());
			c= new Candidate(name , code);
			candids[i++]=c;
		}

		return candids;
	}
	
	public static Voter[] getVoters(String path) throws FileNotFoundException,
	IOException{
		BufferedReader br = new BufferedReader(new FileReader(new File(path)));
		String line; 
		Scanner scan = null; 
		int count=0;
		while((line = br.readLine()) !=null){
			count++;
		}
		
		br = new BufferedReader(new FileReader(new File(path)));
		Voter v; 
		char gender;
		int code; 
		Voter[] voters = new Voter[count];
		int i=0;
		while((line = br.readLine()) !=null){
			scan =new Scanner(line);
			scan.useDelimiter(",");
			gender = scan.next().trim().charAt(0); 
			code = Integer.parseInt(scan.next().trim());
			v= new Voter(gender);
			v.setVote(code);
			voters[i++]=v;
		}

		return voters;		
	}
///////////////////*************************************FROM HERE**************************************/////////////
	
	public static Candidate getWinner(Candidate[] candids, Voter[] voters){
		//TODO
		int b1Count =0;//counts how many times a certain vote is cast
		int b2Count =0;
		int b3Count =0;
		int b4Count =0;
		int b5Count =0;
		
		for(int x=0;x<voters.length;x++) {//Use for this loop is to create the buckets for each candidate
										//use voters.length in order to go through all voter entries
			if (candids[x].getCode() == 1) {//getCode pulls KeyCode from candidate.java this is needed to 
											//assign a correct key to each bucket
				int key = 1;//value to distinguish the key for the bucket
				Bucket b1 = new Bucket(Voter.getTotal(), key);
				/*use voters.getTotal() because there is a chance that all
				 * voters have voted for the same candidate
				 * */
				if(voters[x].getVote() == 1) {
					b1.add(voters[x]);	
				}
				b1Count = b1.getSize();//getSize gets total amount of elements that b1 has
			}
			if (candids[x].getCode() == 2) {
				int key = 2;
				Bucket b2 = new Bucket(Voter.getTotal(), key);
				/*use voters.getTotal() because there is a chance that all
				 * voters have voted for the same candidate
				 * */
				if(voters[x].getVote() == 2) {//add voter info to proper bucket array
					b2.add(voters[x]);	
				}
				b2Count = b2.getSize();
			}
			if (candids[x].getCode() == 3) {
				int key = 3;
				Bucket b3 = new Bucket(Voter.getTotal(), key);
				/*use voters.getTotal() because there is a chance that all
				 * voters have voted for the same candidate
				 * */
				if(voters[x].getVote() == 3) {//add voter info to proper bucket array
					b3.add(voters[x]);	
				}
				b3Count = b3.getSize();
			}
			if (candids[x].getCode() == 4) {
				int key = 4;
				Bucket b4 = new Bucket(Voter.getTotal(), key);
				/*use voters.getTotal() because there is a chance that all
				 * voters have voted for the same candidate
				 * */
				if(voters[x].getVote() == 4) {//add voter info to proper bucket array
					b4.add(voters[x]);	
				}
				b4Count = b4.getSize();
			}
			if (candids[x].getCode() == 5) {
				int key = 5;
				Bucket b5 = new Bucket(Voter.getTotal(), key);
				/*use voters.getTotal() because there is a chance that all
				 * voters have voted for the same candidate
				 * */
				if(voters[x].getVote() == 5) {//add voter info to proper bucket array
					b5.add(voters[x]);	
				}
				b5Count = b5.getSize();
			}
		}
		int returnIndex = 0;//used to store the element value that will show which is the highest number
		int index = 0 ;//used to store the first value of the array that will be used to compare
		int key = 1;//key variable made to for comparison purposes later, set to 1 for default mostVotes
		int bucketCount[] = {b1Count,b2Count,b3Count,b4Count,b5Count};
		int mostVotes = bucketCount[index];//first element is set to mostVotes by default
		for (int y=1;y<Candidate.getTotalCandidates();y++) {//checks rest of array for higher values
			if (bucketCount[y] > mostVotes) {
				mostVotes = bucketCount[y];
				if (y == 2) {
					key = 2;
				}
				if (y == 3) {
					key = 3;
				}
				if (y==4) {
					key = 4;
				}
				if(y==5) {
					key = 5;
				}				
			}
		}
		for(int a =0;a<Candidate.getTotalCandidates();a++) {//search through for the candidate the key belongs to
			if (candids[a].getCode() == key) {
				returnIndex = a;
			}
		}

	return candids[returnIndex];
	}

//////////////////////////////////******************TO HERE*****************************************/////////////////////////////////

}
