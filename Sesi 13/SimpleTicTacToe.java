import javax.swing.*; // Untuk komponen GUI seperti JFrame, JButton
import java.awt.*;    // Untuk pengaturan layout (GridLayout) dan warna (Color)
import java.awt.event.ActionEvent; // Untuk menangani event (aksi)
import java.awt.event.ActionListener; // Interface untuk menangani aksi

public class SimpleTicTacToe extends JFrame implements ActionListener {

    private JButton[] buttons; // Array untuk menyimpan 9 tombol papan Tic-Tac-Toe
    private boolean isXTurn;   // Variabel boolean untuk melacak giliran (true = X, false = O)
    private JPanel boardPanel; // Panel untuk menampung tombol-tombol papan

    public SimpleTicTacToe() {
        // Pengaturan dasar jendela (frame)
        setTitle("Tic-Tac-Toe Sederhana"); // Judul jendela
        setSize(300, 300);              // Ukuran jendela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Aksi ketika tombol close ditekan
        setResizable(false); // Tidak bisa diubah ukurannya

        // Inisialisasi panel untuk papan permainan
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3, 5, 5)); // GridLayout 3x3 dengan jarak antar komponen 5 piksel
        boardPanel.setBackground(Color.DARK_GRAY); // Warna background panel papan

        buttons = new JButton[9]; // Inisialisasi array untuk 9 tombol
        isXTurn = true;           // X mulai duluan

        // Buat dan tambahkan tombol-tombol ke panel
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(new Font("Arial", Font.BOLD, 60)); // Ukuran font besar untuk X/O
            buttons[i].setFocusPainted(false); // Hilangkan border fokus saat diklik
            buttons[i].setBackground(Color.GRAY); // Warna background tombol
            buttons[i].addActionListener(this); // Daftarkan listener untuk setiap tombol
            boardPanel.add(buttons[i]); // Tambahkan tombol ke panel papan
        }

        // Tambahkan panel papan ke frame
        add(boardPanel, BorderLayout.CENTER);

        setVisible(true); // Jadikan jendela terlihat
    }

    // Method yang akan dipanggil ketika sebuah tombol diklik
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource(); // Dapatkan tombol yang diklik

        // Periksa apakah tombol yang diklik masih kosong (belum diisi X atau O)
        if (clickedButton.getText().equals("")) {
            if (isXTurn) {
                clickedButton.setText("X"); // Set teks tombol menjadi "X"
                clickedButton.setForeground(Color.WHITE); // Warna teks putih
            } else {
                clickedButton.setText("O"); // Set teks tombol menjadi "O"
                clickedButton.setForeground(Color.RED); // Warna teks merah
            }
            isXTurn = !isXTurn; // Ganti giliran (jika X turn, jadi O turn, begitu juga sebaliknya)
        }
        // Jika tombol sudah terisi, tidak lakukan apa-apa
    }

    // Method utama (main method) untuk menjalankan aplikasi Swing
    public static void main(String[] args) {
        // Jalankan GUI di Event Dispatch Thread (EDT) untuk keamanan thread
        SwingUtilities.invokeLater(() -> new SimpleTicTacToe());
    }
}