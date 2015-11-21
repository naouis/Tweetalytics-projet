# Tweetalytics-projet
Une application cloud native permettant d'exécuter régulièrement des analyses depuis le flux publique de twitter.

##Membres de l'équipe
- Karim GAWISH
- Youssef SALIM
- Ghassan NAOUIS
- Lalaina Lobo RAKOTOARINORO

##URL de l'application
http://tweetalytics-env23.elasticbeanstalk.com/tweetalytics/

##Lien vers le document google doc
https://docs.google.com/document/d/17ysI5ILfmvCGBN8yFZv0DSQn4_iEUJbg7t4DklD5XBc/edit#heading=h.m2pjr2stx7gt

##Pour déployer l'application "from scratch"
- Pour initialiser l'environement, il faut exécuter le script scriptEnv.sh avec pour paramètre un rôle IAM qui a les autorisations nécessaires.
- Il faut ensuite déployer, après avoir modifié le fichier .elasticbeanstalk/config.yml, avec la suite de commandes :
- mvn clean package
- eb deploy
- /!\ Il faut que l'instance Elastic Beanstalk utilisée soit vide d'applications (sinon modifier les fichiers mesConfigs et mesConfigs2).



