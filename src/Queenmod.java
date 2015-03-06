/*
 * 
 * Board.java
 * 
 * Version 1
 * 
 * Revision 1
 * 
 */


/**
 * This program calculates the maximum number of queens that can be placed on
 * the chessboard without any queen threatening each other.
 * 
 * @author Uday Wadhone
 * 
 *
 */
public class Queenmod {
	
	//global array that holds the position of 8 queens
	
	static int x[] = new int[8];
	
	//variable used for finding max no of queens
	
	static int maxqueens=0;
	
	//array to hold total number of queens on board in each iteration
	
	static int[] totalqueens = new int[92];
	
	//variable to increment place in totalqueens array
	
	static int z=0;
	
	/*initialize the chessboard by making all values true that is every space 
	 * is legal at the start.
	 */
	
	static boolean[][] k = new boolean [][]
	{
			{true,true,true,true,true,true,true,true},
			{true,true,true,true,true,true,true,true},
			{true,true,true,true,true,true,true,true},
			{true,true,true,true,true,true,true,true},
			{true,true,true,true,true,true,true,true},
			{true,true,true,true,true,true,true,true},
			{true,true,true,true,true,true,true,true},
			{true,true,true,true,true,true,true,true}
			
	};	
	
	/**
	 * Returns TRUE if queen can be placed in row r and column c.
	 * x[] is a global array whose first (r-1) values have been set.
	 * @param r      row
	 * @param c      column
	 * @return       true or false
	 */
	
	public static boolean canPlaceQueen(int r, int c) {
        /*
         * check for legal spaces (true) for each row and column by iterating 
         * through the chessboard
         */
		
        for (int i = 0; i < r; i++) {
            if (x[i] == c || (i - r) == (x[i] - c) ||(i - r) == (c - x[i])) {
                return false;
            }
        }
        return true;
    }
	
	/**
	 * method to print queens, calculate total number of queens in the 
	 * chessboard for each iteration and store the same in totalqueens array.
	 *  
	 * @param x     global array with queens position
	 * @param k     chessboard
	 */
	
    public static void printQueens(int[] x, boolean k[][]) {
    	//here N=8 is initialised so we can traverse though all 8 rows
    	
        int N = x.length;
        maxqueens=0;
        
        /*
         * Print out the chessboard for each iteration and place the number of
         * queens for each iteration in totalqueens array
         */
        for (int i = 0; i < N; i++) {
        	
            for (int j = 0; j < N; j++) {
            	
                if (x[i] == j) {
                    System.out.print("Q ");
                    maxqueens++;
                } 
                else {
                	
                	if(k[i][j]==true) {
                		
                		System.out.print("1 ");
                		maxqueens++;
                		modifyRow(i, j);
                	}
                		
                	else
                		System.out.print("* ");
                }
            }
            
            System.out.println();
        }
        
        System.out.println();
        totalqueens[z]=maxqueens;
        z++;
    }
    
    /**
     * Using backtracking this methods finds all possible placements of n 
     * queens on an n x n chessboard so that they are non attacking
     * 
     * @param r       row
     * @param n       columns(8)
     */

    public static void placeNqueens(int r, int n) 
    {
        /*
         * starting with the first row, each column is checked for legal spaces
         * and if a legal space is found, queen is placed and the modifyRow 
         * method is called to define the walls and modify legal spaces in the
         * chessboard
         * 
         */
        for (int c = 0; c < n; c++) {
        	
            if (canPlaceQueen(r, c)) {
            	
                x[r] = c;
                if (r == n - 1) {
                	
                	for (int i = 0; i < 8; i++) {
                		
                        for (int j = 0; j < 8; j++) {
                        	
                            if (x[i] == j) {
                            	
                            	/*
                            	 * make the position where queen is placed
                            	 * as false
                            	 */
                            	
                                k[i][j] = false;
                                modifyRow(i, j);
                            }
                        }
                    }
                    
                	//method called to print queens
                	
                	printQueens(x,k);
                	
                	//reset the chessboard for the next iteration
                	reSetK();
                	
                    
                } 
                else {   
                	/*
                	 * increments the queens position to implement backtracking
                	 * and calls the function again
                	 */
                    placeNqueens(r + 1, n);
                }
            }
        }
    }
    
    /**
     * Method called to reset board after every iteration
     * Note: mind that the value before backtracking is stored in the global
     * array x[]
     */
    
    public static void reSetK() {
    	
    	k = new boolean [][]
    			{
    					{true,true,true,true,true,true,true,true},
    					{true,true,true,true,true,true,true,true},
    					{true,true,true,true,true,true,true,true},
    					{true,true,true,true,true,true,true,true},
    					{true,true,true,true,true,true,true,true},
    					{true,true,true,true,true,true,true,true},
    					{true,true,true,true,true,true,true,true},
    					{true,true,true,true,true,true,true,true}
    					
    			};	
    	
    }
    
    /**
     * method to call each row method to define the walls and modify legal
     * spaces for each iteration.
     * @param i     row
     * @param j     column
     */
    public static void modifyRow(int i,int j) {
    	
    	if(i==0)
    		Row1(i,j);
    	if(i==1)
    		Row2(i,j);
    	if(i==2)
    		Row3(i,j);
    	if(i==3)
    		Row4(i,j);
    	if(i==4)
    		Row5(i,j);
    	if(i==5)
    		Row6(i,j);
    	if(i==6)
    		Row7(i,j);
    	if(i==7)
    		Row8(i,j);
    }
    
    /**
     * method Rown (n through rows 1 to 8) defines the wall and
     * modifies the legal spaces on the chessboard according to queen placement
     * 
     * @param i
     * @param j
     */
    
    public static void Row1(int i,int j) {
    	
        	if(j <= 5) {
        		k[i][0]=false;k[i][1]=false;k[i][2]=false;k[i][3]=false;
        		k[i][4]=false;k[i][5]=false;
        	}
        	if(j==0) {
        		k[i+1][j]=false;k[i+1][j+1]=false;
        	}
        	if(j==1) {
        		k[i+1][j-1]=false;k[i+1][j]=false;k[i+1][j+1]=false;
        		
        	}
        	if(j==2) {
        		k[i+1][j-1]=false;k[i+1][j]=false;k[i+1][j+1]=false;
        	}
        	if(j==3) {
        		k[i+1][j]=false;
        		k[i+2][j]=false;
        		
        	}
        	if(j==4) {
        		k[i+1][j]=false;
        		k[i+2][j]=false;
        		
        	}
        	if(j==5) {
        		k[i+1][j]=false;
        		k[i+2][j]=false;
        		k[i+1][j-1]=false;
        	}
        	if(j==6) {
        		for(int a=1;a<=4;a++)
        			k[a][j]=false;
        		k[i+1][j+1]=false;
        		k[i][j+1]=false;
        	}
        	if(j==7) {
        		for(int a=1;a<=4;a++)
        			k[a][j]=false;
        		k[i+1][j-1]=false;
        		k[i][j-1]=false;
        	}
    	
    }
    public static void Row2(int i,int j) {
    	
        	if(j==0) {
        		k[i][0]=false;k[i][1]=false;k[i][2]=false;k[i][3]=false;
        		k[i-1][j]=false;k[i-1][j+1]=false;
        	}
        	if(j==1) {
        		k[i][0]=false;k[i][1]=false;k[i][2]=false;k[i][3]=false;
        		k[i-1][j-1]=false;k[i-1][j]=false;k[i-1][j+1]=false;
        	}
        	if(j==2) {
        		k[i][0]=false;k[i][1]=false;k[i][2]=false;k[i][3]=false;
        		k[i-1][j-1]=false;k[i-1][j]=false;k[i-1][j+1]=false;
        		k[i+1][j+1]=false;                       
        	}
        	if(j==3) {
        		k[i][0]=false;k[i][1]=false;k[i][2]=false;k[i][3]=false;
        		k[i-1][j]=false;k[i+1][j]=false;
        		
        	}
        	if(j==4) {
        		k[i-1][j]=false;k[i-1][j+1]=false;
        		k[i][j+1]=false;
        		k[i+1][j]=false;
        		k[i+1][j+1]=false;
        	}
        	if(j==5) {
        		k[i-1][j-1]=false;k[i-1][j]=false;
        		k[i][j-1]=false;
        		k[i+1][j-1]=false;k[i+1][j]=false;
        		                                		
        	}
        	if(j==6) {
        		for(int a=0;a<=4;a++)
        			k[a][j]=false;
        		k[i-1][j+1]=false;
        		
        	}
        	if(j==7) {
        		for(int a=0;a<=4;a++)
        			k[a][j]=false;
        		k[i+1][j-1]=false;
        	}
        	
    	
    }
    public static void Row3(int i,int j){
    	
        	if(j==0) {
        		for(int a=0;a<=3;a++)
        			k[i][a]=false;
        		for(int a=0;a<=4;a++)
        			k[i+a][j]=false;
        		
        		k[i+1][j+1]=false;
        	}
        	if(j==1) {
        		for(int a=0;a<=5;a++)
        			k[i+a][j]=false;
        		for(int a=0;a<=3;a++)
        			k[i+a][j+a]=false;
        		k[i+1][j-1]=false;
        		k[i][j-1]=false;k[i][j]=false;k[i][j+1]=false;k[i][j+2]=false;
        	}
        	if(j==2) {
        		k[i][j-1]=false;k[i][j-2]=false;k[i][j]=false;k[i][j+1]=false;
        		for(int a=0;a<=3;a++)
        			k[i+a][j]=false;
        		
        		for(int a=0;a<=2;a++)
        			k[i+a][j-a]=false;
        		
        		
        	}
        	if(j==3) {
        		k[i-1][j]=false;k[i-2][j]=false;
        		
        		for(int a=3;a>=0;a--)
        			k[i][j-a]=false;
        		
        	}
        	if(j==4) {
        		for(int a=0;a<=3;a++)
        			k[i][j+a]=false;
        		k[i-1][j]=false;k[i-2][j]=false;
        		k[i-1][j+1]=false;
        	}
        	if(j==5) {
        		for(int a=0;a<=3;a++)
        			k[i][j+a-1]=false;
        		k[i-1][j]=false;k[i-2][j]=false;
        		k[i-1][j-1]=false;
        		k[i-1][j+1]=false;k[i-2][j+2]=false;
        		
        	}
        	if(j==6) {
        		for(int a=0;a<=3;a++)
        			k[i][j+a-2]=false;
        		for(int a=0;a<=4;a++)
        			k[a][j]=false;
        		k[i-1][j+1]=false;
        		
        		k[i+1][j+1]=false;
        		
        	}
        	if(j==7) {
        		for(int a=0;a<=4;a++)
        			k[a][j]=false;
        		for(int a=0;a<=3;a++)
        			k[i][j+a-3]=false;
        		k[i-1][j-1]=false;
        		k[i+1][j-1]=false;k[i+2][j-2]=false;
        	}
    	
    }
    public static void Row4(int i,int j) {
    	
         	if(j==0) {
         		for(int a=0;a<=4;a++)
         			k[i+a-1][j]=false;
         		for(int a=0;a<=4;a++)
         			k[i][a]=false;
         		k[i-1][j+1]=false;
         		k[i+1][j+1]=false;
         	}
         	if(j==1) {
         		for(int a=0;a<=5;a++)
         			k[i+a-1][j]=false;
         		for(int a=0;a<=4;a++)
         			k[i][a]=false;
         		k[i+1][j-1]=false;
         		k[i-1][j+1]=false;
         		k[i-1][j-1]=false;
         	}
         	if(j==2) {
         		for(int a=0;a<=3;a++)
         			k[i+a-1][j]=false;
         		for(int a=0;a<=4;a++)
         			k[i][a]=false;
         		
         		
         		for(int a=0;a<=3;a++)
         			k[i+a-1][j-1+a]=false;
         		
         	}
         	if(j==3) {
         		for(int a=0;a<=4;a++)
         			k[i][a]=false;
         		k[i+1][j]=false;k[i+2][j]=false;
         		k[i+1][j-1]=false;
         		k[i+1][j+1]=false;
         		
         	}
         	if(j==4) {
         		for(int a=0;a<=4;a++)
         			k[i][a]=false;
         		k[i+1][j]=false;k[i+2][j]=false;
         		k[i+1][j-1]=false;k[i+2][j-2]=false;
         		
         	}
         	if(j==5) {
         		k[i][j+1]=false;k[i][j+2]=false;
         		k[i+1][j]=false;k[i+1][j+1]=false;
         		
         	}
         	if(j==6) {
         		for(int a=0;a<=4;a++)
         			k[a][j]=false;
         		k[i][j-1]=false;k[i][j+1]=false;
         		k[i+1][j-1]=false;k[i+1][j]=false;k[i+1][j+1]=false;
         		
         	}
         	if(j==7) {
         		for(int a=0;a<=4;a++)
         			k[a][j]=false;
         		k[i][j-1]=false;k[i][j-2]=false;
         		k[i+1][j-1]=false;
         		
         		k[i-1][j-1]=false;
         	}
    }
    public static void Row5(int i,int j) {
    	
         	if(j==0) {
         		for(int a=0;a<=4;a++)
         			k[i-2+a][j]=false;
         		k[i][j+1]=false;
         		
         		k[i+1][j+1]=false;
         		for(int a=1;a<=2;a++)
         			k[i-a][j+a]=false;
         	}
         	if(j==1) {
         		for(int a=0;a<=5;a++)
         			k[i-2+a][j]=false;
         		k[i-1][j-1]=false;
         		k[i+1][j-1]=false;
         		
         		k[i][j-1]=false;
         	}
         	if(j==2) {
         		for(int a=0;a<=3;a++)
         			k[i-2+a][j]=false;
         		k[i][j+1]=false;k[i][j+2]=false;
         		k[i-1][j+1]=false;
         		k[i+1][j+1]=false;
         	}
         	if(j==3) {
         		k[i][j-1]=false;k[i][j+1]=false;
         		k[i-1][j]=false;k[i+1][j]=false;
         		k[i+1][j-1]=false;k[i-1][j+1]=false;
         		for(int a=0;a<=3;a++)
         			k[i-2+a][j-2+a]=false;
         	}
         	if(j==4)
         	{
         		k[i-1][j]=false;k[i+1][j]=false;
         		k[i][j-1]=false;k[i][j-2]=false;
         		k[i+1][j-1]=false;
         	
         		k[i-1][j-1]=false;
         	}
         	if(j==5) {
         		k[i][j+1]=false;k[i][j+2]=false;
         		k[i-1][j]=false;
         		k[i-1][j+1]=false;k[i-2][j+2]=false;
         	}
         	if(j==6) {
         		for(int a=0;a<=4;a++)
         			k[a][j]=false;
         		k[i-1][j-1]=false;k[i-1][j+1]=false;
         		k[i][j-1]=false;k[i][j+1]=false;
         	}
         	if(j==7) {
         		for(int a=0;a<=4;a++)
         			k[a][j]=false;
         		k[i][j-1]=false;k[i][j-2]=false;
         		
         		k[i-1][j-1]=false;
         	}    	
    }
    public static void Row6(int i,int j) {
    	
          	if(j==0) {
          		for(int a=0;a<=4;a++)
          			k[i-3+a][j]=false;
          		k[i][j+1]=false;
          		k[i+1][j+1]=false;k[i+2][j+2]=false;
          		
          		k[i-1][j+1]=false;
          	}
          	if(j==1) {
          		for(int a=0;a<=5;a++)
          			k[i-3+a][j]=false;
          		k[i][j-1]=false;
          		k[i+1][j-1]=false;
          		
          		k[i-1][j-1]=false;
          	}
          	if(j >= 2) {
          		for(int a=2;a<=7;a++)
          			k[i][a]=false;
          	}
          	if(j==2) {
          		for(int a=0;a<=3;a++)
          			k[i-+a][j]=false;
          		k[i-1][j+1]=false;k[i-2][j+2]=false;
          	}
          	if(j==3) {
          		k[i-1][j]=false;k[i-2][j]=false;
          		k[i-1][j+1]=false;
          		k[i-1][j-1]=false;
          	}
          	if(j==4) {
          		k[i-1][j]=false;k[i-2][j]=false;
          		for(int a=0;a<=3;a++)
          			k[i-3+a][j-3+a]=false;                                		
          	}
          	if(j==5) {
          		k[i+1][j]=false;k[i+2][j]=false;
          		
          		
          	}
          	if(j==6) {
          		k[i+1][j]=false;k[i+2][j]=false;
          		
          		k[i+1][j+1]=false;
          	}
          	if(j==7) {
          		k[i+1][j]=false;k[i+2][j]=false;
          		k[i+1][j-1]=false;
          	}    	
    }
    public static void Row7(int i,int j) {
    	
        	if(j<=5) {
        		for(int a=0;a<=5;a++)
        			k[i][a]=false;
        	}
        	if(j==0) {
        		for(int a=0;a<=4;a++)
        			k[i-4+a][j]=false;
        		k[i-1][j+1]=false;
        	
        	}
        	if(j==1) {
        		for(int a=0;a<=5;a++)
        			k[i-4+a][j]=false;
        		k[i-1][j-1]=false;k[i+1][j+1]=false;
        	}
        	if(j==2) {
        		k[i+1][j-1]=false;k[i+1][j]=false;
        		
        	}
        	if(j==3) {
        		k[i+1][j-1]=false;k[i+1][j]=false;k[i+1][j+1]=false;
        	}
        	if(j==4) {
        		k[i+1][j-1]=false;k[i+1][j]=false;k[i+1][j+1]=false;
        		
        	}
        	if(j==5) {
        		k[i+1][j-1]=false;k[i+1][j]=false;
        		k[i-1][j]=false;
        		
        	}
        	if(j==6) {
        		k[i-1][j]=false;k[i+1][j]=false;k[i+1][j+1]=false;
        		k[i-1][j+1]=false;k[i][j+1]=false;
        		
        	}
        	if(j==7) {
        		k[i-1][j]=false;k[i+1][j]=false;
        		k[i-1][j-1]=false;k[i][j-1]=false;k[i+1][j-1]=false;
        	}
    }
    
    public static void Row8(int i,int j)
    {
         	if(j <= 5 && j!=0) {
         		for(int a=1;a<=5;a++)
         			k[i][a]=false;
         	}
         	if(j==1) {
         		for(int a=2;a<=7;a++)
         			k[a][j]=false;
         		
         		k[i-1][j+1]=false;
         	}
         	if(j==2) {
         		k[i-1][j]=false;k[i-1][j+1]=false;
         		k[i-1][j-1]=false;k[i-1][j-1]=false;
         	}
         	if(j==3) {
         		k[i-1][j]=false;
         		k[i-1][j+1]=false;
         		
         		k[i-1][j-1]=false;
         	}
         	if(j==4) {
         		k[i-1][j]=false;k[i-1][j-1]=false;
         		k[i-1][j+1]=false;
         	}
         	if(j==5) {
         		k[i-1][j]=false;k[i-2][j]=false;
         		k[i-1][j-1]=false;
         	}
         	if(j==6) {
         		k[i-1][j]=false;k[i-2][j]=false;
         		k[i][j+1]=false;k[i-1][j+1]=false;
         	}
         	if(j==7) {
         		k[i-1][j]=false;k[i-2][j]=false;
         		k[i][j-1]=false;k[i-1][j-1]=false;
         	}
    }
    
    /**
     * method is called to print out the maximum number of queens that can be 
     * placed on the chessboard using the totalqueens array. Also used to
     * display number of queens found in each chessboard iteration.
     */
    
    public static void MaxQueens()
    {
    	//initialise first position in totalqueens array as max
    	int max=totalqueens[0];
    	int no=0;
    	System.out.println("Total solution = "+totalqueens.length);
    	
    	/*
    	 * finds out the max number from totalqueens array which gives us the
    	 * solution to the problem
    	 */
    	
    	for(int i =0;i<totalqueens.length;i++) {
    		
    		if(max <= totalqueens[i]) {
    			max=totalqueens[i];
    			no=i+1;
    		}
    	}
    	
        /*
         * print out the max number of queens that can be placed and number
         * of queens placed on chessboard in each iteration
         */
    	
    	System.out.println("Max Queens = "+max);
    	System.out.println("Max queens in " +no);
    	//print number of queens for each iteration
    	for(int j=0;j<totalqueens.length;j++)
    		System.out.println("totalq["+j+"] = "+totalqueens[j]);
    }
    
    /**
     * The main program
     * 
     * @param args
     */
    public static void main(String[] args) {
    
    	//call method to place queens
    	placeNqueens(0, 8);
    	
    	/*
    	 * call method to calculate maximum number of queens using totalqueens
    	 * array and display the solution and the number of queens placed in
    	 * each iteration.
    	 */
		MaxQueens();
		
	}
}