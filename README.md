![CodeCrunchers-Logo](https://user-images.githubusercontent.com/79836947/162907713-6968206f-8dec-44cb-a3d3-2ddaa1292cbe.png)

# Code Crunchers Game Respository

Code crunchers is a 2D platformer game developed by Jack Harris, Alex Vorraro, Sean Atherton, Jayjun Oh, Christopher Duncan and Bruno Fosenca for RMIT-CPT111-2022 (Building IT Systems), This project and related course work submissions yeilded a High Distinction result for all group members.

### What is code crunchers?

Welcome to Code Crunchers! In Code Crunchers, you play as a first year programming
student with no prior coding experience who must progress through 3 stages of
programming. But this is no ordinary programming course! You will need to navigate
through increasingly tricky levels and defeat enemies using your coding weapons, but
beware – the dreaded Master of Bad Coding lies in wait to challenge any would-be
programmers! Collect power ups by defeating enemies to help you progress and
defeat the Master, or try to complete the game as quickly as possible to earn a high
score!

Code Crunchers is a 2D platforming game.

Code Crunchers functions as a side-scrolling action game: the player needs to
progress through a series of increasingly difficult levels, each populated with a different
layouts and selection of enemies, with the aim of reaching and defeating the final boss
at the final game level. The player has the ability to move left and right, jump over
obstacles or between platforms, and shoot their weapon to defeat enemies. Enemies
may drop ‘power ups’ which will improve the player’s weapon damage or movement
and help them progress through the game. The game will record the player’s score,
and allow them to save their scores for sharing.


![CodeCrunchersGameplay](https://user-images.githubusercontent.com/79836947/162908702-8e2d736e-389d-4199-8589-a65731d0d392.gif)


### Technical goals and aims....

For this project we set out a number of programming technical goals that we wanted to achive, firstly the game should not use a game engine! we will write as much of the core code our selfs and developit using the Java Grapthics 2D library, Secondly we will generate our levels via procedual level generation, this means each level is always random and different orders, these levels will be loaded from a file that contains each room. Lastly we will have a real high score and user login system where the user authenticates with a API to access the game, then uses this authenticated key to post high scores back to the server at the end of each level attemp.

### Login Screen:

![LoginScreen](https://user-images.githubusercontent.com/79836947/162910566-29f670e0-9bad-472c-a2b8-8a7203fa9c64.png)

### Main Menu:

![MainMenu](https://user-images.githubusercontent.com/79836947/162911416-1a13db2c-d5e2-4598-8a31-7ed2688ae02e.png)

### Procedural Rooms File
Below is an example of our procedule room file, this stores all the infromation relating to generating the levels, first off we see that we add a number of variables to be loaded into the system, these are used when processing the level segments / rooms. We can see were we are setting the room width and room height as well as the amount of rooms in this file.

```
//set room variables first
//set room width must match the width of all the lines below,do not put a space after equals
{{room_width}} =21
//set room height must match the height of all the lines below,do not put a space after equals
{{room_height}} =11
//sets the amount of rooms in this file, this must match
{{room_count}} =5
000000000000000000000
000000000000000000000
000000000000000000000
000000000000000000000
000000000000000000000
000000000000000000000
000000000000000000000
000000000000000000000
111100000090000001111
111101110111011101111
111100000000000001111
//always finish a room with the new room flag as shown below on line 13 and 18
{{new_room}}
000000000000000000000
000000000000000000000
000000000000000000000
000000000000000000000
000000000000000000000
000000000000000000000
000000000000000000000
000090000000000000000
111111111000111111111
111111111000111111111
111111111000111111111
{{new_room}}
000000000000000000000
000000000000000000000
000000000000000000000
011111111111111111110
013333333333333333310
013339333333333333310
013311111111111113310
023333333333333333320
023333333393333333320
111111111111111111111
111111111111111111111
{{new_room}}
000000000000000000000
000000000000000000000
000000000000000000000
000000000000000000000
000000000000000000000
000000000100000000000
000000001100011110000
000900011100000000000
111111111100000000011
111111111100000000011
111111111100000000011
{{new_room}}
000000000000000000000
000000000000000000000
000000000000000000000
000000000000000000000
000000000000000000000
000000000000090000000
000000000100111100000
000000100000000000000
111100000000000001111
111100000000000001111
111100000000000001111
{{new_room}}
```


## RMIT University | Academic Integrity Warning


![image](https://user-images.githubusercontent.com/79836947/160737604-273c62fd-1503-4ce6-a292-a351665cc2e1.png#gh-dark-mode-only)
![image](https://user-images.githubusercontent.com/79836947/160738358-eaa88731-2a44-4004-ab9a-3d83a2268742.png#gh-light-mode-only)

Cheating is a serious offense:

> "What happens if you get caught cheating at RMIT? For serious breaches of academic integrity, students can be charged with academic misconduct. Possible penalties > include cancellation of results and expulsion resulting in the cancellation of a student's program."

Academic integrity - RMIT University

### Links:

 [RMIT Academic Integrity Awarness Micro Credential](https://www.rmit.edu.au/study-with-us/levels-of-study/short-courses/academic-integrity-awareness)
 
 [Academic Integrity at RMIT](https://www.rmit.edu.au/students/my-course/assessment-results/academic-integrity)
