Crazy Drive Server
==================



URI
---


URI path                         |  méthode HTTP  |  Description     
-------------------------------- | -------------- | --------------------------------------------------------------------------
**_/vehicules/[text/]all_**      |  GET           |  retourne toutes les voitures



URI path                                |  méthode HTTP  |  Description     
--------------------------------------- | -------------- | --------------------------------------------------------------------------
**_/vehicule/join_**                    |  GET           |  rejoindre le circuit (retourne un `identifiant`)
**_/vehicule/[text/]{id}/front_**       |  GET           |  avance la voiture (retourne les nouvelles coordonnées)
**_/vehicule/[text/]{id}/back_**        |  GET           |  recule la voiture (retourne les nouvelles coordonnées)
**_/vehicule/[text/]{id}/right_**       |  GET           |  tourne à droite (retourne les nouvelles coordonnées)
**_/vehicule/[text/]{id}/left_**        |  GET           |  tourne à gauche (retourne les nouvelles coordonnées)
**_/vehicule/[text/]{id}/none_**        |  GET           |  indique la voiture est roue libre (retourne les nouvelles coordonnées)
**_/vehicule/[text/]{id}/opponents_**   |  GET           |  retourne tous les opposants
**_/vehicule/{id}/quit_**               |  GET           |  



Les données renvoyées peuvent être soit au format json, xml ou plain-text