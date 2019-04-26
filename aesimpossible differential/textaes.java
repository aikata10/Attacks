import java.io.*;
import java.util.*;
import java.util.Random;
class textaes
 {
     static int[] s=new int[16];
      static int[] is=new int[16];


void print(int a[][])
 {

         System.out.println("P-----");
         System.out.println(a[0][0]+" "+a[0][1]);
         System.out.println(a[1][0]+" "+a[1][1]);

}
int[][] addroundkey(int a[][],int r[][])
     {
        a[0][0]=((a[0][0])^(r[0][0]))%16;
          a[0][1]=((a[0][1])^(r[0][1]))%16;
          a[1][0]=((a[1][0])^(r[1][0]))%16;
          a[1][1]=((a[1][1])^(r[1][1]))%16;
      return a;
      }
int[][] invsubbytes(int a[][])
     {
        is[0]=10;is[1]=5;is[2]=9;is[3]=11;is[4]=1;is[5]=7;is[6]=8;is[7]=15;is[8]=6;is[9]=0;is[10]=2;s[11]=3;s[12]=12;s[13]=4;s[14]=13;s[15]=15;//invsbox
           a[0][0]=is[a[0][0]];
           a[0][1]=is[a[0][1]];
           a[1][0]=is[a[1][0]];
           a[1][1]=is[a[1][1]];
          
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
   if(p<=7)
       p=p*2;
   else
     p=((p%8)*2)^(3);
  return p;
 }

int[][] invmixcolumns(int a[][])
     {
      int mc[][]=new int[2][2];
      int b[][]=new int[2][2];
      mc[0][0]=1;mc[0][1]=12;mc[1][0]=12;mc[1][1]=1;
      
           for(int j=0;j<2;j++)
         {
          for(int k=0;k<2;k++)
         {
          for(int l=0;l<2;l++)
           { b[j][k]=((b[j][k])+(((mc[j][l])%16)*a[l][k]))%16;

           }}}
         //}
            
        //  }
     // return b;

           //  int w=(ob.op(a[1][0],3))^(ob.op(a[1][0],2))^(a[0][0]);
//	     a[1][0]=(ob.op(a[0][0],3))^(ob.op(a[0][0],2))^(a[1][0]);
  //           a[0][0]=w;
    //         w=(ob.op(a[1][1],3))^(ob.op(a[1][1],2))^(a[0][1]);
      //       a[1][1]=(ob.op(a[0][1],3))^(ob.op(a[0][1],2))^(a[1][1]);
        //     a[0][1]=w;
          
return b;
      }

int[][] mixcolumns(int a[][])
     {
      int mc[][]=new int[2][2];
      mc[0][0]=1;mc[0][1]=4;mc[1][0]=4;mc[1][1]=1;
int b[][]=new int[2][2];
    
      
           for(int j=0;j<2;j++)
         {
          for(int k=0;k<2;k++)
         {
          for(int l=0;l<2;l++)
           { b[j][k]=((b[j][k])+(((mc[j][l])%16)*a[l][k]))%16;

           }}}
        
       //      int w=(ob.op(a[1][0],2))^(a[0][0]);
	 //    a[1][0]=(ob.op(a[0][0],2))^(a[1][0]);
           //  a[0][0]=w;
             //w=(ob.op(a[1][1],2))^(a[0][1]);
            // a[1][1]=(ob.op(a[0][1],2))^(a[1][1]);
             //a[0][1]=w;
          
      return b;
      }


 public static void main(String args[])throws IOException
    {
int k[]=new int[256];
 for(int i=0;i<256;i++)
   k[i]=1;
int ctr=0;
textaes ob=new textaes();
for(int h1=1;h1<16;h1++)
for(int h2=1;h2<16;h2++)
{
//int h1=1;int h2=10;

int[][] s0={{10,15},{2,9}} , s1={{14,15},{2,5}}  ,s2={{14,15},{2,5}}  ,s3={{14,15},{2,5}} ,s4={{14,15},{2,5}} ,s5={{14,15},{2,5}} ,s6={{14,15},{2,5}} ; 
s[0]=9;s[1]=4;s[2]=10;s[3]=11;s[4]=13;s[5]=1;s[6]=8;s[7]=5;s[8]=6;s[9]=2;s[10]=0;s[11]=3;s[12]=12;s[13]=14;s[14]=15;s[15]=7;//sbox
is[0]=10;is[1]=5;is[2]=9;is[3]=11;is[4]=1;is[5]=7;is[6]=8;is[7]=15;is[8]=6;is[9]=0;is[10]=2;s[11]=3;s[12]=12;s[13]=4;s[14]=13;s[15]=15;//invsbox
int f[]=new int[32768];
int a[][][]=new int[32768][2][2];int c[][][]=new int[32768][2][2];
int b[][]=new int[2][2];int d[][]=new int[2][2];
int q[][]=new int [2][2]; int w[][]=new int [2][2];
int r=1;
for(int i=0;i<32768;i++)
       {
         
        a[i][1][1]=i%16;   //a00 has All property for 16 plaintexts in each set
        a[i][1][0]=(i/16)%16;//a10 is c
        a[i][0][1]=(i/256)%16;// a01 is c 
        a[i][0][0]=(i/(256*16))%16;//a11 has all property for 16 plaintexts in each set
        c[i][0][0]=(a[i][0][0])^(h1);   //a00 has All property for 16 plaintexts in each set
        c[i][1][0]=a[i][1][0];//a10 is c
        c[i][0][1]=a[i][0][1];// a01 is c 
        c[i][1][1]=(a[i][1][1])^(h2);
      }
for(int i=0;i<32768;i++)
  {

     
  //ROUND 1
      b[0][0]=a[i][0][0];b[0][1]=a[i][0][1];b[1][0]=a[i][1][0];b[1][1]=a[i][1][1];
      d[0][0]=c[i][0][0]; d[0][1]=c[i][0][1]; d[1][0]=c[i][1][0]; d[1][1]=c[i][1][1];

//if(i==17401)
 // { ob.print(b);      ob.print(d);}

      b=ob.mixcolumns(ob.shiftrow(ob.subbytes(ob.addroundkey(b,s0))));
      d=ob.mixcolumns(ob.shiftrow(ob.subbytes(ob.addroundkey(d,s0))));

 //if(i==17401)
  //{ ob.print(b);      ob.print(d);}
 //ROUND 2

      b=ob.mixcolumns(ob.shiftrow(ob.subbytes(ob.addroundkey(b,s1))));
      d=ob.mixcolumns(ob.shiftrow(ob.subbytes(ob.addroundkey(d,s1))));
 

//ROUND 3

      b=ob.mixcolumns(ob.shiftrow(ob.subbytes(ob.addroundkey(b,s2))));
      d=ob.mixcolumns(ob.shiftrow(ob.subbytes(ob.addroundkey(d,s2))));
     // ob.print(b);      ob.print(d);

//ROUND 4

      b=ob.mixcolumns(ob.shiftrow(ob.subbytes(ob.addroundkey(b,s3))));
      d=ob.mixcolumns(ob.shiftrow(ob.subbytes(ob.addroundkey(d,s3))));
     // ob.print(b);      ob.print(d);

//ROUND 5

            b=ob.addroundkey(ob.shiftrow(ob.subbytes(ob.addroundkey(b,s4))),s5);
      d=ob.addroundkey(ob.shiftrow(ob.subbytes(ob.addroundkey(d,s4))),s5);
//if(i==17401)
  //   { ob.print(b);      ob.print(d);}

if(((b[0][0])^(d[0][0]))==0 && ((b[1][1])^(d[1][1]))==0)
    {

for(int m=0;m<16;m++){
 for(int n=0;n<16;n++){
  int ss[][]=new int[2][2];
      ss[0][0]=m;ss[0][1]=0;ss[1][0]=0;ss[1][1]=n;
      q[0][0]=a[i][0][0];q[0][1]=a[i][0][1];q[1][0]=a[i][1][0];q[1][1]=a[i][1][1];
      w[0][0]=c[i][0][0]; w[0][1]=c[i][0][1]; w[1][0]=c[i][1][0]; w[1][1]=c[i][1][1];
     
    q=ob.mixcolumns(ob.shiftrow(ob.subbytes(ob.addroundkey(q,ss))));
    w=ob.mixcolumns(ob.shiftrow(ob.subbytes(ob.addroundkey(w,ss))));
   
    //r System.out.println(" Ciphertexts ");ob.print(b);ob.print(d);System.out.println(" PLaintexts "+i);ob.print(a[i]);ob.print(c[i]);    
        if(((q[0][0])^(w[0][0]))!=0 && q[1][1]==w[1][1] && q[0][1]==w[0][1] && q[1][0]==w[1][0])
                 { k[m*16+n]=0;
                     if(m==10 && n==9 ){
                           System.out.println("no");ob.print(a[i]);ob.print(c[i]);ob.print(q);ob.print(w);ob.print(b);ob.print(d);}}
     }}}
}

}
for(int i=0;i<256;i++)
   { if(k[i]!=0)
       {
         System.out.println(i);
         ctr++;
       }

   }
System.out.println("   "+ctr);

}
}



 
