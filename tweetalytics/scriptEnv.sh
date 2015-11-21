#!/bin/bash
if [ "$#" != "1" ] 
then
	echo "Erreur: specifiez en argument le role IAM approprie"
else
	rm -R .elasticbeanstalk/
	eb init < ./mesConfigs
	aws iam add-role-to-instance-profile --instance-profile-name aws-elasticbeanstalk-ec2-role --role $1
	eb create --service-role $1 < ./mesConfigs2
fi
