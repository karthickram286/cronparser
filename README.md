# Cron Parser

This is a simple java utility which takes in a cron expression and displays it in a human readable format.

---

## Requirements

- JDK version 11 or above
- Gradle version 7.5+

## Building locally

- Expand the zip file and enter the folder in command line
- Execute the tests locally to make sure everything is fine
```shell
./gradlew clean test
```
- Build the package
```shell
./gradlew build
```

## Running the project

You can run the project by executing the following command after the build step
```shell
java -jar build/libs/cronparser-1.0.0.jar "*/15 0 1,15 * 1-5 /usr/bin/find"
```

You can also create an alias locally to simplify the command
```shell
alias cronparser="java -jar build/libs/cronparser-1.0.0.jar"
```

After creating the alias you can execute it as
```shell
cronparser "*/15 0 1,15 * 1-5 /usr/bin/find"
```

Note: The alias will only work for the current session. 
To have a global alias you need to add it in the corresponding shell you use.
Example, `bashrc` or `zshrc`.

