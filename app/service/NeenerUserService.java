/**
 * Copyright 2012 Jorge Aliss (jaliss at gmail dot com) - twitter: @jaliss
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package service;

import play.Application;
import scala.Option;
import securesocial.core.Identity;
import securesocial.core.UserId;
import securesocial.core.java.BaseUserService;

import securesocial.core.java.Token;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * Note: This is NOT suitable for a production environment and is provided only as a guide.
 * A real implementation would persist things in a database
 */
public class NeenerUserService extends BaseUserService {
    private HashMap<String, Identity> users  = new HashMap<String, Identity>();
    private HashMap<String, Token> tokens = new HashMap<String, Token>();

    public NeenerUserService(Application application) {
        super(application);
    }

    @Override
    public Identity doSave(Identity user) {
    	
    	ProfileStorage profileStorage = null;
    	
    	if (user.id().providerId().equals("facebook"))
    	{
    		profileStorage = new FacebookProfileStorage();
    	}
    	    	
    	else if (user.id().providerId().equals("linkedin"))
    	{
    		profileStorage = new LinkedInProfileStorage();
    	}
    	
    	profileStorage.save(user);
    	
    	return user;
    }

    @Override
    public void doSave(Token token) {
        tokens.put(token.uuid, token);
    }

    @Override
    public Identity doFind(UserId userId) {
    	
    	ProfileStorage profileStorage = null;
    	
    	if (userId.providerId().equals("facebook"))
    	{
    		profileStorage = new FacebookProfileStorage();
    	}
    	else if (userId.providerId().equals("linkedin"))
    	{
    		profileStorage = new LinkedInProfileStorage();
    	}
    	
    	return profileStorage.find(userId);
    }

    @Override
    public Token doFindToken(String tokenId) {
        return tokens.get(tokenId);
    }

    @Override
    public Identity doFindByEmailAndProvider(String email, String providerId) {
        Identity result = null;
        for( Identity user : users.values() ) {
            Option<String> optionalEmail = user.email();
            if ( user.id().providerId().equals(providerId) &&
                 optionalEmail.isDefined() &&
                 optionalEmail.get().equalsIgnoreCase(email))
            {
                result = user;
                break;
            }
        }
        return result;
    }

    @Override
    public void doDeleteToken(String uuid) {
        tokens.remove(uuid);
    }

    @Override
    public void doDeleteExpiredTokens() {
        Iterator<Map.Entry<String,Token>> iterator = tokens.entrySet().iterator();
        while ( iterator.hasNext() ) {
            Map.Entry<String, Token> entry = iterator.next();
            if ( entry.getValue().isExpired() ) {
                iterator.remove();
            }
        }
    }
}
