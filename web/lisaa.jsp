<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean class="tuoterekisteri.Tuoterekisteri" id="tuoterekisteri"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>XML-tuoterekisteri</title>
    </head>
    <body>
        <h1>Lisää tuote</h1>

        <form method="post">
            Nimi: <input type="text" name="nimi"><br>
            Hinta: <input type="number" name="hinta"><br>
            <input type="submit" name="nappi">
            <input type="submit" name="peruuta" value="Peruuta">
        </form>

        <%
            
            if (tuoterekisteri.haeTuoterekisteri() && request.getParameter("nappi") != null) {
                    String nimi = request.getParameter("nimi");
                    int hinta = Integer.parseInt(request.getParameter("hinta"));

                    tuoterekisteri.lisaaTuote(nimi, hinta);
                    if (tuoterekisteri.tallennaMuutokset() == true) {
                        System.out.println("lisäys ok");
                    } else {
                        System.out.println("lisäys epäonnistui");
                    }
            }
            
            if (request.getParameter("peruuta") != null) {
                response.sendRedirect("/tuoterekisteri");
            }
        %>
    </body>
</html>
