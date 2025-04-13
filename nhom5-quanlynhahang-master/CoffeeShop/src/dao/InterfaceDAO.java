package dao;

import java.util.ArrayList;

// Interface để định nghĩa các phương thức truy xuất dữ liệu chung cho mọi đối tượng
public interface InterfaceDAO<T> {
    
    /**
     * Thêm một đối tượng mới vào cơ sở dữ liệu.
     * @param object đối tượng cần thêm
     * @return số hàng bị ảnh hưởng trong cơ sở dữ liệu, thường là 1 nếu thành công, 0 nếu thất bại
     */
    public int add(T object);
    
    /**
     * Cập nhật thông tin của đối tượng trong cơ sở dữ liệu.
     * @param object đối tượng có thông tin đã thay đổi
     * @return số hàng bị ảnh hưởng, 1 nếu thành công, 0 nếu thất bại
     */
    public int update(T object);
    
    /**
     * Lấy danh sách tất cả các đối tượng từ cơ sở dữ liệu.
     * @return danh sách các đối tượng kiểu T từ cơ sở dữ liệu
     */
    public ArrayList<T> getAll();
    
    /**
     * Tìm đối tượng theo mã định danh.
     * @param id mã định danh của đối tượng cần tìm
     * @return đối tượng có mã định danh tương ứng, hoặc null nếu không tìm thấy
     */
    public T getById(String id);
}
