### Byteland

* Initially, each city is an independent State. The process of integration is divided into steps.

* At each step, due to the limited number of diplomatic envoys available, a State can only be involved in a unification process with at most one other state. At each step two States can unite to form a new State, but only if there exists a road directly connecting some two cities of the uniting States.

* The unification process is considered to be complete when all the cities belong to the same, global State.

The Mayors have asked you to arrange a schedule for the diplomatic talks, so that unification can be completed in as few steps as possible. Can you handle this delicate task?

Input:
The first line contains t, the number of test cases (less than 1000). The descriptions of t test cases follow.
Each test case contains the description of the cities of Byteland, given in two lines. The first line contains a single integer k, representing the number of cities in Byteland (2 < = k < = 600); we assume that the cities are numbered 0,...,k-1. The second line contains exactly k-1 integers, and the ith integer having a value of p represents a road connecting cities having numbers i+1 and p in Byteland.

Output:
For each test case, output a separate line containing one number, equal to the minimum number of steps required to perform the unification.

	Example
	Input:
	3
	
	4
	0 1 2
	
	8
	0 1 2 0 0 3 3
	
	9
	0 1 1 1 1 0 2 2
	
	Output:
	2
	4
	5
	
### How To Work
The project is a maven project which you can import into any ide(ecilpse,netbeans etc.) 
Run the App.java class that is under the com.mustafaergan.byteland package and the above input data are entered.
