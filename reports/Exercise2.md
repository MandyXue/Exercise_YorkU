# Programming Exercise

## Approaches to tackle the questions

#### Programming Process

1. Analyze main technologies and possible difficulties
2. Design and implement data structure
3. Implement XML parser, question by question

#### Main Technologies

1. Java

	I chose Java as the programming language for the following reasons:
	1. Java has its own APIs to read XML files, which makes the programming easier.
	2. Java program can run in different environments and operating systems.
	3. Java provides an exception handling mechanism, which can simplify error handling.
	4. Java is simple and smart for small programs like this.

2. SAX

	There are three main technologies for Java to read XML files: DOM, SAX, and Digester/JAXB.
	
	I chose SAX for the following reasons:
	1. SAX can perform well when meeting large amount of data because the memory required for a SAX parser is much less than a DOM parser. The amount of data the exercises provided is not very light. Since we don't need all of the data here to be stored, SAX is lighter and faster to use.
	2. Although SAX is not ideal if we want to change the contents of the XML files or if we hava strict requirements for XML validation, we only need to read the files here. Thus, we can avoid its main drawbacks here.

3. Git

	I used Git to control versions.
	
#### Data Structures & Parser Implementation

The file structure is as following:

```
programmingExercise/src/com/company
├── javabean/
│   ├── Committer.java
|   └── Report.java
├── xml/
|   ├── MyDefaultHandler.java
│   └── Parse.java
└── test/
    ├── CommitterTest.java
    └── ReportTest.java
```

As we can see, the ```javabean``` package includes two classes named ```Committer``` and ```Report```, corresponding to two kinds of data structure in SVN log and Jira bug reports. I only chose the attributes that I need to read as the attributes of the two classes.

In ```MyDefaultHandler```, I chose to customize the DefaultHandler to read different kinds of files by setting a ```fileType``` attribute to distinguish files of SVN log and bug reports. ```startElement()``` method is used to allocate room for all attributes. ```characters()``` method is used to read the content of each element and store the contents temporaryly according to the requirements. ```endElement()``` method is used to add each content we read from the element to the result attribute (```committers``` or ```reports```) so that we can get the results when we parse the file.

In ```Parse```, there's a ```main()``` method to read files and get results. The other methods are used as supporting methods for choosing the largest, average or smallest values.

## Results

The results of the three questions are as following:

```
The committer who submitted the largest number of code revisions is: stack, and his number of revisions is: 3926
--------------------------------------------
The maximum resolution time is: 9 years 38 days 15 hours 45 minutes 15 seconds.
--------------------------------------------
The minimum number of code revisions is: 0 times.
The average number of code revisions is: 0.4128 times.
The maximum number of code revisions is: 21 times.
```