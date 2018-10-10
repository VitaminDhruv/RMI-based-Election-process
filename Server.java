
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Scanner;


public class Server implements Hello {
	// generating hashmap
	public static HashMap<String, Integer> countingboard = new HashMap<String,Integer>(); 

	public static ArrayList<Integer> VoterID= new ArrayList<Integer>();
	public static ArrayList<String> Voter= new ArrayList<String>();
// creating an boolean array list for the voter to check whether he has voted before or not ! 
	public static ArrayList<String> Vote= new ArrayList<String>();
	// Create Two arrays Here 1) int array for Voter Id
	// 2) String array for Voter name
	public static  ArrayList<String> temps = new ArrayList<String>();

	
    public Server() {
    	
    }

    public String sayHello() {
        return "Hello, world!";
    }


    private int  ViDgenerator() 
    {       

        Random ran =new Random();
        // Generating voter id up to 8 digit unique digit
        int nxt=ran.nextInt(22222222-10000000)+10000000;
        int length = String.valueOf(nxt).length();
        String str = Integer.toString(nxt);
        int[] ints = new int[str.length()];
        // if(length<8 || length>8)
        // {
        //     return false;
        // }

    	    for (int i = 0; i < str.length(); i++) 
            {
			ints[i] = Integer.parseInt(str.substring(i, i + 1));
        	}
		    for (int i = ints.length - 2; i >= 0; i = i - 2) 
            {
			    int j = ints[i];
			    j = j * 2;
			    if (j > 9) 
                {
				    j = j % 10 + 1;
		    	}
			    ints[i] = j;
	    	}
		    int sum = 0;
		    for (int i = 0; i < ints.length; i++) 
            {
			    sum += ints[i];
		    }
		    if (sum % 2 == 0)   
            {
                // if it is even than it return the even number

                return nxt;
                //System.out.println(str + " is a valid credit card number");
		    } 
            else 
            {
                //If it is odd than it add 1 and returns even
                return nxt+1;
			//System.out.println(str + " is an invalid credit card number");
		    }
	}
 
    
    
    // this method will take two inpput , Voter name and Voter ID 
    // Expand Voter ID and Voter name array as method call .....I have used ArrayList so you can remove that 
    public int registerVoter(String Vname) throws RemoteException 
	{
		
       	Voter.add(Vname);
        int x=ViDgenerator();
        //adding a unique number to the arraylist as a voter wants to register
        
    
    
    
    	VoterID.add(x);
		// Every time the voter register its voted value set to false
		Vote.add(null);
		System.out.print("----------");
		System.out.println("Registered Voters are->");
		for (int i=0;i<Voter.size();i++)
        {
    		System.out.print(Voter.get(i));
    		System.out.print(":");
    		System.out.print(VoterID.get(i));
    		System.out.print("|");
		
		}		//obj.DisplayUsers();
	System.out.println(" ");	
        return x;
    }
	








// One Method to only display the candidates


public ArrayList<String> Candidates() throws RemoteException 
	{	//return the candidate list 

		return temps;
	}   
  


    
    public String VerifyVoter(int id) 
    {	
			int index = VoterID.indexOf(id);
    		if (index != -1){
			    //	return Voter.get(index);
                String name=Voter.get(index);
                return name;
            
            }
			return "Voter Not Verified";
//            return Voter.get(index);
    }

	public String Vote(int id,int candidateIndex)
 	{
		int index = VoterID.indexOf(id);

		//Case 1: Voter is not verified
    	if (index == -1){
				return "This Voter Id does Not exist!!!";
		}
		
		//Case 2: Voter has already voted
		if(Vote.get(index) != null){
			return "You have already voted earlier!!!";
		}

		//Case 3: Voter can vote
		String candi = temps.get(candidateIndex);
		Vote.set(index, candi);
		countingboard.put(candi, countingboard.get(candi) + 1);
		return "Your vote has been successfully submited!!!";
 	} 

    public HashMap<String, Integer> tally() throws RemoteException 
        {	//return the candidate list with Votes
            return countingboard;
        }   

    public String Winner()throws RemoteException
    {
        int maxVote = 0;
        ArrayList<String> WinnerList= new ArrayList<String>();
        String winner = temps.get(0);
        int count = 1;
        WinnerList.add(winner);
        for (HashMap.Entry<String, Integer> entry : countingboard.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            if(value > maxVote){
                WinnerList = new ArrayList<String>();
                maxVote = value;
                winner = key;
                count = 1;
                WinnerList.add(winner);
            }
            else if(value == maxVote){
                count += 1;
                winner = key;
                WinnerList.add(winner);
            }
            else{

            }
        }

        if(maxVote == 0){
            return "No one has voted yet!! No Data to show";
        }

        if(count == 1){
            return winner + " has won the election and got " + maxVote + " votes!!";
        }
        
        return "More than one candidate got same maximum number of votes. These candidates got same votes" + WinnerList.toString();

    }
        




    public static void main(String args[]) throws IOException {
        
        try {
        	
            Server obj = new Server();
            Hello stub = (Hello) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("Hello", stub);
            System.err.println("Server ready");
        
    // reading from file code 
            // TODO code application logic here

            // // read KeyWestTemp.txt

            // create token1
            String token1 = "";

            // for-each loop for calculating heat index of May - October

            // create Scanner inFile1
            Scanner inFile1 = new Scanner(new File("name.txt")).useDelimiter(",\\s*");

            // Original answer used LinkedList, but probably preferable to use ArrayList in most cases
            // List<String> temps = new LinkedList<String>();
         
//we will make this arraylist visible to othe methods of the class;

//   ArrayList<String> temps = new ArrayList<String>();

            // while loop
            while (inFile1.hasNext()) 
            {
              // find next line
              token1 = inFile1.next();
              temps.add(token1);
            }
            inFile1.close();

            String[] tempsArray = temps.toArray(new String[0]);
           
            // List will be stored in temps array 
            System.out.println(temps);
            System.out.println(" The list of Candidates are: ");
            System.out.println("---------");            

            for (String s : tempsArray) 
            {
              System.out.println(s);
              //Generating all vote for zero nad displaying the Candidate: 
			  countingboard.put(s,0);
            }
            System.out.println("--------");
            System.out.println("Current Vote Pool of the candidates");
            System.out.println(" ");
			System.out.println(countingboard);
	    }
       	 catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}