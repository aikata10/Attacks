import java.io.*;
import java.util.*;
import java.util.Random;
class aesddt
 {
public static void main(String args[])throws IOException
    {


int Table[][]=new int[16][16]; // the table, In is the XOR of the in-going pair, Out is the resulting XOR, the table returns the number of occurences
int[] s=new int[16];
s[0]=9;s[1]=4;s[2]=10;s[3]=11;s[4]=13;s[5]=1;s[6]=8;s[7]=5;s[8]=6;s[9]=2;s[10]=0;s[11]=3;s[12]=12;s[13]=14;s[14]=15;s[15]=7;//sbox
      
// Initialize the table:
for(int in = 0;in<16;in++) 
{
  for(int out = 0;out<16;out++)
  {
    Table[in][out] = 0;
  }
}

// this makes us go through all the possible value of p1
for(int p1 = 0;p1<16;p1++) 
{
  // this makes us go through all the possible value of p2
  for(int p2 = 0;p2<16;p2++)
  {
    int XOR_IN = (p1)^(p2);
    int XOR_OUT = (s[p1]) ^(s[p2]);
    Table[XOR_IN][XOR_OUT]++;
  }
} 
for(int p1 = 0;p1<16;p1++) 
  System.out.print("	"+p1);
System.out.println();

for(int p1 = 0;p1<16;p1++) 
{System.out.print(p1+"	");
  // this makes us go through all the possible value of p2
  for(int p2 = 0;p2<16;p2++)
  {
    System.out.print(Table[p1][p2]+"	");
  }
System.out.println();
} 

}
}
