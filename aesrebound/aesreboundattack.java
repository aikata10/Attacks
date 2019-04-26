import java.io.*;
import java.util.*;
import java.util.Random;
class aesreboundattack
 {
     static int[] s=new int[16];
     static int[] is=new int[16];
static int[][] s0={{1,2},{3,4}} , s1={{3,4},{5,6}} ,s2={{5,6},{7,8}} ,s3={{7,8},{9,10}},s4={{9,10},{11,12}},s5={{11,12},{13,14}},s6={{13,14},{15,16}}, s7={{1,2},{3,4}} ;
      


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
int[][][] invsubbytes(int a[][][])
     {
        for(int i=0;i<256;i++)
          {
           a[i][0][0]=is[a[i][0][0]];
           a[i][0][1]=is[a[i][0][1]];
           a[i][1][0]=is[a[i][1][0]];
           a[i][1][1]=is[a[i][1][1]];
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
int op(int p,int j)
 {
 for(int i=0;i<j;i++)
   if(p<=7)
       p=p*2;
   else
     p=((p%8)*2)^(3);
  return p;
 }

int[][][] mixcolumns(int a[][][])
     {
      aesreboundattack ob=new aesreboundattack();
      int mc[][]=new int[2][2];
      mc[0][0]=1;mc[0][1]=4;mc[1][0]=4;mc[1][1]=1;
        for(int i=0;i<256;i++)
          {
             int w=(ob.op(a[i][1][0],2))^(a[i][0][0]);
	     a[i][1][0]=(ob.op(a[i][0][0],2))^(a[i][1][0]);
             a[i][0][0]=w;
             w=(ob.op(a[i][1][1],2))^(a[i][0][1]);
             a[i][1][1]=(ob.op(a[i][0][1],2))^(a[i][1][1]);
             a[i][0][1]=w;
          }
      return a;
      }
int[][][] invmixcolumns(int a[][][])
     {
      aesreboundattack ob=new aesreboundattack();
      int mc[][]=new int[2][2];
      int b[][][]=new int[256][2][2];
      mc[0][0]=1;mc[0][1]=12;mc[1][0]=12;mc[1][1]=1;
       // for(int i=0;i<256;i++)
       //   { for(int j=0;j<2;j++)
       //  {
        //  for(int k=0;k<2;k++)
          // {
            // for(int l=0;l<2;l++)
              //  { b[i][j][k]=((b[i][j][k])+((a[i][j][l]*mc[k][l])%16))%16;

         //  }}
         //}
            
        //  }
     // return b;
for(int i=0;i<1;i++)
          {
             int w=(ob.op(a[i][1][0],3))^(ob.op(a[i][1][0],2))^(a[i][0][0]);
	     a[i][1][0]=(ob.op(a[i][0][0],3))^(ob.op(a[i][0][0],2))^(a[i][1][0]);
             a[i][0][0]=w;
             w=(ob.op(a[i][1][1],3))^(ob.op(a[i][1][1],2))^(a[i][0][1]);
             a[i][1][1]=(ob.op(a[i][0][1],3))^(ob.op(a[i][0][1],2))^(a[i][1][1]);
             a[i][0][1]=w;
          }
return a;
      }

void print(int a[][][],int k)
{

  System.out.println((a[k][0][0])+"	"+(a[k][0][1]));
  System.out.println((a[k][1][0])+"	"+(a[k][1][1]));
}
int inbound(int m,int n,int b[][][],int c[][][])
{
aesreboundattack ob=new aesreboundattack();
int ddt[][]={{	16,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0	},
	{	0,	2,	2,	2,	2,	0,	0,	0,	2,	0,	0,	0,	2,	4,	0,	0	},
	{	0,	2,	0,	4,	2,	2,	2,	0,	0,	2,	0,	0,	0,	0,	0,	2	},
	{	0,	2,	4,	0,	0,	2,	0,	0,	2,	2,	0,	2,	0,	0,	2,	0	},
	{	0,	0,	2,	0,	4,	2,	0,	0,	0,	0,	2,	0,	2,	0,	2,	2	},
	{	0,	0,	0,	2,	0,	0,	0,	2,	4,	2,	0,	0,	2,	0,	2,	2	},
	{	0,	4,	0,	0,	0,	2,	0,	2,	0,	2,	2,	0,	2,	2,	0,	0	},
	{	0,	2,	0,	0,	0,	0,	2,	0,	0,	0,	0,	2,	4,	2,	2,	2	},
	{	0,	2,	2,	0,	0,	0,	2,	2,	2,	0,	2,	0,	0,	0,	0,	4	},
	{	0,	0,	2,	2,	0,	0,	0,	0,	0,	2,	2,	4,	0,	2,	0,	2	},
	{	0,	0,	2,	0,	2,	0,	2,	2,	0,	4,	0,	2,	2,	0,	0,	0	},
	{	0,	0,	0,	0,	2,	0,	2,	0,	2,	2,	4,	0,	0,	2,	2,	0	},
	{	0,	0,	0,	2,	0,	4,	2,	0,	2,	0,	2,	2,	2,	0,	0,	0	},
	{	0,	0,	0,	0,	2,	2,	0,	4,	2,	0,	0,	2,	0,	2,	0,	2	},
	{	0,	0,	2,	2,	0,	2,	4,	2,	0,	0,	0,	0,	0,	2,	2,	0	},
	{	0,	2,	0,	2,	2,	0,	0,	2,	0,	0,	2,	2,	0,	0,	4,	0	}};
    b=ob.mixcolumns(b);
    b=ob.addroundkey(b,s2);
    c=ob.invmixcolumns(c);
    c=ob.shiftrow(c);
for(int i=0;i<2;i++)
  for(int j=0;j<2;j++)
     {
       if(ddt[b[m][i][j]][c[n][i][j]]==0)
         return 0;
     }
 return 1;  

}
int outbound(int m,int n,int b[][][],int c[][][])
 { 
  aesreboundattack ob=new aesreboundattack();
  b=ob.invsubbytes(ob.shiftrow(ob.invmixcolumns(ob.addroundkey(ob.invsubbytes(ob.shiftrow(b)),s1))));
  c=ob.addroundkey(ob.mixcolumns(ob.shiftrow(ob.subbytes(ob.addroundkey(c,s3)))),s4);
  if(b[m][0][0]==c[n][0][0] && b[m][0][0]!=0)
    {System.out.println("	difference ->	"+b[m][0][0]);return 1; }
  else
    return 0;

 }
 public static void main(String args[])throws IOException
    {
      aesreboundattack ob=new aesreboundattack();
      int a[][][]=new int[256][2][2];
      int b[][][]=new int[256][2][2];
      int c[][][]=new int[256][2][2];
        for(int i=0;i<256;i++)
          for(int j=0;j<2;j++)
            for(int k=0;k<2;k++)
              b[i][j][k]=20;
      
   s[0]=9;s[1]=4;s[2]=10;s[3]=11;s[4]=13;s[5]=1;s[6]=8;s[7]=5;s[8]=6;s[9]=2;s[10]=0;s[11]=3;s[12]=12;s[13]=14;s[14]=15;s[15]=7;//sbox

is[0]=10;is[1]=5;is[2]=9;is[3]=11;is[4]=1;is[5]=7;is[6]=8;is[7]=15;is[8]=6;is[9]=0;is[10]=2;s[11]=3;s[12]=12;s[13]=4;s[14]=13;s[15]=15;//invsbox
     //int ddt[][]=new int[16][16];
     

      


      for(int i=0;i<256;i++)
       {
        a[i][0][0]=i%16;   //a00 has All property for 16 plaintexts in each set
        a[i][1][0]=0;//a10 is c
        a[i][0][1]=0;// a01 is c 
        a[i][1][1]=(i%16+i/16)%16;;//a11 has all property for 16 plaintexts in each set
       }
for(int i=0;i<256;i++)
       {
        b[i][0][0]=i%16;   //a00 has All property for 16 plaintexts in each set
        b[i][1][0]=0;//a10 is c
        b[i][0][1]=0;// a01 is c 
        b[i][1][1]=(i%16+i/16)%16;;//a11 has all property for 16 plaintexts in each set
       }
for(int i=0;i<256;i++)
       {
        c[i][0][0]=i%16;   //a00 has All property for 16 plaintexts in each set
        c[i][1][0]=0;//a10 is c
        c[i][0][1]=0;// a01 is c 
        c[i][1][1]=(i%16+i/16)%16;;//a11 has all property for 16 plaintexts in each set
       }

int ctr=0;
for(int i=0;i<256;i++)
 {
   for(int j=0;j<256;j++)
    {
      if(ob.inbound(i,j,b,c)==1)
         if(ob.outbound(i,j,b,c)==1)
                    {ob.print(a,i);ob.print(a,j);ctr++;}     
    }
 }
System.out.println("Total collsiions found -> "+ctr);      

}
}


