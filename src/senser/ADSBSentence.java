package senser;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ADSBSentence implements ADSBSentenceInterface {
	//Create relevant fields
	private String timestamp, dfca, icao, payload, parity;

	public ADSBSentence(String timestamp, String dfca, String icao, String payload, String parity) {
		//Fill relevant fields
		this.timestamp = timestamp;
		this.dfca = dfca;
		this.icao = icao;
		this.payload = payload;
		this.parity = parity;
	}

	// Overwrite toString() method to print our relevant fields
	@Override
	public String toString() {
		//1379574427.9127481
		String[] times = this.getTimestamp().split("\\.");

		//Define date format
		SimpleDateFormat dateFormat = new SimpleDateFormat("EE, dd.MM.YYYY hh:mm:ss");
		//Create a DAte object
		Date date = new Date();
		//Create time string
		String time = dateFormat.format(date) + "." + times[1];

		return "Time:\t\t " + time + "\n" +
				"Dfca:\t\t " + this.getDfca() + "\n" +
				"Originator:\t " + this.getIcao() + "\n" +
				"Payload:\t " + this.getPayload() + "\n" +
				"Parity:\t\t " + this.getParity() + "\n";
	}

//Getters and Setters
	public String getDfca() {
	return dfca;
}

	public void setDfca(String dfca) {
		this.dfca = dfca;
	}

	public String getIcao() {
		return icao;
	}

	public void setIcao(String icao) {
		this.icao = icao;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public String getParity() {
		return parity;
	}

	public void setParity(String parity) {
		this.parity = parity;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
}