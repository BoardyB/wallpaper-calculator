# Interview assignment

## Wallpaper calculator

### Chosen design
The application was designed as a simple console application to serve the task of calculating the 
surface area of rectangular rooms in a manner defined in the assignment.
Although I've chosen a simple design, the intention was to have a well-defined architecture with an appropriately
separated service layer (according to SOLID principles - single responsibility) to make it easier to extend 
and more importantly test.

### Error handling
The application was designed to do the initial error-handling during the reading of the file, so that the service layer
can work on error-proof data. I've chosen this logic, because the input file is the only entry-point for the application,
if the service layer were to be opened to new interfaces, there should be additional error handling introduced for the
input parameters of the service methods.

If any errors are occurring during the file reading, for example:
the file was not present, or invalid data was provided, the application should stop immediately with a properly logged
error description.

### Potential improvement points
- I've restrained myself of using too many libraries, it felt like an unnecessary overhead for this task.
If the application were to be extended, I would include the using of Lombok for the removal of repetitive boilerplate
code, such as constructor generation, ToString-s etc.

- Additionally, the file reading could benefit from a more sophisticated solution from IOUtils, or similar libraries, 
for using it once, it felt unnecessary to include it.

- When searching for duplicated rooms, it would be more efficient to look through the string data itself instead of parsing
the data to Room objects and numeric values. The reason why I did not chose this is because it goes against the separation
of concerns I've tried to achieve with having a separate service for file operations, and another one for the room
related business logic. 

### Testing
I've created unit test cases for `RoomService` and `FileParserService` to cover most of their logic.
I did not include any integration test because it did not really feel necessary as there was no mocking involved in
the unit tests, and the logic is not that coupled to validate writing an integration test scenario for it.

### Documentation
Besides this README file, I've added javadoc to the service interfaces to explain what the methods are responsible for.

## Running the application
The application requires a JDK, recommended version is 21.
I've provided a maven-wrapper instance in the repository so that every other project dependency can be met upon cloning.
To start the application your need to run:

`./mvnw clean install` - to build the project and after that run

`java -jar target/wallpaper-calculator-1.0-SNAPSHOT.jar` - to start the built JAR file 

This is going to automatically execute the logic contained in `Main.java`.
I've added the provided `sample-input.txt` file as a resource file so that the application can easily load it's data.
After parsing it, the application calls the methods of the required tasks and logs the calculated solutions.