sudo docker build -t chatserver .
sudo docker run -d -p 80:9999 --name chatserver  --rm chatserver
