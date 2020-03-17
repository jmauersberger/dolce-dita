// PREPARE
var topic = new dita.DitaTopic("Foo", "foo");
topic.openSection("The First");
topic.addParagraph("welcome to my first DITA generation script");
topic.closeSection();
topic.openSection("The Second");
topic.addImage("Side Image", "images/side.svg");
topic.closeSection();

var writer = new java.io.StringWriter();

// RUN
topic.write(writer);

true;