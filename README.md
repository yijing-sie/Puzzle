# Puzzle

This is an assignment for Software Design for Visual Environments

* The goal is to detect and handle **touches**, **swipes**, and **multiple taps** from users when they are solving the puzzle in this application
* The answer to this puzzle is **I LOVE MOBILE PROGRAMMING USING JAVA**
* [puzzlev4](java/com/jblearning/puzzlev4) folder contains all my Java files
* [res](res) folder contains the images and xml files for this application
* [MainActivity.java](java/com/jblearning/puzzlev4/MainActivity.java) handles **touches**, **swipes**, and **multiple taps** from users
* [DynamicSizing.java](java/com/jblearning/puzzlev4/DynamicSizing.java) implements the `DynamicSizing` class so that the font size for all the puzzle pieces is always **optimal** when running on a device with **arbitrary** scree size 

* There are **4** features in this application:

1. The colors of all the puzzle pieces are randomly selected whenever users open the application.
2. Users can click and drag each puzzle pieces to swap their positions
3. Users solve the puzzle when the puzzle pieces are in the order of **I LOVE MOBILE PROGRAMMING USING JAVA** from top to down, and then they cannot move the pieces anymore
4. Users can double-tap the **MOBILE** puzzle piece after they solved the puzzle to change **MOBILE** to **ANDROID**

Below is the demonstartion of runnning Puzzle app on a real phone (Samsung A71) :



https://user-images.githubusercontent.com/84282744/189687623-5b4f8906-798a-42db-9b58-2628055a6e6a.mp4

