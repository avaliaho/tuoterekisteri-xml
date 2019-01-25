<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean class="tuoterekisteri.Tuoterekisteri" id="tuoterekisteri"/>
<jsp:directive.page import="org.w3c.dom.*"/>
<!DOCTYPE html>
<html lang="fi">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>XML-tuoterekisteri</title>
    </head>
    <body>
        <h1>XML-tuoterekisteri</h1> 
        
        <a href="lisaa.jsp">Lisää uusi tuote</a>
        <%

            if (tuoterekisteri.haeTuoterekisteri()) {

                NodeList tuotteet = tuoterekisteri.getDokumentti().getElementsByTagName("tuote");
                
                String merkkijono = "<table cellpadding='3' border='1'><tr><th>Id</th><th>Nimi</th><th>Hinta</th><th></th></tr>";
                
                for (int i = 0; i < tuotteet.getLength(); i++) {
                    Element tuote = (Element) tuotteet.item(i);

                    NodeList idt = tuote.getElementsByTagName("id");
                    NodeList nimet = tuote.getElementsByTagName("nimi");
                    NodeList hinnat = tuote.getElementsByTagName("hinta");
                    
                    merkkijono += "<tr><td>" + idt.item(0).getTextContent() + "</td>";                    
                    merkkijono += "<td>" + nimet.item(0).getTextContent() + "</td>";                    
                    merkkijono += "<td>" + hinnat.item(0).getTextContent() + "</td>";
                    merkkijono += "<td><a href='poista.jsp?id=" + idt.item(0).getTextContent() + "'>Poista</a>";
                    merkkijono += " <a href='muokkaa.jsp?id=" + idt.item(0).getTextContent() + "'>Muokkaa</a></td>";
                }
                
                merkkijono += "</table>";
                out.println(merkkijono);

            } else {
                out.println("Virhe: Ei löydetty XML-tiedostoa.");
            }

        %>
    </body>
</html>
