package com.paranhaslett.gamebook.loader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.paranhaslett.gamebook.Editor;
import com.paranhaslett.gamebook.Editor.Item;
import com.paranhaslett.gamebook.controller.BookController;
import com.paranhaslett.gamebook.model.Book;
import com.paranhaslett.gamebook.model.Library;

public class XMLLoader implements Loader {
	public Document doc;
	private Editor gc = Editor.getEd();
	private Element currentElement;

	@Override
	public Book loadBook(File file) {
		Book gameBook = null;
		BookController gbController = (BookController) gc
				.getController(Item.BOOK);
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(file);
			doc.getDocumentElement().normalize();
			Node nNode = doc.getDocumentElement();
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) nNode;
				gameBook = (Book) gbController.loader.loadFromXML(element);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return gameBook;
	}

	@Override
	public void save(Book gameBook, File file) {
		BookController gbController = (BookController) gc
				.getController(Item.BOOK);
		try {

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.newDocument();
			doc.appendChild(gbController.loader.saveToXML(gameBook));

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(file);

			transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getText(Element eElement, String name) {
		NodeList nodes = eElement.getElementsByTagName(name);
		if (nodes.getLength() > 0) {
			return nodes.item(0).getTextContent();
		}
		return null;
	}

	public ArrayList<Element> getChildren(Element element) {
		ArrayList<Element> result = new ArrayList<Element>();
		NodeList nodes = element.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				result.add((Element) node);
			}
		}
		return result;
	}

	public Element getChild(Element element, String name) {
		NodeList nodes = element.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if (node.getNodeName().equals(name)) {
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					return (Element) node;
				}
			}
		}
		return null;
	}

	public Element getElement(Element element, String name) {

		NodeList nodes = element.getElementsByTagName(name);
		if (nodes.getLength() > 0) {
			Node node = nodes.item(0);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element subElement = (Element) node;
				return subElement;
			}
		}
		return null;
	}

	public List<Element> getElements(Element eElement, String name) {
		ArrayList<Element> result = new ArrayList<Element>();
		NodeList nodes = eElement.getElementsByTagName(name);
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				result.add((Element) node);
			}
		}
		return result;
	}

	public List<Element> getAllElements(Element eElement, String name) {
		ArrayList<Element> result = new ArrayList<Element>();
		NodeList nodes = eElement.getElementsByTagName(name);
		if (nodes.getLength() > 0) {
			Node node = nodes.item(0);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				NodeList subnodes = element.getChildNodes();
				for (int i = 0; i < subnodes.getLength(); i++) {
					Node subnode = subnodes.item(i);
					if (subnode.getNodeType() == Node.ELEMENT_NODE) {
						result.add((Element) subnode);
					}
				}
			}
		}
		return result;
	}

	public void setTextElement(Document doc, Element nodeElement, String name,
			String value) {
		Element titleElement = doc.createElement(name);
		nodeElement.appendChild(titleElement);
		titleElement.appendChild(doc.createTextNode(value));
	}

	public void setStrs(Document doc, Element nodeElement, String name,
			ArrayList<String> values) {
		for (String value : values) {
			Element titleElement = doc.createElement(name);
			nodeElement.appendChild(titleElement);
			titleElement.appendChild(doc.createTextNode(value));
		}

	}

	public String load(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Library loadLibrary(File file) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * load from attribute load from text load from sub element load from sub
	 * elements load from open file open and close appropriate file load from
	 * file chooser
	 */

}
