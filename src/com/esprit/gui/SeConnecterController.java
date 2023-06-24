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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;

public class SeConnecterController implements Initializable {

    @FXML
    private AnchorPane Vbox;
    @FXML
    private TextField TFLogin;
    @FXML
    private PasswordField TFPassword;

    ServiceUser su = new ServiceUser();
    List<User> listUser = new ArrayList<>(su.afficher());
    @FXML
    private ImageView ImageView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Méthode appelée lors de l'initialisation du contrôleur
        
    }

    @FXML
    private void LoginUser(ActionEvent event) throws SQLException, IOException {
        String login = TFLogin.getText();
        String password = TFPassword.getText();
        User e = null;
        int choix  ;
        if (!(login.isEmpty() || password.isEmpty())) {
            for (User u : listUser) {
                if (u.getLogin().equals(login) && verifierMotDePasse(password, u.getPassword())) {
                    e = u;
                    break;
                }
            }

            if (e == null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informations incorrectes");
                alert.setHeaderText(null);
                alert.setContentText("Le login ou le mot de passe est incorrect.");
                alert.showAndWait();
            } else {
                if (e instanceof Administrateur ) {
                    choix = 1;
                   
                    System.out.println("Administrateur ");
                    JOptionPane.showMessageDialog(null, "Administrateur Connecté ");

                    TFPassword.setText("");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/CrudUser1.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Accueil");
                    stage.show();
                    // TFNcarte.setDisable(true);

                } else if (e instanceof Enseignant) {
                    choix = 0;
                    System.out.println("Enseignant ");
                    JOptionPane.showMessageDialog(null, "Enseignant Connecté ");

                    TFPassword.setText("");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Accueil.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Accueil");
                    stage.show();
                } else if (e instanceof Eleve) {
                    choix = -1;
                    System.out.println("Eleve choix");
                    JOptionPane.showMessageDialog(null, "Eleve Connecté choix");

                    TFPassword.setText("");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Accueil.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Accueil");
                    stage.show();

                    // Fermer la fenêtre de connexion
                    Stage loginStage = (Stage) TFLogin.getScene().getWindow();
                    loginStage.close();
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs vides");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
        }
    }

    public boolean verifierMotDePasse(String password, String password0) {
        // Implémenter ici la logique de vérification du mot de passe
        return password.equals(password0);
    }

    @FXML
    private void motpasseoblier(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/MotPasseOblier.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Mot Passe Oblier");
        stage.show();
    }
}
