Télécharger le fichier projet.zip

Déziper le fichier

Importer sous android studio le android manifest et l'arborescence suivra

Le nom de votre projet sous Android Studio doit s'appeler :

    - projet
    - com.example.projet

On a que 3 fichiers à commit je pense :

    - MainActivity.java
    - AndroidManifest.xml
    - activity_main.xml



Cahier des charges : 

Application Artisanat

    - Page principal avec des boutons
        - Bouton redirection Univ Nantes
        - Bouton redirection GitLab
        - Bouton redirection WebMail
    - Menu Hamburger avec des options différentes
    
    - Menu hamburger avec plusieurs options
        - Settings (avec 2/3 options, permissions d'utiliser lampe torche etc.)
        - Activités  
        - Home 
        
    
    Ativités : Menu comprenant des boutons pour accéder aux activités suivantes
    
    - Activité 1 : Envoie de SmS et appel téléphonique en choisissant un numéro
             
    - Activité 2 : Géolocalisation de la personne pour savoir sur quel chantier on se trouve
                     si la personne sort d'une zone, le téléphone vibre (attention vous entrez dans une zone dangereuse)
                     + envoie d'un SMS, attention quelqu'un entre dans une zone dangereuse)
            
    - Activité 3 : Activité pour lancer la lampe torche. Possibilité de secouer le telephone pour le faire.
                    Activation du vibreur
                    Capteur de luminosité : s'il fait noir, je secoue ça allume
                                    s'il fait jour, je secoue ça éteint la lampe
                                    
    - Activité 4 : Niveau d'artisans avec une bille au milieu. 
                     Le niveau a deux axes.
                     Lorsqu'on pose le téléphone sur une table, le niveau apparait grâce à la bille. 
                     Faire apparaitre les degrés. Et faire vibrer quand la bille est pile au milieu.
    
    - Activité 5 : Mesure de decibel d'un chantier
                    Si le son dépasse, affichage du casque + lancement du vibreur
                    
    - Activité 6 : Transmission d'un signal sonore sur une enceinte.                
            
            
    OPTION : Revoir le graphisme des pages
