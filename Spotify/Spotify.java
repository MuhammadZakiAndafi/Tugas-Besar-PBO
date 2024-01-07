import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

//Class Program
public class Spotify {
    // koneksi ke database
    static Connection conn;
    static String link = "jdbc:mysql://localhost/spotify";
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        System.out.println("\n========== Z E A L O U S =========");
        System.out.println("\n          SELAMAT DATANG          \n");
        
        Loginn Admin = new Loginn();
        Admin.login();

        menu();
    }

    private static void menu() throws SQLException {
        boolean Kembali = true;
        Integer pilihan;

        // perulangan while
        while (Kembali) {
            System.out.println("\n==================================");
            System.out.println("============ M E N U =============");
            System.out.println("1. Lihat Daftar Paket");
            System.out.println("2. Input Paket Langganan Baru");
            System.out.println("3. Ubah Paket Langganan");
            System.out.println("4. Hapus Paket Langganan");
            System.out.println("5. Pilih Paket Langganan");
            System.out.println("6. Keluar");
            System.out.print("\nPilihan Anda : ");
            
            try {
                pilihan = Integer.parseInt(input.nextLine());
                // input.nextLine(); // Membersihkan newline dari buffer setelah nextInt()

                switch (pilihan) {
                    case 1:
                        new PaketLangganan().view();
                        break;

                    case 2:
                        new PaketLangganan().insert();
                        break;

                    case 3:
                        new PaketLangganan().update();
                        break;

                    case 4:
                        new PaketLangganan().delete();
                        break;

                    case 5:
                        new Pembelian().save();
                        break;

                    case 6:
                        Kembali = false;
                        break;

                    default:
                        System.out.println("Inputan Angka 1-6 !");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input harus berupa angka.");
                input.nextLine(); // Membersihkan newline dari buffer setelah input yang salah
            }

        }
        System.out.println("\n========= S E L E S A I ==========\n");
    }
}