import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;


public class PaketLangganan implements Data  //class paketlanggan mengimplentasikan interface dari class data
{
    // koneksi ke database
    Connection conn;
    String link = "jdbc:mysql://localhost/spotify";
    Scanner input = new Scanner(System.in);

    String id_paket;
    String jenis_paket;
    String masa_berlaku;
    Integer harga;


    // implementasi dari interface

    @Override
    public void insert() throws SQLException {
        System.out.print("\n----- Tambah Paket Langganan -----");
        System.out.print("\nID Paket\t : ");
        this.id_paket = input.nextLine();
        System.out.print("Nama Paket\t : ");
        this.jenis_paket = input.nextLine();
        System.out.print("masa_berlaku\t : ");
        this.masa_berlaku = input.nextLine();
        System.out.print("Harga\t\t : ");
        this.harga = input.nextInt();

        String sql = "INSERT INTO PaketLangganan (id_paket, jenis_paket, masa_berlaku, harga) VALUES ('" 
        + id_paket + "','" + jenis_paket + "','" + masa_berlaku + "', '" + harga + "' )";
        conn = DriverManager.getConnection(link, "root", "");
        Statement statement = conn.createStatement();
        statement.execute(sql);
        System.out.println("\nInput Data Berhasil!");


        statement.close();
    }

    @Override
    public void view() throws SQLException {
        // akses data yang ada di database PaketLangganan
        String sql = "SELECT * FROM PaketLangganan";
        conn = DriverManager.getConnection(link, "root", "");
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);

        while (result.next()) {
            System.out.print("\nId Paket\t : ");
            System.out.println(result.getString("id_paket"));
            System.out.print("Nama Paket\t : ");
            System.out.println(result.getString("jenis_paket"));
            System.out.print("masa_berlaku\t : ");
            System.out.println(result.getString("masa_berlaku"));
            System.out.print("Harga\t\t : ");
            System.out.println(result.getInt("harga"));
        }

        statement.close();
    }

    @Override
    public void update() throws SQLException {
        // try
        try {
            view();
            Integer pil;
            System.out.print("\n------ Ubah Paket Langganan ------");
            System.out.print("\nid Paket : ");
            String ubah = input.nextLine();

            // akses data database tabel PaketLangganan
            String sql = "SELECT * FROM PaketLangganan WHERE id_paket ='" + ubah + "'";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            // percabangan if
            if (result.next()) {
                System.out.println("\n------ Data yang akan diubah -----");
                System.out.println("1. Masa Berlaku");
                System.out.println("2. Harga Paket");
                System.out.print("\nPilihan Anda  : ");
                pil = input.nextInt();
                input.nextLine();

                // percabangan switch case
                switch (pil) {
                    case 1:
                        System.out.print("\nMasa Berlaku [" + result.getString("masa_berlaku") + "] : ");
                        String ubah1 = input.nextLine();
                        // update data database tabel PaketLangganan
                        sql = "UPDATE PaketLangganan SET masa_berlaku = '" + ubah1 + "' WHERE id_paket ='" + ubah + "'";
                        if (statement.executeUpdate(sql) > 0) {
                            System.out.println("Berhasil Memperbarui Masa Berlaku " + ubah + "!");
                        }
                        break;

                    case 2:
                        System.out.print("\nHarga Paket [" + result.getInt("harga") + "] : ");
                        Integer ubah2 = input.nextInt();
                        // update data database tabel PaketLangganan
                        sql = "UPDATE PaketLangganan SET harga = " + ubah2 + " WHERE id_paket ='" + ubah + "'";
                        input.nextLine();
                        if (statement.executeUpdate(sql) > 0) {
                            System.out.println("Berhasil Memperbarui Harga Paket " + ubah + "!");
                        }
                        break;

                    default:
                        System.out.println("\n\tERROR");
                        System.out.println("Input Angka 1 atau 2!");
                        break;
                }
            }

            else {
                System.out.println("ERROR !");
            }
        }

        // exeption SQL
        catch (SQLException e) {
            System.err.println("Update Data Gagal");
        }

        // exception input tidak sesuai jenis data
        catch (InputMismatchException e) {
            System.err.println("Gagal! Masukkan Data yang Benar");
        }
    }

    public void save() throws SQLException {

    }

    @Override
    public void delete() throws SQLException {
        try {
            view();
            System.out.println("\n-----Hapus Paket Langganan-----");
            System.out.print("Id Paket : ");
            this.id_paket = input.next();

            String sql = String.format("DELETE FROM paketlangganan WHERE id_paket ='%s'", id_paket);
            conn = DriverManager.getConnection(link, "root", "");
            Statement statement = conn.createStatement();
            // ResultSet result = statement.executeQuery(sql);

            if (statement.executeUpdate(sql) > 0) {
                System.out.println("!!!Berhasil Menghapus Paket Langganan " + id_paket + "!!!");
            }
        }

        catch (Exception e) {
            System.out.println("Id Paket yang anda inputkan salah!!");
        }

    }
}
