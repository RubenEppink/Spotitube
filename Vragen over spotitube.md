# Vragen spotitube

1. Token:
   - Hoe belangrijk is dit? -> moet ik hem genereren met java of mag ik hem zelf in de database zetten?
   - moet deze token eens in de zoveel tijd verwijderd worden?

2. Excepties afvangen en gooien 
   - Hoe weet ik waar ik een exceptie moet afvangen (gelijk afvangen of doorgooien? Zie getConnection in SQLDBConnection) 
   - Zelf excepties maken, zoals invalidCredentialsException  -> moet deze op meerdere plaatsen worden gegooid, nu alleen in het loginDomein, maar misschien ook in de DAO als er geen resultaten bij de username horen? of misschien andere exceptie? Laat hij andere excepties ook zien op de client, ipv http 0?
3. Loggen
   - In plaats van stacktrace printen, misschien loggen? Korte uitleg en voorbeeld als het kan?

4. Samen naar mijn database structuur kijken, heb ik dingen over het hoofd gezien?

   - token in welke tabellen?

   - VARCHAR(45)? 
   - TINYINT(1) = boolean?

5. Wat is REST nou precies? Is het de manier waarop je met endpoints gegevens kan uitwisselen, vaak in combinatie met de HTTP methodes: GET, POST, PUT, DELETE etc? 

6. Unittests

   - Meerdere dingen in één test, zoals response entity en status in zelfde methode testen? of meerdere malen verify?
   - void methods zoals delete playlist testen?

