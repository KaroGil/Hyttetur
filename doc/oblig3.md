## Prosjektrapport #3

-   *Hvordan fungerer rollene i teamet? Trenger dere å oppdatere hvem som er teamlead eller kundekontakt?*

Vi har hatt en rollefordelding som har vært relativ tydelig i møtene, og prøvd å gjøre det så realistisk som mulig. Kundekontakten har hatt ansvar for å passe på at vi jobber mot MVP som vi satt opp i starten. Vi har ikke et behov for å oppdatere rollene for aller siste obligatoriske innlevering, men vi kan være tydeligere på arbeidsfordelingen som er knyttet til rollen vår og klarere interne deadlines.

-   *Trenger dere andre roller? Skriv ned noen linjer om hva de ulike rollene faktisk innebærer for dere.*

Vi ser ikke behovet for etabalere noen nye roller i denne tiden av utviklingen. Vi har fremdeles samme teamlead for prosjektet som har det overordnede ansvaret og skriver referater for gruppen. Samt kundekontakt som nevnt. 

For resten av teamet har vi kundekontakt, grafisk og test-ansvarlig. Her har rollene vært gode, men vi har hatt utfordringer med å fordele oppgaver som er knyttet sterkt opp de resterende rollene. I praksis har vi heller fordelt konkrete arbeidsoppgaver som ikke har vært like knyttet opp mot rollen vår, men mer mot hva som trengs for å nå MVP. Med tester er det forbedringspotensiale.x

-   *Er det noen erfaringer enten team-messig eller mtp prosjektmetodikk som er verdt å nevne? Synes teamet at de valgene dere har tatt er gode? Hvis ikke, hva kan dere gjøre annerledes for å forbedre måten teamet fungerer på?*

Team messig har vi møttes en 1-2 ganger for å fordele oppgaver som skal gjøres til neste gang. Selve fordelingen har vært god der man har fordelt arbeidsflyten i konkrete arbeidsoppgaver på tvers av gruppen. Her har vi også fremdeles benyttet oss av Kanban for synliggjøre hvilke oppgaver som må løses for gruppen. Vi har derimot, slik vi opplevde ved sist iterasjon, at man har problemer med en felles forståelse for hvordan oppgaven skal løses og da spesielt knyttet til hvordan koden skal bygges opp og omfanget for hvilket ansvar man har fått på vegne av gruppen. 

Vi har også møtt på problemer med Git, men enkelte medlemmer på gruppen har forbedret siden kompetanse innenfor dette området og hjulpet de andre medlemmene med de utfordringene som har oppstått.

Gruppemedlemmene har vært travle med både andre fag og at man ikke har oppholdt seg i Bergen. Et stort mulig forbedringspotensiale er at vi kunne satt flere lengre bolker der vi parprogrammerte sammen eller bare oppholdt seg på samme sted for å veilede hverandre. Dette kunne hatt mye å si for progresjonen og for å skape en felles forståelse for hvilken retning utviklingen går.

-   *Hvordan er gruppedynamikken? Er det uenigheter som bør løses?*

Gruppedynamikken har fungert fint til tross for at vi har støtt på flere problemer underveis. Det har oppstått noen problemer med kommunikasjon og utvikling av programvaren, men det har ikke vært noen særlig uenigheter. Det har heller kanskje vært problematisk at siden sist iterasjon har det vært en periode med påskeferie og andre emner som gjør at man har slitt med holde progresjon og fokuset på prosjektet.


-   *Hvordan fungerer kommunikasjonen for dere?*

Vi hadde litt kommunikasjonsproblemer da vi benyttet oss av Discord som hovedkanal, da alle var ikke innom like hyppige i mediumet for å sjekke meldinger. Kommunikasjonen løste vi med at vi benyttet oss av en annen kommunikasjonsplattform og dette har fungert mye bedre, da man er langt mer tilgjengelig for å besvare meldinger. 

-   *Gjør et kort retrospektiv hvor dere vurderer hva dere har klart til nå, og hva som kan forbedres. Dette skal handle om prosjektstruktur, ikke kode. Dere kan selvsagt diskutere kode, men dette handler ikke om feilretting, men om hvordan man jobber og kommuniserer.*

I spillet har man fått til grunnleggende som må plass for å lage et spill, der vi har en player, fiender, kart, collisions, animasjon og projektiler, som er delt inn i deres respektive klasser iht. kodestil. Det gjør det enklere å utbedre koden. Det har vært en litt varierende arbeidsmengde på gruppemedlemmene, men alle har bidratt til overnevnde momenter.  Vi har derimot ikke fått flettet dette sammen til et velfungerende spill og man mangler fremdeles vesentlig momenter for at dette kan leveres og ferdigtsilles som et prosjekt og gå til produksjon. Forbedringen ligger i å implementere blant annet startscreen og ulike levels, og her har det vært utfordrende å få koordinere at alle skjønner hvordan man skal få bidratt til å komme i mål.

-   *Under vurdering vil det vektlegges at alle bidrar til kodebasen. Hvis det er stor forskjell i hvem som committer, må dere legge ved en kort forklaring for hvorfor det er sånn. Husk å committe alt. (Også designfiler)*

Vi har ikke endret måten vi har jobbet på siden sist iterasjon og momenter fra forrige innlevering angående bidrag til kodebasen er fremdels gjeldende. 

-   *Referat fra møter siden forrige leveranse skal legges ved (mange av punktene over er typisk ting som havner i referat).*

Referat lgger i wiki.

-   *Bli enige om maks tre forbedringspunkter fra retrospektivet, som skal følges opp under neste sprint.*

Til aller siste iterasjon og presentasjon må vi møtes mer fysisk i lag og over en lengre periode for å jobbe sammen. Vi har vært tydelig på arbeidsfordleing, men det må også være enda tydeligere for hvordan det forventes og hvilke verktøy man skal bruke for å løse oppgaven.



## Krav og spesifikasjon

-   *Oppdater hvilke krav dere har prioritert, hvor langt dere har kommet og hva dere har gjort siden forrige gang. Er dere kommet forbi MVP? Forklar hvordan dere prioriterer ny funksjonalitet.*

Vi har fremdeles ikke kommet oss forbi MVP. Siden sist iterasjon har vi fått flettet sammen koden slik at det fungerer sammen. Vi har derimot ikke fått implementert selve utviklingen i spillet som gjelder at en spiller kan komme seg gjennom ulike levels og baner i spillet. Det mangler funksjonalitet for å kunne levere et spill, men det som må prioriteres nærmest er dreping og bevegelse av fiender, liv til en spiller, for å så kunne implementere forskjellige levels.

Siden sist har vi implementert krav som gjelder hovedsakelig collisions og animasjoner. 

-   *For hvert krav dere jobber med, må dere lage 1) ordentlige brukerhistorier, 2) akseptansekriterier og 3) arbeidsoppgaver. Husk at akseptansekriterier ofte skrives mer eller mindre som tester*

Vi mangler fremdeles flere krav som må implementeres for å kunne ha et spill:

**Startscreen** 

 - Som utvikler vil jeg at spillet skal vises i vindu med grafikk.
   (akseptansekriterie: når spillet vises korrekt i vindu med grafikk da
   vi kjører programmet.)
 - som utvikler vil jeg ha en velfungerende startscreen, hjelpeskjerm.
   (akseptansekriterie: når bruker starter spill og får en startscreen)

**Liv og angrep**

 - Som utvikler vil jeg se at både spiller og fiender har liv og de kan miste det  (akseptansekrav: spiller og fiender kan utføre en handling slik at man mister liv)
 - Som utvikler vil jeg se at spiller og fiender kan angripe (Akseptanskrav: spiller og fiender kan utføre angrep som fører til at motstander mister liv)

**Fiender** 

 - Som utivkler vil jeg at fiender skal forsøke å drepe spiller (akseptansekrav: når spilleren kan miste liv/healtsh som følge av angrep fra fiende)
 - Som utvikler vil jeg se fiender kan bevege seg mot spiller (akseptansekrav: fienden beveger seg av seg selv mot spiller)


**Levels** 

 - Som bruker vil jeg se hvilket level jeg er på (akseptansekrav: jeg
   vil til enhver tid enkelt se hvilket level man er på i interfacet)
 - som bruker vil jeg se når jeg har drept alle fiender / blitt ferdig
   med en level enkelt. (akseptansekriterie: når bruker får beskjed om
   at level har blitt fullført)
 - Som utvikler vil jeg se at spiller er på en ny map når man er på en ny level (akseptansekrav: spiller lastes inn på ny bane eller får intrukser om å bevege seg mot ny bane)


 -   *Forklar kort hvordan dere har prioritert oppgavene fremover*

Vi prioriterer fremdeles oppgavene som gjør at vi kan levere et spill. Det vil si alle krav spesifikasjoner som er nevnt ovenfor må prioriteres. Det må implementeres resterende for liv og fiende, for å så deretter kunne implementere en level. Det er det som må prioreteers nærmest.

 -   *Har dere gjort justeringer på kravene som er med i MVP? Forklar i så fall hvorfor. Hvis det er gjort endringer i rekkefølge utfra hva som er gitt fra kunde, hvorfor er dette gjort?*
 
 Det har ikke blitt endringer siden sist.

 -   *Oppdater hvilke krav dere har prioritert, hvor langt dere har kommet og hva dere har gjort siden forrige gang.*

Det er nevnt ovenfor.




 -   Kravlista er lang, men det er ikke nødvendig å levere på alle kravene hvis det ikke er realistisk. Det er viktigere at de oppgavene som er utført holder høy kvalitet. Utførte oppgaver skal være ferdige


## Produkt og kode

Klasser:

Main - denne kjøres for å kjøre spillet
Den arver fra ApplicationListener i libgdx.

Hyttetur - Oppretter en INSTANCE av spillet. 

MainMenu - Det første som åpnes når man kjører spillet. Har for øyeblikket en play knapp som starter spillet.

Gamescreen - Dette er hvor spillet "tegnes". Create()-metoden oppretter objektene og render tegner spillet og objektene i det.
I render kalles mesteparten av funksjonene og det er her loopen for spillet foregår. 
Imellom batch.draw og batch.end blir alle objektene tegnet på mappet 60 ganger i sekundet. X og Y verdiene til alle prosjektilene og spilleren blir oppdatert hver gang loopen har gått igjennom.


Projectile - Denne klassen er for å kunne lage prosjektiler fra spiller og fiender, for øyeblikket bare fra spilleren. Midlertidig finnes det bare et type prosjektil kalt “beerBottle”. Når createBeerBottle funksjonen kalles, “spawner” det en ølflaske som beveger seg fra spilleren sine koordinater til musepekeren. Det blir gjort en del matte for å beregne x farten og y farten til hvert prosjektil. I game classen blir det kjørt en for loop i render(). Der hvert prosjektil i en liste blir oppdatert.


MapHandler - Har foreløpig 2 verdier som kalles i Game. En TiledMap verdi og en OrthogonalTiledMapRenderer verdi. Disse brukes i Game for å få mappen til å vises.


Entity og Player - I disse 2 klassene blir grafikk og bevegelse til spilleren og fiender håndtert. Det er foreløpig ingen fiender i spillet. Spilleren har verdier som for eksempel x, y, speed, width og height. Disse verdiene kan defineres i Game for å endre på spiller objektet.


Assets - I assets mappen som ligger i hyttetur mappen, ligger alle textures som blir brukt i prosjektet. Her ligger også TiledMap filene som ble laget i programmet “Tiled”.




#### MVP-update:
* Vise et vindu på skjermen - Gjort 
* Vise et spillebrett - Gjort
* Vise spiller på spillebrett - Gjort
* Flytte spiller (vha taster e.l.) - Gjort
* Spiller interagerer med terreng - Gjort 
* Spiller har *poeng* og interagerer med poenggjenstander - Ikke gjort enda
* Vise fiender/fest-folk; de skal interagere med terreng og spiller - De er laget, og interagerer med terrenget. Skyter foreløpig ikke selv, men beveger seg mot spilleren og tar "skade" og forsvinner etter 5 skudd. 
* Spiller kan dø (ved kontakt med fiender) - Føreløpig ikke implementert, ettersom fiendene ikke skyter selv.
* Mål for spillbrett drepe alle fiender - Ikke gjort
* Start-skjerm ved oppstart / game over / hjelpeskjerm - Start-skjerm implementer, ikke game over og hjelpeskjerm


Build og Kjøring

For at programmet skal kunne kjøres, åpner man “hyttetur” folderen med intelliJ. Deretter skal det komme opp en melding der det står noe tilsvarende “maven detected” Når du trykker load her vil prosjektet bli kjørbart. Prosjektet kjøres igjennom Main classen.

Tests

Ingen av testene vi har laget fungerer på grunn av fokus på koding. Vi har for eksempel en test som skal sjekke om størrelsen til player objektet er korrekt. Til neste oblig skal vi lage fungerende tester.

Prosjektet skal kunne bygge, testes og kjøres på Linux, Windows og OS X – dere kan f.eks. spørre de andre teamene på gruppen om dere ikke har tilgang til alle platformene. OBS! Den vanligste grunnen til inkompatibilitet med Linux er at filnavn er case sensitive, mens store/små bokstaver ikke spiller noen rolle på Windows og OS X. Det er viktig å sjekke at stiene til grafikk og lyd og slikt matcher eksakt. Det samme vil antakelig også gjelde når man kjører fra JAR-fil.

Vi har ikke fått testet programmet på noe annet operativsystem enn Windows 10 og Windows 11. Men her skal programmet fungere så lenge prosjektet åpnes fra “hyttetur” mappen. Når prosjektet åpnes for eksempel fra en mappe som inneholder hyttetur, vil ikke stiene til assets fungere.

Statiske analyseverktøy som SpotBugs eller SonarQube kan hjelpe med å finne feil dere ikke tenker på. Hvis dere prøver det, skriv en kort oppsummering av hva dere fant / om det var nyttig.

I denne sprinten har vi ikke tatt i bruk disse verktøyene for å finne feil. Til neste gang skal vi undersøke disse mer siden det høres ut som en god metode å finne feil på.
