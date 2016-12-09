# Codebox - Media Sharing Web App
This is a Git Repository for a School Project, where we created a Media sharing web application, which we decided to call Codebox.
Codebox is used for storage, sharing and commenting different kinds of content. At this point it is recommended to only share posts which include images, though it is planned that in the future application will be able to share different kinds of media.

This repository includes folders: SchoolProject1 for actual Netbeans project with back-end and front-end files, SchoolProjectFront for just front-end, DB for data base structure and sql for developing the database and UI for design mockups created at the very beginning of project.

#Build project on Netbeans
In your Netbeans IDE, go to Team > Git > Clone. For Repository URL type https://github.com/jamiamikko/Media-Sharing-Web-App.git. Below that type in your Git username and password and click on Next. Select the master branch. Make sure your project directories are alright and click Finish. Netbeans will no build the project.

#Connect to server and data base on Netbeans.
On services tab in your Netbeans IDE, add a new Glassfish 4.1.1 server. Type in host: 10.114.32.59, domain: domain1 and leave user name and password blank at this point. Create your new server. Try to connect to server, it should ask for authentication. Type in User name: admin and password: T4js40t4js40. Your server should now be up and running. On you project settings, make sure you have correct server selected.

On services tab, also create new data base connection. Select MySQL as a driver. For host type in 10.114.32.59, for database: SchoolProject and for User name: oscar and password: T4js40t4js40. Create your database, and it should be up and running. Now the project should deploy.

#Presentation slides:
https://docs.google.com/presentation/d/13DYIGk_x8zCOulU7n6K6gqTQI7n4hJAwXX-KZkiZSP0/edit#slide=id.g1a483a52e9_0_25