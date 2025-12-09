package com.medassi.bdpm_console_etudiant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BDPM {

    private static BDPM baseMariadb = null;
    private Statement statement;

    private BDPM(String adresse, String nomBase, String login, String mdp) {
        try {
            String chaineConnexion = "jdbc:mariadb://" + adresse + "/" + nomBase;
            Connection connection
                    = DriverManager.getConnection(chaineConnexion, login, mdp);
            statement = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(BDPM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static BDPM getDatabase() {
        if (baseMariadb == null) {
            baseMariadb = new BDPM("192.168.153.10:3306", "simple_bdpm", "sio", "sio");
        }
        return baseMariadb;
    }

    public ArrayList<Medicament> getMedicamentsSurvRenf() {
        ArrayList<Medicament> ms = new ArrayList<>();
        try {
            ResultSet rs;
            rs = statement.executeQuery("Select * from medicaments where surveillance_renforcee='Oui'");
            while (rs.next()) {
                Medicament m = new Medicament();
                m.code_cis = rs.getString("code_cis");
                m.denomination = rs.getString("denomination");
                m.forme = rs.getString("forme");
                m.voies_admin = rs.getString("voies_admin");
                m.statut_admin_amm = rs.getString("statut_admin_amm");
                m.type_auth_amm = rs.getString("type_auth_amm");
                m.etat_comm = rs.getString("etat_comm");
                m.date_amm = rs.getString("date_amm");
                m.statut_bdm = rs.getString("statut_bdm");
                m.numero_auth = rs.getString("numero_auth");
                m.titulaires = rs.getString("titulaires");
                m.surveillance_renforcee = rs.getString("surveillance_renforcee");
                ms.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BDPM.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ms;
    }

    public ArrayList<Composant> getCompositions(Medicament m) {
        String sql = "SELECT * FROM compositions  where code_cis='" + m.code_cis + "'";
        ArrayList<Composant> cs = new ArrayList<>();
        try {
            ResultSet rs;
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                Composant c = new Composant();
                c.denomination_sub = rs.getString("denomination_sub");
                c.designation = rs.getString("designation");
                c.dosage = rs.getString("dosage");
                cs.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BDPM.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cs;
    }

    public ArrayList<Medicament> getMedicamentsByMotClef(String mot) {
        ArrayList<Medicament> ms = new ArrayList<>();
        try {
            ResultSet rs;
            rs = statement.executeQuery("Select * from medicaments where denomination like ('%" + mot + "%')");
            while (rs.next()) {
                Medicament m = new Medicament();
                m.code_cis = rs.getString("code_cis");
                m.denomination = rs.getString("denomination");
                m.forme = rs.getString("forme");
                m.voies_admin = rs.getString("voies_admin");
                m.statut_admin_amm = rs.getString("statut_admin_amm");
                m.type_auth_amm = rs.getString("type_auth_amm");
                m.etat_comm = rs.getString("etat_comm");
                m.date_amm = rs.getString("date_amm");
                m.statut_bdm = rs.getString("statut_bdm");
                m.numero_auth = rs.getString("numero_auth");
                m.titulaires = rs.getString("titulaires");
                m.surveillance_renforcee = rs.getString("surveillance_renforcee");
                ms.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BDPM.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ms;
    }

    Medicament getMedicamentByCodeCIS(String code) {
        ResultSet rs;
        Medicament m = null;
        try {
            rs = statement.executeQuery("Select * from medicaments where code_cis='" + code + "'");
            if (rs.next()) {
                m = new Medicament();
                m.code_cis = rs.getString("code_cis");
                m.denomination = rs.getString("denomination");
                m.forme = rs.getString("forme");
                m.voies_admin = rs.getString("voies_admin");
                m.statut_admin_amm = rs.getString("statut_admin_amm");
                m.type_auth_amm = rs.getString("type_auth_amm");
                m.etat_comm = rs.getString("etat_comm");
                m.date_amm = rs.getString("date_amm");
                m.statut_bdm = rs.getString("statut_bdm");
                m.numero_auth = rs.getString("numero_auth");
                m.titulaires = rs.getString("titulaires");
                m.surveillance_renforcee = rs.getString("surveillance_renforcee");
            }
        } catch (SQLException ex) {
            System.getLogger(BDPM.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return m;
    }

    public ArrayList<Medicament> getMedicamentsByLabo(String unLaboratoire) {
        ArrayList<Medicament> lesMedocs = new ArrayList<>();
        String sql = "Select * from medicaments where titulaires like '%" + unLaboratoire + "%'";
        try {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Medicament unMedoc = new Medicament();
                unMedoc.code_cis = rs.getString("code_cis");
                unMedoc.denomination = rs.getString("denomination");
                unMedoc.forme = rs.getString("forme");
                unMedoc.voies_admin = rs.getString("voies_admin");
                unMedoc.statut_admin_amm = rs.getString("statut_admin_amm");
                unMedoc.type_auth_amm = rs.getString("type_auth_amm");
                unMedoc.etat_comm = rs.getString("etat_comm");
                unMedoc.date_amm = rs.getString("date_amm");
                unMedoc.statut_bdm = rs.getString("statut_bdm");
                unMedoc.numero_auth = rs.getString("numero_auth");
                unMedoc.titulaires = rs.getString("titulaires");
                unMedoc.surveillance_renforcee = rs.getString("surveillance_renforcee");
                lesMedocs.add(unMedoc);
            }
        } catch (SQLException ex) {
            System.getLogger(BDPM.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return lesMedocs;
    }
}
