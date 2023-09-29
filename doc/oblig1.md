# Oblig A 

### A1: laget et repo i gitlab 

## A2 Konsept:
* Spillfigur som kan styres – WASD
* Top down verden:
   * verden sett fra top down perspektiv der spilleren kan bevege seg høyre/venstre/opp/ned
   * Objekter/bygninger som spilleren ikke kan gå gjennom
* Fest folk / fiender som spilleren skal vinne over ved å "skyte" på dem
* Spilleren kan samle opp våpen og ammunisjon fra bakken og loot-chests
* samle poeng ved å vinner over fiender
* Utfordringen i spillet: å samle nok poeng, å bekjempe fiendene, å nå frem til og bekjempe en «big boss» 

* Verden er bygget opp av blokker med fast størrelse (felter i et 2D-rutenett)
* Verden er større enn skjermen og scroller (horisontalt eller vertikalt)
* Spilleren kan drepe fiendene ved å skyte dem
* «Power-ups» som gir spilleren spesielle krefter 	(ost)
* Skjulte gjenstander

-> lignende en top down shooter spill


## A3.1 Process:	-> Kanban
Møter og hyppighet av dem: 						1 møte i uken +/-
Kommunikasjon mellom møter: 						Discord
Arbeidsfordeling: 							Rollene og board
Oppfølging av arbeid: 							på møtene og gjennom merge requests
Deling og oppbevaring av felles dokumenter, diagram og kodebase: 	GitLab (Git)
	

## A3.2 Forventet produkt

#### MVP:
* Vise et vindu på skjermen
* Vise titelen til spillet på skjermen
* Vise et spillebrett
* Vise spiller på spillebrett
* Flytte spiller (vha taster e.l.)
* Spiller interagerer med terreng
* Spiller har *poeng* og interagerer med poenggjenstander
* Vise fiender/fest-folk; de skal interagere med terreng og spiller
* Spiller kan dø (ved kontakt med fiender)
* Mål for spillbrett drepe alle fiender
* Nytt spillbrett når forrige er ferdig (levels)
* Start-skjerm ved oppstart / game over / hjelpeskjerm

#### Brukerhistorier:
* Som utvikler vil jeg at spillet skal vises i vindu med grafikk.	(akseptansekriterie: når spillet vises korrekt i vindu med grafikk da vi kjører programmet.)
* Som utvikler vil jeg at et spillbrett skal vises.	(akseptansekriterie: når spilbrettet vises korrekt på skjemren da vi kjører programmet.)
* Som bruker vill jeg kunne skille vegger fra gulv.	(akseptansekrav: når det ikke er lenger mulig å gå gjennom vegger og de er godt synlige at de ikke er golv)
* som bruker vil jeg kunne skille fiendens prosjektiler fra mine egne.	(akseptansekrav: når det er uten mistanke mulig å se forksjell mellom fiende og spiller prosjektiler)
* som bruker vil kunne se hvor mange poeng jeg har, liv, level,osv.	(akseptansekrav: når liv og annen nødvendig statistikk blir tydelig vist på spillersiden.)
* som bruker vil jeg kunne se hvilke gjenstander jeg har	(akseptansekrav: når det er lett å se på skjermen hvilke gjenstander spilleren har i inventory, enten som gui rett på spillerskjermen eller ved å åpne en egen view av inventory)
* som utvikler vil jeg at det skal være enkelt å se hvilket level spilleren er på.
* som utvikler vil jeg ha en velfungerende startscreen, hjelpeskjerm. 	(akseptansekriterie: når bruker starter spill og får en startscreen)
* som bruker vil jeg se når jeg har drept alle fiender / blitt ferdig med en level enkelt. (akseptansekriterie: når bruker får beskjed om at level har blitt fullført)
* som utvikler vil jeg se spiller  (akseptansekriterie: når spiller vises på skjerm da programmet skjører.)
* Som bruker vil jeg enkelt se når jeg har drept én gitt fiende, kanskje noe lyd elsk. (akseptansekrav: når det er tydelig at en fiende har død, indikert med en lyd eller bilde, det skal ikke være mistanke om fienden døde eller ei.)
* Som utvikler vil jeg se fiender (akseptansekrav: når fiende brikken vises på skjermen etter kjøring av programm)
* som bruker vil jeg kunne skille spiller fra fiender. (akseptansekritere: når fiender vises på skjerm)


#### En prioritert liste over hvilke brukerhistorier dere vil ha med i første iterasjon (altså frem til levering av denne oppgaven): (1 viktigst -> 10 minst)

* Som utvikler vil jeg at spillet skal vises i vindu. (1) 
* Som utvikler vil jeg at et spillbrett skal vises. (1) 
* Som bruker vil jeg kunne skille vegger fra gulv (3)
* Som utvikler vil jeg se spiller. (2)
* Som utvikler vil jeg se fiender (3)
* Som bruker vil jeg se en startskjerm (1)

## Oppgave A5: Oppsummering

Til første innlevering synes vi at gruppearbeidet gikk fint. Mye av tiden gikk hovedsakelig til å planlegge hvilket og hvordan spillet vårt skal fungere. Alle gruppemedlemmene bidro med innspill og diskusjon når vi fastsatte hvilke brukerhistorier spillet har. Videre ble vi enige om at arbeidsmetodikken vi skal bruke er Kanban da de fleste var enige om at dette passet deres arbeidsstil. Flere på gruppen har ikke prøvd å jobbe med Kanban før så vi må finne ut ved å prøve om denne metodikken fungerer for gruppen. 

Tiden ble prioritert til å diskutere utformingen av spillet for å legge et godt grunnlag videre i oppgaven. Til denne innleveringen har man derfor prioritert kodingen i mindre grad og man har da laget og valgt skjellettstrukturen for hvilke verktøy som skal brukes for å lage spillet.

Vi har brukt Discord som kommunikasjonskanal som har fungert fint siden det er en plattform alle på gruppen er vant med. Alle har en aktiv studiehverdag så det blir en utfordring å bygge en god kultur for å jobbe jevnt med prosjektet. Fremover blir utfordringen derfor å fastsette hvordan vi skal jobbe videre spillet og følge opp at alle har produktive arbeidsoppgaver som de kan bidra til gruppen og utviklingen.

Gruppen har levert på det vi forventet da vi så for oss at mye av tiden skulle gå til planlegging av hvordan spillet skal ta form. Til neste iterasjon vil vi prioritere å levere MVP som er spesifisert i «A3 – få oversikt over forventet produkt».



