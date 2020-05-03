/*
EXTENDIBLE HASHING (BINARY)
AUTHOR---JUHEE GANDHI

*/
package advdatastructureshashing;
import java.util.Arrays;
import java.util.Scanner;
public class hashingextendiable {
    public static void main(String[] args) {
        int index,gainfactor,count;
        String binval[];
        int[] input;
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter number of data values ");
        count=sc.nextInt();
        input=new int[count];
        System.out.println("Enter data ");
        for(int i=0;i<count;i++){
            input[i]=sc.nextInt();
        }
        System.out.println("Enter the index size ");
        index=sc.nextInt();
        System.out.println("Enter the gain factor ");
        gainfactor=sc.nextInt();
        System.out.println("-----HASH TABLE-----");
        extendhash eh=new extendhash(index,gainfactor,input);
    }
}
class extendhash{
    int index,gainfactor,cnt=0;
    String[] binval;
    String[] binlast2,binlast3;
    String finaltab[][];
    int input[],input1[],inporg[];
    public extendhash(int index,int gainfactor,int input[]){
           this.gainfactor=gainfactor;
           this.index=index;
           input=input;
           input1=new int[input.length];
           inporg=new int[input.length];
           binval=new String[input.length];
           binlast2=new String[input.length];
           binlast3=new String[input.length];
           for(int l=0;l<input.length;l++){
            inporg[l]=input[l];
            input[l]=input[l]%64;
            input1[l]=input[l];  
        }
        hash(input,input1);
    }
    void hash(int input[],int input1[]){
       Arrays.sort(input1);
        for(int i=0;i<input.length;i++){
            if((Integer.toBinaryString(input[i])).length()==Integer.toBinaryString(input1[input1.length-1]).length()){
                  binval[i]=Integer.toBinaryString(input[i]);
            }
            else{
                int count=Integer.toBinaryString(input[i]).length();
                while(count!=Integer.toBinaryString(input1[input1.length-1]).length())
                    count++;
                binval[i] =(String) String.format("%0" + count +"d",Integer.parseInt(Integer.toBinaryString(input[i])));
            }
            binlast2[i]=binval[i].substring(binval[i].length()-gainfactor);
            binlast3[i]=binval[i].substring(binval[i].length()-gainfactor-1);
        }
            int k,m=0;
            finaltab=new String[(int)Math.pow(2,gainfactor)][index];
            for(k=0;k<Math.pow(2, gainfactor);k++){
                m=0;
            for(int in=0;in<input.length;in++){
                if(k==Integer.parseInt(binlast2[in],2)){
                    if(m==index){
                      gainfactor++;
                      k=0;m=0;        
                finaltab=new String[(int)Math.pow(2,gainfactor)][index];
            for(k=0;k<Math.pow(2, gainfactor);k++){
              m=0;
              for( in=0;in<input.length;in++){
                if(k==Integer.parseInt(binlast3[in],2)){
                    finaltab[k][m]=String.valueOf(inporg[in]);
                    m++;
              }
            }
          }
                  }
                  else{
                      finaltab[k][m]=String.valueOf(inporg[in]);
                      m++;
                }
              }
            }
          }
       
            for(int x=0;x<Math.pow(2,gainfactor);x++){
                System.out.print(x+" ");
                for(int z=0;z<index;z++)
                  System.out.print(" "+finaltab[x][z]);
                System.out.println();
              }
        
    }
}
