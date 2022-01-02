This is a JetBrains Academy project. It serves for educational purposes. The project is functional. User input verification is not fully implemented. 
I will put a description of the last part of the project in order to make it clear what is being discussed in this project.

Description.
Our temporary solution in the previous stage was bad because of every time you want to interact with concrete classes you must check the Boolean field then apply different code according to the concrete class. So far so good, you implement this behavior every time you need to. However, in a larger application, there can be 100 places or more where you must do this. And at the end of the day, a new feature request might come in: implement a third type of records, something that represents an automated system with robots serving the calls. You would be very annoyed that you were forced to find all the places where you interact with concrete classes.

The solution to this problem is a polymorphism.

Basically, you need to implement the functionality in one place inside a concrete class. All of the derived classes should implement this method, and in the base class, there should be an abstract method. In the code, you actually call this abstract method and get different code executions in the derived classes.

To solve your problem, you should create several methods:

A method that returns all of the possible fields this class is able to change.
A method that takes a string that represents a field that the class is able to change and its new value.
A method that takes a string representation of the field and returns the value of this field.
After that, you don't need any Boolean workarounds and type casts; the code will be nice and clean.

Also, in this stage, you should implement saving to a file and loading from a file. You can save the Contacts using serialization. You should specify a file you are working with by a command-line argument. This would automatically save the Contacts on the hard drive after each action that modifies data. If you don't specify an argument, then you should create a new Contacts and keep it in memory. If you specify a file that doesn't exist, you should create an empty Contacts and save all changes to the newly created file.

Also, in this stage, you should implement search functionality. For this, you can append all of the values from all of the fields and check if this string contains a search request. It should support regular expressions, too! And, of course, it should be case insensitive.

Use an empty line to separate different actions, like in the example.

