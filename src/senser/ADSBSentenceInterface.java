package senser;

public interface ADSBSentenceInterface
{
	public String getTimestamp();
	public String getDfca();
	public String getIcao();
	public String getParity();
	public String getPayload();
}
