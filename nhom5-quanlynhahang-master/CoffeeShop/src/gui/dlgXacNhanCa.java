package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

import controler.bienStatic;
import dao.CaLamDAO;
import dao.NhanVienDAO;
import entity.CaLam;
import entity.NhanVien;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.awt.event.ActionEvent;

/*
 * Chưa chạy được
 * 
 * 
 * */

public class dlgXacNhanCa extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	/**
	 * Create the dialog.
	 */
	public dlgXacNhanCa(JFrame frmDangNhap) {
		super(frmDangNhap, true);
		CaLamDAO caLamDAO = new CaLamDAO();
		ArrayList<CaLam> caLams = caLamDAO.getAll();
		CaLam caLam = new CaLam();
		for (CaLam caLam1 : caLams) {
			if(!caLam1.getThoiGianBatDau().isAfter(LocalTime.now()) && !caLam1.getThoiGianketThuc().isBefore(LocalTime.now())) {
				caLam = caLam1;
			}
		}
		bienStatic.caHienTai = caLam.getMaCa();
		NhanVienDAO nhanVienDAO = new NhanVienDAO();
		NhanVien nv = nhanVienDAO.selectByTK(bienStatic.maTKLogin);

		bienStatic.name = nv.getHoTen();
		setBounds(100, 100, 629, 516);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblTieuDe = new JLabel("Xác nhận ca");
			lblTieuDe.setForeground(new Color(0, 122, 122));
			lblTieuDe.setFont(new Font("Segoe UI", Font.BOLD, 35));
			lblTieuDe.setBounds(155, 10, 226, 47);
			contentPanel.add(lblTieuDe);
		}
		{
			JLabel lblMaNV = new JLabel("Mã tài khoản:");
			lblMaNV.setFont(new Font("Segoe UI", Font.PLAIN, 20));
			lblMaNV.setBounds(26, 77, 131, 26);
			contentPanel.add(lblMaNV);
		}
		{
			JLabel lbltxtMaNV = new JLabel("");
			lbltxtMaNV.setText(nv.getMaNhanVien());
			lbltxtMaNV.setFont(new Font("Segoe UI", Font.PLAIN, 20));
			lbltxtMaNV.setBounds(260, 77, 105, 26);
			contentPanel.add(lbltxtMaNV);
		}
		{
			JLabel lblTenNV = new JLabel("Tên nhân viên:");
			lblTenNV.setFont(new Font("Segoe UI", Font.PLAIN, 20));
			lblTenNV.setBounds(26, 130, 131, 26);
		}
		{
			JLabel lbltxtTenNV = new JLabel("");
			lbltxtTenNV.setText(nv.getHoTen());
			lbltxtTenNV.setFont(new Font("Segoe UI", Font.PLAIN, 20));
			lbltxtTenNV.setBounds(260, 130, 345, 26);
			contentPanel.add(lbltxtTenNV);
		}
		{
			JLabel lblMaCa = new JLabel("Mã ca:");
			lblMaCa.setFont(new Font("Segoe UI", Font.PLAIN, 20));
			lblMaCa.setBounds(26, 183, 131, 26);
			contentPanel.add(lblMaCa);
		}
		{
			JLabel lbltxtMaCa = new JLabel("");
			lbltxtMaCa.setText(caLam.getMaCa());
			lbltxtMaCa.setFont(new Font("Segoe UI", Font.PLAIN, 20));
			lbltxtMaCa.setBounds(260, 183, 105, 26);
			contentPanel.add(lbltxtMaCa);
		}
		{
			JLabel lblThoiGianBatDau = new JLabel("Thời gian bắt đầu ca:");
			lblThoiGianBatDau.setFont(new Font("Segoe UI", Font.PLAIN, 20));
			lblThoiGianBatDau.setBounds(26, 235, 202, 26);
			contentPanel.add(lblThoiGianBatDau);
		}
		{
			JLabel lbltxtThoiGianBatDau = new JLabel("");
			DateTimeFormatter fmt = DateTimeFormatter.ofPattern("hh:mm a");
			lbltxtThoiGianBatDau.setText(caLam.getThoiGianBatDau().format(fmt));
			lbltxtThoiGianBatDau.setFont(new Font("Segoe UI", Font.PLAIN, 20));
			lbltxtThoiGianBatDau.setBounds(260, 235, 105, 26);
			contentPanel.add(lbltxtThoiGianBatDau);
		}
		{
			JLabel lblTienTaiQuay = new JLabel("Tiền tại quầy:");
			lblTienTaiQuay.setFont(new Font("Segoe UI", Font.PLAIN, 25));
			lblTienTaiQuay.setBounds(26, 295, 176, 26);
			contentPanel.add(lblTienTaiQuay);
		}
		{
			JLabel lbltxtTienTaiQuay = new JLabel("");
			NumberFormat fm = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
			lbltxtTienTaiQuay.setText(fm.format(bienStatic.tienTaiQuay));
			lbltxtTienTaiQuay.setForeground(new Color(0, 0, 0));
			lbltxtTienTaiQuay.setFont(new Font("Segoe UI", Font.PLAIN, 25));
			lbltxtTienTaiQuay.setBounds(260, 295, 241, 26);
			contentPanel.add(lbltxtTienTaiQuay);
		}
		{
			JLabel lblHuongDan = new JLabel("(*)Nhân viên vui lòng kiểm tra tiền tại quầy trước khi xác nhận");
			lblHuongDan.setFont(new Font("Segoe UI", Font.PLAIN, 18));
			lblHuongDan.setBounds(10, 444, 510, 25);
			contentPanel.add(lblHuongDan);
		}
		
		JButton btnXacNhan = new JButton("Xác nhận");
		btnXacNhan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	            JWindow loadingWindow = bienStatic.createLoadingWindow("Đang tải dữ liệu...");
	            loadingWindow.setVisible(true);
	            loadingWindow.setAlwaysOnTop(true);
	            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                    	dlgXacNhanCa.this.dispose();
                    	bienStatic.frmTrangChu = new frmTrangChu();
        				bienStatic.thoiGianBatDau = LocalDateTime.now();
                        return null;
                    }

                    @Override
                    protected void done() {
                    	loadingWindow.dispose();            	
                    	bienStatic.frmTrangChu.setVisible(true);
                    }
                };
                worker.execute();		        
				}
		});
		btnXacNhan.setBackground(new Color(255, 255, 255));
		btnXacNhan.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnXacNhan.setBounds(227, 368, 131, 35);
		contentPanel.add(btnXacNhan);
		
		JButton btnHuy = new JButton("Hủy");
		btnHuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dlgXacNhanCa.this.dispose();
				frmDangNhap.setVisible(true);
			}
		});
		btnHuy.setBackground(new Color(255, 255, 255));
		btnHuy.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnHuy.setBounds(389, 368, 131, 35);
		contentPanel.add(btnHuy);
		{
			JLabel lblTenNV = new JLabel("Tên nhân viên:");
			lblTenNV.setFont(new Font("Segoe UI", Font.PLAIN, 20));
			lblTenNV.setBounds(26, 130, 131, 26);
			contentPanel.add(lblTenNV);
		}
		getRootPane().setDefaultButton(btnXacNhan);
	}

}
