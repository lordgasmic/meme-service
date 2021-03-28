#!/bin/bash

echo "Which type of service are you building?"
echo " [1] - Web Service"
echo " [2] - Repository Service"
read -p "? " SERVICE_TYPE
read -p "Service Name (Template Service): " SERVICE_NAME
read -p "Git Name (template-service): " GIT_NAME
read -p "App Name (TemplateService): " APP_NAME

PACKAGE_NAME=${GIT_NAME//-}

FILES=$(find . -type f -not -path "./.git/*" -not -path "./.idea/*" -not -path ./README.md -not -path ./setup.sh -not -path ./config)

sed -i "s/==GIT_NAME==/$GIT_NAME/g" $FILES
sed -i "s/==SERVICE_NAME==/$SERVICE_NAME/g" $FILES
sed -i "s/==PACKAGE_NAME==/$PACKAGE_NAME/g" Application.java
sed -i "s/==APP_NAME==/$APP_NAME/g" Application.java

if [ $SERVICE_TYPE == 1 ]; then
  rm -rf src/main/java/com/lordgasmic/entity
  rm -rf src/main/java/com/lordgasmic/repository
  sed -i "s/==REPOSITORY==//g" $FILES
  sed -i "s/==MYSQL==//g" $FILES
elif [ $SERVICE_TYPE == 2 ]; then
  MVN_TXT=$(<config/repository.config)
  MYSQL_TXT=$(<config/mysql.config)
  sed -i "s/==REPOSITORY==/$MVN_TXT/g" $FILES
  sed -i "s/==MYSQL==/$MYSQL_TXT/g" $FILES
fi

rm -rf config

cd src/main/java/com/lordgasmic
mv SERVICE $PACKAGE_NAME
cd $PACKAGE_NAME
mv Application.java ${APP_NAME}Application.java

# Goodbye, cruel world!
rm -- "$0"
