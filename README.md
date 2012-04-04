Java code for analyzing voting data from ES&S iVotronic Direct Recording Electronic (DRE) machines and Unity Voting System Election Report Manager software.

This is the Java code written by Duncan A. Buell described in the paper:

 _Auditing a DRE-Based Election in South Carolina_,
 D. A. Buell, University of South Carolina; E. Hare, Clemson University; F. Heindel; C. Moore; B. Zia, League of Women Voters of South Carolina
 presented at the Electronic Voting Technology Workshop/Workshop on Trustworthy Elections (EVT/WOTE '11)
 <http://static.usenix.org/events/evtwote11/tech/>

See the "South Carolina Voting Information" web site for background: <http://www.scvotinginfo.com/>

See also the related perl code by Chip Moore:
 <https://github.com/chip-moore/sc-audit>
or
 <https://github.com/nealmcb/election-audit-log-toolkit>

File Descriptions
=================

For descriptions of the data files resulting from running the vote analysis code, see the FileDescriptions.docx file.

These are divided into three categories:

* Vote Counts
* Files reporting the election process
* Statistics and anomaly reports
 * This inclues the EXCEPTIONS file, listing errors, actions contrary to procedure, and such.

Code documentation
==================

The documentation for the global variables in the code is in the AAA.java file.

Sample data
===========

Data for the 2010 general election in two counties in South Carolina, Horry County and McCormick County, are provided, along with the results from running the software on them.

License
=======
This is open source software, using the "MIT" (aka "X11") license.
See the LICENSE.txt file for details.
