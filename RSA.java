package org.other;

import java.util.Scanner;
import java.io.*;

public class RSA {
	
	static int gcd(long m, long n)
	{
		int r;
		while(n!=0)
		{
			r = (int) (m%n);
			m=n; 
			n=r;
		}
		return (int)m;
	}

	
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter message to be encrypted: ");
		try 
		{
			String message = br.readLine();
			int size = message.length();
			char[] msg = new char[size];
			int[] enc = new int[size];
			int[] dec = new int[size];
			int p=0, q=0, n=0, e=0, d=0, phi=0,i, j, k;
			int sizes = msg.length;
			
			for(i=0; i<sizes; i++)
				msg[i] = message.charAt(i);
			System.out.println("Message to be encrypted is: ");
			for(i=0; i<sizes; i++)
				System.out.print(msg[i]);
			
			System.out.println("\nEnter values of p and q: ");
			p = scan.nextInt();
			q = scan.nextInt();
			n = p*q;
			phi = (p-1)*(q-1);
			
			for( i=2; i<phi; i++)
			{
				if(gcd(i, phi)==1)
					break;
			}
			e = i;
			
			for( k=2; k<phi; k++)
			{
				if((e*k-1)%phi==0)
					break;
			}
			d = k;
			
			System.out.println("Value of e: "+e+" \nValue of d: "+d);
			
			int[] num = new int[size];
			for(int x=0; x<sizes; x++)
				num[x] = (int)msg[x];
			
			for(i=0; i<sizes; i++)
			{
				enc[i] = 1;
				for(j=0; j<e; j++)
					enc[i] = (enc[i]*num[i])%n;
			}
			
			for(i=0; i<sizes; i++)
			{
				dec[i] = 1;
				for(j=0; j<d; j++)
					dec[i] = (dec[i]*enc[i])%n;
			}
			
			System.out.println("\nEncrypted message: ");
			for(i=0; i<sizes; i++)
				System.out.print(enc[i]);
			
			System.out.println("\nDecrypted message: ");
			for(i=0; i<sizes; i++)
				System.out.print((char)num[i]);
		} 
		catch (IOException e) { }
		scan.close();
	}
	
	
}
