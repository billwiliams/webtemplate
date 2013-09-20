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
package controllers
import play.api.mvc.{Action, Controller}
import securesocial.core._
import play.api.Play.current
import play.api.cache.Cache


/**
 * The Login page controller
 */
object SocialLoginController extends Controller with securesocial.core.SecureSocial
{  
  def home = SecuredAction { implicit request =>    
    Ok(views.html.home())
  }
}
