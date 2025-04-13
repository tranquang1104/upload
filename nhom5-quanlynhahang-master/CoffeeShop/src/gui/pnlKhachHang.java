package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

import dao.HoaDonDAO;
import dao.KhachHangDAO;
import dao.KhuyenMaiDAO;
import entity.HoaDon;
import entity.KhachHang;
import entity.KhuyenMai;
import entity.PhuongThucThanhToan;

public class pnlKhachHang extends JPanel implements ActionListener, MouseListener{

	private static final long serialVersionUID = 1L;
	private JTable tblKhachHang;
	private JTextField txtmaTim;
	private JFrame frmTrangChu;
	private DefaultTableModel dataModel;
	private KhachHangDAO khachHangDAO;
	private JButton btnCapNhat;
	private AbstractButton btnThem;
	private JButton btnTim;
	private JButton btnLoc;
	private JDateChooser dcTuNgay;
	private JDateChooser dcDenNgay;
	private dlgThongTinKhachHang dlgKhachHang;
	private KhachHang kh;
	/**
	 * Create the frame.
	 */

	public pnlKhachHang(JFrame frmTrangChu) {
		this.frmTrangChu = frmTrangChu;
		setBackground(new Color(205,201,195,255));
		setBounds(10, 58, 1491, 697);
		setLayout(null);
		
		JPanel pnlChucNang = new JPanel();
		pnlChucNang.setBorder(new TitledBorder(null, "Ch\u1EE9c n\u0103ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlChucNang.setBounds(56, 10, 383, 56);
		add(pnlChucNang);
		pnlChucNang.setLayout(null);
		
		btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		       
		    	dlgThongTinKhachHang dlgQuanLyTK = new dlgThongTinKhachHang(frmTrangChu,"Thêm",null);
	            dlgQuanLyTK.setLocationRelativeTo(frmTrangChu);
	            dlgQuanLyTK.setVisible(true);
		    }
		});
		btnThem.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnThem.setBounds(27, 19, 99, 27);
		pnlChucNang.add(btnThem);
		
		btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(kh!=null) {
					dlgKhachHang = new dlgThongTinKhachHang(frmTrangChu,"Cập nhật",kh);
					dlgKhachHang.setLocationRelativeTo(frmTrangChu);
			
					dlgKhachHang.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng cần cập nhật thông tin!", "Thông báo", JOptionPane.WARNING_MESSAGE);
				}
				kh=null;
				
				
			}
		});
		btnCapNhat.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnCapNhat.setBounds(158, 19, 131, 27);
		pnlChucNang.add(btnCapNhat);
		
		JPanel pnlKhungSP = new JPanel();
		pnlKhungSP.setBorder(new TitledBorder(null, "Danh s\u00E1ch kh\u00E1ch h\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlKhungSP.setBounds(133, 73, 1127, 614);
		add(pnlKhungSP);
		pnlKhungSP.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 94, 1107, 510);
		pnlKhungSP.add(scrollPane);
		
		tblKhachHang = new JTable();
		tblKhachHang.setModel(dataModel = new DefaultTableModel(
			new String[] {
				"M\u00E3 Kh\u00E1ch h\u00E0ng", "H\u1ECD t\u00EAn", "Gi\u1EDBi t\u00EDnh", "\u0110i\u1EC7n tho\u1EA1i", "Ng\u00E0y th\u00EAm","Điểm tích lũy"
			}, 0
		));
		tblKhachHang.getColumnModel().getColumn(0).setPreferredWidth(88);
		tblKhachHang.getColumnModel().getColumn(1).setPreferredWidth(112);
		scrollPane.setViewportView(tblKhachHang);
		
		JPanel pnlKhung1 = new JPanel();
		pnlKhung1.setBounds(10, 20, 1107, 64);
		pnlKhungSP.add(pnlKhung1);
		pnlKhung1.setLayout(null);
		
		JLabel lblMaKH = new JLabel("Nhập mã KH:");
		lblMaKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMaKH.setBounds(10, 10, 174, 19);
		pnlKhung1.add(lblMaKH);
		
		txtmaTim = new JTextField();
		txtmaTim.setBounds(20, 32, 208, 20);
		pnlKhung1.add(txtmaTim);
		txtmaTim.setColumns(10);
		
		btnTim = new JButton("Tìm");
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTim.setBounds(238, 31, 60, 21);
		pnlKhung1.add(btnTim);
		btnTim.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String maKH = txtmaTim.getText().trim();
		        // Kiểm tra xem trường mã nhân viên để tìm kiếm có được nhập không
		        if (maKH.isEmpty()) {
		            capNhatBangKhachHang();
		        } else {
		            // Gọi phương thức để tìm nhân viên trong cơ sở dữ liệu dựa trên mã
		        	 KhachHangDAO KhachHangDAO = new KhachHangDAO();
			            KhachHang KhachHang = KhachHangDAO.getById((maKH));
			            if (KhachHang != null) {
			            	timKiem(maKH);
			            	txtmaTim.setText("");
			            } else {
			                // Nếu không tìm thấy nhân viên, hiển thị thông báo không tìm thấy
			                JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên với mã đã nhập!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			            }
		        }
		    }
		});
		
		JLabel lblTuNgay = new JLabel("Từ ngày:");
		lblTuNgay.setBounds(392, 16, 132, 13);
		pnlKhung1.add(lblTuNgay);
		
		btnLoc = new JButton("Lọc");
		btnLoc.setBounds(746, 31, 60, 21);
		pnlKhung1.add(btnLoc);
		btnLoc.addActionListener(new ActionListener() {
    	    @Override
    	    public void actionPerformed(ActionEvent e) {
    	    	 LocalDate d1= convertJDateChooserToLocalDate(dcTuNgay);
    		        LocalDate d2= convertJDateChooserToLocalDate(dcDenNgay);
    		        System.out.println(d1+"\n"+d2);
    		        if (d1.isEqual(LocalDate.now()) || d2.isEqual(LocalDate.now())) {
    					 capNhatBangKhachHang();;
    				}else {
    			        capNhatKhachHangTheoNgay(d1, d2);
    				}
    	    
    	    }
    	});
		dcTuNgay = new JDateChooser();
		dcTuNgay.setBounds(392, 33, 154, 19);
		pnlKhung1.add(dcTuNgay);
		
		JLabel lblDenNgay = new JLabel("Đến ngày:");
		lblDenNgay.setBounds(567, 16, 132, 13);
		pnlKhung1.add(lblDenNgay);
		
		dcDenNgay = new JDateChooser();
		dcDenNgay.setBounds(567, 33, 154, 19);
		pnlKhung1.add(dcDenNgay);
		
		tblKhachHang.addMouseListener(this);
		btnTim.addActionListener(this);
		btnThem.addActionListener(this);
		btnCapNhat.addActionListener(this);
		btnLoc.addActionListener(this);
		
		
		khachHangDAO = new KhachHangDAO();
		
		Calendar calendar = Calendar.getInstance();
	    calendar.set(Calendar.DAY_OF_MONTH, 1);
	    java.util.Date startDate = calendar.getTime();
	    dcTuNgay.setDate(startDate);

	    calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
	    java.util.Date endDate = calendar.getTime();
	    dcDenNgay.setDate(endDate);
		capNhatBangKhachHangThangNay();
	}
	public void capNhatBangKhachHangThangNay() {
        dataModel = (DefaultTableModel) tblKhachHang.getModel();
        dataModel.setRowCount(0);

        KhachHangDAO KhachHangDAO = new KhachHangDAO();
        ArrayList<KhachHang> danhSachKhachHang = KhachHangDAO.selectCustomersAddedThisMonth();
        for (KhachHang hd : danhSachKhachHang) {
            Object[] row = { hd.getMaKhachHang(), hd.getHoTen(),hd.isGioiTinh()?"Nam":"Nữ",hd.getDienThoai(), hd.getNgayThem(),hd.getDiemTichLuy()};
            dataModel.addRow(row);
        }
    }
	
	public void capNhatBangKhachHang() {
        dataModel = (DefaultTableModel) tblKhachHang.getModel();
        dataModel.setRowCount(0);
        KhachHangDAO KhachHangDAO = new KhachHangDAO();
        ArrayList<KhachHang> danhSachKhachHang = KhachHangDAO.getAll();
        for (KhachHang hd : danhSachKhachHang) {
            Object[] row = { hd.getMaKhachHang(), hd.getHoTen(),hd.isGioiTinh()?"Nam":"Nữ",hd.getDienThoai(), hd.getNgayThem(),hd.getDiemTichLuy()};
            dataModel.addRow(row);
        }
    }
	
	private void highlightCustomer(String customerID) {
	    // Duyệt qua từng hàng trong bảng để tìm khách hàng có mã tương ứng
	    for (int row = 0; row < tblKhachHang.getRowCount(); row++) {
	        String id = (String) tblKhachHang.getValueAt(row, 0); // Lấy giá trị mã khách hàng từ cột 0
	        if (id.equals(customerID)) {
	            // Nếu tìm thấy khách hàng, tô đậm dòng tương ứng
	            tblKhachHang.setRowSelectionInterval(row, row);
	            // Cuộn đến dòng được chọn để khách hàng được nhìn thấy ngay
	            tblKhachHang.scrollRectToVisible(tblKhachHang.getCellRect(row, 0, true));
	            return;
	        }
	    }
	    // Hiển thị thông báo nếu không tìm thấy khách hàng
	    JOptionPane.showMessageDialog(this, "Customer not found!", "Error", JOptionPane.ERROR_MESSAGE);
	}
	public void timKiem(String maKH) {
	    // Kiểm tra nếu model đã được khởi tạo
	    if (dataModel != null) {
	        // Xóa dữ liệu hiện tại trong bảng
	        dataModel.setRowCount(0);

	        // Lấy danh sách nhân viên từ cơ sở dữ liệu dựa trên loại chức vụ được chọn
	        KhachHangDAO khachHangDAO = new KhachHangDAO();
	        KhachHang
	        	hd = khachHangDAO.getById(maKH);

	 
	        Object[] row = { hd.getMaKhachHang(), hd.getHoTen(),hd.isGioiTinh()?"Nam":"Nữ",hd.getDienThoai(), hd.getNgayThem(),hd.getDiemTichLuy()};
            dataModel.addRow(row);
	 
	    }
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int selectedRow = tblKhachHang.getSelectedRow();
		
		if(selectedRow>=0) {
			String ma=dataModel.getValueAt(selectedRow, 0).toString();
			String ten=dataModel.getValueAt(selectedRow, 1).toString();
			boolean gt=dataModel.getValueAt(selectedRow, 2).toString().equals("Nam")?true:false;
			String dt=dataModel.getValueAt(selectedRow, 3).toString();
			 String nt = dataModel.getValueAt(selectedRow, 4).toString();
		        
		        // Parse the string to LocalDateTime
		        LocalDateTime day = LocalDateTime.parse(nt, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		        kh= new KhachHang(ma, ten, gt, dt, day);
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
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
	public static LocalDate convertJDateChooserToLocalDate(JDateChooser jDateChooser) {
		 java.util.Date selectedDate = jDateChooser.getDate();
	    if (selectedDate == null) {
	        return LocalDate.now();
	    }
	    return selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public void capNhatKhachHangTheoNgay(LocalDate d1, LocalDate d2 ) {
		
		if (dataModel != null) {
	        dataModel.setRowCount(0);
	        KhachHangDAO khachHangDAO = new KhachHangDAO();
	        ArrayList<KhachHang> danhSachKhachHang = khachHangDAO.selectByDay(d1,d2);
	        for (KhachHang hd : danhSachKhachHang) {
	        	Object[] row = { hd.getMaKhachHang(), hd.getHoTen(),hd.isGioiTinh()?"Nam":"Nữ",hd.getDienThoai(), hd.getNgayThem(),hd.getDiemTichLuy()};
	            dataModel.addRow(row);
	        }
	 
	    }
	}
}
