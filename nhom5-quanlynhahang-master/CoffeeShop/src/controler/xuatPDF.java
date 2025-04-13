package controler;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import dao.HoaDonDAO;
import entity.HoaDon;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class xuatPDF {
	
	public static void xuatPDF(HoaDon hoaDon) throws IOException, DocumentException {
        String hoaDonName = hoaDon.getMaHoaDon();
        String filePath = hoaDonName + ".pdf"; 
        File existingFile = new File(filePath);
        if (existingFile.exists()) {
            existingFile.delete();
        }

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            Document document = new Document();
            
            BaseFont baseFont = BaseFont.createFont("fonts/DejaVuSans.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font = new Font(baseFont, 12); 

            PdfWriter.getInstance(document, fos);

            document.open();
            document.add(new Paragraph(hoaDon.toString(), font)); 
            document.close(); 
        }

        try {
            File pdfFile = new File(filePath);
            if (pdfFile.exists() && Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(pdfFile); 
            } else {
                System.out.println("Không thể mở tệp PDF.");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
