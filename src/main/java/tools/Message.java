package tools;

public class Message {
	private Integer result;
	private String message;//需要显示的信息字段
	private String redirectUrl;//需要跳转到的网址
	private Integer redirectTime;//跳转的时间间隔，单位秒
	
	public Integer getResult() {
		return result;
	}
	public void setResult(Integer result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getRedirectUrl() {
		return redirectUrl;
	}
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	public Integer getRedirectTime() {
		return redirectTime;
	}
	public void setRedirectTime(Integer redirectTime) {
		this.redirectTime = redirectTime;
	}
}
