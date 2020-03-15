/*
 * Copyright the Dolce Dita contributors.
 * All rights reserved.
 */
package dita;

import static dita.DitaId.*;

import org.jdom2.Element;

/**
 * Simple factory to create typical DOM and DITA stuff. No I18N, no unnecessary API DOC.
 */
@SuppressWarnings({ "javadoc", "nls" })
public class DitaFactory {

	public static Element newTitle(String text) {
		return (new Element("title")).addContent(text);
	}

	public static Element newParagraph() {
		return (new Element("p"));
	}

	public static Element newParagraph(String text) {
		return newParagraph().setText(text);
	}

	public static Element newSection() {
		return new Element("section").setAttribute("id", newId("section"));
	}

	public static Element newFigure() {
		return new Element("fig").setAttribute("id", newId("figure"));
	}

	public static Element newImage() {
		return new Element("image").setAttribute("id", newId("image"));
	}
}
