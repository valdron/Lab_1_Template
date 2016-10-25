package senser;


public class ADSBSentenceFactory implements ADSBSentenceFactoryInterface
{
	@Override
	public ADSBSentence fromWebdisJson(String json)
	{		
		//TODO: Get distinct values from the json string
		
		String timestamp = json.substring(0, 18);									
		String dfca 	 = json.substring(25, 27);					
		String icao 	 = json.substring(27, 33);			
		String payload 	 = json.substring(33, 47);			
		String parity 	 = json.substring(47,53);


		return new ADSBSentence(timestamp, dfca, icao, payload, parity);
	}
}
