#! /bin/bash
APP="/Coin-Box"
if [ -e "$APP" ]; then
  cd $APP || exit
  git pull
  mvn spring-boot:start && echo "Back end Start"
else
  git clone https://github.com/Pejvak-Charity/Coin-Box
  cd $APP || exit
  mvn spring-boot:stop && echo "Back end Stop"
  mvn spring-boot:start && echo "Back end Start"
fi

