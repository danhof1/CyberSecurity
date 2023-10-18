You will need to change the file paths in the programs to get them to work, or else you'll get an error

Also, Select Directories and Custom Scan work as one.
Select Directories/CustomScan works like this:
-You run the program and it adds those custom files to cache and customScan.txt
-You can run Select Files again and it will wipe Cache and then allow you to select a whole new batch of files to send to CustomScan.txt

RMwhitelist.java and addToWhitelist.java must be executed in admin mode

-errors to fix: 
--CustomScan.txt needs to be wiped when finished scanning
In hindsight I should have put them in one folder.
--RmWhitelist.java needs to remove the directories from whitelist.txt, and hitlist.txt needs to wipe after program finishes 
