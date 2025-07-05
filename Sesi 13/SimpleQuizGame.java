import javax.swing.*; // Untuk komponen GUI seperti JFrame, JTextField, JButton, JLabel
import java.awt.*;    // Untuk pengaturan warna (Color) dan font (Font)
import java.awt.event.ActionEvent; // Untuk menangani event (aksi) dari tombol
import java.awt.event.ActionListener; // Interface yang harus diimplementasikan untuk menangani event
import java.util.Random; // Untuk menghasilkan angka acak

public class SimpleQuizGame extends JFrame implements ActionListener {

    // Deklarasi komponen GUI
    private JTextField num1Field;       // Kotak teks untuk angka pertama (tidak bisa diedit)
    private JTextField num2Field;       // Kotak teks untuk angka kedua (tidak bisa diedit)
    private JTextField answerField;     // Kotak teks untuk jawaban pengguna (bisa diedit)
    private JButton checkButton;        // Tombol "CHECK"
    private JLabel feedbackLabel;       // Label untuk menampilkan pesan feedback
    private Random random;              // Objek untuk menghasilkan angka acak

    private int correctSum;             // Variabel untuk menyimpan jawaban yang benar dari soal saat ini

    public SimpleQuizGame() {
        // Pengaturan dasar jendela (frame)
        setTitle("Game Kuis Penjumlahan Sederhana"); // Judul jendela
        setSize(400, 250);                      // Ukuran jendela (lebar, tinggi)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Aksi ketika tombol close ditekan (keluar aplikasi)
        setLayout(null); // Menggunakan null layout manager untuk pengaturan posisi manual

        random = new Random(); // Inisialisasi objek Random

        // Inisialisasi dan pengaturan komponen JTextField untuk angka pertama (1)
        num1Field = new JTextField();
        num1Field.setBounds(50, 50, 80, 30); // Posisi dan ukuran (x, y, lebar, tinggi)
        num1Field.setEditable(false);       // TIDAK BISA DIEDIT (sesuai permintaan)
        num1Field.setHorizontalAlignment(JTextField.CENTER); // Teks rata tengah
        num1Field.setFont(new Font("Arial", Font.BOLD, 16)); // Pengaturan font
        add(num1Field); // Tambahkan ke frame

        // Inisialisasi dan pengaturan komponen JTextField untuk angka kedua (2)
        num2Field = new JTextField();
        num2Field.setBounds(150, 50, 80, 30); // Posisi dan ukuran
        num2Field.setEditable(false);       // TIDAK BISA DIEDIT (sesuai permintaan)
        num2Field.setHorizontalAlignment(JTextField.CENTER); // Teks rata tengah
        num2Field.setFont(new Font("Arial", Font.BOLD, 16)); // Pengaturan font
        add(num2Field); // Tambahkan ke frame

        // Label '='
        JLabel equalsLabel = new JLabel("=");
        equalsLabel.setBounds(235, 50, 20, 30); // Posisi dan ukuran
        equalsLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Pengaturan font
        add(equalsLabel); // Tambahkan ke frame

        // Inisialisasi dan pengaturan komponen JTextField untuk jawaban pengguna (3)
        answerField = new JTextField();
        answerField.setBounds(260, 50, 80, 30); // Posisi dan ukuran
        answerField.setHorizontalAlignment(JTextField.CENTER); // Teks rata tengah
        answerField.setFont(new Font("Arial", Font.BOLD, 16)); // Pengaturan font
        add(answerField); // Tambahkan ke frame

        // Inisialisasi dan pengaturan tombol "CHECK"
        checkButton = new JButton("CHECK");
        checkButton.setBounds(150, 100, 100, 40); // Posisi dan ukuran
        checkButton.addActionListener(this); // Daftarkan listener untuk tombol
        add(checkButton); // Tambahkan ke frame

        // Inisialisasi dan pengaturan label feedback (4)
        feedbackLabel = new JLabel("Selamat datang! Silakan jawab soalnya.");
        feedbackLabel.setBounds(50, 160, 300, 30); // Posisi dan ukuran
        feedbackLabel.setFont(new Font("Arial", Font.PLAIN, 14)); // Pengaturan font
        add(feedbackLabel); // Tambahkan ke frame

        // Panggil method untuk menghasilkan soal pertama
        generateNewQuestion();

        setVisible(true); // Jadikan jendela terlihat
    }

    // Method untuk menghasilkan soal penjumlahan baru
    private void generateNewQuestion() {
        // Hasilkan dua angka acak antara 1 dan 100
        int num1 = random.nextInt(100) + 1;
        int num2 = random.nextInt(100) + 1;

        // Set teks di JTextField sesuai angka acak
        num1Field.setText(String.valueOf(num1));
        num2Field.setText(String.valueOf(num2));

        // Hitung jawaban yang benar
        correctSum = num1 + num2;

        answerField.setText(""); // Kosongkan kolom jawaban sebelumnya
        feedbackLabel.setText("Silakan jawab..."); // Reset pesan feedback
        getContentPane().setBackground(null); // Reset warna background frame
                                            // (null akan mengembalikan ke warna default sistem)
    }

    // Method yang akan dipanggil ketika ada aksi (misalnya tombol diklik)
    @Override
    public void actionPerformed(ActionEvent e) {
        // Cek apakah aksi berasal dari tombol "checkButton"
        if (e.getSource() == checkButton) {
            try {
                // Ambil jawaban dari pengguna dari answerField dan ubah ke integer
                int userAnswer = Integer.parseInt(answerField.getText());

                // Periksa apakah jawaban pengguna benar
                if (userAnswer == correctSum) {
                    feedbackLabel.setText("Selamat Jawaban Anda Benar!!!"); // Pesan benar
                    getContentPane().setBackground(Color.GREEN); // Ubah warna frame jadi hijau
                    
                    // Gunakan Timer untuk menunda sedikit sebelum soal baru muncul
                    // Ini agar pengguna bisa melihat pesan "Selamat..." dan warna hijau
                    Timer timer = new Timer(1500, new ActionListener() { // 1.5 detik delay
                        @Override
                        public void actionPerformed(ActionEvent arg0) {
                            generateNewQuestion(); // Hasilkan soal baru
                        }
                    });
                    timer.setRepeats(false); // Timer hanya berjalan sekali
                    timer.start(); // Mulai timer
                    
                } else {
                    feedbackLabel.setText("Maaf, Jawaban Anda Salah."); // Pesan salah
                    getContentPane().setBackground(Color.RED); // Ubah warna frame jadi merah
                    answerField.setText(""); // Kosongkan jawaban salah agar pengguna bisa mencoba lagi
                }
            } catch (NumberFormatException ex) {
                // Tangani error jika pengguna memasukkan input non-angka
                feedbackLabel.setText("Error: Masukkan angka yang valid!");
                getContentPane().setBackground(Color.ORANGE); // Beri warna orange untuk error input
            }
        }
    }

    // Method utama (main method) untuk menjalankan aplikasi Swing
    public static void main(String[] args) {
        // Jalankan GUI di Event Dispatch Thread (EDT) untuk keamanan thread
        SwingUtilities.invokeLater(() -> new SimpleQuizGame());
    }
}