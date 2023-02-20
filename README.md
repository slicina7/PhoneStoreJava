# Phone store
This application is designed as a model of a phone store used to buy phones. 


## To run the CLI

```bash
mvn package -P cli-app 
cd target
java -jar projekat-cli-jar-with-dependencies.jar [option] (parameters) 
```

## To run the GUI

```bash
mvn package -P gui-app
java --module-path "PATH-TO-JAVAFX" --add-modules javafx.controls,javafx.fxml -jar projekat-gui-jar-with-dependencies.jar 
```


## Running Tests

To run tests, run the following command

```bash
  mvn test
```

