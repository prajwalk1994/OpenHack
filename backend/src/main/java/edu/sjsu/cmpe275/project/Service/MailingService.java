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
	
	public boolean makePaymentMail(Hackathon hackathon, int teamid,int userid, String email){
		Email from = new Email("payment@openhack.com");
	    String subject = "Hackathon Payment Mail";
	    Email to = new Email(email);
	    
	    Content content = new Content("text/html", "<html>\n" + 
	    		"  <div>\n" + 
	    		"    Hackathon name:\n" + hackathon.getName()+
	    		"    <br>\n" + 
	    		"    Hackathon Description: \n" + hackathon.getDescription()+
	    		"   <br>\n" + 
	    		"    <br>\n" + 
	    		"  	Please click on below link to make the payment\n" + 
	    		"    <br>\n" + 
	    		"    <a href=\"'"+Config.url+"\"/makePayment?hackathonName=\""+hackathon.getName()+"\"&teamid=\""+teamid+"\"&userid=\""+userid+"\"' value=\"Make payment\">Payment Link </a>\n" + 
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
	
	public boolean sendMail(String accessToken, String email){
		Email from = new Email("verification@openhack.com");
	    String subject = "Hackathon Verification Mail";
	    Email to = new Email(email);
	    
	    Content content = new Content("text/plain", "Please click on this link: " + "http://localhost:8080/activateLogin?accessToken="+accessToken+"&email="+email);
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