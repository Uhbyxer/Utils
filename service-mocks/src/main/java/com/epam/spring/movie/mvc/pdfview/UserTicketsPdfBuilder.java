package com.epam.spring.movie.mvc.pdfview;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.epam.spring.movie.bean.Ticket;
import com.epam.spring.movie.bean.User;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class UserTicketsPdfBuilder extends AbstractITextPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document doc, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		@SuppressWarnings("unchecked")
		List<Ticket> tickets = (List<Ticket>) model.get("tickets");
		User user = (User) model.get("user");
		doc.add(new Paragraph("Tickets for user: " + user.getName()));
		
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100.0f);
        table.setWidths(new float[] {1.0f, 2.0f, 3.0f, 5.0f, 2.0f});
        table.setSpacingBefore(10);
         
        // define font for table header row
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(BaseColor.WHITE);
         
        // define table header cell
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.BLUE);
        cell.setPadding(5);

        // write table header
        cell.setPhrase(new Phrase("#", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Auditorium", font));
        table.addCell(cell);
 
        cell.setPhrase(new Phrase("Time", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Movie", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Price", font));
        table.addCell(cell);
         
        // write table row data
        for (Ticket ticket : tickets) {
            table.addCell(ticket.getId().toString());
            table.addCell(ticket.getAuditorium().getName());
            table.addCell(ticket.getDateTime().toString());
            table.addCell(ticket.getEvent().getName());
            table.addCell(ticket.getPrice().toString());
        }
         
        doc.add(table);
         
		
	}
	
}
