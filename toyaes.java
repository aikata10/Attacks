import java.io.*;
import java.util.*;
import java.util.Random;
class toyaes
 {
     static int[][][] k=new int[15][2][2]; //key schedule
     static int[] s=new int[16];           //sbox
     static int[] is=new int[16];          //inverse sbox
     static int[][] s0={{1,2},{3,4}};      //key
     static int a[][]={{0,1},{0,8}};       //input

int[][] addroundkey(int a[][],int r[][])
     {
          a[0][0]=((a[0][0])^(r[0][0]))%16;
          a[0][1]=((a[0][1])^(r[0][1]))%16;
          a[1][0]=((a[1][0])^(r[1][0]))%16;
          a[1][1]=((a[1][1])^(r[1][1]))%16;
      return a;
      }
int[][] subbytes(int a[][])
     {
           s[0]=9;s[1]=4;s[2]=10;s[3]=11;s[4]=13;s[5]=1;s[6]=8;s[7]=5;s[8]=6;s[9]=2;s[10]=0;s[11]=3;s[12]=12;s[13]=14;s[14]=15;s[15]=7;//sbox
           a[0][0]=s[a[0][0]];
           a[0][1]=s[a[0][1]];
           a[1][0]=s[a[1][0]];
           a[1][1]=s[a[1][1]];
      return a;
      }
int[][] invsubbytes(int a[][])
     {
           is[0]=10;is[1]=5;is[2]=9;is[3]=11;is[4]=1;is[5]=7;is[6]=8;is[7]=15;is[8]=6;is[9]=0;is[10]=2;is[11]=3;is[12]=12;is[13]=4;is[14]=13;is[15]=14;//invsbox
           a[0][0]=is[a[0][0]];
           a[0][1]=is[a[0][1]];
           a[1][0]=is[a[1][0]];
           a[1][1]=is[a[1][1]];
          
      return a;
      }
int[][] shiftrow(int a[][])
     {
           int t=a[1][0];
            a[1][0]=a[1][1];
            a[1][1]=t;
          
      return a;
      }
int op(int p,int j)
 {
  for(int i=0;i<j;i++) 
   {
     if(p<=7)
         p=p*2;
     else
         p=((p%8)*2)^(3);
   }

  return p;
 }


int[][] invmixcolumns(int a[][])
     {
      toyaes ob=new toyaes();
             int w=(ob.op(a[1][0],1))^(ob.op(a[0][0],3))^(a[0][0]);
	     a[1][0]=(ob.op(a[0][0],1))^(ob.op(a[1][0],3))^(a[1][0]);
             a[0][0]=w;
             w=(ob.op(a[1][1],1))^(ob.op(a[0][1],3))^(a[0][1]);
             a[1][1]=(ob.op(a[0][1],1))^(ob.op(a[1][1],3))^(a[1][1]);
             a[0][1]=w;
      return a;
      }
int[][] mixcolumns(int a[][])
     {
      toyaes ob=new toyaes();
             int w=(ob.op(a[1][0],2))^(a[0][0]);
	     a[1][0]=(ob.op(a[0][0],2))^(a[1][0]);
             a[0][0]=w;
             w=(ob.op(a[1][1],2))^(a[0][1]);
             a[1][1]=(ob.op(a[0][1],2))^(a[1][1]);
             a[0][1]=w;
      return a;
      }

void printmat(int a[][])
{
  System.out.println(a[0][0]+"  "+a[0][1]);
  System.out.println(a[1][0]+"  "+a[1][1]);
}

void keyschedule()
{
      int[] rc={1,2,4,8,3,6,12,11,5,10,7,14,15,13,9} ;
      k[0][0][0]=s0[0][0]; k[0][0][1]=s0[0][1]; k[0][1][0]=s0[1][0]; k[0][1][1]=s0[1][1];
 
      for(int i=1;i<15;i++)
            {
             k[i][0][0]=(k[i-1][0][0])^(s[k[i-1][1][1]])^(rc[0]);
             k[i][0][1]=(k[i-1][0][0])^(k[i][0][0]);
             k[i][1][0]=(k[i-1][1][0])^(k[i][0][1]);
             k[i][1][1]=(k[i-1][1][1])^(k[i][1][0]);
            }
       
}
int[][] encrypt(int a[][],int t) 
{
      toyaes ob=new toyaes();
      System.out.println("Original matrix before ENCRYPTION");
      ob.printmat(a);
      a=ob.addroundkey(a,k[0]);
  //k ROUNDS
   for(int i=0;i<t;i++)
    {
      a=ob.subbytes(a);       
      a=ob.shiftrow(a);       
      a=ob.mixcolumns(a);      
      a=ob.addroundkey(a,k[i+1]);
    }
     System.out.println("ENCRYPTION after "+t+" rounds");
      ob.printmat(a);
  return a;
}
int[][] decrypt(int a[][],int t) 
{
      toyaes ob=new toyaes();
      System.out.println("Original matrix before DECRYPTION");   
      ob.printmat(a);
  //k ROUNDS
   for(int i=t;i>0;i--)
     {
      a=ob.addroundkey(a,k[i]);    
      a=ob.invmixcolumns(a);      
      a=ob.shiftrow(a);          
      a=ob.invsubbytes(a);
     }
     a=ob.addroundkey(a,k[0]);
     System.out.println("DECRYPTION after "+t+" rounds");
      ob.printmat(a);
  return a;
}

public static void main(String args[])throws IOException
    {
      toyaes ob=new toyaes();
      ob.keyschedule();        
      ob.encrypt(a,5);
      ob.decrypt(a,5);
   }
}
