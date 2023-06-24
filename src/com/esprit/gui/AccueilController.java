/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.esprit.gui;

import com.esprit.entities.Administrateur;
import com.esprit.entities.Eleve;
import com.esprit.entities.Enseignant;
import com.esprit.entities.User;
import static com.esprit.entities.User.u;
import com.esprit.services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class AccueilController implements Initializable {

    private ServiceUser su = new ServiceUser();
    private List<User> listUser = new ArrayList<>(su.afficher());
    private User user;
    private User e ;
    @FXML
    private Button BAdmission;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            
        
//        for (User u : listUser) {
//            if (u.equals(user)) {
//                e = u;
//                // break;
//                if (e instanceof Administrateur) {
//            BAdmission.setVisible(false);
//        }
//               
//                
//            }
       // }

        

    }
    // TODO
    @FXML
    private void Admission(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/CrudUser1.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Accueil");
        stage.show();
    }

    @FXML
    private void Emploi(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/CrudUser1.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Accueil");
        stage.show();

    }

    @FXML
    private void Classe(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/CrudUser1.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Accueil");
        stage.show();

    }

}
