package edu.csupomona.cs480;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import edu.csupomona.cs480.data.provider.AdminiManager;
import edu.csupomona.cs480.data.provider.FSSubmissionManager;
import edu.csupomona.cs480.data.provider.FSreleaseProbManager;
import edu.csupomona.cs480.data.provider.NewReleaseProbManager;
import edu.csupomona.cs480.data.provider.SubmissionManager;
import edu.csupomona.cs480.util.SendMail;

import org.springframework.beans.factory.*;  
import org.springframework.beans.factory.xml.XmlBeanFactory;  
import org.springframework.core.io.*; 

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class App {
    
    /**
     * This is a good example of how Spring instantiates
     * objects. The instances generated from this method
     * will be used in this project, where the Autowired
     * annotation is applied.
     */
    @Bean
    public SubmissionManager submissionManager() {
        SubmissionManager submissionManager = new FSSubmissionManager();
        return submissionManager;
    }
    @Bean
    public NewReleaseProbManager newReleaseProbManager() {
    	NewReleaseProbManager newReleaseProbManager = new FSreleaseProbManager();
        return newReleaseProbManager;
    }
    @Bean	
    public AdminiManager administratorManager(){
		AdminiManager adminiManager = new AdminiManager();
    	return adminiManager;
    	
    }
 
    /**
     * This is the running main method for the web application.
     * Please note that Spring requires that there is one and
     * ONLY one main method in your whole program. You can create
     * other main methods for testing or debugging purposes, but
     * you cannot put extra main method when building your project.
     */
    public static void main(String[] args) throws Exception {
        // Run Spring Boot
        SpringApplication.run(App.class, args);
//      Resource r=new ClassPathResource("applicationContext.xml");  
//      BeanFactory b=new XmlBeanFactory(r);  
//      SendMail m=(SendMail)b.getBean("mailMail");  
//      String sender="lovemonky88@gmail.com";//write here sender gmail id  
//      String receiver="lovemonky88@gmail.com";//write here receiver id  
//      m.sendMail(sender,receiver,"hi","welcome");  
    }
}
