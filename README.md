# Primer_projekta — Selenium Java Test Automation

Dobrodošli! Ovaj projekat je uvod u automatizovano testiranje web aplikacija pomoću **Selenium WebDriver** i **Java** programskog jezika. Namenjen je studentima koji se prvi put sreću sa pojmovima kao što su lokatori, Page Object Model i automatizovani testovi.

---

## Sadržaj

1. [Tehnologije](#tehnologije)
2. [Struktura projekta](#struktura-projekta)
3. [Kako pokrenuti testove](#kako-pokrenuti-testove)
4. [Šta je Page Object Model?](#šta-je-page-object-model)
5. [Lokatori — kako pronalazimo elemente na stranici](#lokatori)
6. [XPath — pisanje lokatora](#xpath)
7. [CSS Selektori — alternativa XPathu](#css-selektori)
8. [XPath vs CSS — kada koristiti šta?](#xpath-vs-css)
9. [Struktura testa](#struktura-testa)

---

## Tehnologije

| Tehnologija | Uloga |
|---|---|
| **Java 11** | Programski jezik |
| **Maven** | Build alat i upravljanje zavisnostima |
| **Selenium WebDriver 4** | Biblioteka za kontrolu browsera |
| **WebDriverManager** | Automatsko preuzimanje ChromeDriver-a |
| **JUnit 5** | Framework za pisanje i pokretanje testova |

---

## Struktura projekta

```
Primer_projekta/
├── pom.xml                          ← Maven konfiguracija i zavisnosti
└── src/
    ├── main/java/
    │   ├── constants/
    │   │   └── Constants.java       ← Sve konstantne vrednosti (URL, labele, očekivani rezultati)
    │   ├── locators/
    │   │   └── Locators.java        ← Svi XPath lokatori za elemente na stranici
    │   └── pages/
    │       ├── BasePage.java        ← Osnovna klasa koju nasledjuju sve page klase
    │       ├── PaHomePage.java      ← Početna stranica — akcije pretrage
    │       ├── PaResultsPage.java   ← Stranica rezultata — sortiranje, klik na oglas
    │       └── PaDetailPage.java    ← Stranica oglasa — getter metode i verifikacije
    └── test/java/
        ├── base/
        │   └── BaseTest.java        ← Osnovna test klasa (setup i teardown)
        └── tests/
            └── PaPretragaTest.java  ← Konkretan test scenario
```

### Zašto ovakva podela?

- **constants/** — Nikada ne pišemo "magične stringove" direktno u kod. Sve vrednosti koje se koriste na više mesta idu u Constants.java. Ako se URL promeni, menjamo ga na jednom mestu.
- **locators/** — Svi lokatori su na jednom mestu. Ako se HTML promeni, ažuriramo samo Locators.java, bez dodirivanja ostatka koda.
- **pages/** — Svaka stranica aplikacije ima svoju klasu. Klasa zna samo za svoju stranicu i ne meša se u logiku drugih.
- **base/** — Zajednički kod koji koriste svi testovi (pokretanje browsera, zatvaranje browsera).
- **tests/** — Samo test scenariji. Nikakva logika, samo pozivanje metoda.

---

## Kako pokrenuti testove

### Preduslovi
- Instaliran **Java 11** ili noviji
- Instaliran **Maven**
- Instaliran **Google Chrome**

### Pokretanje

```bash
# U folderu projekta
mvn test
```

WebDriverManager će automatski preuzeti odgovarajući ChromeDriver — ne morate ništa ručno instalirati.

---

## Šta je Page Object Model?

**Page Object Model (POM)** je dizajn obrazac u test automatizaciji koji kaže:

> Svaka stranica aplikacije treba da ima svoju Java klasu koja opisuje tu stranicu.

Umesto da pišemo:
```java
// Loše — logika i lokatori su direktno u testu
driver.findElement(By.xpath("//div[contains(@class,'sumo_brand')]")).click();
driver.findElement(By.xpath("//div[contains(@class,'sumo_brand')]//label[text()='Volkswagen']")).click();
```

Pišemo:
```java
// Dobro — test poziva metodu, ne brine o detaljima
homePage.selectBrand("Volkswagen");
```

### Prednosti POM-a

- **Čitljivost** — Test liči na opis scenarija, ne na tehnički kod
- **Održavanje** — Ako se lokator promeni, menjamo ga samo u jednoj klasi
- **Ponovna upotreba** — Istu metodu možemo koristiti u više testova

---

## Lokatori

Da bi Selenium mogao da klikne dugme ili unese tekst, mora prvo da **pronađe element** na stranici. To radi pomoću **lokatora**.

Zamislite stranicu kao dokument sa hiljadama elemenata. Lokator je kao adresa koja kaže: "Pronađi mi ovaj konkretan element."

### Vrste lokatora u Seleniumu

| Vrsta | Primer | Kada koristiti |
|---|---|---|
| `By.id` | `By.id("brand")` | Kada element ima jedinstven ID — najbrži i najpouzdaniji |
| `By.name` | `By.name("q")` | Kada element ima name atribut (čest kod form polja) |
| `By.className` | `By.className("btn-search")` | Kada element ima jedinstven CSS class |
| `By.cssSelector` | `By.cssSelector("input#brand")` | Fleksibilan, brz, čitljiv |
| `By.xpath` | `By.xpath("//input[@id='brand']")` | Najmoćniji, može sve — ali sporiji |
| `By.linkText` | `By.linkText("Prijavi se")` | Za linkove sa tačno poznatim tekstom |

### Kako pronaći lokator u browseru?

1. Otvori stranicu u Chrome-u
2. Desni klik na element → **Inspect**
3. U DevTools-u vidiš HTML strukturu elementa
4. Desni klik na HTML element → **Copy** → **Copy XPath** ili **Copy selector**

> ⚠️ Automatski kopirani XPath/CSS iz DevTools-a su često nestabilni. Bolje je naučiti pisati ih ručno.

---

## XPath

XPath je jezik za navigaciju kroz HTML strukturu. Zamislite HTML kao stablo — XPath opisuje putanju do čvora koji tražite.

### Osnovna sintaksa

```xpath
//tagName[@atribut='vrednost']
```

- `//` — traži element bilo gde u dokumentu (ne mora biti direktan potomak)
- `tagName` — naziv HTML elementa (`div`, `input`, `button`, `a`...)
- `[@atribut='vrednost']` — uslov koji element mora da ispuni

### Primeri

```xpath
// Pronađi input element čiji je id "brand"
//input[@id='brand']

// Pronađi div koji u svom class atributu sadrži "sumo_brand"
//div[contains(@class,'sumo_brand')]

// Pronađi label čiji je tačan tekst "Volkswagen"
//label[text()='Volkswagen']

// Pronađi label čiji tekst sadrži "Volksw"
//label[contains(text(),'Volksw')]

// Pronađi button koji sadrži tekst "PRETRAGA"
//button[contains(text(),'PRETRAGA')]

// Pronađi div sa klasom "sumo_brand", a unutar njega label sa tekstom "Volkswagen"
//div[contains(@class,'sumo_brand')]//label[text()='Volkswagen']

// Pronađi sibling — div koji dolazi odmah posle diva sa tekstom "Marka"
//div[normalize-space(text())='Marka']/following-sibling::div[1]

// Pronađi tačno prvi article element sa klasom "classified"
(//article[contains(@class,'classified')])[1]
```

### Korisne XPath funkcije

| Funkcija | Opis | Primer |
|---|---|---|
| `contains()` | Proverava da li atribut/tekst sadrži vrednost | `contains(@class,'btn')` |
| `text()` | Selektuje tačan tekst elementa | `//button[text()='OK']` |
| `normalize-space()` | Ignoriše višak razmaka | `normalize-space(text())='Marka'` |
| `starts-with()` | Proverava početak stringa | `starts-with(@id,'btn_')` |
| `following-sibling` | Sledeći element na istom nivou | `div/following-sibling::div[1]` |
| `parent::` | Roditeljski element | `//span/parent::div` |
| `ancestor::` | Bilo koji predak | `//span/ancestor::form` |

### Relativni vs Apsolutni XPath

```xpath
// Apsolutni — krhak, lomi se ako se HTML promeni
/html/body/div[3]/div[1]/form/div[2]/input

// Relativni — stabilan, preporučen
//input[@name='brand']
```

> ✅ Uvek koristite **relativni XPath** koji počinje sa `//`.

---

## CSS Selektori

CSS selektori su sintaksa koju poznajete iz CSS stilova. Brži su od XPath-a i čitljiviji za jednostavne slučajeve.

### Osnovna sintaksa

```css
tagName#id
tagName.className
tagName[atribut='vrednost']
```

### Primeri

```css
/* Pronađi input sa id="brand" */
input#brand

/* Pronađi div sa klasom "sumo_brand" */
div.sumo_brand

/* Pronađi element sa atributom name="q" */
input[name='q']

/* Pronađi div koji sadrži klasu "classified" (ekvivalent contains u XPath) */
div[class*='classified']

/* Direktno dete — button koji je direktno unutar forme */
form > button

/* Potomak — button koji je bilo gde unutar forme */
form button

/* Pronađi prvi article */
article:first-child

/* Pronađi element sa više klasa */
button.btn.btn-primary
```

### Pseudo-selektori

```css
/* Pronađi svaki drugi red tabele */
tr:nth-child(2)

/* Pronađi poslednji element liste */
li:last-child

/* Pronađi input koji nije disabled */
input:not([disabled])
```

---

## XPath vs CSS — kada koristiti šta?

| | XPath | CSS Selektor |
|---|---|---|
| **Brzina** | Sporiji | Brži |
| **Čitljivost** | Kompleksniji za složene upite | Čistiji za jednostavne upite |
| **Navigacija gore** | ✅ Može (parent, ancestor) | ❌ Ne može ići "gore" |
| **Pretraga po tekstu** | ✅ `text()`, `contains()` | ❌ Nije podržano |
| **Složena logika** | ✅ Moćniji | Ograničeniji |

**Preporuka:**
- Koristite **CSS** kada možete doći do elementa po ID-u, klasi ili atributu
- Koristite **XPath** kada morate da pretražujete po tekstu, idete "gore" kroz stablo, ili vam trebaju složeniji uslovi

---

## Struktura testa

### BaseTest.java

Svaki test nasledjuje `BaseTest`. On se stara o pokretanju i zatvaranju browsera — to ne mora svaki test da piše iznova.

```java
@BeforeEach  // Pokreće se pre svakog testa
public void setUp() {
    driver = new ChromeDriver();
    driver.get(Constants.PA_URL);  // Otvara browser i ide na URL
}

@AfterEach   // Pokreće se posle svakog testa
public void tearDown() {
    driver.quit();  // Zatvara browser
}
```

### Tok testa

```java
PaDetailPage detailPage = new PaHomePage(driver)
        .selectBrand(Constants.PA_BRAND)       // Bira marku
        .selectYearFrom(Constants.PA_YEAR_FROM) // Bira godište
        .selectChassis(Constants.PA_CHASSIS)    // Bira karoseriju
        .selectFuel(Constants.PA_FUEL)          // Bira gorivo
        .clickSearch()                          // Pokreće pretragu → vraća PaResultsPage
        .sortBy(Constants.PA_SORT_BY_PRICE)     // Sortira rezultate → vraća PaResultsPage
        .clickFirstResult()                     // Otvara prvi oglas → vraća PaDetailPage
        .verifyBrand(Constants.PA_EXPECTED_BRAND)
        .verifyYearFrom(Constants.PA_EXPECTED_YEAR_FROM)
        .verifyChassis(Constants.PA_EXPECTED_CHASSIS)
        .verifyFuel(Constants.PA_EXPECTED_FUEL);
```

Ovaj stil se zove **Method Chaining** — svaka metoda vraća objekat na kome se može pozvati sledeća metoda. Rezultat je čitljiv kod koji liči na opis scenarija rečima.

### Verifikacije (Assertions)

Assertion proverava da li je nešto tačno. Ako nije, test pada i JUnit prijavljuje grešku.

```java
// Proverava da su dva stringa jednaka
assertEquals("Volkswagen", detailPage.getBrand(), "Marka se ne poklapa");

// Proverava da je uslov tačan
assertTrue(getYear() >= 2020, "Godište je pre 2020");
```

---

## Napomene za studente

- **Ne kopirajte XPath iz DevTools slepo** — naučite da ga pišete ručno, biće pouzdaniji
- **Konstante su vaši prijatelji** — nikada ne pišite stringove direktno u test metode
- **Jedan test — jedan scenario** — test treba da proverava jednu stvar, ne sve odjednom
- **Ime testa treba da opisuje šta se testira** — `pretragaPoKriterijumimaIVerifikacijaOglasa` je dobro ime

---

*Projekat koristi [Selenium WebDriver](https://www.selenium.dev/), [JUnit 5](https://junit.org/junit5/) i [WebDriverManager](https://bonigarcia.dev/webdrivermanager/).*
