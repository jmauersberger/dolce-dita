/*
 * Copyright the Dolce Dita contributors.
 * All rights reserved.
 */
package dita;

import static dita.DitaFactory.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

import org.jdom2.DocType;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * DITA topics are the basic units of DITA content and the basic units of reuse. Each topic contains a single subject.
 * Topics may be of specific specialized information types, such as task, concept, or reference, or may be generic, that
 * is, without a specified information type.
 *
 * @see <a href="http://docs.oasis-open.org/dita/v1.2/os/spec/archSpec/topicover.html">The OASIS</a>
 */
public class DitaTopic extends Document {

	private static final long serialVersionUID = 1L; // Satisfy the compiler.

	/**
	 * TODO: Well, you have to do some real actual work
	 */
	public static String DocType_Name = "topic"; //$NON-NLS-1$

	/**
	 * TODO: Well, you have to do some real actual work
	 */
	public static String DocType_PublicID = "-//OASIS//DTD DITA Topic//EN"; //$NON-NLS-1$

	/**
	 * TODO: Well, you have to do some real actual work
	 */
	public static String DocType_SystemID = "topic.dtd"; //$NON-NLS-1$

	private Element root, title, body, active;

	/*
	 * True if a section was opened and not yet closed, i.e. the active element is a section.
	 */
	private boolean sectionOpen;

	/**
	 * Create a new {@link DitaTopic} with given name and id.
	 *
	 * @param name
	 *            topic name (its title)
	 * @param id
	 *            unique id, never <code>null</code>
	 */
	public DitaTopic(String name, String id) {
		setDocType(new DocType(DocType_Name, DocType_PublicID, DocType_SystemID));
		root = new Element(DocType_Name);
		root.setAttribute("id", id); //$NON-NLS-1$
		root.setAttribute("rev", "0.1"); //$NON-NLS-1$ //$NON-NLS-2$
		setRootElement(root);

		title = newTitle(name);
		root.addContent(title);

		body = new Element("body"); //$NON-NLS-1$
		root.addContent(body);
		active = body;
	}

	/**
	 * @param text
	 *            the new topic title
	 */
	public void setTitle(String text) {
		title.setText(text);
	}

	/**
	 * @return the current topic title
	 */
	public String getTitle() {
		return title.getText();
	}

	/**
	 * Adds a simple text paragraph to the active element.
	 *
	 * @param text
	 *            the text, never <code>null</code>
	 * @return this
	 */
	public DitaTopic addParagraph(String text) {
		active.addContent(newParagraph(text));
		return this;
	}

	/**
	 * Adds a simple image figure to the active element.
	 *
	 * @param text
	 *            the figure text, never <code>null</code>
	 * @param href
	 *            the image URL, absolute or relative
	 * @return this
	 */
	public DitaTopic addImage(String text, String href) {
		Element figure = newFigure().addContent(newTitle(text));
		Element imgage = newImage().setAttribute("href", href); //$NON-NLS-1$
		figure.addContent(imgage);
		active.addContent(figure);
		return this;
	}

	/**
	 * Adds a new empty section.
	 *
	 * @param text
	 *            section text/title
	 * @return thus
	 */
	public DitaTopic addSection(String text) {
		openSection(text);
		closeSection();
		return this;
	}

	/**
	 * Opens a new section. The new section becomes the "active" element until it is closed.
	 *
	 * @param text
	 *            section text/title
	 * @return this
	 * @see #closeSection()
	 */
	public DitaTopic openSection(String text) {
		if (sectionOpen) {
			closeSection();
		}

		Element section = newSection().addContent(newTitle(text));
		body.addContent(section);
		sectionOpen = true;
		active = section;
		return this;
	}

	/**
	 * Closes any open section. Does nothing if there is no open section.
	 *
	 * @return this
	 */
	public DitaTopic closeSection() {
		active = body;
		sectionOpen = false;
		return this;
	}

	/**
	 * Write the DITA Topic to a {@link File} with the given file name.
	 *
	 * @param filename
	 *            the name of the {@link File}, never <code>null</code>
	 * @throws IOException
	 *             well, you know, this may fail
	 */
	public void write(String filename) throws IOException {
		write(new File(filename));
	}

	/**
	 * Write the DITA Topic to the given {@link File}.
	 *
	 * @param file
	 *            the {@link File}, never <code>null</code>
	 * @throws IOException
	 *             well, you know, this may fail
	 */
	public void write(File file) throws IOException {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			write(writer);
		}
	}

	/**
	 * Write the DITA Topic to the given {@link Writer}.
	 *
	 * @param writer
	 *            the {@link Writer}, never <code>null</code>
	 * @throws IOException
	 *             well, you know, this may fail
	 */
	public void write(Writer writer) throws IOException {
		Format prettyPrinter = Format.getPrettyFormat();
		prettyPrinter.setExpandEmptyElements(true);
		prettyPrinter.setEncoding(StandardCharsets.UTF_8.name());

		XMLOutputter putter = new XMLOutputter();
		putter.setFormat(prettyPrinter);
		putter.output(this, writer);
	}
}
