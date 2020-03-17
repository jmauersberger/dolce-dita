/*
 * Copyright the Dolce Dita contributors.
 * All rights reserved.
 */
package dita;

import static dita.DitaFactory.*;

import org.jdom2.DocType;
import org.jdom2.Element;

/**
 * DITA topics are the basic units of DITA content and the basic units of reuse. Each topic contains a single subject.
 * Topics may be of specific specialized information types, such as task, concept, or reference, or may be generic, that
 * is, without a specified information type.
 *
 * @see <a href="http://docs.oasis-open.org/dita/v1.2/os/spec/archSpec/topicover.html">The OASIS</a>
 */
public class DitaTopic extends DitaDocument {

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
	public DitaDocument addParagraph(String text) {
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
	public DitaDocument addImage(String text, String href) {
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
	public DitaDocument addSection(String text) {
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
	public DitaDocument openSection(String text) {
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
	public DitaDocument closeSection() {
		active = body;
		sectionOpen = false;
		return this;
	}
}
