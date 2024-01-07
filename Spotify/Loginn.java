import java.util.*;

public class Loginn {
    private HashMap<String, String> users = new HashMap<>();

    public Loginn() {
        admin();
    }

    // Inisialisasi data pengguna (adminname, password)
    private void admin() {
        users.put("zaki", "andafi123");
        users.put("anda", "zaki45");
    }

    // Method untuk melakukan proses login
    public void login() {
        Scanner input = new Scanner(System.in);

        boolean masuk = false;

        while (!masuk) {
            System.out.print("Username: ");
            String username = input.nextLine();

            System.out.print("Password: ");
            String password = input.nextLine();

            masuk = checkCredentials(username, password);

            if (masuk) {
                System.out.println("Login berhasil!\n");
            } else {
                System.out.println("Login gagal. Username atau password salah. Silakan coba lagi.");
            }
        }
    }

    // Method untuk memeriksa kredensial
    private boolean checkCredentials(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }
}

