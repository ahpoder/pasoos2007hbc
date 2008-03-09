package dooralarm.domain;

import java.io.*;

public class FileLogging implements Logging {
  public void log(String text)
  {
	try {
		BufferedWriter out = new BufferedWriter(new FileWriter("log.txt", true));
		out.write("Access granted: " + text + "\r\n");
		out.close();
	} catch (IOException e) { }
  }
}
