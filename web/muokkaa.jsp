<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean class="tuoterekisteri.Tuoterekisteri" id="tuoterekisteri"></jsp:useBean>
    <!DOCTYPE html>
    <html lang="fi">
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>XML-tuoterekisteri</title>
        </head>
        <body>
            <h1>Muokkaa</h1>



        <%

            if (tuoterekisteri.haeTuoterekisteri() && request.getParameter("id") != null) {
                String id = request.getParameter("id");
                String tuoteid = tuoterekisteri.haeTuote(id).get(0);
                String tuotenimi = tuoterekisteri.haeTuote(id).get(1);
                int tuotehinta = Integer.valueOf(tuoterekisteri.haeTuote(id).get(2));

        %>

        <form method="get">
            <input type="hidden" name="id" value="<%= tuoteid %>">
            Nimi: <input type="text" name="nimi" value="<%= tuotenimi %>"><br>
            Nimi: <input type="text" name="hinta" value="<%= tuotehinta %>"><br>
            <input type="submit" name="nappi">
            <input type="submit" name="peruuta" value="Peruuta">
        </form>

        <%            
            }

            if (request.getParameter("nappi") != null) {
                String id = request.getParameter("id");
                String nimi = request.getParameter("nimi");
                String hinta = request.getParameter("hinta");
                
                tuoterekisteri.muokkaaTuotetta(id, nimi, hinta);
                tuoterekisteri.tallennaMuutokset();
            }

            if (request.getParameter("peruuta") != null) {
                response.sendRedirect("/tuoterekisteri");
            }
        %>

    </body>
</html>
