//class Customer
public class Customer {
    String id_customer;
    String nama_customer;

    // constructor
    public Customer() {
    }

    // constructor
    public Customer(String id_customer, String nama_customer) {
        this.id_customer = id_customer;
        this.nama_customer = nama_customer;
        System.out.println("\n");
        System.out.println(this.id_customer + " berhasil berlangganan paket!");
        System.out.println("Atas nama " + this.nama_customer);
    }
}