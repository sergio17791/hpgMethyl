# Graphical interface for HPG-Methyl software

Web application for user registration, uploading .fastq files and introduction of necessary parameters to perform DNA methylation analysis and download of results.

HPG-Methyl is an ultrafast and highly sensitive Next-Generation Sequencing (NGS) read mapper and methylation context extractor.

You can see the HPG-Methyl project in the following link to its [GitHub](https://github.com/grev-uv/hpg-methyl).

## Building

- Create the *gradle.properties* file and configure the database parameters in it. Use the file *gradle.properties.example* as an example.

- It is recommended to use Docker for an automatic build. To do this you can launch the Gradle task *hpgMethylDeploy*. From project root directory you can use task via gradlew: 
``` 
./gradlew hpgMethylDeploy
```

- Register the first user through the registration form. You will have to give it the Admin role through the database.

- In the admin panel, review the application settings.

- Copy your generated bs-index files to the configured folder.

## Deployed

The project is temporarily deployed at [the following url](https://link-url-here.org)
