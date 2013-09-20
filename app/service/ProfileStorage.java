package service;

import securesocial.core.Identity;
import securesocial.core.UserId;

abstract class ProfileStorage
{
	public abstract boolean save(Identity profile);
	
	public abstract Identity find(UserId id);
}