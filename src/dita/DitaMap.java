/*
 * Copyright the Dolce Dita contributors.
 * All rights reserved.
 */
package dita;

import static dita.DitaFactory.*;

import org.jdom2.DocType;
import org.jdom2.Element;

/**
 * DITA maps are documents that organize topics and other resources into structured collections of information. DITA
 * maps specify hierarchy and the relationships among the topics; they also provide the context in which keys are
 * defined and resolved.
 *
 * @see <a href="https://docs.oasis-open.org/dita/v1.2/os/spec/archSpec/definition-of-ditamaps.html">The OASIS</a>
 */
public class DitaMap extends DitaDocument {

	private static final long serialVersionUID = 1L; // Satisfy the compiler.

	/**
	 * TODO: Well, you have to do some real actual work
	 */
	public static String DocType_Name = "map"; //$NON-NLS-1$

	/**
	 * TODO: Well, you have to do some real actual work
	 */
	public static String DocType_PublicID = "-//OASIS//DTD DITA Map//EN"; //$NON-NLS-1$

	/**
	 * TODO: Well, you have to do some real actual work
	 */
	public static String DocType_SystemID = "map.dtd"; //$NON-NLS-1$

	private Element root, title, body, active;

	/**
	 * Create a new {@link DitaMap} with given name and id.
	 *
	 * @param name
	 *            topic name (its title)
	 * @param id
	 *            unique id, never <code>null</code>
	 */
	public DitaMap(String name, String id) {
		setDocType(new DocType(DocType_Name, DocType_PublicID, DocType_SystemID));
		root = new Element(DocType_Name);
		root.setAttribute("id", id); //$NON-NLS-1$
		setRootElement(root);

		title = newTitle(name);
		root.addContent(title);
		active = root;
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
	 * Adds a new topic to the current active element.
	 *
	 * @param path
	 *            topic reference
	 * @return this
	 */
	public DitaMap addTopic(String path) {
		Element topicref = newTopicRef(path);
		active.addContent(topicref);
		return this;
	}

	/**
	 * Opens a new topic. The new topic becomes the "active" element until it is closed.
	 *
	 * @param path
	 *            topic reference
	 * @return this
	 * @see #closeSection()
	 */
	public DitaDocument openTopic(String path) {
		Element topicref = newTopicRef(path);
		active.addContent(topicref);
		active = topicref;
		return this;
	}

	/**
	 * Closes any open topic. Does nothing if there is no open topic.
	 *
	 * @return this
	 */
	public DitaDocument closeSection() {
		active = body;
		return this;
	}

}
