package guru.springframework.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import guru.springframework.domain.InfoPermis;
import guru.springframework.domain.Utilisateur;
import guru.springframework.domain.demande;
import guru.springframework.domain.demandeur;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class GeneratePdfReport {
    private static final Logger logger = LoggerFactory.getLogger(GeneratePdfReport.class);

    public GeneratePdfReport() {

    }

    public static byte[] demandesReport(List<demande> demandes) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(60);
            table.setWidths(new int[]{1, 3, 3});

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("Id demande", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Etat demande", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Type demande ", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            for (demande demande : demandes) {

                PdfPCell cell;

                cell = new PdfPCell(new Phrase(demande.getIddemande()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(demande.getEtatDemande()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(demande.getEtatDemande())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPaddingRight(5);
                table.addCell(cell);
            }

            PdfWriter.getInstance(document, out);
            document.open();
            document.add(table);
            document.close();

        } catch (DocumentException ex) {

            logger.error("Error occurred: {0}", ex);
        }

        return out.toByteArray();
    }

    public static byte[] demandeReport(demande demande, Utilisateur utilisateur, demandeur demandeur) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            Font headFontBold = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            Font headFont = FontFactory.getFont(FontFactory.TIMES_ROMAN);
            PdfWriter.getInstance(document, out);
            document.open();
            document.add(new Phrase("Nom et prénoms : ", headFontBold));
            document.add(new Phrase(utilisateur.getNom() + " " + utilisateur.getPrenom() + "\n", headFont));
            document.add(new Phrase("Email : ", headFontBold));
            document.add(new Phrase(utilisateur.getEmail() + "\n", headFont));
            document.add(new Phrase("N° téléphone : ", headFontBold));
            document.add(new Phrase(utilisateur.getTelephone() + "\n", headFont));
            document.add(new Phrase("Activités : ", headFontBold));
            document.add(new Phrase(demande.getDescriptionActivites() + "\n", headFont));
            document.add(new Phrase("Objectifs demande : ", headFontBold));
            document.add(new Phrase(demande.getObjectifs() + "\n", headFont));
            document.add(new Phrase("Applications envisagées  : ", headFontBold));
            document.add(new Phrase(demande.getApplicationsEnvisagees() + "\n", headFont));
            document.add(new Phrase("Commentaire  : ", headFontBold));
            document.add(new Phrase(demande.getCommentaire() + "\n", headFont));
            document.close();
        } catch (DocumentException ex) {
            logger.error("Error occurred: {0}", ex);
        }
        return out.toByteArray();
    }

    public static byte[] demandeReport(InfoPermis infoPermis) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            Font headFontBold = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            Font headFont = FontFactory.getFont(FontFactory.TIMES_ROMAN);
            Font titre = FontFactory.getFont(FontFactory.COURIER_BOLD);
            PdfWriter.getInstance(document, out);
            document.open();
            document.add(new Phrase("Demande d’autorisation pour l'accès aux ressources génétiques et aux connaissances traditionnelles\n" +
                    "associées à ces ressources\n" +
                    " et le partage des avantages découlant de\n" +
                    "leur utilisation \n\n\n", FontFactory.getFont(FontFactory.COURIER_BOLD)));
            document.add(new Phrase("Coordonnées du demandeur \n\n", titre));
            if (infoPermis.getTypeDemandeur().equals("Personne physique")) {
                document.add(new Phrase("Nom et prénoms : ", headFontBold));
                document.add(new Phrase(infoPermis.getNom() + " " + infoPermis.getPrenom() + "\n\n", headFont));
                document.add(new Phrase("Numéro de sécurité sociale : ", headFontBold));
                document.add(new Phrase(infoPermis.getNss() + "\n\n", headFont));
                document.add(new Phrase("Email : ", headFontBold));
                document.add(new Phrase(infoPermis.getEmail() + "\n\n", headFont));
                document.add(new Phrase("N° téléphone : ", headFontBold));
                document.add(new Phrase(infoPermis.getTelephone() + "\n\n", headFont));
            } else {
                document.add(new Phrase("dénomination (ou raison sociale) : ", headFontBold));
                document.add(new Phrase(infoPermis.getRaisonSociale() + "\n\n", headFont));
                document.add(new Phrase("Numéro d'identification fiscale : ", headFontBold));
                document.add(new Phrase(infoPermis.getNif() + "\n\n", headFont));
                document.add(new Phrase("Forme juridique : ", headFontBold));
                document.add(new Phrase(infoPermis.getFormeJuridique() + "\n\n", headFont));
                document.add(new Phrase("Adresse du siège social : ", headFontBold));
                document.add(new Phrase(infoPermis.getAdresse() + "\n\n", headFont));
            }
            document.add(new Phrase("Détails de la ressource génétique demandée et les connaissances traditionnelles associées\n\n", titre));
            document.add(new Phrase("Nom scientifique de la ressource : ", headFontBold));
            document.add(new Phrase(infoPermis.getScientifiqueNom() + "\n\n", headFont));
            document.add(new Phrase("Les zones géographiques de l'accès : ", headFontBold));
            document.add(new Phrase(infoPermis.getZones() + "\n\n", headFont));
            document.add(new Phrase("Nature de la connaissance traditionnelles  : ", headFontBold));
            document.add(new Phrase(infoPermis.getCta() + "\n\n", headFont));
            document.add(new Phrase("Quantité de la ressource à collecter  : ", headFontBold));
            document.add(new Phrase(infoPermis.getQuantite() + "\n\n", headFont));
            document.add(new Phrase("Périodes proposées pour la collecte : ", headFontBold));
            document.add(new Phrase(infoPermis.getPeriode() + "\n\n", headFont));
            document.add(new Phrase("Activités en vue desquelles la demande est effectuée, objectifs de celle-ci et applications envisagées\n\n", titre));
            document.add(new Phrase("Nature du permis : ", headFontBold));
            document.add(new Phrase("Permis d'accès à " + infoPermis.getTypeAcces() + "\n\n", headFont));
            document.add(new Phrase("Activités : ", headFontBold));
            document.add(new Phrase(infoPermis.getDescriptionActivites() + "\n\n", headFont));
            document.add(new Phrase("Objectifs demande : ", headFontBold));
            document.add(new Phrase(infoPermis.getObjectifs() + "\n\n", headFont));
            document.add(new Phrase("Applications envisagées  : ", headFontBold));
            document.add(new Phrase(infoPermis.getApplicationsEnvisagees() + "\n\n", headFont));
            if (infoPermis.getMoyensTransport() != null) {
                document.add(new Phrase("Informations sur la circulation des ressources\n\n", titre));
                document.add(new Phrase("Moyens de transport : ", headFontBold));
                document.add(new Phrase(infoPermis.getMoyensTransport() + "\n\n", headFont));
                document.add(new Phrase("Itinéraires de transport : ", headFontBold));
                document.add(new Phrase(infoPermis.getItineraires() + "\n\n", headFont));
            }
            if (infoPermis.getEntSort() != null) {
                document.add(new Phrase("Informations sur le transfert des ressources\n\n", titre));
                document.add(new Phrase("Les points de sortie ou entrée au terretoire national : ", headFontBold));
                document.add(new Phrase(infoPermis.getEntSort() + "\n\n", headFont));
            }
            document.add(new Phrase("Commentaire  : ", headFontBold));
            document.add(new Phrase(infoPermis.getCommentaire() + "\n\n", headFont));
            document.close();
        } catch (DocumentException ex) {
            logger.error("Error occurred: {0}", ex);
        }
        return out.toByteArray();
    }

    public static byte[] permisReport(InfoPermis infoPermis) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            Font headFontBold = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            Font headFont = FontFactory.getFont(FontFactory.TIMES_ROMAN);
            Font titre = FontFactory.getFont(FontFactory.COURIER_BOLD);
            PdfWriter.getInstance(document, out);
            document.open();
            document.add(new Phrase("Permis d'accès aux ressources génétiques et aux connaissances traditionnelles\n" +
                    "associées à ces ressources\n" +
                    " et le partage des avantages découlant de\n" +
                    "leur utilisation \n\n\n", FontFactory.getFont(FontFactory.COURIER_BOLD)));
            document.add(new Phrase("Code du permis \n\n", headFontBold));
            document.add(new Phrase(infoPermis.getCodePermis() + "\n\n", headFont));
            document.add(new Phrase("Coordonnées du demandeur \n\n", titre));
            if (infoPermis.getNom() != null) {
                document.add(new Phrase("Nom et prénoms : ", headFontBold));
                document.add(new Phrase(infoPermis.getNom() + " " + infoPermis.getPrenom() + "\n\n", headFont));
                document.add(new Phrase("Numéro de sécurité sociale : ", headFontBold));
                document.add(new Phrase(infoPermis.getNss() + "\n\n", headFont));
            } else {
                document.add(new Phrase("dénomination (ou raison sociale) : ", headFontBold));
                document.add(new Phrase(infoPermis.getRaisonSociale() + "\n\n", headFont));
                document.add(new Phrase("Numéro d'identification fiscale : ", headFontBold));
                document.add(new Phrase(infoPermis.getNif() + "\n\n", headFont));
                document.add(new Phrase("Forme juridique : ", headFontBold));
                document.add(new Phrase(infoPermis.getFormeJuridique() + "\n\n", headFont));
                document.add(new Phrase("Adresse du siège social : ", headFontBold));
                document.add(new Phrase(infoPermis.getAdresse() + "\n\n", headFont));
            }
            document.add(new Phrase("Détails de la ressource génétique demandée et les connaissances traditionnelles associées\n\n", titre));
            document.add(new Phrase("Nom scientifique de la ressource : ", headFontBold));
            document.add(new Phrase(infoPermis.getScientifiqueNom() + "\n\n", headFont));
            document.add(new Phrase("Les zones géographiques de l'accès : ", headFontBold));
            document.add(new Phrase(infoPermis.getZones() + "\n\n", headFont));
            document.add(new Phrase("Nature de la connaissance traditionnelles  : ", headFontBold));
            document.add(new Phrase(infoPermis.getCta() + "\n\n", headFont));
            document.add(new Phrase("Quantité de la ressource à collecter  : ", headFontBold));
            document.add(new Phrase(infoPermis.getQuantite() + "\n\n", headFont));
            document.add(new Phrase("Périodes proposées pour la collecte : ", headFontBold));
            document.add(new Phrase(infoPermis.getDatedebut() + " -- " + infoPermis.getDatefin() + "\n\n", headFont));
            document.add(new Phrase("Activités en vue desquelles la demande est effectuée, objectifs de celle-ci et applications envisagées\n\n", titre));
            document.add(new Phrase("Nature du permis : ", headFontBold));
            document.add(new Phrase("Permis d'accès à but " + infoPermis.getTypeAcces() + "\n\n", headFont));
            document.add(new Phrase("Activités : ", headFontBold));
            document.add(new Phrase(infoPermis.getDescriptionActivites() + "\n\n", headFont));
            document.add(new Phrase("Objectifs demande : ", headFontBold));
            document.add(new Phrase(infoPermis.getObjectifs() + "\n\n", headFont));
            document.add(new Phrase("Applications envisagées  : ", headFontBold));
            document.add(new Phrase(infoPermis.getApplicationsEnvisagees() + "\n\n", headFont));
            if (infoPermis.getMoyensTransport() != null) {
                document.add(new Phrase("Informations sur la circulation des ressources\n\n", titre));
                document.add(new Phrase("Moyens de transport : ", headFontBold));
                document.add(new Phrase(infoPermis.getMoyensTransport() + "\n\n", headFont));
                document.add(new Phrase("Itinéraires de transport : ", headFontBold));
                document.add(new Phrase(infoPermis.getItineraires() + "\n\n", headFont));
            }
            if (infoPermis.getEntSort() != null) {
                document.add(new Phrase("Informations sur le transfert des ressources\n\n", titre));
                document.add(new Phrase("Les points de sortie ou entrée au terretoire national : ", headFontBold));
                document.add(new Phrase(infoPermis.getEntSort() + "\n\n", headFont));
            }
            document.add(new Phrase("Commentaire  : ", headFontBold));
            document.add(new Phrase(infoPermis.getCommentaire() + "\n\n", headFont));
            document.close();
        } catch (DocumentException ex) {
            logger.error("Error occurred: {0}", ex);
        }
        return out.toByteArray();
    }

}
