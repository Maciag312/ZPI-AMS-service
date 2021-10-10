cd ../zpi-ui
npm install
npm run build
mkdir -p ../ZPI-service/src/main/resources/static
rm -r ../ZPI-service/src/main/resources/static/*
cp -a ./build/. ../ZPI-service/src/main/resources/static/
