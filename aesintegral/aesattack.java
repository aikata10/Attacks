import java.io.*;
import java.util.*;
import java.util.Random;
class aesattack
 {
     static int[] s=new int[16];
      
int all(int m[][][],int p,int q,int r)
 { 
  int a[]=new int[16];
  for(int i=0;i<16;i++)
       a[i]=0;
  for(int i=0;i<16;i++)
       {
       if(a[m[i+(p-1)*16][q][r]]==0)
         {
           a[m[i+(p-1)*16][q][r]]=1;
         }
       else
        return 0;
       }
  return 1;
 }
int balance(int m[][][],int p,int q,int r)
 { 
  int a=m[(p-1)*16][q][r];
  for(int i=1;i<16;i++)
      a=a^(m[i+(p-1)*16][q][r]);
  if(a==0)
    return 1;
  else
   return 0;
 }
int constant(int m[][][],int p,int q,int r)
 { 
  int a=m[(p-1)*16][q][r];
  for(int i=1;i<16;i++)
      {if(a!=m[i+(p-1)*16][q][r])
         return 0;
      }
  
  return 1;
 }
String property(int m[][][],int p,int q,int r)
  {
   aesattack ob=new aesattack();
   if(ob.constant(m,p,q,r)==1)
      return "C";
   else
    if(ob.all(m,p,q,r)==1)
      return "A";
   else
    if(ob.balance(m,p,q,r)==1)
      return "B";
   else
      return "U";

  }

void print(int a[][][])
 {
      aesattack ob=new aesattack();
   for(int i=1;i<=1;i++)
       {
         System.out.println("P"+i+"-----");
         System.out.println(ob.property(a,i,0,0)+" "+ob.property(a,i,0,1));
         System.out.println(ob.property(a,i,1,0)+" "+ob.property(a,i,1,1));
       }  
}
int[][][] addroundkey(int a[][][],int r[][])
     {
        for(int i=0;i<256;i++)
          {a[i][0][0]=((a[i][0][0])^(r[0][0]))%16;
          a[i][0][1]=((a[i][0][1])^(r[0][1]))%16;
          a[i][1][0]=((a[i][1][0])^(r[1][0]))%16;
          a[i][1][1]=((a[i][1][1])^(r[1][1]))%16;}
      return a;
      }
int[][][] subbytes(int a[][][])
     {
        for(int i=0;i<256;i++)
          {
           a[i][0][0]=s[a[i][0][0]];
           a[i][0][1]=s[a[i][0][1]];
           a[i][1][0]=s[a[i][1][0]];
           a[i][1][1]=s[a[i][1][1]];
          }
      return a;
      }
int[][][] shiftrow(int a[][][])
     {
        for(int i=0;i<256;i++)
          {
            int t=a[i][1][0];
            a[i][1][0]=a[i][1][1];
            a[i][1][1]=t;
          }
      return a;
      }
int op4(int p)
 {
   if(p<=7)
       p=p*2;
   else
     p=((p%8)*2)^(3);
   if(p<=7)
       p=p*2;
   else
     p=((p%8)*2)^(3);
  return p;
 }

int[][][] mixcolumns(int a[][][])
     {
      aesattack ob=new aesattack();
      int mc[][]=new int[2][2];
      mc[0][0]=4;mc[0][1]=1;mc[1][0]=1;mc[1][1]=4;
        for(int i=0;i<256;i++)
          {
             int w=(ob.op4(a[i][1][0]))^(a[i][0][0]);
	     a[i][1][0]=(ob.op4(a[i][0][0]))^(a[i][1][0]);
             a[i][0][0]=w;
             w=(ob.op4(a[i][1][1]))^(a[i][0][1]);
             a[i][1][1]=(ob.op4(a[i][0][1]))^(a[i][1][1]);
             a[i][0][1]=w;
          }
      return a;
      }

void printmat(int a[][][])
{
 for(int i=0;i<16;i++)
 {
  System.out.println(a[i][0][0]+"	"+a[i][1][0]);
  //System.out.println(a[i][1][0]+" "+a[i][1][1]);
 }
   System.out.println();
}
 public static void main(String args[])throws IOException
    {
      aesattack ob=new aesattack();
      int a[][][]=new int[256][2][2];
      int b[][][]=new int[256][2][2];
        for(int i=0;i<256;i++)
          for(int j=0;j<2;j++)
            for(int k=0;k<2;k++)
              b[i][j][k]=20;
      int[][] s0={{1,2},{3,4}} , s1={{3,4},{5,6}} ,s2={{5,6},{7,8}} ,s3={{7,8},{9,10}},s4={{9,10},{11,12}},s5={{11,12},{13,14}},s6={{13,14},{15,16}}, s7={{1,2},{3,4}} ;
   s[0]=9;s[1]=4;s[2]=10;s[3]=11;s[4]=13;s[5]=1;s[6]=8;s[7]=5;s[8]=6;s[9]=2;s[10]=0;s[11]=3;s[12]=12;s[13]=14;s[14]=15;s[15]=7;//sbox
      
      for(int i=0;i<256;i++)
       {
        a[i][0][0]=i%16;   //a00 has All property for 16 plaintexts in each set
        a[i][1][0]=0;//a10 is c
        a[i][0][1]=1;// a01 is c 
        a[i][1][1]=(i%16+i/16)%16;//a11 has all property for 16 plaintexts in each set
       }
     ob.print(a);
//ROUND 0
System.out.println("ROUND 0 -----");
    a=ob.addroundkey(a,s4);
      a=ob.subbytes(a);
      a=ob.shiftrow(a);
     // ob.print(a);//
      a=ob.mixcolumns(a);
      ob.print(a);
//  Higher order integral
    for(int i=0;i<256;i++)
      {
         int t=a[i][1][0];
         int p=a[i][0][0];
       //System.out.println(p+" "+t);
        if(b[p+t*16][0][0]!=20) 
             {System.out.println("Higher order cannot be applied");return;}
         b[p+t*16][0][0]=a[i][0][0];b[p+t*16][0][1]=a[i][0][1];b[p+t*16][1][0]=a[i][1][0];b[p+t*16][1][1]=a[i][1][1];


          
        }
    ob.print(b);
a=b;
  //ROUND 1
System.out.println("ROUND 1 -----");
      a=ob.addroundkey(a,s0);
      a=ob.subbytes(a);
      a=ob.shiftrow(a);
      a=ob.mixcolumns(a);
      ob.print(a);//
  //ROUND 2
System.out.println("ROUND 2 -----");
      a=ob.addroundkey(a,s1);
      a=ob.subbytes(a);
      a=ob.shiftrow(a);
      a=ob.mixcolumns(a);
      ob.print(a);
  //ROUND 3
System.out.println("ROUND 3 -----");
      a=ob.addroundkey(a,s2);
      a=ob.subbytes(a);
      a=ob.shiftrow(a);
      a=ob.mixcolumns(a);
      ob.print(a);

 
   }
}
