#! /bin/bash
APP="/Coin-Box"
if [ -e "$APP" ]; then
  cd $APP || exit
  git pull
else
  git clone https://github.com/Pejvak-Charity/Coin-Box
  cd $APP || exit
fi
mvn spring-boot:run && echo "Back end Start"


