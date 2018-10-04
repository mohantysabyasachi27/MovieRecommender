# MovieRecommender
• A movie showtime finder

Install Docker.. https://store.docker.com/search?type=edition&offering=community

After Docker is installed : 
Docker for Linux - You are good to go. 
Docker for Mac - You can use docker-machine create with the virtualbox driver to create additional local machines.
Run : docker-machine create --driver virtualbox default

Docker for Windows - You can use docker-machine create with the hyperv driver to create additional local machines
Run : docker-machine create --driver hyperv default

For windows & Mac OS: To get the IP address on which the application runs : docker-machine ip 
For Linux: IP is the localhost

Step-1
To run the application : git clone https://github.com/mohantysabyasachi27/MovieRecommender/
Step-2
run this :  sudo docker-compose down && mvn clean install
Step-3
run the docker containers: docker-compose up --build


Once the docker containers start.
Open web browser and hit http://IP:4200/, where IP is obtained from the above steps. 
