**Thema:**Applikation Android  Sicheres Design/Sichere Kodierung**

# 📱 **Android-Projekt: Sicherer Prototyp mit Biometrie, TLS und SQL-Injection-Schutz** 

Entwicklung eines sicheren Android-Prototyps mit Biometrie, TLS und SQL-Injection-Schutz:

Im Rahmen unserer Studienarbeit haben wir uns für die Implementierung einer Android-Applikation entschieden. Das Ziel unserer Arbeit ist es, zu lernen, wie man eine entsprechende Ansicht einer Android-Anwendung implementiert, und auch Daten in einer Datenbank (Hier Firebase) speichern kann.

## Anforerung: 
* Entwicklung einer sicheren Android-Applikation mit Fokus auf Secure Design und Secure
* Coding. Implementierte Sicherheitsmechanismen wie die Nutzung des Android Keystore
* für sichere Schlüsselverwaltung und SSL/TLS für die verschlüsselte Kommunikation;
* integrierte Best Practices für sicheren Code, einschließlich der sicheren Speicherung von
* Nutzerdaten und Schutz vor SQL-Injektionen; führte Code-Reviews und Sicherheits-Tests
* durch, um Schwachstellen frühzeitig zu identifizieren und zu beheben


---

## 🎯 **Zielsetzung**

Entwicklung eines Android-Prototyps, der:
✅ eine biometrische Authentifizierung (Fingerprint/Face) zur Anmeldung nutzt
✅ ausschließlich über TLS (HTTPS) mit einem Backend kommuniziert
✅ lokal SQLite-Daten speichert, geschützt vor SQL-Injections

---

## 🛠 **Technologien / Frameworks**

| Komponente         | Technologie                                  |
| ------------------ | -------------------------------------------- |
| Programmiersprache | Kotlin (oder Java)                           |
| Biometrie          | AndroidX Biometric Library                   |
| Netzwerk           | Retrofit + OkHttp mit TLS-Only-Konfiguration |
| Datenbank          | Room ORM (als Schutz gegen SQL-Injection)    |
| UI                 | Jetpack Compose oder klassische XML          |
| Weitere Sicherheit | Network Security Config, ProGuard            |

---

## 🗂 **Projektstruktur (Vorschlag)**

```
/app
 ├── /src
 │    ├── /main
 │    │    ├── /java/com/example/secureapp
 │    │    │     ├── MainActivity.kt
 │    │    │     ├── auth/BiometricHelper.kt
 │    │    │     ├── network/ApiClient.kt
 │    │    │     ├── network/ApiService.kt
 │    │    │     ├── data/AppDatabase.kt
 │    │    │     ├── data/UserDao.kt
 │    │    │     ├── data/UserEntity.kt
 │    │    │     └── ui/LoginScreen.kt
 │    │    ├── /res/layout
 │    │    └── /res/values
 └── /network_security_config.xml
```

---

## 🔒 **Sicherheitsfeatures im Detail**

### 1️⃣ **Biometrie-Authentifizierung**

* Verwende die `BiometricPrompt` API (über AndroidX Biometric Library).
* Fallback: PIN / Passwort.
* Beispiel:

```kotlin
BiometricPrompt.PromptInfo.Builder()
   .setTitle("Login via Biometrie")
   .setNegativeButtonText("Abbrechen")
   .build()
```

---

### 2️⃣ **TLS-Only Kommunikation**

* **OkHttp / Retrofit:**

  * TLS erzwingen: nutze `https://` Endpunkte.
  * Zertifikat-Pinning optional (für noch höhere Sicherheit).
* **Network Security Config:**

  * Definiere `network_security_config.xml`:

```xml
<network-security-config>
    <base-config cleartextTrafficPermitted="false" />
</network-security-config>
```

* Stelle sicher: Kein HTTP-Zugriff erlaubt.
<img width="532" alt="2025-06-15 15_04_07-SichereProgrammin_Android docx - Google Docs" src="https://github.com/user-attachments/assets/2b2b4746-649c-4f23-8092-63e8b83d9ce2" />

---

### 3️⃣ **SQL-Injection-Schutz**

* Nutze `Room ORM`.
* Alle Queries parameterisiert (Room erzwingt das).
* Beispiel:

```kotlin
@Query("SELECT * FROM user WHERE username = :username LIMIT 1")
fun getUserByUsername(username: String): UserEntity?
```

* Kein dynamisches SQL-String-Building!

---
<img width="514" alt="2025-06-15 15_00_30-SichereProgrammin_Android docx - Google Docs" src="https://github.com/user-attachments/assets/3333b344-e8b2-4982-99cf-20f7c68736b3" />

## 📌 **Funktionale Features**

✅ Login-Screen mit Biometrie
✅ Synchronisation der Daten über TLS (Dummy REST API)
✅ Speicherung einfacher User-Daten lokal in SQLite (über Room)
✅ Einfache Datenanzeige im UI

---

## 📝 **Projektumfang (MVP)**

| Feature                                         | Status   |
| ----------------------------------------------- | -------- |
| Biometrisches Login                             | Pflicht  |
| TLS-gesicherte API-Kommunikation (Dummy-Server) | Pflicht  |
| SQL-Injection-sichere lokale Speicherung        | Pflicht  |
| Einfacher Screen zur Anzeige der Userdaten      | Optional |
| Zertifikat-Pinning                              | Optional |
| Backend-Mock (z. B. über Postman/Mockoon)       | Optional |

---

## 🚀 **Mini-Roadmap**

1️⃣ Grundgerüst App (UI + Navigation)
2️⃣ Biometrie-Login implementieren
3️⃣ Room-Datenbank + sichere Queries
4️⃣ Retrofit/OkHttp mit TLS konfigurieren
5️⃣ API-Calls + Daten persistieren
6️⃣ Testen: manuelle und automatisierte Tests

---

## 📂 **Zusatz: Beispiel Network Security Config**

```xml
<network-security-config>
    <base-config cleartextTrafficPermitted="false">
        <trust-anchors>
            <certificates src="system" />
        </trust-anchors>
    </base-config>
</network-security-config>
```

---




## 💡 **Besonderheiten**

👉 Alle sicherheitsrelevanten Teile sind dokumentiert im Code (z. B. warum TLS-only, warum Room).
👉 Code und Architektur lassen sich leicht auf Produktionsniveau erweitern (z. B. JWT-Token-Handling).


**1-Login**

<img width="476" alt="2025-06-15 15_02_11-SichereProgrammin_Android docx - Google Docs" src="https://github.com/user-attachments/assets/3a1a0988-db5c-4362-bdca-4aa6a3bc41f6" />


**Wir haben die Firebase-Datenbank verwendet, weil sie für kleine Projekte kostenlos ist und außerdem einfache und sichere Konfigurationen bietet.**

**1-Firebase**

In unserem Firebase Datenbank lassen sich unsere Registrierung und Login Daten einsehen

**2-Code**

1-In unserer MainActivity-Funktion haben wir die Loginfunktion definiert und

in unserer Funktion RegistrationActivity haben wir die Registrierungsfunktion definiert.

**2.2 Überprüfen der Gültigkeit von Eingabe-/Ausgabedaten der DB gemäß den Anforderungen der Anwendung** 

Anforderung SQLite ist eine typ tolerante Datenbank, die Zeichendaten in Spalten speichern kann, die in der DB als Integer deklariert sind. Was die Daten in der Datenbank betrifft, so werden alle Daten, einschließlich der numerischen Werte, in der DB als Zeichendaten des Klartextes gespeichert. Daher kann die Suche nach Zeichenketten in einer Spalte vom Typ Integer ausgeführt werden. (Bz '%123%' usw.) Außerdem ist die Begrenzung für den Wert in SQLite (Gültigkeitsprüfung) nicht vertrauenswürdig, da in manchen Fällen Daten eingegeben werden können, die länger als die Begrenzung sind, z.B. VARCHAR(100). Daher müssen Anwendungen, die SQLite verwenden, sehr vorsichtig mit diesen Eigenschaften der DB sein, und es ist notwendig, Maßnahmen entsprechend den Anforderungen der Anwendung zu ergreifen, um keine unerwarteten Daten in der DB zu speichern oder unerwartete Daten zu erhalten.

Die Maßnahmen zur Gewährleistung dieser Sicherheit sind in den folgenden 2 Punkten beschrieben. Bevor 

1\. Daten in der Datenbank zu speichern, haben wir den entsprechenden Typ und die Länge überprüft.

2\. Um den Wert aus der Datenbank zu erhalten, haben wir geprüft, ob die Daten über den angenommenen Typ und die Länge hinausgehen oder nicht.

![][image2]

Den kompletten Code finden Sie in der Java-Datei in **DashBordFragment.java.**

![][image3]

**In einem Buch mit dem Titel sichere android Programming wird hier als Beispiel im Zusammenhang mit SQL angegeben.**

**public class MainActivity extends** Activity {  
... Abbreviation ...  
*// Process for adding*  
**private void** addUserData(String idno, String name, String info) {  
*// Check for No*  
**if** (\!validateNo(idno, CommonData.REQUEST\_NEW)) {  
**return**;  
}  
*// Inserting data process*  
DataInsertTask task \= **new** DataInsertTask(mSampleDb, **this**);  
task.execute(idno, name, info);  
}  
... Abbreviation ...  
**private boolean** validateNo(String idno, **int** request) {  
**if** (idno \== **null** || idno.length() \== 0) {  
**if** (request \== CommonData.REQUEST\_SEARCH) {  
*// When search process, unspecified is considered as OK.*

**3-Design**

**Grundlegende Kenntnisse über sicheres Design**

Das Design sollte so gestaltet sein, dass es auch zur Sicherheit der Anwendung gegen Angriffe von außen beiträgt und auch die Sicherheit der Benutzerdaten gewährleistet. Man-in-the-Middle-Angriffe sind häufig Angriffe auf Anwendungen.

Dazu gibt es bestimmte Aktivierungs- oder Deaktivierungsschritte, die im Android-Manifest durchgeführt werden müssen. In diesem Teil kann der Entwickler mit Berechtigungen, Standort, ein- und ausgehenden Anrufen, Updates usw. steuern.

=======================================================




