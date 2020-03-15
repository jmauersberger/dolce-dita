# Dolce Dita
Simple and lean library to create DITA documents using Java

# Background
DITA - short for [Darwin Information Typing Architecture](https://en.wikipedia.org/wiki/Darwin_Information_Typing_Architecture "DITA on Wikipedia") is an XML data model for authoring and publishing. It is an open standard that is defined and maintained by the OASIS DITA Technical Committee.

# Objectives
There are many authoring tools to edit DITA documents and tons of projects to generate different formats our of DITA XML documents. However, there is no library (yet) to easily create documents with a few lines of Java or script code, concentrating on the essential content, rather then structure of DITA details. The initial goal is to support DOM like sequential creation of core DITA concepts as Maps, Topics and Tables.  

# Style
The library mainly addresses scripts so we offer setter and getter methods which typically are detected by script engines and offer language and script specific access as for example in Java Script Rhino. API and code wise we try to support two styles, sequential work, typical for structured generation of documents out of other information. And fluent code, as nice as some Google and the JDom APIs.

# Manifesto
Well, this project is from developers, for developers. Our Manifest is not as drastic as [these](http://programming-motherfucker.com/) but the core principles are similar: Programming is the key, do you speak it? 