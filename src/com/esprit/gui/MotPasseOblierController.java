/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.esprit.gui;

import com.esprit.entities.Administrateur;
import com.esprit.entities.Eleve;
import com.esprit.entities.Enseignant;
import com.esprit.entities.User;
import com.esprit.services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class MotPasseOblierController implements Initializable {

    @FXML
    private TextField TFMail;
    @FXML
    private TextField TFCode;

    private User user;
    ServiceUser us = new ServiceUser();

    private Timer timer;
    private TimerTask disable;
    private int code;
    @FXML
    private TextField TFNewPassword;
    @FXML
    private TextField TFConfirmNewPssword;
    @FXML
    private Label LCode;
    @FXML
    private Button BContinue;
    @FXML
    private Label LNPassord;
    @FXML
    private Label LCNPassword;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LCode.setVisible(false);
        BContinue.setVisible(false);
        LNPassord.setVisible(false);
        LCNPassword.setVisible(false);

        TFCode.setVisible(false);
        TFNewPassword.setVisible(false);
        TFConfirmNewPssword.setVisible(false);

    }

    public void sendCode(int code, String nom, String prenom, String recepteur) {
        String emetteur = "ibrahim.fartoun@gmail.com";
        String host = "smtp.gmail.com";
        String mail = "ibrahim.fartoun@gmail.com";
        String password = "wwlkavqimfihpnsn";

        Properties properties = new Properties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.port", "587");

        properties.setProperty("mail.smtp.ssl.trust", host);

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mail, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emetteur));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recepteur));
            message.setSubject("Code de réinitialisation de mot de passe de votre compte");

            message.setText("Bonjour cher(e) " + nom + " " + prenom + ",\n\nVotre code de réinitialisation de mot de passe est : " + code);

            Transport.send(message);

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Mail");
            alert.setHeaderText(null);
            alert.setContentText("L'e-mail contenant le code de réinitialisation de mot de passe a été envoyé à l'adresse " + recepteur);
            alert.showAndWait();

            LCode.setVisible(true);
            BContinue.setVisible(true);
            LNPassord.setVisible(true);
            LCNPassword.setVisible(true);

            TFCode.setVisible(true);
            TFNewPassword.setVisible(true);
            TFConfirmNewPssword.setVisible(true);

        } catch (MessagingException ex) {
            ex.printStackTrace();
            // Afficher une alerte en cas d'erreur lors de l'envoi du message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur s'est produite lors de l'envoi de l'e-mail. Veuillez réessayer plus tard.");
            alert.showAndWait();
        }
    }

    @FXML
    private void Continue(ActionEvent event) throws IOException {
        if (TFNewPassword.getText().equals(TFConfirmNewPssword.getText())) {
            if (user instanceof Administrateur || user instanceof Enseignant || user instanceof Eleve) {
                user.setPassword(TFNewPassword.getText());
                us.modifier(user);
                // boolean name = JOptionPane.showConfirmDialog(null,"Voulez vous supprimer?",null,JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION;	

                JOptionPane.showMessageDialog(null, "mot pase modifier avec succès");

            }
        } else {
            JOptionPane.showMessageDialog(null, "mot pase modifier avec succès");

            // Mots de passe différents
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Accueil.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Accueil");
        stage.show();
    }

    @FXML
    private void SendCode(ActionEvent event) {
        String email = TFMail.getText();

        // Vérifier si le champ email est vide
        if (email.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champ vide");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir le champ email.");
            alert.showAndWait();
            return;
        }

        // Rechercher l'utilisateur correspondant à l'adresse e-mail donnée
        ServiceUser userService = new ServiceUser();
        List<User> userList = new ArrayList<>(userService.afficher());
        User foundUser = null;
        for (User user : userList) {
            if (user.getMail().equals(email)) {
                foundUser = user;
                break;
            }
        }

        if (foundUser != null) {
            // Démarrer le minuteur
            timer = new Timer();
            disable = new TimerTask() {
                @Override
                public void run() {
                    // Code à exécuter après 2000 millisecondes (2 secondes)
                }
            };
            timer.schedule(disable, 2000);

            // Générer un code aléatoire
            Random random = new Random();
            code = random.nextInt(9000) + 1000;
            System.out.println(code);

            // Envoyer l'e-mail avec le code de réinitialisation
            sendCode(code, foundUser.getNom(), foundUser.getPrenom(), foundUser.getMail());

            user = foundUser;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Utilisateur introuvable");
            alert.setHeaderText(null);
            alert.setContentText("Aucun utilisateur trouvé avec l'adresse e-mail spécifiée.");
            alert.showAndWait();
        }

        // Effacer le champ email après l'envoi du code
        TFMail.clear();

    }
}
