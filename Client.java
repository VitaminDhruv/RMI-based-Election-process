import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
//Importing arraylist 
import java.util.*;



public class Client {
	//public static ArrayList<String> Voter= new ArrayList<String>();

    private Client() {}

    public static void main(String[] args) 
    
    {
 
    	int flag=0;
    	
    	
    	
    	Scanner s=new Scanner(System.in);//reading from the system    	
        String host = (args.length < 1) ? null : args[0];
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            Hello stub = (Hello) registry.lookup("Hello");
          
            // Calling the method 
            String response = stub.sayHello();
           
            
          //  System.out.println("Enter the number: ");
            //int n= s.nextInt();
            //int number =stub.calculate(n);
            
            // printing the number from remote method call 
            System.out.println("response: " + response);
            
            //creating choices for users to enter
            while (flag==0)
		     {
		     	System.out.println("Enter 5 choices listed below or enter 0 to quit:");		     	
		     	System.out.println("1. Register to Vote");
		     	System.out.println("2. Verify Voter");
		     	System.out.println("3. Vote");
		     	System.out.println("4. Tally Results");
		     	System.out.println("5. Announce Winner");
		     	System.out.println("0. Quit");
				System.out.println("-----------");
		     	int choice=s.nextInt();
		       
		     	
		     	//selecting the choice from the list
		     	//while (choice!=0) 
		     	{
		     		switch (choice)
		     		{
		     			case 1:
		 
		     				    System.out.print("Enter the voter name:");
		     					String Vname=s.next();
								System.out.println("Voter is registered with the id: ");
								int Vid=stub.registerVoter(Vname);
								System.out.println(Vid);
		     					System.out.println("-----------");
					 	
		     				    break;
		     			case 2:
		     				System.out.println("-----------");
							System.out.println("Enter your id to check: ");
		     				int x=s.nextInt();
		     				System.out.println(stub.VerifyVoter(x));
		     				System.out.println("-----------");
		     				
		     				
		     				break;
		     			case 3:
						 System.out.println("-----------");
							System.out.println("Here is Candidate List");
							ArrayList<String> Candidates= stub.Candidates();
							for(int i=0;i<Candidates.size();i++)
							{	
								System.out.print(i);
								System.out.print(". ");
								System.out.println(Candidates.get(i));
							}
							System.out.println("-----------");
							System.out.println("Enter you VoterId for begin voting: ");
							int id=s.nextInt();
							System.out.println("Now Enter the number of the candiate that you would like to vote: ");
							int can_index=s.nextInt();
							
							System.out.println(stub.Vote(id,can_index));
							//System.out.println(stub.VerifyVoter(x));
							System.out.println("-----------");
		     				break;
		     			case 4:

						 	System.out.println("-----------");
		     				System.out.println("The votes each of the candidates received are:");
							 System.out.println(" ");
							 System.out.println(stub.tally());
							 System.out.println("-----------");
		     				break;
		     			case 5:
		     				System.out.println(stub.Winner());
		     				break;
		     			case 0:
		     				flag++;
		     		}
		     		//end of an if condition 
		         	}
				System.out.println("-----------");
		     	System.out.println("Voting is Closed ");
				System.out.println("-----------");		
		     	}
		     
            
            
            
            s.close();
        } 
        catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}