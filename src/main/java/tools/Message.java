package tools;

public class Message {
	private Integer result;
	private String message;//��Ҫ��ʾ����Ϣ�ֶ�
	private String redirectUrl;//��Ҫ��ת������ַ
	private Integer redirectTime;//��ת��ʱ��������λ��
	
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
