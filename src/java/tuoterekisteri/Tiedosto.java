package tuoterekisteri;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.*;
import javax.xml.transform.OutputKeys;

public class Tiedosto implements Serializable {

    // luokan muuttujat
    protected DocumentBuilderFactory tehdas = null;
    protected DocumentBuilder rakentaja = null;
    protected Document dokumentti = null;
    protected URL polku = null;
    protected File tiedosto = null;
    protected DOMSource lahde = null;
    protected TransformerFactory muuntajatehdas = null;
    protected Transformer muuntaja = null;
    protected StreamResult tulos = null;
    protected StreamResult konsolitulos = null;

    public Document getDokumentti() {
        return this.dokumentti;
    }

    public boolean haeTuoterekisteri() {
        boolean ok = true;
        try {
            // hae tiedosto
            polku = Tuoterekisteri.class.getResource("tuotteet.xml");
            tiedosto = new File(polku.getFile());
            // luo DocumentBuilder olio
            tehdas = DocumentBuilderFactory.newInstance();
            rakentaja = tehdas.newDocumentBuilder();
            dokumentti = rakentaja.parse(this.tiedosto);
        } catch (Exception e) {
            e.printStackTrace();
            ok = false;
        }
        return ok;
    }

    public boolean tallennaMuutokset() {
        boolean ok = true;
        try {
            // luo Transformer ja DOMSource oliot 
            muuntajatehdas = TransformerFactory.newInstance();
            muuntaja = muuntajatehdas.newTransformer();  
            muuntaja.setOutputProperty(OutputKeys.INDENT, "yes");
            muuntaja.setOutputProperty(OutputKeys.METHOD, "xml");
            muuntaja.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            muuntaja.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            lahde = new DOMSource(dokumentti);
            // luo StreamResult olio
            // transform metodilla kirjoitetaan dokumentti haluttuun outputstreamiin
            tulos = new StreamResult(tiedosto);
            muuntaja.transform(lahde, tulos);
            konsolitulos = new StreamResult(System.out);
            muuntaja.transform(lahde, konsolitulos);
        } catch (Exception e) {
            e.printStackTrace();
            ok = false;
        }
        return ok;
    }

}
