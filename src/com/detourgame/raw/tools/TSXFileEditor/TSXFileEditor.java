package com.detourgame.raw.tools.TSXFileEditor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class TSXFileEditor {
	
	static PrintWriter mNewFile;
	
	public static void main(String[] args) throws IOException{
		
		welcome();
	}
	
	private static void welcome() throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Welcome! Create new .tsx file? (yes/no)");
		String yesNo  = in.readLine();
		if(yesNo.equalsIgnoreCase("yes")){
			createNewFile();
		}else if(yesNo.equalsIgnoreCase("no")){
			loadOldFile();
		}else{
			System.out.println("Invalid response. Please try again.");
			welcome();
		}
	}
	
	private static void createNewFile() throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("New file name?");
		String name = in.readLine();
		System.out.println("New file location? (ex: C:\\"+"\\...");
		String location = in.readLine();
		if(name.endsWith(".tsx")){
			mNewFile = new PrintWriter(new FileWriter(location+name));
		}else{
			mNewFile = new PrintWriter(new FileWriter(location+name+".tsx"));
		}
		
		mNewFile.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		System.out.println("New tile set name?");
		String ts_name = in.readLine();
		System.out.println("Size of tiles? (All tiles are assumed squares)");
		String tile_size = in.readLine();
		mNewFile.println("<tileset name=\""+ts_name+"\" tilewidth=\""+tile_size+"\" tileheight=\""+tile_size+"\">");
		System.out.println("Enter tile set image source: ");
		String image = in.readLine();
		System.out.println("Image width?");
		String width = in.readLine();
		System.out.println("Image height?");
		String height = in.readLine();
		mNewFile.println(" <image source=\"" + image + "\" width=\"" + width + "\" height=\"" + height + "\"/>");
		//TODO finish creating file
		mNewFile.close();
		System.out.println("New tile set created!");
		
		System.out.println("Run again?");
		String again = in.readLine();
		if(again.equalsIgnoreCase("yes")){
			welcome();
		}else if(again.equalsIgnoreCase("no")){
			System.out.println("Please go fuck yourself.");
			mNewFile.close();
		}else{
			System.out.println("Invalid response. Please go fuck yourself.");
			mNewFile.close();
		}
		in.close();
	}
	
	private static void loadOldFile() throws IOException{
		//System.out.println("Old");
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter file path and name: ");
		String file_name = console.readLine();
		
		BufferedReader in = new BufferedReader(new FileReader(file_name)); 
		String line1 = in.readLine();
		String line2 = in.readLine();
		String line3 = in.readLine();
		in.close();
		
		File oldFile = new File(file_name);
		if(oldFile.delete()){
			System.out.println("Deleted old file.");
		}else{
			System.out.println("faq");
		}
		
		File tempFile = new File(file_name);
		if(!tempFile.createNewFile()){
			System.out.println("fuck");
		}
		
		mNewFile = new PrintWriter(new FileWriter(tempFile));
		
		mNewFile.println(line1);
		mNewFile.println(line2);
		mNewFile.println(line3);
		
		System.out.println("How many tiles does this set contain? (integers only please)");
		String numberTiles = console.readLine();
		int numTiles =  Integer.valueOf(numberTiles);
		System.out.println("What number should they start with? (integers only please)");
		String startNumber = console.readLine();
		int startNum = Integer.valueOf(startNumber);
		
		for(int x=0, y=startNum;x<numTiles;x++,y++){
			mNewFile.println(" <tile id=\""+ x +"\">");
			mNewFile.println("  <properties>");
			mNewFile.println("   <property name=\"TileType\" value=\""+ y +"\"/>");
			mNewFile.println("  </properties>");
			mNewFile.println(" </tile>");
		}
		mNewFile.println("</tileset>");
		
		console.close();
		mNewFile.close();
		
		System.out.println("Success! Thank you, come again!");
	}
	
}
