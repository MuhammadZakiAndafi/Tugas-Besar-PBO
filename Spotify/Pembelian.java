import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

//class Pembelian
public class Pembelian extends PaketLangganan { // merupakan polymorphysm dari subclass ke superclass
    // koneksi ke database
    Connection conn;
    String link = "jdbc:mysql://localhost/spotify";

    Scanner input = new Scanner(System.in);
    String id_customer, nama_customer, no_hp, id_paket;
    Integer jumlahpaket = 3, paket_tersedia, jmlhpaket_dipesan, total_harga;

    DateFormat Hari = new SimpleDateFormat("EEEEEEEE");
    DateFormat Tanggal = new SimpleDateFormat("dd MMMM yyyy");
    DateFormat Jam = new SimpleDateFormat("HH:mm:ss");
    Date date = new Date();

    @Override
    public void save() throws SQLException {
        try {
            view();
            System.out.print("\n-------------Pembelian------------\n");
            System.out.println("Hari\t\t : " + Hari.format(date));
            System.out.println("Tanggal\t\t : " + Tanggal.format(date));
            System.out.print("Id Customer\t : ");
                this.id_customer = input.nextLine();
            System.out.print("Nama Pelanggan\t : ");
                this.nama_customer = input.nextLine();
            System.out.print("Nomor Hp\t : ");
                this.no_hp = input.nextLine();  
            System.out.print("Id Paket\t : ");
                this.id_paket = input.nextLine();
            System.out.print("Jumlah paket\t : ");
                this.jmlhpaket_dipesan = input.nextInt();
            
            // Mengambil harga dari database berdasarkan id_paket yang dimasukkan
            conn = DriverManager.getConnection(link, "root", "");
            String query = "SELECT harga FROM paketlangganan WHERE id_paket = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, this.id_paket);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                this.harga = resultSet.getInt("harga");
                System.out.print("Harga\t\t : Rp" + this.harga);
            } else {
                System.out.println("ID Paket tidak ditemukan");
                return; // Menghentikan proses jika ID Paket tidak ditemukan
            }

            this.total_harga = this.harga * this.jmlhpaket_dipesan;
            System.out.print("\n\nTotal Harga\t : Rp" + this.total_harga + "");

            conn = DriverManager.getConnection(link, "root", "");
            Statement statement = conn.createStatement();
            String sql = "INSERT INTO Pembelian ( hari, tanggal, id_customer, nama_customer, no_hp, id_paket, jumlah_paket, harga, total_harga) VALUES ( '" 
                + Hari.format(date) + "', '"+ Tanggal.format(date) + "', '"+ this.id_customer + "', '" + this.nama_customer + "', '" + this.no_hp + "' , '"
                    + this.id_paket + "','" + this.jmlhpaket_dipesan + "', '" + this.harga + "', '" + this.total_harga + "')";
            statement.execute(sql);
            System.out.println("\nBerhasil Melakukan Pembelian!!");
        }

        // exception SQL
        catch (SQLException e) {
            System.err.println("\nPembelian Gagal");
        }

        // excception input tidak sesuai dengan tipe data
        catch (InputMismatchException e) {
            System.out.println("\nTipe Inputan Data Harus Benar");
        }

        System.out.println("\nApakah Anda ingin mencetak struk? (y/n): ");
        input.nextLine();
        String printChoice = input.nextLine();

        if (printChoice.equalsIgnoreCase("y")) {
            printStruk();
        } else {
            System.out.println("Struk tidak dicetak/n");
        }
    }

    public void printStruk() throws SQLException {
        try {
            conn = DriverManager.getConnection(link, "root", "");
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM pembelian ORDER BY id_customer DESC LIMIT 1";
            ResultSet resultSet = statement.executeQuery(query);
    
            while (resultSet.next()) {
                System.out.println("\n=================================");
                System.out.println("=========== S T R U K ===========");
                System.out.println("Hari\t\t :" + resultSet.getString("hari"));
                System.out.println("Tanggal\t\t :" + resultSet.getString("tanggal"));
                System.out.println("---------------------------------");
                System.out.println("Id Customer\t :" + resultSet.getString("id_customer"));
                System.out.println("Nama Pelanggan\t :" + resultSet.getString("nama_customer"));
                System.out.println("Nomor Hp\t :" + resultSet.getString("no_hp"));
                System.out.println("Id Paket\t :" + resultSet.getString("id_paket"));
                System.out.println("Jumlah Paket\t :" + resultSet.getInt("jumlah_paket"));
                System.out.println("Harga\t\t :" + resultSet.getInt("harga"));
                System.out.println("Total Harga\t :" + resultSet.getInt("total_harga"));
                System.out.println("\n========== TERIMAKASIH ==========\n\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // System.err.println("\nGagal Mencetak Struk");
        }
    }
}
