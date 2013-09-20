package models;

import java.util.Date;

import javax.persistence.*;
import play.db.ebean.*;
import antlr.collections.List;

import com.avaje.ebean.*;

@Entity
@Table(name = "template_user")
public class User extends Model {
	
	@Id
	public long id;
	
    // facebook info
    public String facebook_first;
    public String facebook_last;
    public String facebook_id;
    public String facebook_pic_url;
    public String facebook_access;
    public String facebook_email;
    public String facebook_auth;
        
    // linkedin info
    public String linkedin_first;
    public String linkedin_last;
    public String linkedin_pic_url;
    public String linkedin_auth;
    public String linkedin_email;
    public String linkedin_access;
    public String linkedin_id;
    public String linkedin_secret;
    
    public static Finder<String,User> find = new Finder<String,User>(
        String.class, User.class
    );
    
    public static User findByFacebookId(String id) {
        return find.where().eq("facebook_id", id).findUnique();
    }
    
    public static User findByLinkedInId(String id) {
        return find.where().eq("linkedin_id", id).findUnique();
    }
}