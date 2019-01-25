<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean class="tuoterekisteri.Tuoterekisteri" id="tuoterekisteri"></jsp:useBean>
<jsp:directive.page import="java.util.*"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>XML-tuoterekisteri</title>
    </head>
    <body>
        <h1>Poista tuote</h1>

        <%

            if (tuoterekisteri.haeTuoterekisteri() && request.getParameter("id") != null) {
                String id = request.getParameter("id");
                String tuoteId = tuoterekisteri.haeTuote(id).get(0);
                out.println("<ul><li>Id: " + tuoterekisteri.haeTuote(id).get(0) + "</li>");
                out.println("<li>Nimi: " + tuoterekisteri.haeTuote(id).get(1) + "</li>");
                out.println("<li>Hinta: " + tuoterekisteri.haeTuote(id).get(2) + "</li></ul>");
        %>

        <form method="get">
            <input type="hidden" name="poistettavaid" value="<%= tuoteId%>">
            <input type="submit" name="poista" value="Poista">
            <input type="submit" name="peruuta" value="Peruuta">
        </form>

        <%
            }

            if (request.getParameter("peruuta") != null) {
                response.sendRedirect("/tuoterekisteri");
            }

            if (request.getParameter("poistettavaid") != null) {
                String poistettavaid = request.getParameter("poistettavaid");
                tuoterekisteri.poistaTuote(poistettavaid);
                if (tuoterekisteri.tallennaMuutokset() == true) {
                    System.out.println("poisto ok");
                } else {
                    System.out.println("poisto epäonnistui");
                }
                response.sendRedirect("/tuoterekisteri");
            }

        %>
    </body>
</html>
