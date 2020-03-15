/*
 * Copyright the Dolce Dita contributors.
 * All rights reserved.
 */
package dita;

import static org.junit.Assert.*;

import java.io.StringWriter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

@SuppressWarnings({ "nls", "javadoc" })
public class DitaTopicTest extends DitaTest {

	@Rule
	public TestName name = new TestName();

	@Test
	public void testTopicInitial() throws Exception {
		// PREPARE
		DitaTopic topic = new DitaTopic("Foo", "foo");
		StringWriter writer = new StringWriter();

		// RUN
		topic.write(writer);

		// ASSERT
		assertResult(writer.toString(), name.getMethodName());
	}

	@Test
	public void testTopicTitle() throws Exception {
		// PREPARE
		DitaTopic topic = new DitaTopic("Foo", "foo");
		topic.setTitle("Foo Bar Foo");
		StringWriter writer = new StringWriter();

		// RUN
		topic.write(writer);

		// ASSERT
		assertResult(writer.toString(), name.getMethodName());
		assertEquals("Foo Bar Foo", topic.getTitle());
	}

	@Test
	public void testTopicSimpleParagraph() throws Exception {
		// PREPARE
		DitaTopic topic = new DitaTopic("Foo", "foo");
		topic.addParagraph(
			"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
		StringWriter writer = new StringWriter();

		// RUN
		topic.write(writer);

		// ASSERT
		assertResult(writer.toString(), name.getMethodName());
	}

	@Test
	public void testTopicSectionWithParagraphs() throws Exception {
		// PREPARE
		DitaTopic topic = new DitaTopic("Foo", "foo");
		topic.openSection("The standard Lorem Ipsum passage, used since the 1500s");
		topic.addParagraph(
			"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
		topic.addParagraph(
			"Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.");
		topic.addParagraph(
			"Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.");
		// its not required to close the section

		StringWriter writer = new StringWriter();

		// RUN
		topic.write(writer);

		// ASSERT
		assertResult(writer.toString(), name.getMethodName());
	}
}
