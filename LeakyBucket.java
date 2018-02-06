package org.other;

import java.util.Scanner;

public class LeakyBucket 
{
	public static void main(String[] args)
	{
		int bs, bucket_size, output_rate, n;
		int[] packets = new int[20];
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter number of packets: ");
		n = scan.nextInt();
		System.out.println("Enter the packets: ");
		for(int i=0; i<n; i++)
			packets[i] = scan.nextInt();
		System.out.println("Enter the bucket size: ");
		bs = scan.nextInt();
		System.out.println("Enter the output rate: ");
		output_rate = scan.nextInt();
		
		bucket_size = 0;
		for(int i=0; i<n; i++)
		{
			bucket_size+=packets[i];
			System.out.println("\nBucket size: "+bucket_size);
			if(bucket_size>bs)
			{
				bucket_size -= packets[i];
				System.out.println("Packet "+packets[i]+" was dropped due to insufficient buffer space");
				System.out.println("\nBucket size: "+bucket_size);
				bucket_size -= output_rate;
				System.out.println("Bucket Size after output flow: "+bucket_size);
			}
			else
			{
				bucket_size -= output_rate;
				System.out.println("Bucket Size after output flow: "+bucket_size);
			}
		}
		System.out.println("\n");
		while(bucket_size!=0)
		{
			bucket_size -= output_rate;
			System.out.println("Bucket Size after output flow: "+bucket_size);
		}
		System.out.println("Bucket empty!!!");
		scan.close();
	}
	
}
