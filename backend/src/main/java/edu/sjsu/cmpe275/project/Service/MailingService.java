package edu.sjsu.cmpe275.project.Service;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

import edu.sjsu.cmpe275.project.Config.Config;
import edu.sjsu.cmpe275.project.Entity.Hackathon;

@Configuration
public class MailingService {
	
	@Bean
	public MailingService mailService(){
		return new MailingService();
	}
	
//	public boolean makeIndividualPaymentMail(Hackathon hackathon, int teamid,int userid, String email){
//		Email from = new Email("payment@openhack.com");
//	    String subject = "Hackathon Payment Mail";
//	    Email to = new Email(email);
//	    System.out.println(( "<html>\n" + 
//	    		"  <div>\n" + 
//	    		"    Hackathon name:\n" + hackathon.getName()+
//	    		"    <br>\n" + 
//	    		"    Hackathon Description: \n" + hackathon.getDescription()+
//	    		"   <br>\n" + 
//	    		"    <br>\n" + 
//	    		"  	Please click on below link to make the payment\n" + 
//	    		"    <br>\n" + 
//	    		"    <a href="+Config.url+"/makePayment?hackathonName="+hackathon.getName()+"&teamid="+teamid+"&userid="+userid+" >Payment Link </a>\n" + 
//	    		"  </div>\n" + 
//	    		"</html>"));
//	    Content content = new Content("text/html", "<html>\n" + 
//	    		"  <div>\n" + 
//	    		"    Hackathon name:\n" + hackathon.getName()+
//	    		"    <br>\n" + 
//	    		"    Hackathon Description: \n" + hackathon.getDescription()+
//	    		"   <br>\n" + 
//	    		"    <br>\n" + 
//	    		"  	Please click on below link to make the payment\n" + 
//	    		"    <br>\n" + 
//	    		"    <a href="+Config.url+"/individualPayment?&teamid="+teamid+"&userid="+userid+" >Payment Link </a>\n" + 
//	    		"  </div>\n" + 
//	    		"</html>");
//	    Mail mail = new Mail(from, subject, to, content);
//
//	    SendGrid sg = new SendGrid("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");
//	    Request request = new Request();
//	    try {
//	      request.setMethod(Method.POST);
//	      request.setEndpoint("mail/send");
//	      request.setBody(mail.build());
//	      Response response = sg.api(request);
//	    } catch (IOException ex) {
//	      System.out.println(ex.getMessage());
//	      return false;
//	    }
//	    return true;
//	}
	public boolean makePaymentMail1(Hackathon hackathon, int teamid,int userid, String email){
		Email from = new Email("payment@openhack.com");
	    String subject = "Dummy Hackathon Payment Mail";
	    Email to = new Email(email);
//	    System.out.println(( "<html>\n" + 
//	    		"  <div>\n" + 
//	    		"    Hackathon name:\n" + hackathon.getName()+
//	    		"    <br>\n" + 
//	    		"    Hackathon Description: \n" + hackathon.getDescription()+
//	    		"   <br>\n" + 
//	    		"    <br>\n" + 
//	    		"  	Please click on below link to make the payment\n" + 
//	    		"    <br>\n" + 
//	    		"    <a href="+Config.url+"/makePayment?hackathonName="+hackathon.getName()+"&teamid="+teamid+"&userid="+userid+" >Payment Link </a>\n" + 
//	    		"  </div>\n" + 
//	    		"</html>"));
	    Content content = new Content("text/html", "<html>\n" + 
	    		"  <div>\n" + 
	    		"    Hackathon name:\n" + hackathon.getName()+
	    		"    <br>\n" + 
	    		"    Hackathon Description: \n" + hackathon.getDescription()+
	    		"   <br>\n" + 
	    		"    <br>\n" + 
	    		"  	Please click on below link to make the payment\n" + 
	    		"    <br>\n" + 
//	    		"    <a href="+Config.url+"/individualPayment?&teamid="+teamid+"&userid="+userid+" >Payment Link </a>\n" + 
				"    <a href="+Config.frontendUrl+"/Payment?&teamid="+teamid+"&userid="+userid+" >Payment Link </a>\n" +
	    		"  </div>\n" + 
	    		"</html>");
	    Mail mail = new Mail(from, subject, to, content);

	    SendGrid sg = new SendGrid("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");
	    Request request = new Request();
	    try {
	      request.setMethod(Method.POST);
	      request.setEndpoint("mail/send");
	      request.setBody(mail.build());
	      Response response = sg.api(request);
	    } catch (IOException ex) {
	      System.out.println(ex.getMessage());
	      return false;
	    }
	    return true;
	}
	public boolean makePaymentMail(Hackathon hackathon, int teamid,int userid, String email){
		Email from = new Email("payment@openhack.com");
	    String subject = "Hackathon Payment Mail";
	    Email to = new Email(email);
//	    System.out.println(( "<html>\n" + 
//	    		"  <div>\n" + 
//	    		"    Hackathon name:\n" + hackathon.getName()+
//	    		"    <br>\n" + 
//	    		"    Hackathon Description: \n" + hackathon.getDescription()+
//	    		"   <br>\n" + 
//	    		"    <br>\n" + 
//	    		"  	Please click on below link to make the payment\n" + 
//	    		"    <br>\n" + 
//	    		"    <a href="+Config.url+"/makePayment?hackathonName="+hackathon.getName()+"&teamid="+teamid+"&userid="+userid+" >Payment Link </a>\n" + 
//	    		"  </div>\n" + 
//	    		"</html>"));
	    Content content = new Content("text/html", "<html>\n" + 
	    		"  <div>\n" + 
	    		"    Hackathon name:\n" + hackathon.getName()+
	    		"    <br>\n" + 
	    		"    Hackathon Description: \n" + hackathon.getDescription()+
	    		"   <br>\n" + 
	    		"    <br>\n" + 
	    		"  	Please click on below link to make the payment\n" + 
	    		"    <br>\n" + 
//	    		"    <a href="+Config.url+"/individualPayment?&teamid="+teamid+"&userid="+userid+" >Payment Link </a>\n" + 
				"    <a href="+Config.frontendUrl+"/Payment?&teamid="+teamid+"&userid="+userid+"&hackId="+hackathon.getId()+">Payment Link </a>\n" +
	    		"  </div>\n" + 
	    		"</html>");
	    Mail mail = new Mail(from, subject, to, content);

	    SendGrid sg = new SendGrid("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");
	    Request request = new Request();
	    try {
	      request.setMethod(Method.POST);
	      request.setEndpoint("mail/send");
	      request.setBody(mail.build());
	      Response response = sg.api(request);
	    } catch (IOException ex) {
	      System.out.println(ex.getMessage());
	      return false;
	    }
	    return true;
	}
	
	public boolean confirmationMail(String email){
		Email from = new Email("verification@openhack.com");
	    String subject = "Hackathon Confirmation Mail";
	    Email to = new Email(email);
	    
	    Content content = new Content("text/plain", "Payment Done.");
	    Mail mail = new Mail(from, subject, to, content);

	    SendGrid sg = new SendGrid("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");
	    Request request = new Request();
	    try {
	      request.setMethod(Method.POST);
	      request.setEndpoint("mail/send");
	      request.setBody(mail.build());
	      Response response = sg.api(request);
	    } catch (IOException ex) {
	      System.out.println(ex.getMessage());
	      return false;
	    }
	    return true;
	}
	
	public boolean confirmationMailTeam(String email){
		Email from = new Email("TeamVerification@openhack.com");
	    String subject = "Hackathon Team Payment Confirmation status";
	    Email to = new Email(email);
	    
	    Content content = new Content("text/plain", "Team Payment is Done. All the people have paid!");
	    Mail mail = new Mail(from, subject, to, content);

	    SendGrid sg = new SendGrid("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");
	    Request request = new Request();
	    try {
	      request.setMethod(Method.POST);
	      request.setEndpoint("mail/send");
	      request.setBody(mail.build());
	      Response response = sg.api(request);
	    } catch (IOException ex) {
	      System.out.println(ex.getMessage());
	      return false;
	    }
	    return true;
	}
	
	public boolean sendMail(String accessToken, String email){
		Email from = new Email("verification@openhack.com");
	    String subject = "Hackathon Verification Mail";
	    Email to = new Email(email);
	    
	    Content content = new Content("text/plain", "Please click on this link: " +Config.url+"/activateLogin?accessToken="+accessToken+"&email="+email);
	    Mail mail = new Mail(from, subject, to, content);

	    SendGrid sg = new SendGrid("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");
	    Request request = new Request();
	    try {
	      request.setMethod(Method.POST);
	      request.setEndpoint("mail/send");
	      request.setBody(mail.build());
	      Response response = sg.api(request);
	    } catch (IOException ex) {
	      System.out.println(ex.getMessage());
	      return false;
	    }
	    return true;
	}
	
	public boolean sendMailCheck( String email){
		Email from = new Email("verification@openhack.com");
	    String subject = "Congratulations! You are the winner";
	    Email to = new Email(email);
	    String a="localhost";
	    Content content = new Content("text/plain", "Hello Hacker,\n" + 
	    		"\n" + 
	    		"Congratulations for Securing a place in the TOP 3. Keep up the good work and Keep coding! \n" + 
	    		"Here's the link for the results:\n" + 
	    		"link here" );
	    Mail mail = new Mail(from, subject, to, content);

	    SendGrid sg = new SendGrid("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");
	    Request request = new Request();
	    try {
	      request.setMethod(Method.POST);
	      request.setEndpoint("mail/send");
	      request.setBody(mail.build());
	      Response response = sg.api(request);
	    } catch (IOException ex) {
	      System.out.println(ex.getMessage());
	      return false;
	    }
	    return true;
	}
	
	public boolean sendMailToWinner( String email){
		Email from = new Email("verification@openhack.com");
	    String subject = "Congratulations! You are the winner";
	    Email to = new Email(email);
	    String a="localhost";
	    
	    Content content = new Content("text/html", "<html>Hi,\n" + 
	    		"<div>\n" + 
	    		"<p>\n"+
	    		"Congratulations for Securing a place in the TOP 3. Keep up the good work and Keep coding! \n" + 
	    		"Here's the link for the results:\n" + 
	    		"</p>\n"+
	    		"<br>"+
	    		"<a href="+Config.frontendUrl+"/resultsReport"+">Click here for results</a>"+
	    		"</div>\n"+
	    		"</html>"+
	    		"" );
//	    System.out.println("<a href="+Config.frontendUrl+"/resultsReport"+">Click here for results</a>");
	    Mail mail = new Mail(from, subject, to, content);

	    SendGrid sg = new SendGrid("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");
	    Request request = new Request();
	    try {
	      request.setMethod(Method.POST);
	      request.setEndpoint("mail/send");
	      request.setBody(mail.build());
	      Response response = sg.api(request);
	    } catch (IOException ex) {
	      System.out.println(ex.getMessage());
	      return false;
	    }
	    return true;
	}
	
	public boolean sendMailToAll( String email){
		Email from = new Email("verification@openhack.com");
	    String subject = "Hackathon results are out !";
	    Email to = new Email(email);
	    String a="localhost";
	    Content content = new Content("text/html", "<html>Hi,\n" + 
	    		"<div>\n" + 
	    		"<p>\n"+
	    		"Hackathon is finalized, Results are out " + 
	    		"here's the link for the results:\n" + 
	    		"</p>\n"+
	    		"<br>"+
	    		"<a href="+Config.frontendUrl+"/resultsReport"+">Click here for results</a>"+
	    		"</div>\n"+
	    		"</html>"+
	    		"" );
	    Mail mail = new Mail(from, subject, to, content);

	    SendGrid sg = new SendGrid("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");
	    Request request = new Request();
	    try {
	      request.setMethod(Method.POST);
	      request.setEndpoint("mail/send");
	      request.setBody(mail.build());
	      Response response = sg.api(request);
	    } catch (IOException ex) {
	      System.out.println(ex.getMessage());
	      return false;
	    }
	    return true;
	}
}