import java.util.HashMap;
import java.util.Map;

public class KorektorCijena {

    public static void main(String[] args) {

        Map<Integer, Integer> tblStavka = new HashMap<>();
        tblStavka.put(8, 30); // inicijalna cijena za stavku 8
        tblStavka.put(9, 55); // inicijalna cijena za stavku 9
        System.out.println("Početne cijene: " + tblStavka);

        boolean korekcija = azuriranjeCijena(tblStavka);

        if (korekcija) {
            System.out.println("Korigirane cijene: " + tblStavka);
        } else {
            System.out.println("Greška, cijene nisu korigirane!");
        }
    }

    private static boolean azuriranjeCijena(Map<Integer, Integer> itemTable) {

        Integer inicijalnaCijenaId8 = itemTable.get(8);
        Integer inicijalnaCijenaId9 = itemTable.get(9);

        try {
            // ažuriranje cijena po stavkama
            if (inicijalnaCijenaId8 != null) {
                itemTable.put(8, inicijalnaCijenaId8 + 10);
            }
            if (inicijalnaCijenaId9 != null) {
                itemTable.put(9, inicijalnaCijenaId9 - 10);
            }

            if (itemTable.get(8) == null || itemTable.get(9) == null) {
                throw new RuntimeException("Transakcija nije izvršena!");
            }
            return true;

        } catch (Exception e) {
            // rollback na inicijalne cijene u slučaju greške
            if (inicijalnaCijenaId8 != null) {
                itemTable.put(8, inicijalnaCijenaId8);
            }
            if (inicijalnaCijenaId9 != null) {
                itemTable.put(9, inicijalnaCijenaId9);
            }
            return false;
        }
    }
}
