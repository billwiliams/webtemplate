package service;

import java.util.Date;
import java.util.List;

import play.cache.Cache;

import models.User;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.Post;

import scala.Option;
import securesocial.core.AuthenticationMethod;
import securesocial.core.Identity;
import securesocial.core.OAuth2Info;
import securesocial.core.SocialUser;
import securesocial.core.UserId;

class FacebookProfileStorage extends ProfileStorage
{
	@Override
	public boolean save(Identity user) {
		
		if (User.findByFacebookId(user.id().id()) == null)
		{
			User neenerUser = new User();
					
			neenerUser.facebook_first = user.firstName();
			neenerUser.facebook_last = user.lastName();
			neenerUser.facebook_id = user.id().id();
			neenerUser.facebook_pic_url = user.avatarUrl().get();
			neenerUser.facebook_access = user.oAuth2Info().get().accessToken();
			neenerUser.facebook_email = user.email().get();
			neenerUser.facebook_auth = user.authMethod().method();
	
			neenerUser.save();
		}

        return true;
	}

	@Override
	public Identity find(UserId userId) {
		
    	SocialUser socialUser = null;
    	
    	User user = User.findByFacebookId(userId.id());

    	String fname = user.facebook_first;
        String lname = user.facebook_last;
        String fullName = fname + " " + lname;
        String id = user.facebook_id;
        String url = user.facebook_pic_url;
        String access = user.facebook_access;
        String email = user.facebook_email;
        String auth = user.facebook_auth;
        
        OAuth2Info oAuth2Info = new OAuth2Info(access, null, null, null);
        Option<OAuth2Info> oAuth2InfoOp = Option.apply(oAuth2Info);
        AuthenticationMethod authMethod = new AuthenticationMethod(auth);
        Option<String> emailOp = Option.apply(email);
        Option<String> avatarUrlOp = Option.apply(url);
        
        UserId socialUserId = new UserId(id.toString(), "facebook");
        socialUser = new SocialUser(socialUserId, fname, lname, fullName, emailOp, avatarUrlOp, authMethod, null, oAuth2InfoOp, null);
  	        	
    	return socialUser;
	}
}
