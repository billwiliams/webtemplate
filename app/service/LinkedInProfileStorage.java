package service;

import java.util.Date;

import play.cache.Cache;
import play.mvc.Http;
import play.mvc.Http.Session;

import models.User;

import scala.Option;
import securesocial.core.AuthenticationMethod;
import securesocial.core.Identity;
import securesocial.core.OAuth2Info;
import securesocial.core.SocialUser;
import securesocial.core.UserId;

class LinkedInProfileStorage extends ProfileStorage
{
	@Override
	public boolean save(Identity user) {
		
		User neenerUser = new User();
				
		neenerUser.linkedin_first = user.firstName();
		neenerUser.linkedin_last = user.lastName();
		neenerUser.linkedin_id = user.id().id();
		
		if (!user.avatarUrl().isEmpty())
			neenerUser.linkedin_pic_url = user.avatarUrl().get();
		
		neenerUser.linkedin_access = user.oAuth1Info().get().token();
		
		if (!user.email().isEmpty())
			neenerUser.linkedin_email = user.email().get();
		
		neenerUser.linkedin_auth = user.authMethod().method();
		neenerUser.linkedin_secret = user.oAuth1Info().get().secret();
		
		neenerUser.save();
		
        return true;
	}

	@Override
	public Identity find(UserId userId) {
		
    	SocialUser socialUser = null;
    	
    	User user = User.findByLinkedInId(userId.id());

        String fname = user.linkedin_first;
        String lname = user.linkedin_last;
        String fullName = fname + " " + lname;
        String id = user.linkedin_id;
        String url = user.linkedin_pic_url;
        String access = user.linkedin_access;
        String email = user.linkedin_email;
        String auth = user.linkedin_auth;
        
        OAuth2Info oAuth2Info = new OAuth2Info(access, null, null, null);
        Option<OAuth2Info> oAuth2InfoOp = Option.apply(oAuth2Info);
        AuthenticationMethod authMethod = new AuthenticationMethod(auth);
        Option<String> emailOp = Option.apply(email);
        Option<String> avatarUrlOp = Option.apply(url);
        
        UserId socialUserId = new UserId(id, "linkedin");
        socialUser = new SocialUser(socialUserId, fname, lname, fullName, emailOp, avatarUrlOp, authMethod, null, oAuth2InfoOp, null);
  	        	
    	return socialUser;		
	}
}