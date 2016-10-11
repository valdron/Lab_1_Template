package senser;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ADSBSentenceFactory implements ADSBSentenceFactoryInterface
{
	//TODO: define a regular express to filter only relevant messages: 
	//{"subscribe":["message","ads.sentence","1379574427.9127481!ADS-B*8D40675258BDF05CDBFB59DA7D6F;\r\n"]}
	private static final String patAdsbJson = "\\{.*[0-9]{10}\\.[0-9]{7}!ADS-B\\*[0-9][A-F]{28}.*\\}";

	@Override
	public ADSBSentence fromWebdisJson(String json)
	{		
		if ( Pattern.matches ( patAdsbJson, json ) ) 
		{					
			//TODO: Get distinct values from the json string
			Pattern pattern = Pattern.compile("[0-9]{10}\\.[0-9]{7}");
			Matcher matcher = pattern.matcher(json);
			matcher.find();
			String timestamp = matcher.group();
			pattern = Pattern.compile("[0-9A-F]{28}");
			matcher.reset();
			matcher = pattern.matcher(json);
			matcher.find();
			String dfca 	 = matcher.group().substring(0,1);
			String icao 	 = matcher.group().substring(2,7);
			String payload 	 = matcher.group().substring(8,21);
			String parity 	 = matcher.group().substring(22,27);
			
			return new ADSBSentence(timestamp, dfca, icao, payload, parity);
		}
		else
		{
			return null;
		}
	}
}
