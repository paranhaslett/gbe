package paranhaslett.gamebook.loader;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import paranhaslett.gamebook.loadable.Loadable;
import paranhaslett.gamebook.model.Item;
import paranhaslett.gamebook.model.Library;
import paranhaslett.gamebook.model.libraryitem.Book;
import paranhaslett.gamebook.model.libraryitem.Series;
import paranhaslett.gamebook.model.libraryitem.Template;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class XMLLoader implements Loader {
    private Document doc;
    private Element element;

    @Override
    public void load(File file, Item item) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            this.doc = dBuilder.parse(file);
            this.doc.getDocumentElement().normalize();
            Node nNode = this.doc.getDocumentElement();
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                this.element = (Element) nNode;
                if (item instanceof Book) {
                    Book.loadable.load(this, item);
                }
                if (item instanceof Library) {
                    Library.loadable.load(this, item);
                }
                if (item instanceof Series) {
                    Series.loadable.load(this, item);
                }
                if (item instanceof Template) {
                    Template.loadable.load(this, item);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void save(File file, Item item) {
        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            this.doc = dBuilder.newDocument();

            if (item instanceof Book) {
                this.element = this.doc.createElement("book");
                Book.loadable.save(this, item);
            }
            if (item instanceof Library) {
                this.element = this.doc.createElement("library");
                Library.loadable.save(this, item);
            }
            if (item instanceof Series) {
                this.element = this.doc.createElement("series");
                Series.loadable.save(this, item);
            }
            if (item instanceof Template) {
                this.element = this.doc.createElement("template");
                Template.loadable.save(this, item);
            }

            this.doc.appendChild(this.element);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory
                    .newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(this.doc);
            StreamResult result = new StreamResult(file);

            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Loader create(String key) {
        XMLLoader xmlLoader = new XMLLoader();
        xmlLoader.setElement(Loadable.xmlLoader.doc.createElement(key));
        this.element.appendChild(xmlLoader.getElement());
        return xmlLoader;
    }

    private Element getElement() {
        return this.element;
    }

    private void setElement(Element element) {
        this.element = element;
    }

    @Override
    public String getText(String key) {
        if (key == null) {
            return beautifyText(this.element.getTextContent());
        }
        if (this.element.hasAttribute(key)) {
            return this.element.getAttribute(key);
        }
        NodeList nl = this.element.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (node.getNodeName().equals(key)) {
                return beautifyText(node.getTextContent());
            }
        }
        return null;
    }

    private static String beautifyText(String text) {
        String result = text.replaceAll("^\\s*", "");
        for (int i = 0; i < 50; i++) {
            result = result.replaceAll("\n\\s*", "\n");
        }
        return result;
    }

    @Override
    public List<Loader> getChildren(String... childrenKeys) {
        ArrayList<Loader> result = new ArrayList<>();
        HashSet<String> keySet = new HashSet<>(Arrays.asList(childrenKeys));
        NodeList nodes = this.element.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {

                if (keySet.contains(node.getNodeName())) {
                    XMLLoader ff = new XMLLoader();
                    ff.setElement((Element) node);
                    result.add(ff);
                }
            }
        }
        return result;
    }

    @Override
    public void setText(String key, String value) {
        if (key == null) {
            this.element.setTextContent(value);
        } else {
            if (key.equals("value") || key.equals("text")) {
                XMLLoader subLoader = (XMLLoader) create(key);
                subLoader.getElement().setTextContent(value);
            } else {
                this.element.setAttribute(key, value);
            }
        }
    }

    @Override
    public String getName() {
        return this.element.getNodeName();
    }

    @Override
    public Loader getChild(String childKey) {
        NodeList nodes = this.element.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {

                if (childKey.equals(node.getNodeName())) {
                    XMLLoader ff = new XMLLoader();
                    ff.setElement((Element) node);
                    return ff;
                }
            }
        }
        return null;
    }

}
