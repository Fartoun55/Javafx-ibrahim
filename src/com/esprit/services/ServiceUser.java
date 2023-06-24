/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.services;

import com.esprit.entities.Administrateur;
import com.esprit.entities.Eleve;
import com.esprit.entities.Enseignant;
import com.esprit.entities.User;

import com.esprit.utils.DataSource;
import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
public class ServiceUser {

    private final Connection cnx = DataSource.getInstance().getCnx();

    public void ajouter(User u) {
        if (u instanceof Administrateur) {
            try {

                String req = "INSERT INTO user(nom, prenom, dateNaissance, mail, tel, login, password, role) VALUES (?,?,?,?,?,?,?,?);";
                PreparedStatement pst = cnx.prepareStatement(req);
                pst.setString(1, u.getNom());
                pst.setString(2, u.getPrenom());
                pst.setDate(3, u.getDateNaissance());
                pst.setString(4, u.getMail());
                pst.setInt(5, u.getTel());
                pst.setString(6, u.getLogin());
                pst.setString(7, u.getPassword());
                pst.setString(8, "Administrateur");

                pst.executeUpdate();
                System.out.println("Administrateur ajoutée !");
                JOptionPane.showMessageDialog(null, "administrateur ajouter");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }

        if (u instanceof Eleve) {
            try {

                String req = "INSERT INTO user(nom, prenom, dateNaissance, mail, tel, login, password, role, n_carte) VALUES (?,?,?,?,?,?,?,?,?);";
                PreparedStatement pst = cnx.prepareStatement(req);
                pst.setString(1, u.getNom());
                pst.setString(2, u.getPrenom());
                pst.setDate(3, u.getDateNaissance());
                pst.setString(4, u.getMail());
                pst.setInt(5, u.getTel());
                pst.setString(6, u.getLogin());
                pst.setString(7, u.getPassword());
                pst.setString(8, "Eleve");
                pst.setInt(9, ((Eleve) u).getN_carte());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Eleve ajouter");
                System.out.println("Eleve ajoutée !");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }

        if (u instanceof Enseignant) {
            try {

                String req = "INSERT INTO user(nom, prenom, dateNaissance, mail, tel, login, password, role, matricule) VALUES (?,?,?,?,?,?,?,?,?);";
                PreparedStatement pst = cnx.prepareStatement(req);
                pst.setString(1, u.getNom());
                pst.setString(2, u.getPrenom());
                pst.setDate(3, u.getDateNaissance());
                pst.setString(4, u.getMail());
                pst.setInt(5, u.getTel());
                pst.setString(6, u.getLogin());
                pst.setString(7, u.getPassword());
                pst.setString(8, "Enseignant");
                pst.setInt(9, ((Enseignant) u).getMatricule());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Enseignat ajouter");
                System.out.println("Enseignant ajoutée !");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }

    }

    public void modifier(User u) {
        try {
            String req = "UPDATE user SET nom=?, prenom=?, dateNaissance=?, mail=?, tel=?, login=?, password=? WHERE id_user=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, u.getNom());
            pst.setString(2, u.getPrenom());
            pst.setDate(3, new java.sql.Date(u.getDateNaissance().getTime()));
            pst.setString(4, u.getMail());
            pst.setInt(5, u.getTel());
            pst.setString(6, u.getLogin());
            pst.setString(7, u.getPassword());
            pst.setInt(8, u.getId_user());
            pst.executeUpdate();

            if (u instanceof Eleve) {
                String reqEleve = "UPDATE user SET n_carte=? WHERE id_user=?";
                PreparedStatement pstEleve = cnx.prepareStatement(reqEleve);
                pstEleve.setInt(1, ((Eleve) u).getN_carte());
                pstEleve.setInt(2, u.getId_user());
                pstEleve.executeUpdate();
                System.out.println("Eleve modifié !");
                JOptionPane.showMessageDialog(null, " Eleve mofifier avec succès");

            } else if (u instanceof Enseignant) {
                String reqEnseignant = "UPDATE user SET matricule=? WHERE id_user=?";
                PreparedStatement pstEnseignant = cnx.prepareStatement(reqEnseignant);
                pstEnseignant.setInt(1, ((Enseignant) u).getMatricule());
                pstEnseignant.setInt(2, u.getId_user());
                pstEnseignant.executeUpdate();
                System.out.println("Enseignant modifié !");
                JOptionPane.showMessageDialog(null, " Enseignant mofifier avec succès");

            } else if (u instanceof Administrateur) {
                System.out.println("Administrateur modifié !");
                JOptionPane.showMessageDialog(null, " Administrateur mofifier avec succès");

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void supprimer(User u) {
        try {
            String req = "DELETE from user WHERE id_user=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, u.getId_user());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, " supprimé avec succès");
            if (u instanceof Enseignant) {

                pst.executeUpdate();
                boolean name = JOptionPane.showConfirmDialog(null, "Voulez vous supprimer?", null, JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION;

                JOptionPane.showMessageDialog(null, "Enseignant supprimé avec succès");
            }
            if (u instanceof Administrateur) {

                pst.executeUpdate();
                boolean name = JOptionPane.showConfirmDialog(null, "Voulez vous supprimer?", null, JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION;

                JOptionPane.showMessageDialog(null, "Administrateur supprimé avec succès");
            }
            if (u instanceof Enseignant) {

                pst.executeUpdate();
                boolean name = JOptionPane.showConfirmDialog(null, "Voulez vous supprimer?", null, JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION;

                JOptionPane.showMessageDialog(null, "Enseignant supprimé avec succès");
            }
//            

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<User> afficher() {

        List list = new ArrayList<User>();
        String req = "SELECT * FROM user";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                if (rs.getString("role").equals("Administrateur")) {
                    User u = new Administrateur(rs.getInt("id_user"), rs.getString("nom"), rs.getString("prenom"), rs.getDate("dateNaissance"), rs.getString("mail"), rs.getInt("tel"), rs.getString("login"), rs.getString("password"), rs.getString("role"));
                    list.add(u);
                }
                if (rs.getString("role").equals("Eleve")) {
                    User u = new Eleve(rs.getInt("n_carte"), rs.getInt("id_user"), rs.getString("nom"), rs.getString("prenom"), rs.getDate("dateNaissance"), rs.getString("mail"), rs.getInt("tel"), rs.getString("login"), rs.getString("password"), rs.getString("role"));

                    list.add(u);
                }
                if (rs.getString("role").equals("Enseignant")) {
                    User u;
                    u = new Enseignant(rs.getInt("matricule"), rs.getInt("id_user"), rs.getString("nom"), rs.getString("prenom"), rs.getDate("dateNaissance"), rs.getString("mail"), rs.getInt("tel"), rs.getString("login"), rs.getString("password"), rs.getString("role"));

                    list.add(u);
                    User.u = u;

                }

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public List<User> rechercherParNom(String nomRecherche) {
        List<User> list = new ArrayList<>();

        String req = "SELECT * FROM user WHERE nom = ?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, nomRecherche); // Remplacez getNom par nomRecherche dans la requête

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String role = rs.getString("role");
                User u;
                if (role.equals("Administrateur")) {
                    u = new Administrateur(rs.getInt("id_user"), rs.getString("nom"), rs.getString("prenom"), rs.getDate("dateNaissance"), rs.getString("mail"), rs.getInt("tel"), rs.getString("login"), rs.getString("password"), role);
                } else if (role.equals("Eleve")) {
                    u = new Eleve(rs.getInt("id_user"), rs.getInt("n_carte"), rs.getString("nom"), rs.getString("prenom"), rs.getDate("dateNaissance"), rs.getString("mail"), rs.getInt("tel"), rs.getString("login"), rs.getString("password"), role);
                } else if (role.equals("Enseignant")) {
                    u = new Enseignant(rs.getInt("id_user"), rs.getInt("matricule"), rs.getString("nom"), rs.getString("prenom"), rs.getDate("dateNaissance"), rs.getString("mail"), rs.getInt("tel"), rs.getString("login"), rs.getString("password"), role);
                } else {
                    // Gérer le cas où le rôle n'est pas reconnu
                    continue;
                }
                list.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception selon les besoins de votre application
        }

        return list;

    }

    public void supprime1(int id_use) {
        try {
            String req = "DELETE from user WHERE id_user=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, id_use);
            pst.executeUpdate();
            boolean name = JOptionPane.showConfirmDialog(null, "Voulez vous supprimer?", null, JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION;

            JOptionPane.showMessageDialog(null, " supprimé avec succès");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void getuserbaypassword(String login, String password) {
        User u = null;
        try {
            String req = "SELECT user WHERE(login=? and password=?)";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(0, login);
            pst.setString(1, password);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                // u=new User(rs.getInt("id_user"), rs.getString("nom"), rs.getString("prenom"), rs.getDate("dateNaissance"), rs.getString("mail"), rs.getInt("tel"), rs.getString("login"),rs.getString("password"),rs.getString("role"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
