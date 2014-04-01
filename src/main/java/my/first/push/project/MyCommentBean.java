package my.first.push.project;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import org.primefaces.push.PushContextFactory;

@ManagedBean(name = "myCommentBean")
@ApplicationScoped
@Stateless
public class MyCommentBean {

   public static final String BROADCAST_CHANNEL = "/comment";
   private int comment;
   public int getComment() {return comment;}
   public void setComment(int comment) {this.comment = comment;}

   
   @Schedule(hour="*",minute="*",second="*/10")
   public void publish() {
	   comment++;
      PushContextFactory.getDefault().getPushContext().push("/comment",new FacesMessage(FacesMessage.SEVERITY_INFO,"Compteur", "Vous avez cliquer "+comment+" fois"));
      System.out.println("Compteur : "+comment);
   }
   private int sec=34;
   @Schedule(hour="*",minute="*",second="*/34")
   public void apres34sec(){
	   PushContextFactory.getDefault().getPushContext().push("/comment",new FacesMessage(FacesMessage.SEVERITY_INFO,"Compteur", "Apres "+sec+"s Vous avez cliquer "+comment+" fois"));
	      System.out.println("Compteur Apres "+sec+" sec : "+comment);
	      sec+=34;
   }
}
