package com.detourgame.raw.tools.TSXFileEditor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class TSXFileEditor {
	
	/*
	 * A shitty little tool I made to help edit .tsx (Tiled tile set) files.
	 * 
	 */
	
	private static boolean mFirstTime = true;
	
	static PrintWriter mNewFile;
	
	public static void main(String[] args) throws IOException{
		
		welcome();
	}
	
	private static void welcome() throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		if(mFirstTime){
			System.out.println("Type \"help\" for additional commands.");
			mFirstTime = false;
		}
		String yesNo = checkText("Welcome! Create new .tsx file? (yes/no)", in);
		if(yesNo.equalsIgnoreCase("yes")){
			createNewFile();
		}else if(yesNo.equalsIgnoreCase("no")){
			loadOldFile();
		}else if(yesNo.equalsIgnoreCase("help") || yesNo.equalsIgnoreCase("exit")){
			System.out.println("Sorry, I suck at programming. Could you repeat that?");
			welcome();
		}else{
			System.out.println("Invalid response. Please try again.");
			welcome();
		}
	}
	
	private static void createNewFile() throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String name = checkText("New file name?", in);
		String location = checkText("New file location? (ex: C:\\"+"\\...", in);
		if(name.endsWith(".tsx")){
			mNewFile = new PrintWriter(new FileWriter(location+name));
		}else{
			mNewFile = new PrintWriter(new FileWriter(location+name+".tsx"));
		}
		
		mNewFile.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		String ts_name = checkText("New tile set name?", in);
		String tile_size = checkText("Size of tiles? (All tiles are assumed squares)", in);
		mNewFile.println("<tileset name=\""+ts_name+"\" tilewidth=\""+tile_size+"\" tileheight=\""+tile_size+"\">");
		String image = checkText("Enter tile set image source: ", in);
		String width = checkText("Image width?", in);
		String height = checkText("Image height?", in);
		mNewFile.println(" <image source=\"" + image + "\" width=\"" + width + "\" height=\"" + height + "\"/>");
		//TODO finish creating file
		mNewFile.close();
		System.out.println("New tile set created!");
		
		String again = checkText("Run again?", in);
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
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		String file_name = checkText("Enter file path and name: ", console);
		
		BufferedReader in = new BufferedReader(new FileReader(file_name)); 
		String line1 = in.readLine();
		String line2 = in.readLine();
		String line3 = in.readLine();
		in.close();
		
		File oldFile = new File(file_name);
		if(oldFile.delete()){
			//System.out.println("Deleted old file.");
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
		
		String numberTiles = checkText("How many tiles does this set contain? (integers only please)", console);
		int numTiles =  Integer.valueOf(numberTiles);
		String startNumber = checkText("What number should they start with? (integers only please)", console);
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
	
	private static String checkText(String prompt, BufferedReader console) throws IOException{
		System.out.println(prompt);
		String input = console.readLine();
		if(input.equalsIgnoreCase("help")){
			System.out.println("Type \"exit\" to end application.");
			checkText(prompt, console);
		}else if(input.equalsIgnoreCase("exit")){
			System.out.println("Are you sure you want to exit? (yes/no)");
			String input2 = console.readLine();
			while(!input2.equalsIgnoreCase("yes")){
				while(!input2.equalsIgnoreCase("no")){
					System.out.println("Invalid response.");
					System.out.println("Are you sure you want to exit? (yes/no)");
					input2 = console.readLine();
					if(input2.equalsIgnoreCase("yes")){
						break;
					}
				}
				if(input2.equalsIgnoreCase("no")){
					break;
				}
			}
			if(input2.equalsIgnoreCase("yes")){
				System.out.println("See ya!");
				System.exit(0);
			}else if(input2.equalsIgnoreCase("no")){
				checkText(prompt, console);
			}
		}
		
		return input;
	}
	
}
