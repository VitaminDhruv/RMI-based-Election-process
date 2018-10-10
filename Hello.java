import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;


public interface Hello extends Remote {
    String sayHello() throws RemoteException;
    int registerVoter(String x) throws RemoteException;
   // void DisplayUsers() throws RemoteException;
	ArrayList<String> Candidates() throws RemoteException;
    String VerifyVoter(int x) throws RemoteException;
    String Vote(int id,int candidateIndex) throws RemoteException;
    HashMap<String, Integer> tally() throws RemoteException;
    String Winner()throws RemoteException;
  //  boolean ViDgenerator(int x) throws RemoteException;
}