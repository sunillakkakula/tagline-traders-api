package com.vtt.apps.controller;
/*
 * package com.vtt.apps.controller;
 * 
 * import java.io.IOException; import java.io.OutputStream; import
 * java.util.Properties;
 * 
 * import javax.activation.DataHandler; import javax.mail.MessagingException;
 * import javax.mail.internet.InternetAddress; import
 * javax.mail.internet.MimeBodyPart; import javax.mail.internet.MimeMessage;
 * import javax.mail.internet.MimeMultipart;
 * 
 * import org.slf4j.Logger; import org.slf4j.LoggerFactory; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.core.io.ClassPathResource; import
 * org.springframework.mail.SimpleMailMessage; import
 * org.springframework.mail.javamail.JavaMailSender; import
 * org.springframework.mail.javamail.MimeMessageHelper; import
 * org.springframework.web.bind.annotation.CrossOrigin; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import java.io.ByteArrayOutputStream; import java.io.OutputStream; import
 * java.util.Properties;
 * 
 * import javax.activation.DataHandler; import javax.activation.DataSource;
 * import javax.mail.Message; import javax.mail.Session; import
 * javax.mail.Transport; import javax.mail.internet.InternetAddress; import
 * javax.mail.internet.MimeBodyPart; import javax.mail.internet.MimeMessage;
 * import javax.mail.internet.MimeMultipart; import
 * javax.mail.util.ByteArrayDataSource;
 * 
 * import com.itextpdf.text.Document; import
 * com.itextpdf.text.DocumentException; import com.itextpdf.text.Paragraph;
 * import com.itextpdf.text.Chunk; import com.itextpdf.text.Document; import
 * com.itextpdf.text.Paragraph; import com.itextpdf.text.pdf.PdfWriter;
 * 
 * 
 * @RestController
 * 
 * @RequestMapping("/api")
 * 
 * @CrossOrigin(origins = "http://localhost:9090")
 * 
 * public class EmailController {
 * 
 * private final Logger LOGGER = LoggerFactory.getLogger(getClass());
 * 
 * @Autowired private JavaMailSender javaMailSender;
 * 
 * 
 * Send Email
 * 
 * @GetMapping("/email/send") public void sendSimpleEmail() {
 * LOGGER.info("Executing sendSimpleEmail in EmailController");
 * 
 * try { sendEmail(); // sendEmailWithAttachment();
 * 
 * } catch (Exception e) { e.printStackTrace(); } catch (IOException e) {
 * e.printStackTrace(); }
 * 
 * 
 * System.out.println("Done");
 * 
 * }
 * 
 * void sendEmail() {
 * 
 * SimpleMailMessage msg = new SimpleMailMessage();
 * msg.setTo("sunil.lakkakula@gmail.com",
 * "sales.taglinetraders@gmail.com","Rajesh100101@gmail.com ");
 * 
 * msg.setSubject("Vishudha Tagline Traders  - Order Acknowledgement");
 * msg.setText("Thanks for placing Order");
 * 
 * javaMailSender.send(msg);
 * 
 * }
 * 
 * void sendEmailWithAttachment() throws MessagingException, IOException {
 * 
 * MimeMessage msg = javaMailSender.createMimeMessage();
 * 
 * // true = multipart message MimeMessageHelper helper = new
 * MimeMessageHelper(msg, true); helper.setTo("1@gmail.com");
 * 
 * helper.setSubject("Testing from Spring Boot");
 * 
 * // default = text/plain //helper.setText("Check attachment for image!");
 * 
 * // true = text/html helper.setText("<h1>Check attachment for image!</h1>",
 * true);
 * 
 * //FileSystemResource file = new FileSystemResource(new
 * File("classpath:android.png"));
 * 
 * //Resource resource = new ClassPathResource("android.png"); //InputStream
 * input = resource.getInputStream();
 * 
 * //ResourceUtils.getFile("classpath:android.png");
 * 
 * helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));
 * 
 * javaMailSender.send(msg);
 * 
 * }
 * 
 *//**
	 * Sends an email with a PDF attachment.
	 */
/*
 * public void email() { String smtpHost = "yourhost.com"; //replace this with a
 * valid host int smtpPort = 587; //replace this with a valid port
 * 
 * String sender = "sender@yourhost.com"; //replace this with a valid sender
 * email address String recipient = "recipient@anotherhost.com"; //replace this
 * with a valid recipient email address String content = "dummy content"; //this
 * will be the text of the email String subject = "dummy subject"; //this will
 * be the subject of the email
 * 
 * Properties properties = new Properties(); properties.put("mail.smtp.host",
 * smtpHost); properties.put("mail.smtp.port", smtpPort); Session session =
 * Session.getDefaultInstance(properties, null);
 * 
 * ByteArrayOutputStream outputStream = null;
 * 
 * try { //construct the text body part MimeBodyPart textBodyPart = new
 * MimeBodyPart(); textBodyPart.setText(content);
 * 
 * //now write the PDF content to the output stream outputStream = new
 * ByteArrayOutputStream(); writePdf(outputStream); byte[] bytes =
 * outputStream.toByteArray();
 * 
 * //construct the pdf body part DataSource dataSource = new
 * ByteArrayDataSource(bytes, "application/pdf"); MimeBodyPart pdfBodyPart = new
 * MimeBodyPart(); pdfBodyPart.setDataHandler(new DataHandler(dataSource));
 * pdfBodyPart.setFileName("test.pdf");
 * 
 * //construct the mime multi part MimeMultipart mimeMultipart = new
 * MimeMultipart(); mimeMultipart.addBodyPart(textBodyPart);
 * mimeMultipart.addBodyPart(pdfBodyPart);
 * 
 * //create the sender/recipient addresses InternetAddress iaSender = new
 * InternetAddress(sender); InternetAddress iaRecipient = new
 * InternetAddress(recipient);
 * 
 * //construct the mime message MimeMessage mimeMessage = new
 * MimeMessage(session); mimeMessage.setSender(iaSender);
 * mimeMessage.setSubject(subject);
 * mimeMessage.setRecipient(Message.RecipientType.TO, iaRecipient);
 * mimeMessage.setContent(mimeMultipart);
 * 
 * //send off the email Transport.send(mimeMessage);
 * 
 * System.out.println("sent from " + sender + ", to " + recipient +
 * "; server = " + smtpHost + ", port = " + smtpPort); } catch(Exception ex) {
 * ex.printStackTrace(); } finally { //clean off if(null != outputStream) { try
 * { outputStream.close(); outputStream = null; } catch(Exception ex) { } } } }
 * 
 *//**
	 * Writes the content of a PDF file (using iText API) to the
	 * {@link OutputStream}.
	 * 
	 * @param outputStream {@link OutputStream}.
	 * @throws Exception
	 *//*
		 * public void writePdf(OutputStream outputStream) throws Exception { Document
		 * document = new Document(); PdfWriter.getInstance(document, outputStream);
		 * 
		 * document.open();
		 * 
		 * document.addTitle("Test PDF"); document.addSubject("Testing email PDF");
		 * document.addKeywords("iText, email"); document.addAuthor("Jee Vang");
		 * document.addCreator("Jee Vang");
		 * 
		 * Paragraph paragraph = new Paragraph(); paragraph.add(new Chunk("hello!"));
		 * document.add(paragraph);
		 * 
		 * document.close(); }
		 * 
		 * 
		 * 
		 * }
		 */