/*
 * Copyright the Dolce Dita contributors.
 * All rights reserved.
 */
package dita;

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

	public static String DocType_Name = "topic";

	public static String DocType_PublicID = "-//OASIS//DTD DITA Topic//EN";

	public static String DocType_SystemID = "topic.dtd";

	private Element root, title;

	public DitaTopic(String name, String id) {
		setDocType(new DocType(DocType_Name, DocType_PublicID, DocType_SystemID));
		root = new Element(DocType_Name);
		root.setAttribute("id", id);
		root.setAttribute("rev", "0.1");
		setRootElement(root);

		title = (new Element("title")).addContent(name); //$NON-NLS-1$
		root.addContent(title);
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
