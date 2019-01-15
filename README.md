# tcp-clent-server-app
Tcp server application for Protey task. Client code u can find [here](https://github.com/nice2h8u/tcp-client). 

## Tcp communication with client
Server communicates with client through tcp. Server and client communicate each other with sending jsons. Json serialize from @entity Message , which consist of typeOfMessage(add,get,delete,search and etc..) and ArrayList of Dictionaries. To get entyties from db, server use Repository from Spring.   

