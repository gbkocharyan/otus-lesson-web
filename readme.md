# UI Testing Framework
This project is a UI testing framework built with **Selenium WebDriver**, **JUnit 5**, and **Google Guice** for dependency injection. It includes reusable components and pre-configured modules for efficient and scalable test automation.
## Project Structure
- **extensions/**: Contains test extensions, such as `UIExtension`, for handling setup and teardown logic.
- **factory/**: Includes `WebDriverFactory`, responsible for managing WebDriver instances.
- **modules/**: Contains Guice modules (`GuicePagesModule` and `GuiceComponentsModule`) to inject dependencies like page objects and components.
- **pages/**: Contains page classes such as `MainPage`, `CoursesPage`, and `CoursePage`.
- **components/**: Includes reusable UI components like `HeaderComponent` and `TrainingComponent`.

## Prerequisites
- **Java**: JDK 21 or higher is required.
- **Maven**: Ensure Maven is installed and configured.
- **Browser Drivers**: Corresponding WebDriver executables (e.g., ChromeDriver) must be accessible in your system's PATH.
- **Dependencies**: Managed via Maven (defined in `pom.xml`).

## Getting Started
### Clone the Repository
``` bash
 git clone https://github.com/gbkocharyan/otus-lesson-web.git
cd otus-lesson-web
```
### Install Dependencies
Run the following Maven command to download and install dependencies:
``` bash
mvn clean install
```
## Configuration
### Browser Configuration
- The browser type is determined by the `browser` system property.
- To set the browser (e.g., Chrome), pass the argument when running tests:
``` bash
-Dbrowser=chrome
```

Alternatively, you can place the executable in the project directory and provide the path programmatically.
## How to Run Tests
### Using Maven
To execute all tests, run:
``` bash
mvn clean test
```
To run test remotely, run:
``` bash
mvn test -Dbrowser=chrome -DremoteIp=45.132.17.22 -DbaseUrl=https://otus.ru
```
