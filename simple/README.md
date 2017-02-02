Camel
----------------------------------

A Simple Demo to illustrate the concepts and terminology of Camel.

file-copy
---------------------

This demo is copying files from the data/inbox to data/outbox directory.

To run the example:

    mvn compile exec:java -Dexec.mainClass=com.utopian.camel.FileCopier
    
When the example is done, then you can see that the file has been copied from `data/inbox/message1.xml` to `data/outbox/message1.xml`.
