## Error: Running a simple test case with this setup results in the following stack trace:

- **Java.lang.NoClassDefFoundError: org/junit/platform/engine/TestDescriptor**: error

Exception in thread "main" java.lang.NoClassDefFoundError: org/junit/platform/engine/TestDescriptor
at java.base/java.lang.Class.forName0(Native Method)
at java.base/java.lang.Class.forName(Class.java:375)
at com.intellij.rt.junit.JUnitStarter.getAgentClass(JUnitStarter.java:230)

Solution:
In IntelliJ, to correct the dependencies, we need to correct the pom.xml. The corrected pom.xml looks like this:

```xml

<dependencies>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-api</artifactId>
        <version>5.11.0-M2</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-engine</artifactId>
        <version>5.11.0-M2</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```