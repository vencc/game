## java五子棋客户端

本系统是使用JAVA语言写的支持在线联机对战的五子棋客户端游戏。

这只是一个新手项目，没有使用到任何框架与高级技术。

本地测试不需要修改任何东西就可以启动并玩人机对战。

如果想联机，就需要创建数据库，并创建 user 表，里面包含的字段顺序为 name,fileName,winNum,loseNum,tiedNum.  
并修改 BaseDao 中的数据库变量，和 MyClient 中的 ip 地址，就可以进行联机对战了。

## Spreadsheet-driven dungeon mode

The home screen also includes **深渊远征** (Abyss Expedition), a solo, 10-floor
action roguelike based on the progression, character, floor, monster, boss,
and item spreadsheets. Pick a character, clear two combat rooms and a floor
boss, choose rewards, then continue down to the Abyss. XP and character
unlocks are saved in `.vencc-delver.properties` under your home directory.
Run `chess.Main` and choose **深渊远征** from the home screen.
On Windows, you can also double-click `Run-Game.bat` in the repository folder.
It checks for JDK 8 or later, compiles the game, and starts it automatically.

Controls: WASD or arrow keys to move; hold Space or the mouse button to attack;
Q uses the character ability; R uses a healing potion; F throws a bomb; T
spends a key to reroll a reward; 1/2/3 spend stat points on damage, health, or
speed; Esc returns to the home screen.

by [vencc](https://github.com/vencc/game)
