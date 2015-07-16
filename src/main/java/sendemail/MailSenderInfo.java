package sendemail;

import java.util.Properties;

public class MailSenderInfo {
	private final String MAIL_TRANSPORT_PROTOCOL = "smtp";
    private String mailHost = "smtp.163.com";
	private String mailServerPort = "25";

	private String fromAddress;
	private String toAddress;

	private String userName;
	private String password;

	private boolean validate = true;

	private String subject;

	private String content;

	private String[] attachFileNames;

    private static MailSenderInfo mailSenderInfo;

    private MailSenderInfo() {}

    public static MailSenderInfo getInstance() {
        if (mailSenderInfo == null) {
            mailSenderInfo = new MailSenderInfo();
        }

        return mailSenderInfo;
    }

	public Properties getProperties() {
        final Properties props = new Properties();
        props.setProperty("mail.transport.protocol", MAIL_TRANSPORT_PROTOCOL);
        props.put("mail.smtp.auth", "true");
        props.setProperty("mail.host", mailHost);
        props.put("mail.smtp.port", mailServerPort);
        if (toAddress.contains("gmail")) {
            props.setProperty("mail.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.fallback", "false");
            userName = "c4codeltd@gmail.com";
            password = "c4coderemote";
        }
        props.setProperty("mail.smtp.quitwait", "false");
		return props;
	}

	public String getMailHost() {
		return mailHost;
	}

	public void setMailHost(String mailHost) {
		this.mailHost = mailHost;
	}

	public String getMailServerPort() {
		return mailServerPort;
	}

	public void setMailServerPort(String mailServerPort) {
		this.mailServerPort = mailServerPort;
	}

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	public String[] getAttachFileNames() {
		return attachFileNames;
	}

	public void setAttachFileNames(String[] fileNames) {
		this.attachFileNames = fileNames;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String textContent) {
		this.content = textContent;
	}

}
