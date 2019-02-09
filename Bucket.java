package A_2;

public class Bucket {
	private Voter[] elements; 
	private int key;
	private int num; 
	
	public Bucket(int n , int k){
		elements = new Voter[n];/*
		Voter.getTotal() used to get the value for n. This is so because there
		is a chance that all voters vote for only 1 candidate
		n represents the number of elements that array can have*/
		key = k;//k represents the key for that bucket
	}
	public void add(Voter v){//new bucket array ref is sent in here to add voter info to bucket
		if(num<elements.length)//length check
			elements[num++] = v;//increment number of elements used up
		else
			System.out.println("This is not the right bucket.");
	}
	
	public Voter[] getElement(){//returns the whole bucket
		return elements;
	}
	
	public int getKey(){//check the key for the bucket
		return key;
	}
	
	
	public int getSize(){//gets the number of used up elements for the bucket
		return num;
	}

}
