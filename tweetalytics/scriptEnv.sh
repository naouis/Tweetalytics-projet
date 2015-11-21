#!/bin/bash
eb init
aws iam add-role-to-instance-profile --instance-profile-name aws-elasticbeanstalk-service-role --role sacc
