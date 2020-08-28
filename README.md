# Teamspeak Support Bot
A simple Teamspeak Bot to automats your support workflow

# Installation
## Download
Download ![TeamspeakSupportBot.zip](https://github.com/Authum09/TeamspeakSupportBot/releases/download/v1.0/TeamspeakSupportBot.zip), unzip it and move both files into a directory on your server.
## Configure config.yaml
Look into the example.config.yaml and enter your own data into it
You will need a support channel, aswell as two server groups (one to support and a busy group)
## Start
Run in your command line: `java -jar TeamspeakSupportBot.jar`
# How to use it
After installing this .jar onto your server and running it, you will see a query connected on your teamspeak
![](https://uploads.authum.de/XISO8/RItOhOji94.png/raw)

Now if your Supporter adds himself to support group, the bot will change the channel name of support channel to indicate how many supporter are available
![](https://uploads.authum.de/XISO8/yaPAhoMe18.png/raw)

If any supporter is available, user can join into the support channel and the first supporter in support list get messaged and added to busy group
![](https://uploads.authum.de/XISO8/mAHotEWA08.png/raw)

After writing yes the supporter gets ,together with the user that needs support, moved into a new sub channel named after the user that get support
![](https://uploads.authum.de/XISO8/JIHuPEVa04.png/raw)

After the support conversation has ended both user and supporter can leave the channel, 
it gets removed and the supporter gets removed from busy group and is ready again to support
