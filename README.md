webtemplate
===========

webtemplate with social media logins and backend db that stores logged in user. this makes web prototyping with social media logins faster

* db configuration in conf/application.conf
* login.scala.html is the view user sees before login
* home.scala.html is the view user sees after login
* modify conf/play.plugins to add support for more social network

social logins are built on top of securesocial. db access is built on top of JPA, default db setup is with postgres
