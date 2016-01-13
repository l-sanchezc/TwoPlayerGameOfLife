Name and student ID number:
	Lazaro Sanchez Campos (A20362245)
	
i. Give a general description of your implementation.
A. How did you decide to store the grid (i.e. what data type for the grid, what data type for the cells)?
First of all, the program reads the generation file and check that it is valid. Then, it stores each line of the generation file in one 
ArrayList of Strings. Since the length changes with each line of the generation file, we do not know the size until we read the generation 
file. Later, my program introduces the data of the ArrayList in a 2D String Array, because it is very easy to change its values, and also
to get its content of each coordinate.

B. Walk through the methods called during the generation of a new state.
Every method has a comment with its explanation in the code.
Basically the execution is: readInitial -> getState -> print -> [run -> getNeighbors -> countNeighbors -> getNeighbors -> run ->...] 
-> getState -> print -> [run -> getNeighbors -> countNeighbors -> getNeighbors -> run ->...] ... (until return of method "run" != null)


ii. What specific problems or challenges did you have implementing your solution? For example,was there a particular requirement that 
you had difficulty implementing? Or perhaps there was a particularly nasty bug in your implementation you had problems tracking down.
I had difficulties implementing my method "getNeighbors", because when I generated the new array my method "run" was changing the
earlier array since I had written next = board. I had spent a lot of time in one method and it did not have any problems. Also, I had
spent a lot of time creating the class TwoPLayerGameOfLifeTest since I had not worked so much with JUnit.

iii. Were there any requirements that were not implemented or not implemented properly in your solution? If so, please elaborate.
No.

iv. Were there any requirements that were vague or open to interpretation that you had to make a decision on how to implement? 
How did you elect to interpret them?
Yes, for example there are not any specifications to know if the generation file is invalid. So, I had to think about the characteristics of
an invalid generation file such as invalid characters, numbers of rows or columns different from the indicated numbers at the top of the
generation file or first line of generation file misspelled. 

v. How would you rate the complexity of this MP on a scale of 1 to 10 where 1 is very easy and 10 is very difficult. Why did you give this rating?
My rate of this MP is 9, since I spent more time that I thought I would spend in the first assignment. I think also that there is a great 
difference between the exercises that we have done in class and this assignment. Therefore,from my point of view this assignment has been 
a big challenge to complete. 