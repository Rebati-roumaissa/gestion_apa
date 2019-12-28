package guru.springframework.db;

import guru.springframework.domain.Directory;
import guru.springframework.domain.Permission;
import guru.springframework.domain.Role;
import guru.springframework.domain.Utilisateur;
import guru.springframework.repositories.DirectoryRepository;
import guru.springframework.repositories.PermissionRepo;
import guru.springframework.repositories.RoleRepository;
import guru.springframework.repositories.UtilisateurRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static org.eclipse.jdt.internal.compiler.lookup.TypeConstants.VALUES;

@Service
public class DbInit implements CommandLineRunner {
    private UtilisateurRepository utilisateurRepository;
    private RoleRepository roleRepository;
    private PermissionRepo permissionRepository;
    private PasswordEncoder passwordEncoder;
private DirectoryRepository directoryRepository;
    public DbInit(UtilisateurRepository utilisateurRepository, DirectoryRepository directoryRepository,
                  RoleRepository roleRepository, PermissionRepo permissionRepository, PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.permissionRepository = permissionRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.directoryRepository=directoryRepository;
    }

    @Override
    public void run(String... args) {
        Permission modifier = new Permission("modifier", "modification");
        Permission valider = new Permission("valider", "validation");
        Permission ajouter = new Permission("ajouter", "ajout");

        Permission permission = new Permission("ACCESS_DEMANDEUR", "access pour demandeur permis ");
        Permission permission1 = new Permission("ACCESS_DEMANDES", "access pour gestion de demandes ");
        Permission permission2 = new Permission("ACCESS_Documents", "access pour gestionnaire ged ");

        List<Permission> permissions = Arrays.asList(modifier, valider, ajouter, permission, permission1, permission2);
        permissionRepository.saveAll(permissions);
        // Create Roles
        Role rolea = new Role("Admin", "super admin", 4,(long) 5);
        
        Role roleannuaire = new Role("Admin_annuaire", "Administrateur de annuaire", 4,(long) 5);
        roleannuaire.setPermissions(Arrays.asList(modifier));
        roleannuaire.setPermissions(Arrays.asList(valider));
        roleannuaire.setPermissions(Arrays.asList(ajouter));

        rolea.setPermissions(permissions);
     
        Role role = new Role("DEMANDEUR", "role pour un utilisateur",4, (long) 6);
        role.setPermissions(Arrays.asList(permission));
        Role role1 = new Role("GESTIONNAIRE_PERMIS", "role pour un manager permis",4, (long) 6);
        role1.setPermissions(Arrays.asList(permission1));
        Role role2 = new Role("ADMIN", "role pour un administrateur",4, (long) 6);
        role2.setPermissions(Arrays.asList(permission2, permission1, permission));
        List<Role> roles = Arrays.asList(role, role1, role2, rolea, roleannuaire);
        roleRepository.saveAll(roles);
        Role role3 = new Role("GESTIONNAIRE_DOCUMENTS", "role pour un gestionnaire documents",4, (long) 6);
        role3.setPermissions(Arrays.asList(permissionRepository.findByNom("ACCESS_Documents")));
        roleRepository.save(role3);
        // Create utilisateurs
        Utilisateur admin = new Utilisateur("HAMITTOUCHE", "Thinhinane", passwordEncoder.encode("admin@2019@"), "Admin", Arrays.asList(rolea));
        Utilisateur demandeur = new Utilisateur("REBATI", "Roumaissa", passwordEncoder.encode("romi123"), "Romi", Arrays.asList(role));
        Utilisateur adminannuaire = new Utilisateur("Louail", "Nabil", passwordEncoder.encode("admin@annuaire"), "Admin_annuaire", Arrays.asList(roleannuaire));
        demandeur.setEmail("fr_rebati@esi.dz");
        demandeur.setNss((long)98345678);
        demandeur.setTelephone("0665293723");
        Utilisateur administrateur = new Utilisateur("REBATI", "Rofeida", passwordEncoder.encode("rofi123"), "Rofi", Arrays.asList(role2));
        Utilisateur gestionnaire = new Utilisateur("Dahdouh", "Aicha", passwordEncoder.encode("aicha123"), "Aicha", Arrays.asList(role1));
        List<Utilisateur> utilisateurs = Arrays.asList(admin, demandeur, administrateur, gestionnaire,adminannuaire);
        // Save to db
        this.utilisateurRepository.saveAll(utilisateurs);
        Utilisateur gestionnaireD = new Utilisateur("Rebati", "Bachir", passwordEncoder.encode("bachir123"), "Bachir", Arrays.asList(role3));
        utilisateurRepository.save(gestionnaireD);
        Directory directory = new Directory();
        directory.setChemin("uploads");
        directory.setIdparent(0);
        directory.setTitre("Racine");
        directoryRepository.save(directory);
    }
}