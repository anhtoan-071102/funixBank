BASTA COMPUTING SOFTWARE PRODUCT INFORMATION


TITLE

AppToService v2.2 (formerly known as ServiceIt)
Copyright (C) 1998-1999 Basta Computing, Inc.
All rights reserved.

This is a freeware product.
Limited distribution (see the license agreement for details).


DESCRIPTION

AppToService is a Windows NT Unicode console application that lets 
you run regular applications as NT Services. This allows you to have 
some of the benefits of a Windows NT service, such as the ability to 
run an application even when no user is logged on the computer, the 
ability to run an application under a specified user account, the 
ability to allow an application to survive logoff/logon sequences, 
hence saving the overhead of re-starting it for each new user, 
and so on.

For instance, you can run a dialer application as a service to 
automatically connect a computer to the Internet following 
unattended reboots.


RELEASE NOTES FOR ServiceIt USERS

AppToService does not interfere with ServiceIt.

If you want to migrate services created with ServiceIt to 
AppToService, remove them using ServiceIt then re-install 
them using AppToService.


HOW DOES IT WORK?

AppToService is a dual execution mode component that runs as:

(1) A console application to install and remove services, 
in addition to providing usage and other helpful information.

(2) A service encapsulating an application specified by the user. 
The application is opened when the service is started, and it is 
closed when the service is stopped.

For each application that you need to run as a service, execute 
AppToService with the appropriate parameters to create a service 
encapsulating the application. Then manage the service using a 
common Windows service manager such as the Services Control Panel 
applet or the Net command. When the service is no longer needed, 
remove it by executing AppToService with the appropriate parameters.


EXAMPLE

Say you want to run a dialer application, namely MyApp, as an NT 
service in order to start it automatically during unattended reboots, 
and have it connect your computer to the Internet without the need 
to logon.

1- Use AppToService to install a service that would run MyApp when 
it is started. At the command line, enter:

    AppToService /Install "C:/Program Files/MyApp/MyApp.exe"

    NOTE: Several installation options are available to customize 
    services. Use the /? parameter for a complete list of options.

2- Open the Control Panel Services applet. You will find the 
following new service in the list:

    AppToService MyApp

3- Next, make sure that everything is OK: select the service by 
clicking it, then perform the following test:

    (a) Press the Start button. This should open MyApp.
    (b) Press the Stop button. This should close MyApp.

    NOTE: You can start and stop the service from the command line 
    as well by using the Net command like this:

        Net Start "AppToService MyApp"
        Net Stop "AppToService MyApp"

4- Setup the startup options to make MyApp start automatically:

    (a) Press the Startup button.
    (b) In the Startup Type group, check the Automatic checkbox.
    (c) In the Log On As group, select This Account.
    (d) Enter a user name and password with enough privileges.
    (a) Since MyApp will start automatically at boot time, you will want 
        to remove its shortcut from the Startup folder if any.

5- That's it. When you no longer need to use MyApp as a service, you 
can remove it using the following command line:

    AppToService /Remove "MyApp"


TIPS:

- For general help, enter: AppToService /?
- For more examples, enter: AppToService /??
- If you close an application started by AppToService, the service 
  will stop automatically.
- If the /Arguments data includes double quotes, preceed each one of them 
  with 3 slashes. E.g., /Arguments:"unquoted \\\"quoted\\\" unquoted".
- If you are using /Directory and want to terminate the path with a 
  slash, use a forward slash (/) instead of a backslash (\). The \" 
  character combination has a special meaning in Windows. To avoid this 
  and other potential parsing problems, we recommend using the forward 
  slash (/) exclusively to separate components in a path.
- The ability of an application to survive logoff depends on its design.
- An application will generally survive logoff if you do not allow the 
  associated service to interact with the desktop. However, this will 
  prevent the application window from being displayed.


REVISION HISTORY

- v2.2 on 1999-09-14: Added the /Name flag to allow the creation of 
  multiple services hosting the same client application with different 
  arguments, and improved the ability to close client applications when 
  services are stopped.
- v2.1 on 1999-04-29: Added the /Directory flag to specify the working 
  directory.
- v2.0 on 1998-12-01: Changed naming conventions and added /DependsOn.
  WARNING: This release is not backward compatible.
- v2.0 Beta on 1998-11-15: Changed the application name from 
  ServiceIt to AppToService, and added support for command line 
  arguments, execution priority, window state, and startup type.
  WARNING: This release is not backward compatible.
- v1.0 on 1998-05-10: Initial release.
- v1.0 Beta on 1998-05-01: Beta release.


SYSTEM REQUIREMENTS

- Windows NT v3.1 or later.
- Enough free disk space to accommodate the software (less than 100KB).


PACKAGE CONTENTS

- AppToService.exe: Program executable.
- ReadMe.txt: This file.
- File_ID.diz: Product identification summary.
- LicenseAgreement.txt: End User License Agreement.


INSTALLATION

1- Create an AppToService folder on one of your local disks. Since 
several applications may come to depend on this system utility, it 
may be a good idea to choose the drive where Windows is installed.
2- Copy the files included in this package to the AppToService folder.


UPGRADES

1- Open the Services Control Panel applet and stop all the services 
using AppToService (their names begin with "AppToService").
2- Locate your current AppToService files, back them up just in case, 
then overwrite them with the new files.
3- Restart the services that you had stopped.


UNINSTALLATION

1- Open the Services Control Panel applet to view all the services 
created using AppToService (their names begin with "AppToService").
2- Use AppToService to remove all the AppToService services.
3- Locate and delete the AppToService folder and files.

AppToService does not store any other configuration information 
in the registry or any other files.


CONTACT

Web site: http://www.basta.com

E-mail:

   info@basta.com
   marketing@basta.com
   sales@basta.com
   registrations@basta.com
   support@basta.com
   bugs@basta.com
   suggestions@basta.com
   guestbook@basta.com

Fax: +1 (425) 889 8745

Address:

   Basta Computing
   P.O. Box 2173
   Kirkland, WA 98083-2173
   USA


FURTHER INFORMATION

For the latest news about our products and services, 
please visit our web site at http://www.basta.com
