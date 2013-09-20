package custom

import securesocial.controllers.TemplatesPlugin;
import play.api.templates.{Html, Txt}
import play.api.mvc.{RequestHeader, Request}
import play.api.data.Form;
import securesocial.controllers.Registration.RegistrationInfo
import securesocial.controllers.PasswordChange.ChangeInfo
import securesocial.core.{Identity, SecuredRequest, SocialUser}
import securesocial.core.IdentityProvider

// we inherit from TemplatesPlugin just to override the login page

class CustomTemplatesPlugin(application: play.Application) extends TemplatesPlugin 
{
 /**
   * Returns the html for the login page
   * @param request
   * @tparam A
   * @return
   */
  override def getLoginPage[A](implicit request: Request[A], form: Form[(String, String)],
                               msg: Option[String] = None): Html =
  {
    views.html.login(form, msg)
  }
  
  /**
   * Returns the html for the signup page
   *
   * @param request
   * @tparam A
   * @return
   */
  override def getSignUpPage[A](implicit request: Request[A], form: Form[RegistrationInfo], token: String): Html = {
    securesocial.views.html.Registration.signUp(form, token)
  }

  /**
   * Returns the html for the start signup page
   *
   * @param request
   * @tparam A
   * @return
   */
  override def getStartSignUpPage[A](implicit request: Request[A], form: Form[String]): Html = {
    securesocial.views.html.Registration.startSignUp(form)
  }

  /**
   * Returns the html for the reset password page
   *
   * @param request
   * @tparam A
   * @return
   */
  override def getStartResetPasswordPage[A](implicit request: Request[A], form: Form[String]): Html = {
    securesocial.views.html.Registration.startResetPassword(form)
  }

  /**
   * Returns the html for the start reset page
   *
   * @param request
   * @tparam A
   * @return
   */
  def getResetPasswordPage[A](implicit request: Request[A], form: Form[(String, String)], token: String): Html = {
    securesocial.views.html.Registration.resetPasswordPage(form, token)
  }

   /**
   * Returns the html for the change password page
   *
   * @param request
   * @param form
   * @tparam A
   * @return
   */
  def getPasswordChangePage[A](implicit request: SecuredRequest[A], form: Form[ChangeInfo]): Html = {
    securesocial.views.html.passwordChange(form)
  }


  /**
   * Returns the email sent when a user starts the sign up process
   *
   * @param token the token used to identify the request
   * @param request the current http request
   * @return a String with the html code for the email
   */
  def getSignUpEmail(token: String)(implicit request: RequestHeader): String = {
    "<html><body><p>Hello,</p><p>Please follow this <a href=\"" + securesocial.core.providers.utils.RoutesHelper.signUp(token).absoluteURL(IdentityProvider.sslEnabled) + "\">link</a> to complete your registration</p></body></html>"
  }

  /**
   * Returns the email sent when the user is already registered
   *
   * @param user the user
   * @param request the current request
   * @return a String with the html code for the email
   */
  def getAlreadyRegisteredEmail(user: securesocial.core.Identity)(implicit request: play.api.mvc.RequestHeader): String =  {
    "<html><body><p>Hello " + user.firstName + ",</p><p>You tried to sign up but you already have an account with us.  If you don't remember your password please go <a href=" + securesocial.core.providers.utils.RoutesHelper.startResetPassword().absoluteURL(IdentityProvider.sslEnabled) + ">here</a> to reset it.</p></body></html>"
  }

  def getNotAuthorizedPage[A](implicit request: play.api.mvc.Request[A]): play.api.templates.Html =
  {
    securesocial.views.html.notAuthorized()
  }
  
  /**
   * Returns the welcome email sent when the user finished the sign up process
   *
   * @param user the user
   * @param request the current request
   * @return a String with the html code for the email
   */
   def getWelcomeEmail(user: securesocial.core.Identity)(implicit request: play.api.mvc.RequestHeader): String =
   {
     "not implemented"
   }

  /**
   * Returns the email sent when a user tries to reset the password but there is no account for
   * that email address in the system
   *
   * @param request the current request
   * @return a String with the html code for the email
   */
  def getUnknownEmailNotice()(implicit request: RequestHeader): String = {
    "not implemented"
  }

  /**
   * Returns the email sent to the user to reset the password
   *
   * @param user the user
   * @param token the token used to identify the request
   * @param request the current http request
   * @return a String with the html code for the email
   */
  def getSendPasswordResetEmail(user: securesocial.core.Identity,token: String)(implicit request: play.api.mvc.RequestHeader): String = {
    "not implemented"
  }

  /**
   * Returns the email sent as a confirmation of a password change
   *
   * @param user the user
   * @param request the current http request
   * @return a String with the html code for the email
   */
  def getPasswordChangedNoticeEmail(user: securesocial.core.Identity)(implicit request: RequestHeader): String = {
    "not implemented"
  }
}
