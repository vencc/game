## java五子棋客户端

本系统是使用JAVA语言写的支持在线联机对战的五子棋客户端游戏。

这只是一个新手项目，没有使用到任何框架与高级技术。

本地测试不需要修改任何东西就可以启动并玩人机对战。

如果想联机，就需要创建数据库，并创建 user 表，里面包含的字段顺序为 name,fileName,winNum,loseNum,tiedNum.  
并修改 BaseDao 中的数据库变量，和 MyClient 中的 ip 地址，就可以进行联机对战了。

by [vencc](https://github.com/vencc/game)




Java Gomoku Client Application
This application is a Gomoku (Five in a Row) client developed in Java, designed to support online multiplayer gaming.

This project is intended for beginners and does not incorporate complex frameworks or advanced programming techniques.

For local testing, the application can be launched and played against an AI opponent without any modifications.

For online gameplay, a database must be established with a user table comprising the following columns in sequence: name, fileName, winNum, loseNum, tiedNum. Additionally, modifications are required for the database parameters in the BaseDao and the IP address in MyClient to facilitate online matches.

Developed by vencc.
