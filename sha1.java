/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sha1;

import java.util.Scanner;

/**
 *
 * @author rupesh
 */
public class Sha1 {

    /**
     * @param args the command line arguments
     */
    public static byte[] compute_sha1(byte[] message)
    {
        int messageLenByte = message.length;
        int noOfBlock = ((messageLenByte+8)>>>6)+1;
        int totalLen = noOfBlock << 6;
        byte[] paddingBytes = new byte[totalLen-messageLenByte];
        paddingBytes[0] = (byte)0x80;
        long messageLenBits = (long) messageLenByte<<3;
        for(int i=7;i>=0;i--)
        {
            paddingBytes[paddingBytes.length-8+i] = (byte) messageLenBits;
            messageLenBits >>>= 8;
        }
        int a = 0x67452301;
        int b = (int) 0xEFCDAB89L;
        int c = (int) 0x98BADCFEL;
        int d = 0x10325476;
        int e = (int) 0xC3D2E1F0L;
        int[] buffer = new int[16];
        byte mergedArray[] = new byte[totalLen];
        System.arraycopy(message, 0, mergedArray, 0, messageLenByte);
        System.arraycopy(paddingBytes, 0, mergedArray, messageLenByte, paddingBytes.length);

        for(int i=0;i<noOfBlock;i++)
        {
            for(int j=0;j<16;j++)
                buffer[j] = (int)mergedArray[4*j]<<24|(mergedArray[4*j+1]<<24)>>>8|(mergedArray[4*j+2]<<24)>>>16
                        |(mergedArray[4*j+3]<<24)>>>24;
            int[] m = new int[80];
            int orgA = a,orgB = b,orgC = c,orgD = d,orgE = e;
            for(int j=0;j<80;j++)
            {
                int div20 = j/20;
                int f=0,k=0;
                if(j<16)
                    m[j] = buffer[j];
                else
                    m[j] = Integer.rotateLeft(m[j-3]^m[j-8]^m[j-14]^m[j-16], 1);
                switch(div20)
                {
                    case 0:
                        f = (b&c) | (~b&d);
                        k = 0x5A827999;
                        break;
                    case 1:
                        f = b^c^d;
                        k = 0x6ED9EBA1;
                        break;
                    case 2:
                        f = (b&c) | (b&d) | (c&d);
                        k = 0x8F1BBCDC;
                        break;
                    case 3:
                        f = b^c^d;
                        k = 0xCA62C1D6;
                        break;
                }
                int temp = e + f + Integer.rotateLeft(a, 5) + m[j] +k;
                e=d;
                d=c;
                c=Integer.rotateLeft(b, 30);
                b=a;
                a=temp;
            }
            a += orgA;
            b += orgB;
            c += orgC;
            d += orgD;
            e += orgE;
        }
        byte[] sha1 = new byte[20];
        int count =0 ;
        for(int i=0;i<5;i++)
        {
            int n=(i==0)?a:(i==1)?b:(i==2)?c:(i==3)?d:e;
            for(int j=0;j<4;j++)
            {
                sha1[(count/4)*4 + (3-(count%4))] = (byte) n;
                count++;
                n >>>=8;
            }
        }
        return sha1;
    }
    public static String toHexString(byte[] b)
  {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < b.length; i++)
    {
      sb.append(String.format("%02x", b[i] & 0xFF));
    }
    return sb.toString();
  }
 
    public static void main(String[] args) {
        // TODO code application logic here
        String text;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the text:");
        text = input.nextLine();
        byte[] sha1 = new byte[20];
        sha1 = compute_sha1(text.getBytes());
        System.out.println(toHexString(sha1));
        
    }
    
}