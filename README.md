# Cos'è
Applicazione / portale web per la ricerca di lavoro.
Progetto finale di gruppo per il corso sviluppatori Java di Generation Italy.

# Descrizione essenziale del funzionamento
0. Gli amministratori del portale, d'ora in poi chiamati "gestori", sono registrati "a freddo" sul database MySQL utilizzato.
1. Un'azienda si registra al sito: dalla sua area riservata crea annunci di lavoro con vari parametri.\
A questo punto, gli annunci non sono ancora visibili al pubblico.
2. Un gestore effettua l'accesso al sito tramite le proprie credenziali: dalla sua area riservata può visualizzare tutti gli annunci e i dati ad essi associati, in particolare lo stato di visibilità (creato/pubblicato/chiuso) dell'annuncio.\
Dunque, trova e visualizza gli annunci creati dall'azienda: dopo averli controllati o valutati, decide se pubblicarli o meno.\
All'atto della pubblicazione, gli annunci diventano visibili al pubblico.
3. Un utente internet qualsiasi entra nel sito e, tramite la barra di ricerca, trova un annuncio di lavoro a cui vuole candidarsi: si registra al sito (come candidato) ed effettivamente si candida all'annuncio scelto.\
A questo punto, la sua candidatura è visibile solo ai gestori.
4. Un gestore seleziona un annuncio e visualizza la lista delle candidature: esaminati i candidati, può decidere se scartarli (perché troppo non idonei) oppure se approvarli e quindi passare la valutazione all'azienda che ha creato l'annuncio.\
In caso di approvazione, il/i candidato/i risultano visibili all'azienda.
5. L'azienda seleziona uno dei propri annunci e visualizza la lista delle candidature "approvate": può dunque valutare i candidati a sua volta ed approvarli o scartarli.

# Di cosa mi sono occupato
Escluso tutto quello inerente alla sola parte estetica, praticamente tutto:
- Back End
- Struttura delle pagine JSP/HTML
- Integrazione col Front-End
- Debugging

## Note
- Il progetto non è da considerarsi un prodotto finito: l'obiettivo era lavorare in gruppo per produrre un'applicazione web come richiesto nella consegna dell'esercizio-progetto, con il principale obiettivo di arrivare ad un buon livello di funzionalità e "presentabilità" estetica.\
Possono essere presenti errori.\
&emsp;Per via di diversi fattori, la logica (più praticamente intesa come suddivisione dei pezzi di codice e scrittura dei metodi) della maggior parte delle funzionalità (classi servlet più i metodi della business logic annessi) è errata: un esempio di logica MVC (più) "corretta" è [`OttieniAnnunciDiCandidatoServlet.java`](src/main/java/com/aziendaRecruiting/ui/servlet/OttieniAnnunciDiCandidatoServlet.java) con il metodo annesso [`getAnnunciOfCandidato()`](src/main/java/com/aziendaRecruiting/businessLogic/RecruitingBusinessLogic.java#L368) della business logic.
- Il sito non è responsive.
- Il sito dovrebbe comportarsi come previsto su uno schermo FullHD con un rapporto d'aspetto di 16:9 (1920x1080).

## File
- [Consegna e richieste del progetto](https://github.com/D4nM/recruiting-webapp/files/11065143/aziendaRecruiting_descrizione_sintetica.pdf) (da considerarsi indicativa)
- [Modello E-R](https://github.com/D4nM/recruiting-webapp/files/11065084/aziendaReclutamentoV2_ER.drawio.pdf) (il modello di base è quello, ma è da considerarsi indicativo perché in itinere alcuni dettagli/campi sono stati modificati)
