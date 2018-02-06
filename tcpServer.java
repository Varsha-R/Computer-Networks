package org.tcp;

import java.net.*;
import java.io.*;

public class tcpServer {

	public static void main(String[] args)
	{
		
		try
		{
			ServerSocket se = new ServerSocket(1537);
			System.out.println("Server waiting");
			
			Socket server = se.accept();
			System.out.println("Connection established");
			
			BufferedReader k = new BufferedReader (new InputStreamReader(server.getInputStream()));
			String filename = k.readLine();
			FileReader f = null;
			BufferedReader ff = null;
			DataOutputStream sendToClient = new DataOutputStream(server.getOutputStream());
			File file = new File(filename);
			if(file.exists())
			{
				sendToClient.writeBytes("Yes\n");
				f = new FileReader(filename);
				ff = new BufferedReader(f);
				String string;
				while((string = ff.readLine())!=null)
					sendToClient.writeBytes(string+"\n");
			}
			else
			{
				sendToClient.writeBytes("No\n");
			}
			server.close();
			k.close();
			sendToClient.close();
			f.close();
			ff.close();
			se.close();
		}
		catch(Exception ex) {}
		
	}
}
