package test;

import lecture.chapter03.SList;
import lecture.chapter03.IKey;
import lecture.chapter03.IFIterator;
import ue_2.s2410238049.MySList;
import ue_2.s2410238049.TelephoneBookEntry;
import ue_2.s2410238049.TelephoneBookEntryKey;

public class TelephoneTest {

    public static void main(String[] args) {
        testMySListWithTelephoneBookEntries();
    }

    private static void testMySListWithTelephoneBookEntries() {
        MySList list = new MySList();

        TelephoneBookEntry e1 = new TelephoneBookEntry(
                "Max", "Mustermann",
                "Hauptstraße 1", "4040", "Linz",
                new String[] { "0732/123456" });

        TelephoneBookEntry e2 = new TelephoneBookEntry(
                "Anna", "Muster",
                "Nebenweg 2", "4020", "Linz",
                new String[] { "0732/654321", "0664/1111111" });

        TelephoneBookEntry e3 = new TelephoneBookEntry(
                "Max", "Mustermann",
                "Hauptstraße 1", "4040", "Linz",
                new String[] { "0664/2222222" });

        TelephoneBookEntry e4 = new TelephoneBookEntry(
                "Julia", "Huber",
                "Ringstraße 3", "1010", "Wien",
                new String[] { "01/1234567" });

        TelephoneBookEntry e5 = new TelephoneBookEntry(
                "Max", "Mustermann",
                "Nebenstraße 9", "4040", "Linz",
                new String[] { "0732/999999" });

        list.prepend(e1);
        list.prepend(e2);
        list.append(e3);
        list.append(e4);

        printList("Liste nach prepend/append:", list);

        boolean insertedExisting = list.insert(e1, e5);
        System.out.println();
        System.out.println("insert(e1, e5) (sollte true sein): " + insertedExisting);
        printList("Liste nach insert hinter e1:", list);

        TelephoneBookEntry notInList = new TelephoneBookEntry(
                "Paul", "NichtDrin",
                "Nowhere 0", "0000", "Nirgendwo",
                new String[] { "000/000000" });

        TelephoneBookEntry e6 = new TelephoneBookEntry(
                "Test", "InsertFail",
                "Failstraße 1", "0000", "Failstadt",
                new String[] { "000/111111" });

        boolean insertedMissing = list.insert(notInList, e6);
        System.out.println();
        System.out.println("insert(notInList, e6) (sollte false sein): " + insertedMissing);
        printList("Liste nach fehlgeschlagenem insert:", list);

        IKey keyMax = new TelephoneBookEntryKey("Max", "Mustermann");
        Object firstFound = list.search(keyMax);
        System.out.println();
        System.out.println("Ergebnis von search(keyMax):");
        System.out.println(firstFound);

        SList allMax = list.searchAll(keyMax);
        System.out.println();
        printList("Ergebnis von searchAll(keyMax):", allMax);

        IKey keyUnknown = new TelephoneBookEntryKey("Unbekannt", "Person");
        Object searchUnknown = list.search(keyUnknown);
        System.out.println();
        System.out.println("Ergebnis von search(keyUnknown) (sollte null sein):");
        System.out.println(searchUnknown);

        SList allUnknown = list.searchAll(keyUnknown);
        System.out.println();
        printList("Ergebnis von searchAll(keyUnknown) (sollte leer sein):", allUnknown);

        list.remove(e1);
        System.out.println();
        printList("Liste nach remove(e1):", list);

        list.remove(e4);
        System.out.println();
        printList("Liste nach remove(e4):", list);
    }

    private static void printList(String title, SList list) {
        System.out.println();
        System.out.println(title);
        IFIterator it = list.iterator();
        int index = 0;
        while (it.hasNext()) {
            System.out.println("[" + index + "]");
            System.out.println(it.next());
            index++;
        }
        if (index == 0) {
            System.out.println("(Liste ist leer)");
        }
        System.out.println("-----------------------------");
    }
}
