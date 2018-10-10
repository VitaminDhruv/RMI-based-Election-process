# RMI-based-Election-proce
Remote method Invocation based program that simulates an Election process. Here Hello is the remote interface. The program basically follow these steps.

1. The register voter methods should take as parameters a name, and assign a voter ID from a list of
valid voter IDs (Each of you decides the criteria of a valid voter ID, it needs to be a format
that your system
generates and accepts, be innovative here).
2. The verify voter method takes a voter ID as input and verifies if the voter with the voter ID is
registered
and under what name? However it should not allow the voter to vote if they have voted before.
3. The vote method should allow a voter to cast a single vote for a single candidate.
4. The tally results, should return the votes each of the candidates received to be displayed by the
client.
5. Announce winner should just return the election winner's name.

A client/server RMI application that takes handles this system, 
This program should start by loading a file with candidate names (Supply as input any text file contains names), it should handle
up to 10 candidates (you can use more than 10 candidates you want to). Then this program should generate valid voter IDs (define your own criteria for what a valid voter ID is). They system should allow for up to 1000 voters.


Then once the system starts, a menu should be displayed to ask the user to either input one of the5 choices listed above or 0 to quit.

1. Register to Vote
2. Verify Voter
3. Vote
4. Tally Results
5. Announce Winner
0. Quit
Please Enter your Choice:
