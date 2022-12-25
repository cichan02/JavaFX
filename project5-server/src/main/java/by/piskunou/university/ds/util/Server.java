package by.piskunou.university.ds.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Server extends Thread {
	private static final Logger log = LogManager.getLogger();
	private ServerSocket ss;	
	private StringBuffer currentDir;
	
	public Server() {
		try {
			this.ss = new ServerSocket(4321);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.currentDir = new StringBuffer("/home/cichan/Documents/University/Discipline of Specialization (DS)/Practice/project5-server/src/main/resources");
	}

	@Override
	public void run() {
		while(true) {
			try (Socket s = ss.accept();
				 BufferedReader bf = new BufferedReader(new InputStreamReader(s.getInputStream()))) {
					
				String command = bf.readLine();	
				String[] commandWords = command.split("\\s+");
				switch (commandWords[0]) {
					case "ls" -> ls(s);
					case "cd" -> cd(s, commandWords[1]);
					case "get" -> send(s);	
					default -> throw new IllegalArgumentException();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void ls(Socket s) {
		log.debug("Get 'ls'-command");
		try {	
			File dir = new File(currentDir.toString());
			PrintWriter pr = new PrintWriter(s.getOutputStream());
			pr.println(dir.list().length);
			for(String name: dir.list()) {
				pr.println(name);
			}
			pr.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void cd(Socket s, String fileName) {
		log.debug("Get 'cd'-command");
		try {
			File dir = new File(currentDir.toString());
			PrintWriter pr = new PrintWriter(s.getOutputStream());
			if(fileName.equals("..")) {
				String[] strings = currentDir.toString().split("/");
				String last = strings[strings.length - 1];
				int size = currentDir.length();
				currentDir = currentDir.replace(size - last.length() - 1, size, "");
				pr.println(currentDir.toString());
			} else {
				for(File file: dir.listFiles()) {
					if(file.getName().equals(fileName)) {
						if(file.isDirectory()) {
							currentDir.append("/" + fileName);
							pr.println("yes");
						} else {
							pr.println("no");
						}
					}
				}
			}
			pr.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void send(Socket s) {
		log.debug("Get 'send'-command");
	}
	
}
