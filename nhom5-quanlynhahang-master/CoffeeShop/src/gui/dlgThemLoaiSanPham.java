package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.LoaiSanPhamDAO;
import entity.LoaiSanPham;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class dlgThemLoaiSanPham extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField txtTenLoai;
	private JTextArea areaMoTa;
	private JCheckBox chkPhanKichThuoc;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			dlgThemLoaiSanPham dialog = new dlgThemLoaiSanPham(new JFrame());
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public dlgThemLoaiSanPham(JFrame frmTrangChu) {
		super(frmTrangChu, true);
		setBounds(100, 100, 544, 466);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblTieuDe = new JLabel("Thông tin loại sản phẩm");
			lblTieuDe.setFont(new Font("Segoe UI", Font.BOLD, 20));
			lblTieuDe.setForeground(new Color(0, 102, 102));
			lblTieuDe.setBounds(10, 10, 278, 50);
			contentPanel.add(lblTieuDe);
		}
		{
			textField = new JTextField();
			textField.setEditable(false);
			textField.setBounds(140, 77, 312, 29);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			JLabel lblLoai = new JLabel("Mã loại:");
			lblLoai.setForeground(new Color(0, 0, 0));
			lblLoai.setFont(new Font("Segoe UI", Font.BOLD, 15));
			lblLoai.setBounds(42, 70, 88, 36);
			contentPanel.add(lblLoai);
		}
		{
			JLabel lblTenLoai = new JLabel("Tên loại:");
			lblTenLoai.setForeground(Color.BLACK);
			lblTenLoai.setFont(new Font("Segoe UI", Font.BOLD, 15));
			lblTenLoai.setBounds(42, 126, 88, 36);
			contentPanel.add(lblTenLoai);
		}
		{
			txtTenLoai = new JTextField();
			txtTenLoai.setColumns(10);
			txtTenLoai.setBounds(140, 133, 312, 29);
			contentPanel.add(txtTenLoai);
		}
		{
			JLabel lblMT = new JLabel("Mô tả:");
			lblMT.setForeground(Color.BLACK);
			lblMT.setFont(new Font("Segoe UI", Font.BOLD, 15));
			lblMT.setBounds(42, 181, 88, 36);
			contentPanel.add(lblMT);
		}
		
		areaMoTa = new JTextArea();
		areaMoTa.setBounds(140, 190, 312, 101);
		contentPanel.add(areaMoTa);
		
		chkPhanKichThuoc = new JCheckBox("Sản phẩm có phân chia kích thước");
		chkPhanKichThuoc.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		chkPhanKichThuoc.setBounds(42, 304, 327, 21);
		contentPanel.add(chkPhanKichThuoc);
		
		JButton btnThem = new JButton("Thêm");
		btnThem.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnThem.setBounds(223, 369, 109, 36);
		contentPanel.add(btnThem);
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkData()) {
					LoaiSanPhamDAO.getInstance().add(convertToObject());
					JOptionPane.showMessageDialog(null, "Đã thêm loại sản phẩm!");
					pnlSanPham.updateCboLoaiSP();
					dlgThemLoaiSanPham.this.dispose();
				}
			}
		});
		JButton btnHuy = new JButton("Hủy");
		btnHuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dlgThemLoaiSanPham.this.dispose();
			}
		});
		btnHuy.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnHuy.setBounds(354, 369, 109, 36);
		contentPanel.add(btnHuy);
		
		JButton btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setBounds(356, 326, 98, 21);
		contentPanel.add(btnLamMoi);
	}
	private LoaiSanPham convertToObject() {
		String tenL = txtTenLoai.getText();
		String moTa = areaMoTa.getText().trim().equals("")?"":areaMoTa.getText();
		String maL = LoaiSanPhamDAO.getInstance().taoMaLoaiSP();
		boolean phanLoai = chkPhanKichThuoc.isSelected();
		return new LoaiSanPham(maL, tenL, moTa, phanLoai);
		
	}
	private boolean checkData() {
		String tenL = txtTenLoai.getText().trim();
		if(tenL.equals("")) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập loại sản phẩm!");
			return false;
		}
		return true;
	}
}
