package gui;
import java.awt.EventQueue;
import dao.HoaDonDAO;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.nio.file.LinkOption;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import connectDB.connectDB;
import controler.bienStatic;
import controler.xuatPDF;
import dao.BanDAO;
import dao.KhachHangDAO;
import dao.SanPhamDAO;
import entity.Ban;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.KichThuoc;
import entity.PhuongThucThanhToan;
import entity.SanPham;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTabbedPane;
import javax.swing.border.EtchedBorder;
import javax.swing.ButtonGroup;
import javax.swing.JSpinner;

public class frmTrangChu extends JFrame implements MouseListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnlMenu;
	JPanel pnlMenuIcon;
	public static JTable tblSanPhamChon;
	private JTextField txtSdt;
	private JTextField txtHoTen;
	public static JTable tblKhuyenMai;
	private JTextField txtTienKhachDua;
	private JTextField txtTienThua;
	private JPanel pnlTrangChu;
	JPanel pnlTienMat ;
	//GUI control 
	private pnlHoaDon pnlHoaDon;
	private pnlKhuyenMai pnlKhuyenMai;
	private pnlSanPham pnlSanPham;
	private pnlKhachHang pnlKhachHang;
	private pnlThongKe pnlThongKe;
	private pnlNhanVien pnlNhanVien;
	private final ButtonGroup groupGioiTinh = new ButtonGroup();
	private JTextField txtMaSPThem;
	//Flag
	private JPanel controlFlag;
	private JButton btnFlag;
	//Button
	JButton btnTrangChu, btnHoaDon, btnKhuyenMai, btnSanPham, btnKhachHang, btnNhanVien, btnThongKe;
	public static ArrayList<SanPham> dsSanPham = new ArrayList<SanPham>();
	public static JLabel lbltxtSoLuongSP, lbltxtPhiVAT,lbltxtTongTien, lblThongBao; 
	private static double tongTien;
	JRadioButton radNam, radNu;
	JLabel lbltxtDiemTichLuy, lbltxtMaKH, lbltxtBanDat;
	public static JLabel lblTenNhanVien, lblThoiGian;
	public static Ban banDat;
	private JComboBox cboThanhToan;
	private Timer timer;
	public static bienStatic bienTemp = new bienStatic();
	/**
	 * Create the frame.
	 */
	public frmTrangChu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        
        setBounds(0, 0, 1515, 790);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(205,201,195,255));
        contentPane.setBounds(0, 0, screenWidth, screenHeight);
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        pnlMenu = new JPanel();
        pnlMenu.setBackground(new Color(0, 102, 102));
        pnlMenu.setBounds(10, 0, 0, 742);
        contentPane.add(pnlMenu);
        pnlMenu.setLayout(null);
        
        JPanel pnlLogo = new JPanel();
        pnlLogo.setBounds(10, 10, 229, 153);
        pnlMenu.add(pnlLogo);
        pnlLogo.setLayout(null);
        
        JButton btnDong = new JButton("");
        btnDong.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dongMenu();
        	}
        });
        btnDong.setIcon(new ImageIcon(frmTrangChu.class.getResource("/icon/iconclose.png")));
        btnDong.setBounds(205, 0, 24, 21);
        pnlLogo.add(btnDong);
        
        JLabel lblLogo = new JLabel("");
        lblLogo.setIcon(new ImageIcon(frmTrangChu.class.getResource("/icon/logoMenu.jpg")));
        lblLogo.setBounds(0, 0, 229, 153);
        pnlLogo.add(lblLogo);
        
        JPanel pnlMenuBar = new JPanel();
        pnlMenuBar.setBorder(null);
        pnlMenuBar.setBounds(0, 173, 246, 507);
        pnlMenu.add(pnlMenuBar);
        pnlMenuBar.setLayout(new GridLayout(7, 1, 0, 0));
        
        controlFlag = new JPanel();
        controlFlag = pnlTrangChu;
       
        
        pnlTrangChu = new JPanel();
        pnlTrangChu.setBounds(10, 58, 1491, 697);
        contentPane.add(pnlTrangChu);
        pnlTrangChu.setLayout(null);
        
        //GUI hóa đơn
        pnlHoaDon = new pnlHoaDon();
        pnlHoaDon.setBounds(10, 58, 1491, 697);
        contentPane.add(pnlHoaDon);
        pnlHoaDon.setVisible(false);
        //GUI Khuyến mãi
        pnlKhuyenMai = new pnlKhuyenMai();
        pnlKhuyenMai.setBounds(10, 58, 1491, 697);
        contentPane.add(pnlKhuyenMai);
        pnlKhuyenMai.setVisible(false);
        //GUI sản phẩm
        pnlSanPham = new pnlSanPham(this);
        pnlSanPham.setBounds(10, 58, 1491, 697);
        contentPane.add(pnlSanPham);
        pnlSanPham.setVisible(false);
        //GUI khách hàng
        pnlKhachHang = new pnlKhachHang(this);
        pnlKhachHang.setBounds(10, 58, 1491, 697);
        contentPane.add(pnlKhachHang);
        pnlKhachHang.setVisible(false);
        //GUI thống kê
        pnlThongKe = new pnlThongKe(this);
        pnlThongKe.setBounds(10, 58, 1491, 697);
        contentPane.add(pnlThongKe);
        pnlThongKe.setVisible(false);
        //GUI nhân viên
        pnlNhanVien = new pnlNhanVien(this);
        pnlNhanVien.setBounds(10, 58, 1491, 697);
        contentPane.add(pnlNhanVien);
        pnlNhanVien.setVisible(false);
        
        ArrayList<SanPham> cafes = loadCafeProducts();
        ArrayList<SanPham> cakes = loadPastryProducts();
        ArrayList<SanPham> drinks = loadDrinkProducts();

        tpKhungSanPham tpKhungSP = new tpKhungSanPham(cafes, cakes, drinks);
        tpKhungSP.setBorder(null);
        tpKhungSP.setBounds(10, 0, 781, 392);
        pnlTrangChu.add(tpKhungSP);
        
        JPanel pnlKhung2 = new JPanel();
        pnlKhung2.setBorder(new TitledBorder(null, "Th\u00F4ng tin s\u1EA3n ph\u1EA9m \u0111\u01B0\u1EE3c ch\u1ECDn", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnlKhung2.setBounds(10, 445, 781, 231);
        pnlTrangChu.setBackground(new Color(205,201,195,255));
        pnlTrangChu.add(pnlKhung2);
        pnlKhung2.setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setToolTipText("");
        scrollPane.setBounds(10, 20, 761, 201);
        pnlKhung2.add(scrollPane);
        
        tblSanPhamChon = new JTable();
        tblSanPhamChon.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tblSanPhamChon.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"M\u00E3 s\u1EA3n ph\u1EA9m", "T\u00EAn s\u1EA3n ph\u1EA9m", "K\u00EDch th\u01B0\u1EDBc", "Gi\u00E1", "Lo\u1EA1i", "S\u1ED1 l\u01B0\u1EE3ng", "Th\u00E0nh ti\u1EC1n"
        	}
        ));
        tblSanPhamChon.getColumnModel().getColumn(1).setPreferredWidth(79);
        tblSanPhamChon.getColumnModel().getColumn(3).setPreferredWidth(60);
        scrollPane.setViewportView(tblSanPhamChon);
        
        JPanel pnlKhung3 = new JPanel();
        pnlKhung3.setBorder(new TitledBorder(null, "Th\u00F4ng tin kh\u00E1ch h\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnlKhung3.setBounds(796, 10, 392, 226);
        pnlTrangChu.add(pnlKhung3);
        pnlKhung3.setLayout(null);
        
        JLabel lblSdt = new JLabel("Số điện thoại:");
        lblSdt.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblSdt.setBounds(10, 22, 111, 23);
        pnlKhung3.add(lblSdt);
        
        txtSdt = new JTextField();
        txtSdt.setBounds(117, 20, 190, 23);
        pnlKhung3.add(txtSdt);
        txtSdt.setColumns(10);
        
        JButton btnTim = new JButton("Tìm");
        btnTim.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(txtSdt.getText().trim().equals("")) {
        			JOptionPane.showMessageDialog(null, "Vui lòng nhập số điện thoại!");
        		}else if(!txtSdt.getText().trim().matches("^(0)[0-9]{9}$")){
        			JOptionPane.showMessageDialog(null, "Số điện thoại không đúng!");
        		}else {
        			KhachHangDAO khDAO = new KhachHangDAO();
        			KhachHang kh = khDAO.selectBySdt(txtSdt.getText().trim());
        			if(kh != null) {
        				txtHoTen.setText(kh.getHoTen());
        				if (kh.isGioiTinh()) {
                            radNam.setSelected(true); 
                        } else {
                            radNu.setSelected(true);
                        }
        				lbltxtDiemTichLuy.setText(kh.getDiemTichLuy() + "");
        				lbltxtMaKH.setText(kh.getMaKhachHang());
        			}else {
        				JOptionPane.showMessageDialog(null, "Khách hàng mới! Vui lòng nhập đầy đủ thông tin.");
        				txtHoTen.setText("");
        				txtHoTen.requestFocus();
        				groupGioiTinh.clearSelection();
        				txtHoTen.requestFocus();
        			}
        		}
        	}
        });
        btnTim.setBounds(314, 22, 63, 21);
        pnlKhung3.add(btnTim);
        
        JLabel lblHoTen = new JLabel("Họ tên:");
        lblHoTen.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblHoTen.setBounds(10, 66, 96, 23);
        pnlKhung3.add(lblHoTen);
        
        txtHoTen = new JTextField();
        txtHoTen.setColumns(10);
        txtHoTen.setBounds(117, 68, 190, 23);
        pnlKhung3.add(txtHoTen);
        
        lblThongBao = new JLabel("");
        lblThongBao.setForeground(new Color(255, 0, 0));
        lblThongBao.setBounds(46, 45, 261, 13);
        pnlKhung3.add(lblThongBao);
        
        JLabel lblGioiTinh = new JLabel("Giới tính:");
        lblGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblGioiTinh.setBounds(10, 106, 75, 23);
        pnlKhung3.add(lblGioiTinh);
        
        radNam = new JRadioButton("Nam");
        groupGioiTinh.add(radNam);
        radNam.setBounds(117, 109, 51, 21);
        pnlKhung3.add(radNam);
        
        radNu = new JRadioButton("Nữ");
        groupGioiTinh.add(radNu);
        radNu.setBounds(185, 109, 51, 21);
        pnlKhung3.add(radNu);
        
        JLabel lblDiemTichLuy = new JLabel("Điểm tích lũy:");
        lblDiemTichLuy.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblDiemTichLuy.setBounds(10, 139, 111, 23);
        pnlKhung3.add(lblDiemTichLuy);
        
        lbltxtDiemTichLuy = new JLabel("0");
        lbltxtDiemTichLuy.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lbltxtDiemTichLuy.setBounds(123, 144, 63, 13);
        pnlKhung3.add(lbltxtDiemTichLuy);
        
        JLabel lblMaKH = new JLabel("Mã KH:");
        lblMaKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblMaKH.setBounds(10, 172, 51, 23);
        pnlKhung3.add(lblMaKH);
        
        lbltxtMaKH = new JLabel("---");
        lbltxtMaKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lbltxtMaKH.setBounds(117, 177, 45, 13);
        pnlKhung3.add(lbltxtMaKH);
        
        JPanel pnlThanhToan = new JPanel();
        pnlThanhToan.setBorder(new TitledBorder(null, "Th\u00F4ng tin thanh to\u00E1n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnlThanhToan.setBounds(796, 462, 685, 225);
        pnlTrangChu.add(pnlThanhToan);
        pnlThanhToan.setLayout(null);
        
        JLabel lblSoLuongSP = new JLabel("Số lượng sản phẩm:");
        lblSoLuongSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblSoLuongSP.setBounds(10, 22, 150, 23);
        pnlThanhToan.add(lblSoLuongSP);
        
        JLabel lblPhiVAT = new JLabel("Phí VAT:");
        lblPhiVAT.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblPhiVAT.setBounds(10, 55, 150, 23);
        pnlThanhToan.add(lblPhiVAT);
        
        JLabel lblTongTien = new JLabel("Tổng tiền:");
        lblTongTien.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblTongTien.setBounds(10, 99, 91, 23);
        pnlThanhToan.setBackground(new Color(122, 159, 149));
        pnlThanhToan.add(lblTongTien);
        
        JLabel lblHinhThucThanhToan = new JLabel("Hình thức thanh toán:");
        lblHinhThucThanhToan.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblHinhThucThanhToan.setBounds(10, 143, 150, 23);
        pnlThanhToan.add(lblHinhThucThanhToan);
        
        lbltxtSoLuongSP = new JLabel("---");
        lbltxtSoLuongSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lbltxtSoLuongSP.setBounds(190, 22, 114, 23);
        pnlThanhToan.add(lbltxtSoLuongSP);
        
        lbltxtPhiVAT = new JLabel("---");
        lbltxtPhiVAT.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lbltxtPhiVAT.setBounds(190, 55, 130, 23);
        pnlThanhToan.add(lbltxtPhiVAT);
        
        lbltxtTongTien = new JLabel("---");
        lbltxtTongTien.setForeground(new Color(255, 0, 0));
        lbltxtTongTien.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lbltxtTongTien.setBounds(188, 97, 150, 23);
        pnlThanhToan.add(lbltxtTongTien);
        cboThanhToan = new JComboBox();
        cboThanhToan.setModel(new DefaultComboBoxModel(new String[] {"Chuyển khoản", "Tiền mặt"}));
        cboThanhToan.setBounds(170, 146, 150, 31);
        pnlThanhToan.add(cboThanhToan);
        
        pnlTienMat = new JPanel();
        cboThanhToan.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String phuongThucThanhToan = (String) cboThanhToan.getSelectedItem();
        		if(phuongThucThanhToan.equals("Chuyển khoản")) {
        			pnlTienMat.setVisible(false);
        		}else {
        			pnlTienMat.setVisible(true);
        		}
        	}
        });
        pnlTienMat.setBounds(348, 21, 327, 131);
        pnlThanhToan.add(pnlTienMat);
        pnlTienMat.setVisible(false);
        pnlTienMat.setLayout(null);
        
        JLabel lblTienKhachDua = new JLabel("Tiền khách đưa:");
        lblTienKhachDua.setBounds(10, 10, 116, 19);
        lblTienKhachDua.setFont(new Font("Tahoma", Font.PLAIN, 15));
        pnlTienMat.add(lblTienKhachDua);
        
        txtTienKhachDua = new JTextField();
        txtTienKhachDua.setColumns(10);
        txtTienKhachDua.setBounds(124, 10, 88, 23);
        pnlTienMat.add(txtTienKhachDua);
        
        JButton btn30 = new JButton("30");
        btn30.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		txtTienKhachDua.setText(30 + "");
        		updateTienThua();
        	}
        });
        btn30.setBackground(new Color(255, 255, 255));
        btn30.setBounds(104, 39, 63, 21);
        pnlTienMat.add(btn30);
        
        JButton btn50 = new JButton("50");
        btn50.setBackground(new Color(240, 240, 240));
        btn50.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		txtTienKhachDua.setText(50 + "");
        		updateTienThua();
        	}
        });
        btn50.setBounds(177, 40, 63, 21);
        pnlTienMat.add(btn50);
        
        JButton btn100 = new JButton("100");
        btn100.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		txtTienKhachDua.setText(100 + "");
        		updateTienThua();
        	}
        });
        btn100.setBackground(new Color(240, 240, 240));
        btn100.setBounds(251, 40, 63, 21);
        pnlTienMat.add(btn100);
        
        JButton btn200 = new JButton("200");
        btn200.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		txtTienKhachDua.setText(200 + "");
        		updateTienThua();
        	}
        });
        btn200.setBackground(new Color(240, 240, 240));
        btn200.setBounds(104, 63, 63, 21);
        pnlTienMat.add(btn200);
        
        JButton btn300 = new JButton("300");
        btn300.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		txtTienKhachDua.setText(300 + "");
        		updateTienThua();
        	}
        });
        btn300.setBackground(new Color(240, 240, 240));
        btn300.setBounds(177, 63, 63, 21);
        pnlTienMat.add(btn300);
        
        JButton btn500 = new JButton("500");
        btn500.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		txtTienKhachDua.setText(500 + "");
        		updateTienThua();
        	}
        });
        btn500.setBackground(new Color(240, 240, 240));
        btn500.setBounds(251, 63, 63, 21);
        pnlTienMat.add(btn500);
        
        JLabel lblTienThua = new JLabel("Tiền thừa:");
        lblTienThua.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblTienThua.setBounds(10, 102, 98, 19);
        pnlTienMat.add(lblTienThua);
        
        txtTienThua = new JTextField();
        txtTienThua.setEditable(false);
        txtTienThua.setColumns(10);
        txtTienThua.setBounds(124, 97, 190, 23);
        pnlTienMat.add(txtTienThua);
        
        JLabel lblBoSung = new JLabel("000 VNĐ");
        lblBoSung.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblBoSung.setBounds(217, 16, 74, 13);
        pnlTienMat.add(lblBoSung);
        
        JButton btnThanhToan = new JButton("THANH TOÁN");
        btnThanhToan.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(checkTT()) {
        			try {
        					int option = JOptionPane.YES_OPTION;
        		            if(banDat == null) {
        		            	option = JOptionPane.showConfirmDialog(
                		                null, 
                		                "Chưa chọn bàn, đây là khách hàng vãng lai, bạn vẫn muốn tiếp tục?", 
                		                "Xác nhận", 
                		                JOptionPane.YES_NO_OPTION,
                		                JOptionPane.QUESTION_MESSAGE
                		            );
        		            }
        		            if (option == JOptionPane.YES_OPTION) {
                    			KhachHang khachHang = KhachHangDAO.getInstance().selectBySdt(txtSdt.getText());
                    			//tạo mã tự động
                    			if(khachHang == null) {
                    				KhachHangDAO khoanDAO= new KhachHangDAO();
               		             
                		            String maKhachHang =khoanDAO.getInstance().getNextMaKhachHang();
                			    	String sdt = txtSdt.getText();
                			    	String hoTen= txtHoTen.getText();
                			    	Boolean gioiTinh= radNam.isSelected();
     
                			    	LocalDateTime ngayThem= LocalDateTime.now();
                			    	
                			    	khachHang= new KhachHang(maKhachHang, hoTen, gioiTinh, sdt, ngayThem);
                			    	KhachHangDAO.getInstance().add(khachHang);
                    			}
                    			String pttt = cboThanhToan.getSelectedItem().toString();
                    			HoaDon hoaDon = new HoaDon(HoaDonDAO.getInstance().taoMaHD(),bienTemp.nhanVienLogin,LocalDateTime.now() , true , "",banDat , pttt.equals("Chuyển khoản")?PhuongThucThanhToan.BANK_TRANSFER:PhuongThucThanhToan.CASH, khachHang);
                    			hoaDon.setDsCTHD(tpKhungSanPham.temp);
                    			hoaDon.setBan(banDat);
                    			if(banDat != null) {
                    				BanDAO.getInstance().setTinhTrang(banDat, false);
                    			}
                    			HoaDonDAO.getInstance().add(hoaDon);
                    			bienStatic.doanhThuTemp += tongTien;
                    			tongTien = 0;
                    			tpKhungSanPham.temp = new ArrayList<ChiTietHoaDon>();
                    			frmTrangChu.updateTable(frmTrangChu.tblSanPhamChon, tpKhungSanPham.temp);
                            	frmTrangChu.updateTableKM(frmTrangChu.tblKhuyenMai, tpKhungSanPham.temp);
                            	frmTrangChu.updateTTThanhToan(tpKhungSanPham.temp);
                            	txtTienKhachDua.setText("");
                            	txtTienThua.setText("");
                            	//Reset TT
                            	int response = JOptionPane.showConfirmDialog(null, "Bạn có muốn xuất hoá đơn không?", "Xác nhận",
                                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                                if (response == JOptionPane.YES_OPTION) {
                                	new xuatPDF().xuatPDF(hoaDon);
                                } else if (response == JOptionPane.NO_OPTION) {
                                }
                            	txtSdt.setText("");
                            	txtHoTen.setText("");
                            	lbltxtDiemTichLuy.setText("0");
                            	lbltxtMaKH.setText("---");
                            	groupGioiTinh.clearSelection();
    		            }
					} catch (Exception e2) {
						e2.printStackTrace();
					}
        		}
        	}
        });
        btnThanhToan.setBackground(new Color(255, 255, 255));
        btnThanhToan.setBounds(488, 161, 187, 41);
        pnlThanhToan.add(btnThanhToan);
        
        JPanel pnlBanTrong = new JPanel();
        pnlBanTrong.setBorder(new TitledBorder(null, "T\u00ECnh tr\u1EA1ng b\u00E0n tr\u1ED1ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnlBanTrong.setBounds(1191, 10, 278, 442);
        pnlTrangChu.add(pnlBanTrong);
        pnlBanTrong.setLayout(null);
        
        JPanel pnlKhung = new JPanel();
        pnlKhung.setBounds(10, 40, 258, 333);
        pnlBanTrong.add(pnlKhung);
        pnlKhung.setLayout(new GridLayout(6, 3, 0, 0));
        BanDAO banDAO = new BanDAO();
        if(bienStatic.bans.size() == 0) {
        	bienStatic.bans = banDAO.getAll();
        	Collections.sort(bienStatic.bans, new Comparator<Ban>() {
                @Override
                public int compare(Ban b1, Ban b2) {
                    int num1 = Integer.parseInt(b1.getMaBan());
                    int num2 = Integer.parseInt(b2.getMaBan());
                    return Integer.compare(num1, num2);
                }
            });
        }
        for (Ban b : bienStatic.bans) {
        	pnlKhung.add(taoBtnBan(b));
        }
        JLabel lblHuongDan = new JLabel("Xanh: đang trống");
        lblHuongDan.setBounds(20, 383, 152, 13);
        pnlBanTrong.add(lblHuongDan);
        
        lbltxtBanDat = new JLabel("Đã đặt bàn: ");
        lbltxtBanDat.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lbltxtBanDat.setBounds(20, 406, 248, 26);
        pnlBanTrong.add(lbltxtBanDat);
        
        JLabel lblHuongDan_1 = new JLabel("(*)Nhấn giữ để đặt lại tình trạng bàn");
        lblHuongDan_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblHuongDan_1.setBounds(9, 17, 259, 20);
        pnlBanTrong.add(lblHuongDan_1);
        
        JPanel pnlKhuyenMaiApDung = new JPanel();
        pnlKhuyenMaiApDung.setBorder(new TitledBorder(null, "Khuy\u1EBFn m\u00E3i \u0111\u00E3 \u00E1p d\u1EE5ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnlKhuyenMaiApDung.setBounds(796, 238, 392, 214);
        pnlTrangChu.add(pnlKhuyenMaiApDung);
        pnlKhuyenMaiApDung.setLayout(null);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(10, 20, 372, 184);
        pnlKhuyenMaiApDung.add(scrollPane_1);
        
        tblKhuyenMai = new JTable();
        tblKhuyenMai.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tblKhuyenMai.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"M\u00E3 Khuy\u1EBFn m\u00E3i", "M\u00E3 SP", "Gi\u00E1 g\u1ED1c", "Gi\u00E1 khuy\u1EBFn m\u00E3i"
        	}
        ));
        tblKhuyenMai.getColumnModel().getColumn(0).setPreferredWidth(81);
        tblKhuyenMai.getColumnModel().getColumn(1).setPreferredWidth(55);
        tblKhuyenMai.getColumnModel().getColumn(1).setMinWidth(22);
        tblKhuyenMai.getColumnModel().getColumn(3).setPreferredWidth(82);
        scrollPane_1.setViewportView(tblKhuyenMai);
        
        JPanel pnlKhungL = new JPanel();
        pnlKhungL.setBackground(new Color(122, 159, 149));
        pnlKhungL.setBounds(0, 0, 10, 697);
        pnlTrangChu.add(pnlKhungL);
        
        JPanel pnlKhungB = new JPanel();
        pnlKhungB.setBackground(new Color(122, 159, 149));
        pnlKhungB.setBounds(9, 685, 1481, 12);
        pnlTrangChu.add(pnlKhungB);
        
        JPanel pnlKhungR = new JPanel();
        pnlKhungR.setBounds(1479, -52, 16, 733);
        pnlTrangChu.add(pnlKhungR);
        pnlKhungR.setBackground(new Color(122, 159, 149));
        
        JPanel pnlNav = new JPanel();
        pnlNav.setBackground(new Color(122, 159, 149));
        pnlNav.setBounds(10, 0, 1491, 59);
        contentPane.add(pnlNav);
        pnlNav.setLayout(null);
        
        lblTenNhanVien = new JLabel();
        lblTenNhanVien.setText(bienStatic.name + " - " +(bienStatic.role==true?"Nhân viên quản lý": "Nhân viên bán hàng"));
        lblTenNhanVien.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTenNhanVien.setBounds(198, 10, 525, 42);
        pnlNav.add(lblTenNhanVien);
        
        lblThoiGian = new JLabel();
        capNhatThoiGian();
        lblThoiGian.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblThoiGian.setBounds(941, 7, 189, 42);
        pnlNav.add(lblThoiGian);
        
        JButton btnDangXuat = new JButton("Thay ca & Đăng xuất");
        btnDangXuat.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dlgThayCa dlgThayCa = new dlgThayCa(frmTrangChu.this);
        		dlgThayCa.setLocationRelativeTo(frmTrangChu.this);
        		dlgThayCa.setVisible(true);
        	}
        });
        btnDangXuat.setBounds(1313, 12, 156, 36);
        pnlNav.add(btnDangXuat);
        
        
        JButton btnDoiMatKhau = new JButton("Đổi mật khẩu");
        btnDoiMatKhau.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JDialog dlgThayDoiMatKhau =  new dlgThayDoiMatKhau(frmTrangChu.this);
        		dlgThayDoiMatKhau.setLocationRelativeTo(frmTrangChu.this);
        		dlgThayDoiMatKhau.setVisible(true);
        	}
        });
        btnDoiMatKhau.setBounds(1173, 10, 119, 36);
        pnlNav.add(btnDoiMatKhau);
        pnlMenuIcon = new JPanel();
        pnlMenuIcon.setBorder(null);
        pnlMenuIcon.setBounds(0, 0, 197, 57);
        pnlNav.add(pnlMenuIcon);
        pnlMenuIcon.setBackground(new Color(122, 159, 149));
        pnlMenuIcon.setLayout(null);
        
        JButton btnMoMenu = new JButton("MENU");
        btnMoMenu.setVerticalAlignment(SwingConstants.BOTTOM);
        btnMoMenu.setFont(new Font("Segoe UI", Font.BOLD, 25));
        btnMoMenu.setBounds(0, 0, 180, 53);
        pnlMenuIcon.add(btnMoMenu);
        btnMoMenu.setBackground(new Color(122, 159, 149));
        btnMoMenu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		moMenuBar();
        	}
        });
        btnMoMenu.setIcon(new ImageIcon(frmTrangChu.class.getResource("/icon/iconmenu.png")));
	
        controlFlag = pnlTrangChu;
        
        JPanel pnlThemNhanh = new JPanel();
        pnlThemNhanh.setBorder(new TitledBorder(null, "Th\u00EAm nhanh", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnlThemNhanh.setBounds(10, 391, 781, 54);
        pnlTrangChu.add(pnlThemNhanh);
        pnlThemNhanh.setLayout(null);
        
        JLabel lblNhap = new JLabel("Nhập mã sản phẩm:");
        lblNhap.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblNhap.setBounds(10, 23, 175, 21);
        pnlThemNhanh.add(lblNhap);
        
        txtMaSPThem = new JTextField();
        txtMaSPThem.setBounds(154, 23, 143, 23);
        pnlThemNhanh.add(txtMaSPThem);
        txtMaSPThem.setColumns(10);
        
        JButton btnThem = new JButton("Thêm");
        btnThem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {

                    if (txtMaSPThem.getText().trim().equals("")) {
                        throw new IllegalArgumentException("Lỗi: Mã thêm rỗng!");
                    }
                    SanPham sp = SanPhamDAO.getInstance().getById(txtMaSPThem.getText());
                    if (sp == null) {
                        throw new IllegalArgumentException("Sản phẩm không tồn tại! Vui lòng kiểm tra lại");
                    }
                    String input = JOptionPane.showInputDialog(null, "Vui lòng nhập số lượng sản phẩm:", "Nhập số lượng", JOptionPane.QUESTION_MESSAGE);
                    int sl = 0;
                    if (input == null || input.trim().isEmpty()) {
                        throw new IllegalArgumentException("Vui lòng nhập số lượng.");
                    }

                    sl = Integer.parseInt(input);

                    if (sl <= 0) {
                        throw new IllegalArgumentException("Số lượng phải lớn hơn 0.");
                    }

                    String size = "D";
                    if (sp.getLoaiSanPham().isCoPhanLoai()) {
                        String[] sizes = {"S", "M", "L"};
                        size = (String) JOptionPane.showInputDialog(
                            null,
                            "Chọn kích thước sản phẩm:",
                            "Chọn kích thước",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            sizes,
                            sizes[0]
                        );
                    }
                    Boolean f = false;
                    ChiTietHoaDon cthdt = new ChiTietHoaDon(sp, sl, KichThuoc.valueOf(size));
                    for (ChiTietHoaDon cthd: tpKhungSanPham.temp) {
						if(cthd.getSanPham().getMaSanPham().equals(cthdt.getSanPham().getMaSanPham()) && cthd.getKt().name().equals(cthdt.getKt().name())) {
							tpKhungSanPham.temp.get(tpKhungSanPham.temp.indexOf(cthd)).setSoLuong(cthd.getSoLuong() + cthdt.getSoLuong());
							f = true;
						}
					}
                    if(f == false) {
                    	tpKhungSanPham.temp.add(cthdt);
                    }
                    System.out.println(new ChiTietHoaDon(sp, sl, KichThuoc.valueOf(size)));
                    updateTable(tblSanPhamChon, tpKhungSanPham.temp);
                    updateTableKM(tblKhuyenMai, tpKhungSanPham.temp);
                    updateTTThanhToan(tpKhungSanPham.temp);
                    txtMaSPThem.setText("");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Giá trị nhập vào không hợp lệ. Vui lòng nhập một số nguyên.", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnThem.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btnThem.setBounds(307, 23, 67, 24);
        pnlThemNhanh.add(btnThem);
        
        JButton btnXoa = new JButton("Xóa");
        btnXoa.setBounds(704, 20, 67, 24);
        pnlThemNhanh.add(btnXoa);
        
        JLabel lblHuongDan1 = new JLabel("(*) Nháy đúp để thêm sản phẩm");
        lblHuongDan1.setBounds(431, 10, 267, 21);
        pnlThemNhanh.add(lblHuongDan1);
        lblHuongDan1.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnTrangChu = new JButton("Trang chủ");
        btnFlag = btnTrangChu;
        btnTrangChu.setIcon(new ImageIcon(frmTrangChu.class.getResource("/icon/iconHome.png")));
        btnTrangChu.setFont(new Font("Segoe UI", Font.BOLD, 25));
        btnTrangChu.setForeground(new Color(255, 255, 255));
        btnTrangChu.setBackground(new Color(151, 181, 173));
        pnlMenuBar.add(btnTrangChu);
        
        btnHoaDon = new JButton("Hóa Đơn");
        btnHoaDon.setIcon(new ImageIcon(frmTrangChu.class.getResource("/icon/iconHoaDon.png")));
        btnHoaDon.setFont(new Font("Segoe UI", Font.BOLD, 25));
        btnHoaDon.setForeground(new Color(255, 255, 255));
        btnHoaDon.setBackground(new Color(0, 102, 102));
        pnlMenuBar.add(btnHoaDon);
        
        btnKhuyenMai = new JButton("Khuyến mãi");
        btnKhuyenMai.setIcon(new ImageIcon(frmTrangChu.class.getResource("/icon/iconKhuyenMai.png")));
        btnKhuyenMai.setFont(new Font("Segoe UI", Font.BOLD, 25));
        btnKhuyenMai.setForeground(new Color(255, 255, 255));
        btnKhuyenMai.setBackground(new Color(0, 102, 102));
        pnlMenuBar.add(btnKhuyenMai);
        
        btnSanPham = new JButton("Sản phẩm");
        btnSanPham.setIcon(new ImageIcon(frmTrangChu.class.getResource("/icon/iconSanPham.png")));
        btnSanPham.setForeground(new Color(255, 255, 255));
        btnSanPham.setFont(new Font("Segoe UI", Font.BOLD, 25));
        btnSanPham.setBackground(new Color(0, 102, 102));
        pnlMenuBar.add(btnSanPham);
        
        btnKhachHang = new JButton("Khách hàng");
        btnKhachHang.setIcon(new ImageIcon(frmTrangChu.class.getResource("/icon/iconKhachHang.png")));
        btnKhachHang.setFont(new Font("Segoe UI", Font.BOLD, 25));
        btnKhachHang.setForeground(new Color(255, 255, 255));
        btnKhachHang.setBackground(new Color(0, 102, 102));
        pnlMenuBar.add(btnKhachHang);
        
        btnNhanVien = new JButton("Nhân viên");
        btnNhanVien.setIcon(new ImageIcon(frmTrangChu.class.getResource("/icon/iconNhanVien.png")));
        btnNhanVien.setFont(new Font("Segoe UI", Font.BOLD, 25));
        btnNhanVien.setForeground(new Color(255, 255, 255));
        btnNhanVien.setBackground(new Color(0, 102, 102));
        pnlMenuBar.add(btnNhanVien);
        
        btnThongKe = new JButton("Thống kê");
        btnThongKe.setIcon(new ImageIcon(frmTrangChu.class.getResource("/icon/iconThongKe.png")));
        btnThongKe.setFont(new Font("Segoe UI", Font.BOLD, 25));
        btnThongKe.setForeground(new Color(255, 255, 255));
        btnThongKe.setBackground(new Color(0, 102, 102));
        pnlMenuBar.add(btnThongKe);
        addMouseListener(this); 
        getRootPane().setDefaultButton(btnThanhToan);
        txtSdt.addKeyListener(new KeyListener() {
          @Override
          public void keyTyped(KeyEvent e) {}

          @Override
          public void keyPressed(KeyEvent e) {
              if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                  btnTim.doClick();
              }
          }

          @Override
          public void keyReleased(KeyEvent e) {}
      });
        btnTrangChu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		controlFlag.setVisible(false);
        		btnFlag.setBackground(new Color(0, 102, 102));
        		pnlTrangChu.setVisible(true);
        		controlFlag = pnlTrangChu;
        		btnFlag = btnTrangChu;
        		btnFlag.setBackground(new Color(151, 181, 173));
        		dongMenu();
        	}
        });
        btnHoaDon.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		controlFlag.setVisible(false);
        		btnFlag.setBackground(new Color(0, 102, 102));
        		pnlHoaDon.setVisible(true);
        		controlFlag = pnlHoaDon;
        		btnFlag = btnHoaDon;
        		btnFlag.setBackground(new Color(151, 181, 173));
        		dongMenu();
        	}
        });
        btnKhuyenMai.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		controlFlag.setVisible(false);
        		btnFlag.setBackground(new Color(0, 102, 102));
        		pnlKhuyenMai.setVisible(true);
        		controlFlag = pnlKhuyenMai;
        		btnFlag = btnKhuyenMai;
        		btnFlag.setBackground(new Color(151, 181, 173));
        		dongMenu();
        	}
        });
        btnSanPham.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		controlFlag.setVisible(false);
        		btnFlag.setBackground(new Color(0, 102, 102));
        		pnlSanPham.setVisible(true);
        		controlFlag = pnlSanPham;
        		btnFlag = btnSanPham;
        		btnFlag.setBackground(new Color(151, 181, 173));
        		dongMenu();
        	}
        });
        btnKhachHang.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		controlFlag.setVisible(false);
        		btnFlag.setBackground(new Color(0, 102, 102));
        		pnlKhachHang.setVisible(true);
        		controlFlag = pnlKhachHang;
        		btnFlag = btnKhachHang;
        		btnFlag.setBackground(new Color(151, 181, 173));
        		dongMenu();
        	}
        });
        btnNhanVien.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(bienStatic.role) {
        			controlFlag.setVisible(false);
            		btnFlag.setBackground(new Color(0, 102, 102));
            		pnlNhanVien.setVisible(true);
            		controlFlag = pnlNhanVien;
            		btnFlag = btnNhanVien;
            		btnFlag.setBackground(new Color(151, 181, 173));
            		dongMenu();
        		}else {
        			JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập vào chức năng này!");
        		}
        	}
        	
        });
        btnThongKe.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		controlFlag.setVisible(false);
        		btnFlag.setBackground(new Color(0, 102, 102));
        		pnlThongKe.setVisible(true);
        		controlFlag = pnlThongKe;
        		btnFlag = btnThongKe;
        		btnFlag.setBackground(new Color(151, 181, 173));
        		dongMenu();
        	}
        });
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tblSanPhamChon.getSelectedRow();
                DefaultTableModel model = (DefaultTableModel) tblSanPhamChon.getModel();
//                model.setRowCount(0);
                if (selectedRow >= 0) {
                    model.removeRow(selectedRow); 
                    tpKhungSP.temp.remove(selectedRow); 
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng cần xóa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
                updateTTThanhToan(tpKhungSanPham.temp);
            }
        });
        txtTienKhachDua.addKeyListener(new KeyListener() {
			
            @Override
            public void keyPressed(KeyEvent e) {
            	NumberFormat fm = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
                if(!txtTienKhachDua.getText().trim().equals("")) {
                	txtTienThua.setText(fm.format(Double.parseDouble(txtTienKhachDua.getText())* 1000 - tongTien) + "");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            	NumberFormat fm = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
                if(!txtTienKhachDua.getText().trim().equals("")) {
                	txtTienThua.setText(fm.format(Double.parseDouble(txtTienKhachDua.getText())* 1000 - tongTien) + "");
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            	NumberFormat fm = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
                if(!txtTienKhachDua.getText().trim().equals("")) {
                	txtTienThua.setText(fm.format(Double.parseDouble(txtTienKhachDua.getText())* 1000 - tongTien) + "");
                }
            }

            private void onKeyAction(KeyEvent e) {
            	NumberFormat fm = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
                if(!txtTienKhachDua.getText().trim().equals("")) {
                	txtTienThua.setText(fm.format(Double.parseDouble(txtTienKhachDua.getText())* 1000 - tongTien) + "");
                }
            }
        });
        txtMaSPThem.addKeyListener(new KeyListener()  {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
              	  btnThem.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });
	}
    private void capNhatThoiGian() {
    	timer = new Timer(); 
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                    String tg = sdf.format(new Date());
                    lblThoiGian.setText(tg); 
                });
            }
        }, 0, 1000);
    }
	/*
	 * Width: 248
	 * height: 808
	 */
	void moMenuBar() {
		new Thread(new Runnable(){
			public void run() {
				pnlMenuIcon.setVisible(false);
				for(int i =0; i < 248; i++) {
					pnlMenu.setSize(i, 808);
					try {
						Thread.sleep(0, 1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	void dongMenu() {
		new Thread(new Runnable() {
			public void run() {
				pnlMenuIcon.setVisible(true);
				for(int i =248; i > 0; i--) {
					pnlMenu.setSize(i, 808);
//					try {
//						Thread.sleep(0, 1);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
				}
			}
		}).start();
	}
	private void updateTienThua() {
		NumberFormat fm = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        if(!txtTienKhachDua.getText().trim().equals("")) {
        	txtTienThua.setText(fm.format(Double.parseDouble(txtTienKhachDua.getText())* 1000 - tongTien) + "");
        }
	}
	 private JPanel addProduct(String name, String imagePath) {
	        JPanel productPanel = new JPanel();
	        productPanel.setLayout(new BorderLayout());

	        JLabel productImage = new JLabel(new ImageIcon(imagePath)); 
	        JLabel productName = new JLabel(name, SwingConstants.CENTER); 

	        productPanel.add(productImage, BorderLayout.CENTER);
	        productPanel.add(productName, BorderLayout.SOUTH);

	        return productPanel;  
	    }
	@Override
	public void mouseClicked(MouseEvent e) {
		 Point clickPoint = e.getPoint();
         if (!pnlMenu.getBounds().contains(clickPoint)) {
        	 dongMenu();
         }
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static ArrayList<SanPham> loadCafeProducts() {
        SanPhamDAO sanPhamDAO = new SanPhamDAO();
        return sanPhamDAO.selectCafe(); 
    }

    public static ArrayList<SanPham> loadPastryProducts() {
        SanPhamDAO sanPhamDAO = new SanPhamDAO();
        return sanPhamDAO.selectCake(); 
    }

    public static ArrayList<SanPham> loadDrinkProducts() {
        SanPhamDAO sanPhamDAO = new SanPhamDAO();
        return sanPhamDAO.selectOrther(); 
    }
    public static void themSanPhamMua(SanPham sanPham) {
    	dsSanPham.add(sanPham);
    	JOptionPane.showMessageDialog(null, "Đã thêm!");
    }
    
    //Xử lý tranh chính
    public static void updateTable(JTable table, ArrayList<ChiTietHoaDon> chiTietHoaDons) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (ChiTietHoaDon chiTietHoaDon : chiTietHoaDons) {
        	System.out.println(chiTietHoaDon);
            String maSanPham = chiTietHoaDon.getSanPham().getMaSanPham();
            String tenSanPham = chiTietHoaDon.getSanPham().getTenSanPham();
            String kichThuoc = chiTietHoaDon.getKt().name(); 
            Double gia = chiTietHoaDon.getGiaBan(); //getKichThuocGia().values().iterator().next()
//            if(chiTietHoaDon.getSanPham().getKhuyenMai() != null && chiTietHoaDon.getSanPham().getKhuyenMai().isTrangThai())
//            	gia = gia - (gia * chiTietHoaDon.getSanPham().getKhuyenMai().getPhanTramGiamGia()/100);
            String loaiSanPham = chiTietHoaDon.getSanPham().getLoaiSanPham().getTenloai(); 
            int soLuong = chiTietHoaDon.getSoLuong();
            double thanhTien = gia * soLuong;
            model.addRow(new Object[] { maSanPham, tenSanPham, kichThuoc, gia, loaiSanPham, soLuong, thanhTien });
        }
    }
    //Khuyen mai
    public static void updateTableKM(JTable table, ArrayList<ChiTietHoaDon> chiTietHoaDons) {
    	DefaultTableModel model = (DefaultTableModel) table.getModel();
    	model.setRowCount(0);
    	for (ChiTietHoaDon chiTietHoaDon : chiTietHoaDons) {
    		if(chiTietHoaDon.getSanPham().getKhuyenMai() != null && chiTietHoaDon.getSanPham().getKhuyenMai().isTrangThai()) {
    			String maKM = chiTietHoaDon.getSanPham().getKhuyenMai().getMaKhuyenMai();
    			String maSanPham = chiTietHoaDon.getSanPham().getMaSanPham();
                Double gia = chiTietHoaDon.getSanPham().getKichThuocGia().values().iterator().next(); 
                Double giaKM = gia - (gia * chiTietHoaDon.getSanPham().getKhuyenMai().getPhanTramGiamGia()/100);
                model.addRow(new Object[] { maKM, maSanPham, gia, giaKM});
    		} 
        }
    }
    //Thong tin khách hàng
    //Check thông tin khách hàng
    public boolean checkTT() {
    	if(tpKhungSanPham.getCTHD().size() == 0) {
    		JOptionPane.showMessageDialog(null, "Chưa có sản phẩm nào!");
    		return false;
    	}
    	if(txtSdt.getText().trim().equals("")) {
    		JOptionPane.showMessageDialog(null, "Vui lòng nhập số điện thoại khách hàng");
    		txtSdt.requestFocus();
    		return false;
    	}else {
    		if(!txtSdt.getText().trim().matches("^(0)[0-9]{9}$")) {
        		JOptionPane.showMessageDialog(null, "Số điện thoại không đúng");
        		txtSdt.requestFocus();
        		return false;
        	}
    	}
    	if(txtHoTen.getText().trim().equals("")) {
    		JOptionPane.showMessageDialog(null, "Vui lòng nhập họ tên khách hàng!");
    		txtHoTen.requestFocus();
    		return false;
    	}
    	if(!radNam.isSelected() && !radNu.isSelected()) {
    		JOptionPane.showMessageDialog(null, "Vui lòng chọn giới tính!");
    		return false;
    	}
    	String pttt = (String) cboThanhToan.getSelectedItem();
    	if(!pttt.equals("Chuyển khoản")) {
    		try {
    			if(txtTienKhachDua.getText().equals("")) {
        			JOptionPane.showMessageDialog(null, "Vui lòng chọn hoặc nhập tiền khách đưa!");
        			txtTienKhachDua.requestFocus();
        			return false;
        		}
    			Double tkd = Double.parseDouble(txtTienKhachDua.getText());
        		if(tkd*1000 - tongTien < 0) {
        			JOptionPane.showMessageDialog(null, "Không đủ tiền thanh toán!");
        			txtTienKhachDua.requestFocus();
        			return false;
        		}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(pnlTienMat, "Tiền phải là số!");
				txtTienKhachDua.requestFocus();
    			return false;
			}
    	}
    	return true;
    }
    //Thông tin thanh toán
    public static void updateTTThanhToan(ArrayList<ChiTietHoaDon> chiTietHoaDons) {
    	int soLuong = chiTietHoaDons.size();
    	double phiVAT = 0;
    	for (ChiTietHoaDon chiTietHoaDon : chiTietHoaDons) {
			phiVAT += chiTietHoaDon.tinhVAT();
		}
    	tongTien = phiVAT;
    	for (ChiTietHoaDon chiTietHoaDon : chiTietHoaDons) {
//			if(chiTietHoaDon.getSanPham().getKhuyenMai() != null && chiTietHoaDon.getSanPham().getKhuyenMai().isTrangThai()) {
//				tongTien += chiTietHoaDon.getSanPham().getKichThuocGia().values().iterator().next() - (chiTietHoaDon.getGiaBan());
//			}else {
//				tongTien += chiTietHoaDon.getSanPham().getKichThuocGia().values().iterator().next();
//			}
    		tongTien += chiTietHoaDon.getGiaBan() * chiTietHoaDon.getSoLuong();
		}
    	NumberFormat fm = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
    	lbltxtSoLuongSP.setText(soLuong + "");
    	lbltxtPhiVAT.setText(fm.format(phiVAT));
    	lbltxtTongTien.setText(fm.format(tongTien));
    }
    private JButton taoBtnBan(Ban ban) {
    	JButton btnBan = new JButton(ban.getMaBan());
    	if(ban.isTinhTrang()) {
    		btnBan.setBackground(new Color(128, 255, 128));
    	}else {
    		btnBan.setBackground(new Color(255, 53, 53));
    	}
    	final long holdThreshold = 200; 
        final long[] pressTime = new long[1];

        btnBan.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                pressTime[0] = System.currentTimeMillis(); 
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                long releaseTime = System.currentTimeMillis(); 
                long duration = releaseTime - pressTime[0]; 

                if (duration > holdThreshold) { 
                    if (!ban.isTinhTrang()) {
                        ban.setTinhTrang(true);
                        BanDAO.getInstance().setTinhTrang(ban, true);
                        JOptionPane.showMessageDialog(btnBan, "Đã cập nhật trạng thái!");
                        btnBan.setBackground(new Color(128, 255, 128));
                    }
                } else { 
                    if (!ban.isTinhTrang()) {
                        JOptionPane.showMessageDialog(btnBan, "Bàn đang bận!");
                    } else {
                        ban.setTinhTrang(false);
                        JOptionPane.showMessageDialog(btnBan, "Đã đặt bàn!");
                        btnBan.setBackground(new Color(255, 53, 53));
                        lbltxtBanDat.setText("Đã đặt bàn: " + ban.getMaBan() + "!");
                        banDat = ban;
                    }
                }
            }
        });
    	return btnBan;
    }

}
