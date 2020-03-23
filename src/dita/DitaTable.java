/*
 * Copyright the Dolce Dita contributors.
 * All rights reserved.
 */
package dita;

import org.jdom2.Element;

/**
 * The Table element organizes arbitrarily complex relationships of tabular information.
 *
 * @see <a href="http://docs.oasis-open.org/dita/v1.2/os/spec/common/table2.html">The OASIS</a>
 */
public class DitaTable extends Element {

	private static final long serialVersionUID = 1L; // Satisfy the compiler.

	private Element group, head, headRow, body;

	/**
	 * TODO: Well, you have to do some real actual work
	 */
	public static String DocType_Name = "table"; //$NON-NLS-1$

	/**
	 * Create a new {@link DitaTable} with given name.
	 *
	 * @param name
	 *            topic name (its title)
	 */
	public DitaTable(String name) {
		this(name, DitaId.newId(DocType_Name), new String[0], null);
	}

	/**
	 * Create a new {@link DitaTable} with given name.
	 *
	 * @param name
	 *            topic name (its title)
	 * @param cNames
	 *            array of column names, never <code>null</code>
	 */
	public DitaTable(String name, String[] cNames) {
		this(name, DitaId.newId(DocType_Name), cNames, null);
	}

	/**
	 * Create a new {@link DitaTable} with given name, id and initial columns.
	 *
	 * @param name
	 *            topic name (its title)
	 * @param id
	 *            unique id, never <code>null</code>
	 * @param cNames
	 *            array of column names, never <code>null</code>
	 * @param cWidths
	 *            array of column widths, can be <code>null</code>
	 * @see #addColumns(String[], double[])
	 */
	public DitaTable(String name, String id, String[] cNames, double[] cWidths) {
		super(DocType_Name);
		this.addContent(DitaFactory.newTitle(name));
		setAttribute("frame", "all"); //$NON-NLS-1$ //$NON-NLS-2$
		setAttribute("rowsep", "1"); //$NON-NLS-1$ //$NON-NLS-2$
		setAttribute("colsep", "1"); //$NON-NLS-1$ //$NON-NLS-2$
		setAttribute("id", id); //$NON-NLS-1$

		head = new Element("thead"); //$NON-NLS-1$
		body = new Element("tbody"); //$NON-NLS-1$
		group = new Element("tgroup"); //$NON-NLS-1$
		group.addContent(head);
		group.addContent(body);
		addContent(group);
		addColumns(cNames, cWidths);
	}

	/**
	 * Adds a number of columns in a single step.
	 *
	 * @param cNames
	 *            array of column names, never <code>null</code>
	 * @return this
	 */
	public DitaTable addColumns(String[] cNames) {
		return addColumns(cNames, null);
	}

	/**
	 * Adds a number of columns in a single step.
	 *
	 * @param cNames
	 *            array of column names, never <code>null</code>
	 * @param cWidths
	 *            array of column widths, can be <code>null</code>
	 * @return this
	 */
	public DitaTable addColumns(String[] cNames, double[] cWidths) {
		for (int i = 0; i < cNames.length; ++i) {
			addColumn(cNames[i], cWidths != null ? cWidths[i] : -1);
		}
		return this;
	}

	/**
	 * Adds a single column.
	 *
	 * @param cName
	 *            column name, never <code>null</code>
	 * @return this
	 */
	public DitaTable addColumn(String cName) {
		return addColumn(cName, -1);
	}

	/**
	 * Adds a single column.
	 *
	 * @param cName
	 *            column name, never <code>null</code>
	 * @param cWidth
	 *            column width, -1 for default
	 * @return this
	 */
	public DitaTable addColumn(String cName, double cWidth) {
		int i = group.getContentSize() - 2; // head and body
		Element colspec = new Element("colspec"); //$NON-NLS-1$
		colspec.setAttribute("colname", "c" + i); //$NON-NLS-1$ //$NON-NLS-2$
		colspec.setAttribute("colnum", Integer.toString(i)); //$NON-NLS-1$
		if (cWidth == -1) {
			colspec.setAttribute("colwidth", "1*"); //$NON-NLS-1$ //$NON-NLS-2$
		} else {
			colspec.setAttribute("colwidth", cWidth + "*"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		group.addContent(i, colspec);
		group.setAttribute("cols", Integer.toString(i + 1)); //$NON-NLS-1$

		// TODO Later we can use DitaTableRow
		if (headRow == null) {
			headRow = new Element("row"); //$NON-NLS-1$
			head.addContent(headRow);
		}

		System.out.println(cName);
		Element entry = new Element("entry").setText(cName); //$NON-NLS-1$
		headRow.addContent(entry);

		return this;
	}
}
