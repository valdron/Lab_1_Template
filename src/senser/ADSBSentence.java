package senser;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringJoiner;

public class ADSBSentence implements ADSBSentenceInterface
{
	//TODO: Create relevant fields
	private String timestamp;
	private String dfca;
	private String icao;
	private String payload;
	private String parity;

	public ADSBSentence(String timestamp, String dfca, String icao, String payload, String parity)
	{
		this.timestamp = timestamp;
		this.dfca = dfca;
		this.icao = icao;
		this.payload = payload;
		this.parity = parity;
	}

	//TODO: Create relevant getter and setter methods

	@Override
	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String getDfca() {
		return dfca;
	}

	public void setDfca(String dfca) {
		this.dfca = dfca;
	}

	@Override
	public String getIcao() {
		return icao;
	}

	public void setIcao(String icao) {
		this.icao = icao;
	}

	@Override
	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	@Override
	public String getParity() {
		return parity;
	}

	public void setParity(String parity) {
		this.parity = parity;
	}

	//TODO: Overwrite toString() method to print our relevant fields
	@Override
	public String toString()
	{
		//1379574427.9127481			
		String[] times = this.getTimestamp().split("\\."); 
		
		//TODO: Define date format
		SimpleDateFormat dateFormat = new SimpleDateFormat("EE, dd.MM.YYYY hh:mm:ss");
		//TODO: Create a DAte object
		Date date = new Date(Long.parseLong(times[0]) * 1000);
		//Create time string
		String time = dateFormat.format(date) + "." + times[1];
		
		return "Time:\t\t " + time + "\n" +
				"Dfca:\t\t " + this.getDfca() + "\n" +
				"Originator:\t " + this.getIcao() + "\n" +
				"Payload:\t " + this.getPayload() + "\n" +
				"Parity:\t\t " + this.getParity() + "\n";
	}
}
