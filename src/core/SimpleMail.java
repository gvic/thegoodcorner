package core;

import javax.mail.*;
import javax.mail.internet.*;

import java.util.Properties;

public class SimpleMail {

	private Properties props;
	private Session mailSession;
	private Transport transport;
	private MimeMessage message;
	private String link;
	private int port = 8080;
	private String host = "localhost";

	public SimpleMail() {
		props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.host", "localhost");
		mailSession = Session.getDefaultInstance(props, null);
		try {
			transport = mailSession.getTransport();
		} catch (NoSuchProviderException e) {
			System.out.println("L'instation du service mail a échoué !");
			e.printStackTrace();
		}
		message = new MimeMessage(mailSession);
	}

	public static String stringToHex(String login, long adId) {
		char[] chars = (login+"_"+adId).toCharArray();
		StringBuffer strBuffer = new StringBuffer();
		for (int i = 0; i < chars.length; i++) {
			strBuffer.append(Integer.toHexString((int) chars[i]));
		}
		return strBuffer.toString();
	}

	public static String hexToStr(String s) {
		byte[] bytes = new byte[s.length() / 2];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) Integer.parseInt(s.substring(2 * i, 2 * i + 2),
					16);
		}

		return new String(bytes);
	}

	public void sendValidationAdMessage(String login, long adId, String to)
			throws MessagingException {

		link = "http://"+host+":"+port+"/thegoodcorner/confirmAd.action?key="
				+ stringToHex(login, adId);
		message.setSubject("Confirmation of your ad");
		String content = "Hello!\n\n";
		content += "You need to confirm your ad.\n";
		content += "Go on the link below to confirm it :\n\n" + link;
		content += "\n\nSee you soon on thegoodcorner !";

		message.setContent(content, "text/plain");
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		transport.connect();
		transport.sendMessage(message,
				message.getRecipients(Message.RecipientType.TO));
		transport.close();
	}
}