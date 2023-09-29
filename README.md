#### Hyttetur

Team: Karolina, Mathias, Thomas, Finn
TeamNavn: KarmøyWarriors
ProsjektNavn: Hyttetur
Gruppenummer: 5


## Kort beskrivelse av spillet 
Det er top down shooter spill, hvor handlingen går ut på at
spilleren befinner seg på en hyttetur og har som mål å skyte øl på de andre hytteturdeltakerne (fiendene)
for å få de fulle nok til å sovne. Spillet avsluttes når alle fiendene er beskjempet,
og man har kommet seg gjennom hele hytten.
Spillet har også et power up system der du kan velge en ny power up hver gang du levler opp.

## Hvordan spillet brukes / tastetrykk sentral
W - walk up
A - walk left
S - walk down 
D - walk right

right mouse click - shoot/use

P - pausemeny mens man spiller


## Hvordan koden kjøres

VIKTIG! Vær i new_main branchen

kjør java -jar .\target\gdx-app-1.0-SNAPSHOT-fat.jar

hvis dette ikke funker kjør mvn clean package og prøv
java -jar .\target\gdx-app-1.0-SNAPSHOT-fat.jar igjen

eller

Klone repositoriet: git@git.app.uib.no:karmoywarios/hyttetur.git

Åpne prosjektet i IntelliJ eller lignende programmer.

Kjør programmet fra Main.java-metoden. Du bør bruke Run øverst til høyre, eller til venstre for Mainmetoden.
## Kilder
- Kilder ligger i "kilder.md" i doc

## Annet

# INF112 libGDX + Maven template 
Simple skeleton with [libGDX](https://libgdx.com/). 

# Known problems
Etter hver testklasse har vi en @AfterClass som avslutter "mock-spillet" for å dispose det. Noen av disse feiler.

~~On Mac OS X:~~

* ~~The application won't start without giving the JVM the `-XstartOnFirstThread` option. In Eclipse, you can set this up with *Run → Run Configurations...*, then choosing the *Arguments* tab and adding `-XstartOnFirstThread` to *VM argument*. [Check this screenshot.](https://git.app.uib.no/inf112/22v/lectures/-/raw/master/img/eclipse-vm-args.png)~~

* ~~On Macs with the M1 processor, a newer version of libgdx is needed. The Maven [`pom.xml`](pom.xml) file has been set up to use version `1.10.1-SNAPSHOT` automatically.~~

# Maven Setup
This project comes with a working Maven `pom.xml` file. You should be able to import it into Eclipse using *File → Import → Maven → Existing Maven Projects* (or *Check out Maven Projects from SCM* to do Git cloning as well). You can also build the project from the command line with `mvn clean compile` and test it with `mvn clean test`.

Pay attention to these folders:
* `src/main/java` – Java source files go here (as usual for Maven) – **IMPORTANT!!** only `.java` files, no data files / assets
* `src/main/resources` – data files go here, for example in an `assets` sub-folder – **IMPORTANT!** put data files here, or they won't get included in the jar file
* `src/test/java` – JUnit tests
* `target/classes` – compiled Java class files


```xml

	< !-- FIXME - set group id -->
	<groupId>inf112.skeleton.app</groupId>
	< !-- FIXME - set artifact name -->
	<artifactId>gdx-app</artifactId>
	<version>1.0-SNAPSHOT</version>
	<packaging>jar</packaging>

	< !-- FIXME - set app name -->
	<name>hyttetur</name>
	< !-- FIXME change it to the project's website -->
	<url>https://git.app.uib.no/karmoywarios/hyttetur</url>
```

	
## Running
You can run the project from Eclipse, or with Maven using `mvn exec:java`. Change the main class by modifying the `main.class` setting in `pom.xml`:

```
		<main.class>inf112.skeleton.app.Main</main.class>
```

## Jar Files

If you run `mvn package` you get everything bundled up into a `.jar` file + a ‘fat’ Jar file where all the necessary dependencies have been added:

* `target/gdx-app-1.0-SNAPSHOT.jar` – your compiled project, packaged in a JAR file
* `target/gdx-app-1.0-SNAPSHOT-fat.jar` – your JAR file packaged with dependencies

Run Jar files with, for example, `java -jar target/gdx-app-1.0-SNAPSHOT-fat.jar`.

## Git Setup
If you look at *Settings → Repository* in GitLab, you can protect branches – for example, forbid pushing to the `main` branch so everyone have to use merge requests.

## Eclipse Setup

It's usually smart to change Eclipse's Maven settings so that it'll automatically download Javadocs and the source code for your dependencies:

![Turn on Download Sources and Javadoc](https://git.app.uib.no/inf112/22v/lectures/-/raw/master/img/eclipse-maven.png)
