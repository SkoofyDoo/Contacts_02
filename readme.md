# Contacts 
# Version 0.1

# Features
* nur Lokal
* alle Kontakte anzeigen
* Kontakte speichern
* Kontakte Löschen

# Plan
* Schichten > Pakete und Interfaces



# TODO
* Benutzermeldung
* Search mit Teilstring und TextField (lovercase)

# Aufgabe 

* erstmal für save und findAll

# zweite ContactDAO Implementierung 
* ContactFileDAO
    ContactFileDAO soll alle Methoden implementieren
* bei save und delete kann die ganze Datei nei gesschrieben werden
* alle Merthoden (auch Hilfsmethoden) können in ContactFileDAO stehen

### Format/Struktur für Text-File

> für jede Zeile
> Trennzeichen z.B 2 | MAX | 1111
> 
>  // 3 | Max | 1111
String line = "3|Max|1111";
String[] arr = line.split("| ");
System.out.println(arr[0]);
System.out.println(arr[1]);
System.out.println(arr[2]);