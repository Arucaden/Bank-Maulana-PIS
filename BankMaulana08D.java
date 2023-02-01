import java.util.Scanner;

public class BankMaulana08D  {
    static Scanner scan = new Scanner(System.in); // Scanner String
    static Scanner sc = new Scanner(System.in); // Scanner Int
    static String[][] dataPegawai = { { "Daffa Maulana, S.Tr.Kom.", "Rifqi Chandra, S.Tr.Ds.", "Vania Jovi, S.Tr.Ak.", "Adam Ahmaddin, S.Tr.M.", "Ariffin Ilham, S.I.Kom.", "Maulani, M.E." },
                                      { "123456789", "234567890", "345678901", "456789012", "567890123", "678901234" } };
    static String[][] biodataPegawai = { { "Laki-laki", "Laki-laki", "Perempuan", "Laki-laki", "Laki-laki", "Perempuan" },
                                         { "19", "19", "19", "20", "18", "23" },
                                         { "Pasuruan, 29-07-2003", "Lampung, 17-11-2003", "Pasuruan, 13-11-2002", "Pasuruan, 12-03-2003", "Pasuruan, 28-07-2004", "Malang, 01-09-1999" },
                                         { "Sarjana Terapan", "Sarjana Terapan", "Sarjana Terapan", "Sarjana Terapan", "Sarjana", "Magister" },
                                         { "0838457434645", "0858477434644", "0866657433625", "0818322444963", "0818787414350", "0825797464691" },
                                         { "Pasuruan", "Pasuruan", "Pasuruan", "Pasuruan", "Pasuruan", "Malang" },
                                         { "IT Manager", "OB", "Financial Analyst", "Staff Manager", "Sales", "Branch Manager" } };
    static int[] dataGaji = { 8000000, 1500000, 8000000, 4000000, 3000000, 19500000, 6000000, 2000000 };
    static String[] laporan = { "(-) Satria, S.Kom.\nNIK\t: 112233445\njabatan\t: IT Manager", "(^) Daffa Maulana, S.Tr.Kom.\nNIK\t: 123456789\njabatan\t: IT Manager" };

    // login===============================================================================================
    static void login08D() {
        String uname, password;
        boolean menu = true, submenu = true;
        while (menu == true) {
            System.out.print("Username : ");
            uname = scan.nextLine();
            if (uname.equals("admin")) {
                while (submenu == true) {
                    System.out.print("Password : ");
                    password = scan.nextLine();
                    if (password.equals("admin")) {
                        menu = submenu = false;
                        System.out.println("Login Sukses !");
                    } 
                    else {
                        System.out.println("Maaf username / password salah, silahkan coba lagi!\n");
                    }
                }
            } 
            else {
                System.out.println("Maaf username tidak ditemukan!\n");
            }
        }
    }

    // Data Semua Pegawai===============================================================================================
    static void semuaDataPegawai08D() {
        //perhitungan spasi agar tulisan rapi
        int max = 0;
        String[] spasi = new String[dataPegawai[0].length];
        int[] jumlahCharNIK = new int[dataPegawai[0].length];
        //memasukkan jumlah char pada String setiap i ke array jumlahCharNIK
        for (int i = 0; i < dataPegawai[0].length; i++) {
            jumlahCharNIK[i] = dataPegawai[0][i].length();
        }
        //Mencari jumlah char terbanyak
        for (int i = 0; i < jumlahCharNIK.length; i++) {
            if (max < jumlahCharNIK[i]) {
                max = jumlahCharNIK[i];
            }
        }
        //Perhitungan spasi
        for (int i = 0; i < dataPegawai[0].length; i++) {
            spasi[i] = "        ";  //spasi fixed
            for (int j = 0; j < (max - jumlahCharNIK[i]); j++) {
                spasi[i] += " "; //Penentuan sisa spasi
            }
        }

        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("\tNama\t\t\t   NIK\t\t\t     JABATAN");
        System.out.println("-------------------------------------------------------------------------------");
        for (int i = 0; i < biodataPegawai[0].length; i++) {
            if (biodataPegawai[0][i].equals("")) {
                break;
            }
            System.out.printf("%s" + spasi[i] + "%s" + "\t\t" + "%s", dataPegawai[0][i], dataPegawai[1][i],
                    biodataPegawai[6][i]);
            System.out.println();
        }
        //Assign ke laporan
        laporan08D("(A) ", "List Pegawai");
    }

    // Perhitungan Gaji===============================================================================================
    static int perhitunganGaji08D(boolean cetakSlip) {
        int jabatan, lembur, alpha;
        char PNS;
        String namaPegawai = null, printJabatan = null, NIK = null;
        boolean menuGaji = true;

        while (menuGaji) {
            System.out.print("Masukkan NIK karyawan : ");
            NIK = scan.nextLine();
            // pencarian berdasarkan NIK
            for (int i = 0; i < dataPegawai[0].length; i++) {
                if (NIK.equals(dataPegawai[1][i])) {
                    namaPegawai = dataPegawai[0][i];
                    NIK.equals(i);
                    System.out.println("\n" + namaPegawai);
                    menuGaji = false;
                    break;
                }
            }
            if (menuGaji) {
                System.out.println("Maaf NIK salah !");
            }
        }

        System.out.println("Pilihan Jabatan :\n1. IT Manager\t\t4. Staff Manager\n2. OB \t\t\t5. Sales\n3. Financial Analyst\t6. Branch Manager");
        System.out.print("Jabatan karyawan : ");
        jabatan = sc.nextInt();
        System.out.print("Masukkan jumlah lembur dalam sebulan : ");
        lembur = sc.nextInt();
        System.out.print("Apakah karyawan PNS ? (y/n) : ");
        PNS = scan.next().toUpperCase().charAt(0);
        System.out.print("Berapa kali karyawan tidak masuk kerja ? (di luar cuti) : ");
        alpha = sc.nextInt();

        int rateLembur = 200000 * lembur, ratePNS = 3000000, rateAlpha = 50000 * alpha;
        double pajak = 0.05, gaji = 0, gajiAkhir = 0, gajiPokok = 0;
        // Pencarian gaji pokok berdasarkan jabatan
        for (int i = 0; i < dataGaji.length; i++) {
            if ((jabatan - 1) == i) {
                gajiPokok = gaji = dataGaji[i]; // gaji pokok untuk slip gaji
                printJabatan = biodataPegawai[6][i]; // print jabatan untuk slip gaji
                break;
            }
        }
        double tunjangan = ((360 - alpha) / 360 * 400000) + (0.1024 * gaji);
        // PNS
        if (PNS == 'Y') {
            gaji += (rateLembur + ratePNS - rateAlpha + tunjangan);
            gajiAkhir = gaji - (gaji * pajak);
        } 
        else {
            gaji += (rateLembur - rateAlpha + tunjangan);
            gajiAkhir = gaji - (gaji * pajak);
            ratePNS = 0;
        }
        // Cetak Slip
        if (cetakSlip == false) {
            System.out.print("Cetak Slip Gaji Karyawan ? (y/n) : ");
            char confirm = scan.next().toUpperCase().charAt(0);
            scan.nextLine();
            if (confirm == 'Y') {
                SlipGaji08D(namaPegawai, printJabatan, rateLembur, ratePNS, rateAlpha, pajak, tunjangan, gajiPokok,
                        gajiAkhir, NIK);
            } 
            else {
                System.out.printf("Gaji perbulan adalah : Rp%,d\n", (int) gajiAkhir);
            }
        } 
        else {
            SlipGaji08D(namaPegawai, printJabatan, rateLembur, ratePNS, rateAlpha, pajak, tunjangan, gajiPokok,
                    gajiAkhir, NIK);
        }
        //Assign ke Laporan
        String data2 = "Nama : " + namaPegawai + "\n" + "NIK : " + NIK;
        laporan08D("(PG) ", data2);
        return (int) gajiAkhir;
    }

    // Print Slip Gaji===============================================================================================
    static void SlipGaji08D(String namaPegawai, String printJabatan, int rateLembur, int ratePNS, int rateAlpha, double pajak, double tunjangan, double gajiPokok, double gajiAkhir, String NIK) {

        System.out.println();
        System.out.println("=========================================================");
        System.out.println("                      Bank Maulana                       ");
        System.out.println("=========================================================");
        System.out.println("                        SLIP GAJI                        ");
        System.out.println(" Nama\t : " + namaPegawai);
        System.out.println(" Jabatan : " + printJabatan);
        System.out.println(" NIK\t : " + NIK);
        System.out.println("------------------------------------");
        System.out.printf(" Gaji pokok\t: %,d\n", (int) gajiPokok);
        System.out.printf(" Tunjangan\t: %,d\n", (int) tunjangan);
        System.out.println(" Pajak\t\t: -0.05%");
        System.out.printf(" Potongan\t: -%,d\n", rateAlpha);
        System.out.printf(" Total Gaji\t: Rp%,d\n", (int) gajiAkhir);
        System.out.println("=========================================================");

        //Assign ke laporan
        String data2 = "Nama : " + namaPegawai + "\n" + "NIK : " + NIK;
        laporan08D("(CSG) ", data2);
    }

    // Penambahan Pegawai Baru===============================================================================================
    static void pegawaiBaru08D() {
        String[][] dataCopy = new String[dataPegawai.length][dataPegawai[0].length];
        String[][] biodataCopy = new String[biodataPegawai.length][biodataPegawai[0].length];
        int[] gajiCopy = new int[dataGaji.length];
        //Copy Data Array
        for (int i = 0; i < dataPegawai.length; i++) {
            for (int j = 0; j < dataPegawai[i].length; j++) {
                dataCopy[i][j] = dataPegawai[i][j];
                gajiCopy[j] = dataGaji[j];
            }
        }
        for (int i = 0; i < biodataPegawai.length; i++) {
            for (int j = 0; j < biodataPegawai[i].length; j++) {
                biodataCopy[i][j] = biodataPegawai[i][j];
            }
        }
        //Inisialisasi ulang + 1
        dataPegawai = new String[dataPegawai.length][dataPegawai[0].length + 1];
        biodataPegawai = new String[biodataPegawai.length][biodataPegawai[0].length + 1];
        dataGaji = new int[dataGaji.length + 1];
        //copy data array
        for (int i = 0; i < dataCopy.length; i++) {
            for (int j = 0; j < dataCopy[i].length; j++) {
                dataPegawai[i][j] = dataCopy[i][j];
                dataGaji[j] = gajiCopy[j];
            }
        }

        for (int i = 0; i < biodataCopy.length; i++) {
            for (int j = 0; j < biodataCopy[i].length; j++) {
                biodataPegawai[i][j] = biodataCopy[i][j];
            }
        }

        System.out.print("Masukkan Nama : ");
        dataPegawai[0][dataPegawai[0].length - 1] = scan.nextLine();

        boolean menuNIK = true, error = false;
        while(menuNIK) {
            System.out.print("Masukkan 9 Digit NIK : ");
            String NIK = scan.nextLine();

            for (int i = 0; i < dataPegawai[1].length; i++) {
                error = false;
                if (NIK.equals(dataPegawai[1][i])) {
                    error = true;
                    break;
                }
            }
            if (error == false && NIK.length() == 9) {
                dataPegawai[1][dataPegawai[0].length - 1] = NIK;
                menuNIK = false;
            }
            else {
                System.out.println("Maaf NIK tidak valid !\n");
            }   
        }
        
        System.out.println("Pilihan Jabatan :\n1. IT Manager\t\t4. Staff Manager\n2. OB \t\t\t5. Sales\n3. Financial Analyst\t6. Branch Manager");
        System.out.print("Masukkan Jabatan : ");
        String[] angka = {"1", "2", "3", "4", "5", "6"}; //untuk jabatan
        biodataPegawai[6][biodataPegawai[0].length - 1] = scan.nextLine();
        for (int i = 0; i < biodataPegawai[0].length; i++) { // pencocokan jabatan dengan user input (angka)
            if (biodataPegawai[6][biodataPegawai[0].length - 1].equals(angka[i])) {
                biodataPegawai[6][biodataPegawai[0].length - 1] = biodataPegawai[6][i];
                break;
            }
        }

        System.out.print("Masukkan Jenis Kelamin : ");
        biodataPegawai[0][biodataPegawai[0].length - 1] = scan.nextLine();
        System.out.print("Masukkan Umur : ");
        biodataPegawai[1][biodataPegawai[0].length - 1] = scan.nextLine();
        System.out.print("Masukkan Tanggal Lahir : ");
        biodataPegawai[2][biodataPegawai[0].length - 1] = scan.nextLine();
        System.out.print("Masukkan Pendidikan Terakhir : ");
        biodataPegawai[3][biodataPegawai[0].length - 1] = scan.nextLine();
        System.out.print("Masukkan No. Telephone : ");
        biodataPegawai[4][biodataPegawai[0].length - 1] = scan.nextLine();
        System.out.print("Masukkan Domisili : ");
        biodataPegawai[5][biodataPegawai[0].length - 1] = scan.nextLine();

        //Assign ke laporan
        String data2 = dataPegawai[0][dataPegawai[0].length - 1] + " \nNIK\t: " + dataPegawai[1][dataPegawai[0].length - 1] + "\nJabatan\t: " + biodataPegawai[6][biodataPegawai[0].length - 1];
        laporan08D("(+) ", data2);
    }

    //Copy Array Data Laporan===============================================================================================
    static void laporan08D(String data1, String data2) {
        String[] laporanCopy = new String[laporan.length];
        // Copy
        for (int i = 0; i < laporan.length; i++) {
            laporanCopy[i] = laporan[i];
        }
        // Copy + tambah length
        laporan = new String[laporan.length + 1];
        for (int i = 0; i < laporanCopy.length; i++) {
            laporan[i] = laporanCopy[i];
        }
        // pengisian data baru
        laporan[laporan.length - 1] = data1 + data2;
    }

    // Cetak Laporan===============================================================================================
    static void cetakLaporan08D() {
        for (int i = 0; i < laporan.length; i++) {
            System.out.println(laporan[i] + "\n");
        }
        System.out.println("---------------------------------------------------------------");
        System.out.println("*Ket :\t(+) : Pegawai Baru\t\t(-) : Pegawai Keluar\n\t(^) : Pegawai Naik Jabatan\t(CSG) : Cetak Slip Gaji\n\t(A) : Akses Data\t\t(PG) : Perhitungan Gaji");
    }

    // Menunjukkan Biodata Pegawai===============================================================================================
    static void dataPegawai08D() {
        String[] biodata = { "Jenis Kelamin\t\t: ", "Umur\t\t\t: ", "TTL\t\t\t: ", "Pendidikan Terakhir\t: ", "No. Telp\t\t: ",
                             "Domisili\t\t: ", "Posisi \t\t\t: " };
        String nama = null;
        String NIK = null;
        boolean menu = true, isDataVoid = true;
        while (menu == true) {
            System.out.print("Silahkan Masukkan NIK : ");
            NIK = scan.nextLine();
            for (int i = 0; i < dataPegawai[1].length; i++) {
                if (NIK.equals(dataPegawai[1][i])) {
                    System.out.println("-------------------------------------------");
                    nama = dataPegawai[0][i];
                    System.out.println(nama);
                    System.out.println("NIK\t\t\t: " + NIK);
                    for (int j = 0; j < biodata.length; j++) {
                        System.out.print(biodata[j]);
                        System.out.println(biodataPegawai[j][i]);
                        isDataVoid = false; // Pembatas
                        menu = false;
                    }
                }
                if (!NIK.equals(dataPegawai[1][i]) && i >= 8 && isDataVoid == true) {
                    System.out.println("Maaf NIK tidak ditemukan !\n");
                }
            }
        }
        //Assign ke laporan
        String data2 = "\nNama : " + nama + "\nNIK : " + NIK;
        laporan08D("(A) Biodata", data2);
    }

    //mainmainmainmainmainmainmainmainmainmainmainmainmainmainmainmainmainmainmainmainmainmainmainmainmainmainmainmainmainmainmainmainmainmainmain
    public static void main(String[] args) {
        System.out.println("===========================================================");
        System.out.println("SELAMAT DATANG DI SISTEM INFORMASI KEPEGAWAIAN BANK MAULANA");
        System.out.println("===========================================================");

        // login
        System.out.println("Silahkan Login Terlebih Dahulu !");
        login08D();

        boolean menuUtama = true, menuAkhir = true;

        while (menuUtama == true) {
            menuAkhir = true;
            System.out.println("\n\t\t    Main Menu");
            System.out.println("--------------------------------------------------");
            System.out.println("1. List Pegawai\t\t\t5. Laporan\n2. Perhitungan gaji pegawai\t6. Biodata Pegawai\n3. Cetak slip gaji pegawai\t7. Keluar \n4. Tambah Pegawai Baru");
            System.out.print("Pilihan menu : ");
            int menu = sc.nextInt();

            switch (menu) {
                // Data Semua Pegawai
                case 1:
                    System.out.println("\nList Pegawai Bank Maulana");
                    semuaDataPegawai08D();
                    break;

                // Perhitungan Gaji
                case 2:
                    System.out.println("\nPerhitungan Gaji Pegawai");
                    System.out.println("--------------------------------");
                    perhitunganGaji08D(false); //false agar bisa memilih cetak slip gaji
                    break;

                // Cetak Slip Gaji
                case 3:
                    System.out.println("\nCetak Slip Gaji Karyawan");
                    perhitunganGaji08D(true); // true agar langsung cetak gaji
                    break;

                // Tambah Pegawai
                case 4:
                    System.out.println("\nPenambahan Data Pegawai Baru");
                    System.out.println("--------------------------------");
                    pegawaiBaru08D();
                    break;

                // Laporan
                case 5:
                    System.out.println("\nLaporan\n");
                    cetakLaporan08D();
                    break;

                // Lihat Data Pegawai
                case 6:
                    System.out.println("\nBiodata Pegawai");
                    dataPegawai08D();
                    break;

                // Keluar
                default:
                    menuUtama = menuAkhir = false;
                    System.out.println("\n===========================================================");
                    System.out.println("\t\t\tLogged Out");
                    System.out.println("===========================================================");
                    break;
            }

            // Kembali / keluar dari program
            while (menuAkhir) {
                System.out.print("\nApakah ingin kembali ke menu utama ?(y/n) : ");
                char kembali = scan.next().toUpperCase().charAt(0);
                scan.nextLine();
                if (kembali == 'Y') {
                    menuAkhir = false;
                }
                if (kembali == 'N') {
                    menuUtama = menuAkhir = false;
                    System.out.println("\n===========================================================");
                    System.out.println("\t\t\tLogged Out");
                    System.out.println("===========================================================");
                    break;
                }
                if (kembali != 'Y') {
                    System.out.println("Maaf kode yang diinputkan salah!");
                }
            }
        }
    }
}