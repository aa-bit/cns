/*package whatever //do not write package name here */

import java.io.*;
import java.util.Scanner;

class GFG {
	public static void main (String[] args) {
	    
	    String x,y;
	    int g=0,u=0,h=0,v=0,flag1=0,s=0;
	    int alphacheck=0,check;
	    int strcount=0,i,j,k,m=97;
       int[][] matrix = new int[10][10];
	     Scanner scan = new Scanner(System.in);
	    x=scan.nextLine();
	    y=scan.nextLine();
	    
	    StringBuilder sb= new StringBuilder(y);
	    char[] str=x.toCharArray();
	        
	        for(j=0;j<5;j++)
	        {
	            for(k=0;k<5;k++) 
	            { 
	                if(strcount<x.length())
	                {
	                    matrix[j][k]=str[strcount];
	                    strcount++;
	                }
	                else{
	                    for(check=0;check<x.length();check++)
	                    {
	                        if(m==(int)str[check])
	                        {
	                           
	                            m++;
	                           check=0;

	                        }

	                    }
	                     
	                        	matrix[j][k]=m;
	                        	m++;
	                        

	                }
	            }
	            
	        }
	        
	   
	for (i=0;i<sb.length(); )
	{
	    if(sb.charAt(i)==sb.charAt(i+1))
	    {
	       sb.insert(i+1,'x'); 
	       i=i+2;
	    }
	    else{
	        i=i+2;
	    }
	} 
	if((sb.length()%2)!=0)
	{
	    sb.append('x');
	}
	    
	 System.out.println(sb);   
	       
	        for(j=0;j<5;j++)
	        {
	            for(k=0;k<5;k++) 
	            {
	               System.out.print((char)matrix[j][k]+" "); 
	            }
	            
	               System.out.println("\n");
	        }
	         System.out.println(sb.length());
    for (i=0;i<sb.length(); i+=2)
	    {
	       System.out.print(sb.charAt(i));
	            s=i+1;
	           System.out.print(sb.charAt(s));
	       // ////////////
	        for(j=0;j<5;j++) 
	        {
	            for(k=0;k<5;k++) 
	            {
	                if((char)matrix[j][k]==sb.charAt(i))
	                {g=j;
	                u=k;
	                flag1=1;
	                System.out.print((char)matrix[j][k]);
	                break;}
	            }
	            if(flag1==1)
	            { //System.out.print(sb.charAt(i)); 
	                break;
	            }
	        }
	       
	        ///////////
	        
	         for(j=0;j<5;j++) 
	        {
	            for(k=0;k<5;k++) 
	            {
	                if((char)matrix[j][k]==sb.charAt(i+1))
	                {h=j;
	                v=k;
	                flag1=1;
	                System.out.print((char)matrix[j][k]);
	                break;}
	            }
	            if(flag1==1)
	            { //System.out.print(sb.charAt(s)); 
	                break;
	            }
	        }
	        
	        if(g==h || u==v)
	        {
	            if(g==h)
	            {
	               u+=1;
	               v+=1;
	               if(u>4)
	               {
	                  u=u-5;
	               }
	               if(v>4)
	               {
	                  v=v-5;
	               }
	                 sb.setCharAt(i, (char)matrix[g][u]);
	                 sb.setCharAt(i+1,(char)matrix[h][v]);
	            }
	            if(u==v)
	            {
	                 g+=1;
	               h+=1;
	                System.out.print(g+""+u+""+h+""+v);
	               if(g>4)
	               {
	                  g=g-5;
	               }
	               if(h>4)
	               {
	                  h=h-5;
	               }
	                sb.setCharAt(i, (char)matrix[g][u]);
	                sb.setCharAt(i+1,(char)matrix[h][v]);
	            }
	        }
	        else{
	        sb.setCharAt(i, (char)matrix[g][v]);
	        sb.setCharAt(i+1,(char)matrix[h][u]);
	        }
	        
	        
	        ///////////
	    } 
	    
	    
	  System.out.println("\n"+sb);  
	}
}