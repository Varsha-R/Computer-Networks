package org.tcp;

import java.net.*;
import java.io.*;

public class tcpClient {

	public static void main(String[] args)
	{
		try
		{
		
			Socket client = new Socket("localhost", 1537);
			
			BufferedReader k = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter file location:");
			String filename = k.readLine();
			DataOutputStream sendToServer = new DataOutputStream(client.getOutputStream());
			sendToServer.writeBytes(filename+"\n");
			
			BufferedReader i = new BufferedReader(new InputStreamReader(client.getInputStream()));
			String string = i.readLine();
			if(string.equals("Yes"))
			{
				while((string=i.readLine())!=null)
					System.out.println(string);
			}
			else
				System.out.println("File not found");
			
			k.close();
			client.close();
			sendToServer.close();
			i.close();
		}
		catch(Exception ex) {}
	}
}
