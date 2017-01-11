import WebServer.WebServer;
import client.Client;
import senser.Senser;
import acamo.*;

public class Starter
{

	public static void main(String[] args)
	{
		String urlString;
		if ((args.length > 0) && args[0].matches("^(?:(?<protocol>[a-z]+)://)?(?<host>(?:(?<prefix>[^.]+)\\.)?(?<domainname>[^.]+)\\.(?<domainsuffix>[^/:]+))(?::(?<port>[0-9]+))?(?<path>/[^?#\\s]*)*(?:[?#](?<queries>[^\\s]*))?$")) {
			urlString = args[0];
			System.out.println("Url from argument is ok!");
		}
		else {
			urlString = "http://flugmon-it.hs-esslingen.de/subscribe/ads.sentence";
			System.out.println("No Url provided or its not an url using default url!");
		}

		Senser server = new Senser(urlString);
		
		Client client = new Client();
		server.addObserver(client);

		client.start();
		new Thread(server).start();
		try {
			WebServer.startServer();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Got through");
	}
}
