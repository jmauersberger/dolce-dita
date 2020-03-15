/*
 * Copyright the Dolce Dita contributors.
 * All rights reserved.
 */
package dita;

import org.jdom2.Element;

/**
 * Simple factory to create typical DOM and DITA stuff. No I18N, no unnecessary API DOC.
 */
@SuppressWarnings({ "javadoc", "nls" })
public class DitaFactory {

	public static Element newTitle(String name) {
		return (new Element("title")).addContent(name);
	}

	public static Element newParagraph() {
		return (new Element("p"));
	}

	public static Element newParagraph(String text) {
		return newParagraph().setText(text);
	}
}
