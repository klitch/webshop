package xml;

import entity.Role;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class SecurityFileXMLParser {
    private final static String CONSTRAINT_NODE = "constraint";
    private final static String URL_PATTERN_NODE = "url-pattern";
    private final static String ROLE_NODE = "role";

    public void parse(final File securityFile) throws SAXException, IOException, ParserConfigurationException {
        final Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(securityFile);
        document.getDocumentElement().normalize();
        final NodeList constraintList = document.getElementsByTagName(CONSTRAINT_NODE);
        for (int i = 0; i < constraintList.getLength(); i++) {
            final Node node = constraintList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                final Element constraint = (Element) node;
                final NodeList urlElementList = constraint.getElementsByTagName(URL_PATTERN_NODE);
                final Element urlElement = (Element) urlElementList.item(0);
                final NodeList urlList = urlElement.getChildNodes();
                final String url = urlList.item(0).getNodeValue();
                final NodeList roleElementList = constraint.getElementsByTagName(ROLE_NODE);
                for (int j = 0; j < roleElementList.getLength(); j++) {
                    final Element roleElement = (Element) roleElementList.item(j);
                    final NodeList roleList = roleElement.getChildNodes();
                    final String role = roleList.item(0).getNodeValue();
                    Role.valueOf(role.toUpperCase()).addPattern(url);
                }
            }
        }
    }
}