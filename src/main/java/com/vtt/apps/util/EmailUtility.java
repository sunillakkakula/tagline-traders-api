/*
 * //package com.vtt.apps.util; // //public class EmailUtility { // void
 * sendEmail() { // // SimpleMailMessage msg = new SimpleMailMessage(); //
 * msg.setTo("1@gmail.com", "2@yahoo.com"); // //
 * msg.setSubject("Testing from Spring Boot"); //
 * msg.setText("Hello World \n Spring Boot Email"); // //
 * javaMailSender.send(msg); // // } // // void sendEmailWithAttachment() throws
 * MessagingException, IOException { // // MimeMessage msg =
 * javaMailSender.createMimeMessage(); // // // true = multipart message //
 * MimeMessageHelper helper = new MimeMessageHelper(msg, true); //
 * helper.setTo("1@gmail.com"); // //
 * helper.setSubject("Testing from Spring Boot"); // // // default = text/plain
 * // //helper.setText("Check attachment for image!"); // // // true = text/html
 * // helper.setText("<h1>Check attachment for image!</h1>", true); // //
 * //FileSystemResource file = new FileSystemResource(new
 * File("classpath:android.png")); // // //Resource resource = new
 * ClassPathResource("android.png"); // //InputStream input =
 * resource.getInputStream(); // //
 * //ResourceUtils.getFile("classpath:android.png"); // //
 * helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));
 * // // javaMailSender.send(msg); // // } //}
 */