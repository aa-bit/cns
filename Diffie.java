package demo;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class demo
{
    public static void findPrimeFactors(HashSet<Integer> s,int n)
    {
        while(n%2==0)
        {
            s.add(2);
            n/=2;
        }
        for(int i=3;i<=Math.sqrt(n);i+=2)
        {
            while(n%i==0)
            {
                s.add(i);
                n/=i;
            }            
        }
        if(n>2)
            s.add(n);
    }
    public static int remainder(int x,int y,int n)
    {
        BigInteger x1 = new BigInteger(x+"");
        BigInteger y1 = new BigInteger(y+"");
        BigInteger n1 = new BigInteger(n+"");
        return x1.modPow(y1, n1).intValue();
    }
    public static void findPrimitiveRoot(HashSet<Integer> set,int n)
    {
        HashSet<Integer> s = new HashSet<Integer>();
        int phi = n-1;
        findPrimeFactors(s, phi);
        for(int i=2;i<=phi;i++)
        {
           boolean flag = false;
           for(Integer a:s)
           {
               if(remainder(i, phi/a, n)==1)
               {
                   flag = true;
                   break;
               }
           }
           if(flag==false)
           {
               set.add(i);
           }
        }
    }
    public static void main(String[] args)
    {
        HashSet<Integer> primitiveRootSet = new HashSet<Integer>();
        BigInteger p = new BigInteger(37+"");
        BigInteger pub_a,pub_b,priv_a,priv_b,key_a,key_b;
        findPrimitiveRoot(primitiveRootSet, p.intValue());
        /*
        This is for printing Each element
        for(Integer a:primitiveRootSet)
        {
            System.out.println(a);
        }*/
        //For randomingly picking a element No need for this
        List asList = new ArrayList(primitiveRootSet);
        Collections.shuffle(asList);
        BigInteger g=BigInteger.valueOf((int)asList.get(0));
        Scanner s=new Scanner(System.in);
        String inp="";
        System.out.println("P value(prime): "+p);
        System.out.println("Primitive root: "+g);
        System.out.println("Enter the private key of A:(less than P)");
        inp=s.nextLine();
        priv_a=new BigInteger(inp);
        System.out.println("Enter the private key of B:(less than P)");
        inp=s.nextLine();
        priv_b=new BigInteger(inp);
       
        pub_a=g.modPow(priv_a, p);
        pub_b=g.modPow(priv_b, p);
       
        System.out.println("Computed Public key of A:"+pub_a);
        System.out.println("Computed Public key of B:"+pub_b);
       
        key_a=pub_b.modPow(priv_a, p);
        key_b=pub_a.modPow(priv_b, p);
        System.out.println("The shared secret key(K): "+key_a);
        System.out.println("The shared secret key(K): "+key_b);
        return;
    }
}