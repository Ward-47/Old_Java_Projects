/*
Name: Ward Leavines
File: MatMult.java
*/
package prog1_4103;

import java.util.*;

//class to implement thread to calculate matrix product
class MatProd extends Thread {

    int[][] a;
    int[][] b;
    int[][] c;
    
    int rnge, col;
    
    int dm;
    
    public MatProd(int[][] a, int[][] b, int[][] c, int rg, int colm, int dm_cm){
        
        this.a=a; 
        this.b=b;
        this.c=c;
        this.rnge=rg;
        this.col=colm;
        this.dm=dm_cm;
    }
    
    //This runs the method to calculate every element of result matrix R 
    
    
    public void run()
    {
        for(int i=0; i<dm; i++){
            c[rnge][col]+=a[rnge][i]*b[i][col];
        }
        
        System.out.println("Thread " + rnge +"," + col + " complete.");
    }
}

//this is where the main class calls and initiates mitrices a and b. 

public class MatMult{
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Scanner Input = new Scanner(System.in);
        
        //Prompts the user to enter the number of rows and colums for Matrix A
        System.out.print("Enter the number of rows and columns for Matrix A respectively: ");
        int rowA = Input.nextInt();
        int colA = Input.nextInt(); //edit
        
        //System.out.print("Enter the number of columns of Matrix A: ");
        //int colA = Input.nextInt();
        
        //Prompts the user to enter the number of rows and columns for Matrix B 
        System.out.print("Enter the number of rows and columns for Matrix B respectively: ");
        int rowB = Input.nextInt();
        int colB = Input.nextInt(); //edit
        
        //System.out.print("Enter the number of columns of Matrix B: ");
        //int colB = Input.nextInt();
        
        System.out.println();
        
        if(colA!=rowB)
        {
        
            System.out.println("Sorry, but the input for this Matrix Product is invalid. Please try again.");
            System.exit(-1);
        }
        
        System.out.println();
        int[][] A = new int[rowA][colA];
        int[][] B = new int[rowB][colB];
        int[][] C = new int[rowA][colB];
        
        MatProd[][] third = new MatProd[rowA][colB];
        
        //Prompts user to input the numbers in Matrix A
        System.out.println("Implement Matrix A");
        System.out.println();
        
        //read Matrix A from user.
        for(int i=0; i<rowA; i++)
        {
            for(int j=0; j <colA; j++)
            {
        
            
            System.out.print(i + "," + j + " = ");
            A[i][j]= Input.nextInt();
        }
    }
        
    //Prompts user to implement numbers in Matrix B
    System.out.println();   
    System.out.println("Implement Matrix B:");
    System.out.println();
    
    //read matrix B from user 
    for(int i=0; i< rowB; i++)
    {
    for(int j=0; j< colB; j++)
    {
     System.out.print(i + "," +j+ " = ");
     B[i][j]=Input.nextInt();
    }
}
    System.out.println();
    //loop to call threads for every calculation of elements of result matrix
    for(int i = 0; i <rowA; i++)
    {
        for (int j = 0; j < colB; j++)
        {
            third[i][j] = new MatProd(A,B,C,i,j,colA);
            third[i][j].start();
            
        }
    }
    
    for(int i=0; i < rowA; i++)
    {
        for(int j=0; j <colB; j++)
        {
            
            try{
                third[i][j].join();
            }
            catch(InterruptedException e){}
        }
    }
    
    //Shows user the product result for both matrices
    System.out.println();
    System.out.println("Result");
    System.out.println();
    
    for (int i = 0; i <rowA; i++)
    {
        for(int j=0; j < colB; j++)
        {
            System.out.print(C[i][j] + " ");
        }
        
        System.out.println();
    }
    
}
}
