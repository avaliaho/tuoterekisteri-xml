package tuoterekisteri;

import org.w3c.dom.*;
import java.util.*;

public class Tuoterekisteri extends Tiedosto {

    // luokan muuttujat
    int poistettavaid = 0;
    int haettavaid = 0;
    ArrayList<String> tiedot = new ArrayList<String>();
    int poistettavaindeksi = 0;
    int muokattavaindeksi = 0;

    public void lisaaTuote(String nimi, int hinta) {
        try {
            // luodaan tuote käyttämällä Element luokan oliota ja appendChild metodia
            Element juuri = dokumentti.getDocumentElement();
            Element uusituote = dokumentti.createElement("tuote");
            Attr attribuutti = dokumentti.createAttribute("xml:id");
            attribuutti.setValue(String.valueOf(luoId()));
            uusituote.setAttributeNode(attribuutti);

            Element eid = dokumentti.createElement("id");
            eid.appendChild(dokumentti.createTextNode(String.valueOf(luoId())));
            uusituote.appendChild(eid);

            Element enimi = dokumentti.createElement("nimi");
            enimi.appendChild(dokumentti.createTextNode(nimi));
            uusituote.appendChild(enimi);

            Element ehinta = dokumentti.createElement("hinta");
            ehinta.appendChild(dokumentti.createTextNode(String.valueOf(hinta)));
            uusituote.appendChild(ehinta);

            juuri.appendChild(uusituote);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ArrayList<String> haeTuote(String id) {
        try {
            NodeList tuoteelementit = dokumentti.getElementsByTagName("tuote");

            for (int i = 0; i < tuoteelementit.getLength(); i++) {
                Node tuote = tuoteelementit.item(i);
                Element tuoteelementti = (Element) tuote;
                if (Objects.equals(Integer.valueOf(tuoteelementti.getAttribute("xml:id")), Integer.valueOf(id))) {
                    tiedot.add(tuoteelementti.getElementsByTagName("id").item(0).getTextContent());
                    tiedot.add(tuoteelementti.getElementsByTagName("nimi").item(0).getTextContent());
                    tiedot.add(tuoteelementti.getElementsByTagName("hinta").item(0).getTextContent());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tiedot;
    }

    public void poistaTuote(String id) {
        try {

            NodeList tuotteet = dokumentti.getElementsByTagName("tuote");
            for (int i = 0; i < tuotteet.getLength(); i++) {
                Node tuote = tuotteet.item(i);
                Element tuoteelementti = (Element) tuote;
                if (Objects.equals(Integer.valueOf(tuoteelementti.getAttribute("xml:id")), Integer.valueOf(id))) {
                    poistettavaindeksi = i;
                }
            }

            Node poistettava = dokumentti.getElementsByTagName("tuote").item(poistettavaindeksi);
            Node juuri = dokumentti.getDocumentElement();
            juuri.removeChild(poistettava);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void muokkaaTuotetta(String id, String nimi, String hinta) {
        NodeList tuotteet = dokumentti.getElementsByTagName("tuote");
        for (int i = 0; i < tuotteet.getLength(); i++) {
            Node tuote = tuotteet.item(i);
            Element tuoteelementti = (Element) tuote;
            if (Objects.equals(Integer.valueOf(tuoteelementti.getAttribute("xml:id")), Integer.valueOf(id))) {
                    muokattavaindeksi = i;
                }
        }
        Node tuote = tuotteet.item(muokattavaindeksi);
        NodeList muokattava = tuote.getChildNodes();
        muokattava.item(3).setTextContent(nimi);
        muokattava.item(5).setTextContent(hinta);
    }

    
    public int luoId() {
        String id = "0";
        NodeList tuotteet = this.dokumentti.getElementsByTagName("tuote");
        for (int i = 0; i < tuotteet.getLength(); i++) {
            Element tuote = (Element) tuotteet.item(i);
            NodeList idt = tuote.getElementsByTagName("id");
            id = idt.item(0).getTextContent();
        }
        int id2 = Integer.parseInt(id);
        int luku = (id2 >= 0) ? (id2 += 1) : (id2 = 1);
        return id2;
    }

}
