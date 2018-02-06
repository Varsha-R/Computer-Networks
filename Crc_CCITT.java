package org.other;

import java.util.*;

public class Crc_CCITT {

	static Scanner scan = new Scanner(System.in);
	static int[] dataword = new int[20];
	static int[] message = new int[20];
	static int[] generator = new int[20];
	static int[] codeword = new int[20];
	static int[] check = new int[20];
	static int[] transmitted_message = new int[20];
	static int n_dataword, n_message, n_generator, n_copy, count;
	
	public static void main(String[] args)
	{
		
		System.out.println("Enter number of bits to be transmitted: ");
		n_dataword = scan.nextInt();
		System.out.println("Enter the dataword: ");
		for(int i=0; i<n_dataword; i++)
			dataword[i] = scan.nextInt();
		
		for(int i=0; i<dataword.length; i++)
			message[i] = dataword[i];
		for(int i=n_dataword; i<(n_dataword+16); i++)
			message[i] = 0;
		n_message = message.length;
		
		System.out.println("Enter number of bits in generator: ");
		n_generator = scan.nextInt();
		System.out.println("Enter the generator: ");
		for(int i=0; i<n_generator; i++)
			generator[i] = scan.nextInt();
		
		count = n_message - n_generator;
		
		System.out.println("Appended message/dataword:");
		for(int i=0; i<n_dataword+16; i++)
			System.out.print(message[i]);
		System.out.println("\nGenerator: ");
		for(int i=0; i<n_generator; i++)
			System.out.print(generator[i]);
		
		crc_codewordGenerator();
		crc_check();
	}
	
	public static void crc_codewordGenerator()
	{
		for(int i=0; i<n_message; i++)
			message[i] = message[i]^generator[i];
		n_copy = n_message-1;
		for(int i=0; i<n_copy; i++)
			message[i] = message[i+1];
		
		for(int i=0; i<count; i++)
		{
			if(message[0]==0)
			{
				for(int k=0; k<n_copy; k++)
					message[k] = message[k+1];
			}
			else
			{
				for(int k=0; k<n_copy; k++)
					message[k] = message[k]^generator[k];
			}
			n_copy-=1;
		}
		
		for(int i=0; i<n_dataword; i++)
			codeword[i] = dataword[i];
		for(int i=n_dataword, j=1; i<(n_dataword+16); i++, j++)
			codeword[i]=message[j];
		
		System.out.println("\nCodeword: ");
		for(int i=0; i<(n_dataword+16); i++)
			System.out.print(codeword[i]);
	}
	
	public static void crc_check()
	{
		System.out.println("\nEnter "+(n_dataword+16)+" bit of transmitted message:");
		for(int i=0; i<(n_dataword+16); i++)
			transmitted_message[i] = scan.nextInt();
		
		
		for(int i=0; i<(n_dataword+16); i++)
			check[i] = codeword[i]^transmitted_message[i];
		
		for(int i=0; i<(n_dataword+16); i++)
			if(check[i]!=0)
			{
				System.out.println("Error Detected!!!");
				System.exit(0);
			}
		System.out.println("No Error!!!");
	}
	
}

//Number of bits in generator must be 17 and generator of CRC-CCITT is 10001000000100001
