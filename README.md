# **Overview**
I built a TodoList app to get hands-on experience with Android app development. Here’s a breakdown of my process:

Learning Phase: I started by watching a tutorial on Android app development to get the basics down.
Development Phase: I jumped straight into creating the TodoList app.
    
  ### **Key Components**
  
TodoList Class: I created a TodoList class for each to-do list.
Custom LinkedList: I implemented my own linked list to manage the tasks. This included a Node class to represent each task.
  
  ### **How the App Works**
  
 Creating a TodoList: You can create a new to-do list by pressing the plus button at the bottom right of the screen. Each to-do list must have a unique name.
 Adding Tasks: Once you add a name, a new to-do list is created with your specified purpose.
  
  ### **Requirments**
  
  Clickable Buttons: The to-do list names need to be displayed as buttons. When you click a button, it should take you to the corresponding to-do list with its tasks.
  Data Persistence: The app needs to save the data so that when you reopen it, you can quickly access your to-do lists and tasks.

### **Main Activity**

The main activity is responsible for storing the names of the to-do lists and creating new ones. When a new to-do list is created, its name is sent to the second activity where you can view and manage the tasks for that list.

### **Data Storage**

To save the data, I used an object called SharedPreferences, which acts like a cache. Whenever the app loads, it retrieves the names of the to-do lists from SharedPreferences and displays them as buttons. The names are stored in a set.

### **Handling Updates**

SharedPreferences has a known issue with updating string sets. To work around this, whenever a new name is added, I create a new string set with the updated information and save it back to SharedPreferences. Note that the buttons only store the name of the to-do list.

### **Navigating Between Activities**

When a button is clicked, it sends the name of the to-do list to the second activity using an Intent. You can think of an Intent as a universal hashmap. You put in a key-value pair (the name of the to-do list), and in the second activity, you retrieve it with:

    Intent intent = getIntent();
    String name = intent.getStringExtra("NAME");

### **Second Activity**

The second activity is where the tasks for each to-do list are managed. Tasks are stored in a key-value format using SharedPreferences. However, SharedPreferences only stores simple data types like strings and integers.

### **Storing Tasks**

I had two options for storing tasks:

  1.Save a list containing the string names of the tasks in SharedPreferences and rebuild the linked list each time the app is opened.
  
  2.Use JSON to convert the task list to a string with Gson and store that in SharedPreferences.
  
I chose the latter because it made more sense to store the linked list directly as a JSON string rather than converting it back and forth.

### **Additional Features**

I planned to add a delete function, but the main goal of this project was to gain experience with Android app development. I also created a class called NodeButton, which is a button that stores a node. This allows for operations like deletion or renaming of tasks. When you hold down on a task, a menu pops up with options to delete, rename, or move the task by index. This approach is faster than using a list, especially for deletion operations.
